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
var errorCode = {
    200 :null,
    400:function(){window.location = "/error.html?code=400"},
    401:function(){window.location = "/error.html?code=401"},
    403:function(){window.location = "/error.html?code=403"},
    404:function(){window.location = "/error.html?code=404"},
    500:null
};
function isMobileDevice(){
    var whatDevice = device.mobile() || device.tablet();
    var landscape = (screen.width <= SCREEN_RESOLUTION.width) && (screen.height <= SCREEN_RESOLUTION.height);
    var portrait = (screen.width <= SCREEN_RESOLUTION.height) && (screen.height <= SCREEN_RESOLUTION.width);
    return whatDevice || landscape || portrait;
}


