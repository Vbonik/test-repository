<%@ taglib prefix="s" uri="/struts-tags" %>

<table class="sortable" id="logTable">
    <thead>
    <tr>
        <th>User name</th>
        <th>Authorities</th>
        <th>Action</th>
        <th>Status</th>
        <th>Date</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="administrationForm.logEntryList" var="logEntry">
        <tr>
            <td><s:property value="#logEntry.userName"/></td>
            <td><s:property value="#logEntry.authorities"/></td>
            <td><s:property value="#logEntry.action"/></td>
            <td><s:property value="#logEntry.status"/></td>
            <td><s:property value="#logEntry.date"/></td>
        </tr>
    </s:iterator>
    </tbody>
</table>