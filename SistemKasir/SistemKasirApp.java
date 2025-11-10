import java.util.Date;
import java.util.Scanner;

public class SistemKasirApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Kasir kasir = new Kasir("Aryan", "KSR001");

        System.out.println("===== SISTEM KASIR ZAKY-MART =====");
        System.out.print("Masukkan kode transaksi: ");
        String kodeTransaksi = input.nextLine();

        Transaksi transaksi = new Transaksi(kodeTransaksi, new Date());

        System.out.println("Kasir " + kasir.getNamaKasir() + " memulai transaksi baru pada " + new Date());
        System.out.println("----------------------------------");

        Barang barang1 = new Barang("BRG01", "Sabun Mandi", 5000, 10);
        Barang barang2 = new Barang("BRG02", "Sampo", 12000, 8);
        Barang barang3 = new Barang("BRG03", "Pasta Gigi", 9000, 5);

        boolean lanjut = true;

        while (lanjut) {
            System.out.println("\nDaftar Barang Tersedia:");
            System.out.println("1. " + barang1);
            System.out.println("2. " + barang2);
            System.out.println("3. " + barang3);

            System.out.print("\nPilih barang (1-3): ");
            int pilihan = input.nextInt();

            Barang barangDipilih = null;

            switch (pilihan) {
                case 1 -> barangDipilih = barang1;
                case 2 -> barangDipilih = barang2;
                case 3 -> barangDipilih = barang3;
                default -> System.out.println("Pilihan tidak valid!");
            }

            if (barangDipilih != null) {
                System.out.print("Masukkan jumlah pembelian: ");
                int jumlah = input.nextInt();

                if (jumlah > barangDipilih.getStok()) {
                    System.out.println("Stok tidak mencukupi! Stok tersedia: " + barangDipilih.getStok());
                } else {
                    barangDipilih.kurangiStok(jumlah);
                    ItemTransaksi item = new ItemTransaksi(barangDipilih, jumlah);
                    transaksi.tambahItem(item);
                    System.out.println("Item berhasil ditambahkan ke transaksi.");
                }
            }

            System.out.print("\nApakah ingin menambah barang lagi? (y/n): ");
            char jawab = input.next().toLowerCase().charAt(0);
            lanjut = (jawab == 'y');
        }

        System.out.println("\n===== RINCIAN TRANSAKSI =====");
        transaksi.tampilkanDetail();

        System.out.print("\nMasukkan metode pembayaran (Tunai / Non-Tunai): ");
        input.nextLine(); 
        String metode = input.nextLine();

        kasir.selesaikanTransaksi(transaksi, metode);

        System.out.println("\n===== TRANSAKSI SELESAI =====");
        System.out.println("Terima kasih telah berbelanja!");
        input.close();
    }
}

