
function mapGraph (state, url, devide ) {

    var H = Highcharts,
        map = H.maps['countries/nl/nl-all'];
    Highcharts.setOptions({
        colors: ['#59AC28']
    });

    var query = function(){
        $.ajax({
            type: 'get',
            url: url,
            crossDomain: true,
            error: function (json) {
                $('#container').html('<p style="text-align: center">Service unavailable!</p>');
                console.error(json.responseText);
            },
            statusCode: {
                200: function (json) {
                    graph(json);
                }
            }
        });
    };
    var graph = function(json) {
        // Add series with state capital bubbles
        //$.getJSON(url, function (json) {

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
        //];*/
        var data = [];
        $.each(json, function (ix, entry) {

            entry.z = new Number(entry.value/devide).toFixed(1);
            data.push(entry);
        });
        console.log(data);
        $('#container').highcharts('Map', {

            credits: {
                enabled: false
            },

            title: {
                text: 'Region '+state+' for current month'
            },

            tooltip: {
                formatter: function () {
                    return this.point.name +  '<br>'+ state + ': ' + new Number(this.point.value/devide).toFixed(1);
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
            },
            {
                type: 'mapbubble',
                showInLegend: false,
                dataLabels: {
                    enabled: true
                },
                name: 'Provinces',
                data: data,
                maxSize: '12%'
            }]
        });
        //});
    };

    query();

}
