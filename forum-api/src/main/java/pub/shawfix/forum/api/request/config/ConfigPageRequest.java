package pub.shawfix.forum.api.request.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shawfix
 * @create 2025/6/3 08:58
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigPageRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String state;

    private String type;

    private String name;
}
