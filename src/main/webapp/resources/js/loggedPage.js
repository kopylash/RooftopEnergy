$(function(){
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
        var t = $("#datepicker").datepicker("getDate");
        t = $.datepicker.formatDate("yy-mm-dd", t);
        if (!t) {
            var d = new Date();
            var month = d.getMonth()+1;
            var day = d.getDate();
            t = d.getFullYear() + "-" +(month < 10 ? '0' : '') + month + "-" +
            (day < 10 ? '0' : '') + day;
        }
        t = t + " 00:00:00";

        $.ajax({
            type: 'post',
            url: '/rest/boxData/monthTotal',
            crossDomain: true,
            data: {'id': "1", 'date': t},
            error: function (data) {
                $('#login_message').html(data.responseText);
            },
            statusCode: {
                200: function (data) {
                    $(".search-result").html("<b>" + "total energy " + data + "</b>");
                }
            }
        });
    });
});


