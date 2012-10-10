<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="setting-area">
    <h2>Email notifications:</h2>
    <span>You can configure notifications emails about activity on FTP Server</span>
    <div>
        <s:form action="updateUserNotifications" method="post">
            <s:checkboxlist list="userForm.notifications"
                            listKey="notificationId"
                            listValue="notification"
                            label="Choose notifications:"
                            value="userForm.checkedNotifications"
                            name="userForm.checkedNotifications"/>
            <s:submit value="Save"/>
        </s:form>
    </div>
</div>

<div class="setting-area">
    <h2>Change password:</h2>
    <span>You can change your password:</span>
    <div>
        <s:form action="changePassword" method="post" id="changePasswordForm">
            <s:password label="Old password" key="userForm.oldPassword" name="userForm.oldPassword"/>
            <s:password label="New password" key="userForm.newPassword" id="newPassword" name="userForm.newPassword"/>
            <s:password label="Repeat new password" key="userForm.newPasswordConfirm" name="userForm.newPasswordConfirm"/>
            <s:submit value="Save" id="changePasswordBtn"/>
        </s:form>
    </div>
</div>