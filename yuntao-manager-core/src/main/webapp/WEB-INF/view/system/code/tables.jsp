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
        <div class="layui-form-item">
          <div class="layui-inline">
            <label class="layui-form-label">表名称</label>
            <div class="layui-input-block">
              <input type="text" id="tableName" placeholder="请输入数据表名称" class="layui-input" autocomplete="off">
            </div>
          </div>
          <div class="layui-inline">
            <button id="search" class="layui-btn"><i class="layui-icon layui-icon-search"></i>搜索</button>
          </div>
        </div>
      </div>
      <!-- 数据展示 -->
      <table class="layui-hide" id="datas" lay-filter="datas"></table>
    </div>
    <!-- 自定义模板 -->
    <script type="text/html" id="toolbar">
      {{# if(d.tableType == 'BASE TABLE'){ }}
        <a class="layui-btn layui-btn-xs layui-bg-blue" lay-event="column">字典</a>
      {{# } }}
      {{# if(${powers.containsKey('DOWNLOAD') }){ }}
        <a class="layui-btn layui-btn-xs" lay-event="generate">代码生成</a>
      {{# } }}
    </script>
    
    <%@ include file="../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx }/js/system/code-generation.js?v=0.0.8.1"></script>
  </body>
</html>