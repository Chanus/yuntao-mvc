var reload, action_path = ctx + '/system/param/';
layui.config({
    base: '../../js/',
    version: '0.0.1.1'
}).use(['table', 'popup', 'form', 'operations'], function () {
    var $ = layui.jquery, table = layui.table, layer = layui.layer, popup = layui.popup, form = layui.form,
        operations = layui.operations;

    // 渲染表格
    table.render({
        elem: '#datas',
        id: 'd',
        url: action_path + 'list.do',
        method: 'post',
        cols: [[
            {type: 'checkbox', width: 60, fixed: 'left'},
            {field: 'id', title: 'ID', width: '8%', unresize: true, align: 'center'},
            {field: 'paramCode', title: '参数代码', width: '15%', unresize: true, align: 'center'},
            {field: 'paramData', title: '参数值', width: '15%', unresize: true, align: 'center'},
            {field: 'validStatus', title: '状态', width: '8%', unresize: true, align: 'center', templet: '#validStatusTpl'},
            {field: 'remark', title: '备注', unresize: true, align: 'center'},
            {fixed: 'right', title: '操作', width: '20%', unresize: true, align: 'center', toolbar: '#toolbar'}
        ]],
        page: true,
        limits: [10, 15, 20, 25],
        limit: 20,
        even: true,
        size: 'sm'
    });

    // 重新加载
    reload = function () {
        table.reload('d', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                v: new Date().getTime()
            }
        });
    };

    // 添加
    $("#add").on('click', function () {
        popup.open(600, 550, '<i class="layui-icon layui-icon-add-circle"></i>添加系统参数', action_path + 'add.do');
    });

    // 重载系统参数
    $("#reload").on('click', function () {
        $.ajax({
            type: 'post',
            url: action_path + 'reload-param.do',
            dataType: 'json',
            success: function (data) {
                if (data.code === 0) {
                    layer.msg(data.msg, {icon: 1, time: 1000});
                } else {
                    layer.msg(data.msg, {icon: 2, anim: 6, time: 2000});
                }
            },
            error: function () {
                layer.msg('请求异常，操作失败', {icon: 2, anim: 6, time: 2000});
            }
        });
    });

    // 监听工具条
    table.on('tool(datas)', function (obj) {
        var data = obj.data; // 获得当前行数据
        var layEvent = obj.event; // 获得lay-event对应的值
        // var tr = obj.tr; // 获得当前行tr的DOM对象
        if (layEvent === 'update') {// 编辑
            popup.open(600, 600, '<i class="layui-icon layui-icon-edit"></i>编辑系统参数', action_path + 'update.do?id=' + data.id);
        } else if (layEvent === 'del') {// 删除
            operations.del({ids: [data.id]}, action_path + 'delete.do');
        } else if (layEvent === 'up' || layEvent === 'down' || layEvent === 'top') {// 调整优先级
            var msg = layEvent === 'up' ? '提升优先级' : (layEvent === 'down' ? '降低优先级' : '置顶优先级');
            layer.confirm('确定' + msg + '吗？', {icon: 3, title: '提示'}, function (index) {
                if (data.priority) {
                    $.ajax({
                        type: 'post',
                        url: action_path + 'priority.do?priority=' + data.priority + '&direction=' + layEvent,
                        dataType: 'json',
                        success: function (data) {
                            if (data.code === 0) {
                                layer.msg(data.msg, {icon: 1, time: 1000}, function () {
                                    $(".layui-laypage-btn")[0].click();
                                });
                            } else {
                                layer.msg(data.msg, {icon: 2, anim: 6, time: 2000});
                            }
                        },
                        error: function () {
                            layer.msg('请求异常，操作失败', {icon: 2, anim: 6, time: 2000});
                        }
                    });
                } else {
                    layer.msg('请选择要操作的记录！', {icon: 2, anim: 6, time: 2000});
                }
                layer.close(index);
            });
        }
    });

    // 增删改
    var p = $('.layui-show', window.parent.document).children()[0].contentWindow;
    operations.id = 'id';
    operations.addUrl = action_path + 'add.do';
    operations.commonAddFunc = function () {
        p.reload();
    };
    operations.updateUrl = action_path + 'update.do';
    operations.delUrl = action_path + 'delete.do';
});