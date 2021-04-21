package chigirh.app.kakeibo.domain.error.business;

public class AlreadyExistsException extends KakeiboBusinessException {

    private static final long serialVersionUID = 1L;

    public AlreadyExistsException(String message) {
        super(message);
    }
}
