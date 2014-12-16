$(function(){
    var ratingUrl = '/rest/score';

    var ratingList = function(data){
        //console.log("RatingList function!!!";
        var arrowValue;
        var lineCode = '<div id="" class="rating">Company 1 <span class="ratingSymbol"><i class="fa fa-minus fa-1x "></i></span></div>';
        //var arr = new Array();
        for (i in data) {
            //arr[i] =  [data[i]["company"], data[i]["arrow"]];
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
            lineCode = "<div id='' class='rating'>"+tt+"<span class='ratingSymbol'><i class='fa "+arrowValue+" fa-1x'></i></span></div>";
            $("#main").append(lineCode);
        }

    }


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
        var url = ratingUrl + "/consumption";
        ajaxScoreQuery(url);
    });


    $("#overScore").click(function(){
        buttonStyles(this.id);
        var url = ratingUrl + "/overall";
        ajaxScoreQuery(url);
    });

    $("#prod").click(function(){
        buttonStyles(this.id);
        var url = ratingUrl + "/production";
        ajaxScoreQuery(url);
    });

    var url1 = ratingUrl + "/production";
    ajaxScoreQuery(url1);


});