package chigirh.app.kakeibo.domain.error.system;

public class ResourcesAccessError extends KakeiboSystemError {

    private static final long serialVersionUID = 1L;

    private final Throwable th;

    public ResourcesAccessError(Throwable th, String message) {
        super(message != null ? message : th.getMessage());
        this.th = th;
    }
}
