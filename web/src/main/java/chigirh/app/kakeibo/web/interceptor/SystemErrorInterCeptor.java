package chigirh.app.kakeibo.web.interceptor;

import chigirh.app.kakeibo.domain.error.system.KakeiboSystemError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SystemErrorInterCeptor {

    @ExceptionHandler(KakeiboSystemError.class)
    public ModelAndView testExceptionHandle(KakeiboSystemError e, ModelAndView mav) {
        mav.addObject("error-message", e.getMessage());
        mav.setViewName("error/index");
        return mav;
    }
}
