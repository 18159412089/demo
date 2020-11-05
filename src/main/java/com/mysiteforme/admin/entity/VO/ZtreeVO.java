package com.mysiteforme.admin.entity.VO;

import java.io.Serializable;
import java.util.List;

/**
 * 最好签证官
 * Ztree 树
 *
 * @author Administrator
 * @date 2020/09/17 09:08:40
 */
public class ZtreeVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * pid
     */
    private Long pid;

    /**
     * 名字
     */
    private String name;

    /**
     * url
     */
    private String url;

    /**
     * 开放
     */
    private Boolean open = true;

    /**
     * 是否父母
     */
    private Boolean isParent;

    /**
     * 图标
     */
    private String icon;

    /**
     * 孩子们
     */
    private List<ZtreeVO> children;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public List<ZtreeVO> getChildren() {
        return children;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setChildren(List<ZtreeVO> children) {
        this.children = children;
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
}
