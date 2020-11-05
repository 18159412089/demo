<!DOCTYPE html>
<html class="loginHtml">
<head>
    <meta charset="utf-8">
    <title>生菜科技--官网后台</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="https://shengcaiweb.oss-cn-beijing.aliyuncs.com/icon.ico">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/login.css?t=${.now?long}" media="all" />
</head>
<script>
    if(window.top!==window.self){alert('未登录或登录超时。请重新登录');window.top.location=window.location};
</script>
<body>
<div id="bg-body"></div>
<div class="login">
    <h1>生菜官网后台</h1>
    <form class="layui-form" action="${base}/login/main" method="post">
        <div class="layui-form-item">
            <input class="layui-input" name="username" placeholder="请输入用户名/手机号码/邮箱" value="super" lay-verify="required" type="text" autocomplete="off">
        </div>
        <div class="layui-form-item">
            <input class="layui-input" name="password" placeholder="请输入密码" lay-verify="required" value="1" type="password" autocomplete="off">
        </div>
        <div class="layui-form-item form_code">
            <input class="layui-input" name="code" placeholder="验证码" lay-verify="required" type="text" autocomplete="off">
            <div class="code"><img src="${base}/genCaptcha" width="116" height="36" id="mycode"></div>
        </div>
        <div class="layui-form-item">
            <input type="checkbox" name="rememberMe" value="true" lay-skin="primary" checked title="记住帐号?">
        </div>
        <div class="layui-form-item">
            <button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录</button>
        </div>
        <#--<div class="layui-form-item">
            <fieldset class="layui-elem-field">
                <div class="layui-field-box" style="color: #fff;font-size: 20px;">
                    用户名:admin <br/>密码:123456
                </div>
            </fieldset>
        </div>-->
    </form>
</div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/js/jquery.bcat.bgswitcher.js"></script>
<script>
    layui.use(['layer', 'form'], function() {
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form;

        $(document).ready(function() {
            var srcBgArray = [/*"https://static.mysiteforme.com/chun.jpg",*/
                /*"https://shengcaiweb.oss-cn-beijing.aliyuncs.com/htback.png"*/

                "https://static.mysiteforme.com/xia.jpg",
                 /*"https://static.mysiteforme.com/qiu.jpg",
                "https://static.mysiteforme.com/dong.jpg"*/];
            $('#bg-body').bcatBGSwitcher({
                timeout:5000,
                urls: srcBgArray,
                alt: 'Full screen background image'
            });
        });

        $("#mycode").on('click',function(){
            var t = Math.random();
            $("#mycode")[0].src="${base}/genCaptcha?t= "+t;
        });

        form.on('submit(login)', function(data) {
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            if($('form').find('input[type="checkbox"]')[0].checked){
                data.field.rememberMe = true;
            }else{
                data.field.rememberMe = false;
            }
            $.post(data.form.action, data.field, function(res) {
                layer.close(loadIndex);
                if(res.success){
                    location.href="${base}/"+res.data.url;
                }else{
                   layer.msg(res.message, {icon: 2});
                    $("#mycode").click();
                    $("input[name='code']").val('');

                }
            });
            return false;
        });
    });
</script>
</body>
</html>