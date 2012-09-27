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
    notNull('password');

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

function confirmDelete() {
    if (confirm("Are you sure you want to delete this user?"))
        return true;
    else return false;
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
