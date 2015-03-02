$(function(){
    var errorCode = {
        '400':'There is a problem with your request.',
        '401':'Not authorized.',
        '403':'Forbidden: Access is denied',
        '404':'Page does not exist',
        '500':'Server Error. We are working on a fix now.'
    };


        $.ajax({
            type: 'get',
            url: '/rest/user/authenticate',
            crossDomain: true,
            statusCode: {
                400: function () {
                    $('.cover-heading').html("400");
                    $('.inner p').html(errorCode['400']);
                },
                401: function () {
                    $('.cover-heading').html("401");
                    $('.inner p').html(errorCode['400']);
                },
                403: function () {
                    $('.cover-heading').html("403");
                    $('.inner p').html(errorCode['403']);
                },
                404: function () {
                    $('.cover-heading').html("404");
                    $('.inner p').html(errorCode['404']);
                },
                500: function () {
                    $('.cover-heading').html("500");
                    $('.inner p').html(errorCode['500']);
                }
            }
        });

});
