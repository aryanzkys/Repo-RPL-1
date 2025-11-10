import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transaksi {
    public enum StatusTransaksi {
        PENDING,
        COMPLETED,
        CANCELLED
    }

    private final String kodeTransaksi;
    private final LocalDateTime waktuMulai;
    private LocalDateTime waktuSelesai;
    private final List<ItemTransaksi> daftarItem;
    private StatusTransaksi status;
    private String metodePembayaran;
    private String kodeDiskon;
    private double nilaiDiskon;

    public Transaksi(String kodeTransaksi, LocalDateTime waktuMulai) {
        this.kodeTransaksi = kodeTransaksi;
        this.waktuMulai = waktuMulai;
        this.daftarItem = new ArrayList<>();
        this.status = StatusTransaksi.PENDING;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public LocalDateTime getWaktuMulai() {
        return waktuMulai;
    }

    public LocalDateTime getWaktuSelesai() {
        return waktuSelesai;
    }

    public StatusTransaksi getStatus() {
        return status;
    }

    public List<ItemTransaksi> getDaftarItem() {
        return Collections.unmodifiableList(daftarItem);
    }

    public double getNilaiDiskon() {
        return nilaiDiskon;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public String getKodeDiskon() {
        return kodeDiskon;
    }

    public boolean tambahAtauPerbaruiItem(Barang barang, int jumlah) {
        if (status != StatusTransaksi.PENDING || barang == null || jumlah <= 0) {
            return false;
        }

        for (ItemTransaksi item : daftarItem) {
            if (item.getKodeBarang().equalsIgnoreCase(barang.getKodeBarang())) {
                item.setJumlah(item.getJumlah() + jumlah);
                return true;
            }
        }

        daftarItem.add(new ItemTransaksi(barang, jumlah));
        return true;
    }

    public boolean ubahJumlahItem(String kodeBarang, int jumlahBaru) {
        if (status != StatusTransaksi.PENDING || jumlahBaru <= 0) {
            return false;
        }
        for (ItemTransaksi item : daftarItem) {
            if (item.getKodeBarang().equalsIgnoreCase(kodeBarang)) {
                item.setJumlah(jumlahBaru);
                return true;
            }
        }
        return false;
    }

    public boolean hapusItem(String kodeBarang) {
        if (status != StatusTransaksi.PENDING) {
            return false;
        }
        return daftarItem.removeIf(item -> item.getKodeBarang().equalsIgnoreCase(kodeBarang));
    }

    public double hitungTotalSebelumDiskon() {
        double total = 0;
        for (ItemTransaksi item : daftarItem) {
            total += item.getSubtotal();
        }
        return total;
    }

    public double hitungTotal() {
        double total = hitungTotalSebelumDiskon();
        double potongan = Math.min(nilaiDiskon, total);
        return total - potongan;
    }

    public void terapkanDiskon(DiskonService.Diskon diskon) {
        if (status != StatusTransaksi.PENDING || diskon == null) {
            return;
        }
        double total = hitungTotalSebelumDiskon();
        double potongan = diskon.hitungPotongan(total);
        this.nilaiDiskon = potongan;
        this.kodeDiskon = diskon.kode();
    }

    public void hapusDiskon() {
        if (status == StatusTransaksi.PENDING) {
            this.nilaiDiskon = 0;
            this.kodeDiskon = null;
        }
    }

    public void pilihMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public boolean selesaikan(Inventory inventory) {
        if (status != StatusTransaksi.PENDING || daftarItem.isEmpty()) {
            return false;
        }
        if (inventory == null) {
            return false;
        }

        List<Barang> barangUntukDikurangi = new ArrayList<>();
        for (ItemTransaksi item : daftarItem) {
            Barang barang = inventory.getBarang(item.getKodeBarang());
            if (barang == null || !barang.stokCukup(item.getJumlah())) {
                return false;
            }
            barangUntukDikurangi.add(barang);
        }

        for (int i = 0; i < daftarItem.size(); i++) {
            Barang barang = barangUntukDikurangi.get(i);
            ItemTransaksi item = daftarItem.get(i);
            barang.kurangiStok(item.getJumlah());
        }

        this.status = StatusTransaksi.COMPLETED;
        this.waktuSelesai = LocalDateTime.now();
        return true;
    }

    public void tampilkanDetail() {
        System.out.println("Kode Transaksi: " + kodeTransaksi);
        System.out.println("Status: " + status);
        System.out.println("Mulai: " + waktuMulai);
        if (waktuSelesai != null) {
            System.out.println("Selesai: " + waktuSelesai);
        }
        System.out.println("Metode Pembayaran: " + (metodePembayaran == null ? "-" : metodePembayaran));
        System.out.println("Daftar Item:");
        if (daftarItem.isEmpty()) {
            System.out.println("(Belum ada item)");
        }
        for (ItemTransaksi item : daftarItem) {
            item.tampilkanInfoItem();
        }
        System.out.println("Subtotal: Rp" + hitungTotalSebelumDiskon());
        System.out.println("Diskon: Rp" + nilaiDiskon + (kodeDiskon != null ? " (" + kodeDiskon + ")" : ""));
        System.out.println("Total: Rp" + hitungTotal());
    }

    @Override
    public String toString() {
        return "Transaksi{" +
                "kodeTransaksi='" + kodeTransaksi + '\'' +
                ", status=" + status +
                ", total=" + hitungTotal() +
                '}';
    }
}

