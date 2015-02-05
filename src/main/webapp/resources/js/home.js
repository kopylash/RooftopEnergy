/**
 * Created by Владислав on 16.01.2015.
 */
$(function(){
    currentPage.name = 'home';
    var productionPeriodUrl = '/rest/production';
    var consumptionPeriodUrl = '/rest/consumption';
    allDateButtons(productionPeriodUrl,consumptionPeriodUrl);
});

function allDateButtons(url1,url2){
    var i = 0;
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

    var buttonClicker = function(url1,url2, x, d){
        var date = new Date();
        switch(x){
            case 0:
                url1 = url1+"/totally";
                url2 = url2+"/totally";
                break;
            case 1:
                url1 = url1+"/daily";
                url2 = url2+"/daily";
                break;
            case 2:
                url1 = url1+"/monthly";
                url2 = url2+"/monthly";
                break;
            case 3:
                url1 = url1+"/yearly";
                url2 = url2+"/yearly";
                break;
            default :
        }
        ajaxGraphQuery(url1,url2,pickerDate);
    };
    var tt = url1+"/daily";
    var pp = url2+"/daily";

    buttonClicker(tt,pp,pickerDate);

    $("#day").click(function() {
        buttonStyles(this.id);
        season = 1;
        buttonClicker(url1,url2, season,pickerDate);

    });

    $("#month").click(function(){
        buttonStyles(this.id);
        season = 2;
        buttonClicker(url1,url2,season,pickerDate);

    });

    $("#year").click(function(){
        buttonStyles(this.id);
        season = 3;
        buttonClicker(url1,url2,season,pickerDate);
    });

    $("#total").click(function(){
        buttonStyles(this.id);
        season = 0;
        buttonClicker(url1,url2, season, pickerDate);

    });

     $("#datepicker").change(function(){
        picButtons();
        buttonClicker(url1,url2, season ,pickerDate);
    });

}

function ajaxGraphQuery(strUrl1,strUrl2,endDate) {
    $.ajax({
        type: 'post',
        url: strUrl1,
        crossDomain: true,
        data: { 'date': endDate.getTime()},
        error: function (data1) {
            $('#main').html(data1.responseText);
        },
        statusCode: {
            200: function (data1) {
                $.ajax({
                    type: 'post',
                    url: strUrl2,
                    crossDomain: true,
                    data: { 'date': endDate.getTime()},
                    error: function (data2) {
                        $('#main').html(data2.responseText);
                    },
                    statusCode: {
                        200: function (data2) {
                            graph(data1,data2);
                            appliancePairData(data1, data2, strUrl1);
                        }
                    }
                })
            }
        }
    }




    );
}

function graph (data1,data2) {
    var arr1 = new Array();
    for (var i in data1) {
        if(data1.hasOwnProperty(i)) {
            if (data1[i]["value"] !== null) {
                arr1[i] = [data1[i]["date"], data1[i]["value"] / 1000];
            } else {
                arr1[i] = [data1[i]["date"], null];
            }
        }
    }
    var arr2 = new Array();
    for (var j in data2) {
        if(data2.hasOwnProperty(j)) {
            if (data1[j]["value"] !== null) {
                arr2[j] = [data2[j]["date"], data2[j]["value"] / 1000];
            } else {
                arr2[j] = [data2[j]["date"], null];
            }
        }
    }

    Highcharts.setOptions({
        global: {
            useUTC: false

        },
        colors: ['#59AC28','#00BFFF']

    });


    $('main').highcharts({

        rangeSelector: {
            inputEnabled : false

        },
        chart: {
            type: 'column',
            zoomType: 'x',
            borderWidth: 1,
            borderColor: '#108f38',
            marginRight: 15
        },
        title: {
            text: 'Production/Consumption'
        },
        credits: {
            enabled: false
        },

        xAxis: {
            type: 'datetime',
            minRange:  3600*1000

        },
        yAxis: {
            title: {
                text: 'Energy (KWh)'

            }
        },
        legend: {
            enabled: true
        },
        plotOptions: {

            area: {
                fillColor: {
                    linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1},
                    stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0.75).get('rgba')]

                    ]
                },
                marker: {
                    radius: 1
                },
                lineWidth: 1,
                states: {
                    hover: {
                        lineWidth: 1
                    }
                },
                threshold: null
            }
        },

        series: [{
            name: 'Production',
            pointInterval:  3600 * 1000,
            data: arr1,
            minPointWidth: 50


        },{
            name: 'Consumption',
            pointInterval:  3600 * 1000,
            data: arr2,
            minPointWidth: 50

        }]
    });
}
