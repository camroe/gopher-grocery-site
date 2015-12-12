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


  <div class="ordersummary ">
    <br>
    <!-- LEFT HAND SIDE -->
    <div class="order-review-left">
      <span
        class="deliver-to-header simple-tooltip"
        title="Where to deliver the groceries.This will almost always be the condominium/cabin where you will be staying for your holidays.This is NOT your homeaddress."
      >Delivery Information</span>
      <div style="clear: both;"></div>
      <hr>

      <label
        class="delivery-detail-label"
        for="name"
      >Name: </label>
      <div
        class="detail"
        id="name"
      >${coe.firstName}${coe.lastName}</div>
      <label
        class="delivery-detail-label"
        for="location"
      >Location: </label>
      <div
        class="detail"
        id="location"
      >${coe.location}</div>

      <label
        class="delivery-detail-label"
        for="unit"
      >Unit: </label>
      <div
        class="detail"
        id="unit"
      >${coe.unit}</div>

      <label
        class="delivery-detail-label"
        for="phone"
      >Phone: </label>
      <div
        class="detail"
        id="phone"
      >${coe.phone}</div>

      <label
        class="delivery-detail-label"
        for="email"
      >EMail: </label>
      <div
        class="detail"
        id="email"
      >${coe.email}</div>

      <label
        class="delivery-detail-label"
        for="checkindate"
      >Check-In Date:</label>
      <div
        class="detail"
        id="checkindate"
      >${coe.checkinDate}</div>

      <label
        class="delivery-detail-label"
        for="username"
      >UserName: </label>
      <div
        class="detail simple-tooltip"
        id="username"
        title="This is the username that you are logged in as. If you have not logged in this will be 'anonymousUser'"
      >${coe.username}</div>
      <div style="clear: both;"></div>
      <hr>
    </div>

    <!-- RIGHT HAND SIDE  -->
    <div class="order-review-right">
      <span
        class="deliver-to-header simple-tooltip"
        title="Order Summary."
      >Order Summary</span>
      <div style="clear: both;"></div>
      <hr>

      <label
        class="delivery-detail-label"
        for="confirmationid"
      >Order ID: </label>
      <div
        class="detail"
        id="confirmationid"
      >${coe.confirmationId}</div>

      <label
        class="delivery-detail-label"
        for="id"
      >Order ID: </label>
      <div
        class="detail"
        id="id"
      >${coe.id}</div>


      <label
        class="delivery-detail-label"
        for="totalitems"
      >Number of Items: </label>
      <div
        class="detail"
        id="totalitems"
      >${cos.numberOfItems}</div>

      <label
        class="delivery-detail-label"
        for="total"
      >Total: </label>
      <div
        class="detail"
        id="total"
      >$${cos.total}</div>
      <div style="clear: both;"></div>
      <hr>
    </div>
    <!--  End Right -->
    <!-- COMMENT -->
    <div class="comment">
      <label
        class="delivery-detail-label"
        for="comment"
        style="font: bolder;"
      >Comment:</label>
      <div
        class="comment-detail"
        id="comment"
      >${coe.comment}</div>
    </div>
    <!-- End Comment -->
    <div class="orders">
      <table>
        <tr>
          <th></th>
          <th>Item</th>
          <th>Description</th>
          <th>Quantity</th>
          <th>Price</th>
          <th>Total</th>
          <th class="hiddenTableColumn">OrderLineEntryID</th>
        </tr>

        <c:set
          var="itemCount"
          value="1"
          scope="page"
        />
        <c:set
          var="salesTotal"
          value="0"
          scope="page"
        />
        <c:forEach
          items="${confirmedOrderSummaryResult.confirmedOrderSummary.confirmedOrdersEntity.orderLines}"
          var="orderLine"
        >
          <c:set
            var="count"
            value="${count+1}"
            scope="page"
          />
          <c:set
            var="salesTotal"
            value="${salesTotal + (orderLine.price * orderLine.quantity)}"
            scope="page"
          />
          <tr>
            <td>${count }.</td>
            <td>${orderLine.product.name}</td>
            <td>${orderLine.product.description}</td>
            <td>${orderLine.quantity}</td>
            <td class="tdPrice">$<fmt:formatNumber
                value="${orderLine.price}"
                minFractionDigits="2"
              /></td>
            <td class="tdTotal">$<fmt:formatNumber
                value="${orderLine.price * orderLine.quantity}"
                minFractionDigits="2"
              />
            </td>
            <td class="hiddenTableColumn tdOrderLineID">"${orderLine.id}"</td>
          </tr>
        </c:forEach>
        <tr>
          <td
            id="totalLabel"
            colspan="5"
          >Total</td>
          <td id="grandTotal">$<fmt:formatNumber
              value="${salesTotal}"
              minFractionDigits="2"
            ></fmt:formatNumber></td>
      </table>
    </div>
    <div style="clear: both; display: block;"></div>
  </div>
</body>
</html>