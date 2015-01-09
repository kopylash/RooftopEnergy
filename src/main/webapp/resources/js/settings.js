$(function(){
    var insertSettings = function(data){
        //$("input[type=text]").val(function(){
        //    console.log("we are here");
        //    var inId = this.id;
        //    console.log(inId);
        //    var someTxt = data[inId];
        //    console.log(someTxt);
        //    var t = '<span>someTxt</span>';
        //    this.html(t);
        //});
        var someTxt = data.userName;
        $("#userName").val(someTxt);
        someTxt = data.country;
        $("#country").val(someTxt);
        someTxt = data.province;
        $("#province").val(someTxt);
        someTxt = data.street;
        $("#street").val(someTxt);
        someTxt = data.zipCode;
        $("#zipCode").val(someTxt);
        someTxt = data.city;
        $("#city").val(someTxt);
        someTxt = data.company;
        $("#company").val(someTxt);
       someTxt = data.panelType;
        $("#panelType").val(someTxt);
         someTxt = data.email;
        $("#email").val(someTxt);
        someTxt = data.description;
        $("#description").val(someTxt);

    };


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
                    insertSettings(data);
                }
            }
        });
    };

    ajaxGetSettingsQuery();
})