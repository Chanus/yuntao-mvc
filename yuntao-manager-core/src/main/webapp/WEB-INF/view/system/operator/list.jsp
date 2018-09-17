<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../public/public.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../../public/header.jsp" %>
    <link rel="stylesheet" href="${ctx }/lib/zTree/zTreeStyle/zTreeStyle.css" />
  </head>
  <body onload="loadTree();">
    <div class="pd-20">
      <!-- 角色列表树 -->
      <div class="page-tree">
        <ul id="roles" class="ztree"></ul>
      </div>
      <!-- 操作员列表 -->
      <div class="page-tree-list" id="role-list">
        <!-- 搜索条件 -->
        <div class="layui-form layui-form-pane">
          <div class="layui-inline">
            <div class="layui-form-item">
              <label class="layui-form-label">操作员账号</label>
              <div class="layui-input-inline">
                <input type="text" id="operatorNo" class="layui-input" placeholder="请输入操作员账号" autocomplete="off">
              </div>
              <div class="layui-input-inline" style="width: 85px;">
                <button id="search" class="layui-btn"><i class="layui-icon layui-icon-search"></i>搜索</button>
              </div>
              <div class="layui-input-inline operator-div">
                <c:if test="${powers['ADD'] }">
                  <button id="add" class="layui-btn layui-btn-normal" style="display: none;"><i class="layui-icon layui-icon-add-circle-fine"></i>添加</button>
                </c:if>
                <c:if test="${powers['DELETE'] }">
                  <button id="delete" class="layui-btn layui-btn-danger"><i class="layui-icon layui-icon-delete"></i>删除</button>
                </c:if>
              </div>
            </div>
          </div>
        </div>
        <!-- 数据展示 -->
        <table class="layui-hide" id="datas" lay-filter="datas"></table>
      </div>
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
		{{# if(${powers.containsKey('PASSWORD') }){ }}
			<a class="layui-btn layui-btn-xs layui-bg-blue" lay-event="password">修改密码</a>
		{{# } }}
		{{# if(${powers.containsKey('DELETE') }){ }}
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		{{# } }}
  	</script>
    
    <%@ include file="../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx }/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="${ctx }/lib/zTree/jquery.ztree.core.min.js"></script>
    <script type="text/javascript" src="${ctx }/js/system/operator.js?v=0.0.1.1"></script>
  </body>
</html>