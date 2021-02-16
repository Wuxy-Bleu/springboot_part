package icu.bleuweb.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        log.info("binder.getFieldDefaultPrefix {}", binder.getFieldDefaultPrefix());
        log.info("binder.getFieldMarkerPrefix {}", binder.getFieldMarkerPrefix());
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "wuxy");
    }

    @ExceptionHandler(LoginError.class)
    public Map<String, String> defaultErrorHandler(HttpServletRequest req, Exception e) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "900");
        map.put("msg", req.toString());
        map.put("data", e.toString());
        return map;
    }
}
