<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../public/public.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../../public/header.jsp" %>
  </head>
  <body>
    <div class="pd-5">
      <form class="layui-form layui-form-pane layui-row">
        <div class="layui-col-md3 layui-col-xs12 user_right">
          <div class="layui-upload-list">
            <img class="layui-upload-img layui-circle userFaceBtn userAvatar" id="userFace">
          </div>
          <button type="button" class="layui-btn layui-btn-primary userFaceBtn">
            <i class="layui-icon">&#xe67c;</i> 更换头像
          </button>
          <p>请选择小于20KB的图片作为头像</p>
        </div>
        <div class="layui-col-md6 layui-col-xs12">
          <input type="hidden" name="id" value="${operator.id }" class="layui-input" autocomplete="off">
          <div class="layui-form-item">
            <label class="layui-form-label">账号</label>
            <div class="layui-input-block">
              <input type="text" value="${operator.operatorNo }" readonly="readonly" disabled="disabled" class="layui-input layui-disabled" autocomplete="off">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label">名称<span class="red">*</span></label>
            <div class="layui-input-block">
              <input type="text" name="operatorName" required lay-verify="required" value="${operator.operatorName }" class="layui-input" autocomplete="off">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label">角色</label>
            <div class="layui-input-block">
              <input type="text" value="${operator.roleName }" readonly="readonly" disabled="disabled" class="layui-input layui-disabled" autocomplete="off">
            </div>
          </div>
          <c:if test="${operator.operatorRoleCode eq '1' }">
            <div class="layui-form-item">
              <label class="layui-form-label">主账号</label>
              <div class="layui-input-block">
                <input type="text" value="${operator.masterNo }" readonly="readonly" disabled="disabled" class="layui-input layui-disabled" autocomplete="off">
              </div>
            </div>
          </c:if>
          <div class="layui-form-item">
            <label class="layui-form-label">邮箱账号</label>
            <div class="layui-input-block">
              <input type="text" name="email" lay-verify="email" lay-ignore="ignore" value="${operator.email }" class="layui-input" autocomplete="off">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label">电话号码</label>
            <div class="layui-input-block">
              <input type="text" name="tel" lay-verify="phone" lay-ignore="ignore" value="${operator.tel }" class="layui-input" autocomplete="off">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label">创建时间</label>
            <div class="layui-input-block">
              <input type="text" id="gmtCreate" readonly="readonly" disabled="disabled" class="layui-input layui-disabled" autocomplete="off">
            </div>
          </div>
          <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
              <textarea name="remark" class="layui-textarea">${operator.remark }</textarea>
            </div>
          </div>
          <div class="layui-form-item">
            <div class="layui-input-block">
              <button class="layui-btn" lay-submit lay-filter="update">立即提交</button>
              <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
          </div>
        </div>
      </form>
    </div>
    
    <%@ include file="../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx }/js/system/operator-info.js?v=0.0.1.1"></script>
    <script type="text/javascript">
        var gmtCreate = '${operator.gmtCreate }';
        var headImage = '${operator.headImage }' ? ('${parentCtx }' + '${operator.headImage }') : '${ctx }/images/head_image.jpg';

        layui.config({
            base: '../../js/',
            version: '0.0.1.1'
        }).use(['jquery', 'laydate'], function () {
            var $ = layui.jquery, laydate = layui.laydate;

            // 头像
            $('#userFace').attr('src', headImage);

            gmtCreate = gmtCreate ? datetime2Str(dateCST2GMT(gmtCreate)) : '';
            // 日期时间选择器
            laydate.render({
                elem: '#gmtCreate',
                type: 'datetime',
                format: 'yyyy-MM-dd HH:mm:ss',
                value: gmtCreate,
                theme: 'grid',
                btns: ['now', 'confirm']
            });
        });
    </script>
  </body>
</html>