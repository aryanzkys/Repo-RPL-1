import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DiskonService {
    public static class Diskon {
        private final String kode;
        private final double persentase;
        private final double potonganTetap;
        private final double maksimumPotongan;

        public Diskon(String kode, double persentase, double potonganTetap, double maksimumPotongan) {
            this.kode = kode == null ? "" : kode.toUpperCase();
            this.persentase = Math.max(0, persentase);
            this.potonganTetap = Math.max(0, potonganTetap);
            this.maksimumPotongan = Math.max(0, maksimumPotongan);
        }

        public String kode() {
            return kode;
        }

        public double hitungPotongan(double total) {
            double potongan = potonganTetap;
            if (persentase > 0) {
                potongan += (persentase / 100.0) * total;
            }
            if (maksimumPotongan > 0) {
                potongan = Math.min(potongan, maksimumPotongan);
            }
            return Math.min(potongan, Math.max(total, 0));
        }

        @Override
        public String toString() {
            return "Kode " + kode + " - Potongan " + persentase + "% + Rp" + potonganTetap +
                    (maksimumPotongan > 0 ? " (maks Rp" + maksimumPotongan + ")" : "");
        }
    }

    private final Map<String, Diskon> diskonMap = new ConcurrentHashMap<>();

    public void tambahDiskon(Diskon diskon) {
        if (diskon != null) {
            diskonMap.put(diskon.kode(), diskon);
        }
    }

    public Diskon cariDiskon(String kodeDiskon) {
        if (kodeDiskon == null) {
            return null;
        }
        return diskonMap.get(kodeDiskon.toUpperCase());
    }

    public void tampilkanDiskon() {
        System.out.println("=== Daftar Diskon Aktif ===");
        if (diskonMap.isEmpty()) {
            System.out.println("(Tidak ada diskon)");
            return;
        }
        for (Diskon diskon : diskonMap.values()) {
            System.out.println(diskon);
        }
    }
}
