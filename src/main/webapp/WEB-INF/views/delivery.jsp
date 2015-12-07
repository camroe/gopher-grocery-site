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
  href="/resources/css/delivery.css"
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

  <div class="full_wrap full_wrap-back">
    <div
      id="header"
      class="wide_wrap"
    >
      <%--       <a href="/logout">Logout. Hello <security:authentication property="principal.username" />!</a> --%>
           <a href="/"> <img class="logo-marginn simple-tooltip" title="Click here to return to the home page"
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
    id="delivery"
    class="delivery"
    style="display: none"
  ></div>

  <!--  Set up the page -->
  <c:set
    var="oe"
    value="${orderSummaryResult.orderSummary.order.orderEntity}"
  />

  <div class="full_wrap ">
    <div class="wide_wrap">
      <div
        id="delivery"
        class="delivery"
      >
        <div class="deliveryHeaderContainer">
          <span
            class="deliveryHeaderLabel simple-tooltip"
            title="Where to deliver the groceries.This will almost always be the condominium/cabin where you will be staying for your holidays.This is NOT your homeaddress."
          >Delivery Information</span>
        </div>
        <form
          id="deliveryForm"
          action="/v1/delivery"
          method="post"
        >
          <!-- Include csrf in form submission -->
          <input
            type="hidden"
            id="csrf-token"
            name="${_csrf.parameterName}"
            value="${_csrf.token}"
          />
          <fieldset>

            <table id="errorLocation">
              <tr>
                <td colspan="2">
                  <h2 style="border-bottom: 1px solid #CCCCCC;">Deliver
                    TO Information</h2>
                </td>
              </tr>
              <tr>
                <td>
                <td>
              </tr>

              <tr>
                <td class="label"><label for="first_name">First
                    Name:</label></td>
                <td><input
                    id="first_name"
                    name="first_name"
                    size="20"
                    type="text"
                    tabindex="1"
                    value="<c:out value="${oe.firstName}"/>"
                    placeholder="Your First Name"
                    data-val="true"
                    data-val-required="Please enter a valid name. This field is required"
                    data-val-length="Please use at least 3 characters"
                    data-val-length-min="3"
                  > <span
                    data-valmsg-replace="true"
                    data-valmsg-for="first_name"
                  ></span></td>
              </tr>
              <tr>
                <td class="label"><label for="last_name">Last
                    Name:</label></td>
                <td><input
                    id="last_name"
                    name="last_name"
                    size="20"
                    type="text"
                    tabindex="2"
                    value="<c:out value="${oe.lastName}"/>"
                    placeholder="Your Last Name"
                    data-val="true"
                    data-val-required="Please enter a valid name. This field is required"
                    data-val-length="Please use at least 3 characters"
                    data-val-length-min="3"
                  > <span
                    data-valmsg-replace="true"
                    data-valmsg-for="last_name"
                  ></span></td>
              </tr>

              <c:set
                var="ns"
                value=""
                scope="page"
              />
              <c:set
                var="ese"
                value=""
                scope="page"
              />
              <c:set
                var="esw"
                value=""
                scope="page"
              />
              <c:set
                var="pl"
                value=""
                scope="page"
              />
              <c:set
                var="cross"
                value=""
                scope="page"
              />
              <c:set
                var="creek"
                value=""
                scope="page"
              />
              <c:set
                var="na"
                value=""
                scope="page"
              />
              <c:choose>
                <c:when test="${oe.location eq 'NS'}">
                  <c:set
                    var="ns"
                    value="selected"
                  />
                </c:when>
                <c:when test="${oe.location eq 'Eagle Springs East'}">
                  <c:set
                    var="ese"
                    value="selected"
                  />
                </c:when>
                <c:when test="${oe.location eq 'Eagle Spings West'}">
                  <c:set
                    var="esw"
                    value="selected"
                  />
                </c:when>
                <c:when test="${oe.location eq 'Powderhorn Lodge'}">
                  <c:set
                    var="pl"
                    value="selected"
                  />
                </c:when>
                <c:when test="${oe.location eq 'Crossings'}">
                  <c:set
                    var="cross"
                    value="selected"
                  />
                </c:when>
                <c:when test="${oe.location eq 'Creekside'}">
                  <c:set
                    var="creek"
                    value="selected"
                  />
                </c:when>
                <c:when test="${oe.location eq 'NA'}">
                  <c:set
                    var="na"
                    value="selected"
                  />
                </c:when>
                <c:otherwise>
                  <c:set
                    var="ns"
                    value="selected"
                  />
                </c:otherwise>
              </c:choose>

              <tr>
                <td class="label"><label for="location">Delivery
                    Address:</label></td>
                <td><select
                    id="location"
                    name="location"
                    tabindex="3"
                  >

                    <option
                      value="NS"
                      <c:out value="${ns}"/>
                    >Choose Solitude Location:(currently select - none)</option>
                    <option
                      value="Eagle Springs East"
                      <c:out value="${ese}"/>
                    >Eagle Springs East</option>
                    <option
                      value="Eagle Spings West"
                      <c:out value="${esw}"/>
                    >Eagle Springs West</option>
                    <option
                      value="Powderhorn Lodge"
                      <c:out value="${pl}"/>
                    >Powderhorn Lodge</option>
                    <option
                      value="Crossings"
                      <c:out value="${cross}"/>
                    >Crossings</option>
                    <option
                      value="Creekside"
                      <c:out value="${creek}"/>
                    >Creekside</option>
                    <option
                      value="NA"
                      <c:out value="${na}"/>
                    >The address of where I'm staying isn't in the
                      list! I'm using the comments to give the address</option>
                  </select></td>
              </tr>
              <tr>
                <td class="label"><label for="unit">Unit
                    Number</label></td>
                <td><input
                    id="unit"
                    name="unit"
                    size="20"
                    type="text"
                    tabindex="4"
                    placeholder="1234"
                    value="<c:out value="${oe.unit}"/>"
                    data-val="true"
                    data-val-number="Unit must be a just a number with no special characters "
                    data-val-required="Please enter a valid unit number. This field is required"
                    data-val-length="Please use between 1 to 4 digits"
                    data-val-length-min="1"
                    data-val-length-max="4"
                  > <span
                    data-valmsg-replace="true"
                    data-valmsg-for="unit"
                  ></span></td>
              </tr>

              <!--               <tr> -->
              <!--                 <td class="label"><label for="address1">Delivery -->
              <!--                     Address:</label></td> -->
              <!--                 <td><input id="address1" name="address1" size="30" -->
              <!--                     type="text" tabindex="3" value=""></td> -->
              <!--               </tr> -->
              <!--               <tr> -->
              <!--                 <td class="label"></td> -->
              <!--                 <td><input maxlength="40" name="address2" size="30" -->
              <!--                     type="text" tabindex="4" value=""></td> -->
              <!--               </tr> -->


              <tr>
                <td class="label"><label for="city">City:</label></td>
                <td>Salt Lake City</td>
              </tr>
              <tr>
                <td class="label"><label for="state">State:</label></td>
                <td>UT</td>
              </tr>
              <tr>
                <td class="label"><label for="zip">Zip:</label></td>
                <td>84121</td>
              </tr>
              <tr>
                <td class="label"><label for="checkindate">Checkin
                    Date and Time:</label></td>
                <!-- datetime-local (yyyy-mm-ddTHH:MMZ) -->
                <td><input
                    id="checkindate"
                    name="checkindate"
                    type="text"
                    size="40"
                  ></td>
              </tr>

              <tr>
                <td colspan="2">
                  <h2 style="border-bottom: 1px solid #CCCCCC;">Contact
                    Information</h2>
                </td>
              </tr>

              <tr>
                <td class="label"><label for="phone">Phone:</label></td>
                <td><input
                    id="phone"
                    name="phone"
                    type="tel"
                    tabindex="5"
                    value="<c:out value="${oe.phone}"/>"
                    placeholder="(___) ___-____"
                    data-val="true"
                    data-val-required="Please enter a valid telephone number. This field is required"
                    data-val-length="A phone number must be at least 10 characters"
                    data-val-length-min="10"
                    data-val-regex="This does not match any known pattern for a telephone number. Try just the 10 digits."
                    data-val-regex-pattern="^(\+\d{1,2}\s)?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$"
                  > <span
                    data-valmsg-replace="true"
                    data-valmsg-for="phone"
                  ></span></td>
              </tr>
              <tr>
                <td class="label"><label for="email">Email:</label></td>
                <td><input
                    id="email"
                    name="email"
                    size="20"
                    type="email"
                    tabindex="6"
                    value="<c:out value="${oe.email}"/>"
                    placeholder="name@domain.com"
                    data-val="true"
                    data-val-required="Please enter a valid email. This field is required"
                    data-val-length="An email address must be at least 5 characters"
                    data-val-length-min="5"
                    data-val-email="This does not match any known pattern for an email address."
                  > <span
                    data-valmsg-replace="true"
                    data-valmsg-for="email"
                  ></span></td>
              </tr>

              <tr>
                <td class="label"><label for="emailRetype">Retype
                    Email:</label></td>
                <td><input
                    id="emailRetype"
                    name="emailRetype"
                    size="20"
                    type="email"
                    tabindex="7"
                    value=""
                    placeholder="retype your email address"
                    data-val="true"
                    data-val-equalto="Retyped Email does not match"
                    data-val-equalto-other="email"
                    data-val-required="Please enter a valid email. This field is required"
                    data-val-length="An email address must be at least 5 characters"
                    data-val-length-min="5"
                    data-val-email="This does not match any known pattern for an email address."
                  > <span
                    data-valmsg-replace="true"
                    data-valmsg-for="emailRetype"
                  ></span></td>
              </tr>


              <tr>
                <td class="label"><label for="comment">Comment:</label></td>
                <td><textarea
                    rows="5"
                    cols="60"
                    id="comment"
                    name=comment
                    tabindex="7"
                    placeholder="Comments - Further information - Special instructions or Requests"
                  ><c:out value="${oe.comment}" /></textarea></td>
              </tr>


              <tr>
                <td></td>
                <td>
                  <div>
                    <input
                      class="nextButton"
                      type="submit"
                      value="Next"
                      tabindex="7"
                    >
                    <label class="buttonLabel">When you are
                      ready to submit your delivery information and
                      review your complete order, just click 'Next'.</label>
                  </div>
                </td>
              </tr>
            </table>
            <br> <br>
          </fieldset>
        </form>
        <br clear="all">
      </div>
    </div>
  </div>

  <script type="text/javascript">
      //Set the datepicker format
      $("#checkindate").datepicker("option", "dateFormat", "DD, d MM, yy");
      //Set the datepicker event
      $(function() {
        $("#checkindate").datepicker();
        $("#checkindate").datepicker("option", "dateFormat", "DD, d MM, yy");
      });
    </script>

</body>
</html>
