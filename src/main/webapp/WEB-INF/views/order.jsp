<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="security"
  uri="http://www.springframework.org/security/tags"%>

<%@ include file="includes/header.jsp"%>


<body>

  <div class="full_wrap full_wrap-back">
    <div id="header" class="wide_wrap">
      <%--       <a href="/logout">Logout. Hello <security:authentication property="principal.username" />!</a> --%>
      <a href="NotYetImplemented.html"> <img class="logo-marginn"
        src="/resources/FONTS/GG-Logo-Color.png"
        alt="Gopher-Groceries | Order your Groceries Online Today - Great Ski Holidays"
        width="160px">
      </a>
    </div>
    <!-- header -->
  </div>
  <!-- full_wrap  -->
  ORDERPAGE
</body>
</html>