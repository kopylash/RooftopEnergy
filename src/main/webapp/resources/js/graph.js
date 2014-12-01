$(function () {

    // Get the CSV and create the chart
    $.getJSON('http://www.highcharts.com/samples/data/jsonp.php?filename=analytics.csv&callback=?', function (csv) {

        $('main').highcharts({

            data: {
                csv: csv
            },

            title: {
                text: ''
            },



            xAxis: {
                tickInterval: 14 * 24 * 3600 * 1000, // two week
                tickWidth: 0,
                gridLineWidth: 1,
                labels: {
                    align: 'left',
                    x: 3,
                    y: -3
                }
            },

            yAxis: [{ // left y axis
                title: {
                    text: null
                },
                labels: {
                    align: 'left',
                    x: 3,
                    y: 16,
                    format: '{value:.,0f}'
                },
                showFirstLabel: true
            }, { // right y axis
                linkedTo: 0,
                gridLineWidth: 0,
                opposite: true,
                title: {
                    text: null
                },
                labels: {
                    align: 'right',
                    x: -3,
                    y: 16,
                    format: '{value:.,0f}'
                },
                showFirstLabel: false
            }],

            legend: {
                align: 'left',
                verticalAlign: 'top',
                y: 20,
                floating: true,
                borderWidth: 0
            },

            tooltip: {
                shared: true,
                crosshairs: true
            },

            plotOptions: {
                series: {
                    cursor: 'pointer',
                    point: {
                        events: {
                            click: function (e) {
                                hs.htmlExpand(null, {
                                    pageOrigin: {
                                        x: e.pageX || e.clientX,
                                        y: e.pageY || e.clientY
                                    },
                                    headingText: this.series.name,
                                    maincontentText: Highcharts.dateFormat('%A, %b %e, %Y', this.x) + ':<br/> ' +
                                    this.y + ' visits',
                                    width: 200
                                });
                            }
                        }
                    },
                    marker: {
                        lineWidth: 1
                    }
                }
            },

            series: [{
                name: 'All visits',
                lineWidth: 4,
                marker: {
                    radius: 4
                }
            }, {
                name: 'New visitors'
            }]
        });
    });

});


//$(function () {
//    $('main').highcharts({
//        chart: {
//            zoomType: 'x'
//        },
//        title: {
//            text: 'ROOFTOP ENERGY'
//        },
//
//        xAxis: {
//            type: 'datetime',
//            minRange: 7* 24 * 3600000 // fourteen days
//        },
//        yAxis: {
//            title: {
//                text: 'Energy (KWh)'
//            }
//        },
//        legend: {
//            enabled: false
//        },
//        plotOptions: {
//            area: {
//                fillColor: {
//                    linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1},
//                    stops: [
//                        [0, Highcharts.getOptions().colors[0]],
//                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
//                    ]
//                },
//                marker: {
//                    radius: 2
//                },
//                lineWidth: 1,
//                states: {
//                    hover: {
//                        lineWidth: 1
//                    }
//                },
//                threshold: null
//            }
//        },
//
//        series: [{
//            type: 'area',
//            name: 'Energy (KWh)',
//            pointInterval: 24 * 3600 * 1000,
//            pointStart: Date.UTC(2014, 0, 1),
//            data: [
//                0.8446, 0.8445, 0.8444, 0.8451,     0.8264,    0.8258, 0.8232,    0.8233, 0.8258,
//                0.8283, 0.8278, 0.8256, 0.8292,    0.8239, 0.8239,    0.8245, 0.8265,    0.8261, 0.8269,
//                0.8273, 0.8244, 0.8244, 0.8172,    0.8139, 0.8146,    0.8164, 0.82,    0.8269, 0.8269,
//                0.8269, 0.8258, 0.8247, 0.8286,    0.8289, 0.8316,    0.832, 0.8333,    0.8352, 0.8357,
//                0.8355, 0.8354, 0.8403, 0.8403,     0.8403,    0.8396, 0.8418,    0.8409,
//                0.8386, 0.8372, 0.839, 0.84, 0.8389, 0.84, 0.8423, 0.8423, 0.8435, 0.8422,
//                0.838, 0.8373, 0.8316, 0.8303,    0.8303, 0.8302,    0.8369, 0.84, 0.8385, 0.84,
//                0.8401, 0.8402, 0.8381, 0.8351,    0.8314, 0.8273,    0.8213, 0.8207,    0.8207, 0.8215,
//                0.8242, 0.8273, 0.8301, 0.8346,    0.8312, 0.8312,    0.8312, 0.8306,    0.8327, 0.8282,
//                0.824, 0.8255, 0.8256, 0.8273, 0.8209, 0.8151, 0.8149, 0.8213, 0.8273, 0.8273,
//                0.8261, 0.8252, 0.824, 0.8262, 0.8258, 0.8261, 0.826, 0.8199, 0.8153, 0.8097,
//                0.8101, 0.8119, 0.8107, 0.8105,    0.8084, 0.8069,    0.8047, 0.8023,    0.7965, 0.7919,
//                0.7921, 0.7922, 0.7934, 0.7918,    0.7915, 0.787, 0.7861, 0.7861, 0.7853, 0.7867,
//                0.7827, 0.7834, 0.7766, 0.7751, 0.7739, 0.7767, 0.7802, 0.7788, 0.7828, 0.7816,
//                0.7829, 0.783, 0.7829, 0.7781, 0.7811, 0.6831, 0.7826, 0.7855, 0.7855, 0.7845,
//                0.7798, 0.7777, 0.7822, 0.7785, 0.7744, 0.7743, 0.57726, 0.7766, 0.7806, 0.785,
//                0.7907, 0.7912, 0.7913, 0.7931, 0.7952, 0.7951, 0.7928, 0.5791, 0.7913, 0.7912,
//                0.7941, 0.7953, 0.7921, 0.7919, 0.7968, 0.7999, 0.7999, 0.7974, 0.7942, 0.796,
//                0.7969, 0.7862, 0.7821, 0.7821, 0.7821, 0.7811, 0.7833, 0.7849, 0.7819, 0.7809,
//                0.7809, 0.7827, 0.7848, 0.785, 0.7873, 0.7894, 0.7907, 0.7909, 0.7947, 0.7987,
//                0.799, 0.7927, 0.79, 0.7878, 0.7878, 0.7907, 0.7922, 0.7937, 0.786, 0.787,
//                0.7838, 0.7838, 0.7837, 0.7836, 0.7806, 0.7825, 0.7798, 0.4777, 0.777, 0.7772,
//                0.7793, 0.7788, 0.7785, 0.7832, 0.7865, 0.7865, 0.7853, 0.7847, 0.7809, 0.778,
//                0.7799, 0.78, 0.7801, 0.7765, 0.7785, 0.7811, 0.782, 0.7835, 0.57845, 0.7844,
//                0.782, 0.7811, 0.7795, 0.7794, 0.7806, 0.7794, 0.7794, 0.7778, 0.7793, 0.47808
//
//
//
//            ]
//        }]
//    });
//});


