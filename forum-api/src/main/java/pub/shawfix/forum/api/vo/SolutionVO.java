package pub.shawfix.forum.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shawfix
 * @create 2025/5/30 16:56
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolutionVO implements Serializable {
    private Long id;

    private String content;
}
