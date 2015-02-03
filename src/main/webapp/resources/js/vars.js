/**
 * Created by alex on 1/13/15.
 */
var currentPage ={
    name : 'none'
};


var companyInfo = {
    status : true,
    name:''
};

const ENERGY_ICO = {
    production : '<i class="fa fa-sun-o fa-1x"></i>',
    consumption: '<i class="fa fa-plug  fa-1x">'

};

const SCREEN_RESOLUTION = {
    width:1300,
    height:1000

};

function isMobileDevice(){
    var whatDevice = device.mobile() || device.tablet();
    var landscape = (screen.width <= SCREEN_RESOLUTION.width) && (screen.height <= SCREEN_RESOLUTION.height);
    var portrait = (screen.width <= SCREEN_RESOLUTION.height) && (screen.height <= SCREEN_RESOLUTION.width);
    return whatDevice || landscape || portrait;
}

const applianceList = {
    vacuum : "<i class='icon-vacuming'></i>",
    desktop : "<i class='icon-desktop'></i>",
    coffeemaker :"<i class='icon-coffee'></i>",
    microwave : "<i class='icon-microvawe'></i>",
    dishwasher : "<i class='icon-dishwasher'></i>",
    washer : "<i class='icon-washer'></i>",
    iron : "<i class='icon-iron'></i>",
    laptop : "<i class='icon-laptop'></i>",
    television : "<i class='icon-tv'></i>",
    teapot : "<i class='icon-teapot'></i>"
};

var applianceListResult = function() {
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