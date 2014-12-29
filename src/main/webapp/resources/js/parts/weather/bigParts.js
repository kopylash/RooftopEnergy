/**
 * Created by alex on 12/26/14.
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
        var TempWeatherObj = function (dt, skyIcon, skyDescription, tempNight, tempDay, wind, clouds, pressure, temperatureMorning, temperatureDay, temperatureEvening, temperatureNight) {
            this.dt = dt;
            this.skyIcon = skyIcon;
            this.tempNight = tempNight;
            this.tempDay = tempDay;
            this.wind = wind;
            this.clouds = clouds;
            this.pressure = pressure;
            this.skyDescription = skyDescription;
            this.temperatureMorning = temperatureMorning;
            this.temperatureDay = temperatureDay;
            this.temperatureEvening = temperatureEvening;
            this.temperatureNight = temperatureNight;
        };
        for (var i = 0; i<number; i++) {
            data[i] = new TempWeatherObj(1419325200000, '10d', 'moderate rain fdfd', ' -36.6200000000000045', ' -38.350000000000023', '20.21', '0', '1000.96','-1', '1', '-2', '-3');
        }
        forecastListSixteen(data);
    };
    var testInfo = function(){

        var InfoObj = function (sunrise, sunset, wind){
            this.sunrise = sunrise;
            this.sunset = sunset;
            this.wind = wind;
        };
        var obj = new InfoObj('8:00', '18:00','Gentle Breeze 4.97 m/s (350.002°)');
        dayInfo(obj);
    };
    /* end mock for weather forecast list*/

    //var number = 3;
    var dayInfo = function(info){
        var sunrise = info.sunrise;
        var sunset = info.sunset;
        var wind = info.wind;
        var content = "<div class='infoIcon'>"+
                "<ul><li class='icon-sunrise'></li><li><p>Sunrise</p></li></ul>"+
                "<ul><li class='icon-sunset'></li><li><p>Sunset</p></li></ul>"+
                "<ul><li class='basecloud'></li><li class='icon-windy'></li><li><p>Wind</p></li></ul>"+
                "</div>"+
            "<div class='infoValue'>"+
            "<div id='sunrise'>"+ sunrise +"</div>"+
            "<div id='sunset'>"+ sunset +"</div>"+
            "<div id='windInfo'>"+ wind +"</div>"+
                    "</div>"
        ;
        $("#weatherInfoToday").html(content);


    };
    var forecastListSixteen = function (data) {

        var monthNames = ["January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"];

        var daysArr = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
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

        var fillDailyTemperature = function (activatedTab){
            var suffix = " <span>&ordmC</span>";
            $("#weatherNight .dailyTempValue").html(data[activatedTab].temperatureNight + suffix);
            $("#weatherEvening .dailyTempValue").html(data[activatedTab].temperatureEvening + suffix);
            $("#weatherDay .dailyTempValue").html(data[activatedTab].temperatureDay + suffix);
            $("#weatherMorning .dailyTempValue").html(data[activatedTab].temperatureMorning + suffix);
        };

        var activateTab = function(tab){
            $(".contentLine").css({'background-color': '#c8e5bc', 'border-bottom': '1px solid #108f38'});
            $(tab).css({'background-color': '#ffffff', 'border-bottom': 'none'});
        };

        var tabs = function () {
            var note;
            var date, weatherImage, description, nightTemperature, dayTemperature, wind, sunshine, pressure;
            var activatedTab = 0;

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


                note = "<button class='contentLine' id='line" + i + "'>" +
                "<div class='lineDate' id='date" + i + "'>" +
                "<div class='dayOfWeek' id='day" + i+ "'></div>"+
                "<div class='shortDate' id='shortDate" + i + "'></div>" +
                "</div>"+
                "<div class='wCondition' id='wCondition"+i+"'>"+

                "<div class='lineIco' id='ico" + i + "'>" + weatherImage + "</div>"+
                "<div class='description' id='wDescription"+i+"'>"+description+"</div>"+
                "</div>"+
                "<div class='temperature' id='temperature"+i+"'>"+
                "<div class='lineNightTemp' id='nTemp" + i + "'></div>" +
                "<div class='lineDayTemp' id='dTemp" + i + "'></div>" +
                "</div>"+
                "<div class='lineWind' id='wind" + i + "'></div>" +
                "<div class='lineSun' id='sun" + i + "'></div>" +
                "<div class='linePressure' id='press" + i + "'></div>" +


                "</button>";

                $("#weatherContent").append(note);
                $("#nTemp" + i).html(fillTemperature("", nightTemperature));
                $("#dTemp" + i).html(fillTemperature("", dayTemperature));
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
                var t = [[1,4],[2,5],[3,3],[4,1],[5,6],[6,3]];
                $("#line"+i).button();
                $("#line"+i).click(function(){
                   activateTab(this);
                    weatherGraph(t);
                    fillDailyTemperature(activatedTab);
                });

                weatherGraph(t);
                fillDailyTemperature(activatedTab);

            }
            $(function(){
                $("#line0").css({'background-color': '#ffffff', 'border-bottom': 'none'});

            });
        };
        tabs();



    };
    $("#threeDays").click(function(){
        //ajaxForecastQuery(3);
        testFunction(3);
    });
    $("#sevenDays").click(function(){
        //ajaxForecastQuery(7);
        testFunction(7);
    });
    $("#fourteenDays").click(function(){
        //ajaxForecastQuery(14);
        testFunction(14);
    });



    userInfo(info);
    testFunction(3);
    testInfo();
    //ajaxUserInfoQuery();
    //ajaxForecastQuery(3);

});