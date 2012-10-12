<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="sortable">
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
    <s:iterator value="administrationForm.newUsersList" var="user">
        <s:url var="userEditURL" action="editUser">
            <s:param name="administrationForm.userId">
                <s:property value="#user.userId"/>
            </s:param>
        </s:url>
        <s:url var="userDeleteURL" action="deleteUser">
            <s:param name="administrationForm.userId">
                <s:property value="#user.userId"/>
            </s:param>
        </s:url>
        <tbody>
        <tr>
            <td><s:property value="#user.userId"/></td>
            <td><s:property value="#user.userPassword"/></td>
            <td><s:property value="#user.homeDirectory"/></td>
            <td><s:property value="#user.enableFlag"/></td>
            <td><s:property value="#user.writePermission"/></td>
            <td><s:property value="#user.idleTime"/></td>
            <td><s:property value="#user.uploadRate"/></td>
            <td><s:property value="#user.downloadRate"/></td>
            <td><s:property value="#user.maxLoginNumber"/></td>
            <td><s:property value="#user.maxLoginPerIP"/></td>
            <td><s:property value="#user.email"/></td>
            <td><s:property value="#user.user_roles.authority"/></td>
            <td>
                <s:a href="%{userEditURL}">
                    <span class="icon gray small" data-icon="7" title="Edit" style="display: inline-block; ">
                            <span aria-hidden="true">7</span>
                    </span>
                </s:a>
                <s:a href="%{userDeleteURL}">
                    <span class="icon red small" data-icon="m" title="Delete" style="display: inline-block; "
                          onclick="return confirmDelete('<s:property value="#user.userId"/>');">
                    <span aria-hidden="true">m</span>
                    </span>
                </s:a>
            </td>
        </tr>
        </tbody>
    </s:iterator>
</table>