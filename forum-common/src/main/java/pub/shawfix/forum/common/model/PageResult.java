package pub.shawfix.forum.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author shawfix
 * @create 2025/5/28 11:34
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

    private Long total;
    
    private Integer size;

    private List<T> list;

    public static <T> PageResult<T> build(Long total, Integer size, List<T> list) {
        PageResult<T> result = new PageResult<>();
        result.setSize(size);
        result.setTotal(total);
        result.setList(list);

        return result;
    }
}
