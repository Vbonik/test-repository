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
                <s:textfield key="userForm.user.userName" label="Name" id="name"/>
            </tr>
            <tr>
                <s:textfield key="userForm.user.password" label="Password" id="password"/>
            </tr>
            <tr>
                <s:select name="userForm.user.user_roles.id"
                          id="role"
                          list="userForm.userRoleList"
                          value="userForm.defaultRole.id"
                          listKey="id"
                          listValue="authority"
                          label="User role"/>
            </tr>
            <tr>
                <s:select name="userForm.user.enabled"
                          id="enable"
                          list="#{'false':'false', 'true':'true'}"
                          value="userForm.defaultEnable"
                          label="Banned"/>
            </tr>
        </table>
        <s:submit action="updateUser" value="Save or Update" theme="simple" onclick="return userFormValidation()"/>
    </s:form>
</div>
</body>
</html>