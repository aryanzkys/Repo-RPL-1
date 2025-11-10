import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    private final Map<String, Barang> dataBarang = new ConcurrentHashMap<>();

    public void tambahBarang(Barang barang) {
        if (barang != null) {
            dataBarang.put(barang.getKodeBarang().toUpperCase(), barang);
        }
    }

    public Barang getBarang(String kodeBarang) {
        if (kodeBarang == null) {
            return null;
        }
        return dataBarang.get(kodeBarang.toUpperCase());
    }

    public Collection<Barang> getSemuaBarang() {
        return Collections.unmodifiableCollection(dataBarang.values());
    }

    public void tampilkanDaftarBarang() {
        System.out.println("=== Daftar Barang ===");
        if (dataBarang.isEmpty()) {
            System.out.println("(Belum ada barang)");
            return;
        }
        for (Barang barang : dataBarang.values()) {
            System.out.println(barang);
        }
    }
}
