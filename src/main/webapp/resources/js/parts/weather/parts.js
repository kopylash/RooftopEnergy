/**
 * Created by alex on 12/23/14.
 */
$(function() {
    const WEATHER_ICONS = {
        sunny : '<ul><li class="icon-sun"></li></ul>',
        moon : '<ul><li class="icon-moon"></li></ul>',
        fewCloudsDay : '<ul><li class="basecloud"></li><li class="icon-sunny"></li></ul>',
        fewCloudsNight : '<ul><li class="basecloud"></li><li class="icon-night"></li></ul>',
        scatteredClouds : '<ul><li class="cloud-plus"></li></ul>',
        brokenClouds : '<ul><li class="icon-cloud-dark"></li></ul>',
        showerRain : '<ul><li class="basecloud"></li><li class="icon-showers"></li></ul>',
        rainDay : '<ul><li class="basecloud"></li><li class="icon-showers icon-sunny"></li></ul>',
        rainNight : '<ul><li class="basecloud"></li><li class="icon-showers icon-night"></li></ul>',
        thunderstorm : '<ul><li class="basecloud"></li><li class="icon-thunder"></li></ul>',
        snow : '<ul><li class="basecloud"></li><li class="icon-snowy"></li></ul>',
        mist : '<ul><li class="icon-mist"></li></ul>'
    };

    function ajaxUserInfoQuery(){
        $.ajax({
            type: 'get',
            url: "/rest/boxData/getUserDescription",
            crossDomain: true,
            //data: {'city': city, 'days': numberOfDays},
            error: function (info) {
                $('#main').html(info.responseText);
            },
            statusCode: {
                200: function (info) {
                    userInfo(info);
                }
            }
        });
    }

    function ajaxForecastQuery(numberOfDays) {
        $.ajax({
            type: 'post',
            url: "/rest/weather/daily",
            crossDomain: true,
            data: {'days': numberOfDays},
            error: function (data) {
                $('#main').html(data.responseText);
            },
            statusCode: {
                200: function (data) {
                    forecastListSixteen(data);
                }
            }
        });
    }
    var userInfo = function(info){
        $("#generalHeader").html(info.city);
    };

//////////////BEGIN - TEST DATA////////////////////////////////////////////////////////////////////

    /* mock for info about weather */

    var TempUserInfo = function(city){
        this.city = city;
    };
    var info = new TempUserInfo("Kiev");
 /* end mock info about weather */

 /* mock for weather forecast list*/
    var testFunction = function(number) {
        var data = new Array();
        var TempWeatherObj = function (dt, skyIcon, skyDescription, tempNight, tempDay, wind, clouds, pressure) {
            this.dt = dt;
            this.skyIcon = skyIcon;
            this.tempNight = tempNight;
            this.tempDay = tempDay;
            this.wind = wind;
            this.clouds = clouds;
            this.pressure = pressure;
            this.skyDescription = skyDescription;
        };
        for (var i = 0; i<number; i++) {
            data[i] = new TempWeatherObj(1419325200000, '10d', 'moderate rain fdfd', ' -36.6200000000000045', ' -38.350000000000023', '20.21', '0', "1000.96");
        }
        forecastListSixteen(data);
    };
/* end mock for weather forecast list*/
//////////////END - TEST DATA////////////////////////////////////////////////////////////////////

    var forecastListSixteen = function (data) {

        var list = function () {

            $("#weatherContent").html("");

            for (var i = 0; i < data.length; i++) {
                fillLine(data, i);
                $("#line"+(data.length-1)).css({'border':'none'});
            }

            switch(data.length){
                case(3):
                    $("#general").css({'height': '455px'});
                    break;
                case(7):
                    $("#general").css({'height': '1000px'});
                    break;
                case(14):
                    $("#general").css({'height': '1955px'});
            }

        };
        list();
    };

    var buttonStyles = function(buttId){
        $("#weatherPeriod button").css({'background-color': '#108F38', 'color': '#fffffe'});
        $("#"+buttId).css({'background-color': '#fffff0', 'color': '#108F38'});
    };

    $("#threeDays").click(function(){
        buttonStyles(this.id);
        ajaxForecastQuery(3);
       //testFunction(3);
    });
    $("#sevenDays").click(function(){
        buttonStyles(this.id);
        ajaxForecastQuery(7);
       //testFunction(7);
    });
    $("#fourteenDays").click(function(){
        buttonStyles(this.id);
        ajaxForecastQuery(14);
       //testFunction(14);
    });


    //userInfo(info);
    //testFunction(3);
    ajaxUserInfoQuery();
    ajaxForecastQuery(3);


    function fillTemperature(pic, temper) {
        var roundTemper = Math.round(temper);
        return pic + " " + roundTemper + " <span>&ordmC</span>";
    }

    function fillLine(data, index){
        const MONTH_NAMES = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

        const DAY_OF_WEEK = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
        const WEATHER_PICTURE = {
            '01d' : WEATHER_ICONS.sunny,
            '01n' : WEATHER_ICONS.moon,
            '02d' : WEATHER_ICONS.fewCloudsDay,
            '02n' : WEATHER_ICONS.fewCloudsDay,
            '03d' : WEATHER_ICONS.scatteredClouds,
            '03n' : WEATHER_ICONS.scatteredClouds,
            '04n' : WEATHER_ICONS.brokenClouds,
            '04d' : WEATHER_ICONS.brokenClouds,
            '09d' : WEATHER_ICONS.showerRain,
            '09n' : WEATHER_ICONS.showerRain,
            '10d' : WEATHER_ICONS.rainDay,
            '10n' : WEATHER_ICONS.rainNight,
            '11d' : WEATHER_ICONS.thunderstorm,
            '11n' : WEATHER_ICONS.thunderstorm,
            '13d' : WEATHER_ICONS.snow,
            '13n' : WEATHER_ICONS.snow,
            '50d' : WEATHER_ICONS.mist,
            '50n' : WEATHER_ICONS.mist
        };
        var note;
        var date, weatherImage, description, nightTemperature, dayTemperature, wind, sunshine, pressure;

        date = new Date(data[index].dt);
        //"<div class='lineIco' id='ico" + i + "'>" + weatherImage + "</div>" +
        //weatherImage = "<img src='resources/images/weather/" + data[i].skyIcon + ".png'/>";
        weatherImage = WEATHER_PICTURE[data[index].skyIcon];
        nightTemperature = data[index].tempNight;
        dayTemperature = data[index].tempDay;
        wind = data[index].wind;
        sunshine = 100 - data[index].clouds;
        pressure = data[index].pressure;
        description = data[index].skyDescription;


        note = "<div class='contentLine' id='line" + index + "'>" +
        "<div class='lineDate' id='date" + index + "'>" +
        "<div class='dayOfWeek' id='day" + index+ "'></div>"+
        "<div class='shortDate' id='shortDate" + index + "'></div>" +
        "</div>"+
        "<div class='temperature' id='temperature"+index+"'>"+
        "<div class='lineNightTemp' id='nTemp" + index + "'></div>" +
        "<div class='lineDayTemp' id='dTemp" + index + "'></div>" +
        "</div>"+
        "<div class='wCondition' id='wCondition"+index+"'>"+
        "<div class='lineIco' id='ico" + index + "'>" + weatherImage + "</div>"+
        "<div class='description' id='wDescription"+index+"'>"+description+"</div>"+
        "</div>"+
        "<div class='lineWind' id='wind" + index + "'></div>" +
        "<div class='lineSun' id='sun" + index + "'></div>" +
        "<div class='linePressure' id='press" + index + "'></div>" +


        "</div>";

        $("#weatherContent").append(note);
        $("#nTemp" + index).html(fillTemperature("<i class='fa fa-moon-o'></i>", nightTemperature));
        $("#dTemp" + index).html(fillTemperature("<i class='fa fa-sun-o'></i>", dayTemperature));
        $("#day" + index).html(function(){
            return DAY_OF_WEEK[date.getDay()];
        });
        $("#shortDate" + index).html(function () {
            return date.getDate() + " " + MONTH_NAMES[date.getMonth()];
        });
        $("#wind" + index).html(function () {
            return "<i class='fa fa-long-arrow-right'></i> " + Math.round(wind) + "<span> m/s</span>";
        });
        $("#sun" + index).html("<i class='fa fa-sun-o'></i> " + sunshine + "<span> %</span>");
        $("#press" + index).html(function(){
            var press = new Number(pressure);
            return press.toFixed(1) + "<span> Hpa</span>"
        });
    }

});