$(document).ready(function() {
    if ($('#changePasswordForm').length > 0) {
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
    }

    if ($('#registrationForm').length > 0) {
        $('#registrationForm').validate({
            rules : {
                'administrationForm.user.userId' : {
                    required : true
                },
                'administrationForm.user.userPassword' : {
                    required: true,
                    minlength: 5
                },
                'administrationForm.user.email' : {
                    required: true,
                    email : true
                }
            }
        });
    }
});