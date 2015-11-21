<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="security"
  uri="http://www.springframework.org/security/tags"%>

<%@ include file="includes/header.jsp"%>


<body>

  <div class="full_wrap full_wrap-back">
    <div id="header" class="wide_wrap">
      <%--       <a href="/logout">Logout. Hello <security:authentication property="principal.username" />!</a> --%>
      <a href="/"> <img class="logo-marginn"
        src="/resources/FONTS/GG-Logo-Color.png"
        alt="Gopher-Groceries | Order your Groceries Online Today - Great Ski Holidays"
        width="160px">
      </a>
    </div>
    <!-- header -->
  </div>
  <!-- full_wrap  -->


  <div id="orders" class="orders" style="display: none;"></div>



  <%@ include file="includes/mainNavBar.jsp"%>
  <div class="full_wrap ">
    <div class="wide_wrap"></div>
  </div>
  <div class="orders">
    <table>
      <tr>
        <th></th>
        <th>Item</th>
        <th>Description</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Total</th>
      </tr>

      <c:set var="itemCount" value="1" scope="page" />
      <c:set var="salesTotal" value="0" scope="page" />
      <c:forEach
        items="${orderSummaryResult.orderSummary.order.orderEntity.orderLines}"
        var="orderLine">
        <c:set var="count" value="${count+1}" scope="page" />
        <c:set var="salesTotal"
          value="${salesTotal + (orderLine.price * orderLine.quantity)}"
          scope="page" />
        <tr>
          <td>${count }.</td>
          <td>${orderLine.product.name}</td>
          <td>${orderLine.product.description}</td>
          <td><input class="spinner simple-tooltip" name="value"
              placeholder="${orderLine.quantity}"
              title="You can change the quantity if you want but remember to click the update when you're done changing quantities!"></td>
          <td>$<fmt:formatNumber value="${orderLine.price}"
              minFractionDigits="2" /></td>
          <td>$<fmt:formatNumber
              value="${orderLine.price * orderLine.quantity}"
              minFractionDigits="2" />
          </td>
        </tr>
      </c:forEach>
      <tr>
        <td id="totalLabel" colspan="5">Total</td>
        <td>$<fmt:formatNumber value="${salesTotal}"
            minFractionDigits="2"></fmt:formatNumber></td>
    </table>
    <input class="simple-tooltip" id="updateButton" name="updateButton"
      type="button" disabled value="Update"
      title="This button is disabled until you change a quantity" />
  </div>

  <!-- -------------------------------------------------------------------- -->
  <!-- Page Specific Javascript HERE -->
  <script type="text/javascript" src="/resources/js/gopher.js"></script>
  <script type="text/javascript">
      var errorMsg = "${orderSummaryResult.errorMsg}";
      console.log(errorMsg);
      $("#updateButton").prop("disabled", true);
      $("#updateButton").tooltip({
        show : {
          effect : "blind",
          duration : 200
        }
      });

      $(document).on(
          "click",
          "#updateButton",
          function(event) {
            var inputForm = $(event.target);
            var data = inputForm.serialize();
            console.log(data);
            $("#updateButton").attr("title",
                "Quantity has changed, update enabled. ");
            alert("Update Button pushed");
          });

      $(function() {
        $(".spinner").spinner(
            {
              spin : function(event, ui) {
                if (ui.value > 10) {
                  $(this).spinner("value", 0);
                  return false;
                } else if (ui.value < 0) {
                  $(this).spinner("value", 0);
                  return false;
                }
                // Someone hit the spinner make the update button visible
                console.log("Someone hit the spinner")
                $("#updateButton").prop('disabled', false);
                //For some reason need to remove the attr first. 
                $("#updateButton").removeAttr("title");
                $("#updateButton").attr("title",
                    "Quantity has changed, update enabled. ");
                $("#updateButton").attr("id","updateButtonEnabled");
              }
            });
      });
    </script>

</body>
</html>