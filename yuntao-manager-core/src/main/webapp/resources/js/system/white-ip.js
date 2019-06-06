var reload, action_path = ctx + '/system/whiteip/';
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
            {field: 'whiteIp', title: '白名单IP', width: '27%', unresize: true, align: 'center'},
            {field: 'loginNo', title: '登录账号', width: '27%', unresize: true, align: 'center'},
            {field: 'validStatus', title: '状态', width: '8%', unresize: true, align: 'center', templet: '#validStatusTpl'},
            {field: 'fixedStatus', title: '固定IP', width: '8%', unresize: true, align: 'center', templet: '#fixedStatusTpl'},
            {fixed: 'right', title: '操作', unresize: true, align: 'center', toolbar: '#toolbar'}
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
        }, 'data');
    };

    // 添加
    $("#add").on('click', function () {
        popup.open(600, 720, '<i class="layui-icon layui-icon-add-circle"></i>添加IP白名单', action_path + 'add.do');
    });

    // 监听工具条
    table.on('tool(datas)', function (obj) {
        var data = obj.data; // 获得当前行数据
        var layEvent = obj.event; // 获得lay-event对应的值
        // var tr = obj.tr; // 获得当前行tr的DOM对象
        if (layEvent === 'update') {// 编辑
            popup.open(600, 720, '<i class="layui-icon layui-icon-edit"></i>编辑IP白名单', action_path + 'update.do?id=' + data.id);
        } else if (layEvent === 'del') {// 删除
            operations.del({ids: [data.id]}, action_path + 'delete.do');
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