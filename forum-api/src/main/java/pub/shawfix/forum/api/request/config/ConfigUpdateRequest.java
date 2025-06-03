package pub.shawfix.forum.api.request.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shawfix
 * @create 2025/6/3 09:00
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigUpdateRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String type;

    private String name;

    private String content;

    private Date startAt;

    private Date endAt;
}
