$(function(){
    var userInfo;
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

    $("#sub").click(function(){

       var panel =  $("#panelType").val();
       var mail =  $("#email").val();
       var descr =  $("#description").val();
       var stat =  $("#statusCompany").is(":checked")?true:false;
        userInfo.publicStatus = stat;
        userInfo.email = mail;
        userInfo.panelType = panel;
        userInfo.description = descr;

        companyInfo.status = stat;
        console.log("stat= "+ stat);
        ajaxSaveSettings( panel, mail, descr, stat);

    });
    $("#res").click(function(){
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
});