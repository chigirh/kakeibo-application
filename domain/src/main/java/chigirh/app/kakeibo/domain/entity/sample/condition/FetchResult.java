package chigirh.app.kakeibo.domain.entity.sample.condition;

import chigirh.app.kakeibo.domain.entity.sample.Sample;
import lombok.Value;

import java.util.List;

@Value
public class FetchResult {
    int total;
    List<Sample> entities;
}
