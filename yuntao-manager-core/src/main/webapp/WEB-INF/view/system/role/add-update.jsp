<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../public/public.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../../public/header.jsp" %>
  </head>
  <body>
    <div class="pd-20">
      <form class="layui-form layui-form-pane">
        <input type="hidden" name="id" value="${role.id }" autocomplete="off" class="layui-input">
        <c:choose>
          <c:when test="${cmd eq 'add' }">
            <div class="layui-form-item">
              <label class="layui-form-label">上级角色<span class="red">*</span></label>
              <div class="layui-input-block">
                <select name="parentRoleId" lay-filter="parentRoleId" lay-verify="required" lay-search>
                  <option value="">请选择上级角色</option>
                  <c:forEach var="parentRole" items="${parentRoles }">
                    <option value="${parentRole.roleId }">${parentRole.roleName }</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </c:when>
          <c:otherwise>
            <div class="layui-form-item">
              <label class="layui-form-label">角色ID</label>
              <div class="layui-input-block">
                <input type="text" name="roleId" readonly value="${role.roleId }" placeholder="请输入角色ID" autocomplete="off" class="layui-input">
              </div>
            </div>
          </c:otherwise>
        </c:choose>
        <div class="layui-form-item">
          <label class="layui-form-label">角色代码</label>
          <div class="layui-input-block">
            <input type="text" name="roleCode" value="${role.roleCode }" placeholder="请输入角色代码" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">角色名称<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="roleName" required lay-verify="required" value="${role.roleName }" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item" pane>
          <label class="layui-form-label">登录系统<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="radio" name="loginFlag" value="1" title="允许" <c:if test="${empty role.loginFlag or role.loginFlag == '1' }">checked</c:if>>
            <input type="radio" name="loginFlag" value="0" title="不允许" <c:if test="${role.loginFlag == '0' }">checked</c:if>>
          </div>
        </div>
        <c:if test="${cmd eq 'add' }">
          <div class="layui-form-item" pane>
            <label class="layui-form-label">下属成员<span class="red">*</span></label>
            <div class="layui-input-block">
              <input type="radio" name="hasOperator" value="1" title="具有" checked>
              <input type="radio" name="hasOperator" value="0" title="不具有">
            </div>
          </div>
        </c:if>
        <div class="layui-form-item" pane>
          <label class="layui-form-label">状态<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="radio" name="validStatus" lay-verify="mustradio" value="1" title="启用" <c:if test="${empty role.validStatus or role.validStatus == '1' }">checked</c:if>>
            <input type="radio" name="validStatus" value="0" title="停用" <c:if test="${role.validStatus == '0' }">checked</c:if>>
          </div>
        </div>
        <c:if test="${cmd eq 'update' }">
          <div class="layui-form-item">
            <label class="layui-form-label">排序</label>
            <div class="layui-input-block">
              <input type="text" name="priority" lay-verify="pinteger" lay-ignore="ignore" value="${role.priority }" placeholder="请输入角色排序优先级" autocomplete="off" class="layui-input">
            </div>
          </div>
        </c:if>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label">备注</label>
          <div class="layui-input-block">
            <textarea name="remark" placeholder="请输入备注" class="layui-textarea">${role.remark }</textarea>
          </div>
        </div>
        <div class="layui-form-item text-c">
          <div class="layui-inline">
            <c:choose>
              <c:when test="${cmd eq 'add' }">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="add">添加</button>
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="addAgain">保存并添加下一个</button>
              </c:when>
              <c:when test="${cmd eq 'update' }">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="update">确定</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
              </c:when>
            </c:choose>
            <span class="layui-btn layui-btn-primary" id="cancel">取消</span>
          </div>
        </div>
      </form>
    </div>
    <%@ include file="../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx }/js/system/role.js?v=0.0.1.1"></script>
  </body>
</html>