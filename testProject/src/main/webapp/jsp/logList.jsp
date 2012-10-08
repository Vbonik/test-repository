<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<s:url id="remoteurl" value="/jsontable"/>
<sjg:grid
        id="gridtable"
        caption="Logs"
        dataType="json"
        href="%{remoteurl}"
        pager="true"
        gridModel="gridModel"
        rowList="10,15,20"
        rowNum="15"
        rownumbers="true"
        >
    <sjg:gridColumn name="userName" index="userName" title="User name" sortable="false"/>
    <sjg:gridColumn name="authorities" index="authorities" title="Authorities" sortable="false"/>
    <sjg:gridColumn name="action" index="action" title="Action" sortable="false"/>
    <sjg:gridColumn name="status" index="status" title="Status" sortable="false"/>
    <sjg:gridColumn name="date" index="date" title="Date" sortable="false"/>
</sjg:grid>