<%--
  Created by IntelliJ IDEA.
  User: nikitadavydov
  Date: 9/11/12
  Time: 4:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Download</title>
</head>
<body>
<h5><s:property value="ftpFile.fileList"/> </br></h5>
<s:form action="Ftp">
    <s:textfield name="ftpFile.userFileFileName" label="File name"/>
    <s:textfield name="ftpFile.destination" label="Destination"/>
    <s:submit action="downloadFtp" value="Download"/>
</s:form>
<s:form action="Ftp">
    <s:submit action="getDownloadFileListFtp" value="Refresh"/>
</s:form>
</body>
</html>