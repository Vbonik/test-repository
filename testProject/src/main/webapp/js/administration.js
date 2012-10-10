$(document).ready(function () {
    $.subscribe('completeDiv',
        datePick = function (elem) {
            $(elem).datepicker();
            $('#ui-datepicker-div').css("z-index", 2000);
        });
});