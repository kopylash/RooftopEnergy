$(function(){

    var firstHead = $('header').html();

    var code = '<div > <div id="consumption" class="mainButtons"><i class="fa fa-plug   fa-4x"></i></div>\
    <div id="loggedPage" class="mainButtons"><i class="fa fa-sun-o  fa-4x"></i></div>\
    <div id="weather" class="mainButtons"><i class="fa fa-cloud  fa-4x"></i></div>\
    <div id="rating" class="mainButtons"><i class="fa fa-long-arrow-down  fa-4x"></i><i class="fa fa-long-arrow-up  fa-4x"></i></div>\
    <div id="settings" class="mainButtons"><i class="fa fa-wrench  fa-4x"></i></div></div>';





    var foo = function(kod) {
        if (screen.width <= 768) {
            $('footer').html(kod).addClass("footer");
        } else {
            $('header').html(kod).removeClass("header").addClass("header1");

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
        window.location = this.id + ".html";
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
