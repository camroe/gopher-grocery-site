<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="security"
  uri="http://www.springframework.org/security/tags"%>
<%@ include file="includes/header.jsp"%>


<body>
  <input type="hidden" id="csrf-token" name="${_csrf.parameterName}"
    value="${_csrf.token}" />

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

  <%@ include file="includes/mainNavBar.jsp"%>

  <div id="delivery" class="delivery" style="display: none"></div>

  <div class="full_wrap ">
    <div class="wide_wrap">
      <div id="delivery" class="delivery">
        <div class="deliveryHeaderContainer">
          <span class="deliveryHeaderLabel simple-tooltip"
            title="Where to deliver the groceries.This will almost always be the condominium/cabin where you will be staying for your holidays.This is NOT your homeaddress.">Delivery
            Information</span>
        </div>
        <form id="deliveryForm" action="delivery" method="post">
          <!-- Include csrf in form submission -->
          <input type="hidden" id="csrf-token"
            name="${_csrf.parameterName}" value="${_csrf.token}" />
          <fieldset>

            <table id="errorLocation">
              <tr>
                <td colspan="2">
                  <h2 style="border-bottom: 1px solid #CCCCCC;">Deliver
                    TO Information</h2>
                </td>
              </tr>
              <tr>
                <td>
                <td>
              </tr>

              <tr>
                <td class="label"><label for="first_name">First
                    Name:</label></td>
                <td><input id="first_name" name="first_name"
                    size="20" type="text" tabindex="1" value=""
                    placeholder="Your First Name" data-val="true"
                    data-val-required="Please enter a valid name. This field is required"
                    data-val-length="Please use at least 3 characters"
                    data-val-length-min="3"><span
                  data-valmsg-replace="true"
                  data-valmsg-for="first_name"></span></td>
              </tr>
              <tr>
                <td class="label"><label for="last_name">Last
                    Name:</label></td>
                <td><input id="last_name" name="last_name"
                    size="20" type="text" tabindex="2" value=""
                    placeholder="Your Last Name" data-val="true"
                    data-val-required="Please enter a valid name. This field is required"
                    data-val-length="Please use at least 3 characters"
                    data-val-length-min="3"><span
                  data-valmsg-replace="true" data-valmsg-for="last_name"></span></td>
              </tr>
              <tr>
                <td class="label"><label for="location">Delivery
                    Address:</label></td>
                <td><select id="location" name="location"
                  tabindex="3">
                    <option value="NS" selected>Choose Solitude
                      Location:</option>
                    <option value="Eagle Springs East">Eagle
                      Springs East</option>
                    <option value="Eagle Spings West">Eagle
                      Springs West</option>
                    <option value="Powderhorn Lodge">Powderhorn
                      Lodge</option>
                    <option value="Crossings">Crossings</option>
                    <option value="Creekside">Creekside</option>
                    <option value="NA">The address of where I'm
                      staying isn't in the list! I'm using the comments
                      to give the address</option>
                </select></td>
              </tr>
              <tr>
                <td class="label"><label for="unit">Unit
                    Number</label></td>
                <td><input id="unit" name="unit" size="20"
                    type="text" tabindex="4" placeholder="1234" value=""
                    data-val="true"
                    data-val-number="Unit must be a just a number with no special characters "
                    data-val-required="Please enter a valid unit number. This field is required"
                    data-val-length="Please use between 1 to 4 digits"
                    data-val-length-min="1" data-val-length-max="4"><span
                  data-valmsg-replace="true" data-valmsg-for="unit"></span></td>
              </tr>

              <!--               <tr> -->
              <!--                 <td class="label"><label for="address1">Delivery -->
              <!--                     Address:</label></td> -->
              <!--                 <td><input id="address1" name="address1" size="30" -->
              <!--                     type="text" tabindex="3" value=""></td> -->
              <!--               </tr> -->
              <!--               <tr> -->
              <!--                 <td class="label"></td> -->
              <!--                 <td><input maxlength="40" name="address2" size="30" -->
              <!--                     type="text" tabindex="4" value=""></td> -->
              <!--               </tr> -->


              <tr>
                <td class="label"><label for="city">City:</label></td>
                <td>Salt Lake City</td>
              </tr>
              <tr>
                <td class="label"><label for="state">State:</label></td>
                <td>Utah</td>
              </tr>
              <tr>
                <td class="label"><label for="zip">Zip:</label></td>
                <td>84121</td>
              </tr>
              <tr>
                <td colspan="2">
                  <h2 style="border-bottom: 1px solid #CCCCCC;">Contact
                    Information</h2>
                </td>
              </tr>

              <!-- ^(\+\d{1,2}\s)?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$ -->

              <tr>
                <td class="label"><label for="phone">Phone:</label></td>
                <td><input id="phone" name="phone" type="tel"
                    tabindex="5" value="" placeholder="(___) ___-____"
                    data-val="true"
                    data-val-required="Please enter a valid telephone number. This field is required"
                    data-val-length="A phone number must be at least 10 characters"
                    data-val-length-min="10"
                    data-val-regex="This does not match any known pattern for a telephone number. Try just the 10 digits."
                    data-val-regex-pattern="^(\+\d{1,2}\s)?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$"><span
                  data-valmsg-replace="true" data-valmsg-for="phone"></span></td>
              </tr>
              <tr>
                <td class="label"><label for="email">Email:</label></td>
                <td><input id="email" name="email" size="20"
                    type="email" tabindex="6" value=""
                    placeholder="name@domain.com" data-val="true"
                    data-val-required="Please enter a valid email. This field is required"
                    data-val-length="An email address must be at least 5 characters"
                    data-val-length-min="5"
                    data-val-email="This does not match any known pattern for an email address."><span
                  data-valmsg-replace="true" data-valmsg-for="email"></span></td>
              </tr>

              <tr>
                <td class="label"><label for="emailRetype">Retype
                    Email:</label></td>
                <td><input id="emailRetype" name="emailRetype"
                    size="20" type="email" tabindex="7" value=""
                    placeholder="retype your email address"
                    data-val="true"
                    data-val-equalto="Retyped Email does not match"
                    data-val-equalto-other="email"
                    data-val-required="Please enter a valid email. This field is required"
                    data-val-length="An email address must be at least 5 characters"
                    data-val-length-min="5"
                    data-val-email="This does not match any known pattern for an email address."><span
                  data-valmsg-replace="true"
                  data-valmsg-for="emailRetype"></span></td>
              </tr>


              <tr>
                <td class="label"><label for="comment">Comment:</label></td>
                <td><textarea rows="5" cols="60" id="comment"
                    name=comment tabindex="7"
                    placeholder="Comments - Further information - Special instructions or Requests"></textarea></td>
              </tr>


              <tr>
                <td></td>
                <td>
                  <div class="buttonSubmit">
                    <span></span>
                    <input class="nextButton" type="submit" value="Next"
                      tabindex="7">
                    <label class="buttonLabel">When you are
                      ready to submit your delivery information and
                      review your complete order, just click 'Next'.</label>
                  </div>
                </td>
              </tr>
            </table>
            <br> <br>
          </fieldset>
        </form>
        <br clear="all">
      </div>
    </div>
  </div>


  <!--  Javascript  -->
  <script type="text/javascript">
      $(document).ready(

          function() {

            /**
            Function to allow the removal of javascript and css files from the page. This allows us to redefine css labels
            and classes for common elements such as tables.
             */
            function removejscssfile(filename, filetype) {
              var targetelement = (filetype == "js") ? "script"
                  : (filetype == "css") ? "link" : "none" //determine element type to create nodelist from
              var targetattr = (filetype == "js") ? "src"
                  : (filetype == "css") ? "href" : "none" //determine corresponding attribute to test for
              var allsuspects = document.getElementsByTagName(targetelement)
              for (var i = allsuspects.length; i >= 0; i--) { //search backwards within nodelist for matching elements to remove
                if (allsuspects[i]
                    && allsuspects[i].getAttribute(targetattr) != null
                    && allsuspects[i].getAttribute(targetattr)
                        .indexOf(filename) != -1)
                  allsuspects[i].parentNode.removeChild(allsuspects[i]); //remove element by calling parentNode.removeChild()
              }
            }

            //             removejscssfile("somescript.js", "js"); //remove all occurences of "somescript.js" on page
            //             removejscssfile("somestyle.css", "css"); //remove all occurences "somestyle.css" on page
            removejscssfile("order.css", "css");
            //************************************************************************
            //        VALIDATION FUNCTIONS
            //************************************************************************

            //Intercept the form submission
            //                 $('myform').submit(function() {
            //                   return false;
            //                 });

            //Intercept the form submission
            //                                 $.validator.setDefaults({
            //                                   submitHandler : function() {
            //                                     alert("submitted!");
            //                                   }
            //                                 });

            //Catch the formsubmission on the enter key
            $('#deliveryForm').on('keyup keypress', function(event) {
              var code = event.keyCode || event.which;
              if (code == 13) {
                event.preventDefault();
                return false;
              }
            });

            //             $("input[type='submit']").click(function(event) {
            //               event.preventDefault(); // Do not actually submit
            //               alert("You entered " + $("#first_name").val());
            //             });

            //             validate signup form on keyup and submit
            //                             $("#deliveryForm")
            //                                 .validate(
            //                                     {
            //                                       //turn off the dynamic checking
            //                                       onkeyup: false,
            //                                       onclick: false,
            //                                       onfocusout: false,
            //                                       rules : {
            //                                         first_name : {
            //                                           required : true,
            //                                           minlength : 3,
            //                                           maxlenght : 40
            //                                         },
            //                                         last_name : {
            //                                           required : true,
            //                                           minlength : 3,
            //                                           maxlenght : 40
            //                                         },
            //                                         address1 : {
            //                                           required : true
            //                                         },
            //                                         address2 : {
            //                                         //None
            //                                         },
            //                                         address : {
            //                                           required : true
            //                                         },
            //                                         unit : {
            //                                           required : true,
            //                                           maxlength : 4,
            //                                           number : true
            //                                         },
            //                                         phone : {
            //                                           maxlength : 14,
            //                                           phone : true,
            //                                           required : true
            //                                         },
            //                                         email : {
            //                                           required : true,
            //                                           email : true,
            //                                           maxlength : 50
            //                                         },
            //                                         comments : {
            //                                           maxlength : 500
            //                                         }
            //                                       },
            //                                       messages : {
            //                                         first_name : {
            //                                           required : "First Name is required, Please enter your firstname",
            //                                           minlength : "First Name must be 3 or more characters",
            //                                           maxlength : "First Name can be a maximum of 40 characters "
            //                                         },
            //                                         last_name : {
            //                                           required : "Last Name is required, Please enter your firstname",
            //                                           minlength : "Last Name must be 3 or more characters",
            //                                           maxlength : "Last Name can be a maximum of 40 characters "
            //                                         },
            // //                                         address1 : {
            // //                                           required : "We must have an address to deliver to. If in doubt, just put the name of the condo unit. Eg.Eagle Springs East, #208"
            // //                                         },
            //                                         address : {
            //                                           required : "Location Required. "
            //                                         },
            //                                         unit : {
            //                                           required : "Unit is required. You can always use the notes to inform us about special circumstances.",
            //                                           maxlength : "Unit number can only be 4 digits long",
            //                                           number : "Unit number can only have digits"
            //                                         },
            //                                         phone : {
            //                                           maxlength : "Phone number is too long. 14 numbers max",
            //                                           phone : "Phone number must follow a phone number format. Eg. (xxx)-xxx-xxxx",
            //                                           required : "Phone number is required"
            //                                         },
            //                                         email : {
            //                                           maxlength : "Email is too long. 50 characters max",
            //                                           phone : "Email must follow an Email format. Eg. username@domain.com",
            //                                           required : "Email is required"
            //                                         },
            //                                         comments : {
            //                                           maxlength : "Comments too long, please keep to under 500 characters"
            //                                         }
            //                                       },
            //                                     //                           onkeyup: function(element) { $(element).valid(); },
            //                                     //                           onfocusout: function(element) { $(element).valid(); }
            //                                     });

            //                 $("input").focus(function(event) {
            //                   var target = event.target;
            //                   $(this).next("label").remove();
            //                   $(this).removeClass("error");
            //                   console.log(this);
            //                   console.log(target);
            //                 });

          });//on document ready
    </script>

</body>
</html>