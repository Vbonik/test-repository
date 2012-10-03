<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<sj:head/>

<s:url id="user" value="/userList"/>
<s:url id="log" value="/logList"/>

<sj:tabbedpanel id="adminTabPanel" animate="true">
    <sj:tab href="%{user}" id="userTab" target="usersDiv" label="Users"/>
    <sj:tab id="logTab" target="logsDiv" label="Logs"/>

    <div id="usersDiv">
    </div>

    <div id="logsDiv">
        <sj:a href="%{log}" targets="logTableContainer" onCompleteTopics="completeDiv">
            Update Log
        </sj:a>

        <sj:div id="logTableContainer"></sj:div>
    </div>

</sj:tabbedpanel>