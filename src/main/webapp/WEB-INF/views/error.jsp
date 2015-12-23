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
    var="datetime"
    value="${datetime}"
  />

  <c:set
    var="exception"
    value="${exception}"
  />

  <c:set
    var="url"
    value="${url}"
  />

  <c:set
    var="exceptionName"
    value="${exceptionName}"
  />
  <c:set
    var="httpstatus"
    value="${httpstatus}"
  />

  <div class="full_wrap full_wrap-back">
    <div
      id="header"
      class="wide_wrap"
    >
      <a href="/"> <img
        class="logo-marginn simple-tooltip"
        title="Click here to return to the home page"
        src="/resources/FONTS/GG-Logo-Color.png"
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
    <p style="font-size: 18px;">We're Sorry, an Error Has Occurred:
      We have logged the error and will investigate.</p>
    <p>Please contact support on our contacts page for more help,
      else you can simply return to shopping.</p>
  </div>
  <div class="order-review-left">
    <section class="pressadjust">
      <button
        class="simple-tooltip"
        title="Click here to return to the home page"
        onclick="window.location.href='/';"
      >Return to Shopping</button>
    </section>
  </div>
  <div style="clear: both; display: block;"></div>


  <div
    class="ordersummary delivery-detail-label"
    style="left: 50px;"
  >

    <p style="font-size: 18px;">Error Details:</p>
    <p>Server Date/Time: ${datetime}</p>
    <p>The error occurred while calling URL: ${url}</p>
    <p>The HttpStatus is : ${httpstatus}</p>
    <p>The exception raised (if any) was : ${exceptionName}</p>

  </div>

  <div style="clear: both; display: block;"></div>
</body>
</html>