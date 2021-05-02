package chigirh.app.kakeibo.domain.entity.page.condition;

import lombok.Value;

import java.util.List;

@Value
public class Pageable<F extends Field> {
    int page;
    int size;
    List<Sort<F>> sortCondition;

    @Value
    public static class Sort<F extends Field> {
        F field;
        Order order;
    }
}
