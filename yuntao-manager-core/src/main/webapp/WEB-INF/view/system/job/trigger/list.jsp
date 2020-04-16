<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../public/public.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../../../public/header.jsp" %>
  </head>
  <body>
    <div class="pd-5">
      <!-- 搜索条件 -->
      <div class="layui-form layui-form-pane">
        <input type="hidden" id="jobId" value="${jobId }" class="layui-input" autocomplete="off">
        <div class="layui-form-item">
          <c:if test="${ powers['ADD'] }">
            <div class="layui-inline">
              <button id="add" class="layui-btn layui-btn-normal"><i class="layui-icon layui-icon-add-circle-fine"></i>添加</button>
            </div>
          </c:if>
          <c:if test="${ powers['DELETE'] }">
            <div class="layui-inline">
              <button id="delete" class="layui-btn layui-btn-danger"><i class="layui-icon layui-icon-delete"></i>删除</button>
            </div>
          </c:if>
        </div>
      </div>
      <!-- 数据展示 -->
      <table class="layui-hide" id="datas" lay-filter="datas"></table>
    </div>
    <!-- 自定义模板 -->
    <script type="text/html" id="toolbar">
        {{# if(${powers.containsKey('UPDATE')}){ }}
          <a class="layui-btn layui-btn-xs" lay-event="update">编辑</a>
        {{# } }}
        {{# if(${powers.containsKey('DELETE')}){ }}
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        {{# } }}
    </script>

    <%@ include file="../../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx}/js/system/job-trigger.js?v=0.1.7.2"></script>
  </body>
</html>