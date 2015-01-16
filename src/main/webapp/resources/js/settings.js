$(function(){
    var userInfo;
    var regV = /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/;
    $(document).ajaxComplete( function(){
        //$("input[type=text]").val(function(){
        //    console.log("we are here");
        //    var inId = this.id;
        //    console.log(inId);
        //    var someTxt = data[inId];
        //    console.log(someTxt);
        //    var t = '<span>someTxt</span>';
        //    this.html(t);
        //});
        fillFields();
    });

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
        $.ajax({
            type: 'get',
            url: "/rest/boxData/getUserDescription",
            crossDomain: true,
            error: function (data) {
                $('#main').html(data.responseText);
            },
            statusCode: {
                200: function (data) {
                    userInfo = data;
                }
            }
        });
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
        $.ajax({
            type: 'post',
            url: "/rest/boxData/saveNewUserInfo",
            crossDomain: true,
            data: {'description': descr, 'email': mail, 'panelType': panel, 'publicStatus': stat},
            error: function (data) {
                $('#main').html(data.responseText);
            }/*,
            statusCode: {
                200: function (data) {
                    //alert("Settings have been saved!");
                }
            }*/
        });
    }


    $("#panelType, #email, #description, #statusCompany").change(function(){

        //var emailVal = $("#email").val();
        //console.log("emailVal="+emailVal);
        //
        //var inpVal = $(this).val() + "";
        //console.log("inpVal="+inpVal);
        //
        //var emailRegVar = emailVal.search(regV);
        //console.log("emailRegVar="+emailRegVar);
        //
        //if(inpVal == ""){
        //    //$("$sub").disable();
        //    $(this).css({"backgroundColor":"#FF6D6A", "borderWidth":"3px", "borderColor":"#FF0000"});
        //}
        //else if(emailRegVar == -1){
        //    $("$email").css({"backgroundColor":"#FF6D6A", "borderWidth":"3px", "borderColor":"#FF0000"});
        //}
        //else {
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
        //}
    });



});