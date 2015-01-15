$(function(){
    currentPage.name = 'production';
    //var productionPeriodUrl = '/rest/production/production_period';
    var productionPeriodUrl = '/rest/production';
    allDateButtons(productionPeriodUrl);

    var isDevice = device.tablet();
    var isAndroid =  device.androidTablet();
    alert(isDevice + ' || ' + isAndroid);





});

//$.ajax({
//    url: url,
//    dataType: 'json',
//    data: data,
//    success: callback
//});
