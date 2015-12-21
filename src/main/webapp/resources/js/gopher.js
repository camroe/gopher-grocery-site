/**
 * 
 */
      //**********************************************************************
      //Intercept the Login/Logout nav bar selection
      //**********************************************************************
      $(document).on('click', "#loginlogoutlabel", (function(event) {
        event.preventDefault();
        selection = $(event.target);
        label = $(selection).text();
        //just toggle for now.
        if (label === "Login") {
          $(selection).html("Logout");
        } else {
          $(selection).html("Login");
        }
      }));
      
      function canIReadIt(jsonObject) {
        if (jsonObject === null) {
          console.log("JSON object is null");
        } else {
          console.log("Error: " + jsonObject.error);
          console.log("Error Msg: " + jsonObject.errorMsg);
          var os = jsonObject.orderSummary;
          console.log("OS-NumberOfItems: " + os.numberOfItems);
          console.log("OS-Total: $" + os.total);
          var order = os.order;
          var oe = order.orderEntity;
          console.log("UserName: " + oe.username);
          console.log("SessionID: " + oe.sessionID);
          console.log("EMAIL: " + oe.email);
          console.log("ConfirmationID: " + oe.confirmationID);
          console.log("CreationDate: " + oe.creationdate);
          console.log("PayPalNumber: " + oe.paypalnumber);
          console.log("OrderLines: " + oe.orderlines);
          console.log("-----------------------------------------");
          $.each(oe.orderlines, function(i, ole) {
            console.log("     Product: " + ole.product.name);
            console.log("     Quantity: " + ole.quantity);
            console.log("-----------------------------------------");

          });
        }
      }
