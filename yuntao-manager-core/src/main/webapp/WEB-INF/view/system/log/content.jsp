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
        <div class="layui-form-item">
          <label class="layui-form-label">操作账号</label>
          <div class="layui-input-block">
            <input type="text" name="operateNo" readonly value="${log.operateNo }" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">请求地址</label>
          <div class="layui-input-block">
            <input type="text" name="operateUrl" readonly value="${log.operateUrl }" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">方法描述</label>
          <div class="layui-input-block">
            <input type="text" name="operateMethod" readonly value="${log.operateMethod }" autocomplete="off" class="layui-input">
          </div>
        </div>
        <c:if test="${log.operateType eq 'EXCEPTION' }">
          <div class="layui-form-item">
            <label class="layui-form-label">异常代码</label>
            <div class="layui-input-block">
              <input type="text" name="operateTypeDesc" readonly value="${log.operateTypeDesc }" autocomplete="off" class="layui-input">
            </div>
          </div>
        </c:if>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label">内容</label>
          <div class="layui-input-block">
            <textarea name="content" class="layui-textarea" style="height: 300px;" disabled="disabled">${log.content }</textarea>
          </div>
        </div>
      </form>
    </div>
    <%@ include file="../../public/footer.jsp" %>
  </body>
</html>