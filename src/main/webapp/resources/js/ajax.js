function ajaxGraphQuery(strUrl,endDate, tooltipDateFormat) {
    $.ajax({
        type: 'post',
        url: strUrl,
        crossDomain: true,
        data: { 'date': endDate.getTime()},
        error: function (data) {
            $('#main').html(data.responseText);
        },
        statusCode: {
            200: function (data) {
                graph(data, tooltipDateFormat);
                applianceSingleData(data, strUrl);
            },
            400: function () {
                window.location = "/error.html?code=400";
            },
            401: function () {
                window.location = "/error.html?code=401";
            },
            403: function () {
                window.location = "/error.html?code=403";
            },
            404: function () {
                window.location = "/error.html?code=404";
            },
            500: function () {
                window.location = "/error.html?code=500";
            }
        }
    });
}