<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/27
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Title</title>
    <link rel="stylesheet" href="/assets/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="/assets/plugin/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="/assets/plugin/framework/css/gloabl.css">
    <link rel="stylesheet" href="/assets/plugin/Animate/animate.min.css">
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-navbar">
        <div class="layui-navbar-header">
            <img src="/assets/plugin/framework/img/logo-1.png" alt="">
            <button class="layui-btn sidebar-toggle"  style="margin-left: 20px;"><i class="fa fa-tasks"></i></button>
        </div>
        <ul class="layui-nav navbar-right">
            <li class="layui-nav-item"><a href="">最新活动</a></li>
            <li class="layui-nav-item layui-this">
                <a href="javascript:;">产品</a>
                <dl class="layui-nav-child">
                    <dd><a href="">选项1</a></dd>
                    <dd><a href="">选项2</a></dd>
                    <dd><a href="">选项3</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">大数据</a></li>
            <li class="layui-nav-item"><a href="">社区</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">${sessionScope.loginUser.username}</a>
                <dl class="layui-nav-child">
                    <dd><a href="/logout"><i class="fa fa-circle-o-notch"></i> 注销</a></dd>
                    <dd><a href="">后台模版</a></dd>
                    <dd class="layui-this"><a href="">选中项</a></dd>
                    <dd><a href="">电商平台</a></dd>
                </dl>
            </li>
        </ul>
    </div>
    <aside class="layout-side">
        <ul class="side-menu">

        </ul>
    </aside>
    <div class="content">
        <section class="layout-main"  >
            <div class="layout-main-tab">
                <button class="tab-btn btn-left"><i class="icon-font">&#xe60e;</i></button>
                <nav class="tab-nav animated fadeIn">
                    <div class="tab-nav-content">
                        <a href="javascript:;" class="content-tab active" data-id="home.html">首页</a>
                    </div>
                </nav>
                <button class="tab-btn btn-right"><i class="icon-font">&#xe60f;</i></button>
            </div>
            <div class="layout-main-body animated fadeIn" >
                <iframe class="body-iframe" name="iframe0" width="100%" height="100%" src="/assets/demo/home.html" frameborder="0" data-id="home.html" seamless></iframe>
            </div>
        </section>
        <div class="footer" style="height: 40px;position: fixed;bottom: 0;background: #F2F2F2;width: 100%;">
            <div class="sm-toggle">
                <button class="layui-btn layui-btn-small layui-btn-default">
                    <i class="layui-icon">&#xe602;</i>
                </button>
            </div>
            <div style="text-align: center;margin-top: 15px;color: #666">hzhh123@2014</div>
        </div>
    </div>
</div>
<script src="/assets/plugin/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/assets/plugin/framework/layer/layer.js"></script>
<script src="/assets/plugin/layui/layui.js"></script>
<script src="/assets/plugin/framework/js/default.js"></script>
<script>
    layui.use('element', function(){
        var element = layui.element(); //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(demo)', function(elem){
            //console.log(elem)
            layer.msg(elem.text());
        });
    });
    $('aside').height($(window).height()-60);
    $('.content').height($(window).height()-60);
    $('.sidebar-toggle').mouseenter(function () {
        layer.tips("切换左侧菜单", this);
    });
    $('.sm-toggle button').mouseenter(function () {
        layer.tips("切换左侧菜单", this);
    });
    $('.sidebar-toggle').click(function () {
        if($(window).width()>768) {
            if ($('aside').css('margin-left') == '0px') {
                $('aside').animate({marginLeft: -200}, 'fast');
                $('.content').animate({width: $(window).width()}, 'fast');
            }
            if ($('aside').css('margin-left') < '0px') {
                $('aside').animate({marginLeft: 0}, 'fast');
                $('.content').animate({width: $(window).width()-200}, 'fast');
            }
        }else{
            $('.layui-nav').toggle();
            $('.content').css('width','100%');
        }
    })

    $(window).resize(function () {
        if($(window).width()>768){
            $('aside').animate({marginLeft: 0}, 'fast');
            $('.content').width($(window).width()-$('aside').width());
            $('.layui-nav').show();
        }else{
            $('.content').width($(window).width());
            $('.layui-nav').hide();
            $('aside').animate({marginLeft: -200}, 'fast');
        }
        $('.content').height($(window).height()-60);
        $('aside').height($(window).height()-60);
    })
    $('.sm-toggle button').click(function () {
        $('aside').animate({marginLeft: 0}, 'fast');
    })
</script>
</body>
</html>
