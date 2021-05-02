package chigirh.app.kakeibo.domain.error.business;

/**
 * Kakeibo Application Business Exception.
 * <p>
 * business Exceptionは全てこのclassを継承する。
 */
public class KakeiboBusinessException extends RuntimeException {

    public KakeiboBusinessException(final String message) {
        super(message);
    }
}
