<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../public/public.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../../../public/header.jsp" %>
  </head>
  <body>
    <div class="pd-5 layui-form-sm">
      <form class="layui-form layui-form-pane">
        <input type="hidden" name="id" value="${scheduleTrigger.id }" class="layui-input" autocomplete="off">
        <input type="hidden" name="jobId" value="${scheduleTrigger.jobId }" class="layui-input" autocomplete="off">
        <div class="layui-form-item">
          <label class="layui-form-label">触发器名称<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="triggerName" required lay-verify="required" value="${scheduleTrigger.triggerName }" placeholder="请输入触发器名称" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">触发器组<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="triggerGroup" required lay-verify="required" value="${scheduleTrigger.triggerGroup }" placeholder="请输入触发器组" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">Cron表达式<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="triggerCron" required lay-verify="required" value="${scheduleTrigger.triggerCron }" placeholder="请输入Cron表达式" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">开始时间</label>
          <div class="layui-input-block">
            <input type="text" id="triggerStartTimeStr" name="triggerStartTimeStr" readonly value="${scheduleTrigger.triggerStartTimeStr }" placeholder="请输入开始时间" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">结束时间</label>
          <div class="layui-input-block">
            <input type="text" id="triggerEndTimeStr" name="triggerEndTimeStr" readonly value="${scheduleTrigger.triggerEndTimeStr }" placeholder="请输入结束时间" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label">属性配置</label>
          <div class="layui-input-block">
            <textarea name="triggerData" placeholder="请输入触发器属性配置，数据为json格式" class="layui-textarea">${scheduleTrigger.triggerData }</textarea>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">优先级</label>
          <div class="layui-input-block">
            <input type="text" name="priority" required lay-verify="integer" lay-ignore="ignore" lay-error-msg="优先级必须为整数" value="${scheduleTrigger.priority }" placeholder="请输入优先级" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item" pane>
          <label class="layui-form-label">状态<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="radio" name="validStatus" lay-verify="mustradio" value="1" title="启用" <c:if test="${scheduleTrigger.validStatus == '1' }">checked</c:if>>
            <input type="radio" name="validStatus" value="0" title="停用" <c:if test="${empty scheduleTrigger.validStatus or scheduleTrigger.validStatus == '0' }">checked</c:if>>
          </div>
        </div>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label">备注</label>
          <div class="layui-input-block">
            <textarea name="remark" placeholder="请输入备注" class="layui-textarea">${scheduleTrigger.remark }</textarea>
          </div>
        </div>
        <div class="layui-form-item text-c">
          <div class="layui-inline">
            <c:choose>
              <c:when test="${ cmd eq 'add' }">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="add">添加</button>
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="addAgain">保存并添加下一个</button>
              </c:when>
              <c:when test="${ cmd eq 'update' }">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="update">确定</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
              </c:when>
            </c:choose>
            <span class="layui-btn layui-btn-primary" id="cancel">取消</span>
          </div>
        </div>
      </form>
    </div>
    <%@ include file="../../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx}/js/system/job-trigger.js?v=0.1.7.2"></script>
    <script type="text/javascript">
        layui.use([ 'jquery', 'layer', 'laydate' ], function() {
            var $ = layui.jquery, layer = layui.layer, laydate = layui.laydate;
            // 日期时间选择器
            laydate.render({
                elem : '#triggerStartTimeStr',
                trigger: 'click',
                type: 'datetime',
                format: 'yyyy-MM-dd HH:mm:ss',
                theme: 'grid',
                btns: ['clear', 'now', 'confirm']
            });
            laydate.render({
                elem : '#triggerEndTimeStr',
                trigger: 'click',
                type: 'datetime',
                format: 'yyyy-MM-dd HH:mm:ss',
                theme: 'grid',
                btns: ['clear', 'now', 'confirm']
            });
        });
    </script>
  </body>
</html>
