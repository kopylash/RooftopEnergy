$(function(){
    var companyNumber;
    function screenH() {
        if (screen.height <= 550) {
            companyNumber = 6;
        } else if (screen.height <= 670) {
            companyNumber = 9;
        } else if (screen.height <= 750) {
            companyNumber = 10;
        } else if (screen.height <= 820) {
            companyNumber = 11;
        } else if (screen.height <= 1380) {
            companyNumber = 12;
        } else if (screen.height <= 1700) {
            companyNumber = 14;
        } else {
            companyNumber = 16;
        }
    }
    screenH();

    //
    $(window).resize(function(){
        screenH();
    });

    $(window).resize();
    //

    var ratingUrl = '/rest/score';
    var begin = 0;
    var end = companyNumber - 1;
    var lengthList;
    var secondPart = "/production";

    var ratingList = function(data){
        $("#main1").html("");
        var arrowValue;
       /* function Obj(comp, arr){
            this.company = comp;
            this.arrow = arr;
        }
        var arr = new Array();
        for (var i = 0; i < 42; i++){
            arr[i] = new Obj("Company"+i, 1);
        }
        data = arr;*/

        lengthList = data.length;
        console.log(lengthList);
        //for (i in data) {
        for (var i = begin; i <= end; i++) {
            var tt = data[i].company;
           console.log(data[i].arrow);
           switch (data[i].arrow){
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
            lineCode = "<div  class='rating'>"+"<span id="+i+">"+tt+"</span>"+"<span class='ratingSymbol'><i class='fa "+arrowValue+" fa-1x'></i></span></div>";
            $("#main1").append(lineCode);
        }

    }

    $("#main1 .rating span").click(function(){
        //var rr = this.id;
        console.log("Hello");
        //someName.name = $(this).text();
    });

    function ajaxScoreQuery(strUrl) {
        $.ajax({
            type: 'post',
            url: strUrl,
            crossDomain: true,
            error: function (data) {
                $('#main').html(data.responseText);
            },
            statusCode: {
                200: function (data) {
                    ratingList(data);
                }
            }
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
    });


    $("#overScore").click(function(){
        buttonStyles(this.id);
        secondPart = "/overall";
        var url = ratingUrl + secondPart;
        ajaxScoreQuery(url);
    });

    $("#prod").click(function(){
        buttonStyles(this.id);
        secondPart = "/production";
        var url = ratingUrl + secondPart;
        ajaxScoreQuery(url);
    });

    var url1 = ratingUrl + "/production";
    ajaxScoreQuery(url1);
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
       }//} else {
        //   $(this).css({"display":"none"});
        //}
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
        }//} else {
        //   $(this).css({"display":"none"});
        //}
    });


});