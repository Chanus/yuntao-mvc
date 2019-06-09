<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="../public/header.jsp"%>
  </head>
  <body class="layui-layout-body">
    <input type="hidden" id="singleLocationLogin" value="${singleLocationLogin }" class="layui-input" autocomplete="off">
    <div id="LAY_app">
      <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
          <!-- 头部区域 -->
          <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item layadmin-flexible" lay-unselect>
              <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩"> <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i></a>
            </li>
            <!-- <li class="layui-nav-item layui-hide-xs" lay-unselect>
              <a href="javascript:;" target="_blank" title="前台"> <i class="layui-icon layui-icon-website"></i></a>
            </li> -->
            <li class="layui-nav-item" lay-unselect>
              <a href="javascript:;" layadmin-event="refresh" title="刷新"> <i class="layui-icon layui-icon-refresh-3"></i></a>
            </li>
            <li class="layui-tip-item">
              <a href="javascript:;"><span id="currentTime"></span>&nbsp;&nbsp;(GMT+8)</a>
            </li>
          </ul>
          
          <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
            <li class="layui-nav-item" lay-unselect>
              <a lay-href="javascript:;" layadmin-event="message" lay-text="消息中心"> 
                <i class="layui-icon layui-icon-notice"></i>
                <!-- 如果有新消息，则显示小圆点 -->
                <span class="layui-badge-dot"></span>
              </a>
            </li>
            <li class="layui-nav-item layui-hide-xs" lay-unselect>
              <a href="javascript:;" layadmin-event="theme"> <i class="layui-icon layui-icon-theme"></i></a>
            </li>
            <li class="layui-nav-item layui-hide-xs" lay-unselect>
              <a href="javascript:;" layadmin-event="note"> <i class="layui-icon layui-icon-note"></i></a>
            </li>
            <li class="layui-nav-item layui-hide-xs" lay-unselect>
              <a href="javascript:;" layadmin-event="fullscreen"> <i class="layui-icon layui-icon-screen-full"></i></a>
            </li>

            <li class="layui-nav-item" lay-unselect>
              <a href="javascript:;"> 
                <c:choose>
                  <c:when test="${empty sessionScope.loginUser.headImage }">
                    <img src="${ctx }/images/head_image.jpg" class="layui-nav-img" width="35" height="35">
                  </c:when>
                  <c:otherwise>
                    <img src="${parentCtx }${sessionScope.loginUser.headImage }" class="layui-nav-img" width="35" height="35">
                  </c:otherwise>
                </c:choose>
                <cite>${sessionScope.loginUser.loginNo }</cite>
              </a>
              <dl class="layui-nav-child">
                <c:if test="${sessionScope.loginUser.userType eq '0' or sessionScope.loginUser.userType eq '1' }">
                  <dd style="text-align: center;">
                    <a lay-href="${ctx }/index/user/operator-info.do">个人资料</a>
                  </dd>
                  <dd style="text-align: center;">
                    <a lay-href="${ctx }/index/user/update-own-password.do">修改密码</a>
                  </dd>
                </c:if>
                <dd style="text-align: center;">
                  <a href="javascript:;" id="reloadAuthority">刷新权限</a>
                </dd>
                <hr>
                <dd layadmin-event="logout" style="text-align: center;">
                  <a>退出</a>
                </dd>
              </dl>
            </li>
  
            <li class="layui-nav-item layui-hide-xs" lay-unselect>
              <a href="javascript:;" layadmin-event="about"><i class="layui-icon layui-icon-more-vertical"></i></a>
            </li>
            <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
              <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
            </li>
          </ul>
        </div>
  
        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu">
          <div class="layui-side-scroll">
            <div class="layui-logo">
              <span><a href="${ctx }/index/index.do">${system_name } v${system_version }</a></span>
            </div>
  
            <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
              <!-- 一级菜单 -->
              <c:forEach var="menu" items="${loginUser.menus }">
                <c:if test="${menu.moduleIsMenu eq '1' }">
                  <li data-name="${menu.moduleId }" class="layui-nav-item">
                    <a href="javascript:;" lay-tips="${menu.moduleName }" lay-direction="2"> <i class="${menu.moduleIcon }"></i> <cite>${menu.moduleName }</cite></a>
                    <dl class="layui-nav-child">
                      <!-- 二级菜单 -->
                      <c:forEach var="menu2" items="${menu.children }">
                        <c:if test="${menu2.moduleIsMenu eq '1' }">
                          <dd data-name="${menu2.moduleId }">
                            <a lay-href="${ctx }/${menu2.moduleUrl }"> <i class="${menu2.moduleIcon }"></i> ${menu2.moduleName }</a>
                          </dd>
                        </c:if>
                      </c:forEach>
                    </dl>
                  </li>
                </c:if>
              </c:forEach>
            </ul>
          </div>
        </div>
  
        <!-- 页面标签 -->
        <div class="layadmin-pagetabs" id="LAY_app_tabs">
          <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
          <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
          <div class="layui-icon layadmin-tabs-control layui-icon-down">
            <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
              <li class="layui-nav-item" lay-unselect>
                <a href="javascript:;"></a>
                <dl class="layui-nav-child layui-anim-fadein">
                  <dd layadmin-event="closeThisTabs">
                    <a href="javascript:;">关闭当前标签页</a>
                  </dd>
                  <dd layadmin-event="closeOtherTabs">
                    <a href="javascript:;">关闭其它标签页</a>
                  </dd>
                  <dd layadmin-event="closeAllTabs">
                    <a href="javascript:;">关闭全部标签页</a>
                  </dd>
                </dl></li>
            </ul>
          </div>
          <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
            <ul class="layui-tab-title" id="LAY_app_tabsheader">
              <li lay-id="0" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
            </ul>
          </div>
        </div>
  
        <!-- 主体内容 -->
        <div class="layui-body" id="LAY_app_body">
          <div class="layadmin-tabsbody-item layui-show">
            <iframe src="" frameborder="0" class="layadmin-iframe"></iframe>
          </div>
        </div>
  
        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
      </div>
    </div>
  
    <%@ include file="../public/footer.jsp" %>
    <script type="text/javascript" src="${ctx }/js/system/index.js?v=0.0.7.1"></script>
  </body>
</html>