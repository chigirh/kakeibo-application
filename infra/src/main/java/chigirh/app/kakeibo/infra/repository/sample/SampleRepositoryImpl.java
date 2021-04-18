package chigirh.app.kakeibo.infra.repository.sample;

import chigirh.app.kakeibo.application.repository.sample.SampleRepository;
import chigirh.app.kakeibo.domain.entity.Sample;
import chigirh.app.kakeibo.infra.mapper.cluster.SampleClusterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class SampleRepositoryImpl implements SampleRepository {

    final SampleClusterMapper sampleClusterMapper;

    @Override
    public void insert(List<Sample> entities) {
        sampleClusterMapper.insert(
                entities.stream().map(entity ->
                        new chigirh.app.kakeibo.infra.entity.sample.Sample(
                                entity.getKey(),
                                entity.getValue()
                        )
                ).collect(Collectors.toList())
        );

    }
}
