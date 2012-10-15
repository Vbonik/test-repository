<%@ taglib prefix="s" uri="/struts-tags" %>

<s:form action="Ftp" method="POST" id="userInfoForm">
    <s:textfield key="administrationForm.user.userId" name="administrationForm.user.userId"
                 labelposition="left" labelSeparator="" label="Username" id="name" placeholder="johndoe"
                 disabled="%{administrationForm.restrictEdit}"/>
    <s:textfield key="administrationForm.user.userPassword" name="administrationForm.user.userPassword"
                 labelposition="left" labelSeparator="" label="Password" id="password"
                 placeholder="johndoepassword" disabled="%{administrationForm.restrictEdit}"/>
    <s:textfield key="administrationForm.user.homeDirectory" name="administrationForm.user.homeDirectory"
                 labelposition="left" labelSeparator="" label="Home directory" id="home_directory" placeholder="d:\users\johndoe"/>

    <s:select name="administrationForm.user.user_roles.id"
              id="role"
              list="administrationForm.userRoleList"
              value="administrationForm.defaultRole.id"
              listKey="id"
              listValue="authority"
              labelposition="left"
              label="User role"/>

    <s:select name="administrationForm.user.enableFlag"
              id="enableFlag"
              list="#{'false':'false', 'true':'true'}"
              value="administrationForm.defaultEnable"
              labelposition="left"
              label="Enabled"/>

    <s:select name="administrationForm.user.writePermission"
              id="write_permission"
              list="#{'false':'false', 'true':'true'}"
              value="administrationForm.defaultWritePermission"
              labelposition="left"
              label="Write"/>

    <s:textfield key="administrationForm.user.idleTime" name="administrationForm.user.idleTime"
                 labelposition="left" labelSeparator="" label="Idle time" id="idle_time" placeholder="1000"/>
    <s:textfield key="administrationForm.user.uploadRate" name="administrationForm.user.uploadRate"
                 labelposition="left" labelSeparator="" label="Upload rate" id="upload_rate" placeholder="1000"/>
    <s:textfield key="administrationForm.user.downloadRate" name="administrationForm.user.downloadRate"
                 labelposition="left" labelSeparator="" label="Download rate" id="download_rate" placeholder="1000"/>
    <s:textfield key="administrationForm.user.maxLoginNumber" name="administrationForm.user.maxLoginNumber"
                 labelposition="left" labelSeparator="" label="Max login number" id="max_login_number" placeholder="1000"/>
    <s:textfield key="administrationForm.user.maxLoginPerIP" name="administrationForm.user.maxLoginPerIP"
                 labelposition="left" labelSeparator="" label="Max login per IP" id="max_login_per_ip" placeholder="1000"/>
    <s:textfield key="administrationForm.user.email" name="administrationForm.user.email"
                 id="email" labelposition="left" labelSeparator="" label="Email" placeholder="johndoe@email.com"
                 disabled="%{administrationForm.restrictEdit}"/>

    <td>
        <s:submit action="updateUser" value="Save or Update" theme="simple" id="saveUpdateUserBtn"/>
    </td>

    <script type="text/javascript">
        <jsp:include page="../js/userProfileValidation.js"/>
    </script>
</s:form>
