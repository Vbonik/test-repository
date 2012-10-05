<%@ taglib prefix="s" uri="/struts-tags" %>

<h1>Here you can add/edit user information</h1>

<div class="inner">
    <s:form action="Ftp" method="POST">
        <s:textfield key="administrationForm.user.user_id" labelposition="top" labelSeparator="" label="" id="name"
                     placeholder="Name"/>
        <s:textfield key="administrationForm.user.user_password" labelposition="top" labelSeparator="" label="" id="password"
                     placeholder="Password"/>
        <s:textfield key="administrationForm.user.home_directory" labelposition="top" labelSeparator="" label=""
                     id="home_directory" placeholder="User home directory"/>

        <s:select name="administrationForm.user.user_roles.id"
                  id="role"
                  list="administrationForm.userRoleList"
                  value="administrationForm.defaultRole.id"
                  listKey="id"
                  listValue="authority"
                  labelposition="top"
                  label="Role"/>

        <s:select name="administrationForm.user.enableflag"
                  id="enableflag"
                  list="#{'false':'false', 'true':'true'}"
                  value="administrationForm.defaultEnable"
                  labelposition="top"
                  label="Enabed"/>

        <s:select name="administrationForm.user.write_permission"
                  id="write_permission"
                  list="#{'false':'false', 'true':'true'}"
                  value="administrationForm.defaultWritePermission"
                  labelposition="top"
                  label="Write"/>

        <s:textfield key="administrationForm.user.idle_time" labelposition="top" labelSeparator=":" label="Administration"
                     id="idle_time" placeholder="Idle time"/>
        <s:textfield key="administrationForm.user.upload_rate" labelposition="top" labelSeparator="" label="" id="upload_rate"
                     placeholder="Upload rate"/>
        <s:textfield key="administrationForm.user.download_rate" labelposition="top" labelSeparator="" label="" id="download_rate"
                     placeholder="Download rate"/>
        <s:textfield key="administrationForm.user.max_login_number" labelposition="top" labelSeparator="" label=""
                     id="max_login_number" placeholder="Max login number"/>
        <s:textfield key="administrationForm.user.max_login_per_ip" labelposition="top" labelSeparator="" label=""
                     id="max_login_per_ip" placeholder="Max login number per IP"/>
        <s:textfield key="administrationForm.user.email" id="email" labelposition="top" labelSeparator="" label=""
                     placeholder="Email"/>
        <br>
        <td><s:submit action="updateUser" value="Save or Update" theme="simple"
                      onclick="return userFormValidation()"/></td>
    </s:form>
</div>
