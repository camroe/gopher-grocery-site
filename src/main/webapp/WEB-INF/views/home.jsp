<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Gopher-Groceries | Order your Groceries Online Today for
	a GREAT Ski Holiday</title>

<meta name="Keywords"
	content="shopping, online shopping,
		grocery shopping and delivery, online grocery shopping, grocery shopping services,home delivery,
		grocery delivery, delivery services, grocery, quality food,
		food delivery,giant food delivery, Gopher, gopher groceries, Gopher-Groceries" />

<meta name="Description"
	content="Gopher-Groceries: online grocery shopping and delivery to the Big Cottonwood Canyon resorts!  " />
<!-- ACCORDION -->
<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<link href="resources/css/accordion.css" type="text/css"
	rel="stylesheet" />
<!-- OTHER CSS REQUIRED -->
<link href='//fonts.googleapis.com/css?family=Oswald&amp;v1'
	rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Maven+Pro&v2'
	rel='stylesheet' type='text/css'>
<link href="resources/css/styles.css" type="text/css" rel="stylesheet" />
<link href="resources/css/results.css" type="text/css" rel="stylesheet" />
<!--<link href="resources/css/template.css" type="text/css" rel="stylesheet" />
		<link href="resources/css/style1816.css" rel="stylesheet"
		type="text/css" />
		<link href="resources/css/jquery/style.css" rel="stylesheet"
		type="text/css" />
		<link
		href="resources/css/jquery/themes/smoothness/jquery-ui-1.8.16.custom.css"
		rel="stylesheet" type="text/css" />
		<link type="text/css" href="resources/js/colorbox.css" rel="stylesheet" />
		-->
</head>
<body>

	<div class="full_wrap full_wrap-back">
		<div id="header" class="wide_wrap">
			<div class="cart" style="float: right">
				<div class="moduletable">
					<div style="float: left; color: #41BECF; margin-left: 28px">
						<div class="vmCartModule">
							Now in your cart <a style="text-decoration: none"
								href="NotYetImplemented.html"><b>0 Items</b></a>
						</div>
					</div>
				</div>
			</div>
			<div id="search_form" class="right">
				<form action="NotYetImplmented.html" method="post"
					accept-charset="utf-8">
					<input type="text" name="term"
						placeholder="Enter search Keywords here"
						onkeyup="searchProducts(this.value)" autocomplete="off" />
					<button style="border-radius: 0px" type="submit">Search</button>
				</form>
			</div>
			<a href="NotYetImplemented.html"> <img class="logo-marginn"
				src="resources/images/gopherNoSkis.jpg"
				alt="Gopher-Groceries | Order your Groceries Online Today - Great Ski Holidays"
				width="100px">
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
	Body is here

	<div class="full_wrap ">
		full_wrap is here
		<div class="wide_wrap">
			class = wide_wrap
			<div style="float: right">Float:Right within wide_wrap</div>
			<div>
				<ul id="accordion">
					<li>
						<div class="category">First Catalog Header</div>
						<ul class=subcategory>
							<li id="First-One"><a href=#>1st item</a></li>
							<li id="First-Two"><a href=#>2nd item</a></li>
							<li id="First-Three"><a href=#>3rd item</a></li>
						</ul>
					</li>
					<li>
						<div class="category">Second Catalog Header</div>
						<ul class=subcategory>
							<li id="Second-One"><a href=#>1st item</a></li>
							<li id="Second-Two"><a href=#>2nd item</a></li>
							<li id="Second-Three"><a href=#>3rd item</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<div class="results">
				Here is the start of Results.
				<div style="float: right">Results float right</div>
				Multiple lines Multiple lines
				<ul>
					<li>List Item one</li>
					<li>List Item Two</li>
					<li>List Item Three</li>
					<li>List Item one</li>
					<li>List Item one</li>
					<li>List Item one</li>
					<li>List Item one</li>
					<li>List Item one</li>
					<li>List Item one</li>
					<li>List Item one</li>
					<li>List Item one</li>

				</ul>
			</div>
		</div>
	</div>

	<c:forEach items="${catMap}" var="category">
		
		MajorCategory = ${category.displayName}, urlAddress = ${category.urlAddress}</br>
		<c:forEach items="${category.subCategories}" var="subCategory">
			SubCategory = ${subCategory.displayName}, idName=${subCategory.idName}</br>
		</c:forEach>
	</c:forEach>


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

		$(".subcategory li").click(function() {
			console.log("subcat clicked");
			var target = $(event.target);
			var origin = (target.context.origin);//http://localhost:8080
			var pathname = (target.context.pathname);
			var selectedSubCategory = target.context.innerText;
			console.log(selectedSubCategory);
			var callURL = origin.concat(pathname.concat(selectedSubCategory));
			console.log(callURL);
			// 			console.log(target);
		});
	</script>
</body>
</html>