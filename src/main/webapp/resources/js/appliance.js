const applianceListPower = {
    vacuum : 1500,
    desktop : 600,
    coffeemaker :1000,
    microwave : 900,
    dishwasher : 1800,
    washer : 500,
    iron : 1000,
    laptop : 100,
    television : 170,
    teapot : 2000
};


function applianceSingleData(data){
    var sum = getSum(data);
   var result = getList(sum);
    result = getTimeFormat(result);




}

function appliancePairData(prod, consupt){
    var sumProduction = getSum(prod);
    var sumConsumption = getSum(consupt);
    var result = [];
    var list = getList(sumProduction);
    result[0] = getTimeFormat(list);
    list = getList(sumConsumption);
    result[1] = getTimeFormat(list);

    $("#valTime1_0").html(sumProduction);
    $("#valTime1_1").html(sumConsumption);

    $("#val0_0").html(result[0].coffeemaker);
    console.log(result[0].coffeemaker+"");
    $("#val0_1").html(result[1].coffeemaker);
    $("#val1_0").html(result[0].desktop);
    $("#val1_1").html(result[1].desktop);
    $("#val2_0").html(result[0].vacuum);
    $("#val2_1").html(result[1].vacuum);



}

function getSum(data){
    var sum = 0;
    for (var index in data){
        if (data.hasOwnProperty(index)) {
            sum += data[index]["value"];
        }
    }
    return sum;
}
function getList(sumEnergy){
    var result = new applianceListResult();
    for (var i in applianceListPower) {
        if (applianceListPower.hasOwnProperty(i)) {
            result[i] = sumEnergy / applianceListPower[i];
        }
    }
    return result;
}
function getTimeFormat(list){
    var year =  24 * 365;
    var week = 24 * 7;
    var day = 24;
    for (var i in list){
        if (list.hasOwnProperty(i)) {
            if (list[i] >= year) {
                list[i] = Math.round(list[i] / year) + ' years';
                continue;
            }
            if (list[i] >= week) {
                list[i] = Math.round(list[i] / week) + ' weeks';
                continue;
            }
            if (list[i] >= day) {
                list[i] = Math.round(list[i] / day) + ' days';
            }
        }
    }
    return list;
}
/*
Coffee maker	900-1200 watts
Microwave	750-1100 watts
Toaster	800-1400 watts
Dishwasher	1200-2400 watts
Washer	350-500 watts
Dryer	1800-5000 watts
Iron	100-1800 watts
Ceiling fan	65-175 watts
Space heater (40gal)	4500-5500 watts
Hair dryer	1200-1875 watts
Laptop	50 watts
Computer monitor	150 watts
Computer tower	120 watts
Television 19"-36"	65-133 watts
Television 53"-61"	170 watts*/
