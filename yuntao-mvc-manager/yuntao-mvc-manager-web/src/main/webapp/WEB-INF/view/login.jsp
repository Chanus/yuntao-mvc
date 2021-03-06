<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="${ctx }/lib/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${ctx }/lib/layui/css/modules/layer/default/layer.css" media="all"/>
    <link rel="stylesheet" href="${ctx }/css/login.css?v=0.1.7.3"/>
    <link rel="shortcut icon" href="${ctx }/images/favicon.ico" type="image/x-icon"/>
    <title>系统登录</title>
    <script type="text/javascript">
        var ctx = '${ctx }';
    </script>
  </head>
  <body class="beg-login-bg">
    <div class="beg-login-box">
      <header>
        <h1>系统登录</h1>
      </header>
      <div class="beg-login-main">
        <form class="layui-form" method="post">
          <div class="layui-form-item">
            <label class="beg-login-icon layui-icon layui-icon-username"> </label>
            <input type="text" name="loginname" lay-verify="letternumline" lay-data-length="3-16" lay-null-msg="请输入登录账号"
                   lay-error-msg="登录账号由3-16位字母数字下划线组成" placeholder="账号" class="layui-input" autocomplete="off">
          </div>
          <div class="layui-form-item">
            <label class="beg-login-icon layui-icon layui-icon-password"> </label>
            <input type="password" name="password" lay-verify="password" lay-data-length="6-16" lay-null-msg="请输入登录密码"
                   lay-error-msg="登录密码由6-16位字符组成" placeholder="密码" class="layui-input" autocomplete="off">
          </div>
          <c:if test="${isCheckVerifyCode eq '1' }">
            <div class="layui-form-item">
              <div class="beg-pull-left beg-login-veritycode">
                <label class="beg-login-icon layui-icon layui-icon-vercode"> </label>
                <input type="text" name="verifyCode" lay-verify="digit" lay-data-length="4" lay-null-msg="请输入验证码"
                       lay-error-msg="验证码不正确" placeholder="验证码" class="layui-input" autocomplete="off">
              </div>
              <div class="beg-pull-right">
                <img id="img" title="点击更换" src="verify-code"/>
              </div>
              <div class="beg-clear"></div>
            </div>
          </c:if>
          <c:if test="${isCheckGoogleAuthenticator eq '1' }">
            <div class="layui-form-item">
              <label class="beg-login-icon layui-icon layui-icon-auz"> </label>
              <input type="text" name="googleAuthenticatorCode" lay-verify="digit" lay-data-length="6"
                     lay-null-msg="请输入动态验证码" lay-error-msg="动态验证码为6位数字" placeholder="动态验证码" autocomplete="off"
                     class="layui-input">
            </div>
          </c:if>
          <div id="logindiv" class="layui-form-item">
            <button id="loginbtn" class="layui-btn layui-btn-primary" lay-submit lay-filter="login">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
          </div>
        </form>
      </div>
    </div>
    <%@ include file="public/footer.jsp" %>
    <script type="text/javascript" src="${ctx }/js/jsencrypt.min.js"></script>
    <script>
        layui.use(['layer', 'form'], function () {
            var layer = layui.layer, $ = layui.jquery, form = layui.form, verifyCodeUrl = $('#img').attr('src');

            form.on('submit(login)', function (data) {
                var loading = layer.load(0, {shade: [0.2, '#000']});//0.2透明度的白色背景

                $.post('rsa-public-key', function (rsakey) {
                    var encrypt = new JSEncrypt();
                    encrypt.setPublicKey(rsakey);
                    data.field.password = encrypt.encrypt(data.field.password);
                    data.field.rsaPublicKey = rsakey;
                    $.post('login', data.field, function (message) {
                        layer.close(loading);
                        if (message.code === 0) {
                            layer.msg(message.msg, {icon: 1, time: 1000}, function () {
                                location.href = 'index/index.do';
                            });
                        } else {
                            if (verifyCodeUrl)
                                $('#img').attr('src', verifyCodeUrl + '?timestamp=' + new Date().getTime());
                            layer.msg(message.msg, {icon: 2, anim: 6, time: 1000});
                        }
                    });
                });

                return false;
            });

            $('#img').on('click', function () {
                $(this).attr('src', verifyCodeUrl + '?timestamp=' + new Date().getTime());
            });
        });
    </script>
  </body>
</html>