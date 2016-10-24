/* 
 * É necessário o JQuery
 */

function expandirMenu(){
    //var selected = $("#navbar-toggle").hasClass('slide-active');

    $(".navbar-toggle").toggleClass('slide-active');

    $('#slidemenu').toggleClass("col-xs-10");
    $('#corpo').toggleClass("col-xs-2");
    $('#slidemenu').toggleClass("sidebar");
}

$(document).ready(function () {

    // Enter your ids or classes
    var toggler = '.navbar-toggle';
    
    $("#slide-nav").on("click", toggler, function (e) {
        var selected = $(this).hasClass('slide-active');

      
        $(this).toggleClass('slide-active', !selected);
        
        $('#slidemenu').toggleClass("col-xs-10", !selected);
        $('#corpo').toggleClass("col-xs-2", !selected);
        $('#slidemenu').toggleClass("sidebar", selected);
        
                         
        //$('#slidemenu').toggleClass("sidebar", 1000, "easeOutSine");
                                
    });


    //var selected = '#slidemenu, #page-content, body, .navbar, .navbar-header';


    $(window).on("resize", function () {

        if ($(window).width() > 767) {
            $('.navbar-toggle').toggleClass('slide-active', false);
            $('#slidemenu').toggleClass("sidebar", true);
            $('#slidemenu').toggleClass("col-xs-10", false);
            $('#corpo').toggleClass("col-xs-2", false);
        }


    });

});