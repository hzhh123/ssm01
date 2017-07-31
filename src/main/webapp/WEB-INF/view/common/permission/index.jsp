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
        .layui-form-switch,.layui-form-onswitch{
            width: 54px;height: 24px;
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
    <input type="text" class="layui-input" id="searach"  placeholder="search..">
    <button class="layui-btn-small layui-btn layui-btn-normal" onclick="search();">搜索</button>
</div>

<div class="layui-form table-responsive">
    <table class="layui-table elight-table" id="gridList" lay-skin="line">
        <thead>
        <tr>
            <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
            <th>编码</th>
            <th>图标</th>
            <th>名称</th>
            <th>连接</th>
            <th>类型</th>
            <th>排序码</th>
            <th>公共</th>
            <th>状态</th>
            <th>备注</th>
        </tr>
        </tr>
        </thead>
        <tbody id="t1"></tbody>
    </table>
</div>
<div class="pull-left page-tip" style="margin-top: 20px;">总计<span id="total"></span>条记录,每页5条，共<span id="pages"></span>页</div>
<div id="page" class="pull-right"></div>
<script type="text/javascript" src="/assets/plugin/jquery/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/assets/plugin/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="/assets/plugin/framework/js/global.js" charset="utf-8"></script>
<!--内容模板-->
<script id="contentTpl" type="text/html">
    {{#  layui.each(d.content, function(index, item){ }}
    <tr>
        <td>
            <input type="checkbox" lay-skin="primary" value="{{item.id}}"></td>
        <td>{{# if(item.code==null){ }}
            {{# }else{ }}{{item.code}}
            {{# } }}
        </td>
        <td><i class="{{item.icon}}"></i></td>
        <td>{{item.name}}</td>
        <td>{{# if(item.url==null){ }}无
            {{# }else{ }}{{item.url}}
            {{# } }}
        </td>
        <td>{{# if(item.type==0){ }} 菜单
            {{# }else if(item.type==1){ }} 按钮
            {{# }else{ }} 其他
            {{# } }}
        </td>
        <td>{{item.sortCode}}</td>
        <td>{{# if(item.isPublic==true){ }}  <span class="label label-success label-sm">是</span>
            {{# }else{ }}  <span class="label label-info label-sm">否</span>
            {{# } }}
        </td>
        <td>{{# if(item.isEnable==true){ }}  <span class="label label-success label-sm">启用</span>
            {{# }else{ }}  <span class="label label-info label-sm">禁用</span>
            {{# } }}
        </td>
        <td>{{# if(item.remark==null){ }}无
            {{# }else{ }}{{item.remark}}
            {{# } }}
        </td>
    </tr>
    {{#  }); }}
    {{# if(d.content.length<=0) { }}
    <tr style="color: red">
        <td colspan="10">查无数据。</td>
    </tr>
    {{# } }}
</script>
<script type="text/javascript">
    function init() {
        layui.use(['form','laypage','laytpl'], function(){
            var $ = layui.jquery, form = layui.form(),laypage = layui.laypage;
            var laytpl = layui.laytpl;
            function toTime(t) {
                var d=new Date(t);
                var year=d.getFullYear();
                var month=d.getMonth()+1;
                var day=d.getDate();
                var h=d.getHours();
                var m=d.getMinutes();
                var s=d.getSeconds();
                if(month<10){
                    month="0"+month;
                }
                if(day<10){
                    day="0"+day;
                }
                if(h<10){
                    h="0"+h;
                }
                if(m<10){
                    m ="0"+m;
                }
                if(s<10){
                    s="0"+s;
                }
                return year+"-"+month+"-"+day+" "+h+":"+m+":"+s;
            }
            function render(data) {
                var getTpl = contentTpl.innerHTML;
                laytpl(getTpl).render(data, function(html){
                    t1.innerHTML = html;
                    form.render('checkbox');
                });
            }
            function getJson(curr) {
                $.ajax({
                    url: '/permission/list',
                    data: {"pageSize": 5, "pageIndex": curr||1,"keyword":$('#searach').val()},
                    dataType: 'json',
                    success: function (data) {
                        if (data.totalElements > 0) {
                            $('.page-tip').show();
                            render(data);
                            $('#total').text(data.totalElements);
                            $('#pages').text(data.totalPages);
                            laypage({
                                cont: 'page'
                                , prev: '<em><</em>'
                                , next: '<em>></em>'
                                , groups: 5
                                , skip: true
                                , pages: data.totalPages
                                , jump: function (obj) {
                                    $('#allChoose').attr('checked', false);
                                    var curr = obj.curr;
                                    $.ajax({
                                        url: '/permission/list',
                                        data: {"pageSize": 5, "pageIndex": curr,"keyword":$('#searach').val()},
                                        dataType: 'json',
                                        success: function (data) {
                                            render(data);
                                        }
                                    })
                                }
                            });
                        }else{
                            $('#t1').html('');
                            $('.page-tip').hide();
                        }
                    }

                });
            }
            getJson(1);
            //全选
            form.on('checkbox(allChoose)', function(data){
                var child = $(data.elem).parents('table').find('tbody .table-check');
                child.each(function(index, item){
                    item.checked = data.elem.checked;
                });
                form.render('checkbox');
            });
            form.on('switch', function(data){
                var id=data.value;
                var value=0;
                if(data.elem.checked){
                    value=1;
                }
                $.ajax({
                    url:'/user/updateState',
                    data:{"id":id,"state":value},
                    dataType:'json',
                    type:'post',
                    success:function(data){
                        if(data=="success"){
                            $.layerMsg("状态修改成功！","success");
                        }
                    }
                })
            });
        });
    }

    init();
    function del() {
        var ids =getPrimaryKey("gridList");
        console.log(ids);
        if (ids.length <= 0) {
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
                        // $("#gridList").gridRemoveSelectedRow();
                        init();
                    }
                }
            })
        }
    }

    function add() {
        $.layerOpen({
            id: "add",
            title: "<i class='fa fa-plus'></i> 新增权限",
            width: "670px",
            height: "530px",
            shadeClose:false,
            content: "/common/permission/form.html",
            yes: function (iBody) {
                iBody.find('#btnSubmit').click();
                init();
            }
        });
    }
    function getPrimaryKey(id) {
        var result=[];
        $.each($("#"+id).find('.table-check:checked'),function (index,item) {
            result.push(item.value);
        })
        return result;
    }
    function edit() {
        var ids = getPrimaryKey("gridList");
        console.log(ids)
        if (ids.length != 1) {
            $.layerMsg("请勾选要编辑的记录且条数只能为一。", "warning");
            return;
        }else {
            $.layerOpen({
                id: "edit",
                title: "<i class='fa fa-edit'></i> 编辑权限",
                width: "670px",
                height: "530px",
                content: "/common/permission/form.html?id="+ids[0],
                yes: function (iBody) {
                    iBody.find('#btnSubmit').click();
                    init();
                }
            });
        }
    }

    function edit1(id) {
        $.layerOpen({
            id: "edit1",
            title: "<i class='fa fa-edit'></i> 编辑权限",
            width: "670px",
            height: "250px",
            content: "/common/user/form.html?id="+id,
            yes: function (iBody) {
                iBody.find('#btnSubmit').click();
                init();
            }
        });
    }
    function del1(id) {
        var ids =[];
        ids.push(id);
        $.ajax({
            url:'/user/delete',
            data:{"ids":ids},
            dataType:'json',
            type:'post',
            success:function (data) {
                if(data=="success"){
                    $.layerMsg('删除成功!','success');
                    // $("#gridList").gridRemoveSelectedRow();
                    init();
                }
            }
        })
    }

    function view(id) {
        $.layerOpen({
            id: "view",
            title: "<i class='fa fa-search'></i> 查看权限",
            width: "670px",
            height: "250px",
            content: "/common/user/detail.html?id="+id,
            btn:null
        });
    }

    function search() {
        init();
    }
</script>
</body>
</html>
