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
    <link rel="stylesheet" href="/assets/plugin/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet" href="/assets/plugin/font-awesome/css/font-awesome.css">
    <style type="text/css">
        .elight-table .layui-form-checkbox {
            margin-top: 0;
            height: 20px;
            margin-right: 0px;
            line-height: 20px;
        }
        .toolbar{
            position:relative;float: right;
        }
        .toolbar input{
            height: 30px;width: 200px;position:relative;float: left;
        }
        .toolbar button{
            position:relative;float: right;
        }
    </style>
</head>
<body style="padding: 10px;">

<div class="layui-btn-group">
    <button class="layui-btn layui-btn-normal layui-btn-small" onclick="add()">添加</button>
    <button class="layui-btn layui-btn-default layui-btn-small" onclick="del()">删除</button>
    <button class="layui-btn layui-btn-default layui-btn-small" onclick="edit()">修改</button>
</div>
<div class="toolbar" >
    <input type="text" class="layui-input"  placeholder="search..">
    <button class="layui-btn-small layui-btn layui-btn-normal" >搜索</button>
</div>

<div class="layui-form">
    <table class="layui-table elight-table" id="gridList">
        <colgroup>
            <col width="50"/>
            <col width="50"/>
            <col width="50"/>
            <col width="200"/>
            <col width="250"/>
        </colgroup>
        <thead>
        <tr>
            <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
            <th>ID</th>
            <th>用户名</th>
            <th>密码</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="t1"></tbody>
    </table>
</div>
<div id="page"></div>
<script type="text/javascript" src="/assets/plugin/jquery/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/assets/plugin/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="/assets/plugin/framework/js/global.js" charset="utf-8"></script>
<script type="text/javascript">

    layui.use(['form','laypage'], function(){
        var $ = layui.jquery, form = layui.form(),laypage = layui.laypage;

        var render = function(data){
            $('#t1').html('');
            $.each(data.content, function(index, item){
                if(item.username==null){
                    item.username="";
                }
                if(item.password==null){
                    item.password="";
                }
                $('#t1').append("<tr>"
                    +"<td><input type=\"checkbox\" lay-skin=\"primary\" value='"+item.id+"'></td>"
                    +"<td>"+item.id+"</td>"
                    +"<td>"+item.username+"</td>"
                    +"<td>"+item.password+"</td>"
                    +"<td><div class=\"layui-btn-group\">"
                    +"<button class=\"layui-btn layui-btn-default layui-btn-small\" onclick=\"edit()\">修改</button>"
                    +"<button class=\"layui-btn layui-btn-warm layui-btn-small\" onclick=\"del()\">删除</button>"
                    +"<button class=\"layui-btn layui-btn-normal layui-btn-small\" onclick=\"del()\">查看</button>"
                    +"</div> </td>"
                    +"</tr>");
            });
            form.render();
        };
        function getJson(curr) {
            $.ajax({
                url: '/user/list',
                data: {"pageSize": 5, "pageIndex": curr||1},
                dataType: 'json',
                success: function (data) {
                    render(data);
                    laypage({
                        cont: 'page'
                        ,prev: '<em><</em>'
                        ,next: '<em>></em>'
                        ,groups: 5
                        ,skip: true
                        ,pages:data.totalPages
                        ,jump: function(obj){
                            $('#allChoose').attr('checked',false);
                            var curr=obj.curr;
                            $.ajax({
                                url: '/user/list',
                                data: {"pageSize": 5, "pageIndex": curr},
                                dataType: 'json',
                                success: function (data) {
                                    render(data);
                                }
                            })
                        }
                    });
                }

            });

        }
        getJson(1);
        //全选
        form.on('checkbox(allChoose)', function(data){
            var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
            child.each(function(index, item){
                item.checked = data.elem.checked;
            });
            form.render('checkbox');
        });
    });

    function del() {
        var ids = $("#gridList").gridSelectedRowValue();
        if (ids.length == 0) {
            $.layerMsg("请勾选要删除的记录。", "warning");
            return;
        }else {
            $.ajax({
                url:'/user/delete',
                data:{"ids":ids},
                dataType:'json',
                type:'post',
                success:function (data) {
                    if(data=="success"){
                        $.layerMsg('删除成功!','success');
                        $("#gridList").gridRemoveSelectedRow();
                    }
                }
            })
        }
    }

    function add() {
        $.layerOpen({
            id: "add",
            title: "<i class='fa fa-plus'></i> 新增用户",
            width: "670px",
            height: "250px",
            content: "/common/user/form.html",
            yes: function (iBody) {
                iBody.find('#btnSubmit').click();
                getJson(1);
            }
        });
    }
    function edit() {
        var ids = $("#gridList").gridSelectedRowValue();
        if (ids.length != 1) {
            $.layerMsg("请勾选要编辑的记录且条数只能为一。", "warning");
            return;
        }else {
            $.layerOpen({
                id: "add",
                title: "<i class='fa fa-edit'></i> 编辑用户",
                width: "670px",
                height: "250px",
                content: "/common/user/form.html?id="+ids[0],
                yes: function (iBody) {
                    iBody.find('#btnSubmit').click();
                    getJson(1);
                }
            });
        }
    }
</script>
</body>
</html>
