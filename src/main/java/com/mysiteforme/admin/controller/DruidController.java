package com.mysiteforme.admin.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Druid控制器
 *
 * @author huangyanglai
 * @date 2020/09/17 09:25:00
 */
@Controller
@RequestMapping("/admin/druid")
public class DruidController {

    @RequiresPermissions("sys:druid:list")
    @GetMapping("list")
    public String index() {
        return "admin/system/druid/index";
    }
}
