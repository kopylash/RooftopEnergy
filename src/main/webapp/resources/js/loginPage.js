$(function(){
    $('#logForm').submit(function(e){
        var username = $('#login1').val();
        var password = $('#password1').val();

        $.ajax({
            type: 'post',
            url: '/rest/user/authenticate',
            crossDomain: true,
            data: {'username': username, 'password': password},
            error: function (data) {
                $('#login_message').html(data.responseText);
            },
            statusCode: {
                // HTTP 307 - redirect
                307: function (data) {
                    document.location.href = data.responseText;
                }
            }
        });
        return false;
    });
});