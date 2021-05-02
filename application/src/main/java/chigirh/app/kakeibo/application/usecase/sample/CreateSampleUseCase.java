package chigirh.app.kakeibo.application.usecase.sample;

import chigirh.app.kakeibo.application.repository.sample.SampleRepository;
import chigirh.app.kakeibo.application.usecase.UseCase;
import chigirh.app.kakeibo.application.usecase.UseCaseBase;
import chigirh.app.kakeibo.domain.entity.Sample;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@UseCase
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CreateSampleUseCase extends UseCaseBase<CreateSampleUseCase.Input, Void> {

    final SampleRepository sampleRepository;

    @Override
    protected String useCaseName() {
        return "CreateSampleUseCase";
    }

    public void invoke(Sample sample) {
        invoke(new Input(sample));
    }

    @Override
    protected Void process(Input input) {
        sampleRepository.insert(Collections.singletonList(input.entity));
        return null;
    }

    @AllArgsConstructor
    @Data
    public static class Input implements UseCaseBase.UpdateInput {
        private Sample entity;
    }

}
