<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<sj:head jqueryui="true"/>
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
        <!--<input type="button" onclick="showFileDialog()" value="Показать стандартный диалог выбора файлов"><br>-->

        <s:iterator value="ftpFile.fileNamesOnFTP" var="fileName">
            <s:url var="fileDownloadUrl" action="downloadFile?ftpFile.userFileFileName=%{fileName}" ></s:url>
            <s:a href="%{fileDownloadUrl}"> <s:property/> </s:a>
                <br/>
        </s:iterator>       

    </body>

</html>