package chigirh.app.kakeibo.web.interceptor;

import chigirh.app.kakeibo.domain.error.system.KakeiboSystemError;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SystemErrorInterceptor {

    @ExceptionHandler(KakeiboSystemError.class)
    public String SystemErrorHandle(KakeiboSystemError e, Model model) {
        model.addAttribute("error-message", e.getMessage());
        return "error/index";
    }
}
