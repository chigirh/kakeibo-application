package chigirh.app.kakeibo.infra.convert.sample;

import chigirh.app.kakeibo.domain.entity.sample.Sample;
import chigirh.app.kakeibo.infra.dto.sample.SampleDto;
import org.springframework.stereotype.Component;

@Component
public class SampleConverter {
    public Sample toDomainEntity(SampleDto dto) {
        return new Sample(
                dto.getKey(),
                dto.getValue(),
                dto.getVersion()
        );
    }
}
