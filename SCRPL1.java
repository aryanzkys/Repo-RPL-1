import java.util.*;

class Barang {
    private String kodeBarang;
    private String namaBarang;
    private double hargaSatuan;
    private int stok;

    public Barang(String kodeBarang, String namaBarang, double hargaSatuan, int stok) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaSatuan = hargaSatuan;
        this.stok = stok;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public double getHargaSatuan() {
        return hargaSatuan;
    }

    public int getStok() {
        return stok;
    }

    public void kurangiStok(int jumlah) {
        if (stok >= jumlah) {
            stok -= jumlah;
        } else {
            System.out.println("Stok tidak mencukupi untuk barang: " + namaBarang);
        }
    }
}

class ItemTransaksi {
    private Barang barang;
    private int jumlah;
    private double subtotal;

    public ItemTransaksi(Barang barang, int jumlah) {
        this.barang = barang;
        this.jumlah = jumlah;
        this.subtotal = barang.getHargaSatuan() * jumlah;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Barang getBarang() {
        return barang;
    }

    public int getJumlah() {
        return jumlah;
    }
}

class Transaksi {
    private String idTransaksi;
    private Date waktu;
    private List<ItemTransaksi> daftarItem;
    private double totalHarga;
    private String metodePembayaran;
    private String status;

    public Transaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
        this.waktu = new Date();
        this.daftarItem = new ArrayList<>();
        this.status = "Pending";
    }

    public void tambahItem(Barang barang, int jumlah) {
        if (barang.getStok() >= jumlah) {
            ItemTransaksi item = new ItemTransaksi(barang, jumlah);
            daftarItem.add(item);
            barang.kurangiStok(jumlah);
        } else {
            System.out.println("Gagal menambah item. Stok tidak cukup.");
        }
    }

    public void hitungTotal() {
        totalHarga = 0;
        for (ItemTransaksi item : daftarItem) {
            totalHarga += item.getSubtotal();
        }
    }

    public void selesaikanTransaksi(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
        this.status = "Completed";
        hitungTotal();
        System.out.println("Transaksi selesai. Total yang harus dibayar: Rp " + totalHarga);
    }
}

class Kasir {
    private String namaKasir;

    public Kasir(String namaKasir) {
        this.namaKasir = namaKasir;
    }

    public Transaksi buatTransaksi(String idTransaksi) {
        System.out.println("Kasir " + namaKasir + " memulai transaksi baru.");
        return new Transaksi(idTransaksi);
    }
}

public class SCRPL1 {
    public static void main(String[] args) {
        Barang barang1 = new Barang("BRG01", "Sabun Mandi", 5000, 10);
        Barang barang2 = new Barang("BRG02", "Sampo", 12000, 8);

        Kasir kasir = new Kasir("Aryan");
        Transaksi transaksi = kasir.buatTransaksi("TRX001");

        transaksi.tambahItem(barang1, 2);
        transaksi.tambahItem(barang2, 1);

        transaksi.selesaikanTransaksi("Tunai");
    }
}
