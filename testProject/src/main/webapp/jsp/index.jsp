<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: nikitadavydov
  Date: 9/12/12
  Time: 8:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello my friend</title>
</head>
<body>
<h1>Choose link:</h1>

<s:url action="getDownloadFileListFtp" var="urlTag"/>
<a href="<s:property value="#urlTag" />">Download page</a>


</body>
</html>