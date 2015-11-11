<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%-- <c:set var="req" value="${paaeContext.request}"/> --%>
<%-- <c:set var="uri" value="${req.requestURI}" /> --%>
<%-- <c:set var="url">${req.requestURL}</c:set> --%>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<%-- <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" /> --%>
<!-- <base href="http://localhost:8080/site/"/> -->
<!-- <base href=""/> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Gopher-Groceries | Order your Groceries Online Today for a GREAT Ski Holiday</title>

<meta name="Keywords"
  content="shopping, online shopping,
		grocery shopping and delivery, online grocery shopping, grocery shopping services,home delivery,
		grocery delivery, delivery services, grocery, quality food,
		food delivery,giant food delivery, Gopher, gopher groceries, Gopher-Groceries"
/>

<meta name="Description"
  content="Gopher-Groceries: online grocery shopping and delivery to the Big Cottonwood Canyon resorts!"
/>

<!-- ACCORDION -->
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css"
  rel="stylesheet" type="text/css"
/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<link href="resources/css/accordion.css" type="text/css" rel="stylesheet" />
<!-- OTHER CSS REQUIRED -->
<link href='//fonts.googleapis.com/css?family=Oswald&amp;v1' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Maven+Pro&v2' rel='stylesheet' type='text/css'>
<link href="resources/css/styles.css" type="text/css" rel="stylesheet" />
<link href="resources/css/results.css" type="text/css" rel="stylesheet" />
<link href="resources/css/productbox.css" type="text/css" rel="stylesheet" />
<link href="resources/FONTS/GG-Logo-BW.png" type="image/png" rel="icon" />

<!-- JTIPPED  -->
<script src="resources/js/tipped.js" type="text/javascript"></script>
<link href="resources/css/tipped.css" type="text/css" rel="stylesheet" />

</head>
<body>

  <div class="full_wrap full_wrap-back">
    <div id="header" class="wide_wrap">
      <div class="cart" style="float: right">
        <div class="moduletable">
          <div style="float: left; color: #41BECF; margin-left: 28px">
            <div class="vmCartModule">
              Now in your cart <a style="text-decoration: none" href="NotYetImplemented.html"><b>0
                  Items</b></a>
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
        <li><a href="NotYetImplemented.html">Online store</a></li>
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
                <form class="addtocart" action="/site/v1/addtocart" method="post"
                  accept-charset="utf-8"
                >
                  <div class="hiddenCartDetails">
                    <input type="hidden" name="cartkey" value="CartKey" /> <input type="hidden"
                      name="id" value='${popularProducts.id}'
                    /> <input type="hidden" name="sku" value='${popularProducts.sku}' />
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
			// 	$("#accordion > li > div").click(function() {
			$(".category").click(function() {
				console.log("cat clicked")

				if (false == $(this).next().is(':visible')) {
					$('#accordion ul').slideUp(200);
				}
				$(this).next().slideToggle(200);
			});

			$(".subcategory li").click(
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
						$.ajax({
							url : "v1/products/category/"
									.concat(selectedSubCategory),
							type : "GET",
							dataType : "html",
							success : function(data) {
								$('.results').replaceWith(
										$('.results').html(data));
							},
							error : function(xhr, status) {
								alert("Sorry, there was a problem!");
							}
						});
					});
			//Intercept Form Submission
			$(".addtocart").submit(function(event) {
				event.preventDefault();
				// Get the submit button element
				var inputform = $(event.target);
				var data = inputform.serialize();
				console.log(data);
				var id = $("[name='id']",inputform).val();
				var sku = $("[name='sku']",inputform).val();
				var quantity = $("[name='quantity']",inputform).val();
				var cartkey = $("[name='cartkey']",inputform).val();
				console.log("ID:",id);
				console.log("SKU:",sku);
				console.log("Quantity:",quantity);
				console.log("Cart Key:",cartkey);
			});
		</script>
</body>
</html>