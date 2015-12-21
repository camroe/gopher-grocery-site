<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="includes/header.jsp"%>
<link href="/resources/css/order.css" type="text/css" rel="stylesheet" />
<link href="/resources/css/orderreview.css" type="text/css" rel="stylesheet" />

</head>
<body>
  <input type="hidden" id="csrf-token" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <!--  Set up the page -->

  <c:set var="cosr" value="${confirmedOrderSummaryResult}" />

  <c:set var="confirmationid" value="${confirmationid}" />

  <div class="full_wrap full_wrap-back">
    <div id="header" class="wide_wrap">
      <a href="/"> <img class="logo-marginn simple-tooltip"
        title="Click here to return to the home page" src="/resources/FONTS/GG-Logo-Color.png"
        alt="Gopher-Groceries | Order your Groceries Online Today - Great Ski Holidays"
        width="160px"
      >
      </a>
    </div>
  </div>


   <div
    class="ordersummary delivery-detail-label"
    style="left: 50px;"
  >
    <p style="font-size: 18px;">An Error Has Occured: Confirmation Number: ${confirmationid} Was not found.   </p>
    <p>Please contact support on our contacts page for help. </p>
    </div>
  <div style="clear: both; display: block;"></div>
</body>
</html>