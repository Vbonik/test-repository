$(document).ready(function() {
    var newUsersLabel = $('#newUsersTab span').html();
    newUsersLabel += $('#newUsersCount').html();
    $('#newUsersTab span').html(newUsersLabel);

    datePick = function(elem) {
        $(elem).datepicker();
        $('#ui-datepicker-div').css("z-index", 2000);
    }
});