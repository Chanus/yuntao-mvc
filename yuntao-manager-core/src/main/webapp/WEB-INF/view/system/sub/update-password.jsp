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
        <input type="hidden" name="subNo" value="${subNo }" autocomplete="off" class="layui-input">
        <div class="layui-form-item">
          <label class="layui-form-label">新密码<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="password" name="password" required lay-verify="password" lay-data-length="6-16" lay-null-msg="请输入新密码" lay-error-msg="密码为6-16位非空字符" placeholder="请输入新密码" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">确认密码<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="password" name="password2" required lay-verify="password" lay-recheck="password" lay-data-length="6-16" lay-null-msg="请再次输入密码" lay-error-msg="密码为6-16位非空字符" placeholder="请再次输入密码" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item text-c">
          <div class="layui-inline">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="updatePwd">确定</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            <span class="layui-btn layui-btn-primary" id="cancel">取消</span>
          </div>
        </div>
      </form>
    </div>
    <%@ include file="../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx }/js/system/sub.js?v=0.0.1.1"></script>
  </body>
</html>