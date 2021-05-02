package chigirh.app.kakeibo.domain.error.system;

/**
 * Kakeibo Application Syste Exception.
 * <p>
 * System Errorは全てこのclassを継承する。
 */
public class KakeiboSystemError extends RuntimeException {

    public KakeiboSystemError(final String message) {
        super(message);
    }
}
