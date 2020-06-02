<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../public/public.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../../public/header.jsp" %>
  </head>
  <body>
    <div class="pd-5">
      <form class="layui-form layui-form-pane">
        <input type="hidden" name="id" value="${parameter.id }" class="layui-input" autocomplete="off">
        <c:choose>
          <c:when test="${cmd eq 'add' }">
            <div class="layui-form-item">
              <label class="layui-form-label">参数代码<span class="red">*</span></label>
              <div class="layui-input-block">
                <input type="text" name="paramCode" required lay-verify="required" value="${parameter.paramCode }" placeholder="请输入参数代码" class="layui-input" autocomplete="off">
              </div>
            </div>
          </c:when>
          <c:otherwise>
            <div class="layui-form-item">
              <label class="layui-form-label">参数代码</label>
              <div class="layui-input-block">
                <input type="text" name="paramCode" required readonly lay-verify="required" value="${parameter.paramCode }" class="layui-input" autocomplete="off">
              </div>
            </div>
          </c:otherwise>
        </c:choose>
        <div class="layui-form-item">
          <label class="layui-form-label">参数值<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="paramData" required lay-verify="required" value="${parameter.paramData }" placeholder="请输入参数值" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">可浏览角色</label>
          <div class="layui-input-block">
            <input type="text" name="modifiedRoleCode" value="${parameter.modifiedRoleCode }" placeholder="请输入可浏览该参数的角色，多个以“,”分隔" class="layui-input" autocomplete="off">
          </div>
        </div>
        <div class="layui-form-item" pane>
          <label class="layui-form-label">状态<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="radio" name="validStatus" lay-verify="mustradio" value="1" title="启用" <c:if test="${empty parameter.validStatus or parameter.validStatus == '1' }">checked</c:if>>
            <input type="radio" name="validStatus" value="0" title="停用" <c:if test="${parameter.validStatus == '0' }">checked</c:if>>
          </div>
        </div>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label">备注</label>
          <div class="layui-input-block">
            <textarea name="remark" placeholder="请输入备注" class="layui-textarea">${parameter.remark }</textarea>
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
    <script type="text/javascript" src="${ctx }/js/system/param.js?v=0.1.1.1"></script>
  </body>
</html>