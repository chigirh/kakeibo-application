package chigirh.app.kakeibo.infra.repository.sample;

import chigirh.app.kakeibo.application.repository.sample.SampleRepository;
import chigirh.app.kakeibo.domain.entity.Sample;
import chigirh.app.kakeibo.domain.error.business.AlreadyExistsException;
import chigirh.app.kakeibo.domain.error.system.ResourcesAccessError;
import chigirh.app.kakeibo.infra.mapper.cluster.SampleClusterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class SampleRepositoryImpl implements SampleRepository {

    final SampleClusterMapper sampleClusterMapper;

    /**
     * Sampleの登録を行う.
     *
     * @param entities:validate済み
     * @throws AlreadyExistsException:Sample重複時にThrowする
     * @throws ResourcesAccessError:Postgresアクセス時にエラーが発生した場合Throwする
     */
    @Override
    public void insert(List<Sample> entities) {
        try {
            sampleClusterMapper.insert(
                    entities.stream().map(entity ->
                            new chigirh.app.kakeibo.infra.entity.sample.Sample(
                                    entity.getKey(),
                                    entity.getValue()
                            )
                    ).collect(Collectors.toList())
            );
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistsException("Sample is already exists.");
        } catch (DataAccessException e) {
            throw new ResourcesAccessError(e, "sample insert error.");
        }

    }
}
