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
            <label class="layui-form-label">模块</label>
            <div class="layui-input-inline">
              <select name="moduleParentId" id="moduleParentId" lay-filter="moduleParentId" lay-search>
                <option value="">选择一级模块</option>
                <c:forEach var="module1" items="${modules1 }">
                  <option value="${module1.moduleId }">${module1.moduleName }</option>
                </c:forEach>
              </select>
            </div>
            <div class="layui-input-inline">
              <select name="moduleId" id="moduleId" lay-filter="moduleId" lay-search>
                <option value="">选择二级模块</option>
              </select>
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">权限项</label>
            <div class="layui-input-block">
              <select name="powerItem" lay-filter="powerItem" lay-search>
                <option value="">选择权限项</option>
                <c:forEach var="powerItem" items="${powerItems }">
                  <option value="${powerItem.powerItem }">${powerItem.powerName }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="layui-inline">
            <button id="search" class="layui-btn"><i class="layui-icon layui-icon-search"></i>搜索</button>
          </div>
          <c:if test="${powers['ADD'] }">
            <div class="layui-inline">
              <button id="add" class="layui-btn layui-btn-normal"><i class="layui-icon layui-icon-add-circle-fine"></i>添加</button>
            </div>
          </c:if>
          <c:if test="${powers['DELETE'] }">
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
      {{# if(${powers.containsKey('UPDATE') }){ }}
        <a class="layui-btn layui-btn-xs" lay-event="update">编辑</a>
      {{# } }}
      {{# if(${powers.containsKey('CONFIG') }){ }}
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="configure">配置权限项URL</a>
      {{# } }}
      {{# if(${powers.containsKey('DELETE') }){ }}
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
      {{# } }}
    </script>
    
    <%@ include file="../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx }/js/system/module-power.js?v=0.1.1.1"></script>
  </body>
</html>