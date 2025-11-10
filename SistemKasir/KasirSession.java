public class KasirSession {
    private final String sessionId;
    private final Kasir kasir;
    private Transaksi transaksiAktif;

    public KasirSession(String sessionId, Kasir kasir) {
        this.sessionId = sessionId;
        this.kasir = kasir;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Kasir getKasir() {
        return kasir;
    }

    public synchronized Transaksi getTransaksiAktif() {
        return transaksiAktif;
    }

    public synchronized void setTransaksiAktif(Transaksi transaksiAktif) {
        this.transaksiAktif = transaksiAktif;
    }

    public synchronized boolean memilikiTransaksiAktif() {
        return transaksiAktif != null && transaksiAktif.getStatus() == Transaksi.StatusTransaksi.PENDING;
    }
}
