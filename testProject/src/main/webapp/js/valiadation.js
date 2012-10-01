/**
 * User: nikitadavydov
 * Date: 9/25/12
 */

var errorScope = [];
var count = 0;
var flag = true;

function userFormValidation() {
    resetCountFlagArray();
    notNull('name');
    notNull('home_directory');
    notNull('idle_time');
    notNull('idle_time');
    notNull('upload_rate');
    notNull('download_rate');
    notNull('max_login_number');
    notNull('max_login_per_ip');
    notNull('email');

    if (flag == false) {
        alert(errorScope);
    }
    return flag;
}

function notNull(fieldName) {
    if (document.getElementById(fieldName).value.length == 0) {
        errorScope[count] = fieldName + " can't be null" + '\n';
        count++;
        flag = false;
    }
}

function confirmDelete(name) {
    var deleteFlag = false;
    if (confirm("Are you sure you want to delete this user?")) {
        deleteFlag = true;
        if (name == 'admin') {
            alert("You can't delete admin.")
            deleteFlag = false;
        }
    }
    return deleteFlag;
}

function onlyCharacters() {
}

function onlyNumbers() {
}

function resetCountFlagArray() {
    errorScope.length = 0;
    count = 0;
    flag = true;
}
