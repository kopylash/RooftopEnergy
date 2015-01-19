$(function() {
    var firstHead = $('header').html();

    var companyName;
    var userName;


    function ajaxGetUserInfo() {
        $.ajax({
            type: 'get',
            url: "/rest/boxData/getUserDescription",
            crossDomain: true,
            error: function (data) {
                $('#main').html(data.responseText);
            },
            statusCode: {
                200: function (data) {
                    userName = data.userName;
                    companyName = data.company;
                    companyInfo.status = data.publicStatus;

                    //loadHtml();
                }
            }
        });
    }


    ajaxGetUserInfo();

    $(document).ajaxComplete(function () {
        $("#desktopCompany").html(companyName);
        $("#desktopUser span").html(userName);




    });
    //function loadHtml() {
    var code = '<div id="mainMenu"><div id="settingsMenu" class="ui-widget-content ui-corner-all">\
    <div id="settings" class="mainButtons hideButtons"><i class="fa fa-cogs"><span>&nbspSettings</span></i></div>\
    <div id="changePassword" class="mainButtons hideButtons"><i class="fa fa-key"><span>&nbspChange&nbsppassword</span></i></div>\
    <div id="logout" class="mainButtons hideButtons"><a href="j_spring_security_logout" data-role="button" data-direction="reverse" data-transition="fade" data-ajax="false"><i class="fa fa-sign-out"><span>&nbspLogout</span></i></a></div></div>\
    <div id="showButtons" > <div id="consumption" class="mainButtons"><i class="fa fa-plug   fa-4x"></i></div>\
    <div id="loggedPage" class="mainButtons"><i class="fa fa-sun-o  fa-4x"></i></div>\
    <div id="weather" class="mainButtons"><i class="fa fa-cloud  fa-4x"></i></div>\
    <div id="rating" class="mainButtons"><i class="fa fa-long-arrow-down  fa-4x"></i><i class="fa fa-long-arrow-up  fa-4x"></i></div>\
    <div id="menu" class="mainButtons"><i class="fa fa-list  fa-4x"></i></div></div></div>';

    var headerHtml = '<div class="desktopHeaderClass" id="desktopHeader">' +
        '<div id="desktopLogo"><a href="home.html"><img src="../resources/images/logo.svg"></a></div> ' +
        '<div id="desktopUser"><a href="j_spring_security_logout"><span></span> <i class="fa fa-sign-out"></i></a></div>' +
        '<div id="desktopCompany"></div></div><div id="forClear"></div>';

    function runEffect() {
        $("#settingsMenu").show("slide", {direction: "right"}, 500);
        $("#mainMenu").hover(function () {
            $("#settingsMenu").hide("slide", {direction: "right"}, 500);
        })
    }


    //var foo = function(kod) {
    //    if (screen.width <= 768) {
    //        $('footer').html(kod).addClass("footer");
    //    } else {
    //        $('header').html(kod).removeClass("header").addClass("header1");
    //        $("#consumption").html(" ");
    //        var hh = $("#consumption").html();
    //
    //
    //    }
    //};
    //foo(code);

    var colorSwitch = function (k, kk) {
        var page = window.location;
        page = page + "";
        var first = page.lastIndexOf("/") + 1;
        var second = page.indexOf(".html");
        var idi = page.slice(first, second);
        $("#" + idi).css({'color': k, 'backgroundColor': kk});

        $(".mainButtons").click(function () {
            if (this.id == "menu") {
                runEffect();
            } else {
                if (companyInfo.status == false && this.id == "rating") {
                alert("Your company is not public!");

            } else {
                if (this.id != "logout") {
                    window.location = this.id + ".html";
                }
            }

        }
        });

    };



    colorSwitch();
    var jQmobile = '<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>';

    var whatDevice = device.mobile() || device.tablet();
    var landscape = (screen.width <= 1300) && (screen.height <= 1000);
    var portrait = (screen.width <= 1000) && (screen.height <= 1300);
    console.log(whatDevice);
    //window.addEventListener("resize", function () {
    //    document.location.reload(true);
    ////});
    //    $(window).resize(function () {
            if (whatDevice || landscape || portrait) {
                $('footer').html(code).addClass("footer");
                $('header').removeClass("header1").html(firstHead).addClass("header");
                var k1 = '#0062D2';
                var k3 = '#59AC28';
                $("footer").append(jQmobile);
                colorSwitch(k1, k3);
                 } else {

                $('header').removeClass('header').html(headerHtml).append("<div id='deskMenu'>" + code + "</div>");
                if (currentPage.name!='Comparing') {
                    $("#periodMenuButtons").css({"width": "40%", "textAlign": "center", "margin-left": "50%"});
                } else {
                    $("#periodMenuButtons").css({"width": "90%", "textAlign": "center", "margin-left": "auto"});
                    $("#period").css({"margin": "8px 50% 10px", "width": "50%"});
                    $("#date").css({"margin": "8px 50% 10px", "width": "50%"});
                }

                $("#period .btn").css({"width":"20%"});
                $('.mainButtons:not(.hideButtons)').each(function () {
                    var buttId = this.id;
                    $(this).text(buttId).addClass('bigButtonsFont');
                    $("#loggedPage").text("production");
                    $("#rating").text("score");
                    $("#menu").text("settings").click(function () {
                        window.location = "settings.html";
                    });

                });

                $('footer').html(" ").removeClass('footer');
                var k2 = "#fffffe";
                var k4 = "#108f38";
                colorSwitch(k2, k4);
                $("#desktopHeader").addClass("desktopHeaderClass");

            }
        //});
        //
        //$(window).resize();


    //}
});

