package chigirh.app.kakeibo.application.usecase.sample;

import chigirh.app.kakeibo.application.repository.sample.SampleRepository;
import chigirh.app.kakeibo.application.usecase.UseCase;
import chigirh.app.kakeibo.application.usecase.UseCaseBase;
import chigirh.app.kakeibo.domain.entity.sample.Sample;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@UseCase
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CreateSampleUseCase extends UseCaseBase<CreateSampleUseCase.Input, UseCaseBase.Empty> {

    final SampleRepository sampleRepository;

    @Override
    protected String useCaseName() {
        return "CreateSampleUseCase";
    }

    public void invoke(Sample sample) {
        invoke(new Input(sample));
    }

    @Override
    protected Empty process(final Input input) {
        sampleRepository.insert(Collections.singletonList(input.entity));
        return Empty.instance();
    }

    @Value
    public static class Input implements UseCaseBase.Input {
        Sample entity;
    }

}
