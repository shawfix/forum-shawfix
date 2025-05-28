package pub.shawfix.forum.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shawfix
 * @create 2025/5/28 10:18
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminBooleanRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Boolean is;
}
