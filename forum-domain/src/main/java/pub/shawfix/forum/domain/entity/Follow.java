package pub.shawfix.forum.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pub.shawfix.forum.common.enums.FollowedTypeEn;

/**
 * @author shawfix
 * @create 2025/5/28 11:42
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Follow extends BaseEntity {
    private Long followed;

    private FollowedTypeEn followedType;

    private Long follower;
}
