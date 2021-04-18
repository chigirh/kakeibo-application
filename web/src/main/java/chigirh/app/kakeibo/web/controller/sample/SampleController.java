package chigirh.app.kakeibo.web.controller.sample;

import chigirh.app.kakeibo.application.usecase.sample.CreateSampleUseCase;
import chigirh.app.kakeibo.domain.entity.Sample;
import chigirh.app.kakeibo.web.form.sample.CreateSampleForm;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RequestMapping("/sample")
@RequiredArgsConstructor
@RestController
public class SampleController {

    final CreateSampleUseCase createSampleUseCase;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        mav.addObject("form", new CreateSampleForm());
        mav.setViewName("sample/index");
        return mav;

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(
            @Valid @ModelAttribute("form") CreateSampleForm form,
            BindingResult bindingResult,
            ModelAndView mav) {

        if (bindingResult.hasErrors()) {
            mav.addObject("message", "error");
            mav.setViewName("sample/index");
            return mav;
        }

        createSampleUseCase.invoke(new Sample(form.getKey(), form.getValue(), 1));

        mav.addObject("message", "succes!!");
        mav.setViewName("sample/index");

        return mav;

    }
}
