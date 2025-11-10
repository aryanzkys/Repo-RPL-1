import java.util.ArrayList;
import java.util.List;

public class Kasir {
    private String namaKasir;
    private String idKasir;
    private List<Transaksi> daftarTransaksi;

    public Kasir(String namaKasir, String idKasir) {
        this.namaKasir = namaKasir;
        this.idKasir = idKasir;
        this.daftarTransaksi = new ArrayList<>();
    }

    public String getNamaKasir() {
        return namaKasir;
    }

    public void setNamaKasir(String namaKasir) {
        this.namaKasir = namaKasir;
    }

    public String getIdKasir() {
        return idKasir;
    }

    public void setIdKasir(String idKasir) {
        this.idKasir = idKasir;
    }

    public List<Transaksi> getDaftarTransaksi() {
        return daftarTransaksi;
    }

    public void tambahTransaksi(Transaksi transaksi) {
        daftarTransaksi.add(transaksi);
    }

    public void tampilkanSemuaTransaksi() {
        System.out.println("=== Daftar Transaksi oleh " + namaKasir + " ===");
        for (Transaksi t : daftarTransaksi) {
            System.out.println("Kode Transaksi: " + t.getKodeTransaksi());
            System.out.println("Tanggal: " + t.getTanggal());
            System.out.println("Total: Rp" + t.hitungTotal());
            System.out.println("------------------------------");
        }
    }

    public double hitungTotalPenjualan() {
        double total = 0;
        for (Transaksi t : daftarTransaksi) {
            total += t.hitungTotal();
        }
        return total;
    }

    public void selesaikanTransaksi(Transaksi transaksi, String metodePembayaran) {
        tambahTransaksi(transaksi);
        System.out.println("Kasir " + namaKasir + " menyelesaikan transaksi.");
        System.out.println("Metode pembayaran: " + metodePembayaran);
        System.out.println("Total dibayar: Rp" + transaksi.hitungTotal());
    }

    @Override
    public String toString() {
        return "Kasir: " + namaKasir + " (ID: " + idKasir + ")";
    }
}

