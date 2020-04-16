<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="layui-inline">
  <label class="layui-form-label date-description">时间</label>
  <div class="layui-input-inline">
    <input type="text" id="beginTime" placeholder="请选择开始时间" readonly class="layui-input" autocomplete="off">
  </div>
  <div class="layui-input-inline">
    <input type="text" id="endTime" placeholder="请选择结束时间" readonly class="layui-input" autocomplete="off">
  </div>
</div>
<script type="text/javascript">
    layui.config({
        base: '../js/',
        version: '0.0.1.1'
    }).use(['laydate', 'util'], function () {
        var $ = layui.jquery, laydate = layui.laydate, util = layui.util;
        var dateConfig = $('#date-config');
        var initDate = dateConfig.attr('date-init'), dateDescription = dateConfig.attr('date-description');
        initDate = initDate === undefined || initDate === 'true';
        dateDescription = (dateDescription === undefined || dateDescription === '') ? '时间' : dateDescription;
        $('.date-description').html(dateDescription);
        var initBeginTime = '', initEndTime = '', initBtns = ['clear', 'now', 'confirm'];
        if (initDate) {
            var d = new Date();
            initBeginTime = d.getFullYear() + '-' + util.digit(d.getMonth() + 1) + '-' + util.digit(d.getDate()) + ' 00:00:00';
            initEndTime = d.getFullYear() + '-' + util.digit(d.getMonth() + 1) + '-' + util.digit(d.getDate()) + ' 23:59:59';
            initBtns = ['now', 'confirm'];
        }
        // 日期时间选择器
        laydate.render({
            elem: '#beginTime',
            trigger: 'click',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm:ss',
            value: initBeginTime,
            theme: 'grid',
            btns: initBtns
        });
        laydate.render({
            elem: '#endTime',
            trigger: 'click',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm:ss',
            value: initEndTime,
            theme: 'grid',
            btns: initBtns
        });
    });
</script>