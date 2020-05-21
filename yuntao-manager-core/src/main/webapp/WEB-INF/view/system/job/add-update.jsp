<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../public/public.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../../public/header.jsp" %>
  </head>
  <body>
    <div class="pd-5">
      <form class="layui-form layui-form-pane">
        <input type="hidden" name="id" value="${scheduleJob.id }" class="layui-input" autocomplete="off">
        <div class="layui-form-item">
          <label class="layui-form-label">任务名称<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="jobName" required lay-verify="required" value="${scheduleJob.jobName }" placeholder="请输入任务名称" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">任务组<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="jobGroup" required lay-verify="required" value="${scheduleJob.jobGroup }" placeholder="请输入任务组" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">执行类<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="jobClassName" required lay-verify="required" value="${scheduleJob.jobClassName }" placeholder="请输入定时任务执行类" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label">属性配置</label>
          <div class="layui-input-block">
            <textarea name="jobData" placeholder="请输入定时任务属性配置，数据为json格式" class="layui-textarea">${scheduleJob.jobData }</textarea>
          </div>
        </div>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label">备注</label>
          <div class="layui-input-block">
            <textarea name="remark" placeholder="请输入备注" class="layui-textarea">${scheduleJob.remark }</textarea>
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
    <%@ include file="../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx}/js/system/job.js?v=0.1.7.1"></script>
  </body>
</html>
