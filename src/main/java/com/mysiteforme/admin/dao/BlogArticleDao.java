package com.mysiteforme.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mysiteforme.admin.entity.BlogArticle;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章内容 Mapper 接口
 * </p>
 *
 * @author huangyl
 * @since 2018-01-17
 */
public interface BlogArticleDao extends BaseMapper<BlogArticle> {

    List<BlogArticle> selectIndexArticle(Map<String, Object> map);

    List<BlogArticle> selectDetailArticle(Map<String, Object> map, Page<BlogArticle> page);

    List<BlogArticle> selectDetailArticle(Map<String, Object> map);

    List<BlogArticle> selectNewCommentArticle(Integer limit);

    /**
     * 查找当前文章的标签相似的文章
     *
     * @param map
     * @return
     */
    List<BlogArticle> selectLikeSameWithTags(Map<String, Object> map);

    void saveArticleTags(Map<String, Object> map);

    void removeArticleTags(Long articleId);

    /* *
     * @Description 文章点击直接访问数据库，不访问缓存
     * @ClassName BlogArticleDao
     * @param articleId
     * @Return void
     * @Date 2020/9/29 16:19
     * @Author huangyl
     */
    void changeClicks(Long articleId);
}
