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

<div class="productHeaderContainer">
  <span class="productHeaderLabel">${subCategoryName}</span>
</div>
<br>
<c:forEach
  items="${selectedProducts}"
  var="selectedProducts"
>
  <div class="productbox">
    <div
      class="productImageContainer simple-tooltip"
      title='${selectedProducts.description}'
    >
      <img src='${selectedProducts.imagefile}' />
    </div>
    <div class="productTitle">${selectedProducts.name}</div>
    <div class="productDetailsContainer">
      <div class="priceContainer">
        <span class="priceLabel">Price </span>
        <span class="priceAmount">$${selectedProducts.price}</span>
      </div>
      <div class="productClear"></div>
      <div class="addToCartContainer">
        <form
          class="addtocart"
          action="/v1/addtocart"
          method="post"
          accept-charset="utf-8"
        >
          <div class="hiddenCartDetails">
            <input
              type="hidden"
              name="cartkey"
              value="CartKey"
            />
            <input
              type="hidden"
              name="id"
              value='${selectedProducts.id}'
            />
            <input
              type="hidden"
              name="sku"
              value='${selectedProducts.sku}'
            />
            <input
              type="hidden"
              name="${_csrf.parameterName}"
              value="${_csrf.token}"
            >
          </div>
          <div class="productQuantityContainer">
            <span class=quantityLabel>Quantity </span>
            <input
              class="productQuantityBox"
              type="text"
              name="quantity"
              value="1"
            />
          </div>
          <div class="addToCartButtonContainer">
            <input
              class="addtocartButton"
              type="submit"
              value="Add to Cart"
            />

            <div class="checkout">
              <a href="/v1/orderAPI/orders"> Checkout</a>
            </div>

          </div>
        </form>


      </div>
    </div>
    <div class="pricingSeperator"></div>
  </div>
  <div class="productClear"></div>
</c:forEach>
