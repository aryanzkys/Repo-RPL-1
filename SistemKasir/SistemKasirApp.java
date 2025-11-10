import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

public class SistemKasirApp {
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        Inventory inventory = siapkanInventoryAwal();
        DiskonService diskonService = siapkanDiskon();
        KasirService kasirService = siapkanKasirService();
        RiwayatTransaksi riwayatTransaksi = new RiwayatTransaksi();

        System.out.println("===== SISTEM KASIR ZAKY-MART =====");
        KasirSession session = prosesLogin(kasirService);
        if (session == null) {
            System.out.println("Login gagal. Program dihentikan.");
            return;
        }
        System.out.println("Login berhasil. Session ID: " + session.getSessionId());

        boolean berjalan = true;
        while (berjalan) {
            tampilkanMenu();
            System.out.print("Pilih menu: ");
            int pilihan = bacaAngka(INPUT.nextLine());
            switch (pilihan) {
                case 1 -> mulaiTransaksiBaru(session);
                case 2 -> inputBarangKeTransaksi(session, inventory);
                case 3 -> terapkanDiskonPadaTransaksi(session, diskonService);
                case 4 -> pilihMetodePembayaran(session);
                case 5 -> selesaikanTransaksi(session, inventory, riwayatTransaksi);
                case 6 -> tampilkanRingkasanTransaksiAktif(session);
                case 7 -> session.getKasir().tampilkanSemuaTransaksi();
                case 8 -> inventory.tampilkanDaftarBarang();
                case 9 -> riwayatTransaksi.tampilkanRiwayat();
                case 0 -> {
                    kasirService.logout(session.getSessionId());
                    berjalan = false;
                }
                default -> System.out.println("Pilihan tidak dikenal.");
            }
        }

        System.out.println("Terima kasih. Sampai jumpa!");
    }

    private static Inventory siapkanInventoryAwal() {
        Inventory inventory = new Inventory();
        inventory.tambahBarang(new Barang("BRG01", "Sabun Mandi", 5000, 25));
        inventory.tambahBarang(new Barang("BRG02", "Sampo", 12000, 15));
        inventory.tambahBarang(new Barang("BRG03", "Pasta Gigi", 9000, 20));
        inventory.tambahBarang(new Barang("BRG04", "Tissue", 8000, 30));
        inventory.tambahBarang(new Barang("BRG05", "Hand Sanitizer", 15000, 18));
        return inventory;
    }

    private static DiskonService siapkanDiskon() {
        DiskonService diskonService = new DiskonService();
        diskonService.tambahDiskon(new DiskonService.Diskon("HEMAT10", 10, 0, 20000));
        diskonService.tambahDiskon(new DiskonService.Diskon("LEBIHHEMAT", 0, 5000, 0));
        diskonService.tambahDiskon(new DiskonService.Diskon("SUPER20", 20, 0, 50000));
        return diskonService;
    }

    private static KasirService siapkanKasirService() {
        KasirService kasirService = new KasirService();
        kasirService.registrasiKasir(new Kasir("KSR001", "Aryan", "aryan", "12345"));
        kasirService.registrasiKasir(new Kasir("KSR002", "Zaky", "zaky", "54321"));
        return kasirService;
    }

    private static KasirSession prosesLogin(KasirService kasirService) {
        for (int percobaan = 1; percobaan <= 3; percobaan++) {
            System.out.print("Username kasir: ");
            String username = INPUT.nextLine();
            System.out.print("Password: ");
            String password = INPUT.nextLine();

            KasirSession session = kasirService.login(username, password);
            if (session != null) {
                return session;
            }
            System.out.println("Kredensial salah. Sisa percobaan: " + (3 - percobaan));
        }
        return null;
    }

    private static void tampilkanMenu() {
        System.out.println();
        System.out.println("=== MENU SISTEM KASIR ===");
        System.out.println("1. Mulai transaksi baru");
        System.out.println("2. Tambah barang ke transaksi aktif");
        System.out.println("3. Terapkan atau hapus diskon");
        System.out.println("4. Pilih metode pembayaran");
        System.out.println("5. Selesaikan transaksi");
        System.out.println("6. Lihat ringkasan transaksi aktif");
        System.out.println("7. Lihat riwayat transaksi kasir");
        System.out.println("8. Lihat stok barang");
        System.out.println("9. Lihat riwayat transaksi sistem");
        System.out.println("0. Logout dan keluar");
    }

    private static void mulaiTransaksiBaru(KasirSession session) {
        if (session.memilikiTransaksiAktif()) {
            System.out.print("Transaksi sebelumnya belum selesai. Ganti? (y/n): ");
            if (!jawabanYa(INPUT.nextLine())) {
                return;
            }
        }
        String kodeTransaksi = generateKodeTransaksi(session.getKasir().getIdKasir());
        Transaksi transaksiBaru = new Transaksi(kodeTransaksi, LocalDateTime.now());
        session.setTransaksiAktif(transaksiBaru);
        System.out.println("Transaksi baru dimulai dengan kode: " + kodeTransaksi);
    }

    private static void inputBarangKeTransaksi(KasirSession session, Inventory inventory) {
        Transaksi transaksi = session.getTransaksiAktif();
        if (transaksi == null || transaksi.getStatus() != Transaksi.StatusTransaksi.PENDING) {
            System.out.println("Tidak ada transaksi aktif. Mulai transaksi baru terlebih dahulu.");
            return;
        }

        inventory.tampilkanDaftarBarang();
        System.out.print("Masukkan kode barang: ");
        String kodeBarang = INPUT.nextLine();
        Barang barang = inventory.getBarang(kodeBarang);
        if (barang == null) {
            System.out.println("Barang dengan kode tersebut tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan jumlah: ");
        int jumlah = bacaAngka(INPUT.nextLine());
        if (jumlah <= 0) {
            System.out.println("Jumlah harus lebih dari nol.");
            return;
        }

        int jumlahSudahAda = transaksi.getDaftarItem().stream()
                .filter(item -> item.getKodeBarang().equalsIgnoreCase(barang.getKodeBarang()))
                .mapToInt(ItemTransaksi::getJumlah)
                .findFirst()
                .orElse(0);

        if (!barang.stokCukup(jumlah + jumlahSudahAda)) {
            System.out.println("Stok tidak mencukupi. Stok tersedia: " + barang.getStok());
            return;
        }

        if (transaksi.tambahAtauPerbaruiItem(barang, jumlah)) {
            System.out.println("Barang berhasil ditambahkan ke transaksi.");
        } else {
            System.out.println("Gagal menambahkan barang ke transaksi.");
        }
    }

    private static void terapkanDiskonPadaTransaksi(KasirSession session, DiskonService diskonService) {
        Transaksi transaksi = session.getTransaksiAktif();
        if (transaksi == null || transaksi.getStatus() != Transaksi.StatusTransaksi.PENDING) {
            System.out.println("Tidak ada transaksi aktif.");
            return;
        }
        if (transaksi.getDaftarItem().isEmpty()) {
            System.out.println("Tambahkan barang terlebih dahulu sebelum menerapkan diskon.");
            return;
        }

        diskonService.tampilkanDiskon();
        System.out.print("Masukkan kode diskon (kosongkan untuk menghapus): ");
        String kodeDiskon = INPUT.nextLine().trim();

        if (kodeDiskon.isEmpty()) {
            transaksi.hapusDiskon();
            System.out.println("Diskon dibatalkan.");
            return;
        }

        DiskonService.Diskon diskon = diskonService.cariDiskon(kodeDiskon);
        if (diskon == null) {
            System.out.println("Kode diskon tidak valid.");
            return;
        }

        transaksi.terapkanDiskon(diskon);
        System.out.println("Diskon berhasil diterapkan.");
    }

    private static void pilihMetodePembayaran(KasirSession session) {
        Transaksi transaksi = session.getTransaksiAktif();
        if (transaksi == null || transaksi.getStatus() != Transaksi.StatusTransaksi.PENDING) {
            System.out.println("Tidak ada transaksi aktif.");
            return;
        }

        System.out.println("Pilih metode pembayaran:");
        System.out.println("1. Cash");
        System.out.println("2. Kartu debit/kredit");
        System.out.println("3. E-Wallet");
        System.out.print("Pilihan: ");
        int pilihan = bacaAngka(INPUT.nextLine());
        String metode;
        switch (pilihan) {
            case 1 -> metode = "Cash";
            case 2 -> metode = "Card";
            case 3 -> metode = "E-Wallet";
            default -> {
                System.out.println("Metode tidak dikenali.");
                return;
            }
        }
        transaksi.pilihMetodePembayaran(metode);
        System.out.println("Metode pembayaran diset ke: " + metode);
    }

    private static void selesaikanTransaksi(KasirSession session, Inventory inventory, RiwayatTransaksi riwayatTransaksi) {
        Transaksi transaksi = session.getTransaksiAktif();
        if (transaksi == null || transaksi.getStatus() != Transaksi.StatusTransaksi.PENDING) {
            System.out.println("Tidak ada transaksi aktif.");
            return;
        }
        if (transaksi.getDaftarItem().isEmpty()) {
            System.out.println("Transaksi belum memiliki barang.");
            return;
        }
        if (transaksi.getMetodePembayaran() == null || transaksi.getMetodePembayaran().isBlank()) {
            System.out.println("Metode pembayaran belum dipilih.");
            pilihMetodePembayaran(session);
            if (transaksi.getMetodePembayaran() == null || transaksi.getMetodePembayaran().isBlank()) {
                System.out.println("Transaksi belum dapat diselesaikan.");
                return;
            }
        }

        boolean sukses = session.getKasir().selesaikanTransaksi(transaksi, transaksi.getMetodePembayaran(), inventory, riwayatTransaksi);
        if (sukses) {
            System.out.println("Transaksi " + transaksi.getKodeTransaksi() + " selesai.");
            System.out.println("Total pembayaran: Rp" + transaksi.hitungTotal());
            session.setTransaksiAktif(null);
        } else {
            System.out.println("Transaksi gagal diselesaikan. Periksa stok barang.");
        }
    }

    private static void tampilkanRingkasanTransaksiAktif(KasirSession session) {
        Transaksi transaksi = session.getTransaksiAktif();
        if (transaksi == null) {
            System.out.println("Tidak ada transaksi aktif.");
            return;
        }
        transaksi.tampilkanDetail();
    }

    private static boolean jawabanYa(String jawaban) {
        return jawaban != null && jawaban.trim().equalsIgnoreCase("y");
    }

    private static int bacaAngka(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static String generateKodeTransaksi(String idKasir) {
        String prefix = Optional.ofNullable(idKasir).orElse("TRX");
        return prefix + "-" + System.currentTimeMillis();
    }
}

