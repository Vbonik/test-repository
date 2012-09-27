<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  User: nikitadavydov
  Date: 9/19/12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="../js/jquery-1.8.1.js"></script>
    <script type="text/javascript" src="../js/kickstart.js"></script>
    <script type="text/javascript" src="../js/prettify.js"></script>
    <script type="text/javascript" src="../js/valiadation.js"></script>
    <link href="../css/kickstart.css" rel="stylesheet" type="text/css"/>
    <title>User page</title>
</head>
<body>
<h1>Here you can add/edit user information</h1>

<div class="inner">
    <s:form action="Ftp" method="POST">
        <table width="100px">
            <tr>
                <s:textfield key="userForm.user.user_id" label="Name" id="name"/>
            </tr>
            <tr>
                <s:textfield key="userForm.user.user_password" label="Password" id="password"/>
            </tr>
            <tr>
                <s:textfield key="userForm.user.home_directory" label="Home directory" id="home_directory"/>
            </tr>
            <tr><s:select name="userForm.user.user_roles.id"
                          id="role"
                          list="userForm.userRoleList"
                          value="userForm.defaultRole.id"
                          listKey="id"
                          listValue="authority"
                          label="User role"/>
            </tr>
            <tr><s:select name="userForm.user.enableflag"
                          id="enableflag"
                          list="#{'false':'false', 'true':'true'}"
                          value="userForm.defaultEnable"
                          label="Banned"/>
            </tr>
            <tr><s:select name="userForm.user.write_permission"
                          id="write_permission"
                          list="#{'false':'false', 'true':'true'}"
                          value="userForm.defaultWritePermission"
                          label="Write"/>
            </tr>
            <tr>
                <s:textfield key="userForm.user.idle_time" label="idle_time" id="idle_time"/>
            </tr>
            <tr>
                <s:textfield key="userForm.user.upload_rate" label="upload_rate" id="upload_rate"/>
            </tr>

            <tr>
                <s:textfield key="userForm.user.download_rate" label="download_rate" id="download_rate"/>
            </tr>

            <tr>
                <s:textfield key="userForm.user.max_login_number" label="max_login_number" id="max_login_number"/>
            </tr>

            <tr>
                <s:textfield key="userForm.user.max_login_per_ip" label="max_login_per_ip" id="max_login_per_ip"/>
            </tr>

            <tr>
                <s:textfield key="userForm.user.email" label="email" id="email"/>
            </tr>
            <tr>
                <s:submit action="updateUser" value="Save or Update" theme="simple"
                          onclick="return userFormValidation()"/>
            </tr>
        </table>
        <s:submit action="updateUser" value="Save or Update" theme="simple" onclick="return userFormValidation()"/>
    </s:form>
</div>
</body>
</html>