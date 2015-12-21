<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%><div>
  <ul id="accordion">
    <c:forEach items="${catMap}" var="mainCategory">
      <li>
        <div class="category">
          <a href='${mainCategory.urladdress}'>${mainCategory.name}</a>
        </div>
        <ul class=subcategory>
          <c:forEach items="${mainCategory.getSubCats()}" var="subCategory">
            <li id='${subCategory.urladdress}'><a href=#>${subCategory.displayname}</a>
          </c:forEach>
        </ul>
      </li>
    </c:forEach>
  </ul>
</div>