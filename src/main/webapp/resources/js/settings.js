$(function(){
    function ajaxGraphQuery(strUrl,endDate) {
        $.ajax({
            type: 'post',
            url: "rest/",
            crossDomain: true,
            data: { 'date': endDate.getTime()},
            error: function (data) {
                $('#main').html(data.responseText);
            },
            statusCode: {
                200: function (data) {
                    graph(data);
                }
            }
        });
    }
})