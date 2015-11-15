<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%-- <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> --%>

<%@ include file="includes/header.jsp"%>


<body>

  <div class="full_wrap full_wrap-back">
    <div id="header" class="wide_wrap">
      <%--       <a href="/logout">Logout. Hello <security:authentication property="principal.username" />!</a> --%>

      <div class="cart" style="float: right">
        <div class="moduletable">
          <div style="float: left; color: #41BECF; margin-left: 28px">
            <div class="vmCartModule">
              Now in your cart <a style="text-decoration: none" href="NotYetImplemented.html"><b><span
                  id="cartItemsCount"
                >0</span> Items</b></a>
            </div>
          </div>
        </div>
      </div>
      <div id="search_form" class="right">
        <form action="NotYetImplmented.html" method="post" accept-charset="utf-8">
          <input type="text" name="term" placeholder="Enter search Keywords here"
            onkeyup="searchProducts(this.value)" autocomplete="off"
          />
          <button style="border-radius: 0px" type="submit">Search</button>
        </form>
      </div>
      <a href="NotYetImplemented.html"> <img class="logo-marginn"
        src="resources/FONTS/GG-Logo-Color.png"
        alt="Gopher-Groceries | Order your Groceries Online Today - Great Ski Holidays"
        width="110px"
      >
      </a>
    </div>
  </div>

  <div id="results" class="results" style="display: none;"></div>

  <!-- Main Section -->
  <div class="main-nav-bar">
    <div class="cat-headimg">
      <h3>Categories</h3>
    </div>
    <div class="nav-bar">
      <ul>
        <li><a href="/">Online store</a></li>
        <li><a href="NotYetImplemented.html">Resort Delivery</a></li>
        <li><a href="NotYetImplemented.html">FAQs</a></li>
        <li><a href="contacts.html">Contacts</a></li>
      </ul>
    </div>

    <div class="clear"></div>
  </div>

  <div class="full_wrap ">
    <div class="wide_wrap">
      <div>
        <ul id="accordion">
          <c:forEach items="${catMap}" var="mainCategory">
            <li>
              <div class="category">
                <a href='${mainCategory.urlAddress}'>${mainCategory.name}</a>
              </div>
              <ul class=subcategory>
                <c:forEach items="${mainCategory.getSubCats()}" var="subCategory">
                  <li id='${subCategory.urlAddress}'><a href=#>${subCategory.displayName}</a>
                </c:forEach>
              </ul>
            </li>
          </c:forEach>
        </ul>
      </div>


      <div class="results">

        <div class="productHeaderContainer">
          <span class="productHeaderLabel">Popular Products</span>
        </div>
        <br>
        <c:forEach items="${popularProducts}" var="popularProducts">
          <!-- ProductBOX-->
          <div class="productbox">
            <div class="productImageContainer simple-tooltip" title='${popularProducts.description}'>
              <img src='${popularProducts.imageFile}' />
            </div>
            <div class="productTitle">${popularProducts.name}</div>
            <div class="productDetailsContainer">
              <div class="priceContainer">
                <span class="priceLabel">Price </span> <span class="priceAmount">$${popularProducts.price}</span>
              </div>
              <div class="productClear"></div>
              <!-- This is code from simply groceries -->

              <div class="addToCartContainer">

                <form class="addtocart" action="/v1/addtocart" method="post" accept-charset="utf-8">
                  <div class="hiddenCartDetails">
                    <input type="hidden" name="cartkey" value="CartKey" /> <input type="hidden"
                      name="id" value='${popularProducts.id}'
                    /> <input type="hidden" name="sku" value='${popularProducts.sku}' /> <input
                      type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"
                    />
                  </div>
                  <div class="productQuantityContainer">
                    <span class=quantityLabel>Quantity </span> <input class="productQuantityBox"
                      type="text" name="quantity" value="1"
                    />
                  </div>
                  <div class="addToCartButtonContainer">
                    <input class="addtocartButton" type="submit" value="Add to Cart" />

                  </div>
                </form>
              </div>
            </div>
            <div class="pricingSeperator"></div>
          </div>
          <div class="productClear"></div>
        </c:forEach>



      </div>
      <!--  Results -->

    </div>
  </div>

  <!-- JAVASCRIPT SECTION  -->



  <script type="text/javascript">
      $(".category").click(function() {
        console.log("cat clicked")

        if (false == $(this).next().is(':visible')) {
          $('#accordion ul').slideUp(200);
        }
        $(this).next().slideToggle(200);
      });

      $(".subcategory li")
          .click(
              function(e) {
                console.log("subcat clicked");
                var target = $(e.target);
                var origin = (target.context.origin);//http://localhost:8080
                var pathname = (target.context.pathname);
                // 			var selectedSubCategory = target.context.innerText;
                var selectedSubCategory = $(this).attr('id');
                var callURL = origin.concat(pathname
                    .concat(selectedSubCategory));

                console.log(selectedSubCategory);
                console.log(callURL);
                console.log(target);
                console.log($(this).attr('id'));
                //Call product function here
                // 						window.location.href = callURL;
                $
                    .ajax({
                      url : "v1/products/category/".concat(selectedSubCategory),
                      type : "GET",
                      dataType : "html",
                      success : function(data) {
                        $('.results').replaceWith($('.results').html(data));
                      },
                      error : function(jqXHR, textStatus, errorThrown) {
                        alert("Sorry, there was a problem! I was unable to access this URL. \n This problem has been logged.");
                      }
                    });
              });
      //Intercept Form Submission, Tie the event handler to the document so we
      //can replace the .results with other results.
      $(document).on('submit', ".addtocart", (function(event) {
        event.preventDefault();
        // Get the submit button element
        var inputform = $(event.target);
        var data = inputform.serialize();
        console.log("addtocart event caught");
        console.log(data);
        var URL = inputform.attr("action");
        $.post(URL, data, function(data, textStatus, jqXHR) {
          //data
          //Replace number of items in cart here. 
          console.log(data);
          var text = $('#cartItemsCount').html(data);
          console.log(text);
        }).fail(function(jqXHR, textStatus, errorThrown) {
          alert(errorThrown);
        });
      }));
    </script>
</body>
</html>