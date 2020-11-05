package com.mysiteforme.admin.util;

import java.util.List;

/**
 * Created by huangyl on 2017/11/27.
 * todo:
 */
public class LayerData<T> {
    /**
     * 代码
     */
    private Integer code = 0;
    /**
     * 数
     */
    private Integer count;
    /**
     * 数据
     */
    private List<T> data;
    /**
     * 信息
     */
    private String msg = "";

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
