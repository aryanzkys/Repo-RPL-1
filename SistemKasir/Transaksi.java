import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaksi {
    private String kodeTransaksi;
    private Date tanggal;
    private List<ItemTransaksi> daftarItem;

    public Transaksi(String kodeTransaksi, Date tanggal) {
        this.kodeTransaksi = kodeTransaksi;
        this.tanggal = tanggal;
        this.daftarItem = new ArrayList<>();
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public List<ItemTransaksi> getDaftarItem() {
        return daftarItem;
    }

    public void tambahItem(ItemTransaksi item) {
        daftarItem.add(item);
    }

    public double hitungTotal() {
        double total = 0;
        for (ItemTransaksi item : daftarItem) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void tampilkanDetail() {
        System.out.println("Kode Transaksi: " + kodeTransaksi);
        System.out.println("Tanggal: " + tanggal);
        System.out.println("Daftar Item:");
        for (ItemTransaksi item : daftarItem) {
            item.tampilkanInfoItem();
        }
        System.out.println("Total Transaksi: Rp" + hitungTotal());
    }

    @Override
    public String toString() {
        return "Transaksi{" +
                "kodeTransaksi='" + kodeTransaksi + '\'' +
                ", tanggal=" + tanggal +
                ", total=" + hitungTotal() +
                '}';
    }
}

