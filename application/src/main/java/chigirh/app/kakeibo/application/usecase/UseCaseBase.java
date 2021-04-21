package chigirh.app.kakeibo.application.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class UseCaseBase<I extends UseCaseBase.UpdateInput, O> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UseCaseBase.class);

    protected final O invoke(I input) {
        LOGGER.info(useCaseName());
        return process(input);
    }

    protected abstract O process(I input);

    protected abstract String useCaseName();

    public interface UpdateInput {
    }

}
