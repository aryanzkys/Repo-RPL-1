public class ItemTransaksi {
    private final String kodeBarang;
    private final String namaBarang;
    private final double hargaSatuan;
    private int jumlah;

    public ItemTransaksi(Barang barang, int jumlah) {
        this(barang.getKodeBarang(), barang.getNamaBarang(), barang.getHargaSatuan(), jumlah);
    }

    public ItemTransaksi(String kodeBarang, String namaBarang, double hargaSatuan, int jumlah) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaSatuan = hargaSatuan;
        this.jumlah = Math.max(jumlah, 0);
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

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        if (jumlah > 0) {
            this.jumlah = jumlah;
        }
    }

    public double getSubtotal() {
        return hargaSatuan * jumlah;
    }

    public void tampilkanInfoItem() {
        System.out.println(namaBarang + " x" + jumlah + " = Rp " + getSubtotal());
    }
}

