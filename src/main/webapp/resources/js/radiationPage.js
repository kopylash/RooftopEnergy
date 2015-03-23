$(function(){

    var angle =  $("#angle").val();
    var square = $("#square").val();
    //var chosenDate = new Date();
    var chosenDate = $.datepicker.formatDate("yy-mm-dd", new Date());
    var chosenDay = 1;
    var regExpAngle = /(^\d{1,2}$)|(^\d{1,2}\.\d{1,2}$)/g;
    var regExpSquare = /(^\d{1,3}$)|(^\d{1,3}\.\d{1,2}$)/g;
////////DATEPICKER////////////////////////////
    $( "#datepicker" ).datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd-mm-yy"
    });

    $("#datepicker").datepicker("setDate", "dd-mm-yy");
    var k = $.datepicker.formatDate("dd-mm-yy", new Date());
    chosenDate =  $("#datepicker").datepicker("getDate");
    console.log(chosenDate);
    $( "#datepicker").text(k);
    $("#datepicker").change(function(){

        console.log("ww "+ chosenDate);
        ajaxGraphYear();
        powerD();

    });
///////////END DATEPICKER /////////////////////////////////
//    $('#radiationText span').html("120.30");
    $("#butDate").click(function(){

        angle = $("#angle").val();
        square = $("#square").val();
        var regExpAngleVar = angle.search(regExpAngle);
        var regExpSquareVar = square.search(regExpSquare);
        //console.log("s="+regExpSquareVar+" a= "+regExpAngleVar);
        if(regExpAngleVar == -1 || regExpSquareVar == -1){
            var wrongData = "<p>You have entered incorrect data!</p>";
            $("#wrongInf").html(wrongData).css({'display':'block'});
        } else{
            powerD();
            ajaxGraphYear();
        }
    });
    powerD();
    ajaxGraphYear();


    $("input").focus(function(){
        $("#wrongInf").css({"display":"none"});
    });

    function ajaxGraphYear(){
        var code = Object.create(errorCode);
        code['200'] = function(json){
            powerGraph(json);
        };
        code['500'] = function(json){
            $('#container').html('<p style="text-align: center">Service unavailable!</p>');
            console.error(json.responseText);
        };
        $.ajax({
            type: 'post',
            url: "/rest/radiation/year",
            data:{'tilt' : angle},
            crossDomain: true,
            statusCode:code
        });
    }

    function powerDay(json, date){
        var data = json.data;
        var value = new Number(data[date]) * square;
       $('#radiationText span').html(value.toFixed(2));
    }

    function ajaxDay(date){
        var code = Object.create(errorCode);
        code['200'] = function(json){
            powerDay(json, date);
        };
        code['500'] = function(json){
            $('#radiationText').html('<p style="text-align: center">Service unavailable!</p>');
            console.error(json.responseText);
        };
        $.ajax({
            type: 'post',
            url: "/rest/radiation/day",
            data:{'tilt' : angle, 'day': date},
            crossDomain: true,
            statusCode:code
        });
    }

    function powerD(){
        chosenDate =  $("#datepicker").datepicker("getDate");
        var now = chosenDate;
        var start = new Date(now.getFullYear(), 0, 1);
        var diff = now - start;
        var oneDay = 1000 * 60 * 60 * 24;
        var day = Math.floor(diff / oneDay);
        console.log('day = ' + day);
        chosenDay = day;
        ajaxDay(day);
    }


//////////Graph//////////////////////////////////////
    function powerGraph(json) {
        //console.log(json);
        var dateNow = new Date();
        var year = dateNow.getFullYear();
        var date = new Date(year,0,1);
        //console.log(date);
        var obj = json.data;
        var arr = [];
        for (var i = 0; i<365; i++){
            //arr[i] = [i, obj[i]];
            arr[i] = [date.getTime()+i*24*3600*1000, parseFloat((obj[i+1]*square).toFixed(2))];
        }

        Highcharts.setOptions({
            global: {
                useUTC: true
           },
            colors: ['#59AC28']
        });

        $('#container').highcharts({
            chart: {
                zoomType: 'x',
                borderWidth: 1,
                borderColor: '#108f38'
            },
            credits: {
                enabled: false
            },

            title: {
                text: 'Energy production per current year, kWt'
            },

            xAxis: {
                //categories: arrX
                type: 'datetime',
                minRange:  24 * 3600000 // one day
            },
            yAxis: {
                title: {
                    text: 'Energy production, kWt'
                }
            },
            legend: {
                enabled: true
            },
            tooltip: {
                xDateFormat: "%a, %d %b %Y"
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {x1: 0, y1: 0, x2: 0, y2: 1},
                        stops: [
                            [0, Highcharts.getOptions().colors[0]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
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
            colors: ['#59AC28'],
            series: [{
                type: 'area',
                name: 'Production energy, kWt',
                showInLegend: false,
                pointInterval: 24 * 3600 * 1000,
                //pointStart: Date.UTC(2006, 0, 1),

                data: arr
            },
                {
                    name: 'Current day',
                    showInLegend: false,
                  data: [ {
                    x: chosenDate.getTime(),
                      y: parseFloat((obj[chosenDay+1]*square).toFixed(2)),
                    marker: {
                        symbol: 'url(http://www.highcharts.com/demo/gfx/sun.png)'
                    }
                }]
                }]
        });
    }
////////End graph///////////////
    $(".ui-loader h1").css({'display':'none'});
});
