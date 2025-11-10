import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RiwayatTransaksi {
    public static class RekamTransaksi {
        private final String idKasir;
        private final String namaKasir;
        private final Transaksi transaksi;
        private final LocalDateTime dicatatPada;

        public RekamTransaksi(String idKasir, String namaKasir, Transaksi transaksi, LocalDateTime dicatatPada) {
            this.idKasir = idKasir;
            this.namaKasir = namaKasir;
            this.transaksi = transaksi;
            this.dicatatPada = dicatatPada;
        }

        public String getIdKasir() {
            return idKasir;
        }

        public String getNamaKasir() {
            return namaKasir;
        }

        public Transaksi getTransaksi() {
            return transaksi;
        }

        public LocalDateTime getDicatatPada() {
            return dicatatPada;
        }
    }

    private final List<RekamTransaksi> riwayat = new CopyOnWriteArrayList<>();

    public void simpanTransaksi(Kasir kasir, Transaksi transaksi) {
        if (kasir == null || transaksi == null) {
            return;
        }
        riwayat.add(new RekamTransaksi(kasir.getIdKasir(), kasir.getNamaKasir(), transaksi, LocalDateTime.now()));
    }

    public List<RekamTransaksi> getRiwayat() {
        return Collections.unmodifiableList(riwayat);
    }

    public void tampilkanRiwayat() {
        System.out.println("=== Riwayat Transaksi Sistem ===");
        if (riwayat.isEmpty()) {
            System.out.println("(Belum ada transaksi)");
            return;
        }
        for (RekamTransaksi rekam : riwayat) {
            System.out.println("Kasir: " + rekam.getNamaKasir() + " (" + rekam.getIdKasir() + ")");
            rekam.getTransaksi().tampilkanDetail();
            System.out.println("Dicatat pada: " + rekam.getDicatatPada());
            System.out.println("------------------------------");
        }
    }
}
