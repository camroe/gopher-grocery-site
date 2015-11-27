<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ include file="includes/header.jsp"%>


<body>
  <input type="hidden" id="csrf-token" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <input type="hidden" id="orderInfo" name="orderInfo" value="${osJson}" />
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


  <div id="orders" class="orders" style="display: none;"></div>



  <%@ include file="includes/mainNavBar.jsp"%>
  <div class="full_wrap ">
    <div class="wide_wrap">

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
          <c:forEach items="${orderSummaryResult.orderSummary.order.orderEntity.orderLines}"
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
              <td><input class="spinner simple-tooltip" name="itemQuantity"
                value="${orderLine.quantity}"
                title="You can change the quantity if you want but remember to click the update when you're done changing quantities!"
              ></td>
              <td class="tdPrice">$<fmt:formatNumber value="${orderLine.price}"
                  minFractionDigits="2"
                /></td>
              <td class="tdTotal">$<fmt:formatNumber
                  value="${orderLine.price * orderLine.quantity}" minFractionDigits="2"
                />
              </td>
              <td class="hiddenTableColumn tdOrderLineID">"${orderLine.id}"</td>
            </tr>
          </c:forEach>
          <tr>
            <td id="totalLabel" colspan="5">Total</td>
            <td id="grandTotal">$<fmt:formatNumber value="${salesTotal}" minFractionDigits="2"></fmt:formatNumber></td>
        </table>
        <input class="simple-tooltip" id="updateButton" name="updateButton" type="button" disabled
          value="Update Changes to Order"
          title="This button is disabled until you change a quantity"
        /> <input class="simple-tooltip" id="nextButton" name="next" type="button"
          value="Next Page - Delivery Details"
          title="Go to Delivery Details page - This will NOT update your order"
          onclick="window.location.href='/v1/delivery'"
        />
      </div>


    </div>
  </div>

  <!-- -------------------------------------------------------------------- -->
  <!-- Page Specific Javascript HERE -->
  <script type="text/javascript" src="/resources/js/gopher.js"></script>
  <script type="text/javascript">
      $(document)
          .ready(
              function() {
                var token1 = $("input#csrf-token").attr("value");
                console.log(token1);
                var errorMsg = "${orderSummaryResult.errorMsg}";
                console.log(errorMsg);
                var osJson = $.parseJSON('${osJson}'); //Page scoped osJson
                canIReadIt(osJson);

                /* Change the quantity in the OrderLineEntity */
                function changeQuantity(olid, newQuantity) {
                  /* Find the OrderLineEntity in the osJson object identifed by the olid */
                  var orderLines = osJson.orderSummary.order.orderEntity.orderLines;
                  var olidNum = olid.replace('"', '');
                  olidNum = olid.replace('"', '');
                  olidNum = parseInt(olidNum);
                  console.log("olidNum " + olidNum);
                  /*Check for undefined or null (falsy) */
                  if (orderLines == null) {
                    console
                        .log("Attempt to change quantities on an orderLine that can't be found");
                  } else {
                    /* Search through orderLines until you find the id 
                    - Could try to manage the index into the arrary but for now
                    just brute hunt */
                    $.each(orderLines, function(i, ole) {
                      console.log("OLE.id : " + ole.id + " .vs " + "olidNum : "
                          + olidNum);
                      if (ole.id == olidNum) {
                        ole.quantity = newQuantity;
                        console.log("Updated OLE: " + olidNum + " to "
                            + newQuantity);
                        return false;
                      }
                    });
                  }
                }

                //                 $(document)
                //                     .on(
                //                         "click",
                //                         "#nextButton",
                //                         function(event) {
                //                           var eTarget = $(event.target);
                //                           var oeData = JSON
                //                               .stringify(osJson.orderSummary.order.orderEntity);
                //                           var URL = "/v1/delivery";
                //                           $
                //                               .ajax({
                //                                 url : URL,
                //                                 type : "POST",
                //                                 data : oeData,
                //                                 contentType : "application/json; charset=utf-8",
                //                                 headers : {
                //                                   'X-CSRF-Token' : "${_csrf.token}"
                //                                 },
                //                                 success : function(data, textStatus, jqXHR) {
                //                                   if (data.error) {
                //                                     alert(data.errorMsg);
                //                                   } else {
                //                                     window.location.href = "/v1/delivery";                                  }
                //                                 },
                //                                 error : function(jqXHR, textStatus, errorThrown) {
                //                                   alert("Sorry, there was a problem! I was unable to access this URL. \n"
                //                                       + errorThrown);
                //                                 }
                //                               });

                //                         });

                $(document)
                    .on(
                        "click",
                        "#updateButtonEnabled",
                        function(event) {
                          var eTarget = $(event.target);
                          var data = JSON
                              .stringify(osJson.orderSummary.order.orderEntity);
                          console.log("ABOUT TO SEND : " + data);

                          //                 $("#updateButtonEnabled").tooltip("close");
                          var URL = "/v1/orderAPI/orders";

                          $
                              .ajax({
                                url : URL,
                                type : "POST",
                                data : data,
                                contentType : "application/json; charset=utf-8",
                                headers : {
                                  'X-CSRF-Token' : "${_csrf.token}"
                                },
                                success : function(data, textStatus, jqXHR) {
                                  //data - In the form of an AddToCartResult
                                  //Replace number of items in cart here. 
                                  if (data.error) {
                                    alert(data.errorMsg);
                                    //                                     location.reload();
                                  } else {
                                    //                                     $('body').html(data);
                                    location.reload();
                                  }
                                },
                                error : function(jqXHR, textStatus, errorThrown) {
                                  alert("Sorry, there was a problem! I was unable to access this URL. \n"
                                      + errorThrown);
                                }
                              });

                        });

                $(function() {
                  $(".spinner").spinner(
                      {
                        spin : function(event, ui) {
                          if (ui.value > 100) {
                            $(this).spinner("value", 0);
                            return false;
                          } else if (ui.value < 0) {
                            $(this).spinner("value", 0);
                            return false;
                          }
                          // Someone hit the spinner make the update button visible
                          $("#updateButton").prop('disabled', false);
                          //For some reason need to remove the attr first. 
                          $("#updateButton").removeAttr("title");
                          $("#updateButton").attr("title",
                              "Quantity has changed, update enabled. ");
                          $("#updateButton").attr("id", "updateButtonEnabled");
                          var $olidField = $(this).closest("tr") //Finds the closest row
                          .find(".tdOrderLineID"); //Gets the descendent with the class = .orderLineID
                          var $olid = $olidField.text(); //Retrieves the text with the <td>
                          console.log("Spinner:Change Quantity on orderLine : "
                              + $olid + " to " + ui.value);
                          var $price = $(this).closest("tr").find(".tdPrice")
                              .text().replace('$', '');
                          console.log("Price: " + $price);
                          var $totalField = $(this).closest("tr").find(
                              ".tdTotal");
                          var $total = $price * ui.value;
                          console.log("Total: $" + $total.toFixed(2));
                          $(this).closest("tr").find(".tdTotal").text(
                              "$" + $total.toFixed(2));
                          //Grand Total - recalc now that the subtotal in a row has changed
                          var sum = 0;
                          $(".tdTotal").each(function() {
                            var value = $(this).text().replace('$', '');
                            // add only if the value is number
                            if (!isNaN(value) && value.length != 0) {
                              sum += parseFloat(value);
                            }
                          });
                          $("#grandTotal").text("$" + sum.toFixed(2));
                          //Now change the json
                          changeQuantity($olid, ui.value);
                        }
                      });
                });

                $(function() {
                  $(".spinner").spinner(
                      {
                        change : function(event, ui) {
                          if (ui.value > 100) {
                            $(this).spinner("value", 0);
                            return false;
                          } else if (ui.value < 0) {
                            $(this).spinner("value", 0);
                            return false;
                          }
                          // Someone hit the spinner make the update button visible
                          $("#updateButton").prop('disabled', false);
                          //For some reason need to remove the attr first. 
                          $("#updateButton").removeAttr("title");
                          $("#updateButton").attr("title",
                              "Quantity has changed, update enabled. ");
                          $("#updateButton").attr("id", "updateButtonEnabled");
                          var $olidField = $(this).closest("tr") //Finds the closest row
                          .find(".tdOrderLineID"); //Gets the descendent with the class = .orderLineID
                          var $olid = $olidField.text(); //Retrieves the text with the <td>
                          console.log("Change:Change Quantity on orderLine : "
                              + $olid + " to " + this.value);
                          var $price = $(this).closest("tr").find(".tdPrice")
                              .text().replace('$', '');
                          console.log("Price: " + $price);
                          var $totalField = $(this).closest("tr").find(
                              ".tdTotal");
                          var $total = $price * this.value;
                          console.log("Total: $" + $total.toFixed(2));
                          $(this).closest("tr").find(".tdTotal").text(
                              "$" + $total.toFixed(2));
                          //Grand Total - recalc now that the subtotal in a row has changed
                          var sum = 0;
                          $(".tdTotal").each(function() {
                            var value = $(this).text().replace('$', '');
                            // add only if the value is number
                            if (!isNaN(value) && value.length != 0) {
                              sum += parseFloat(value);
                            }
                          });
                          $("#grandTotal").text("$" + sum.toFixed(2));
                          changeQuantity($olid, this.value);
                        }
                      });
                });

              });
    </script>

</body>
</html>