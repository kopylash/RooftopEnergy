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
            }
        }
    });
}