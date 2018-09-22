<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../public/public.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../../public/header.jsp" %>
  </head>
  <body>
    <div class="pd-5">
      <!-- 搜索条件 -->
      <div class="layui-form layui-form-pane">
        <div class="layui-inline">
          <div class="layui-form-item">
            <input type="hidden" id="mpId" value="${mpId }" autocomplete="off" class="layui-input">
            <label class="layui-form-label">类名</label>
            <div class="layui-input-inline" style="width: 500px;">
              <select name="className" id="className" lay-filter="className" lay-search>
                <option value="">选择类名</option>
                <c:forEach var="clazz" items="${clazzs }">
                  <option value="${clazz }">${clazz }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label">URL</label>
            <div class="layui-input-inline" style="width: 500px;">
              <select name="url" id="url" lay-filter="url" lay-search>
                <option value="">选择URL</option>
              </select>
            </div>
          </div>
          <div class="layui-input-inline" style="width: 100%; text-align: center;">
            <button id="add" class="layui-btn layui-btn-normal"><i class="layui-icon layui-icon-add-circle-fine"></i>添加</button>
            <button id="delete" class="layui-btn layui-btn-danger"><i class="layui-icon layui-icon-delete"></i>删除</button>
          </div>
        </div>
      </div>
      <!-- 数据展示 -->
      <table class="layui-hide" id="datas" lay-filter="datas"></table>
    </div>
    <!-- 自定义模板 -->
    <script type="text/html" id="toolbar">
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
  	</script>
    
    <%@ include file="../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx }/js/system/module-power-method.js?v=0.1.1.1"></script>
  </body>
</html>