/**
 * Created by alex on 12/26/14.
 */
$(function() {

    const DAYS_OF_WEEK = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
    const MONTH_NAMES = ["January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"];
    const SHOWED_TABS = 3; //Number of visible tabs in forecast.
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
    const loading = '<div class="spinner">\
                        <img src="../resources/images/loader.gif"/>\
                    </div>';

    function ajaxCloudsForecastQuery(){
        var code = Object.create(errorCode);
        code['200'] = function(cloudsInfo){
            cloudarr = cloudsInfo;
            init();
        };
        code['500'] = function(cloudsInfo){
            $('#main').html("<h3 style='text-align: center'>Service Unavailable!</h3>");
            console.error(cloudsInfo.responseText);
            //$('#main').html(cloudsInfo.responseText);
            rez = null;
        };
        $.ajax({
            type: 'post',
            url: "/rest/weather/cloudsFiveDays",
            crossDomain: true,
            statusCode:code

        });
    }

    var cloudarr;
    var data;
    $("#weatherGraph").html(loading);
    function ajaxUserInfoQuery(){
        var code = Object.create(errorCode);
        code['200'] = function(info){
            userInfo(info);
        };
        code['500'] = function(info){
            console.error(info.responseText);
        };
        $.ajax({
            type: 'get',
            url: "/rest/boxData/getUserDescription",
            crossDomain: true,
            statusCode:code
        });
    }
    function ajaxForecastQuery(numberOfDays) {
        var code = Object.create(errorCode);
        code['200'] = function(d){
            data = d;
            forecastListSixteen(data);
        };
        code['500'] = function(d){
            $('#weatherContent').html("<h3 style='text-align: center'>Service Unavailable!</h3>");
            console.error(d.responseText);
        };
        $.ajax({
            type: 'post',
            url: "/rest/weather/daily",
            crossDomain: true,
            data: {'days': numberOfDays},
            statusCode:code
        });
    }
    function ajaxActualDayForecastQuery(){
        var code = Object.create(errorCode);
        code['200'] = function(actualDayInfo){
            actualDay(actualDayInfo);
        };
        code['500'] = function(actualDayInfo){
            $('#weatherInfoToday').html("<h3 style='text-align: center'>Service Unavailable!</h3>");
            console.error(actualDayInfo.responseText);
        };
        $.ajax({
            type: 'post',
            url: "/rest/weather/actualDay",
            crossDomain: true,
            statusCode:code
        });
    }

    function userInfo(info){
        $("#generalHeader").html(info.city);
    }

//////////////BEGIN - TEST DATA////////////////////////////////////////////////////////////////////

    /* mock for info about weather */

    var TempUserInfo = function(city){
        this.city = city;
    };
    var info = new TempUserInfo("Kiev");
    /* end mock info about weather */

    /* mock for weather forecast list*/
    var testFunction = function(number) {
        var d = new Array();

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
            d[i] = new TempWeatherObj(1419325200000 + 24*i*3600000, '10d', 'moderate rain fdfd', ' -36.6200000000000045', ' -38.350000000000023', '20.21', '0', '1000.96','-1'-i, '1'+i, '-2'-i, '-3'-i);
        }

        data = d;
        forecastListSixteen(data);
    };
    var testInfo = function(){

        var InfoObj = function (sunrise, sunset, speed, deg, description, dt){
            this.sunrise = sunrise;
            this.sunset = sunset;
            this.speed = speed;
            this.deg = deg;
            this.description = description;
            this.dt = dt;
        };
        var obj = new InfoObj('8:00', '18:00', '4.97', '350.002', 'Gentle Breeze  m/s (350.002°)', 1419325200000);
        dayInfo(obj);
    };
    /* end mock for weather forecast list*/
    var testGraph = function(){
        var InfoObj = function (dt, clouds){
            this.dt = dt;
            this.clouds = clouds;
        };
        for (var i = 0; i < 8; i++){
            cloudarr[i] = new InfoObj(1419325200000 +3 *i * 3600000, 10 * i);
        }
        weatherGraph(cloudarr);
    };

//////////////END - TEST DATA////////////////////////////////////////////////////////////////////

    var length;
    var startNumber = 0;
    var activeTab = 'line0';
    var tabs;
    function forecastListSixteen (data) {
        startNumber = 0;
        activeTab = 'line0';
        length = data.length;
        tabs = function() {
            $("#weatherContent").html("");
            for (var i = startNumber; i < (startNumber + SHOWED_TABS); i++) {
                //Create tab
                fillTab(data, i);
                //Transform tab to button
                if (i === 0){
                    activateTab("#" + activeTab);
                }
                $("#line"+i).button();
                $("#line"+i).click(function(){
                    activeTab = $(this).attr('id');
                    activateTab("#"+activeTab);
                    fillDailyTemperature(data, activeTab);
                });

                //fill morning, day, evening, night temperatures
                fillDailyTemperature(data, "0");
            }
        };
        //build tabs
        tabs();
    }

    //Tabs scrolling settings
    var scrollForward = function(){
        var nextPosition = startNumber + SHOWED_TABS + 1;
        if (nextPosition <= length){
            startNumber = nextPosition - SHOWED_TABS;
            tabs();
        }
    };
    var scrollBack = function(){
        var nextPosition = startNumber - 1;
        if (nextPosition < length){
            startNumber = nextPosition;
            tabs();
        }
    };

    $("#scrollLeft").click(function(){
        var id = getTabIndex(activeTab);
        if (id > 0){
            id -=1 ;
            if (id < startNumber){
                scrollBack();
            }
            activeTab = "line"+id;
            activateTab("#"+activeTab);
            fillDailyTemperature(data, activeTab);
            //console.log(activeTab);
        }
    });
    $("#scrollRight").click(function(){

        var id = getTabIndex(activeTab);
        if (id < data.length - 1){
            id +=1 ;
            if (id >= startNumber+SHOWED_TABS){
                scrollForward();
            }
            activeTab = "line"+id;
            activateTab("#"+activeTab);
            fillDailyTemperature(data, activeTab);
        }


    });

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
    //testInfo();

    ajaxCloudsForecastQuery();

    function init(){
        ajaxUserInfoQuery();
        ajaxForecastQuery(3);
        ajaxActualDayForecastQuery();
    }

    function buildGraph(thisDay){
        var day;
        if (thisDay instanceof Date){
            day = thisDay.getDate();
        }
        var array = [];
        var j = 0;
        for (var i = 0; i < cloudarr.length; i++){
            var dt = new Date(cloudarr[i]['dt']).getDate();
            if (dt === day){
                array[j] = [cloudarr[i]['dt'], cloudarr[i]['clouds']];
                j++;
            }
        }
        weatherGraph(array);
    }
    function actualDay(info){
        var sunriseTime = new Date(info.sunrise);
        var sunsetTime = new Date(info.sunset);
        var sunrise = sunriseTime.getHours() + ":" + sunriseTime.getMinutes();
        var sunset = sunsetTime.getHours() + ":" + sunsetTime.getMinutes();
        var wind = info.speed + " m/s (" + Math.round(info.deg) +"&ordm)";
        var dt = new Date(info.dt);
        var content = "<h6>Actual. Today: "+ dt.getDate() + " " + MONTH_NAMES[dt.getMonth()] + "</h6>" +
        "<div class='infoIcon'>"+
                "<ul><li class='icon-sunrise'></li><li><p>Sunrise</p></li></ul>"+
                "<ul><li class='icon-sunset'></li><li><p>Sunset</p></li></ul>"+
                "<ul id='iconWind'><li class='basecloud'></li><li class='icon-windy'></li><li><p>Wind</p></li></ul>"+
                "</div>"+
                "<div class='infoValue'>"+
                "<div id='sunrise'>"+ sunrise +"</div>"+
                "<div id='sunset'>"+ sunset +"</div>"+
                "<div id='windInfo'>"+ wind +"</div>"+
                "</div>";
        $("#weatherInfoToday").html(content);
    }
    function fillTemperature (pic, temper) {
        var roundTemper = Math.round(temper);
        return pic + " " + roundTemper + " <span>&ordmC</span>";
    }
    function fillTab(data, index){
        var i = index;
        var note;
        var date, weatherImage, description, nightTemperature, dayTemperature, wind, sunshine, pressure;

        const WEATHER_PICTURES = {
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

        date = new Date(data[i].dt);
        weatherImage = WEATHER_PICTURES[data[i].skyIcon];
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
            return DAYS_OF_WEEK[date.getDay()];
        });
        $("#shortDate" + i).html(function () {
            return date.getDate() + " " + MONTH_NAMES[date.getMonth()];
        });
        $("#wind" + i).html(function () {
            return "<i class='fa fa-long-arrow-right'></i> " + Math.round(wind) + "<span> m/s</span>";
        });
        $("#sun" + i).html("<i class='fa fa-sun-o'></i> " + sunshine + "<span> %</span>");
        $("#press" + i).html(function(){
            var press = new Number(pressure);
            return press.toFixed(1) + "<span> Hpa</span>"
        });
    }
    function fillDailyTemperature(data, activatedTab){
        var index = getTabIndex(activatedTab);

        var suffix = " <span>&ordmC</span>";
        var night = Math.round(Number(data[index].temperatureNight));
        var evening = Math.round(Number(data[index].temperatureEvening));
        var day = Math.round(Number(data[index].temperatureDay));
        var morning = Math.round(Number(data[index].temperatureMorning));

        $("#weatherNight .dailyTempValue").html(night + suffix);
        $("#weatherEvening .dailyTempValue").html(evening + suffix);
        $("#weatherDay .dailyTempValue").html(day + suffix);
        $("#weatherMorning .dailyTempValue").html(morning + suffix);
    }

    function getTabIndex(id){
        var regex = /(\d+)/g;
        return Number(id.match(regex)[0]);
    }
    function activateTab(tab){
        $(".contentLine").css({'background-color': '#c8e5bc', 'border': '1px solid #108f38'});
        $(tab).css({'background-color': '#ffffff', 'border': 'none'});

        var ind = getTabIndex(tab);
        buildGraph(new Date(data[ind].dt));
    }
});