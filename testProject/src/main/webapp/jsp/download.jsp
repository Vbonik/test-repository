
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
        <script language="JavaScript" type="text/javascript">
            function showFileDialog() {
                var dHlpr = document.createElement( 'INPUT');
                dHlpr.type = "dir";
                dHlpr.style.display = 'none';
                document.body.appendChild( dHlpr);
                dHlpr.click();
            }
        </script>
        <input type="button" onclick="showFileDialog()" value="Показать стандартный диалог выбора файлов"><br>
        <s:iterator value="ftpFile.fileNamesOnFTP" var="fileName">
            <a id="fileDownload" href="downloadFile?ftpFile.userFileFileName=<s:property />" ><s:property /></a>
        </s:iterator>       

<s:form action="Ftp">
    <s:submit action="directoryList" value="Refresh"/>
</s:form>

<s:form action="Ftp" method="post" enctype="multipart/form-data">
    <s:file name="ftpFile.userFile" label="User file"/>
    <s:submit action="uploadFtp" value="Upload"/>
</s:form>
</body>
</html>