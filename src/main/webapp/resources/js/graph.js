    function graph (data, tooltipDateFormat) {
        var arr = new Array();
        for (var i in data) {
            if (data.hasOwnProperty(i)) {
                arr[i] = [data[i]["date"], data[i]["value"] / 1000];
            }
        }

        Highcharts.setOptions({
            global: {
                useUTC: true
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

            tooltip: {
                xDateFormat: tooltipDateFormat
            },

            colors:['#59AC28'],

            series: [{
                type: 'column',
                name: 'Energy (KWh)',
                pointInterval:  12*300 * 1000,
                data: arr
            }]
        });
    }
