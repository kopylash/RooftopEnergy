//var endDate = new Date();
function ajaxGraphQuery(strUrl,startDate,endDate) {
    $.ajax({
        type: 'post',
        url: strUrl,
        crossDomain: true,
        data: { 'currentBox': "1", 'dateStart': startDate.getTime(), 'dateEnd': endDate.getTime()},
        error: function (data) {
            $('#login_message').html(data.responseText);
        },
        statusCode: {
            200: function (data) {
                graph(data);
            }
        }
    });
}