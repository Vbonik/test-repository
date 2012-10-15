<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<sj:head/>

<s:url id="user" value="/userList"/>
<s:url id="log" value="/logList"/>
<s:url id="addUser" value="/addUser"/>
<s:url id="newUsers" value="/newUsers"/>

<sj:tabbedpanel id="adminTabPanel" animate="true">
    <sj:tab href="%{user}" id="userTab" target="usersDiv" label="Users"/>
    <sj:tab href="%{log}" id="logTab" target="logsDiv" label="Logs"/>
    <sj:tab href="%{addUser}" id="addUserTab" target="addUserDiv" label="Add User"/>
    <sj:tab href="%{newUsers}" id="newUsersTab" target="newUsersDiv" label="New Users "/>

    <div id="usersDiv">
    </div>

    <div id="logsDiv">
    </div>

    <div id="addUserDiv">
    </div>

    <div id="newUsersDiv">
    </div>

</sj:tabbedpanel>

<div class="hide" id="newUsersCount">
    (<c:out value='${fn:length(administrationForm.newUsersList)}'/>)
</div>

<div id="chatlog"></div>
<input type="button" onClick="history.go(0)" value="Start">
<input type="button" onclick="clearInterval(timer)" value="Stop">
<input type="button" onclick="emptyListOfNotifications()" value="Clear">

<script type="text/javascript">
    window.onload = function() {
        var timer = setInterval(function() { getNotificationFromServer() }, 10000);
    }
</script>