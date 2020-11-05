<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>系统信息</title>
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all"/>
</head>
<body>
<!-- 正文开始 -->
<div class="layui-fluid">
    <button type="button" class="layui-btn" id="init">手动连接</button>
    <button type="button" class="layui-btn" id="destroy">断开连接</button>
    <div class="layui-row layui-col-space15">
        <div class="layui-col-lg6">
            <div class="layui-card">
                <div class="layui-card-header">CPU信息</div>
                <div class="layui-card-body">
                    <table class="layui-table layui-text">
                        <thead>
                        <tr>
                            <th>属性</th>
                            <td>值</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>核心数</td>
                            <td id="cpuNum"></td>
                        </tr>
                        <tr>
                            <td>用户使用率</td>
                            <td id="cpuUsed"></td>
                        </tr>
                        <tr>
                            <td>系统使用率</td>
                            <td id="cpuSys"></td>
                        </tr>
                        <tr>
                            <td>当前空闲率</td>
                            <td id="cpuFree"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="layui-col-lg6">
            <div class="layui-card">
                <div class="layui-card-header">内存信息</div>
                <div class="layui-card-body">
                    <table class="layui-table layui-text">
                        <thead>
                        <tr>
                            <th>属性</th>
                            <th>内存</th>
                            <th>JVM</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>总内存</td>
                            <td id="memTotal"></td>
                            <td id="jvmTotal"></td>
                        </tr>
                        <tr>
                            <td>已用内存</td>
                            <td id="memUsed"></td>
                            <td id="jvmUsed"></td>
                        </tr>
                        <tr>
                            <td>剩余内存</td>
                            <td id="memFree"></td>
                            <td id="jvmFree"></td>
                        </tr>
                        <tr>
                            <td>使用率</td>
                            <td id="memUsage"></td>
                            <td id="jvmUsage"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="layui-col-lg12">
            <div class="layui-card">
                <div class="layui-card-header">JAVA虚拟机信息</div>
                <div class="layui-card-body">
                    <table class="layui-table layui-text">
                        <tbody>
                        <tr>
                            <td class="layui-table-header">Jvm名称</td>
                            <td id="jvmName"></td>
                            <td class="layui-table-header">Java版本</td>
                            <td id="jvmVersion"></td>
                        </tr>
                        <tr>
                            <td class="layui-table-header">启动时间</td>
                            <td id="jvmStartTime"></td>
                            <td class="layui-table-header">运行时长</td>
                            <td id="jvmRunTime"></td>
                        </tr>
                        <tr>
                            <td colspan="1" class="layui-table-header">安装路径</td>
                            <td colspan="3" id="jvmHome"></td>
                        </tr>
                        <tr>
                            <td colspan="1" class="layui-table-header">项目路径</td>
                            <td colspan="3" id="sysUserDir"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="layui-col-lg12">
            <div class="layui-card">
                <div class="layui-card-header">服务器信息</div>
                <div class="layui-card-body">
                    <table class="layui-table layui-text">
                        <tbody>
                        <tr>
                            <td class="layui-table-header">服务器名称</td>
                            <td id="sysComputerName"></td>
                            <td class="layui-table-header">操作系统</td>
                            <td id="sysOsName"></td>
                        </tr>
                        <tr>
                            <td class="layui-table-header">服务器IP</td>
                            <td id="sysComputerIp"></td>
                            <td class="layui-table-header">系统架构</td>
                            <td id="sysOsArch"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="layui-col-lg12">
            <div class="layui-card">
                <div class="layui-card-header">磁盘信息</div>
                <div class="layui-card-body">
                    <table class="layui-table layui-text">
                        <thead>
                        <tr>
                            <th>盘符路径</th>
                            <th>文件系统</th>
                            <th>盘符类型</th>
                            <th>总大小</th>
                            <th>可用大小</th>
                            <th>已用大小</th>
                            <th>已用百分比</th>
                        </tr>
                        </thead>
                        <tbody id="sysFiles">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</div>
<script type="text/javascript" src="${base}/static/layui/layui.all.js"></script>
<script src="${base}/static/js/core.util.js"></script>
<script src="${base}/static/js/sockjs.min.js"></script>
<script src="${base}/static/js/stomp.js"></script>
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script>
    const wsHost = "http://${url!}:${port!}/notification";
    const wsTopic = "/topic/system";
    const layer = layui.layer;
    const $ = jQuery = layui.jquery;
    let socket = {};
    let stompClient = {};

    function setInfo(data) {
        if (data != null) {
            $("#cpuNum").text(data.cpu.cpuNum);
            $("#cpuUsed").text(data.cpu.used + '%');
            $("#cpuSys").text(data.cpu.sys + '%');
            $("#cpuFree").text(data.cpu.free + '%');
            $("#memTotal").text(data.mem.total + 'GB');
            $("#jvmTotal").text(data.jvm.total + 'GB');
            $("#memUsed").text(data.mem.used + 'GB');
            $("#jvmUsed").text(data.jvm.used + 'GB');
            $("#memFree").text(data.mem.free + 'GB');
            $("#jvmFree").text(data.jvm.free + 'GB');
            $("#memUsage").text(data.mem.usage + '%');
            $("#jvmUsage").text(data.jvm.usage + '%');
            $("#jvmName").text(data.jvm.name);
            $("#jvmVersion").text(data.jvm.version);
            $("#jvmStartTime").text(data.jvm.startTime);
            $("#jvmRunTime").text(data.jvm.runTime);
            $("#jvmHome").text(data.jvm.home);
            $("#sysUserDir").text(data.sys.userDir);
            $("#sysComputerName").text(data.sys.computerName);
            $("#sysOsName").text(data.sys.osName);
            $("#sysComputerIp").text(data.sys.computerIp);
            $("#sysOsArch").text(data.sys.osArch);
            $("#sysFiles").empty();
            data.sysFiles.map((item, index) => {
                $("#sysFiles").append('<tr><td>' + item.usage + '%</td><td>' + item.typeName + '</td><td>' + item.sysTypeName + '</td><td>' + item.used + '</td><td>' + item.total + '</td><td>' + item.free + '</td><td>' + item.dirName + '</td></tr>');
            })
            monitor(data);
        }
    }

    $(function () {
        initSockJs();
        //强制关闭浏览器  调用websocket.close（）,进行正常关闭
        window.onunload = function() {
            destroySockJs();
        }
        function initSockJs() {
            getSystemInfo();
            socket = new SockJS(wsHost);
            stompClient = Stomp.over(socket);
            stompClient.connect({}, (frame) => {
                console.log('websocket连接成功:' + frame);
                $('#init').addClass("layui-btn-disabled").attr("disabled", true);
                $('#destroy').removeClass("layui-btn-disabled").attr("disabled", false);
                layer.msg('websocket服务器连接成功');
                // 另外再注册一下消息推送
                stompClient.subscribe(wsTopic, (response) => {
                    //页面渲染
                    console.log("stompClient response");
                    let res = JSON.parse(response.body);
                    setInfo(res);
                });
            });
        }

        function getSystemInfo() {
            $.ajax({
                url: "${base}/sys/info",
                type: "get",
                async: false,
                dataType: "json",
                success: function (res) {
                    console.log("getSystemInfo:" + res);
                    var data = res.data;
                    setInfo(data);
                }
            });
        }

        function destroySockJs() {
            if (stompClient != null) {
                stompClient.disconnect();
                socket.onclose;
                socket.close();
                stompClient = {};
                socket = {};
                $('#init').removeClass("layui-btn-disabled").attr("disabled", false);
                $('#destroy').addClass("layui-btn-disabled").attr("disabled", true);
            }
            console.log('websocket断开成功！');
            layer.msg('websocket断开成功！');
        }


        $('#init').on('click', () => {
            initSockJs()
        })

        $('#destroy').on('click', function () {
            destroySockJs()
        })
    });
    function monitor(data) {
        let msg = "";

        if (data.cpu.sys >= 80) {
            $("#cpuSys").addClass("text-danger");
            msg += "提示：CPU使用率过高！</br>";
        }

        if (data.mem.usage >= 80) {
            $("#memUsage").addClass("text-danger");
            msg += "提示：内存使用率过高！</br>";
        }

        if (data.jvm.usage >= 80) {
            $("#jvmUsage").addClass("text-danger");
            msg += "提示：jvm使用率过高！";
        }

        if (msg !== "") {
            layer.msg(msg)
        }
    }
</script>
</body>

</html>