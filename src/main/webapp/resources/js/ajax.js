function ajaxGraphQuery(strUrl,endDate, tooltipDateFormat) {
    var code = Object.create(errorCode);
    code['200'] = function(data){
        graph(data, tooltipDateFormat);
        applianceSingleData(data, strUrl);
        $("#radiation").show();
    };
    code['500'] = function(data){
        $('#main').html("<h3 style='text-align: center'>500. Service Unavailable!</h3>");
        console.error(data.responseText);
    };
    $.ajax({
        type: 'post',
        url: strUrl,
        crossDomain: true,
        data: { 'date': endDate.getTime()},
     /*   error: function (data) {
            $('#main').html("<h3 style='text-align: center'>Service Unavailable!</h3>");
            console.error(data.responseText);
        },*/
        /*statusCode: {
            200: function (data) {
                graph(data, tooltipDateFormat);
                applianceSingleData(data, strUrl);
            }
        }*/
        statusCode:code
    });
}