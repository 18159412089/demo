<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>layui表单生成器</title>
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${base}/static/css/build.css">
    <style>
        .layout-page .card-header {
            border-radius: 0;
            border-top: 4px solid #d2d2d2;
            border-bottom: 1px solid #e2e2e2;
        }

        /* 页面通用样式 */
        .layout-page {
            background-color: #eeeeee;
            padding: 18px;
        }
    </style>
</head>
<body class="layout-page">
<div class="layui-row layui-col-space20">
    <div class="layui-col-md5">
        <div class="layui-card">
            <div class="layui-card-header card-header">
                <span><i class="fa fa-bars"></i> 将表单元素拖拽到构建面板中即可生成表单块</span>
            </div>
            <div class="layui-card-body">
                <div class="layui-form element-panel">
                    <div class="layui-form-item">
                        <label class="layui-form-label">输入框</label>
                        <div class="layui-input-inline">
                            <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">密码框</label>
                        <div class="layui-input-inline">
                            <input type="password" name="password" placeholder="请输入密码" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">选择框</label>
                        <div class="layui-input-inline">
                            <select name="interest" lay-filter="aihao">
                                <option value=""></option>
                                <option value="0">写作</option>
                                <option value="1">阅读</option>
                                <option value="2">游戏</option>
                                <option value="3">音乐</option>
                                <option value="4">旅行</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">上传图片</label>
                        <div class="layui-input-inline">
                            <button type="button" class="layui-btn upload-image" name="image[]"
                                    th:attr="up-url=@{/upload/image}" up-field="path">
                                <i class="layui-icon">&#xe67c;</i>上传图片
                            </button>
                        </div>
                        <div class="upload-show"></div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">时间选择</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="laydate" placeholder="yyyy-MM-dd">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">复选框</label>
                        <div class="layui-input-block">
                            <input type="checkbox" name="like[write]" title="写作">
                            <input type="checkbox" name="like[read]" title="阅读" checked="">
                            <input type="checkbox" name="like[game]" title="游戏">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">开关</label>
                        <div class="layui-input-block">
                            <input type="checkbox" name="close" lay-skin="switch" lay-text="ON|OFF">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">单选框</label>
                        <div class="layui-input-block">
                            <input type="radio" name="sex" value="男" title="男" checked="">
                            <input type="radio" name="sex" value="女" title="女">
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">文本域</label>
                        <div class="layui-input-block">
                            <textarea placeholder="请输入内容" class="layui-textarea" name="desc"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn ajax-submit"><i class="fa fa-check-circle"></i> 保存</button>
                            <button class="layui-btn btn-secondary close-popup"><i class="fa fa-times-circle"></i> 关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-col-md7">
        <div class="layui-card build-card">
            <div class="layui-card-header card-header">
                <span><i class="fa fa-bars"></i> 构建面板</span>
                <button class="layui-btn layui-btn-primary layui-btn-sm build-generate">生成HTML</button>
            </div>
            <div class="layui-card-body layui-form build-panel"></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/build.js"></script>
</body>
</html>
