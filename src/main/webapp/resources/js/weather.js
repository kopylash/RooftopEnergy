$(function(){

    var code2 = '<div id="general">' +
        '<div id="generalSize">' +
        '<div id="generalGap">' +
        '<div id="generalHeader">Weather forecast<br/>' +
        '<span style="font-weight:normal;">Amsterdam</span>' +
        '</div>' +
        '<iframe id="widgetFrame" src="http://www.weeronline.nl/Go/ExternalWidgetsNew/ThreeDaysCity?gid=4058223&sizeType=1&temperatureScale=Celsius&defaultSettings=False" frameborder="0" scrolling="no" allowtransparency="true">' +
        '</iframe>' +
        '<a id="linkOne" href="http://www.weeronline.nl/Europa/Nederland/Amsterdam/4058223" ' +
        'target="_blank">Weeronline.nl - Meer weer in Amsterdam</a>' +
        '<a id="linkTwo" href="http://www.weeronline.nl/" title="weeronline.nl Altijd jouw weer" target="_blank">' +
        '<img src="http://www.weeronline.nl/Shared/Images/widget/new-widget-logo-color.png" width="113" height="25" alt="weeronline.nl Altijd jouw weer" style="border: none;background-color: transparent;box-shadow: none;" />' +
        '</a>' +
        '</div>' +
        '</div>' +
        '</div>';



    var code = '<div id="general">' +
        '<div id="generalSize">' +
        '<div id="generalGap">' +
        '<div id="generalHeader">UV-index<br/>' +
        '<span style="font-weight:normal;">Amsterdam</span>' +
        '</div>' +
        '<iframe id="widgetFrame" src="http://www.weeronline.nl/Go/ExternalWidgetsNew/TwoDaysCityUV?gid=4058223&temperatureScale=Celsius&defaultSettings=False"' +
        ' frameborder="0" scrolling="no" style="border: none;" allowtransparency="true">' +
        '</iframe>' +
        '<a href="http://www.weeronline.nl/Europa/Nederland/Zonkracht-Amsterdam/4058223" ' +
        'id="linkOne" target="_blank">Uitgebreide UV-index verwachting in Amsterdam' +
        '</a>' +
        '<a href="http://www.weeronline.nl/" id="linkTwo" title="weeronline.nl Altijd jouw weer" target="_blank">' +
        '<img src="http://www.weeronline.nl/Shared/Images/widget/new-widget-logo-color.png" width="113" height="25" ' +
        'alt="weeronline.nl Altijd jouw weer" style="border: none;background-color: transparent;box-shadow: none;" />' +
        '</a>' +
        '</div>' +
        '</div>' +
        '</div>';

    var foo = function(kod1, kod2) {
        if (screen.width <= 768) {
            //$('main').html(kod2);
            $('#main').load("weatherForecast.html");
        } else {
            //$('main').html(kod1).css(" ");
            $('#main').load("weatherForecastDesk.html");

        }
    };
    foo(code, code2);

    $(window).resize(function(){
        if (screen.width <= 768) {
            $('#main').load("weatherForecast.html");
            //$('main').html(code2);
        } else {
            //$('main').html(code);
            $('#main').load("weatherForecastDesk.html");

        }
    });

    $(window).resize();

});
