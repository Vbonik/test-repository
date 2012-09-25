<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<c:redirect url="/directoryList" />

Hello at index page <br>
If you are already sing in, you can:
<a href="<c:url value="j_spring_security_logout"/>">Logout</a>
<br> or

<s:form action="Ftp">
    <s:submit action="directoryList" value="Login"/>
</s:form>


