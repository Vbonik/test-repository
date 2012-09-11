<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head></head>
<body>
<h1>Login</h1>

<s:form action="Ftp">
    <s:textfield name="user.login" label="Login"/>
    <s:password name="user.password" label="Password"/>
    <s:submit action="loginFtp" value="Login"/>
</s:form>
<a href="jsp/upload.jsp">Upload page</a>
<a href="jsp/download.jsp">Download page</a>
</body>
</html>