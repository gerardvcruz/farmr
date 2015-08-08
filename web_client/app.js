$( "#login" ).on( "click",  function() {
  $.ajax({
     type: 'POST',
     url:'http://52.76.32.213:3000/auth/sign_in',
     data: {
      email:$("#username").val(),
      password:$("#password").val()
     },
     success: function(data, status, xhr) {
      if(data.data.user_type)
        window.location='supplier.html?id='+data.data.id;
      else
        window.location='buyer.html?id='+data.data.id;
     },
    error: function (request, textStatus, errorThrown) {
      alert("Invalid Login");
    }
  });
});

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

var id = getUrlParameter('id');
if(id!=undefined)
  alert(id);
