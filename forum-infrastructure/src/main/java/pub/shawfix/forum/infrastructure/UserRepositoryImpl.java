package pub.shawfix.forum.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pub.shawfix.forum.common.model.PageRequest;
import pub.shawfix.forum.common.model.PageResult;
import pub.shawfix.forum.domain.entity.Follow;
import pub.shawfix.forum.domain.entity.User;
import pub.shawfix.forum.domain.repository.UserRepository;
import pub.shawfix.forum.infrastructure.dal.dao.UserDAO;
import pub.shawfix.forum.infrastructure.dal.dataobject.UserDO;
import pub.shawfix.forum.infrastructure.transfer.UserTransfer;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author shawfix
 * @create 2025/5/28 16:55
 * @desc
 **/
@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserDAO userDAO;

    @Override
    public User getByEmail(String email) {
        UserDO userDO = userDAO.getByEmail(email);

        return UserTransfer.toUser(userDO);
    }

    @Override
    public void update(User user) {
        userDAO.update(UserTransfer.toUserDO(user));
    }

    @Override
    public void save(User user) {
        UserDO userDO = UserTransfer.toUserDO(user);
        assert userDO != null;
        userDO.initBase();
        userDAO.insert(userDO);
        user.setId(userDO.getId());
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public List<User> queryByIds(List<Long> ids) {
        return Collections.emptyList();
    }

    @Override
    public PageResult<User> page(PageRequest<User> pageRequest) {
        return null;
    }

    @Override
    public void follow(Long followed, Long id) {

    }

    @Override
    public PageResult<User> pageFollower(PageRequest<Long> longPageRequest) {
        return null;
    }

    @Override
    public PageResult<User> pageFans(PageRequest<Long> longPageRequest) {
        return null;
    }

    @Override
    public Follow getFollow(Long followed, Long follower) {
        return null;
    }

    @Override
    public void cancelFollow(Long followId) {

    }

    @Override
    public PageResult<User> pageActive(PageRequest<?> pageRequest) {
        return null;
    }
}
