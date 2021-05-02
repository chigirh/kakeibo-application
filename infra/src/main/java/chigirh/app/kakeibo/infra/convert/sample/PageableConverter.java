package chigirh.app.kakeibo.infra.convert.sample;

import chigirh.app.kakeibo.domain.entity.page.condition.Field;
import chigirh.app.kakeibo.domain.entity.page.condition.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PageableConverter {
    /**
     * Pageable.sortConditionからorder by句を生成する.
     *
     * @param sortCondition:validate済み
     * @param mapper:Fieldからcolumn名を求めるためのfunctional interface
     * @param <F>:FieldName
     * @return order by句
     */
    public <F extends Field> String createOrderByClause(List<Pageable.Sort<F>> sortCondition, Function<F, String> mapper) {
        if (CollectionUtils.isEmpty(sortCondition)) {
            return null;
        }
        return "ORDER BY " + sortCondition.stream().map(sort -> {
            String field = mapper.apply(sort.getField());
            String order = "asc";
            switch (sort.getOrder()) {
                case DESC:
                    order = "desc";
                case ASC:
                    order = "asc";
            }
            return field + " " + order;
        }).collect(Collectors.joining(","));
    }

    /**
     * page数と1pageの表示数からoffsetを求める.
     *
     * @param page:validate済み
     * @param size:validate済み
     * @return offset
     */
    public int calcOffset(final int page, final int size) {
        return (page - 1) * size;
    }
}
