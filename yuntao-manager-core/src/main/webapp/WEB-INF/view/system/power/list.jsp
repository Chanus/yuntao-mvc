<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../public/public.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../../public/header.jsp" %>
  </head>
  <body>
    <div class="pd-20">
      <!-- 搜索条件 -->
      <div class="layui-form layui-form-pane">
        <div class="layui-input-inline operator-div">
          <c:if test="${powers['ADD'] }">
            <button id="add" class="layui-btn layui-btn-normal"><i class="layui-icon layui-icon-add-circle-fine"></i>添加</button>
          </c:if>
          <c:if test="${powers['DELETE'] }">
            <button id="delete" class="layui-btn layui-btn-danger"><i class="layui-icon layui-icon-delete"></i>删除</button>
          </c:if>
        </div>
      </div>
      <!-- 数据展示 -->
      <table class="layui-hide" id="datas" lay-filter="datas"></table>
    </div>
    <!-- 自定义模板 -->
    <script type="text/html" id="validStatusTpl">
		{{# if(d.validStatus == "1"){ }}<span class="layui-badge layui-bg-custom-lightgreen">已启用</span>
		{{# }else if(d.validStatus == "0"){ }}<span class="layui-badge layui-bg-gray">已停用</span>
		{{# }else{ }}{{ d.validStatus }}{{# } }}
  	</script>
    <script type="text/html" id="toolbar">
		{{# if(${powers.containsKey('UPDATE') }){ }}
			<a class="layui-btn layui-btn-xs" lay-event="update">编辑</a>
		{{# } }}
		{{# if(${powers.containsKey('DELETE') }){ }}
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		{{# } }}
		{{# if(${powers.containsKey('PRIORITY') }){ }}
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="up" title="提升优先级"><i class="layui-icon layui-icon-up"></i></a>
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="down" title="降低优先级"><i class="layui-icon layui-icon-down"></i></a>
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="top" title="置顶优先级"><i class="layui-icon layui-icon-top"></i></a>
		{{# } }}
  	</script>
    
    <%@ include file="../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx }/js/system/power.js?v=0.0.1.1"></script>
  </body>
</html>