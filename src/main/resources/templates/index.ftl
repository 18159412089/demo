<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <!-- 页面描述 -->
    <meta name="description" content="${site.description}"/>
    <!-- 页面关键词 -->
    <meta name="keywords" content="${site.keywords}"/>
    <!-- 网页作者 -->
    <meta name="author" content="${site.author}"/>
    <link rel="icon" href="${site.logo}">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/main.css" media="all" />
</head>
<body class="main_body">
<div class="layui-layout layui-layout-admin">
    <!-- 顶部 -->
    <div class="layui-header header">
        <div class="layui-main">
            <a href="#" class="logo">生菜科技网站后台</a>
            <!-- 显示/隐藏菜单 -->
            <a href="javascript:" class="iconfont hideMenu icon-menu1"></a>
            <#--<!-- 搜索 &ndash;&gt;-->
            <#--<div class="layui-form component">-->
            <#--<select name="modules" lay-verify="required" lay-search="">-->
            <#--<option value="">直接选择或搜索选择</option>-->
            <#--<#if (userMenu?size>0)>-->
            <#--<#list userMenu as items>-->
            <#--<option value="${items.href}">${items.name}</option>-->
            <#--</#list>-->
            <#--</#if>-->
            <#--</select>-->
            <#--<i class="layui-icon">&#xe615;</i>-->
            <#--</div>-->
            <!-- 天气信息 -->
            <div class="weather" pc>
                <iframe name="weather" frameborder="0" style=" max-height: 30px;" scrolling="no" hspace="0" src="https://i.tianqi.com/?c=code&id=10"></iframe>
            </div>
            <!-- 顶部右侧菜单 -->
            <ul class="layui-nav top_menu">
                <li class="layui-nav-item showNotice" id="showNotice" pc>
<#--                    <div>-->
<#--                        <embed wmode="transparent"-->
<#--                               src="http://chabudai.sakura.ne.jp/blogparts/honehoneclock/honehone_clock_tr.swf"-->
<#--                               quality="high" bgcolor="#ffffff" width="160" height="70" name="honehoneclock"-->
<#--                               align="middle" allowscriptaccess="always" type="application/x-shockwave-flash"-->
<#--                               pluginspage="http://www.macromedia.com/go/getflashplayer">-->
<#--                    </div>-->
                   <site><span id="sj"></span></site>
                </li>
                <li class="layui-nav-item" mobile>
                    <a href="javascript:" class="mobileAddTab" data-url="page/user/changePwd.html"><i class="iconfont icon-shezhi1" data-icon="icon-shezhi1"></i><cite>设置</cite></a>
                </li>
                <li class="layui-nav-item" mobile>
                    <a href="${base}/systemLogout" class="signOut"><i class="iconfont icon-loginout"></i> 退出</a>
                </li>
                <@shiro.hasPermission name="sys:clearCache">
                <li class="layui-nav-item" onclick="clearCache()" pc>
                    <a href="javascript:"><i class="iconfont icon-guanbi"></i><cite>清除缓存</cite></a>
                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="sys:lockms">
                <li class="layui-nav-item lockcms" pc>
                    <a href="javascript:"><i class="iconfont icon-lock1"></i><cite>锁屏</cite></a>
                </li>
                </@shiro.hasPermission>
                <li class="layui-nav-item" pc>
                    <a href="javascript:">
                        <img id="headImg" src="<#if (currentUser.icon??)>${currentUser.icon}<#else>${base}/static/images/face.jpg</#if>" class="layui-circle" width="35" height="35">
                        <cite><#if currentUser.nickName!''>${currentUser.nickName}<#else>${currentUser.loginName}</#if></cite>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:" data-url="${base}/admin/system/user/userinfo"><i class="iconfont icon-zhanghu" data-icon="icon-zhanghu"></i><cite>个人资料</cite></a></dd>
                        <dd><a href="javascript:" data-url="${base}/admin/system/user/changePassword"><i class="iconfont icon-shezhi1" data-icon="icon-shezhi1"></i><cite>修改密码</cite></a></dd>
                        <dd><a href="javascript:" class="changeSkin"><i class="iconfont icon-huanfu"></i><cite>更换皮肤</cite></a></dd>
                        <dd><a href="${base}/systemLogout" class="signOut"><i class="iconfont icon-loginout"></i><cite>退出</cite></a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <!-- 左侧导航 -->
    <div class="layui-side layui-bg-black">
        <div class="user-photo">
            <a class="img" title="我的头像" ><img src="<#if (currentUser.icon??)>${currentUser.icon}<#else>${base}/static/images/face.jpg</#if>"></a>
            <p>你好！<span class="userName"><#if currentUser.nickName!''>${currentUser.nickName}<#else>${currentUser.loginName}</#if></span>, 欢迎登录</p>
        </div>
        <div class="navBar layui-side-scroll"></div>
    </div>
    <!-- 右侧内容 -->
    <div class="layui-body layui-form">
        <div class="layui-tab marg0" lay-filter="bodyTab" id="top_tabs_box">
            <ul class="layui-tab-title top_tab" id="top_tabs">
                <li class="layui-this" lay-id=""><i class="iconfont icon-computer"></i> <cite>后台首页</cite></li>
            </ul>
            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:"><i class="iconfont icon-caozuo"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:" class="refresh refreshThis"><i class="layui-icon">&#x1002;</i> 刷新当前</a></dd>
                        <dd><a href="javascript:" class="closePageOther"><i class="iconfont icon-prohibit"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:" class="closePageAll"><i class="iconfont icon-guanbi"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div class="layui-tab-item layui-show">
                    <iframe src="${base}/main"></iframe>
                </div>
            </div>
        </div>
    </div>
    <!-- 底部 -->
    <div class="layui-footer footer">
        <p>Copyright © 生菜科技  Design By 老黄 <a href="http://icp.chinaz.com/home/info?host=shengcaikeji.cn" target="_blank">闽ICP备17019386号 </a></p>
    </div>
</div>
<script>
    var baseUrl = "${base}";
    window.sessionStorage.username = "${currentUser.nickName}"
</script>
<!-- 移动导航 -->
<div class="site-tree-mobile layui-hide"><i class="layui-icon">&#xe602;</i></div>
<div class="site-mobile-shade"></div>

<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/leftNav.js?v=2.0"></script>
<script type="text/javascript" src="${base}/static/js/index.js?t=3.0"></script>
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
</body>
<script>
    $(function () {
        setInterval("getTime();", 1000); //每隔一秒运行一次
    })

  function clearCache() {
        $.post("/admin/system/user/clearCache", null, function (res) {
            if (res.success) {
                layer.msg("清除成功", { icon: 1 });
                $(this).removeClass("layui-this");
            } else {
                layer.msg(res.message);
            }
        });
    }

    //取得系统当前时间
    function getTime() {
        var myDate = new Date();
        var year = myDate.getFullYear();
        var month = myDate.getMonth()+1;
        var day = myDate.getDate();
        var weekday=myDate.getDay();
        var weekdayCh=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
        var hours = myDate.getHours();
        var minutes = myDate.getMinutes();
        var seconds = myDate.getSeconds();
        if (day < 10) {
            day = "0" + day;
        }

        if (hours<10)
        {
            hours = "0" + hours;
        }

        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        if (seconds < 10) {
            seconds = "0" + seconds;
        }

        $("#sj").html(year + "年" + month + "月" + day + "日" + " " + weekdayCh[weekday] + " " + hours + ":" + minutes + ":" + seconds);//这里的sj是显示的控件的id
    }
</script>
</html>