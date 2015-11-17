/**
 * 
 */
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
          var origin = (target.context.origin);// http://localhost:8080
          var pathname = (target.context.pathname);
          // var selectedSubCategory = target.context.innerText;
          var selectedSubCategory = $(this).attr('id');
          var callURL = origin.concat(pathname.concat(selectedSubCategory));

          console.log(selectedSubCategory);
          console.log(callURL);
          console.log(target);
          console.log($(this).attr('id'));
          $
              .ajax({
                url : "v1/products/category/".concat(selectedSubCategory),
                type : "GET",
                dataType : "html",
                success : function(data, textStatus) {
                  $('.results').replaceWith($('.results').html(data));
                  console.log("Success", textStatus);
                },
                error : function(xhr, status) {
                  alert("Sorry, there was a problem! I was unable to access this URL. \n This problem has been logged as a problem.");
                }
              });
        });
// Intercept Form Submission
$(".addtocart").submit(function(event) {
  event.preventDefault();
  // Get the submit button element
  var inputform = $(event.target);
  var data = inputform.serialize();
  console.log(data);
  var id = $("[name='id']", inputform).val();
  var sku = $("[name='sku']", inputform).val();
  var quantity = $("[name='quantity']", inputform).val();
  var cartkey = $("[name='cartkey']", inputform).val();
  console.log("ID:", id);
  console.log("SKU:", sku);
  console.log("Quantity:", quantity);
  console.log("Cart Key:", cartkey);

  $.ajax({
    url : "v1/addtocart-fail",
    type : "POST",
    data : data,
    success : function(data, textStatus) {
      console.log("Success", textStatus);
    },
    error : function(xhr, textStatus, erro) {
      alert("Error Returned from 'addtocart'", status);
      // console.log(xhr);
      // console.log(status);
    }
  });
});