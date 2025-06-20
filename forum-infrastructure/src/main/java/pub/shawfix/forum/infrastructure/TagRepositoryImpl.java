package pub.shawfix.forum.infrastructure;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Sets;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.ObjectUtils;
import pub.shawfix.forum.common.enums.AuditStateEn;
import pub.shawfix.forum.common.enums.ErrorCodeEn;
import pub.shawfix.forum.common.exception.BizException;
import pub.shawfix.forum.common.model.PageRequest;
import pub.shawfix.forum.common.model.PageResult;
import pub.shawfix.forum.common.support.SafesUtil;
import pub.shawfix.forum.domain.entity.Posts;
import pub.shawfix.forum.domain.entity.Tag;
import pub.shawfix.forum.domain.repository.TagRepository;
import pub.shawfix.forum.infrastructure.dal.dataobject.TagDO;
import pub.shawfix.forum.infrastructure.dal.dataobject.TagPostsMappingDO;
import pub.shawfix.forum.infrastructure.transfer.TagTransfer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author shawfix
 * @create 2025/5/30 14:33
 * @desc
 **/
public class TagRepositoryImpl extends AbstractPostsRepository implements TagRepository {

    @Override
    public void save(Tag tag) {
        TagDO tagDO = TagTransfer.toTagDO(tag);

        try {
            tagDAO.insert(tagDO);
            tag.setId(tagDO.getId());
        } catch (DuplicateKeyException e) {
            throw new BizException(ErrorCodeEn.TAG_IS_EXIST);
        }
    }

    @Override
    public List<Tag> query(Tag tag) {
        return TagTransfer.toTags(tagDAO.query(TagTransfer.toTagDO(tag)));
    }

    @Override
    public List<Tag> queryByIds(Set<Long> ids) {
        return TagTransfer.toTags(tagDAO.queryInIds(ids));
    }

    @Override
    public List<Tag> queryByState(AuditStateEn auditState) {
        return TagTransfer.toTags(tagDAO.query(TagDO.builder()
                .auditState(auditState.getValue())
                .build()));
    }

    @Override
    public void deletePostsMapping(Long articleId) {
        List<TagPostsMappingDO> tagPostsMappingDOS = tagPostsMappingDAO.queryInPostsIds(Sets.newHashSet(articleId));
        Set<Long> tagIds = SafesUtil.ofList(tagPostsMappingDOS)
                .stream()
                .map(TagPostsMappingDO::getTagId)
                .collect(Collectors.toSet());
        if (!ObjectUtils.isEmpty(tagIds)) {
            tagDAO.decreaseRefCount(tagIds);
        }

        tagPostsMappingDAO.deleteByPostsId(articleId);
    }

    @Override
    public void increaseRefCount(Set<Long> ids) {
        tagDAO.increaseRefCount(ids);
    }

    @Override
    public void decreaseRefCount(Set<Long> ids) {
        tagDAO.decreaseRefCount(ids);
    }

    @Override
    public Tag getByNameAndState(String name, AuditStateEn pass) {
        List<TagDO> tagDOS = tagDAO.query(TagDO.builder()
                .auditState(pass.getValue())
                .name(name)
                .build());
        if (!ObjectUtils.isEmpty(tagDOS)) {
            return null;
        }

        return TagTransfer.toTag(tagDOS.get(0));
    }

    @Override
    public PageResult<Posts> pagePosts(PageRequest<Long> pageRequest) {
        PageRequest<Set<Long>> pageRequest1 = new PageRequest<>();
        pageRequest1.setPageNo(pageRequest.getPageNo());
        pageRequest1.setPageSize(pageRequest.getPageSize());
        pageRequest1.setFilter(Sets.newHashSet(pageRequest.getFilter()));

        return pagePostsByTagIds(pageRequest1);
    }

    @Override
    public PageResult<Posts> pagePostsByTagIds(PageRequest<Set<Long>> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());
        List<TagPostsMappingDO> tagPostsMappingDOS = tagPostsMappingDAO.queryInTagIds(pageRequest.getFilter());
        PageInfo<TagPostsMappingDO> pageInfo = new PageInfo<>(tagPostsMappingDOS);

        if (ObjectUtils.isEmpty(tagPostsMappingDOS)) {
            return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), new ArrayList<>());
        }

        Set<Long> postsIds = new HashSet<>();
        tagPostsMappingDOS.forEach(tagPostsMappingDO -> postsIds.add(tagPostsMappingDO.getTagId()));

        return basePagePosts(new ArrayList<>(postsIds), pageInfo, AuditStateEn.PASS);
    }

    @Override
    public PageResult<Tag> page(PageRequest<Tag> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());

        Tag tag = pageRequest.getFilter();
        List<TagDO> tagDOList = tagDAO.query(TagDO.builder()
                .auditState(ObjectUtils.isEmpty(tag.getAuditState()) ? null : tag.getAuditState()
                        .getValue())
                .name(tag.getName())
                .groupName(tag.getGroupName())
                .description(tag.getDescription())
                .build());

        PageInfo<TagDO> pageInfo = new PageInfo<>(tagDOList);
        if (ObjectUtils.isEmpty(tagDOList)) {
            return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), new ArrayList<>());
        }

        return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), TagTransfer.toTags(tagDOList));
    }

    @Override
    public Tag get(Long id) {
        return TagTransfer.toTag(tagDAO.get(id));
    }

    @Override
    public void update(Tag tag) {
        TagDO tagDO = TagDO.builder()
                .description(tag.getDescription())
                .groupName(tag.getGroupName())
                .name(tag.getName())
                .auditState(ObjectUtils.isEmpty(tag.getAuditState()
                        .getValue()) ? null : tag.getAuditState()
                        .getValue())
                .creatorId(tag.getCreatorId())
                .refCount(tag.getRefCount())
                .build();
        tagDO.setId(tag.getId());

        tagDAO.update(tagDO);
    }
}
