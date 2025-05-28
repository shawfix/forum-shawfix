package pub.shawfix.forum.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shawfix
 * @create 2025/5/27 11:06
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@RequestMapping
public class ResultModel<T> {
    private Integer code = 200;
    private String message = "success";
    private Boolean success = Boolean.TRUE;
    private T data;
}
