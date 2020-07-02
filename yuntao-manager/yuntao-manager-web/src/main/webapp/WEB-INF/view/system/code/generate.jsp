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
        <input type="hidden" name="tableSchema" value="${tableSchema }" class="layui-input" autocomplete="off">
        <div class="layui-form-item">
          <label class="layui-form-label">表名</label>
          <div class="layui-input-block">
            <input type="text" name="tableName" readonly value="${tableName }" class="layui-input layui-disabled" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">作者<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="author" required lay-verify="required" value="${author }" placeholder="请输入作者名称" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">版本号<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="since" required lay-verify="required" value="${since }" placeholder="请输入代码版本号" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item" pane>
          <label class="layui-form-label">项目结构<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="radio" name="multi" lay-verify="mustradio" value="1" title="多模块" <c:if test="${multi == '1' }">checked</c:if>>
            <input type="radio" name="multi" value="0" title="单模块" <c:if test="${empty multi or multi == '0' }">checked</c:if>>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">服务类包名<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="serverPackage" required lay-verify="required" value="${serverPackage }" placeholder="请输入服务类代码包名" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">控制器包名<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="controllerPackage" required lay-verify="required" value="${controllerPackage }" placeholder="请输入控制器代码包名" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">表前缀</label>
          <div class="layui-input-block">
            <input type="text" name="tablePrefix" value="${tablePrefix }" placeholder="请输入表前缀" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item" pane>
          <label class="layui-form-label">去除表前缀</label>
          <div class="layui-input-block">
            <input type="radio" name="autoRemovePrefix" lay-verify="mustradio" value="1" title="自动去除" <c:if test="${autoRemovePrefix == '1' }">checked</c:if>>
            <input type="radio" name="autoRemovePrefix" value="0" title="不自动去除" <c:if test="${empty autoRemovePrefix or autoRemovePrefix == '0' }">checked</c:if>>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">URL标识</label>
          <div class="layui-input-block">
            <input type="text" name="pathName" placeholder="请输入请求URL标识" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">模块ID</label>
          <div class="layui-input-block">
            <input type="text" name="moduleId" placeholder="请输入模块ID" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item text-c">
          <div class="layui-inline">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="generate">生成代码</button>
            <span class="layui-btn layui-btn-primary" id="cancel">取消</span>
          </div>
        </div>
      </form>
    </div>
    <%@ include file="../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx }/js/system/code-generation.js?v=0.2.3.1"></script>
  </body>
</html>