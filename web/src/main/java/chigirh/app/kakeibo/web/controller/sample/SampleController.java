package chigirh.app.kakeibo.web.controller.sample;

import chigirh.app.kakeibo.application.usecase.sample.CreateSampleUseCase;
import chigirh.app.kakeibo.application.usecase.sample.ListSampleUseCase;
import chigirh.app.kakeibo.domain.entity.sample.Sample;
import chigirh.app.kakeibo.domain.error.business.KakeiboBusinessException;
import chigirh.app.kakeibo.web.form.sample.CreateSampleForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/sample")
@RequiredArgsConstructor
@Controller
public class SampleController {

    /**
     * １ページの表示数
     */
    private static final int size = 3;

    /**
     * ページネーションで表示するページ数
     */
    private static final int showPageSize = 5;


    final CreateSampleUseCase createSampleUseCase;
    final ListSampleUseCase listSampleUseCase;

    @RequestMapping(method = RequestMethod.GET)
    public String index(
            Model model,
            // 初期表示は1pageを指定
            @RequestParam(value = "page", required = false, defaultValue = "1") final Integer currentPage
    ) {
        // pagination処理
        ListSampleUseCase.Output output = listSampleUseCase.invoke(currentPage, size);

        // "総数/1ページの表示数"から総ページ数を割り出す
        int totalPage = (output.getTotal() + size - 1) / size;
        int page = currentPage;
        // 表示する最初のページ番号を算出（今回は3ページ表示する設定）
        // (例)1,2,3ページのstartPageは1。4,5,6ページのstartPageは4
        int startPage = page - (page - 1) % showPageSize;
        // 表示する最後のページ番号を算出
        int endPage = Math.min(startPage + showPageSize - 1, totalPage);
        model.addAttribute("items", output.getEntities());
        model.addAttribute("total", output.getTotal());
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        // form
        model.addAttribute("form", new CreateSampleForm());
        return "sample/index";

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("form") CreateSampleForm form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "error");
            return "sample/index";
        }

        createSampleUseCase.invoke(new Sample(form.getKey(), form.getValue(), 1));

        model.addAttribute("message", "success!!");
        return index(model, 1);
    }

    // エラーメッセージを画面遷移せずに表示する場合にはControllerにhandlerを実装する
    @ExceptionHandler(KakeiboBusinessException.class)
    public String exceptionHandler(KakeiboBusinessException e, Model model) {
        model.addAttribute("form", new CreateSampleForm());
        model.addAttribute("message", e.getMessage());
        return "sample/index";
    }
}
