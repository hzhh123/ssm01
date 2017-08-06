<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/28
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
    <link rel="stylesheet" href="/assets/plugin/layui/css/layui.css">
</head>
<body>
<form id="form" class="layui-form" style="margin-top: 25px">
    <div class="layui-form-item">
            <label class="layui-form-label label-required">用户名</label>
            <div class="layui-input-inline">
                <input type="text" name="username" id="username" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label label-required">密码</label>
        <div class="layui-input-inline">
            <input type="password" name="password" id="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button id="btnSubmit" class="layui-btn" lay-submit lay-filter="login">提交</button>
        </div>
    </div>
</form>
    <script type="text/javascript" src="/assets/plugin/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/assets/plugin/layui/layui.js"></script>
    <script type="text/javascript" src="/assets/plugin/framework/js/global.js"></script>
    <script type="text/javascript">
        layui.use('form',function () {
                var form=layui.form(),
                    $=layui.jquery;
            if (window != top)
                top.location.href = location.href;
            form.on('submit(login)',function (form) {
                $.ajax({
                    type:'post',
                    url:'/login',
                    showMsg:false,
                    data: $('#form').serialize(),
                    success:function (data) {
                        var d=eval(data);
                        console.log(d=="1");
                        if(d=="1"){
                            location.href="/index";
                        }else{
                            $.layerMsg("密码或用户名不对！",'warning');
                        }
                    }
                });
                return false;
            });

        })
    </script>
</body>
</html>
