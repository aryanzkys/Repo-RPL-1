import java.util.concurrent.atomic.AtomicInteger;

public class Barang {
    private final String kodeBarang;
    private String namaBarang;
    private double hargaSatuan;
    private final AtomicInteger stok;

    public Barang(String kodeBarang, String namaBarang, double hargaSatuan, int stokAwal) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaSatuan = hargaSatuan;
        this.stok = new AtomicInteger(Math.max(stokAwal, 0));
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public double getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(double hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    public int getStok() {
        return stok.get();
    }

    public boolean stokCukup(int jumlah) {
        return jumlah > 0 && stok.get() >= jumlah;
    }

    public boolean kurangiStok(int jumlah) {
        if (jumlah <= 0) {
            return false;
        }
        while (true) {
            int stokSaatIni = stok.get();
            if (stokSaatIni < jumlah) {
                return false;
            }
            if (stok.compareAndSet(stokSaatIni, stokSaatIni - jumlah)) {
                return true;
            }
        }
    }

    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            stok.addAndGet(jumlah);
        }
    }

    @Override
    public String toString() {
        return namaBarang + " (" + kodeBarang + ") - Rp" + hargaSatuan + " | Stok: " + stok.get();
    }
}

