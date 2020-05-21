<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../public/public.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../../public/header.jsp" %>
  </head>
  <body>
    <div class="pd-5">
      <table class="layui-table" lay-even lay-size="sm">
        <thead>
          <tr class="text-c">
            <th>字典名称</th>
            <th>字典类型</th>
            <th>能否为空</th>
            <th>字符集</th>
            <th>字符序</th>
            <th>默认值</th>
            <th>备注</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="column" items="${columns }">
            <tr class="text-c">
              <td>${column.columnName }</td>
              <td>${column.columnType }</td>
              <td>${column.nullable }</td>
              <td>${column.characterSetName }</td>
              <td>${column.collationName }</td>
              <td>
                <c:choose>
                  <c:when test="${column.columnDefault == null }">(NULL)</c:when>
                  <c:when test="${column.columnDefault == '' }">EMPTY STRING</c:when>
                  <c:otherwise>${column.columnDefault }</c:otherwise>
                </c:choose>
              </td>
              <td>${column.columnComment }</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
    <%@ include file="../../public/footer.jsp" %>
  </body>
</html>