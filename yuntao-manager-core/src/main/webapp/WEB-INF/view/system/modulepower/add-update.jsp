<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../public/public.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../../public/header.jsp" %>
  </head>
  <body>
    <div class="pd-5">
      <form class="layui-form layui-form-pane">
        <input type="hidden" name="mpId" value="${modulePower.mpId }" class="layui-input" autocomplete="off">
        <div class="layui-form-item">
          <label class="layui-form-label">权限代码</label>
          <div class="layui-input-block">
            <input type="text" name="powerItem" required readonly lay-verify="required" value="${modulePower.powerItem }" placeholder="请输入权限代码" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">权限名称<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="aliasName" required lay-verify="required" value="${modulePower.aliasName }" placeholder="请输入权限名称" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item text-c">
          <div class="layui-inline">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="update">确定</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            <span class="layui-btn layui-btn-primary" id="cancel">取消</span>
          </div>
        </div>
      </form>
    </div>
    <%@ include file="../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx }/js/system/module-power.js?v=0.1.1.1"></script>
  </body>
</html>