package com.mysiteforme.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mysiteforme.admin.entity.BlogComment;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章评论 Mapper 接口
 * </p>
 *
 * @author huangyl
 * @since 2018-01-17
 */
public interface BlogCommentDao extends BaseMapper<BlogComment> {

    /**
     * 查询文章评论 手动分页
     *
     * @param map
     * @return
     */
    List<BlogComment> selectArticleComments(Map<String, Object> map);

    Integer selectArticleCommentsCount(Map<String, Object> map);

    List<BlogComment> selectArticleCommentsByPlus(Map<String, Object> map, Page page);

    List<BlogComment> getCommentByReplyId(Long replyId);

}
