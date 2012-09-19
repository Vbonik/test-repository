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

        <s:iterator value="currentDirectory.fileList" var="file">
            <s:url var="fileDownloadUrl" action="downloadFile?currentDirectory.currentFile.name=%{name}" >

            </s:url>
            <s:a href="%{fileDownloadUrl}"> <s:property value="name"/> </s:a>
            <br/>
        </s:iterator>      
        <br/>
        <br/>
        <div class="result ui-widget-content ui-corner-all">
            <s:form action="uploadFile?currentDirectory.currentFile.name" method="post" enctype="multipart/form-data" >
                <s:file name="currentDirectory.currentFile.file" label="User file" />
                <s:submit value="Upload"/>
            </s:form>
        </div>
    </body>

</html>