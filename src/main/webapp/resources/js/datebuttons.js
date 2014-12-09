function allDateButtons(Url1){
    var i = 0;
    var t2 = new Date();
    var season = 1;
    //var productionPeriodUrl = '/rest/consumption/consumption_period';

    var buttonStyles = function(buttId){
        $("#period button").css({'background-color': '#108F38', 'color': '#fffffe'});
        $("#"+buttId).css({'background-color': '#fffff0', 'color': '#108F38'});
    };

    $( "#datepicker" ).datepicker();
    $("#datepicker").datepicker("setDate");
    var k = $.datepicker.formatDate("dd.mm.yy", new Date());
    $( "#dateBlock").text(k);

    var picButtons = function(ii){
        if (ii == 0){
            $( "#dateBlock").text(k);
        }else {
            $("#datepicker").datepicker("setDate", +ii);
            var r = $("#datepicker").datepicker("getDate");
            //console.log(r);
            var hour = 23;
            var minutes = 59;
            var sec = 59;
            var year = r.getFullYear();
            var month = r.getMonth();
            var day = r.getDate();
            t2 = new Date(year, month, day, hour, minutes, sec);
            console.log(t2);
            r = $.datepicker.formatDate("dd.mm.yy", r);
            $("#dateBlock").text(r);
        }
    };

    var buttonClicker = function(url2, x){

        var date = new Date();
        var year = 1970;
        var month = 0;
        var day = 1;
        switch(x){
            case 1:
                day = date.getDate();
            case 2:
                month = date.getMonth();
            case 3:
                year = date.getFullYear();
                break;
            default :
            // var ttt = 141;
        }

        var t = new Date(year, month, day,0, 0, 0);
        ajaxGraphQuery(url2,t,t2);
    };


        buttonClicker(Url1,1,t2);



    $("#day").click(function() {
        buttonStyles(this.id);
        season = 1;
        buttonClicker(Url1,season,t2);

    });


    $("#month").click(function(){
        buttonStyles(this.id);
        season = 2;
        buttonClicker(Url1,season,t2);

    });

    $("#year").click(function(){
        buttonStyles(this.id);
        season = 3;
        buttonClicker(Url1,season,t2);
    });

    $("#total").click(function(){
        buttonStyles(this.id);
        season = 0;
        buttonClicker(Url1,season,t2);

    });

    $("#max").click(function(){
        i +=1;
        picButtons(i);
        buttonClicker(Url1,season,t2);
    });

    $("#min").click(function(){
        i -=1;
        picButtons(i);
        buttonClicker(Url1,season,t2);
    });
}




