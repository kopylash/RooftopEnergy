$(function(){
     var userInfo = null;
    var regV = /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/;
    //$(document).ajaxComplete( function(){});


    function fillFields(){
        $("#userName").val(userInfo.userName);
        $("#country").val(userInfo.country);
        $("#province").val(userInfo.province);
        $("#street").val(userInfo.street);
        $("#zipCode").val(userInfo.zipCode);
        $("#city").val(userInfo.city);
        $("#company").val(userInfo.company);
        $("#panelType").val(userInfo.panelType);
        $("#email").val(userInfo.email);
        $("#description").val(userInfo.description);
        //console.log(userInfo.publicStatus);
        $("#statusCompany").attr("checked", userInfo.publicStatus);
    }


    function ajaxGetSettingsQuery() {
         var code = Object.create(errorCode);
        code['200'] = function(data){
            userInfo = data;
            fillFields();
        };
        code['500'] = function(data){
            console.error(data.responseText);
        };
        $.ajax({
            type: 'get',
            url: "/rest/boxData/getUserDescription",
            crossDomain: true,
            statusCode:code
        });
        var f = 0;
    }
    ajaxGetSettingsQuery();


    function changeImg(){
        $("#panelType, #email, #description").css({"backgroundColor":"#FFFFFF", "borderWidth":"1px", "borderColor":"#108f38"});
        $("#lab").css({"color":"#ACA799"});
        var devSize = "";
        if(device.mobile() || device.tablet())
        {
            devSize = "24px";
        } else {
            devSize = "36px";
        }
        $(".form .field  i").css({"height":devSize});
    }

    $("#sub").click(function(){
       var panel =  $("#panelType").val();
       var mail =  $("#email").val();
       var descr =  $("#description").val();
       var stat =  $("#statusCompany").is(":checked")?true:false;
        changeImg();
        userInfo.publicStatus = stat;
        userInfo.email = mail;
        userInfo.panelType = panel;
        userInfo.description = descr;

        companyInfo.status = stat;
        console.log("stat= "+ stat);
        ajaxSaveSettings( panel, mail, descr, stat);
    });

    $("#res").click(function(){
        changeImg();
        fillFields();
    });

    function ajaxSaveSettings( panel, mail, descr, stat){
        var code = Object.create(errorCode);
        code['200'] = function(data){
            $("#settingsAlert").html("<div id=settingsAlertMessage>Settings have been saved!</div>");
        };
        code['500'] = function(data){
            $("#settingsAlert").html("<div id=settingsAlertMessage>Settings have not been saved!</div>");
            console.error(data.responseText);
        };
        $.ajax({
            type: 'post',
            url: "/rest/boxData/saveNewUserInfo",
            crossDomain: true,
            data: {'description': descr, 'email': mail, 'panelType': panel, 'publicStatus': stat},
            statusCode:code
         });
    }

    $("#panelType, #email, #description, #statusCompany").change(function(){
            if (this.id == "statusCompany") {
                $("#lab").css({"color": "#108f38"});
            } else
                $(this).css({"backgroundColor": "#FAFFBD", "borderWidth": "2px"});
            var devSize = "";
            if (device.mobile() || device.tablet()) {
                devSize = "26px";
            } else {
                devSize = "38px";
            }
            $(".form .field #" + this.id + "+ i").css({"height": devSize});
    });

$("#changePasswordButton").click(function(){
    window.location = "changePassword.html";
});
});