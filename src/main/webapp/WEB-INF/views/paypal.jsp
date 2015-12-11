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
  <c:set
    var="oe"
    value="${orderSummaryResult.orderSummary.order.orderEntity}"
  />
  <c:set
    var="os"
    value="${orderSummaryResult.orderSummary}"
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
    <p style="font-size: 18px;">Thank you for your order!</p>
    <p>We will be reviewing you order for special instructions,
      contact information, etc. When we have completed this process we
      may contact you with any questions that we have. When it all looks
      good we will send you an invoice through Paypal with a copy of
      your order, delivery instructions, etc.</p>
    <p>Paypal is great because we never have (or want) access to
      your credit card number or any personal information, and you're
      secure in the knowledge that you have the security of Paypal on
      your side!</p>
    <p>You will hear from us within 1 business day!</p>
    <p
      class=simple-tooltip
      title="For more information on PayPal Invoicing, please follow this link"
    >
      <a
        href="https://www.paypal.com/uk/cgi-bin/webscr?cmd=p/sell/invoicing_intro-outside"
      > <img
        src="/resources/images/paypal.png"
        alt="For more information on PayPal invoicing"
      >
      </a>
    </p>

    <div style="clear: both"></div>
    <!-- Look at Confirmed Order -->
    <div class="order-review-left" style="width:400px;">
      <section class="pressadjust">
        <button
          class="simple-tooltip"
          title="Click here to review and/or print your order information"
          onclick="window.location.href='/v1/confirmedorders';"
        >Review or Print your Order Information</button>
      </section>
    </div>

  </div>
</body>