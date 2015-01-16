$(function(){
    var whatDevice = device.mobile() || device.tablet();

var smallLink = '<link rel="stylesheet"  href="../resources/css/weatherSmall.css" />';
var bigLink = '<link rel="stylesheet" href="../resources/css/weatherBig.css" />';
    var weatherFunction = function() {
        if (whatDevice && (screen.width <= 1300) && (screen.height <= 1000)) {
            $("head").append(smallLink);
            $('#main').load("weatherForecast.html");
        } else {
            $("head").append(bigLink);
            $('#main').load("weatherForecastDesk.html");

        }
    };
    weatherFunction();

    $(window).resize(function(){
        if (whatDevice && (screen.width <= 1300) && (screen.height <= 1000)) {
            $("head").append(smallLink);
            $('#main').load("weatherForecast.html");
        } else {
            $("head").append(bigLink);
            $('#main').load("weatherForecastDesk.html");

        }
    });

    $(window).resize();

});
