<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/25
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/assets/plugin/layui/css/layui.css">
</head>
<body>
<table class="layui-table">
    <thead>
    <tr>
        <th>
            <input type="checkbox" lay-skin="primary"></th>
        <th>ID</th>
        <th>用户名</th>
        <th>密码</th>
    </tr>
    </thead>
    <!--内容容器-->
    <tbody id="content"></tbody>
</table>
<div id="paged"></div>
<script type="text/javascript" src="/assets/plugin/jquery/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/assets/plugin/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="/assets/plugin/framework/js/global.js" charset="utf-8"></script>
<script type="text/javascript" src="/assets/plugin/framework/js/paging.js" charset="utf-8"></script>
<!--内容模板-->
<script id="contentTpl" type="text/html">
    {{#  layui.each(d.content, function(index, item){ }}
    <tr>
        <td>
            <input type="checkbox" lay-skin="primary" value="{{item.id}}"></td>
        <td>{{item.id}}</td>
        <td>{{item.username}}</td>
        <td>{{item.password}}</td>
    </tr>
    {{#  }); }}
    {{# if(d.content.length<=0) { }}
    <tr style="color: red">
        <td colspan="8">查无数据。</td>
    </tr>
    {{# } }}
</script>

<script type="text/javascript">
    var paging;
    layui.config({
        base: parent._baseUrl
    }).use(['paging', 'form', 'layer'], function () {
        var layer = parent.layer || layui.layer;
        var form = layui.form();
        paging = layui.paging();
        initGrid();
    });

    function initGrid() {
        paging.init({
            url: '/user/list',
            elem: '#content',
            tempElem: '#contentTpl',
            pageConfig: {
                elem: '#paged',
                pageSize: 10,
            },
            success: function () {

            }
        });
    }



</script>
</body>
</html>
