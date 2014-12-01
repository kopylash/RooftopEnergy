$(function(){
    var page = window.location;
    page = page+"";
    var first = page.lastIndexOf("/")+1;
    var second = page.indexOf(".html");
    var idi = page.slice(first, second);
    $("#"+idi).css({ 'color': '#0062D2'});

    $(".mainButtons").click(function() {
        window.location = this.id + ".html";
    });


});
