package pub.shawfix.forum.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shawfix
 * @create 2025/5/28 11:36
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest<T> {

    private static final Integer DEF_PAGE_SIZE = 10;
    private static final Integer DEF_PAGE_NO = 1;

    private Integer pageSize = DEF_PAGE_SIZE;
    private Integer pageNo = DEF_PAGE_NO;
    private T filter;

    public static <T> PageRequest<T> build(Integer pageNo, Integer pageSize, T filter) {
        PageRequest<T> pageRequest = new PageRequest<>();
        pageRequest.setPageNo(pageNo);
        pageRequest.setPageSize(pageSize);
        pageRequest.setFilter(filter);

        return pageRequest;
    }


    public static <T> PageRequest<T> build(Integer pageNo, Integer pageSize) {
        PageRequest<T> pageRequest = new PageRequest<>();
        pageRequest.setPageNo(pageNo);
        pageRequest.setPageSize(pageSize);

        return pageRequest;
    }

}
