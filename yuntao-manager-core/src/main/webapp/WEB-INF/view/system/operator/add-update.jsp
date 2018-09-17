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
        <input type="hidden" name="id" value="${operator.id }" autocomplete="off" class="layui-input">
        <div class="layui-form-item">
          <label class="layui-form-label">账号<span class="red">*</span></label>
          <div class="layui-input-block">
            <c:choose>
              <c:when test="${cmd eq 'add' }">
                <input type="text" name="operatorNo" required lay-verify="letternumline" lay-data-length="3-16" lay-null-msg="请输入操作员账号" lay-error-msg="操作员账号由3-16位字母数字下划线组成" placeholder="请输入操作员账号" autocomplete="off" class="layui-input">
                <input type="hidden" name="operatorRoleId" value="${operatorRoleId }" autocomplete="off" class="layui-input">
              </c:when>
              <c:otherwise>
                <input type="text" name="operatorNo" required readonly lay-verify="required" value="${operator.operatorNo }" placeholder="请输入操作员账号" autocomplete="off" class="layui-input">
              </c:otherwise>
            </c:choose>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">名称<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="text" name="operatorName" required lay-verify="required" value="${operator.operatorName }" placeholder="请输入操作员名称" autocomplete="off" class="layui-input">
          </div>
        </div>
        <c:if test="${cmd eq 'add' }">
          <div class="layui-form-item">
            <label class="layui-form-label">密码<span class="red">*</span></label>
            <div class="layui-input-block">
              <input type="password" name="operatorPassword" required lay-verify="password" lay-data-length="6-16" lay-null-msg="请输入密码" lay-error-msg="密码为6-16位非空字符" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label">确认密码<span class="red">*</span></label>
            <div class="layui-input-block">
              <input type="password" name="operatorPassword2" required lay-verify="password" lay-recheck="operatorPassword" lay-data-length="6-16" lay-null-msg="请再次输入密码" lay-error-msg="密码为6-16位非空字符" placeholder="请再次输入密码" autocomplete="off" class="layui-input">
            </div>
          </div>
        </c:if>
        <div class="layui-form-item">
          <label class="layui-form-label">邮箱账号</label>
          <div class="layui-input-block">
            <input type="text" name="email" lay-verify="email" lay-ignore="ignore" value="${operator.email }" placeholder="请输入操作员邮箱账号" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">电话号码</label>
          <div class="layui-input-block">
            <input type="text" name="tel" value="${operator.tel }" placeholder="请输入操作员电话号码" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item" pane>
          <label class="layui-form-label">状态<span class="red">*</span></label>
          <div class="layui-input-block">
            <input type="radio" name="validStatus" lay-verify="mustradio" value="1" title="启用" <c:if test="${empty operator.validStatus or operator.validStatus == '1' }">checked</c:if>>
            <input type="radio" name="validStatus" value="0" title="停用" <c:if test="${operator.validStatus == '0' }">checked</c:if>>
          </div>
        </div>
        <div class="layui-form-item layui-form-text">
          <label class="layui-form-label">备注</label>
          <div class="layui-input-block">
            <textarea name="remark" placeholder="请输入备注" class="layui-textarea">${operator.remark }</textarea>
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
    <script type="text/javascript" src="${ctx }/js/system/operator.js?v=0.0.1.1"></script>
  </body>
</html>