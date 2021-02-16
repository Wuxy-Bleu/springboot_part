package icu.bleuweb.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ApiResponse {

    @JSONField(serialize = false)
    private final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * {@link HttpStatus}
     */
    @JSONField(defaultValue = "200", ordinal = 1)
    private int httpStatus;

    /**
     * 消息
     */
    @JSONField(ordinal = 3)
    private String message;

    /**
     * 时间戳
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", ordinal = 2)
    private LocalDateTime timestamp;

    /**
     * 数据
     */
    @JSONField(defaultValue = "{}", ordinal = 4)
    private Object data;
}
