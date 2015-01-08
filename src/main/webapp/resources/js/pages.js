$(function(){

    var firstHead = $('header').html();

    var code = '<div id="mainMenu"><div id="settingsMenu" class="ui-widget-content ui-corner-all">\
    <div id="settings" class="mainButtons hideButtons"><i class="fa fa-cogs"><span>&nbspSettings</span></i></div>\
    <div id="changePassword" class="mainButtons hideButtons"><i class="fa fa-key"><span>&nbspChange&nbsppassword</span></i></div>\
    <div id="logout" class="mainButtons hideButtons"><a href="j_spring_security_logout"><i class="fa fa-sign-out"><span>&nbspLogout</span></i></a></div></div>\
    <div id="showButtons" > <div id="consumption" class="mainButtons"><i class="fa fa-plug   fa-4x"></i></div>\
    <div id="loggedPage" class="mainButtons"><i class="fa fa-sun-o  fa-4x"></i></div>\
    <div id="weather" class="mainButtons"><i class="fa fa-cloud  fa-4x"></i></div>\
    <div id="rating" class="mainButtons"><i class="fa fa-long-arrow-down  fa-4x"></i><i class="fa fa-long-arrow-up  fa-4x"></i></div>\
    <div id="menu" class="mainButtons"><i class="fa fa-list  fa-4x"></i></div></div></div>';


    function runEffect(){
        $("#settingsMenu").show("slide", {direction: "right"}, 500);
        $("#mainMenu").hover(function(){
            $("#settingsMenu").hide("slide", {direction: "right"}, 500);
        })
    }



    var foo = function(kod) {
        if (screen.width <= 768) {
            $('footer').html(kod).addClass("footer");
        } else {
            $('header').html(kod).removeClass("header").addClass("header1");
            $("#consumption").html(" ");
            var hh = $("#consumption").html();


        }
    };
    foo(code);

    var colorSwitch = function(){

    var page = window.location;
    page = page+"";
    var first = page.lastIndexOf("/")+1;
    var second = page.indexOf(".html");
    var idi = page.slice(first, second);
    $("#"+idi).css({ 'color': '#0062D2'});

    $(".mainButtons").click(function() {
        if(this.id == "menu"){
            runEffect();
        } else {
            window.location = this.id + ".html";
        }
    });

    };

    colorSwitch();

    $(window).resize(function(){
        if (screen.width <= 768) {
            $('footer').html(code).addClass("footer");
            $('header').removeClass("header1").html(firstHead).addClass("header");
            colorSwitch();

        } else {
            $('header').html(code).removeClass("header").addClass("header1");
            $('.mainButtons:not(.hideButtons)').each(function(){
                var buttId = this.id;
                $(this).text(buttId).addClass('bigButtonsFont');
            });
            $('footer').html(" ").removeClass('footer');
            colorSwitch();
        }
    });

    $(window).resize();



});
