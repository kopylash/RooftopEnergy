/**
 * Created by alex on 1/12/15.
 */
$(function(){
    var Energy = function(){
        this.production = 0;
        this.consumption = 0;
    };
    var trees = 0;
    var carbon = 0;
    var month = new Energy();
    var year = new Energy();
    var tot = new Energy();

    /*Testing data ---> */
   /* trees = 0.26;
    carbon = 0.12;
    month.production = 30.4;
    month.consumption = 10.4;
    year.production = 300.56;
    year.consumption = 200.65;
    tot.production = 558888.43;
    tot.consumption = 5588.43;*/
    /*Testing data ----X */

    function ajaxTreesQuery() {
        var code = Object.create(errorCode);
        code['200'] = function(data){
            trees = new Number(data).toFixed(2);
        };
        code['500'] = function(data){
            console.error(data.responseText);
        };
        $.ajax({
            type: 'post',
            url: '/rest/eco/trees',
            crossDomain: true,
            statusCode:code
        });
    }
    function ajaxCarbonQuery() {
        var code = Object.create(errorCode);
        code['200'] = function(data){
            carbon = new Number(data/1000).toFixed(2);
        };
        code['500'] = function(data){
            console.error(data.responseText);
        };
        $.ajax({
            type: 'post',
            url: '/rest/eco/carbonOffset',
            crossDomain: true,
            statusCode:code
        });
    }
    function ajaxTotalMonthQuery(type) {
        var code = Object.create(errorCode);
        code['200'] = function(data){
            month[type] = new Number(data).toFixed(1);
        };
        code['500'] = function(data){
            console.error(data.responseText);
        };
        $.ajax({
            type: 'post',
            url: '/rest/'+type+'/thisMonthTotal',
            crossDomain: true,
            statusCode:code
        });
    }
    function ajaxTotalYearQuery(type) {
        var code = Object.create(errorCode);
        code['200'] = function(data){
            year[type] = new Number(data).toFixed(1);
        };
        code['500'] = function(data){
            console.error(data.responseText);
        };
        $.ajax({
            type: 'post',
            url: '/rest/'+type+'/thisYearTotal',
            crossDomain: true,
            statusCode:code
        });
    }
    function ajaxTotalTotalQuery(type) {
        var code = Object.create(errorCode);
        code['200'] = function(data){
            tot[type] = new Number(data).toFixed(1);
        };
        code['500'] = function(data){
            console.error(data.responseText);
        };
        $.ajax({
            type: 'post',
            url: '/rest/'+type+'/total_'+type,
            crossDomain: true,
            statusCode:code

        });
    }

    ajaxTreesQuery();
    ajaxCarbonQuery();

    var pageName = currentPage.name;
    if (pageName == 'home') {
        ajaxTotalQuery('production');
        ajaxTotalQuery('consumption');
    } else {
        ajaxTotalQuery(pageName);
    }

    function ajaxTotalQuery(energy){
        ajaxTotalMonthQuery(energy);
        ajaxTotalYearQuery(energy);
        ajaxTotalTotalQuery(energy);
    }

   var htmlCode = '<div id="ecoTrees" class="ecoRow"></div>\
       <div id="ecoCarbon" class="ecoRow"></div>\
       <div id="totalMonthly" class="energyRow"></div>\
       <div id="totalYearly" class="energyRow"></div>\
       <div id="totalTotal" class="energyRow"></div>';

    var whatDevice = device.mobile() || device.tablet();
    var landscape = (screen.width <= SCREEN_RESOLUTION.width) && (screen.height <= SCREEN_RESOLUTION.height);
    var portrait = (screen.width <= SCREEN_RESOLUTION.height) && (screen.height <= SCREEN_RESOLUTION.width);

    $(window).resize(function () {
        if (whatDevice || landscape || portrait) {
            $("#ecoSection").html("");
            $("#mobileEcoValues").html(htmlCode).addClass("mobilEco");
            $("#ecoSection").removeClass("eco");
            fillEcoTrees(trees);
            fillEcoCarbon(carbon);
            fillEnergyValues(month, year, tot);
        } else {
            $("#mobileEcoValues").html("").removeClass("mobilEco");
            $("#ecoSection").addClass("eco");
            $("#ecoSection").html(htmlCode);
            fillEcoTrees(trees);
            fillEcoCarbon(carbon);
            fillEnergyValues(month, year, tot);
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
        var monthHtml;
        var yearHtml;
        var totalHtml;

        if (pageName === 'home'){
            monthHtml = '<div class="energyTitle">Monthly energy</div>'+
            '<div id="monthValue" class="energyValue">'+ ENERGY_ICO.production +' <span>' + toKWt(m.production) + '</span> KWh</div>'+
            '<div id="otherMonthValue" class="energyValue">'+ ENERGY_ICO.consumption +' <span>' + toKWt(m.consumption) + '</span> KWh</div>';

            yearHtml = '<div class="energyTitle">Yearly energy</div>'+
            '<div id="yearValue" class="energyValue">'+ ENERGY_ICO.production +' <span>' + toKWt(y.production) + '</span>  KWh</div>'+
            '<div id="otherYearValue" class="energyValue">'+ ENERGY_ICO.consumption +' <span>' + toKWt(y.consumption) + '</span>  KWh</div>';

            totalHtml = '<div class="energyTitle">Total energy</div>'+
            '<div id="totalValue" class="energyValue">'+ ENERGY_ICO.production +' <span>' + toKWt(t.production) + '</span> KWh</div>'+
            '<div id="otherTotalValue" class="energyValue">'+ ENERGY_ICO.consumption +' <span>' + toKWt(t.consumption) + '</span> KWh</div>';

        } else {
            monthHtml = '<div class="energyTitle">Monthly energy</div>'+
            '<div id="monthValue" class="energyValue">'+ ENERGY_ICO[pageName] +' <span>' + toKWt(m[pageName]) + '</span> KWh</div>';

            yearHtml = '<div class="energyTitle">Yearly energy</div>'+
            '<div id="yearValue" class="energyValue">'+ ENERGY_ICO[pageName] +' <span>' + toKWt(y[pageName]) + '</span>  KWh</div>';

            totalHtml = '<div class="energyTitle">Total energy</div>'+
            '<div id="totalValue" class="energyValue">'+ ENERGY_ICO[pageName] +' <span>' + toKWt(t[pageName]) + '</span> KWh</div>';
        }
        $("#totalMonthly").html(monthHtml);
        $("#totalYearly").html(yearHtml);
        $("#totalTotal").html(totalHtml);
    }

    $(document).ajaxComplete(function(){

        $("#treeValue span").html(trees);
        $("#carbonValue span").html(carbon);
        if (pageName === 'home') {
            $("#monthValue span").html(toKWt(month.production));
            $("#yearValue span").html(toKWt(year.production));
            $("#totalValue span").html(toKWt(tot.production));
            $("#otherMonthValue span").html(toKWt(month.consumption));
            $("#otherYearValue span").html(toKWt(year.consumption));
            $("#otherTotalValue span").html(toKWt(tot.consumption));
        } else {
            $("#monthValue span").html(toKWt(month[pageName]));
            $("#yearValue span").html(toKWt(year[pageName]));
            $("#totalValue span").html(toKWt(tot[pageName]));
        }
        });
    function toKWt(number){
        return new Number(number/1000).toFixed(2);
    }
});

