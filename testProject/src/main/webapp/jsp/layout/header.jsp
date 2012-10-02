<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="header">
    <h1>FTP Server</h1>
    <sec:authorize access="isAuthenticated()">
        <span>
            <a href="<c:url value='j_spring_security_logout'/>">Logout</a>
        </span>
    </sec:authorize>
</div>