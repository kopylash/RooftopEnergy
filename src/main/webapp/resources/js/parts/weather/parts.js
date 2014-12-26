/**
 * Created by alex on 12/23/14.
 */
$(function() {
    var weatherIcons = {
        sunny : '<ul><li class="icon-sun"></li></ul>',
        moon : '<ul><li class="icon-moon"></li></ul>',
        fewCloudsDay : '<ul><li class="cloud-plus"></li><li class="icon-sunny"></li></ul>',
        fewCloudsNight : '<ul><li class="cloud-plus"></li><li class="icon-night"></li></ul>',
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

    //var number = 3;

    var forecastListSixteen = function (data) {

        var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

        var daysArr = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
        var weatherPictures = {
            '01d' : weatherIcons.sunny,
            '01n' : weatherIcons.moon,
            '02d' : weatherIcons.fewCloudsDay,
            '02n' : weatherIcons.fewCloudsDay,
            '03d' : weatherIcons.scatteredClouds,
            '03n' : weatherIcons.scatteredClouds,
            '04n' : weatherIcons.brokenClouds,
            '04d' : weatherIcons.brokenClouds,
            '09d' : weatherIcons.showerRain,
            '09n' : weatherIcons.showerRain,
            '10d' : weatherIcons.rainDay,
            '10n' : weatherIcons.rainNight,
            '11d' : weatherIcons.thunderstorm,
            '11n' : weatherIcons.thunderstorm,
            '13d' : weatherIcons.snow,
            '13n' : weatherIcons.snow,
            '50d' : weatherIcons.mist,
            '50n' : weatherIcons.mist
        };

        var fillTemperature = function (pic, temper) {
            var roundTemper = Math.round(temper);
            return pic + " " + roundTemper + " <span>&ordmC</span>";
        };

        var list = function () {
            var note;
            var date, weatherImage, description, nightTemperature, dayTemperature, wind, sunshine, pressure;

            $("#weatherContent").html("");
            for (var i = 0; i < data.length; i++) {
                date = new Date(data[i].dt);
                //"<div class='lineIco' id='ico" + i + "'>" + weatherImage + "</div>" +
                //weatherImage = "<img src='resources/images/weather/" + data[i].skyIcon + ".png'/>";
                weatherImage = weatherPictures[data[i].skyIcon];
                nightTemperature = data[i].tempNight;
                dayTemperature = data[i].tempDay;
                wind = data[i].wind;
                sunshine = 100 - data[i].clouds;
                pressure = data[i].pressure;
                description = data[i].skyDescription;


                note = "<div class='contentLine' id='line" + i + "'>" +
                            "<div class='lineDate' id='date" + i + "'>" +
                                "<div class='dayOfWeek' id='day" + i+ "'></div>"+
                                "<div class='shortDate' id='shortDate" + i + "'></div>" +
                            "</div>"+
                            "<div class='temperature' id='temperature"+i+"'>"+
                                "<div class='lineNightTemp' id='nTemp" + i + "'></div>" +
                                "<div class='lineDayTemp' id='dTemp" + i + "'></div>" +
                            "</div>"+
                            "<div class='wCondition' id='wCondition"+i+"'>"+
                                "<div class='lineIco' id='ico" + i + "'>" + weatherImage + "</div>"+
                                "<div class='description' id='wDescription"+i+"'>"+description+"</div>"+
                            "</div>"+
                            "<div class='lineWind' id='wind" + i + "'></div>" +
                            "<div class='lineSun' id='sun" + i + "'></div>" +
                            "<div class='linePressure' id='press" + i + "'></div>" +


                        "</div>";

                $("#weatherContent").append(note);
                $("#nTemp" + i).html(fillTemperature("<i class='fa fa-moon-o'></i>", nightTemperature));
                $("#dTemp" + i).html(fillTemperature("<i class='fa fa-sun-o'></i>", dayTemperature));
                $("#day" + i).html(function(){
                    return daysArr[date.getDay()];
                });
                $("#shortDate" + i).html(function () {
                    return date.getDate() + " " + monthNames[date.getMonth()];
                });
                $("#wind" + i).html(function () {
                    return "<i class='fa fa-long-arrow-right'></i> " + Math.round(wind) + "<span> m/s</span>";
                });
                $("#sun" + i).html("<i class='fa fa-sun-o'></i> " + sunshine + "<span> %</span>");
                $("#press" + i).html(function(){
                    var press = new Number(pressure);
                    return press.toFixed(1) + "<span> Hpa</span>"
                });

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

    $("#threeDays").click(function(){
        ajaxForecastQuery(3);
       //testFunction(3);
    });
    $("#sevenDays").click(function(){
        ajaxForecastQuery(7);
       //testFunction(7);
    });
    $("#fourteenDays").click(function(){
        ajaxForecastQuery(14);
       //testFunction(14);
    });


    //userInfo(info);
    //testFunction(3);
    ajaxUserInfoQuery();
    ajaxForecastQuery(3);

});