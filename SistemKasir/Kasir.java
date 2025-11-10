import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Kasir {
    private final String idKasir;
    private final String namaKasir;
    private final String username;
    private final String password;
    private final List<Transaksi> riwayatTransaksi;

    public Kasir(String idKasir, String namaKasir, String username, String password) {
        this.idKasir = idKasir;
        this.namaKasir = namaKasir;
        this.username = username;
        this.password = password;
        this.riwayatTransaksi = new CopyOnWriteArrayList<>();
    }

    public String getNamaKasir() {
        return namaKasir;
    }

    public String getIdKasir() {
        return idKasir;
    }

    public String getUsername() {
        return username;
    }

    public boolean cocokkanKredensial(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        return this.username.equalsIgnoreCase(username) && this.password.equals(password);
    }

    public List<Transaksi> getRiwayatTransaksi() {
        return Collections.unmodifiableList(riwayatTransaksi);
    }

    public void tampilkanSemuaTransaksi() {
        System.out.println("=== Riwayat Transaksi Kasir " + namaKasir + " ===");
        if (riwayatTransaksi.isEmpty()) {
            System.out.println("(Belum ada transaksi)");
            return;
        }
        for (Transaksi transaksi : riwayatTransaksi) {
            transaksi.tampilkanDetail();
            System.out.println("------------------------------");
        }
    }

    public double hitungTotalPenjualanSelesai() {
        double total = 0;
        for (Transaksi transaksi : riwayatTransaksi) {
            if (transaksi.getStatus() == Transaksi.StatusTransaksi.COMPLETED) {
                total += transaksi.hitungTotal();
            }
        }
        return total;
    }

    public boolean selesaikanTransaksi(Transaksi transaksi, String metodePembayaran, Inventory inventory, RiwayatTransaksi riwayatGlobal) {
        if (transaksi == null || inventory == null) {
            return false;
        }
        if (metodePembayaran != null && !metodePembayaran.isBlank()) {
            transaksi.pilihMetodePembayaran(metodePembayaran);
        }
        boolean berhasil = transaksi.selesaikan(inventory);
        if (berhasil) {
            riwayatTransaksi.add(transaksi);
            if (riwayatGlobal != null) {
                riwayatGlobal.simpanTransaksi(this, transaksi);
            }
        }
        return berhasil;
    }

    @Override
    public String toString() {
        return "Kasir: " + namaKasir + " (ID: " + idKasir + ")";
    }
}

