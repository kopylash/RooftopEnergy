function allDateButtons(url1){
    var i = 0;
    var pickerDate = new Date();
    var season = 1;
    //var productionPeriodUrl = '/rest/consumption/consumption_period';

    var buttonStyles = function(buttId){
        $("#period button").css({'background-color': '#108F38', 'color': '#fffffe'});
        $("#"+buttId).css({'background-color': '#fffff0', 'color': '#108F38'});
    };

      $( "#datepicker" ).datepicker({
        changeMonth: true,
        changeYear: true,
          dateFormat: "dd-mm-yy"

    });
    $("#datepicker").datepicker("setDate", "dd-mm-yy");
    var k = $.datepicker.formatDate("dd-mm-yy", new Date());
    $( "#datepicker").text(k);

    var picButtons = function(){
        console.log("Hello");
        //if (ii == 0){
        //    $( "#datepicker").text(k);
        //}else {
            //$("#datepicker").datepicker("setDate");
            var r = $("#datepicker").datepicker("getDate");
            console.log(r);
            var hour = 23;
            var minutes = 59;
            var sec = 59;
            var year = r.getFullYear();
            var month = r.getMonth();
            var day = r.getDate();
            pickerDate = new Date(year, month, day, hour, minutes, sec);
            //console.log(t2);
            //r = $.datepicker.formatDate("dd-mm-yy", r);
            //$("#datepicker").text(r);
        //}
    };

    var buttonClicker = function(url2, x, d){

        var date = new Date();
        //var year = 1970;
        //var month = 0;
        //var day = 1;
        switch(x){
            case 0:
                //day = date.getDate();
                url2 = url2+"/totally";
                break;
            case 1:
                //day = date.getDate();
                url2 = url2+"/daily";
                break;
            case 2:
                //month = date.getMonth();
                url2 = url2+"/monthly";
                break;
            case 3:
                //year = date.getFullYear();
                url2 = url2+"/yearly";
                break;
            default :
            // var ttt = 141;
        }

        //var t = new Date(year, month, day,0, 0, 0);
        ajaxGraphQuery(url2,pickerDate);
    };
    var tt = url1+"/daily";

        buttonClicker(tt,pickerDate);



    $("#day").click(function() {
        buttonStyles(this.id);
        season = 1;
        buttonClicker(url1, season,pickerDate);

    });


    $("#month").click(function(){
        buttonStyles(this.id);
        season = 2;
        buttonClicker(url1,season,pickerDate);

    });

    $("#year").click(function(){
        buttonStyles(this.id);
        season = 3;
        buttonClicker(url1,season,pickerDate);
    });

    $("#total").click(function(){
        buttonStyles(this.id);
        season = 0;
        buttonClicker(url1, season, pickerDate);

    });

    //$("#max").click(function(){
    //    i +=1;
    //    picButtons(i);
    //    buttonClicker(Url1,season,t2);
    //});
    //
    //$("#min").click(function(){
    //    i -=1;
    //    picButtons(i);
    //    buttonClicker(Url1,season,t2);
    //});
    $("#datepicker").change(function(){
        //alert('hello');
        picButtons();
        buttonClicker(url1, season ,pickerDate);
    });

}




