package pub.shawfix.forum.domain.repository;

import pub.shawfix.forum.common.enums.AuditStateEn;
import pub.shawfix.forum.common.model.PageRequest;
import pub.shawfix.forum.common.model.PageResult;
import pub.shawfix.forum.domain.entity.Posts;
import pub.shawfix.forum.domain.entity.Tag;

import java.util.List;
import java.util.Set;

/**
 * @author shawfix
 * @create 2025/5/30 11:30
 * @desc
 **/
public interface TagRepository {
    void save(Tag tag);

    List<Tag> query(Tag tag);

    List<Tag> queryByIds(Set<Long> ids);

    List<Tag> queryByState(AuditStateEn auditState);

    void deletePostsMapping(Long articleId);

    void increaseRefCount(Set<Long> ids);

    void decreaseRefCount(Set<Long> ids);

    Tag getByNameAndState(String name, AuditStateEn pass);

    PageResult<Posts> pagePosts(PageRequest<Long> longPageRequest);

    PageResult<Posts> pagePostsByTagIds(PageRequest<Set<Long>> pageRequest);

    PageResult<Tag> page(PageRequest<Tag> tagPageRequest);

    Tag get(Long id);

    void update(Tag tag);
}
