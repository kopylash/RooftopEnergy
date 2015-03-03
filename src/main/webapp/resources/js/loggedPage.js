$(function(){
    currentPage.name = 'production';
    //var productionPeriodUrl = '/rest/production/production_period';
    var productionPeriodUrl = '/rest/production';
    allDateButtons(productionPeriodUrl);

    $("#radiation").click(function(){
        window.location = "radiationPage.html";
    });




});

//$.ajax({
//    url: url,
//    dataType: 'json',
//    data: data,
//    success: callback
//});
