$(document).ready(function() {
    $.subscribe('completeDiv', function(event,data) {
        $('#logTable').dataTable( {
            "sPaginationType": "full_numbers"
        } );
    });
});