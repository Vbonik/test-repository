<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="header">
    <h1>FTP Server</h1>
    <span>
        <sec:authorize access="isAuthenticated()">
            <a href="<c:url value='j_spring_security_logout'/>">Logout</a>

            <sec:authorize access="hasRole('ROLE_USER')">
                <s:url var="userProfileURL" action="profile"/>
                <s:a href="%{userProfileURL}">Profile</s:a>

                <s:url var="ftpFilesURL" action="list?currentDirectory.typeOfFile=1"/>
                <s:a href="%{ftpFilesURL}">Files</s:a>
            </sec:authorize>

        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <s:url var="userLoginURL" action="login"/>
            <s:a href="%{userLoginURL}">Sign In</s:a>

            <s:url var="userRegistrationURL" action="userRegistration"/>
            <s:a href="%{userRegistrationURL}">Sign Up</s:a>
        </sec:authorize>
    </span>
</div>