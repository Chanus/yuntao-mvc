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
            <label class="layui-form-label">表名称</label>
            <div class="layui-input-inline">
              <input type="text" id="tableName" class="layui-input" placeholder="请输入数据表名称" autocomplete="off">
            </div>
            <div class="layui-input-inline" style="width: 85px;">
              <button id="search" class="layui-btn"><i class="layui-icon layui-icon-search"></i>搜索</button>
            </div>
            <div class="layui-input-inline operator-div">
              <c:if test="${powers['DOWNLOAD'] }">
                <button id="generation" class="layui-btn layui-btn-normal">批量生成</button>
              </c:if>
            </div>
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
			<a class="layui-btn layui-btn-xs" lay-event="update">生成代码</a>
		{{# } }}
  	</script>
    
    <%@ include file="../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx }/js/system/code-generation.js?v=0.0.1.2"></script>
  </body>
</html>