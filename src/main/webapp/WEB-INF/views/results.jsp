<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<base href="" />
<div class="productHeaderContainer">
  <span class="productHeaderLabel">${subCategoryName}</span>
</div>
<br>
<c:forEach items="${selectedProducts}" var="selectedProducts">
  <div class="productbox">
    <div class="productImageContainer simple-tooltip" title='${selectedProducts.description}'>
      <img src='${selectedProducts.imageFile}' />
    </div>
    <div class="productTitle">${selectedProducts.name}</div>
    <div class="productDetailsContainer">
      <div class="priceContainer">
        <span class="priceLabel">Price </span> <span class="priceAmount">$${selectedProducts.price}</span>
      </div>
      <div class="productClear"></div>
      <div class="addToCartContainer">
        <form action="/site/v1/addtocart" method="post" accept-charset="utf-8">
          <div class="hiddenCartDetails">
            <input type="hidden" name="cartkey" value="" /> <input type="hidden" name="id"
              value='${popularProducts.id}'
            /> <input type="hidden" name="sku" value='${popularProducts.sku}' />
          </div>
          <div class="productQuantityContainer">
            <span class=quantityLabel>Quantity </span> <input class="productQuantityBox" type="text"
              name="quantity" value="1"
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
