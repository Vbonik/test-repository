/**
 * User: nikitadavydov
 * Date: 10/8/12
 */

function getNotificationFromServer() {
    AdministrationNotificationScript.sendNotifications({
        callback:handleAddSuccess,
        errorHandler:handleAddError
    });
}

function handleAddSuccess(message) {
    if (message != null) {
        receiveMessages(message);
    }
}

function receiveMessages(messages) {
    var chatlog = "";
    var count = messages.length;
    for (var i in messages) {
        if (findSubStringInString(messages[i].activityStatus, 'failure')) {
            chatlog = " " + "<font color='red'>" + dwr.util.escapeHtml(messages[i].dateTime + ' ' + messages[i].user + ' ' + messages[i].activity + ' ' + messages[i].activityStatus) + "</font>" + "<br>" + chatlog;
        } else {
            chatlog = " " + "<font color='green'>" + dwr.util.escapeHtml(messages[i].dateTime + ' ' + messages[i].user + ' ' + messages[i].activity + ' ' + messages[i].activityStatus) + "</font>" + "<br>" + chatlog;
        }
    }
    dwr.util.setValue("chatlog", chatlog, { escapeHtml:false });
}

function findSubStringInString(message, string) {
    if (message.indexOf(string) != -1) {
        return true;
    } else return false;
}

function emptyListOfNotifications() {
    AdministrationNotificationScript.emptyListOfNotifications({
        callback:clearNotificationMessages,
        errorHandler:handleAddError
    });
}

function clearNotificationMessages(message) {
    var chatlog = "";
    dwr.util.setValue("chatlog", chatlog, { escapeHtml:false });
}

function handleAddError() {
    alert("Some problem with server.");
}