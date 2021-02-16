//package icu.bleuweb.error;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.web.ErrorProperties;
//import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
//import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
//import org.springframework.boot.web.servlet.error.ErrorAttributes;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class CustomErrorController extends BasicErrorController {
//
//    @Autowired
//    public CustomErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
//        super(errorAttributes, errorProperties, errorViewResolvers);
//    }
//
//    @Override
//    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
//        HttpStatus status = getStatus(request);
//        // 获取 Spring Boot 默认提供的错误信息，然后添加一个自定义的错误信息
//        Map<String, Object> body = getErrorAttributes(request,
//                isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        body.put("msg", "出错啦------");
//        return new ResponseEntity<>(body, status);
//    }
//}
