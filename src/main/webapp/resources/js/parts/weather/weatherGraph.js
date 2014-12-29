/**
 * Created by alex on 12/28/14.
 */
//$(function () {
function weatherGraph (data) {
    var arr = new Array();
    //for (i in data) {
    //    arr[i] =  [data[i]["date"], data[i]["value"]/1000];
    //}
    for (var i = 0; i < 8; i++) {
        arr[i] =  [i, i*(Math.random()+10)];
    }

    Highcharts.setOptions({
        global: {
            useUTC: false

        },
        colors: ['#59AC28']

    });

    $('#weatherGraph').highcharts({

        chart: {
            zoomType: 'x',
            borderWidth: 1,
            borderColor: '#108f38',
            marginRight: 15

        },
        title: {
            text: 'Some Graph'
        },
        credits: {
            enabled: false
        },

        xAxis: {
            type: 'datetime',
            minRange:  3 * 36000000 // 3 hour

        },
        yAxis: {
            title: {
                text: 'mm'

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

        colors:['#59AC28'],

        series: [{
            type: 'column',
            name: 'Rain (mm)',
            pointInterval:  12*300 * 1000,
            //pointStart: arrDate[0],
            data: arr


        }]
    });
}
