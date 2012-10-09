$(document).ready(function() {
    $("#changePasswordForm").validate({
        rules : {
            'userForm.oldPassword': {
                required: true
            },
            'userForm.newPassword': {
                required: true,
                minlength: 5
            },
            'userForm.newPasswordConfirm': {
                required: true,
                equalTo: "#newPassword"
            }
        }
    });
});