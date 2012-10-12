<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<s:url id="remoteurl" value="/jsontable"/>

<div id="logDatepicker" class="hide">
    <sj:datepicker/>
</div>

<sjg:grid
        id="gridtable"
        caption="Logs"
        dataType="json"
        href="%{remoteurl}"
        pager="true"
        gridModel="gridModel"
        rowList="10,15,20"
        rowNum="15"
        viewrecords="true"
        altRows="true"
        gridview="true"
        navigator="true"
        navigatorAdd="false"
        navigatorRefresh="true"
        navigatorDelete="false"
        navigatorEdit="false"
        navigatorSearch="true"
        pagerPosition="left"
        >
    <sjg:gridColumn name="id" index="id" title="Id" sortable="true" width="25" align="center" search="false"/>
    <sjg:gridColumn name="userName" index="userName" title="User name" sortable="true" search="true"
                    searchoptions="{sopt:['cn','eq']}"/>
    <sjg:gridColumn name="authorities" index="authorities" title="Authorities" sortable="true" search="true"
                    searchoptions="{sopt:['cn','eq']}"/>
    <sjg:gridColumn name="action" index="action" title="Action" sortable="true" search="true"
                    searchoptions="{sopt:['cn','eq']}"/>
    <sjg:gridColumn name="status" index="status" title="Status" sortable="true" search="true"
                    searchoptions="{sopt:['cn','eq']}"/>
    <sjg:gridColumn name="date" index="date" title="Date" sortable="true" search="true"
                    formatter="date"
                    formatoptions="{newformat : 'm.d.Y H:i', srcformat : 'Y-m-d H:i:s'}"
                    searchoptions="{sopt:['cn','lt','gt'], dataInit:datePick}"/>
</sjg:grid>