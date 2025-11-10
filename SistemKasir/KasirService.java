import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class KasirService {
    private final Map<String, Kasir> kasirByUsername = new ConcurrentHashMap<>();
    private final Map<String, KasirSession> sesiAktif = new ConcurrentHashMap<>();

    public void registrasiKasir(Kasir kasir) {
        if (kasir != null) {
            kasirByUsername.put(kasir.getUsername().toLowerCase(), kasir);
        }
    }

    public KasirSession login(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        Kasir kasir = kasirByUsername.get(username.toLowerCase());
        if (kasir != null && kasir.cocokkanKredensial(username, password)) {
            String sessionId = UUID.randomUUID().toString();
            KasirSession session = new KasirSession(sessionId, kasir);
            sesiAktif.put(sessionId, session);
            return session;
        }
        return null;
    }

    public KasirSession getSession(String sessionId) {
        if (sessionId == null) {
            return null;
        }
        return sesiAktif.get(sessionId);
    }

    public void logout(String sessionId) {
        if (sessionId != null) {
            sesiAktif.remove(sessionId);
        }
    }
}
