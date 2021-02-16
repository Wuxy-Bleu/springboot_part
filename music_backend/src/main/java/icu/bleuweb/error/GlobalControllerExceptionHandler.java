package icu.bleuweb.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        log.info("binder.getFieldDefaultPrefix {}", binder.getFieldDefaultPrefix());
        log.info("binder.getFieldMarkerPrefix {}", binder.getFieldMarkerPrefix());
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     *
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "wuxy");
    }

    @ExceptionHandler(Exception.class)
    public Map<String, String> defaultErrorHandler(HttpServletRequest req, Exception e) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "900");
        map.put("msg", req.toString());
        map.put("data", e.toString());
        System.out.println("[ExceptionController] 异常 xxxRequestId = {}; message = {}; 响应接口 = {}" + req.getAttribute("xxxRequestId") +
                e.getMessage() + map);
        return map;
    }

//    /**
//     * 用来捕获404，400这种无法到达controller的错误
//     *
//     * @param ex
//     * @return
//     * @throws Exception
//     */
//    @ExceptionHandler(value = Exception.class)
//    public Map<String, String> defaultErrorHandler(Exception ex) throws Exception {
//        Map<String, String> map = new HashMap<>();
//        map.put("msg", ex.getMessage());
//        if (ex instanceof NoHandlerFoundException) {
//            map.put("code", "404");
//        } else {
//            map.put("code", "500");
//        }
//        map.put("data", ex.toString());
//        return map;
//    }
}
