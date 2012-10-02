
<%@ taglib prefix="s" uri="/struts-tags" %>

<table class="sortable" width="100px;">
    <thead>
    <tr>
        <th>User name</th>
        <th>Authorities</th>
        <th>Action</th>
        <th>Status</th>
        <th>Date</th>
    </tr>
    </thead>
    <s:iterator value="userForm.logEntryList" var="logEntry">
        <tbody>
        <tr>
            <td><s:property value="#logEntry.userName"/></td>
            <td><s:property value="#logEntry.authorities"/></td>
            <td><s:property value="#logEntry.action"/></td>
            <td><s:property value="#logEntry.status"/></td>
            <td><s:property value="#logEntry.date"/></td>
        </tr>
        </tbody>
    </s:iterator>
</table>