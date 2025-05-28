package pub.shawfix.forum.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author shawfix
 * @create 2025/5/27 11:15
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseModel<T> implements Serializable {
    private List<T> list;
    private Long total;
    private Integer size;

    public static <T> PageResponseModel<T> build(Long total, Integer size, List<T> list) {
        PageResponseModel<T> result = new PageResponseModel<>();
        result.setSize(size);
        result.setTotal(total);
        result.setList(list);

        return result;
    }
}
