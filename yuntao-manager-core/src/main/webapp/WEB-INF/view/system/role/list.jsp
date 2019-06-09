<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../public/public.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../../public/header.jsp" %>
    <link rel="stylesheet" href="${ctx }/lib/zTree/zTreeStyle/zTreeStyle.css" />
  </head>
  <body onload="loadTree();">
    <div class="pd-5">
      <!-- 角色列表树 -->
      <div class="page-tree">
        <ul id="roles" class="ztree"></ul>
      </div>
      <!-- 角色列表 -->
      <div class="page-tree-list" id="role-list">
        <!-- 搜索条件 -->
        <div class="layui-form layui-form-pane">
          <div class="layui-form-item">
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
      <!-- 模块权限列表 -->
      <div class="page-tree-list" id="power-list" style="display: none;">
        <table class="layui-table" lay-even>
          <thead>
            <tr>
              <th width="150px" style="text-align: center;">模块</th>
              <th colspan="2" style="text-align: center;">【<label id="roleName"></label>】菜单/权限</th>
              <th width="60px" style="text-align: center;">全选<input type="checkbox" id="selectAll" style="width:16px;height:16px;"></th>
            </tr>
          </thead>
          <tbody id="roleModulePowerContent">
            <tr>
              <td colspan="4">无数据</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <!-- 自定义模板 -->
    <script type="text/html" id="validStatusTpl">
      {{# if(d.validStatus == "1"){ }}<span class="layui-badge layui-bg-custom-lightgreen">已启用</span>
      {{# }else if(d.validStatus == "0"){ }}<span class="layui-badge layui-bg-gray">已停用</span>
      {{# }else{ }}{{ d.validStatus }}{{# } }}
    </script>
    <script type="text/html" id="loginFlagTpl">
      {{# if(d.loginFlag == "1"){ }}<span class="layui-badge layui-bg-custom-lightgreen">可以登录</span>
      {{# }else if(d.loginFlag == "0"){ }}<span class="layui-badge layui-bg-gray">不能登录</span>
      {{# }else{ }}{{ d.loginFlag }}{{# } }}
    </script>
    <script type="text/html" id="hasOperatorTpl">
      {{# if(d.hasOperator == "1"){ }}<span class="layui-badge layui-bg-custom-lightgreen">可以</span>
      {{# }else if(d.hasOperator == "0"){ }}<span class="layui-badge layui-bg-gray">不可以</span>
      {{# }else{ }}{{ d.hasOperator }}{{# } }}
    </script>
    <script type="text/html" id="toolbar">
      {{# if(${powers.containsKey('UPDATE') }){ }}
      <a class="layui-btn layui-btn-xs" lay-event="update">编辑</a>
      {{# } }}
      {{# if(${powers.containsKey('DELETE') }){ }}
      <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
      {{# } }}
    </script>

    <!-- 角色模块权限列表 -->
    <script type="text/html" id="roleModulePowerTpl">
      {{# var moduleCount; }}
      {{# for(var i = 0; i < d.length; i++){
        moduleCount = d[i].children.length; }}
        {{# for(var j = 0; j < moduleCount; j++){ }}
        <tr>
          {{# if(j == 0){ }}
            <td rowspan="{{ moduleCount }}" align="center">{{ d[i].moduleName }}</td>
          {{# } }}
          <td width="180px" align="center">{{ d[i].children[j].moduleName }}</td>
          <td align="left">
            {{# for(var k = 0; k < d[i].children[j].modulePowers.length; k++){ }}
              <input type="checkbox" id="module_power_{{ d[i].children[j].moduleId }}_{{ d[i].children[j].modulePowers[k].mpId }}" value="{{ d[i].children[j].moduleId }}_{{ d[i].children[j].modulePowers[k].powerItem }}" {{# if(d[i].children[j].modulePowers[k].hasPower){ }}checked{{# } }} style="width:16px;height:16px;">
              {{ d[i].children[j].modulePowers[k].aliasName }}
            {{# } }}
          </td>
          <td align="center"><input type="checkbox" id="module_{{ d[i].children[j].moduleId }}" style="width:16px;height:16px;"></td>
        </tr>
        {{# } }}
      {{# } }}
      {{# if(d.currentRoleId != '0' && ${sessionScope.loginUser.roleId } != d.currentRoleId && ${powers.containsKey('CONFIG') }){ }}
      <tr>
        <td colspan="4" align="center">
          <button id="grant" class="layui-btn layui-btn-normal"><i class="layui-icon">授权</button>
        </td>
      </tr>
      {{# } }}
    </script>

    <%@ include file="../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx }/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="${ctx }/lib/zTree/jquery.ztree.core.min.js"></script>
    <script type="text/javascript" src="${ctx }/js/system/role.js?v=0.0.1.1"></script>
  </body>
</html>