$( "#login" ).on( "click",  function() {
  $.ajax({
     type: 'POST',
     url:'http://52.76.30.161/auth/sign_in',
     data: {
      email:$("#username").val(),
      password:$("#password").val()
     },
     success: function(data, status, xhr) {
      window.localStorage.setItem("current_user", JSON.stringify(data));
      if(data.data.user_type == 1)
        window.location='supplier.html';
      else
        window.location='buyer.html';
     },
    error: function (request, textStatus, errorThrown) {
      alert("Invalid Login");
    }
  });
});

/*If user is logged in*/
var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

var uid = getUrlParameter('uid');
if(uid!=undefined){
  $.ajax({
     type: 'GET',
     url:'http://52.76.30.161/api/v1/users/'+uid,
     success: function(data, status, xhr) {
      $("#username").text(data.name);
     }
  });
}
