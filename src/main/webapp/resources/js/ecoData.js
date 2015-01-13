/**
 * Created by alex on 1/12/15.
 */
$(function(){
    var trees = 0;
    var carbon = 0;
    var month = 0;
    var year = 0;
    var tot = 0;
    /*Testing data ---> */
    trees = 0.26;
    carbon = 0.12;
    month = 30.4;
    year = 300.56;
    tot = 558888.43;


    /*Testing data ----X */

    function ajaxTreesQuery() {
        $.ajax({
            type: 'post',
            url: '/rest/eco/trees',
            crossDomain: true,
            error: function (data) {
                $('#main').html(data.responseText);
            },
            statusCode: {
                200: function (data) {
                    trees = new Number(data).toFixed(2);
                }
            }
        });
    }
    function ajaxCarbonQuery() {
        $.ajax({
            type: 'post',
            url: '/rest/eco/carbonOffset',
            crossDomain: true,
            error: function (data) {
                $('#main').html(data.responseText);
            },
            statusCode: {
                200: function (data) {
                    carbon = new Number(data/1000).toFixed(2);
                }
            }
        });
    }
    function ajaxTotalMonthQuery(type) {
        $.ajax({
            type: 'post',
            url: '/rest/'+type+'/thisMonthTotal',
            crossDomain: true,
            error: function (data) {
                $('#main').html(data.responseText);
            },
            statusCode: {
                200: function (data) {
                    month = new Number(data).toFixed(1);
                }
            }
        });
    }
    function ajaxTotalYearQuery(type) {
        $.ajax({
            type: 'post',
            url: '/rest/'+type+'/thisYearTotal',
            crossDomain: true,
            error: function (data) {
                $('#main').html(data.responseText);
            },
            statusCode: {
                200: function (data) {
                    year = new Number(data).toFixed(1);
                }
            }
        });
    }
    function ajaxTotalTotalQuery(type) {
        $.ajax({
            type: 'post',
            url: '/rest/'+type+'/total_production',
            crossDomain: true,
            error: function (data) {
                $('#main').html(data.responseText);
            },
            statusCode: {
                200: function (data) {
                    tot = new Number(data).toFixed(1);
                }
            }
        });
    }

    ajaxTreesQuery();
    ajaxCarbonQuery();

    var pageName = currentPage.name;
    ajaxTotalMonthQuery(pageName);
    ajaxTotalYearQuery(pageName);
    ajaxTotalTotalQuery(pageName);


   var htmlCode = '<div id="ecoTrees" class="ecoRow"></div>\
       <div id="ecoCarbon" class="ecoRow"></div>\
       <div id="totalMonthly" class="energyRow"></div>\
       <div id="totalYearly" class="energyRow"></div>\
       <div id="totalTotal" class="energyRow"></div>';


    $(window).resize(function () {
        if (screen.width > 768) {
            $("#ecoSection").addClass("eco");
            $("#ecoSection").html(htmlCode);
            fillEcoTrees(trees);
            fillEcoCarbon(carbon);
            fillEnergyValues(month, year, tot);


        } else {
            $("#ecoSection").clear();
            $("#ecoSection").removeClass("eco");

        }
    });

    $(window).resize();

    function fillEcoTrees(tr){
        var treeHtml ='<div id="treeIco" class="icon-tree"></div>\
                        <div class="ecoValueWrapper">\
                            <div id="treeTitle" class="ecoValue">Trees<br>saved</div>\
                            <div id="treeValue" class="ecoValue">Trees: <span>'+tr+'</span></div>\
                        </div>';
        $("#ecoTrees").html(treeHtml);
    }

    function fillEcoCarbon(carb){
        var carbonHtml ='<div id="carbonIco" class="icon-factory"></div>\
                        <div class="ecoValueWrapper">\
                            <div id="carbonTitle" class="ecoValue">Carbon<br>offset</div>\
                            <div id="carbonValue" class="ecoValue">Ton: <span>'+carb+'</span></div>\
                        </div>';
        $("#ecoCarbon").html(carbonHtml);
    }

    function fillEnergyValues(m, y, t){
        var monthHtml = '<div class="energyTitle">Monthly energy</div>'+
                         '<div id="monthValue" class="energyValue">KWh <span>' + toKWt(m) + '</span></div>';
        $("#totalMonthly").html(monthHtml);

        var yearHtml = '<div class="energyTitle">Yearly energy</div>'+
            '<div id="yearValue" class="energyValue">KWh <span>' + toKWt(y) + '</span></div>';
        $("#totalYearly").html(yearHtml);

        var totalHtml = '<div class="energyTitle">Total energy</div>'+
            '<div id="totalValue" class="energyValue">KWh <span>' + toKWt(t) + '</span></div>';
        $("#totalTotal").html(totalHtml);

    }

    $(document).ajaxComplete(function(){
        $("#treeValue span").html(trees);
        $("#carbonValue span").html(carbon);
        $("#monthValue span").html(toKWt(month));
        $("#yearValue span").html(toKWt(year));
        $("#totalValue span").html(toKWt(tot));
    });

    function toKWt(number){
        return new Number(number/1000).toFixed(2);
    }

});

