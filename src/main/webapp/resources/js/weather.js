$(function(){
    var whatDevice = device.mobile() || device.tablet();
    var landscape = (screen.width <= 1300) && (screen.height <= 1000);
    var portrait = (screen.width <= 1000) && (screen.height <= 1300);

    var smallLink = '<link rel="stylesheet"  href="../resources/css/weatherSmall.css" />';
var bigLink = '<link rel="stylesheet" href="../resources/css/weatherBig.css" />';
    var weatherFunction = function() {
        if (whatDevice || landscape || portrait) {
            $("head").append(smallLink);
            $('#main').load("weatherForecast.html");
        } else {
            $("head").append(bigLink);
            $('#main').load("weatherForecastDesk.html");

        }
    };
    weatherFunction();



});
