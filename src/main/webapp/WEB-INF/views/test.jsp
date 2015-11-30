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

  <div class="main-nav-bar">

    <div class="nav-bar">
      <ul>
        <li><a href="/">Online store</a></li>
        <li><a href="/NotYetImplemented">How It Works</a></li>
        <li><a href="/NotYetImplemented">FAQs</a></li>
        <li><a href="contacts.html">Contacts</a></li>
        <li><a href="/NotYetImplemented"><span id="loginlogoutlabel">Login</span></a></li>
      </ul>
    </div>

  </div>


  <div class="clear"></div>
  <div class="ordersummary ">
     <br>
        <!-- LEFT HAND SIDE -->
        <div class="order-review-left">
          <span class="deliver-to-header simple-tooltip"
            title="Where to deliver the groceries.This will almost always be the condominium/cabin where you will be staying for your holidays.This is NOT your homeaddress."
          >Delivery Information</span>
          <hr>

          <label class="delivery-detail-label" for="name">Name: </label>
          <div class="detail" id="name">${oe.firstName}${oe.lastName}</div>

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
    
    </div>

</body>
</html>