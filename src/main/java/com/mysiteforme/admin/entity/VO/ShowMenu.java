package com.mysiteforme.admin.entity.VO;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * 显示菜单
 * Created by huangyl on 2017/11/28.
 * todo:
 *
 * @author huangyanglai
 * @date 2020/09/17 09:07:52
 */
public class ShowMenu implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * pid
     */
    private Long pid;
    /**
     * 标题
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
    /**
     * href
     */
    private String href;
    /**
     * bg的颜色
     */
    private String bgColor;
    /**
     * 传播
     */
    private Boolean spread = false;
    /**
     * 孩子们
     */
    private List<ShowMenu> children = Lists.newArrayList();

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ShowMenu> getChildren() {
        return children;
    }

    public void setChildren(List<ShowMenu> children) {
        this.children = children;
    }
}
