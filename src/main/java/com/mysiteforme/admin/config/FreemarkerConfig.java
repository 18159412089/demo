package com.mysiteforme.admin.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import com.mysiteforme.admin.freemark.*;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.annotation.PostConstruct;

/**
 * Created by huangyl on 2017/11/26.
 * todo:
 */
@Component
public class FreemarkerConfig {

    @Autowired
    private Configuration configuration;

    @Autowired
    private SystemDirective systemDirective;

    @Autowired
    private ArticleDirective articleDirective;

    @Autowired
    private IndexArticleDirective indexArticleDirective;

    @Autowired
    private ChannelDirective channelDirective;

    @Autowired
    private ParentChannelListDirective parentChannelListDirective;

    @Autowired
    private ArticleClickTempletModel articleClickTempletModel;

    @Autowired
    private SysUserTempletModel sysUserTempletModel;

    @Autowired
    private TagsTempletModel tagsTempletModel;

    @Autowired
    private NewCommentArticleTempletModel newCommentArticleTempletModel;

    @Autowired
    private LookLikeArticlesTempletModel lookLikeArticlesTempletModel;

    @Autowired
    private CommentNumberTempletModel commentNumberTempletModel;
    @Autowired
    private FreeMarkerViewResolver resolver;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct
    public void setSharedVariable() {
        //系统字典标签
        configuration.setSharedVariable("my", systemDirective);
        //文章文章标签
        configuration.setSharedVariable("ar", articleDirective);
        //文章首页文章列表标签
        configuration.setSharedVariable("myindex", indexArticleDirective);
        //文章栏目标签
        configuration.setSharedVariable("mychannel", channelDirective);
        //文章当前栏目所有父目录集合标签
        configuration.setSharedVariable("articleChannelList", parentChannelListDirective);

        //获取文章点击量标签
        configuration.setSharedVariable("clickNumber", articleClickTempletModel);
        //获取文章评论数量
        configuration.setSharedVariable("commentNumber", commentNumberTempletModel);
        //获取系统用户信息
        configuration.setSharedVariable("sysuser", sysUserTempletModel);
        //获取标签集合
        configuration.setSharedVariable("tags", tagsTempletModel);
        //最新评论文章集合
        configuration.setSharedVariable("nca", newCommentArticleTempletModel);
        //当前文章相似的文章
        configuration.setSharedVariable("same", lookLikeArticlesTempletModel);
        // 加上这句后，可以在页面上使用shiro标签
        freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro", new ShiroTags());
        // 加上这句后，可以在页面上用${context.contextPath}获取contextPath
        resolver.setRequestContextAttribute("context");
    }
}
