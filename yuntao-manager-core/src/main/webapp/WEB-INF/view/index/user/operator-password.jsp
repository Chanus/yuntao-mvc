<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../public/public.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../../public/header.jsp" %>
  </head>
  <body>
    <div class="pd-20">
      <form class="layui-form layui-form-pane">
        <div class="layui-form-item">
          <label class="layui-form-label">当前密码<span class="red">*</span></label>
          <div class="layui-input-inline">
            <input type="password" name="oldPassword" required lay-verify="password" lay-data-length="6-16" lay-null-msg="请输入当前密码" lay-error-msg="密码为6-16位非空字符" placeholder="请输入当前密码" autocomplete="off" class="layui-input">
          </div>
          <div class="layui-form-mid layui-word-aux">密码为6-16位非空字符</div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">新密码<span class="red">*</span></label>
          <div class="layui-input-inline">
            <input type="password" name="newPassword" required lay-verify="password" lay-data-length="6-16" lay-null-msg="请输入新密码" lay-error-msg="密码为6-16位非空字符" placeholder="请输入新密码" autocomplete="off" class="layui-input">
          </div>
          <div class="layui-form-mid layui-word-aux">密码为6-16位非空字符</div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">确认密码<span class="red">*</span></label>
          <div class="layui-input-inline">
            <input type="password" name="newPassword2" required lay-verify="password" lay-recheck="newPassword" lay-data-length="6-16" lay-null-msg="请再次输入密码" lay-error-msg="密码为6-16位非空字符" placeholder="请再次输入密码" autocomplete="off" class="layui-input">
          </div>
          <div class="layui-form-mid layui-word-aux">再次输入密码</div>
        </div>
        <div class="layui-form-item text-l">
          <div class="layui-inline">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="updatePwd">确认修改</button>
          </div>
        </div>
      </form>
    </div>
    <%@ include file="../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx }/js/system/operator-info.js?v=0.0.1.1"></script>
  </body>
</html>