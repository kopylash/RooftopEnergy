/**
 * Created by alex on 1/12/15.
 */
$(function(){
    /*Testing data ---> */
      var trees = 0.26;
      var carbon = 0.12;
    var month = 30.4;
    var year = 300.56;
    var tot = 558888.43;


    /*Testing data ----X */


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
                         '<div class="energyValue">KWh ' + m + '</div>';
        $("#totalMonthly").html(monthHtml);

        var yearHtml = '<div class="energyTitle">Yearly energy</div>'+
            '<div class="energyValue">KWh ' + y + '</div>';
        $("#totalYearly").html(yearHtml);

        var totalHtml = '<div class="energyTitle">Total energy</div>'+
            '<div class="energyValue">KWh ' + t + '</div>';
        $("#totalTotal").html(totalHtml);

    }
});

