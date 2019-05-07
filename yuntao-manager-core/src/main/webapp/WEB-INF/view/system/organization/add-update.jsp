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
            <input type="hidden" name="orgParentId" value="${orgParentId }" autocomplete="off" class="layui-input">
          </c:when>
          <c:otherwise>
            <input type="hidden" name="orgId" value="${organization.orgId }" autocomplete="off" class="layui-input">
          </c:otherwise>
        </c:choose>
        <div class="layui-form-item">
          <label class="layui-form-label">代码<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="orgCode" required lay-verify="required" value="${organization.orgCode }" placeholder="请输入组织结构代码" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">名称<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="orgName" required lay-verify="required" value="${organization.orgName }" placeholder="请输入组织结构名称" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">简称</label>
          <div class="layui-input-block">
            <input type="text" name="orgShortName" value="${organization.orgShortName }" placeholder="请输入组织结构简称" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">全称</label>
          <div class="layui-input-block">
            <input type="text" name="orgLongName" value="${organization.orgLongName }" placeholder="请输入组织结构全称" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">地址</label>
          <div class="layui-input-block">
            <input type="text" name="orgLocation" value="${organization.orgLocation }" placeholder="请输入组织结构所在地址" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">联系方式</label>
          <div class="layui-input-block">
            <input type="text" name="orgPhone" value="${organization.orgPhone }" placeholder="请输入组织结构联系方式" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item" pane>
          <label class="layui-form-label">状态<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="radio" name="validStatus" lay-verify="mustradio" value="1" title="启用" <c:if test="${empty organization.validStatus or organization.validStatus == '1' }">checked</c:if>>
            <input type="radio" name="validStatus" value="0" title="停用" <c:if test="${organization.validStatus == '0' }">checked</c:if>>
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
    <script type="text/javascript" src="${ctx }/js/system/organization.js?v=0.0.8.3"></script>
  </body>
</html>