<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../public/public.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../../public/header.jsp" %>
  </head>
  <body>
    <div class="pd-5">
      <form class="layui-form layui-form-pane">
        <c:choose>
          <c:when test="${cmd eq 'add' }">
            <input type="hidden" name="moduleParentId" value="${moduleId }" class="layui-input" autocomplete="off">
          </c:when>
          <c:otherwise>
            <input type="hidden" name="moduleId" value="${module.moduleId }" class="layui-input" autocomplete="off">
          </c:otherwise>
        </c:choose>
        <div class="layui-form-item">
          <label class="layui-form-label">模块名称<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="moduleName" required lay-verify="required" value="${module.moduleName }" placeholder="请输入模块名称" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">模块URL</label>
          <div class="layui-input-block">
            <input type="text" name="moduleUrl" value="${module.moduleUrl }" placeholder="请输入模块URL" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item" pane>
          <label class="layui-form-label">是否菜单<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="radio" name="moduleIsMenu" lay-verify="mustradio" value="1" title="是" <c:if test="${empty module.moduleIsMenu or module.moduleIsMenu == '1' }">checked</c:if>>
            <input type="radio" name="moduleIsMenu" value="0" title="否" <c:if test="${module.moduleIsMenu == '0' }">checked</c:if>>
          </div>
        </div>
        <div class="layui-form-item" pane>
          <label class="layui-form-label">设置权限<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="radio" name="moduleForPermission" lay-verify="mustradio" value="1" title="需要" <c:if test="${empty module.moduleForPermission or module.moduleForPermission == '1' }">checked</c:if>>
            <input type="radio" name="moduleForPermission" value="0" title="不需要" <c:if test="${module.moduleForPermission == '0' }">checked</c:if>>
          </div>
        </div>
        <div class="layui-form-item" pane>
          <label class="layui-form-label">超管专属<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="radio" name="moduleForAdmin" lay-verify="mustradio" value="1" title="是" <c:if test="${module.moduleForAdmin == '1' }">checked</c:if>>
            <input type="radio" name="moduleForAdmin" value="0" title="否" <c:if test="${empty module.moduleForAdmin or module.moduleForAdmin == '0' }">checked</c:if>>
          </div>
        </div>
        <div class="layui-form-item" pane>
          <label class="layui-form-label">菜单分组<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="radio" name="moduleBelong" lay-verify="mustradio" value="0" title="共有" <c:if test="${empty module.moduleBelong or module.moduleBelong == '0' }">checked</c:if>>
            <input type="radio" name="moduleBelong" value="1" title="操作员" <c:if test="${module.moduleBelong == '1' }">checked</c:if>>
            <input type="radio" name="moduleBelong" value="2" title="非操作员" <c:if test="${module.moduleBelong == '2' }">checked</c:if>>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">模块图标</label>
          <div class="layui-input-block">
            <input type="hidden" id="moduleIcon" name="moduleIcon" value="${module.moduleIcon }" class="layui-input" autocomplete="off">
            <input type="text" id="iconPicker" value="${module.moduleIcon }" lay-filter="iconPicker" class="hide">
          </div>
        </div>
        <div class="layui-form-item" pane>
          <label class="layui-form-label">状态<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="radio" name="validStatus" lay-verify="mustradio" value="1" title="启用" <c:if test="${empty module.validStatus or module.validStatus == '1' }">checked</c:if>>
            <input type="radio" name="validStatus" value="0" title="停用" <c:if test="${module.validStatus == '0' }">checked</c:if>>
          </div>
        </div>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label">备注</label>
          <div class="layui-input-block">
            <textarea name="remark" placeholder="请输入备注" class="layui-textarea">${module.remark }</textarea>
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
    <script type="text/javascript" src="${ctx }/js/system/module.js?v=0.0.1.9"></script>
  </body>
</html>