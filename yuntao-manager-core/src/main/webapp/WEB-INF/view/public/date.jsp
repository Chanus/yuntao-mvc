<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="layui-inline">
  <label class="layui-form-label date-description">日期</label>
  <div class="layui-input-inline" style="width: 120px;">
    <input type="text" id="beginDate" placeholder="请选择开始日期" readonly class="layui-input" autocomplete="off">
  </div>
  <div class="layui-input-inline" style="width: 120px;">
    <input type="text" id="endDate" placeholder="请选择结束日期" readonly class="layui-input" autocomplete="off">
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
        dateDescription = (dateDescription === undefined || dateDescription === '') ? '日期' : dateDescription;
        $('.date-description').html(dateDescription);
        var initBeginDate = '', initEndDate = '', initBtns = ['clear', 'now', 'confirm'];
        if (initDate) {
            var d = new Date();
            initBeginDate = d.getFullYear() + '-' + util.digit(d.getMonth() + 1) + '-' + util.digit(d.getDate());
            initEndDate = d.getFullYear() + '-' + util.digit(d.getMonth() + 1) + '-' + util.digit(d.getDate());
            initBtns = ['now', 'confirm'];
        }

        // 日期时间选择器
        laydate.render({
            elem: '#beginDate',
            trigger: 'click',
            type: 'date',
            format: 'yyyy-MM-dd',
            value: initBeginDate,
            theme: 'grid',
            btns: initBtns
        });
        laydate.render({
            elem: '#endDate',
            trigger: 'click',
            type: 'date',
            format: 'yyyy-MM-dd',
            value: initEndDate,
            theme: 'grid',
            btns: initBtns
        });
    });
</script>