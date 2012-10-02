<%@ taglib prefix="s" uri="/struts-tags" %>

<h1>Here you can add/edit user information</h1>

<div class="inner">
    <s:form action="Ftp" method="POST">
        <s:textfield key="userForm.user.user_id" label="Name" id="name"/>
        <s:textfield key="userForm.user.user_password" label="Password" id="password"/>
        <s:textfield key="userForm.user.home_directory" label="Home directory" id="home_directory"/>

        <s:select name="userForm.user.user_roles.id"
                  id="role"
                  list="userForm.userRoleList"
                  value="userForm.defaultRole.id"
                  listKey="id"
                  listValue="authority"
                  label="User role"/>

        <s:select name="userForm.user.enableflag"
                  id="enableflag"
                  list="#{'false':'false', 'true':'true'}"
                  value="userForm.defaultEnable"
                  label="Enabed"/>

        <s:select name="userForm.user.write_permission"
                  id="write_permission"
                  list="#{'false':'false', 'true':'true'}"
                  value="userForm.defaultWritePermission"
                  label="Write"/>

        <s:textfield key="userForm.user.idle_time" label="Idle time" id="idle_time"/>
        <s:textfield key="userForm.user.upload_rate" label="Upload rate" id="upload_rate"/>
        <s:textfield key="userForm.user.download_rate" label="Download rate" id="download_rate"/>
        <s:textfield key="userForm.user.max_login_number" label="Max login number" id="max_login_number"/>
        <s:textfield key="userForm.user.max_login_per_ip" label="Max login per IP" id="max_login_per_ip"/>
        <s:textfield key="userForm.user.email" label="Email" id="email"/>

        <td><s:submit action="updateUser" value="Save or Update" theme="simple"
                      onclick="return userFormValidation()"/></td>
    </s:form>
</div>
