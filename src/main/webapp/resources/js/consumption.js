$(function(){
    var productionPeriodUrl = '/rest/consumption/consumption_period';

    var i = 0;
    $( "#datepicker" ).datepicker();
    $("#datepicker").datepicker("setDate");
    var k = $.datepicker.formatDate("dd.mm.yy", new Date());
    $( "#dateBlock").text(k);

    $("#max").click(function(){
        i +=1;
        if (i == 0){
            $( "#dateBlock").text(k);
        }else {
            $("#datepicker").datepicker("setDate", +i);
            var r = $("#datepicker").datepicker("getDate");
            r = $.datepicker.formatDate("dd.mm.yy", r);
            $("#dateBlock").text(r);
        }
    });

    $("#min").click(function(){
        i -=1;
        if (i == 0){
            $( "#dateBlock").text(k);
        } else {
            $("#datepicker").datepicker("setDate", +i);
            var r = $("#datepicker").datepicker("getDate");
            r = $.datepicker.formatDate("dd.mm.yy", r);
            $("#dateBlock").text(r);
        }
    });



    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth();
    var day = date.getDate();
    t = new Date(year, month, day, 0,0,0);

    ajaxGraphQuery(productionPeriodUrl,t);

    $("#day").click(function() {
        $("#period button").css({'background-color': '#108F38', 'color': '#fffffe'});
        $(this).css({'background-color': '#fffff0', 'color': '#108F38'});
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth();
        var day = date.getDate();
        t = new Date(year, month, day, 0,0,0);

        ajaxGraphQuery(productionPeriodUrl,t);

    });


    $("#month").click(function(){
        $("#period button").css({'background-color':'#108F38','color':'#fffffe'});
        $(this).css({'background-color':'#fffff0','color':'#108F38'});

        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth();

        t = new Date(year, month, 1, 0,0,0);

        ajaxGraphQuery(productionPeriodUrl,t);

    });

    $("#year").click(function(){
        $("#period button").css({'background-color':'#108F38','color':'#fffffe'});
        $(this).css({'background-color':'#fffff0','color':'#108F38'});

        var date = new Date();
        var year = date.getFullYear();

        t = new Date(year, 0, 1, 0,0,0);

        ajaxGraphQuery(productionPeriodUrl,t);
    });

    $("#total").click(function(){
        $("#period button").css({'background-color':'#108F38','color':'#fffffe'});
        $(this).css({'background-color':'#fffff0','color':'#108F38'});


        var t = $("#datepicker").datepicker("getDate");
        //t = $.datepicker.formatDate("yy-mm-dd", t);
        if (!t) {
            t = new Date(1971,1,1,0,0,0);
            /* var d = new Date();
             var month = d.getMonth()+1;
             var day = d.getDate();
             t = d.getFullYear() + "-" +(month < 10 ? '0' : '') + month + "-" +
             (day < 10 ? '0' : '') + day;*/
        }
        /*t = t + " 00:00:00";*/


        //TODO figure out the problem with dataEnd

        ajaxGraphQuery(productionPeriodUrl,t);

    });
});

//$.ajax({
//    url: url,
//    dataType: 'json',
//    data: data,
//    success: callback
//});

