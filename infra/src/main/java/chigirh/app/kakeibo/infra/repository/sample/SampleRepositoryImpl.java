package chigirh.app.kakeibo.infra.repository.sample;

import chigirh.app.kakeibo.application.repository.sample.SampleRepository;
import chigirh.app.kakeibo.domain.entity.page.condition.Pageable;
import chigirh.app.kakeibo.domain.entity.sample.Sample;
import chigirh.app.kakeibo.domain.entity.sample.condition.FetchResult;
import chigirh.app.kakeibo.domain.entity.sample.condition.SampleField;
import chigirh.app.kakeibo.domain.error.business.AlreadyExistsException;
import chigirh.app.kakeibo.domain.error.system.ResourcesAccessError;
import chigirh.app.kakeibo.infra.convert.sample.PageableConverter;
import chigirh.app.kakeibo.infra.convert.sample.SampleConverter;
import chigirh.app.kakeibo.infra.mapper.cluster.SampleClusterMapper;
import chigirh.app.kakeibo.infra.mapper.reader.SampleReaderMapper;
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
    final SampleReaderMapper sampleReaderMapper;

    final PageableConverter pageableConverter;
    final SampleConverter converter;

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

    /**
     * Sampleを取得する
     *
     * @param pageable:validate済み
     * @return 検索結果:0件以上
     */
    @Override
    public FetchResult fetch(Pageable<SampleField> pageable) {
        return new FetchResult(
                sampleReaderMapper.countSample(),
                sampleReaderMapper.findSamples(
                        pageableConverter.calcOffset(pageable.getPage(), pageable.getSize()),
                        pageable.getSize(),
                        pageableConverter.createOrderByClause(
                                pageable.getSortCondition(),
                                this::createField)
                ).stream()
                        .map(converter::toDomainEntity)
                        .collect(Collectors.toList())
        );
    }

    private String createField(SampleField field) {
        switch (field) {
            case KEY:
                return "key";
            default:
                throw new IllegalArgumentException(""); // dead logic
        }
    }
}
