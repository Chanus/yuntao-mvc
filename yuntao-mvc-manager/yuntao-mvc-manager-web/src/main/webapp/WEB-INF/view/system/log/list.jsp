<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../public/public.jsp" %>
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
        <div class="layui-form-item">
          <%@ include file="../../public/datetime.jsp" %>
          <div class="layui-inline">
            <label class="layui-form-label">操作账号</label>
            <div class="layui-input-block">
              <input type="text" id="operateNo" placeholder="请输入操作账号" class="layui-input" autocomplete="off">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">模块代码</label>
            <div class="layui-input-block">
              <input type="text" id="operateModuleCode" placeholder="请输入操作模块代码" class="layui-input" autocomplete="off">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">操作内容</label>
            <div class="layui-input-block">
              <input type="text" id="operateContent" placeholder="请输入操作内容" class="layui-input" autocomplete="off">
            </div>
          </div>
          <div class="layui-inline">
            <button id="search" class="layui-btn"><i class="layui-icon layui-icon-search"></i>搜索</button>
          </div>
          <c:if test="${powers['DELETE'] }">
            <div class="layui-inline">
              <button id="clear" class="layui-btn layui-btn-danger"><i class="layui-icon layui-icon-delete"></i>清除日志</button>
            </div>
          </c:if>
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
      {{# if(${powers.containsKey('DELETE')}){ }}
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
      {{# } }}
    </script>
    
    <script type="text/javascript" src="${ctx }/js/system/log.js?v=0.1.9.1"></script>
  </body>
</html>