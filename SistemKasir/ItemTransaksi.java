public class ItemTransaksi {
    private Barang barang;     
    private int jumlah;        
    private double subtotal;   

    public ItemTransaksi(Barang barang, int jumlah) {
        this.barang = barang;
        this.jumlah = jumlah;
        this.subtotal = barang.getHargaSatuan() * jumlah;
    }

    public Barang getBarang() {
        return barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getHargaSatuan() {
        return barang.getHargaSatuan();
    }

    public String getNamaBarang() {
        return barang.getNamaBarang();
    }

    public void setJumlah(int jumlahBaru) {
        this.jumlah = jumlahBaru;
        this.subtotal = barang.getHargaSatuan() * jumlahBaru;
    }

    public void tampilkanInfoItem() {
        System.out.println(barang.getNamaBarang() + " x" + jumlah + " = Rp " + subtotal);
    }
}

