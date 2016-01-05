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

</head>

<body>
  <c:set
    var="numberOfItems"
    scope="page"
    value="${items}"
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
      <div
        class="cart"
        style="float: right; cursor: pointer"
        onclick="window.location='/v1/orderAPI/orders';"
      >
        <!--         <a -->
        <!--           href="/v1/orderAPI/orders" -->
        <!--           style="text-decoration: none" -->
        <!--         > -->
        <div class="moduletable">
          <div style="float: left; color: #41BECF; margin-left: 28px">
            <div
              class="vmCartModule simple-tooltip"
              title="Click here to go to your cart and place your order"
            >
              Now in your cart <b><span id="cartItemsCount">
                  <c:out value="${numberOfItems}" />
                </span> Items</b>
            </div>
          </div>
        </div>
      </div>
      <div
        id="search_form"
        class="right"
      >
        <form
          id="searchButton"
          action="NotYetImplmented.html"
          method="post"
          accept-charset="utf-8"
        >
          <input
            type="text"
            name="term"
            placeholder="Search is coming soon"
            placeholder="Enter search Keywords here"
            onkeyup="searchProducts(this.value)"
            autocomplete="off"
          />
          <button
            style="border-radius: 0px"
            type="submit"
          >Search</button>
          <input
            type="hidden"
            name="${_csrf.parameterName}"
            value="${_csrf.token}"
          />
        </form>
      </div>
      <!-- end search_form -->
    </div>
  </div>
  <div
    id="results"
    class="results"
    style="display: none;"
  ></div>
  <%@ include file="includes/mainNavBarWithCategories.jsp"%>
  <div class="full_wrap ">
    <div class="wide_wrap">
      <%@ include file="includes/categories.jsp"%>
      <div class="results">
        <div class="productHeaderContainer">
          <span class="productHeaderLabel">Welcome</span>
        </div>
        <br>
        <div class="welcomebox">
          <p class="welcomeboxgreetingline">Welcome to our website!</p>

          <p>Don’t stand in line at the grocery and liquor store.
            Get up to Solitude Village and enjoy your vacation.</p>

          <p>We will ‘gopher” your supplies. We shop the entire Salt
            Lake Valley to provide you with everything our area has to
            offer. A 20% service fee will be added to your order. A
            minimum $20.00 service fee will be added to orders under
            $100.00.</p>

          <p>Arriving late? No problem! We will arrange for your
            groceries to be delivered and properly stored, ready for
            your arrival.</p>

          <p>
            We also deliver beer, wine and spirits. Utah does not sell
            wine or spirits in their grocery stores and the State run
            liquor stores are not open on Sunday. You can give us a list
            or click <a href="http://abc.utah.gov/products/index.html">here</a>
            to view available products if you have something specific
            you would like. List them in your additional items list. We
            will do our best to acquire it. As with groceries a 20%
            delivery fee will apply to this order.
          </p>

          <p>Here’s how it works…</p>

          <p>1. Select the items and quantity you want from our
            product offering.</p>

          <p>2. Proceed to the delivery instructions page and let us
            know where you are staying and when you will arrive.</p>


          <p>3. Complete your order two ways. We can contact you for
            a credit card payment OR you can be invoiced and pay through
            Paypal. We're sorry, we can not accept personal checks.</p>

        </div>
      </div>
      <!--  Results -->
    </div>
  </div>

  <!-- JAVASCRIPT SECTION  -->
  <script
    type="text/javascript"
    src="/resources/js/gopher.js"
  ></script>
  <script type="text/javascript">
      //**********************************************************************
      $(".category").click(function() {
        if (false == $(this).next().is(':visible')) {
          $('#accordion ul').slideUp(200);
        }
        $(this).next().slideToggle(200);
      });
      //**********************************************************************
      $(".subcategory li")
          .click(
              function(event) {
                var selectedSubCategory = $(this).attr('id');
                $
                    .ajax({
                      url : "/v1/products/category/"
                          .concat(selectedSubCategory),
                      type : "GET",
                      dataType : "html",
                      success : function(data) {
                        $('.results').replaceWith($('.results').html(data));
                      },
                      error : function(jqXHR, textStatus, errorThrown) {
                        alert("Sorry, there was a problem! Your SESSION could be expired, please try refreshing the page.");
                      }
                    });
              });
      //**********************************************************************
      //Intercept 'addtocart' Form Submission, Tie the event handler to the document 
      //so we can replace the .results with other results.
      //**********************************************************************
      $(document)
          .on(
              'submit',
              ".addtocart",
              (function(event) {
                event.preventDefault();
                // Get the submit button element
                var inputform = $(event.target);
                var data = inputform.serialize();
                console.log("addtocart event caught");
                console.log(data);
                var URL = inputform.attr("action");
                $
                    .post(
                        URL,
                        data,
                        function(data, textStatus, jqXHR) {
                          //data - In the form of an AddToCartResult
                          //Replace number of items in cart here. 
                          if (data.error) {
                            alert(data.errorMsg);
                          } else {
                            console.log(data);
                            alert("Successfully Added to Cart\nTotal Number of Items: "
                                + data.orderSummary.numberOfItems
                                + "\n Total in Cart = $"
                                + data.orderSummary.total.toFixed(2)
                                + "\n\n NOTE: Includes all Service Fees"
                                + "\n Click Ok or simply hit 'Enter' to continue");
                            var text = $('#cartItemsCount').html(
                                data.orderSummary.numberOfItems);
                          }
                        })
                    .fail(
                        function(jqXHR, textStatus, errorThrown) {
                          alert("Sorry, there was a problem! Your SESSION could be expired, please try refreshing the page. \n"
                              + errorThrown);
                        });
              }));
      //**********************************************************************
      //Intercept 'Search' Form Submission, Tie the event handler to the document 
      //so we can replace the .results with other results.
      //**********************************************************************
      $(document)
          .on(
              'submit',
              "#searchButton",
              (function(event) {
                event.preventDefault();
                // Get the submit button element
                var term = $('input[name="term"]').val();
                var searchValue = $(event.target);
                var data = searchValue.serialize();
                console.log("Search event caught");
                console.log("Serialized DATA: " + data);
                console.log("Search Term: " + term);
                alert("The function to search is coming soon. Thank you for your patience while we work on this.");
              }));

      function getOrderSummary() {
        $
            .ajax({
              url : "v1/addtocart/ordersummary",
              type : "GET",
              success : function(data) {
                var text = $('#cartItemsCount').html(
                    data.orderSummary.numberOfItems);
              },
              error : function(jqXHR, textStatus, errorThrown) {
                alert("Sorry, there was a problem!.Your SESSION could be expired, please try refreshing the page.  \n"
                    + errorThrown);
              }
            });
      }
      function searchProducts(searchValue) {
        //NO OP
      }
      $(document).ready(getOrderSummary);
    </script>
</body>
</html>