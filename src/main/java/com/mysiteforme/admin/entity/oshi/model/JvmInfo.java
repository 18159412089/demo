package com.mysiteforme.admin.entity.oshi.model;


import com.xiaoleilu.hutool.date.DateUnit;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.NumberUtil;

import java.lang.management.ManagementFactory;
import java.util.Date;

/**
 * JvmInfo
 *
 * @author huangyl
 * @version V1.0
 * @date 2020年9月8日17:05:24
 */
public class JvmInfo {
    /**
     * 当前JVM占用的内存总数(M)
     */
    private double total;

    /**
     * JVM最大可用内存总数(M)
     */
    private double max;

    /**
     * JVM空闲内存(M)
     */
    private double free;

    /**
     * JDK版本
     */
    private String version;

    /**
     * JDK路径
     */
    private String home;

    /*已用内存*/
    private double used;

    /*Jvm名称*/
    private String name;

    /*启动时间*/
    private String startTime;

    /*运行时长*/
    private String runTime;

    private String usage;


    public double getTotal() {
        return NumberUtil.div(total, (1024 * 1024), 2);
    }

    public double getMax() {
        return NumberUtil.div(max, (1024 * 1024), 2);
    }

    public double getFree() {
        return NumberUtil.div(free, (1024 * 1024), 2);
    }

    public double getUsed() {
        return NumberUtil.div(total - free, (1024 * 1024), 2);
    }

    public String getVersion() {
        return version;
    }

    public String getHome() {
        return home;
    }

    public double getUsage() {
        return NumberUtil.mul(NumberUtil.div(total - free, total, 4), 100);
    }

    /**
     * 获取JDK名称
     */
    public String getName() {
        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    /**
     * JDK启动时间
     */
    public String getStartTime() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        Date date = new Date(time);
        return DateUtil.formatDateTime(date);
    }

    /**
     * JDK运行时间
     */
    public String getRunTime() {

        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        Date date = new Date(time);

        //运行多少分钟
        long runMS = DateUtil.between(date, new Date(), DateUnit.MS);

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;

        long day = runMS / nd;
        long hour = runMS % nd / nh;
        long min = runMS % nd % nh / nm;

        return day + "天" + hour + "小时" + min + "分钟";
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void setFree(double free) {
        this.free = free;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public void setUsed(double used) {
        this.used = used;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }
}
