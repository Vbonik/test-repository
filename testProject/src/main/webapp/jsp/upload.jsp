<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 09.09.12
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Upload page</title>
</head>
<body>
<s:form action="uploadFtp" method="post" enctype="multipart/form-data">
    <s:file name="ftpFile.userFile" label="User file"/>
    <s:submit action="uploadFtp" value="Upload"/>
</s:form>
</body>
</html>