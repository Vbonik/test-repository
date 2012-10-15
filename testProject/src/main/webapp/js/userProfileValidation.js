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

    if ($('#userInfoForm').length > 0) {
        $('#userInfoForm').validate({
            rules : {
                'administrationForm.user.userId' : {
                    required : true
                },
                'administrationForm.user.userPassword' : {
                    required: true,
                    minlength: 5
                },
                'administrationForm.user.homeDirectory' : {
                    required : true
                },
                'administrationForm.user.idleTime' : {
                    required : true,
                    number : true
                },
                'administrationForm.user.uploadRate' : {
                    required : true,
                    number : true
                },
                'administrationForm.user.downloadRate' : {
                    required : true,
                    number : true
                },
                'administrationForm.user.maxLoginNumber' : {
                    required : true,
                    number : true
                },
                'administrationForm.user.maxLoginPerIP' : {
                    required : true,
                    number : true
                },
                'administrationForm.user.email' : {
                    required: true,
                    email : true
                }
            }
        });
    }
});