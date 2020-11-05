package com.mysiteforme.admin.util.quartz.task;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mysiteforme.admin.entity.BlogArticle;
import com.mysiteforme.admin.entity.oshi.SystemHardwareInfo;
import com.mysiteforme.admin.service.BlogArticleService;
import com.mysiteforme.admin.service.LogService;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by huangyl on 2018/1/26.
 * todo: 系统定时任务
 */
@Component("systemTask")
public class SystemTask {
    private static Log log = LogFactory.get();

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private BlogArticleService blogArticleService;
    @Autowired
    private LogService logService;
    @Resource
    private SimpMessagingTemplate wsTemplate;
    /* *
     * @Description 推送系统信息
     * @ClassName SystemTask
     * @param params
     * @Return void
     * @Date 2020/9/9 9:36
     * @Author huangyl
     */
    public void runSysInfo(String params){
        log.info("【推送消息】开始执行：{}", DateUtil.formatDateTime(new Date()));
        // 查询服务器状态
        SystemHardwareInfo server = new SystemHardwareInfo();
        server.copyTo();
        wsTemplate.convertAndSend("/topic/system", JSONUtil.toJsonStr(server));
        //发送给指定用户
//		wsTemplate.convertAndSendToUser();
        log.info("【推送消息】执行结束：{}", DateUtil.formatDateTime(new Date()));
    }

    /**
     * 同步文章点击量
     *
     * @param params
     */
    public void synchronizationArticleView(String params) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        EntityWrapper<BlogArticle> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag", false);
        List<BlogArticle> list = blogArticleService.selectList(wrapper);
        for (BlogArticle article : list) {
            String key = "article_click_id_" + article.getId();
            if (redisTemplate.hasKey(key)) {
                Integer count = (Integer) operations.get(key);
                if (count > 0) {
                    article.setClick(blogArticleService.getArticleClick(article.getId()));
                    if (StringUtils.isNotBlank(params)) {
                        article.setUpdateId(Long.valueOf(params));
                    }
                    blogArticleService.updateById(article);
                }
            }
        }
    }

    /**
     * 生成文章搜索索引
     */
    public void createArticleIndex(String params) {
        blogArticleService.createArticlIndex();
    }

    /**
     * 生成文章搜索索引
     */
    public void clearLog(String params) {
        logService.deleteById(100L);
    }

}
