<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ include file="includes/header.jsp"%>


<body>
  <input type="hidden" id="csrf-token" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <div class="full_wrap full_wrap-back">
    <div id="header" class="wide_wrap">
      <%--       <a href="/logout">Logout. Hello <security:authentication property="principal.username" />!</a> --%>
      <a href="/"> <img class="logo-marginn" src="/resources/FONTS/GG-Logo-Color.png"
        alt="Gopher-Groceries | Order your Groceries Online Today - Great Ski Holidays"
        width="160px"
      >
      </a>
    </div>
    <!-- header -->
  </div>
  <!-- full_wrap  -->





  <div class="full_wrap ">
    <div class="wide_wrap"></div>
    
    
    
  </div>
</body>
</html>