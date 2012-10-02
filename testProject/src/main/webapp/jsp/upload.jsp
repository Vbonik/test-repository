<%@ taglib prefix="s" uri="/struts-tags" %>

<s:form action="uploadFile" method="post" enctype="multipart/form-data" >
    <s:file name="ftpForm.userFile" label="User file" />
    <s:submit value="Upload" id="uploadButton"/>
</s:form>
