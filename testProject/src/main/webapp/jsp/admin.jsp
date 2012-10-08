<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<sj:head/>

<s:url id="user" value="/userList"/>
<s:url id="log" value="/logList"/>

<sj:tabbedpanel id="adminTabPanel" animate="true">
    <sj:tab href="%{user}" id="userTab" target="usersDiv" label="Users"/>
    <sj:tab href="%{log}" id="logTab" target="logsDiv" label="Logs"/>

    <div id="usersDiv">
    </div>

    <div id="logsDiv">
    </div>

</sj:tabbedpanel>