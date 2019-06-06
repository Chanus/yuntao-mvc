<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="shortcut icon" href="${ctx }/images/favicon.ico" type="image/x-icon"/>
    <script type="text/javascript" src="${ctx }/lib/layui/layui.js"></script>
    <title>重新登录</title>
  </head>
  <body>
    <script type="text/javascript">
        layui.use(['layer'], function () {
            var layer = layui.layer;
            // Session失效时重新登录
            layer.msg('请重新登录', {icon: 0, anim: 6, time: 2000}, function () {
                window.parent.location.href = 'login';
            });
        });
    </script>
  </body>
</html>