<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<sx:head parseContent="true"/>

<div id="tabvanilla" class="widget">

    <ul class="tabnav">
        <li><a href="#userManagement">User management</a></li>
        <li><a href="#log">Log</a></li>
    </ul>

    <%--User management tab--%>
    <div id="userManagement" class="tabdiv">
        <s:url action="userList" id="userListUrl"/>
        <div style="text-align: left;">
            <sx:a notifyTopics="/refreshUsers">Refresh</sx:a>
        </div>
        </br>
        <sx:div preload="true" id="users" theme="ajax" href="%{userListUrl}" loadingText="Loading..."
                listenTopics="/refreshUsers"/>
    </div>

    <%--Log tab--%>
    <div id="log" class="tabdiv">
        <s:url action="logList" id="logListUrl"/>
        <div style="text-align: left;">
            <sx:a notifyTopics="/refreshLog">Show</sx:a>
        </div>
        </br>
        <sx:div preload="false" id="logs" theme="ajax" href="%{logListUrl}" loadingText="Loading..."
                listenTopics="/refreshLog"/>
    </div>
</div>