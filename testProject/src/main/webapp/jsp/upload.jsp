<%--
  User: Никита
  Date: 09.09.12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Upload page</title>
</head>
<body>
<s:form action="Ftp" method="post" enctype="multipart/form-data">
    <s:file name="ftpFile.userFile"/>
    <s:submit action="uploadFtp" value="Upload"/>
</s:form>
</body>
</html>