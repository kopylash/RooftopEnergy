$(function(){



    var foo = function() {
        if (screen.width <= 768) {
            $('#main').load("weatherForecast.html");
        } else {
            $('#main').load("weatherForecastDesk.html");

        }
    };
    foo();

    $(window).resize(function(){
        if (screen.width <= 768) {
            $('#main').load("weatherForecast.html");
        } else {
            $('#main').load("weatherForecastDesk.html");

        }
    });

    $(window).resize();

});
