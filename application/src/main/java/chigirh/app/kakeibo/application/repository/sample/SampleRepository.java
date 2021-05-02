package chigirh.app.kakeibo.application.repository.sample;

import chigirh.app.kakeibo.domain.entity.page.condition.Pageable;
import chigirh.app.kakeibo.domain.entity.sample.Sample;
import chigirh.app.kakeibo.domain.entity.sample.condition.FetchResult;
import chigirh.app.kakeibo.domain.entity.sample.condition.SampleField;

import java.util.List;

public interface SampleRepository {
    void insert(List<Sample> entities);

    FetchResult fetch(Pageable<SampleField> pageable);
}
