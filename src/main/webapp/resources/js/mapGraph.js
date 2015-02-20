//$(function () {
//
//    $.getJSON('http://www.highcharts.com/samples/data/jsonp.php?filename=us-population-density.json&callback=?', function (data) {
//
//        data = [            {
//                "value": 438,
//                "name": "Friesland"
//            },
//            {
//                "value": 387.35,
//                "name": "Noord-Holland"
//            },
//            {
//                "value": 312.68,
//                "name": "Groningen"
//            },
//            {
//                "value": 271.4,
//                "name": "Flevoland"
//            },
//            {
//                "value": 209.23,
//                "name": "Zeeland"
//            },
//            {
//                "value": 195.18,
//                "name": "Noord-Brabant"
//            },
//            {
//                "value": 154.87,
//                "name": "Utrecht"
//            },
//            {
//                "value": 114.43,
//                "name": "Zuid-Holland"
//            },
//            {
//                "value": 107.05,
//                "name": "Drenthe"
//            },
//            {
//                "value": 105.8,
//                "name": "Gelderland"
//            },
//            {
//                "value": 86.27,
//                "name": "Limburg"
//            },
//            {
//                "value": 83.85,
//                "name": "Overijssel"
//            }
//    ];
//        // Make codes uppercase to match the map data
//        //$.each(data, function () {
//        //    this.code = this.code.toUpperCase();
//        //});
//
//
//        // Instanciate the map
//        $('#container').highcharts('Map', {
//
//            chart : {
//                borderWidth : 1
//            },
//
//            title : {
//                text : 'NL production (KWt)'
//            },
//
//            legend: {
//                layout: 'horizontal',
//                borderWidth: 0,
//                backgroundColor: 'rgba(255,255,255,0.85)',
//                floating: true,
//                verticalAlign: 'top',
//                y: 25
//            },
//
//            mapNavigation: {
//                enabled: true
//            },
//
//            colorAxis: {
//                min: 1,
//                type: 'logarithmic',
//                minColor: '#EEEEFF',
//                maxColor: '#000022',
//                stops: [
//                    [0, '#EFEFFF'],
//                    [0.67, '#4444FF'],
//                    [1, '#000022']
//                ]
//            },
//
//            series : [{
//                animation: {
//                    duration: 1000
//                },
//                data : data,
//                mapData: Highcharts.maps['countries/nl/nl-all'],
//                joinBy: ['name', 'name'],
//                dataLabels: {
//                    enabled: true,
//                    color: 'white',
//                    format: '{point.name}'
//                },
//                name: 'Population density',
//                tooltip: {
//                    pointFormat: '{point.name}: {point.value}/km²'
//                }
//            }]
//        });
//    });
//});

$(function () {

    var H = Highcharts,
        map = H.maps['countries/nl/nl-all'],
        chart;
    Highcharts.setOptions({
        colors: ['#59AC28']
    });

    // Add series with state capital bubbles
    $.getJSON('/rest/map/production/monthly', function (json) {

    //    json = [            {
    //            "value": 438,
    //            "y":-1553,
    //            "x":900,
    //            "name": "Friesland"
    //        },
    //        {
    //            "value": 387.35,
    //            "y":-1200,
    //            "x":550,
    //            "name": "Noord-Holland"
    //        },
    //        {
    //            "value": 312.68,
    //            "y":-1700,
    //            "x":1200,
    //            "name": "Groningen"
    //        },
    //        {
    //            "value": 200,
    //            "y":-1150,
    //            "x":830,
    //            "name": "Flevoland"
    //        },
    //        {
    //            "value": 209.23,
    //            "y":-500,
    //            "x":200,
    //            "name": "Zeeland"
    //        },
    //        {
    //            "value": 195.18,
    //            "y":-600,
    //            "x":700,
    //            "name": "Noord-Brabant"
    //        },
    //        {
    //            "value": 154.87,
    //            "y":-900,
    //            "x":700,
    //            "name": "Utrecht"
    //        },
    //        {
    //            "value": 300.43,
    //            "y":-800,
    //            "x":400,
    //            "name": "Zuid-Holland"
    //        },
    //        {
    //            "value": 107.05,
    //            "y":-1400,
    //            "x":1200,
    //            "name": "Drenthe"
    //        },
    //        {
    //            "value": 105.8,
    //            "y":-950,
    //            "x":1000,
    //            "name": "Gelderland"
    //        },
    //        {
    //            "value": 86.27,
    //            "y":-400,
    //            "x":1000,
    //            "name": "Limburg"
    //        },
    //        {
    //            "value": 83.85,
    //            "y":-1150,
    //            "x":1200,
    //            "name": "Overijssel"
    //        }
    //];
        var data =[];
        $.each(json, function (ix, entry) {
            entry.z = new Number(entry.value/1000).toFixed(0);
            data.push(entry);
        });
console.log(data);
        $('#container').highcharts('Map', {

            credits: {
                enabled: false
            },

            title: {
                text: 'Region production(kWt)'
            },

            tooltip: {
                formatter: function () {
                    return this.point.name +  '<br>Production kWt: ' + new Number(this.point.value/1000).toFixed(0);
                }

            },

            mapNavigation: {
                enabled: false
            },

            series: [{
                name: 'Basemap',
                mapData: map,
                borderColor: '#606060',
                nullColor: 'rgba(89, 172, 40, 0.2)',
                showInLegend: false
            }, /*{
                name: 'Separators',
                type: 'mapline',
                data: H.geojson(map, 'mapline'),
                color: '#101010',
                enableMouseTracking: true
            },*/ {
                type: 'mapbubble',
                dataLabels: {
                    enabled: true
                    //format: '{point.name}'
                },
                name: 'Provinces',
                data: data,
                maxSize: '12%'
                //color: H.getOptions().colors[7]
            }]
        });

    });


});
