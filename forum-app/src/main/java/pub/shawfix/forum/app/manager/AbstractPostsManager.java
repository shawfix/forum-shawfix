package pub.shawfix.forum.app.manager;

import pub.shawfix.forum.domain.repository.PostsRepository;
import pub.shawfix.forum.domain.repository.TagRepository;

import javax.annotation.Resource;

/**
 * @author shawfix
 * @create 2025/5/30 11:28
 * @desc
 **/
public abstract class AbstractPostsManager {

    @Resource
    CommentRepository commentRepository;

    @Resource
    PostsRepository postsRepository;

    @Resource
    TagRepository tagRepository;
}
