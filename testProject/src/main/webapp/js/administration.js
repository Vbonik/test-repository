$(document).ready(function() {
    $.subscribe('completeDiv', function(event,data) {
        $('#logTable').dataTable( {
            "bJQueryUI": true
        } );
    });
});