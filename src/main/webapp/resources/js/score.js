$(function(){
    currentPage.name = 'rating';
    function screenH() {
        var rows;
        if (screen.height <= 550) {
            rows = 6;
        } else if (screen.height <= 670) {
            rows = 9;
        } else if (screen.height <= 750) {
            rows = 10;
        } else if (screen.height <= 820) {
            rows = 11;
        } else if (screen.height <= 1380) {
            rows = 12;
        } else if (screen.height <= 1700) {
            rows = 14;
        } else {
            rows = 16;
        }
        return rows;
    }

    $(window).resize(function(){
        screenH();
    });

    $(window).resize();

    var ratingUrl = '/rest/score';
    var begin = 0;
    var companyNumber = screenH();
    var end = companyNumber - 1;
    var lengthList;
    var secondPart = "/overall";

    var ratingList = function(data){
        if (companyInfo.status === true) {
            $("#main1").html("");
            var arrowValue;
            lengthList = data.length;
            var companyName;
            for (var i = begin; i <= end && i < lengthList; i++) {
                companyName = data[i].company;
                console.log(data[i].arrow);
                switch (data[i].arrow) {
                    case -1:
                        arrowValue = "fa-arrow-down";
                        break;
                    case 0:
                        arrowValue = "fa-minus";
                        break;
                    case 1:
                        arrowValue = "fa-arrow-up";
                        break;
                    default :
                }
                var lineCode = "<div id='' class='rating'><span>" + companyName + "</span><span class='ratingSymbol'><i class='fa " + arrowValue + " fa-1x'></i></span></div>";
                $("#main1").append(lineCode);
            }
            $(".rating").click(function(){
                var comparingCompanyName = $(this).text();
                var location = 'comparing.html?companyName='+comparingCompanyName;
                window.location=location;
            });
            $(".ui-loader h1").css({'display':'none'});

        } else {
            var htmlCode = '<div id="ratingNonePublic">Your company is not public!<br/> Change this setting to watch other companies here.</div>';
            $("#up1, #down").css({'display':'none'});
            $("#main1").html(htmlCode);
        }
    };

    function ajaxScoreQuery(strUrl) {
        var code = Object.create(errorCode);
        code['200'] = function(data){
            ratingList(data);
        };
        code['500'] = function(data){
            $('#main1').html("<h3 style='text-align: center'>Service Unavailable!</h3>");
            console.error(data.responseText);
        };
        $.ajax({
            type: 'post',
            url: strUrl,
            crossDomain: true,
            statusCode:code
        });
    }

    var buttonStyles = function(buttId){
        $(".btn").css({'background-color': '#108F38', 'color': '#fffffe'});
        $("#"+buttId).css({'background-color': '#fffff0', 'color': '#108F38'});
    };

    $("#cons").click(function() {
        buttonStyles(this.id);
        secondPart = "/consumption";
        var url = ratingUrl + secondPart;
        ajaxScoreQuery(url);
        $("#blockMapTitle p").html("Consumption by regions");
        mapGraph('Consumption (kWt)', '/rest/map/consumption/monthly', 1000);
    });

    $("#overScore").click(function(){
        buttonStyles(this.id);
        secondPart = "/overall";
        var url = ratingUrl + secondPart;
        ajaxScoreQuery(url);
        $("#blockMapTitle p").html("Production to Consumption by regions");
        mapGraph('Production/Consumption', '/rest/map/ratio/monthly', 1);

    });

    $("#prod").click(function(){
        buttonStyles(this.id);
        secondPart = "/production";
        var url = ratingUrl + secondPart;
        ajaxScoreQuery(url);
        $("#blockMapTitle p").html("Production by regions");
        mapGraph('Production (kWt)', '/rest/map/production/monthly', 1000);
    });

    //data loading
    var url1 = ratingUrl + "/overall";
    ajaxScoreQuery(url1);
    buttonStyles('overScore');
    mapGraph('Production/Consumption', '/rest/map/ratio/monthly', 1);

    $("#up").css({"display": "none"});
    $("#up1").css({"display": "none"});

    $("#up").click(function(){
       if(begin >= companyNumber) {
           $("#down").css({"display": "block"});
           begin = begin - companyNumber;
           end = end - companyNumber;
           console.log(end);
           console.log(begin);
           var url = ratingUrl + secondPart;
           ajaxScoreQuery(url);
           if (begin < companyNumber) {
               $(this).css({"display": "none"});
               $("#up1").css({"display": "none"});
           }
       }
    });

    $("#down").click(function(){
        if (end <= lengthList) {
            $("#up1").css({"display": "block"});
            $("#up").css({"display": "block"});
            begin = begin + companyNumber;
            end = end + companyNumber;
            console.log(end);
            console.log(begin);
            var url = ratingUrl + secondPart;
            ajaxScoreQuery(url);
            if (end > lengthList) {
                $(this).css({"display": "none"});
            }
        }
    });
});