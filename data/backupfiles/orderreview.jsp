<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%-- <%@ include file="includes/header.jsp"%> --%>


<body>
  <input type="hidden" id="csrf-token" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <!--  Set up the page -->
  <c:set var="oe" value="${orderSummaryResult.orderSummary.order.orderEntity}" />
  <c:set var="os" value="${orderSummaryResult.orderSummary}" />

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
<%--   <%@ include file="includes/mainNavBar.jsp"%> --%>
    <div id="delivery" class="delivery" style="display: none"></div>
  
  <div class="full_wrap ">
    <div class="wide_wrap">
      <div class="order-review-summary">
        <br>
        <!-- LEFT HAND SIDE -->
        <div class="order-review-left">
          <span class="deliver-to-header simple-tooltip"
            title="Where to deliver the groceries.This will almost always be the condominium/cabin where you will be staying for your holidays.This is NOT your homeaddress."
          >Delivery Information</span>
          <hr>

          <label class="delivery-detail-label" for="name">Name: </label>
          <div class="detail" id="name">${oe.firstName}${oe.lastname}</div>

          <label class="delivery-detail-label" for="location">Location: </label>
          <div class="detail" id="location">${oe.location}</div>

          <label class="delivery-detail-label" for="unit">Unit: </label>
          <div class="detail" id="unit">${oe.unit}</div>

          <label class="delivery-detail-label" for="phone">Phone: </label>
          <div class="detail" id="phone">${oe.phone}</div>

          <label class="delivery-detail-label" for="email">EMail: </label>
          <div class="detail" id="email">${oe.email}</div>

          <label class="delivery-detail-label" for="username">UserName: </label>
          <div class="detail simple-tooltip" id="username"
            title="This is the username that you are logged in as. If you have not logged in this will be 'anonymousUser'"
          >${oe.username}</div>
          <hr>
        </div>

        <!-- RIGHT HAND SIDE  -->
        <div class="order-review-right">
          <span class="deliver-to-header simple-tooltip" title="Order Summary.">Order Summary</span>
          <hr>

          <label class="delivery-detail-label" for="id">Order ID: </label>
          <div class="detail" id="id">${oe.id}</div>

          <label class="delivery-detail-label" for="totalitems">Number of Items: </label>
          <div class="detail" id="totalitems">${os.numberOfItems}</div>

          <label class="delivery-detail-label" for="total">Total: </label>
          <div class="detail" id="total">$$<fmt:formatNumber
              value="${os.total}"
              minFractionDigits="2"
              maxFractionDigits="2"
            ></fmt:formatNumber></div>
          <hr>
        </div>
        <!--  End Right -->
        <!-- COMMENT -->
        <div class="comment">
          <label class="delivery-detail-label" for="comment" style="font: bolder;">Comment:</label>
          <div class="comment-detail" id="comment">${oe.comment}</div>
        </div>
        <!-- End Comment -->
        <!-- Delivery Adjust Button -->
        <div class="order-review-left">
          <section class="pressadjust">
            <button onclick="window.location.href='/v1/delivery';">Adjust Delivery</button>
          </section>
        </div>
        <!-- End Left -->
        <!-- Order Adjust Button -->
        <div class="order-review-right">
          <section class="pressadjust" style="float: right;">
            <button onclick="window.location.href='/v1/orderAPI/orders';">Adjust Order</button>
          </section>
        </div>
        <!-- End Right -->
        <!--  ORDER Details -->
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

            <c:set var="itemCount" value="1" scope="page" />
            <c:set var="salesTotal" value="0" scope="page" />
            <c:forEach items="${orderSummaryResult.orderSummary.order.orderEntity.orderlines}"
              var="orderLine"
            >
              <c:set var="count" value="${count+1}" scope="page" />
              <c:set var="salesTotal" value="${salesTotal + (orderLine.price * orderLine.quantity)}"
                scope="page"
              />
              <tr>
                <td>${count }.</td>
                <td>${orderLine.product.name}</td>
                <td>${orderLine.product.description}</td>
                <td>${orderLine.quantity}</td>
                <td class="tdPrice">$<fmt:formatNumber value="${orderLine.price}"
                    minFractionDigits="2"
                    maxFractionDigits="2"
                    
                  /></td>
                <td class="tdTotal">$<fmt:formatNumber
                    value="${orderLine.price * orderLine.quantity}" minFractionDigits="2"
                    maxFractionDigits="2"
                  />
                </td>
                <td class="hiddenTableColumn tdOrderLineID">"${orderLine.id}"</td>
              </tr>
            </c:forEach>
            <tr>
              <td id="totalLabel" colspan="5">Total</td>
              <td id="grandTotal">$<fmt:formatNumber value="${salesTotal}"
                  minFractionDigits="2"
                  maxFractionDigits="2"
                ></fmt:formatNumber></td>
          </table>
        </div>
      </div>
      <div style="clear: both; display: block;"></div>
      <div class="order-review-summary" style="display:block;">

        <!-- Call for payment Button -->
        <div class="order-review-left" style="top:50px;">
          <section class="pressorder">
            <button onclick="window.location.href='/NotYetImplemented';">Call For Payment</button>
          </section>
        </div>
        <!-- End Left -->
        <!-- Paypal Invoice Button -->
        <div class="order-review-right"  style="top:50px;">
          <section class="pressorder" style="float: right; ">
            <button onclick="window.location.href='/v1/delivery/paypal';">PayPal Invoice</button>
          </section>
        </div>
      </div>
    </div>
  </div>
</body>
</html>