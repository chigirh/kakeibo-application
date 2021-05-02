package chigirh.app.kakeibo.application.usecase.sample;

import chigirh.app.kakeibo.application.repository.sample.SampleRepository;
import chigirh.app.kakeibo.application.usecase.UseCase;
import chigirh.app.kakeibo.application.usecase.UseCaseBase;
import chigirh.app.kakeibo.domain.entity.page.condition.Order;
import chigirh.app.kakeibo.domain.entity.page.condition.Pageable;
import chigirh.app.kakeibo.domain.entity.sample.Sample;
import chigirh.app.kakeibo.domain.entity.sample.condition.FetchResult;
import chigirh.app.kakeibo.domain.entity.sample.condition.SampleField;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ListSampleUseCase extends UseCaseBase<ListSampleUseCase.Input, ListSampleUseCase.Output> {

    final SampleRepository sampleRepository;

    @Override
    protected String useCaseName() {
        return "ListSampleUseCase";
    }

    /**
     * サンプル一覧取得ユースケース.
     * <p>
     * Keyの昇順にsortする。
     *
     * @param page:validate済み
     * @param size:validate済み
     * @return サンプル一覧と総件数
     */
    public Output invoke(final int page, final int size) {
        return invoke(new Input(page, size));
    }

    @Override
    protected Output process(final Input input) {
        Pageable<SampleField> pageable = new Pageable<>(
                input.page,
                input.size,
                Collections.singletonList(new Pageable.Sort<>(SampleField.KEY, Order.ASC))
        );
        FetchResult result = sampleRepository.fetch(pageable);
        return new Output(
                result.getTotal(),
                result.getEntities()
        );
    }

    @Value
    public static class Input implements UseCaseBase.Input {
        int page;
        int size;
    }

    @Value
    public static class Output implements UseCaseBase.Output {
        int total;
        List<Sample> entities;
    }

}
