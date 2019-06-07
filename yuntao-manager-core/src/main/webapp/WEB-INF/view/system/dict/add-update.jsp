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
        <input type="hidden" name="id" value="${dict.id }" autocomplete="off" class="layui-input">
        <c:choose>
          <c:when test="${'add' eq cmd}">
            <div class="layui-form-item">
              <label class="layui-form-label">字典集代码<span class="red">*</span></label>
              <div class="layui-input-block">
                <input type="text" name="dictCode" required lay-verify="required" value="${dict.dictCode }" placeholder="请输入字典集代码" autocomplete="off" class="layui-input">
              </div>
            </div>
          </c:when>
          <c:otherwise>
            <div class="layui-form-item">
              <label class="layui-form-label">字典集代码</label>
              <div class="layui-input-block">
                <input type="text" name="dictCode" readonly value="${dict.dictCode }" autocomplete="off" class="layui-input layui-disabled">
              </div>
            </div>
          </c:otherwise>
        </c:choose>
        <div class="layui-form-item">
          <label class="layui-form-label">字典集名称<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="dictName" required lay-verify="required" value="${dict.dictName }" placeholder="请输入字典集名称" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item" pane>
          <label class="layui-form-label">状态<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="radio" name="validStatus" lay-verify="mustradio" value="1" title="启用" <c:if test="${empty dict.validStatus or dict.validStatus == '1' }">checked</c:if>>
            <input type="radio" name="validStatus" value="0" title="停用" <c:if test="${dict.validStatus == '0' }">checked</c:if>>
          </div>
        </div>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label">备注</label>
          <div class="layui-input-block">
            <textarea name="remark" placeholder="请输入备注" class="layui-textarea">${dict.remark }</textarea>
          </div>
        </div>
        <div class="layui-form-item text-c">
          <div class="layui-inline">
            <c:choose>
              <c:when test="${ cmd eq 'add' }">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="add">添加</button>
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="addAgain">保存并添加下一个</button>
              </c:when>
              <c:when test="${ cmd eq 'update' }">
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
    <script type="text/javascript" src="${ctx}/js/system/dict.js?v=0.1.1.1"></script>
  </body>
</html>
