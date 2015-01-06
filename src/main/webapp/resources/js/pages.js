$(function(){

    var firstHead = $('header').html();

    var code = '<div id="settingsMenu" class="ui-widget-content ui-corner-all">\
    <div id="settings" class="mainButtons hideButtons"><i class="fa fa-cogs"><span>&nbspSettings</span></i></div>\
    <div id="changePassword" class="mainButtons hideButtons"><i class="fa fa-key"><span>&nbspChange&nbsppassword</span></i></div>\
    <div id="logout" class="mainButtons hideButtons"><i class="fa fa-sign-out"><span>&nbspLogout</span></i></div></div>\
    <div > <div id="consumption" class="mainButtons"><i class="fa fa-plug   fa-4x"></i></div>\
    <div id="loggedPage" class="mainButtons"><i class="fa fa-sun-o  fa-4x"></i></div>\
    <div id="weather" class="mainButtons"><i class="fa fa-cloud  fa-4x"></i></div>\
    <div id="rating" class="mainButtons"><i class="fa fa-long-arrow-down  fa-4x"></i><i class="fa fa-long-arrow-up  fa-4x"></i></div>\
    <div id="menu" class="mainButtons"><i class="fa fa-list  fa-4x"></i></div></div>';

    //$( "#settingsMenu .hideButtons" ).css();
var counter = 0;

    function runEffect(counter) {
        if(counter == 1) {
            $("#settingsMenu").show("slide", {direction: "right"}, 500);
            //$("#settingsMenu").focusin();
                } else {

                $("#settingsMenu").hide("slide", {direction: "right"}, 500);
            }
        if ( !($("#menu").is(':hover'))) {
            $("#settingsMenu").hide("slide", {direction: "right"}, 500);
        }

    };




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
            if(counter == 0){
                counter = 1;
            } else {
                counter = 0;
            }
            runEffect(counter);
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
            $('footer').html(" ").removeClass('footer');
            colorSwitch();
        }
    });

    $(window).resize();



});
