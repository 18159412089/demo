package com.mysiteforme.admin.entity.VO;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 树菜单
 * Created by tnt on 2017/12/6.
 * ${TODO}
 *
 * @author huangyanglai
 * @date 2020/09/17 09:09:18
 */
public class TreeMenu {
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
     * 传播
     */
    private Boolean spread;
    /**
     * 孩子们
     */
    List<TreeMenu> children = Lists.newArrayList();

    public TreeMenu(Boolean spread) {
        this.spread = false;
    }

    public TreeMenu(Long id, Long pid, String title, String icon, String href) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
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

    public List<TreeMenu> getChildren() {
        return children;
    }

    public void setChildren(List<TreeMenu> children) {
        this.children = children;
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
}
