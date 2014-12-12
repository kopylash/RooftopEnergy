//$(function () {
    function graph (data) {
        var arr = new Array();
        for (i in data) {
            arr[i] =  [data[i]["date"], data[i]["value"]/1000];
        }

        Highcharts.setOptions({
            global: {
                useUTC: false

            },
            colors: ['#59AC28']

        });

        $('main').highcharts({

            chart: {
                zoomType: 'x',
                borderWidth: 1,
                borderColor: '#108f38',
                marginRight: 15
            },
            title: {
                text: 'ROOFTOP ENERGY'
            },
            credits: {
                enabled: false
            },

            xAxis: {
                type: 'datetime',
                minRange:  12 * 300000 // 5 min

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

            colors:['#59AC28'],

            series: [{
                type: 'column',
                name: 'Energy (KWh)',
                pointInterval:  12*300 * 1000,
                //pointStart: arrDate[0],
                data: arr


            }]
        });
    }


//});


//function graph (data) {
//        var arr = new Array();
//        for (i in data) {
//            arr[i] =  [data[i]["date"], data[i]["value"]/1000];
//        }
//
//
//        // Create the chart
//        $('#main').highcharts('StockChart', {
//
//
//            rangeSelector : {
//                selected : 1
//            },
//
//            title : {
//                text : 'AAPL Stock Price'
//            },
//
//            series : [{
//                name : 'AAPL Stock Price',
//                data : arr,
//                type : 'areaspline',
//                threshold : null,
//                tooltip : {
//                    valueDecimals : 2
//                },
//                fillColor : {
//                    linearGradient : {
//                        x1: 0,
//                        y1: 0,
//                        x2: 0,
//                        y2: 1
//                    },
//                    stops : [
//                        [0, Highcharts.getOptions().colors[0]],
//                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
//                    ]
//                }
//            }]
//        });
//}


//function graph (data) {
//    var arr = new Array();
//    for (i in data) {
//        arr[i] =  [data[i]["date"], data[i]["value"]/1000];
//    }
//
//    Highcharts.setOptions({
//        global: {
//            useUTC: false
//
//        },
//        colors: ['#59AC28']
//
//    });
//
//    // create the chart
//    $('#main').highcharts('StockChart', {
//        chart: {
//            alignTicks: false
//        },
//
//        rangeSelector: {
//            selected: 1
//        },
//
//        title: {
//            text: 'AAPL Stock Volume'
//        },
//
//        series: [{
//            type: 'column',
//            name: 'AAPL Stock Volume',
//            data: arr,
//            dataGrouping: {
//                units: [[
//                    'week', // unit name
//                    [1] // allowed multiples
//                ], [
//                    'month',
//                    [1, 2, 3, 4, 6]
//                ]]
//            }
//        }]
//    });
//}