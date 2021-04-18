package chigirh.app.kakeibo.application.repository.sample;

import chigirh.app.kakeibo.domain.entity.Sample;

import java.util.List;

public interface SampleRepository {
    void insert(List<Sample> entities);
}
