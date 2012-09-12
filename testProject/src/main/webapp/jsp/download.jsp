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
<h2>Download</h2>
<s:iterator value="ftpFile.fileNamesOnFTP" id="fileName">
    <s:url action="downloadFtp" var="urlTag">
        <s:param name="ftpFile.userFileFileName">
            <s:property/>
        </s:param>
        <s:param name="ftpFile.destination">
            C:/
        </s:param>
    </s:url>
    <a href="<s:property value="#urlTag" />"><s:property/></a>
    <br>
</s:iterator>

<s:form action="Ftp">
    <s:submit action="getDownloadFileListFtp" value="Refresh"/>
</s:form>

<h2>Upload</h2>
<s:form action="Ftp" method="post" enctype="multipart/form-data">
    <s:file name="ftpFile.userFile"/>
    <s:submit action="uploadFtp" value="Upload"/>
</s:form>
</body>
</html>