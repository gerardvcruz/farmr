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
        window.location='supplier.html';
      else
        window.location='buyer.html';
     },
    error: function (request, textStatus, errorThrown) {
      alert("Invalid Login");
    }
  });
});
