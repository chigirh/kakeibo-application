package chigirh.app.kakeibo.application.usecase.sample;

import chigirh.app.kakeibo.application.repository.sample.SampleRepository;
import chigirh.app.kakeibo.application.usecase.UpdateUsecase;
import chigirh.app.kakeibo.domain.entity.Sample;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class CreateSampleUseCase extends UpdateUsecase<CreateSampleUseCase.Input, Void> {

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
    public static class Input implements UpdateUsecase.UpdateInput {
        private Sample entity;
    }

}
