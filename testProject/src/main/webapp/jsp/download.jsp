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

        <form action="deleteFiles">
            <input type="hidden" name="ttt" value="utrtrfhn">
            <table>
                <s:iterator value="currentDirectory.fileList" var="file">
                    <tr>
                        <td>                 
                            <input type="checkbox" value="<s:property value="name"/>" name="delete">
                            <s:url var="fileDownloadUrl" action="downloadFile?currentDirectory.currentFile.name=%{name}" >
                            </s:url>
                            <s:a href="%{fileDownloadUrl}"> <s:property value="name"/> </s:a>
                            </td>
                        </tr>
                </s:iterator> 
                <tr>
                    <td>
                        <input type="submit" value="delete">
                    </td></tr>
            </table>
        </form>

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