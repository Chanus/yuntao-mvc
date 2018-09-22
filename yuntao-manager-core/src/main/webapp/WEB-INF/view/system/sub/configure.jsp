<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../public/public.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../../public/header.jsp" %>
  </head>
  <body>
    <div class="pd-5">
      <!-- 搜索条件 -->
      <div class="layui-form layui-form-pane">
        <div class="layui-input-inline operator-div">
          <a href="${ctx }/system/sub/main.do" class="layui-btn layui-btn-small"><i class="layui-icon layui-icon-return"></i>返回</a>
        </div>
      </div>
      <input type="hidden" id="subNo" value="${subNo }" autocomplete="off" class="layui-input">
      <!-- 子账号权限列表 -->
      <table class="layui-table" lay-even>
        <thead>
          <tr>
            <th width="150px" style="text-align: center;">模块</th>
            <th colspan="2" style="text-align: center;">【${subNo }】菜单/权限</th>
            <th width="60px" style="text-align: center;">全选<input type="checkbox" id="selectAll" style="width:16px;height:16px;"></th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${menus == null || fn:length(menus) == 0 }">
              <tr>
                <td colspan="4">无数据</td>
              </tr>
            </c:when>
            <c:otherwise>
              <c:forEach var="item1" items="${menus }" varStatus="status1">
                <c:set var="moduleCount" value="${fn:length(item1.children) }"/>
                <c:forEach var="item2" items="${item1.children }" varStatus="status2">
                  <tr>
                    <c:if test="${status2.index == 0 }">
                      <td rowspan="${moduleCount }" align="center">${item1.moduleName }</td>
                    </c:if>
                    <td width="180px" align="center">${item2.moduleName }</td>
                    <td align="left">
                      <c:forEach var="item3" items="${item2.modulePowers }" varStatus="status3">
                        <input type="checkbox" id="module_power_${item2.moduleId }_${item3.mpId }" value="${item2.moduleId }_${item3.powerItem }" <c:if test="${!empty item3.hasPower }">checked</c:if> style="width:16px;height:16px;">${item3.aliasName }
                      </c:forEach>
                    </td>
                    <td align="center"><input type="checkbox" id="module_${item2.moduleId }" style="width:16px;height:16px;"></td>
                  </tr>
                </c:forEach>
              </c:forEach>
              <tr>
                <td colspan="4" align="center"><button id="grant" class="layui-btn layui-btn-normal"><i class="layui-icon">授权</button></td>
              </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>
    
    <%@ include file="../../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx }/js/system/sub.js?v=0.0.1.1"></script>
  </body>
</html>