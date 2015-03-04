$(function(){
    currentPage.name = 'production';
    var productionPeriodUrl = '/rest/production';
    allDateButtons(productionPeriodUrl);

    $("#radiation").click(function(){
        window.location = "radiationPage.html";
    });
});
