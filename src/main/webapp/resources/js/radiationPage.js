$(function(){

    $( "#datepicker" ).datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd-mm-yy"
    });

    $("#datepicker").datepicker("setDate", "dd-mm-yy");
    var k = $.datepicker.formatDate("dd-mm-yy", new Date());
    $( "#datepicker").text(k);

});
