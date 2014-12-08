//$(function () {
    function graph (data) {
        var arr = new Array();
        for (i in data) {
            arr[i] =  [data[i]["date"], data[i]["value"]/1000];
        }

        Highcharts.setOptions({
            global: {
                useUTC: false
            }

        });

        $('main').highcharts({

            chart: {
                zoomType: 'x'
            },
            title: {
                text: 'ROOFTOP ENERGY'
            },
            credits: {
                enabled: false
            },

            xAxis: {
                type: 'datetime'
                //minRange:  300000 // 5 min

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
                            [0, Highcharts.getOptions().colors[2]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[2]).setOpacity(0.8).get('rgba')]
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

            colors:['#00ff33'],

            series: [{
                type: 'area',
                name: 'Energy (KWh)',
                //pointInterval:  300 * 1000,
                //pointStart: arrDate[0],
                data: arr


            }]
        });
    }


//});


