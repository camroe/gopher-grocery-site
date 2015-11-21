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
