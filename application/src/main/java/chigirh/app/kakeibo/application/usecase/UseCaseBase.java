package chigirh.app.kakeibo.application.usecase;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class UseCaseBase<I extends UseCaseBase.Input, O extends UseCaseBase.Output> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UseCaseBase.class);

    @NonNull
    protected final O invoke(final I input) {
        LOGGER.info(useCaseName());
        return process(input);
    }

    protected abstract O process(I input);

    protected abstract String useCaseName();

    public interface Input {
    }

    public interface Output {
    }

    // UseCaseの戻り値がない場合はこのclassを利用する
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Empty implements Output {
        public static Empty instance() {
            return new Empty();
        }
    }

}
