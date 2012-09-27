<%--
  Created by IntelliJ IDEA.
  User: nikitadavydov
  Date: 9/14/12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <script type="text/javascript" src="../js/prettify.js"></script>
    <script type="text/javascript" src="../js/kickstart.js"></script>
    <link href="../css/kickstart.css" rel="stylesheet" type="text/css"/>
    <title>Administrator panel</title>
</head>
<body>
<h1>Good morning administrator</h1>
User list:<br>
add users in table and some additional information about users actions<br>
<table class="sortable" width="100px;">
    <thead>
    <tr>
        <th>User name</th>
        <th>Password</th>
        <th>Home directory</th>
        <th>Enabled</th>
        <th>Write</th>
        <th>Idle time</th>
        <th>Upload rate</th>
        <th>Download rate</th>
        <th>Max login number</th>
        <th>Max login IP</th>
        <th>Email</th>
        <th>User role</th>
        <th>Action</th>
    </tr>
    </thead>
    <s:iterator value="userForm.usersList" var="user">
        <s:url var="userEditURL" action="editUser">
            <s:param name="userForm.user_id">
                <s:property value="#user.user_id"/>
            </s:param>
        </s:url>
        <s:url var="userDeleteURL" action="deleteUser">
            <s:param name="userForm.user_id">
                <s:property value="#user.user_id"/>
            </s:param>
        </s:url>
        <tbody>
        <tr>
            <td><s:property value="#user.user_id"/></td>
            <td><s:property value="#user.user_password"/></td>
            <td><s:property value="#user.home_directory"/></td>
            <td><s:property value="#user.enableflag"/></td>
            <td><s:property value="#user.write_permission"/></td>
            <td><s:property value="#user.idle_time"/></td>
            <td><s:property value="#user.upload_rate"/></td>
            <td><s:property value="#user.download_rate"/></td>
            <td><s:property value="#user.max_login_number"/></td>
            <td><s:property value="#user.max_login_per_ip"/></td>
            <td><s:property value="#user.email"/></td>
            <td><s:property value="#user.user_roles.authority"/></td>
            <td>
                <s:a href="%{userEditURL}">
                    <span class="icon gray small" data-icon="7" title="Edit" style="display: inline-block; ">
                            <span aria-hidden="true">7</span>
                    </span>
                </s:a>
                <s:a href="%{userDeleteURL}" onclick="return confirmDelete">
                    <span class="icon gray small" data-icon="m" title="Delete" style="display: inline-block; ">
                            <span aria-hidden="true">m</span>
                    </span>
                </s:a>
            </td>
        </tr>
        </tbody>
    </s:iterator>
</table>

<s:url var="userAddURL" action="addUser"/>
<s:a href="%{userAddURL}">
    <span class="icon gray large" data-icon="p" title="Add" style="display: inline-block; ">
        <span aria-hidden="true">p</span>
    </span>
</s:a>
<br>
<a href="<c:url value="j_spring_security_logout"/>">Logout</a>
</body>
</html>