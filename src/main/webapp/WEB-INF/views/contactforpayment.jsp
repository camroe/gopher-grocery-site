<!DOCTYPE html>
<%@ taglib
  uri="http://java.sun.com/jsp/jstl/core"
  prefix="c"
%>
<%@taglib
  prefix="fn"
  uri="http://java.sun.com/jsp/jstl/functions"
%>
<%@ page session="false"%>
<%@ page
  contentType="text/html;charset=UTF-8"
  language="java"
%>
<%@ taglib
  prefix="security"
  uri="http://www.springframework.org/security/tags"
%>
<%@ taglib
  uri="http://java.sun.com/jsp/jstl/fmt"
  prefix="fmt"
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="includes/header.jsp"%>
<link
  href="/resources/css/order.css"
  type="text/css"
  rel="stylesheet"
/>
<link
  href="/resources/css/orderreview.css"
  type="text/css"
  rel="stylesheet"
/>
</head>


<body>
  <input
    type="hidden"
    id="csrf-token"
    name="${_csrf.parameterName}"
    value="${_csrf.token}"
  />
  <!--  Set up the page -->
  <!--  Set up the page -->
  <c:set
    var="coe"
    value="${confirmedOrderSummaryResult.confirmedOrderSummary.confirmedOrdersEntity}"
  />
  <c:set
    var="cos"
    value="${confirmedOrderSummaryResult.confirmedOrderSummary}"
  />

  <div class="full_wrap full_wrap-back">
    <div
      id="header"
      class="wide_wrap"
    >
      <%--       <a href="/logout">Logout. Hello <security:authentication property="principal.username" />!</a> --%>
      <a href="/"> <img
        class="logo-marginn simple-tooltip"
        title="Click here to return to the home page"
        src="/resources/FONTS/GG-Logo-Color.png"
        alt="Gopher-Groceries | Order your Groceries Online Today - Great Ski Holidays"
        width="160px"
      >
      </a>
    </div>
    <!-- header -->
  </div>
  <!-- full_wrap  -->
  <%@ include file="includes/mainNavBar.jsp"%>
  <div
    class="ordersummary delivery-detail-label"
    style="left: 50px;"
  >
    <p style="font-size: 18px;">Thank you for your order!
      (Confirmation Number: ${coe.confirmationID})</p>
    <p>We will be reviewing you order for special instructions,
      contact information, etc. When we have completed this process we
      will contact you with any questions that we have as well as get
      your credit card information over the phone. We will then email
      your order, delivery instructions, etc.</p>
    <p>You will hear from us shortly!</p>
  </div>
  <div style="clear: both"></div>
  <!-- Look at Confirmed Order -->
  <div
    class="order-review-left"
    style="width: 400px;"
  >
    <section class="pressadjust">
      <button
        class="simple-tooltip"
        title="Click here to review and/or print your order information"
        onclick="window.location.href='/v1/confirmedorders';"
      >Review or Print your Order Information</button>
    </section>
  </div>
</body>