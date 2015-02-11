function allDateButtons(url1){
    var pickerDate = new Date();
    var season = 1;

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
            var r = $("#datepicker").datepicker("getDate");
            console.log(r);
            var hour = 23;
            var minutes = 59;
            var sec = 59;
            var year = r.getFullYear();
            var month = r.getMonth();
            var day = r.getDate();
            pickerDate = new Date(year, month, day, hour, minutes, sec);
    };

    var buttonClicker = function(url2, x, d){
        var tooltipDateFormat = "%a, %d %b %Y %H:%M";//Format for day (Mon, 01 Feb 2015 00:00)
        switch(x){
            case 0:
                url2 = url2+"/totally";
                tooltipDateFormat = "%B, %Y"; // 2015, February
                break;
            case 1:
                url2 = url2+"/daily";
                tooltipDateFormat = "%a, %d %b %Y %H:%M";
                break;
            case 2:
                url2 = url2+"/monthly";
                tooltipDateFormat = "%A, %d %B %Y";
                break;
            case 3:
                url2 = url2+"/yearly";
                tooltipDateFormat = "%B, %Y";
                break;
            default :
        }
        ajaxGraphQuery(url2,pickerDate, tooltipDateFormat);
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

    $("#datepicker").change(function(){
        picButtons();
        buttonClicker(url1, season ,pickerDate);
    });

}




