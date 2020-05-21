<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>无效证书</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${ctx }/lib/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${ctx }/lib/layuiadmin/style/admin.css" media="all">
    <link rel="shortcut icon" href="${ctx }/images/favicon.ico" type="image/x-icon"/>
  </head>
  <body>
    <div class="layui-fluid">
      <div class="layadmin-tips">
        <i class="layui-icon" face>&#xe664;</i>
        <div class="layui-text" style="font-size: 20px;">
          系统授权失败，请配置有效的授权证书
        </div>
      </div>
    </div>
  </body>
</html>