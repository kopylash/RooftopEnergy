/**
 * Created by Владислав on 17.01.2015.
 */

$(function(){
    currentPage.name = 'Comparing';
    var myCompany;

    var restUrl = '/rest/comparing/production';
    var comparingType='production';
    var compareIcons = {
        tree : '<div class="icon-tree compare"></div><div class="compareValue"></div>',
        carbon : '<div class="icon-factory compare"><div class="compareValue"></div>',
        panel:'<div class="icon-solarpanel compare"></div><div class="compareValue"></div>'
    };
    var query = window.location.search.substring(1);
    var vars = query.split('=');
    var str=vars[1];
    var comparingCompanyName = str.replace(/%20/," ");

    function ajaxGetCompanyName() {
        $.ajax({
            type: 'get',
            url: "/rest/boxData/getUserDescription",
            crossDomain: true,
            error: function (data) {
                console.error(data.responseText);
            },
            statusCode: {
                200: function (data) {
                    myCompany = data.company;
                }
            },
            complete: function(jqXHR,textStatus) {
                allDateButtons(restUrl);
                ajaxComparingInfoQuery();
            }
        });
    }

    ajaxGetCompanyName();

    function allDateButtons(url1){
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

        var buttonClicker = function(url1, x, d){
            var date = new Date();
            switch(x){
                case 0:
                    url1 = url1+"/totally";
                    break;
                case 1:
                    url1 = url1+"/daily";
                    break;
                case 2:
                    url1 = url1+"/monthly";
                    break;
                case 3:
                    url1 = url1+"/yearly";
                    break;
                default :
            }
            ajaxGraphQuery(url1,comparingCompanyName,pickerDate);
        };

        buttonClicker(url1,1,pickerDate);

        $("#day").click(function() {
            buttonStyles(this.id);
            season = 1;
            buttonClicker(url1, season,pickerDate);
        });

        $("#month").click(function(){
            buttonStyles(this.id);
            season = 2;
            buttonClicker(url1,season,pickerDate);
        });

        $("#year").click(function(){
            buttonStyles(this.id);
            season = 3;
            buttonClicker(url1,season,pickerDate);
        });

        $("#total").click(function(){
            buttonStyles(this.id);
            season = 0;
            buttonClicker(url1, season, pickerDate);
        });

        $("#datepicker").change(function(){
            picButtons();
            buttonClicker(url1, season ,pickerDate);
        });

        $('#comparingProduction').click(function() {
            restUrl='/rest/comparing/production';
            comparingType='production';
            $(this).css({"color":"#108f38", "borderColor":"#108f38", "backgroundColor":"#fffffe"});
            $('#comparingConsumption').css({"color":"#fffffe", "backgroundColor":"#108f38"});
            buttonClicker(restUrl,1,pickerDate);
        });

        $('#comparingConsumption').click(function() {
            restUrl='/rest/comparing/consumption';
            comparingType='consumption';
            $('#comparingProduction').css({"color":"#fffffe", "backgroundColor":"#108f38"});
            $(this).css({"color":"#108f38", "borderColor":"#108f38", "backgroundColor":"#fffffe"});
            buttonClicker(restUrl,1,pickerDate);
        });

    }

    function ajaxGraphQuery(strUrl1,companyName,endDate) {
        $.ajax({
                type: 'post',
                url: strUrl1,
                crossDomain: true,
                data: { 'companyName':myCompany,
                    'date': endDate.getTime()},
                error: function (data1) {
                    $('main').html("<h3 style='text-align: center'>Service Unavailable!</h3>");
                    console.error(data1.responseText);
                },
                statusCode: {
                    200: function (data1) {
                        $.ajax({
                            type: 'post',
                            url: strUrl1,
                            crossDomain: true,
                            data: { 'companyName':companyName,
                                'date': endDate.getTime()},
                            error: function (data2) {
                                $('main').html("<h3 style='text-align: center'>Service Unavailable!</h3>");
                                console.error(data2.responseText);
                            },
                            statusCode: {
                                200: function (data2) {
                                    graph(data1,data2);
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
            if (data1.hasOwnProperty(i)) {
                arr1[i] = [data1[i]["date"], data1[i]["value"] / 1000];
            }
        }
        var arr2 = new Array();
        for (var i in data2) {
            if (data2.hasOwnProperty(i)) {
                arr2[i] = [data2[i]["date"], data2[i]["value"] / 1000];
            }
        }

        Highcharts.setOptions({
            global: {
                useUTC: false
            },
            colors: ['#59AC28','#DC143C']
        });

        $('main').highcharts({
            chart: {
                type: 'column',
                zoomType: 'x',
                borderWidth: 1,
                borderColor: '#108f38',
                marginRight: 15
            },
            title: {
                text: 'Comparing '+comparingType
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
                name: myCompany,
                pointInterval:  3600 * 1000,
                data: arr1
            },{
                name: comparingCompanyName,
                pointInterval:  3600 * 1000,
                data: arr2
            }]
        });
    }
    var htmlCodeEco = '<div class="comparingEco">\
        <div class="comparingTrees">\
    <div id="ourCompanyTrees"></div>\
    <div id="theirCompanyTrees"></div>\
    </div>\
    <div class="comparingCarbon">\
    <div id="ourCompanyCarbon"></div>\
    <div id="theirCompanyCarbon"></div>\
    </div>\
    <div class="comparingPanel">\
    <div id="ourCompanyPanel"></div>\
    <div id="theirCompanyPanel"></div>\
    </div>\
    </div>';
    var htmlCompareCompanies = '<div id="ourCompanyEnergy">\
    <div id="ourCompanyTitle" class="compareCompanyTitle"></div>\
         <div id="ourCompanyProduction" class="comparingCompanyEnergyValue"></div>\
        <div id="ourCompanyConsumption" class="comparingCompanyEnergyValue"></div>\
    </div>\
        <div id="theirCompanyEnergy">\
         <div id="theirCompanyTitle" class="compareCompanyTitle"></div>\
            <div id="theirCompanyProduction" class="comparingCompanyEnergyValue"></div>\
            <div id="theirCompanyConsumption" class="comparingCompanyEnergyValue"></div>\
        </div>';

    var whatDevice = device.mobile() || device.tablet();
    var landscape = (screen.width <= 1300) && (screen.height <= 1000);
    var portrait = (screen.width <= 1000) && (screen.height <= 1300);

    var codeButtons ='<button type="button" class="btn btn-default btn-sm" id="comparingProduction">Production</button>\
    <button type="button" class="btn btn-default btn-sm" id="comparingConsumption">Consumption</button>';

    if (whatDevice || landscape || portrait) {
        $("#desktopVersionButtons").html("");
        $("#typeButtons").html(codeButtons);
        $("#ecoComparing").html("");
        $("#compareCompanyInfo").css({'width':0});
        $("#mobileVersionEcoComparingEnergy").html(htmlCompareCompanies).addClass("ecoComparingEnergyMobile");
        $("#ecoComparing").html(htmlCodeEco).addClass("ecoComparingMobile");
        $("#ecoComparing div div").addClass("ecoComparingMobile");
        ajaxComparingInfoQuery();
    } else {
        $("#mobileVersionEcoComparingEnergy").html("");
        $("#desktopVersionButtons").html(codeButtons).addClass("buttonForDesktop");
        $("main").addClass("mainDesktop");
        $("#compareCompanyInfo").html(htmlCompareCompanies);
        $("#ecoComparing").html(htmlCodeEco).addClass("ecoComparingDesktop");
        ajaxComparingInfoQuery();
    }

    function ajaxComparingInfoQuery() {
        $.ajax({
            type: 'post',
            url:'/rest/comparing/comparingInfo',
            crossDomain: true,
            data: { 'comparingCompany':comparingCompanyName },
            error: function (data1) {
                $('#main').html("<h3 style='text-align: center'>Service Unavailable!</h3>");
                console.error(data1.responseText);
                //$('#main').html(data1.responseText);
            },
            statusCode: {
                200: function (data) {
                   //here will be code to parse comparingInfo
                    initOurData(data);
                }
            }
        });
    }

    function initOurData(data){
        $("#ourCompanyTrees").html(compareIcons.tree);
        $("#theirCompanyTrees").html(compareIcons.tree);
        $("#ourCompanyCarbon").html(compareIcons.carbon);
        $("#theirCompanyCarbon").html(compareIcons.carbon);
        $("#ourCompanyPanel").html(compareIcons.panel);
        $("#theirCompanyPanel").html(compareIcons.panel);

        $("#ourCompanyTrees .compareValue").html(new Number(data[0].treesSaved).toFixed(2));
        $("#theirCompanyTrees .compareValue").html(new Number(data[1].treesSaved).toFixed(2));
        $("#ourCompanyCarbon .compareValue").html(new Number(data[0].carbonOffset/1000).toFixed(2));
        $("#theirCompanyCarbon .compareValue").html(new Number(data[1].carbonOffset/1000).toFixed(2));
        $("#ourCompanyPanel .compareValue").html(data[0].solarPanels);
        $("#theirCompanyPanel .compareValue").html(data[1].solarPanels);
        $("#ourCompanyTitle").html(companyInfo.name);
        $("#ourCompanyProduction").html(ENERGY_ICO.production + " " + new Number(data[0].totalProduction/1000).toFixed(2)+ " KWh");
        $("#ourCompanyConsumption").html(ENERGY_ICO.consumption + " " +new Number(data[0].totalConsumption/1000).toFixed(2)+ " KWh");
        $("#theirCompanyProduction").html(ENERGY_ICO.production + " " + new Number(data[1].totalProduction/1000).toFixed(2)+ " KWh");
        $("#theirCompanyConsumption").html(ENERGY_ICO.consumption + " " + new Number(data[1].totalConsumption/1000).toFixed(2)+ " KWh");
        $("#theirCompanyTitle").html(comparingCompanyName);
    }
});

