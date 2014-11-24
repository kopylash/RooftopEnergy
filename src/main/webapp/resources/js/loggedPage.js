$(function(){
    var i = 0;
    $( "#datepicker" ).datepicker();
    var k = $.datepicker.formatDate("dd.mm.yy", new Date());
    $( "#dateBlock").text(k);

$("#max").click(function(){
    i +=1;
    if (i == 0){
        var k = $("#datepicker").datepicker("getDate");
        k = $.datepicker.formatDate("dd.mm.yy", k);//for current date

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
            var k = $("#datepicker").datepicker("getDate");
            k = $.datepicker.formatDate("dd.mm.yy", k);//for current date

            $( "#dateBlock").text(k);
        } else {
            $("#datepicker").datepicker("setDate", +i);
            var r = $("#datepicker").datepicker("getDate");
            r = $.datepicker.formatDate("dd.mm.yy", r);
            $("#dateBlock").text(r);
        }
    });



$("#day").click(function() {
    $("#period button").css({'background-color': '#108F38', 'color': '#fffffe'});
    $(this).css({'background-color': '#fffff0', 'color': '#108F38'});
    $("#main").css({'background-color': '#fffffe'});
});


$("#month").click(function(){
    $("#period button").css({'background-color':'#108F38','color':'#fffffe'});
    $(this).css({'background-color':'#fffff0','color':'#108F38'});
        $("#main").css({'background-color':'#ffff00'});
});

    $("#year").click(function(){
        $("#period button").css({'background-color':'#108F38','color':'#fffffe'});
        $(this).css({'background-color':'#fffff0','color':'#108F38'});
        $("#main").css({'background-color':'#ff0000'});
    });

    $("#total").click(function(){
        $("#period button").css({'background-color':'#108F38','color':'#fffffe'});
        $(this).css({'background-color':'#fffff0','color':'#108F38'});
        $("#main").css({'background-color':'#ff00ff'});
    });

});
