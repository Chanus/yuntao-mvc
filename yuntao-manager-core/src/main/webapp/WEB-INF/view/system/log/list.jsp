<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../../public/header.jsp" %>
    <%@ include file="../../public/footer.jsp" %>
  </head>
  <body>
    <div class="pd-5">
      <!-- 搜索条件 -->
      <div class="layui-form layui-form-pane">
        <div class="layui-inline">
          <div class="layui-form-item">
            <%@ include file="../../public/datetime.jsp" %>
            <label class="layui-form-label">操作账号</label>
            <div class="layui-input-inline">
              <input type="text" id="operateNo" class="layui-input" placeholder="请输入操作账号" autocomplete="off">
            </div>
            <label class="layui-form-label">模块代码</label>
            <div class="layui-input-inline">
              <input type="text" id="operateModuleId" class="layui-input" placeholder="请输入操作模块代码" autocomplete="off">
            </div>
            <label class="layui-form-label">操作内容</label>
            <div class="layui-input-inline">
              <input type="text" id="operateContent" class="layui-input" placeholder="请输入操作内容" autocomplete="off">
            </div>
            <div class="layui-input-inline" style="width: 85px;">
              <button id="search" class="layui-btn"><i class="layui-icon layui-icon-search"></i>搜索</button>
            </div>
          </div>
        </div>
      </div>
      
      <div class="layui-tab" lay-filter="log">
        <ul class="layui-tab-title">
          <li class="layui-this" lay-id="1">操作日志</li>
          <li lay-id="2">异常日志</li>
        </ul>
      </div>
      
      <!-- 数据展示 -->
      <table class="layui-hide" id="datas" lay-filter="datas"></table>
    </div>
    
    <script type="text/html" id="toolbar">
		<a class="layui-btn layui-btn-xs" lay-event="content">日志内容</a>
  	</script>
    
    <script type="text/javascript" src="${ctx }/js/system/log.js?v=0.0.8.3"></script>
  </body>
</html>