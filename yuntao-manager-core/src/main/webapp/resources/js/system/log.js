var action_path = ctx + '/system/log/';
layui.config({
    base: '../../js/',
    version: '0.0.1.1'
}).use(['table', 'popup', 'element'], function () {
    var $ = layui.jquery, table = layui.table, layer = layui.layer, popup = layui.popup, element = layui.element;
    var operateType = '1';

    // 渲染表格
    table.render({
        elem: '#datas',
        id: 'd',
        url: action_path + 'list.do',
        where: {
            beginTime: $('#beginTime').val(),
            endTime: $('#endTime').val(),
            operateType: '1'
        },
        method: 'post',
        cols: [[
            {field: 'operateNo', title: '操作账号', width: '10%', unresize: true, align: 'center'},
            {field: 'operateRoleName', title: '角色', width: '10%', unresize: true, align: 'center'},
            {field: 'operateModuleCode', title: '模块', width: '10%', unresize: true, align: 'center', templet: function (d) {
                if (d.operateModuleCode != null && d.operateModuleCode !== '') {
                    return d.operateModuleName + '(' + d.operateModuleCode + ')';
                } else {
                    return '';
                }
            }},
            {field: 'operateTypeDesc', title: '操作描述', unresize: true, align: 'center'},
            {field: 'operateConsumeTime', title: '耗时(毫秒)', width: '8%', unresize: true, align: 'center'},
            {field: 'operateIp', title: '操作IP', width: '12%', unresize: true, align: 'center'},
            {field: 'operateTime', title: '操作时间', width: '12%', unresize: true, align: 'center'},
            {fixed: 'right', title: '操作内容', width: '10%', unresize: true, align: 'center', toolbar: '#toolbar'}
        ]],
        page: true,
        limits: [10, 15, 20, 25],
        limit: 20,
        even: true,
        size: 'sm'
    });

    // 监听工具条
    table.on('tool(datas)', function (obj) {
        var data = obj.data; // 获得当前行数据
        var layEvent = obj.event; // 获得lay-event对应的值
        // var tr = obj.tr; // 获得当前行tr的DOM对象
        if (layEvent === 'content') {// 操作内容
            popup.open(800, 700, '<i class="layui-icon layui-icon-about"></i>操作内容', action_path + 'content.do?id=' + data.id);
        }
    });

    // 重新加载
    var reload = function () {
        table.reload('d', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                operateModuleCode: $("#operateModuleCode").val(),
                operateNo: $("#operateNo").val(),
                beginTime: $("#beginTime").val(),
                endTime: $("#endTime").val(),
                operateContent: encodeURI($("#operateContent").val()),
                operateType: operateType,
                v: new Date().getTime()
            }
        });
    };

    // 搜索
    $("#search").on('click', function () {
        reload();
    });

    // 监听tab标签
    element.on('tab(log)', function () {
        operateType = $(this).attr('lay-id');
        reload();
    });
});