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
        <th>ID</th>
        <th>用户名</th>
        <th>密码</th>
    </tr>
    </thead>
    <tbody id="t1"></tbody>
</table>
<div id="page"></div>
<input type="hidden"  id="pages" >
<script type="text/javascript" src="/assets/plugin/jquery/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/assets/plugin/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['laypage', 'layer','laytpl'], function() {
        var laypage = layui.laypage
            , layer = layui.layer,
           $ = layui.jquery,
            laytpl = layui.laytpl;
        //模拟渲染
        var render = function(data){
            $('#t1').html('');
            layui.each(data.content, function(index, item){
                $('#t1').append("<tr>");
                $('#t1').append("<td>"+item.id+"</td>");
                $('#t1').append("<td>"+item.username+"</td>");
                $('#t1').append("<td>"+item.password+"</td>");
                $('#t1').append("</tr>");
            });
        };
        function getJson(curr) {
            $.ajax({
                url: '/user/list',
                data: {"pageSize": 5, "pageNo": curr||1},
                dataType: 'json',
                async:false,//这一步很重要，必填
                success: function (data) {
                    $('#pages').val(data.totalPages);
                    render(data);
                }

            });

        }
        getJson(1);
        laypage({
            cont: 'page'
            ,prev: '<em><</em>'
            ,next: '<em>></em>'
            ,groups: 5
            ,skip: true
//            ,skin: '#1E9FFF'
            ,pages:$('#pages').val()
            ,jump: function(obj){
                var curr=obj.curr;
                getJson(curr);
            }
        });


    });


</script>
</body>
</html>
