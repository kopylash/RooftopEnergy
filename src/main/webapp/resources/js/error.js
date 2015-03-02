$(function(){
    var errorCode = {
        '400':'There is a problem with your request',
        '401':'Not authorized',
        '403':'Forbidden: Access is denied',
        '404':'Page does not exist',
        '500':'Server Error. We are working on a fix now'
    };



    var paramName = "code";
    var query = window.location.search.substring(1);
    var vars = query;
        var pair = vars.split('=');
          if (decodeURIComponent(pair[0]) == paramName) {
              $('.cover-heading').html(pair[1]);
            $('.inner p').html(errorCode[pair[1]]);
        }
});
