package chigirh.app.kakeibo.application.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class UpdateUsecase<I extends UpdateUsecase.UpdateInput, O>
        implements UseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateUsecase.class);

    protected final O invoke(I input) {
        LOGGER.info(useCaseName());
        return process(input);
    }

    protected abstract O process(I input);

    protected abstract String useCaseName();

    public interface UpdateInput {
    }

}
