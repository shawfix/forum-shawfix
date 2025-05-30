package pub.shawfix.forum.domain.repository;

import pub.shawfix.forum.common.model.PageRequest;
import pub.shawfix.forum.common.model.PageResult;
import pub.shawfix.forum.domain.entity.Article;
import pub.shawfix.forum.domain.entity.value.PostsPageQueryValue;

/**
 * @author shawfix
 * @create 2025/5/30 15:54
 * @desc
 **/
public interface ArticleRepository {
    void save(Article article);

    Article get(Long id);

    void update(Article article);

    PageResult<Article> page(Integer pageNo, Integer pageSize, PostsPageQueryValue pageQueryValue);
}
