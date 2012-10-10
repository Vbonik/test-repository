$(document).ready(function() {
    $.subscribe('completeDiv', function(event,data) {
        $('#logTable').dataTable( {
            "bJQueryUI": true
        } );
    });

    datePick = function(elem) {
        $(elem).datepicker({displayFormat: "m.d.Y"});
        $('#ui-datepicker-div').css("z-index", 2000);
    }
});