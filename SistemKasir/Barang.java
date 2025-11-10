public class Barang {
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

    // Getter
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

    @Override
    public String toString() {
        return namaBarang + " (" + kodeBarang + ") - Rp" + hargaSatuan + " | Stok: " + stok;
    }
}

