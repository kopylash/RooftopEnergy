
    const totalNote = {
        daily : 'Day',
        monthly : 'Month',
        yearly : 'Year',
        totally : 'Total'
    };
    var ApplianceList = function(){
        this.vacuum = applianceUnit("<i class='icon-vacuming' title='Vacuum cleaner'></i>", 1500);
        this.desktop = applianceUnit("<i class='icon-desktop' title='Desktop'></i>", 600);
        this.coffeemaker = applianceUnit("<i class='icon-coffee' title='Coffee maker'></i>", 1000);
        this.microwave = applianceUnit("<i class='icon-microvawe' title='Microwave oven'></i>", 900);
        this.dishwasher = applianceUnit("<i class='icon-dishwasher' title='Dishwasher'></i>", 1800);
        this.washer = applianceUnit("<i class='icon-washer' title='Washer'></i>", 500);
        this.iron = applianceUnit("<i class='icon-iron' title='Iron'></i>", 1000);
        this.laptop = applianceUnit("<i class='icon-laptop' title='Laptop'></i>", 100);
        this.television = applianceUnit("<i class='icon-tv' title='Plasma TV'></i>", 170);
        this.teapot = applianceUnit("<i class='icon-teapot' title='Teapot'></i>", 2000);
        this.key = function(n) {
            return this[Object.keys(this)[n]];
        }
    };

    var ApplianceListResult = function() {
        this.vacuum = 0;
        this.desktop = 0;
        this.coffeemaker = 0;
        this.microwave = 0;
        this.dishwasher = 0;
        this.washer = 0;
        this.iron = 0;
        this.laptop = 0;
        this.television = 0;
        this.teapot = 0;
    };

    /*if you want to use production and consumption data
     * you should use duration1 property for production value and
     * duration2 property for consumption value.
     * Otherwise, if you want to use only production or consumption
     * data you should use duration1 property.*/
    function applianceUnit(ico, power) {
        return {
            ico: ico,
            power: power,
            duration1: 0,
            duration2: 0
        }
    }

    function applianceSingleData(data, url) {
        var timePeriod = url.split("/");
        timePeriod = totalNote[timePeriod[3]];

        var sum = getSum(data);
        var result = getList(sum);
        result = getTimeFormat(result);
        var appliances = new ApplianceList();
        for (var i in appliances){
            if (appliances.hasOwnProperty(i)){
                appliances[i].duration1 = result[i];
            }
        }
        $("#valTime1_0").html(timePeriod + ": " + new Number(sum/1000).toFixed(2) + " kWh");

        var indexes = generateUnitNumber();
        var firstUnit = appliances.key(indexes[0]);
        var secondUnit = appliances.key(indexes[1]);
        var thirdUnit = appliances.key(indexes[2]);

        $("#pic_0").html(firstUnit.ico);
        $("#val0_0").html(firstUnit.duration1);

        $("#pic_1").html(secondUnit.ico);
        $("#val1_0").html(secondUnit.duration1);

        $("#pic_2").html(thirdUnit.ico);
        $("#val2_0").html(thirdUnit.duration1);


    }

    function appliancePairData(production, consumption, url) {
        var timePeriod = url.split("/");
        timePeriod = totalNote[timePeriod[3]];
        var sumProduction = getSum(production);
        var sumConsumption = getSum(consumption);
        var listProduction = getList(sumProduction);
        listProduction = getTimeFormat(listProduction);
        var listConsumption = getList(sumConsumption);
        listConsumption = getTimeFormat(listConsumption);
        var appliances = new ApplianceList();
        for (var i in appliances){
            if (appliances.hasOwnProperty(i)){
                appliances[i].duration1 = listProduction[i];
                appliances[i].duration2 = listConsumption[i];
            }
        }

        $("#valTime1_0").html(timePeriod + ": " + new Number(sumProduction/1000).toFixed(2) + " kWh");
        $("#valTime1_1").html(timePeriod + ": " + new Number(sumConsumption/1000).toFixed(2) + " kWh");

        var indexes = generateUnitNumber();
        var firstUnit = appliances.key(indexes[0]);
        var secondUnit = appliances.key(indexes[1]);
        var thirdUnit = appliances.key(indexes[2]);

        $("#pic_0").html(firstUnit.ico);
        $("#val0_0").html(firstUnit.duration1);
        $("#val0_1").html(firstUnit.duration2);

        $("#pic_1").html(secondUnit.ico);
        $("#val1_0").html(secondUnit.duration1);
        $("#val1_1").html(secondUnit.duration2);

        $("#pic_2").html(thirdUnit.ico);
        $("#val2_0").html(thirdUnit.duration1);
        $("#val2_1").html(thirdUnit.duration2);


    }

    function getSum(data) {
        var sum = 0;
        for (var index in data) {
            if (data.hasOwnProperty(index)) {
                sum += data[index]["value"];
            }
        }
        return sum;
    }

    function getList(sumEnergy) {
        var result = new ApplianceListResult();
        var list = new ApplianceList();
        for (var i in result) {
            if (result.hasOwnProperty(i)) {
                result[i] = sumEnergy / list[i].power;
            }
        }
        return result;
    }


    function getTimeFormat(list) {
        var year = 24 * 365;
        var week = 24 * 7;
        var day = 24;
        for (var i in list) {
            if (list.hasOwnProperty(i)) {
                if (list[i] >= year) {
                    list[i] = parseInt(list[i] / year, 10) + ' years';

                } else if (list[i] >= week) {
                    list[i] = parseInt(list[i] / week, 10) + ' weeks';

                } else if (list[i] >= day) {
                    list[i] = parseInt(list[i] / day, 10) + ' days';
                } else {
                    list[i] = parseInt(list[i], 10) + ' hours';
                }
            }
        }
        return list;
    }

    //Generate 3 random indexes to choose particular appliance from the list.
    function generateUnitNumber(){
        var indexes = new Set();
        var result = [];
        while (indexes.size != 3){
            indexes.add(parseInt((Math.random() * 10), 10));
        }
        var i = 0;
        indexes.forEach(function(element){
            result[i++] = parseInt(element.toString());
        });
        return result;
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
