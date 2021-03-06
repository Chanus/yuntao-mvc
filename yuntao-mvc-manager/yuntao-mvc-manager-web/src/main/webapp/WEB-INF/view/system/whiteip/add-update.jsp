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
        <input type="hidden" name="id" value="${whiteIp.id }" class="layui-input" autocomplete="off">
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label">白名单IP<span class="red">*</span></label>
          <div class="layui-input-block">
            <textarea name="whiteIp" required lay-verify="required" placeholder="请输入白名单IP，多个IP以英文字符逗号‘,’分隔" class="layui-textarea">${whiteIp.whiteIp }</textarea>
          </div>
        </div>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label">登录账号</label>
          <div class="layui-input-block">
            <textarea name="loginNo" placeholder="请输入登录账号，多个账号以英文字符逗号‘,’分隔，若为空则当前白名单IP所有用户都可以登录" class="layui-textarea">${whiteIp.loginNo }</textarea>
          </div>
        </div>
        <div class="layui-form-item" pane>
          <label class="layui-form-label">状态<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="radio" name="validStatus" lay-verify="mustradio" value="1" title="启用" <c:if test="${empty whiteIp.validStatus or whiteIp.validStatus == '1' }">checked</c:if>>
            <input type="radio" name="validStatus" value="0" title="停用" <c:if test="${whiteIp.validStatus == '0' }">checked</c:if>>
          </div>
        </div>
        <div class="layui-form-item" pane>
          <label class="layui-form-label">固定IP<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="radio" name="fixedStatus" lay-verify="mustradio" value="1" title="固定" <c:if test="${whiteIp.fixedStatus == '1' }">checked</c:if>>
            <input type="radio" name="fixedStatus" value="0" title="不固定" <c:if test="${empty whiteIp.fixedStatus or whiteIp.fixedStatus == '0' }">checked</c:if>>
          </div>
        </div>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label">备注</label>
          <div class="layui-input-block">
            <textarea name="remark" placeholder="请输入备注" class="layui-textarea">${whiteIp.remark }</textarea>
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
    <script type="text/javascript" src="${ctx }/js/system/white-ip.js?v=0.1.1.1"></script>
  </body>
</html>