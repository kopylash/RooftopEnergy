$(function(){
 var code =    '<div style="font-family: Arial;background-color: #fbfbfb;border: 1px solid #e7e7e7;' +
     'width: 500px;height: 315px;-moz-box-shadow: 0 0 2px 1px #e7e7e7;-webkit-box-shadow: 0 0 2px 1px #e7e7e7;' +
     'box-shadow: 0 0 2px 1px #e7e7e7;overflow: hidden; -webkit-border-radius: 4px; -moz-border-radius: 4px;' +
     ' border-radius: 4px;"><div style="width: 315px;height: 260px;margin-top: 27px;margin-left: 92px;">' +
     '<div style="margin:7px 10px;"><div style="color: #222222;font-family: Arial;font-size: 12px;font-weight: bold;' +
     'margin: 0px 0px 7px 0px;line-height: 14px;">UV-index<br/><span style="font-weight:normal;">Amsterdam</span></div>' +
     '<iframe id="widget-frame" src="http://www.weeronline.nl/Go/ExternalWidgetsNew/TwoDaysCityUV?gid=4058223&temperatureScale=Celsius&defaultSettings=False" ' +
     'width="295" height="145" frameborder="0" scrolling="no" style="border: none;" allowtransparency="true">' +
     '</iframe><a href="http://www.weeronline.nl/Europa/Nederland/Zonkracht-Amsterdam/4058223" ' +
     'style="background: url(http://www.weeronline.nl/Shared/Images/list_icon_blue_trans.png)' +
     ' no-repeat scroll left 1px transparent;color: #0160b2;font-family: Arial;font-size: 12px;font-weight: normal;' +
     'padding-left: 14px;margin: 7px 0px 5px 0px;line-height: 12px;outline: none;text-decoration: none;' +
     'display: inline-block;" target="_blank">Uitgebreide UV-index verwachting in Amsterdam</a>' +
     '<a href="http://www.weeronline.nl/" style="display: block;height: 25px;width: 113px;margin: 0px 10px 8px 0px;' +
     'outline: none;text-decoration: none;" title="weeronline.nl Altijd jouw weer" target="_blank">' +
     '<img src="http://www.weeronline.nl/Shared/Images/widget/new-widget-logo-color.png" width="113" ' +
     'height="25" alt="weeronline.nl Altijd jouw weer" style="border: none;background-color: transparent;' +
     'box-shadow: none;" /></a></div></div></div>';

    var code2 = '<div style="font-family: Arial;background-color: #fbfbfb;border: 1px solid #e7e7e7;width: 255px;' +
        'height: 335px;-moz-box-shadow: 0 0 2px 1px #e7e7e7;-webkit-box-shadow: 0 0 2px 1px #e7e7e7;' +
        'box-shadow: 0 0 2px 1px #e7e7e7;overflow: hidden; -webkit-border-radius: 4px; -moz-border-radius: 4px; ' +
        'border-radius: 4px;"><div style="width: 255px;height: 335px;"><div style="margin:7px 10px;">' +
        '<div style="color: #222222;font-family: Arial;font-size: 12px;font-weight: bold;margin: 0px 0px 7px 0px;' +
        'line-height: 14px;">Weersverwachting<br/><span style="font-weight:normal;">Amsterdam</span></div>' +
        '<iframe id="widget-frame" src="http://www.weeronline.nl/Go/ExternalWidgetsNew/ThreeDaysCity?gid=4058223&sizeType=1&temperatureScale=Celsius&defaultSettings=True"' +
        ' width="235" height="216" frameborder="0" scrolling="no" style="border: none;" allowtransparency="true"></iframe>' +
        '<a href="http://www.weeronline.nl/Europa/Nederland/Amsterdam/4058223" ' +
        'style="background: url(http://www.weeronline.nl/Shared/Images/list_icon_blue_trans.png) no-repeat scroll left 1px transparent;' +
        'color: #0160b2;font-family: Arial;font-size: 12px;font-weight: normal;padding-left: 14px;' +
        'margin: 7px 0px 5px 0px;line-height: 12px;outline: none;text-decoration: none;display: inline-block;" ' +
        'target="_blank">Weeronline.nl - Meer weer in Amsterdam</a><a href="http://www.weeronline.nl/" ' +
        'style="display: block;height: 25px;width: 113px;margin: 0px 10px 8px 0px;outline: none;text-decoration: none;"' +
        ' title="weeronline.nl Altijd jouw weer" target="_blank">' +
        '<img src="http://www.weeronline.nl/Shared/Images/widget/new-widget-logo-color.png" width="113" height="25" alt="weeronline.nl Altijd jouw weer"' +
        ' style="border: none;background-color: transparent;box-shadow: none;" /></a></div></div></div>';

    var foo = function(kod1, kod2) {
        if (screen.width <= 768) {
            $('main').html(kod2);
        } else {
            $('main').html(kod1);

        }
    };
    foo(code, code2);

    $(window).resize(function(){
        if (screen.width <= 768) {
            $('main').html(code2);
        } else {
            $('main').html(code);

        }
    });

    $(window).resize();

});
