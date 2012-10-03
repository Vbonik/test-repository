<%@ taglib prefix="s" uri="/struts-tags" %>

<h1>Type your information</h1>

<div class="inner">
    <s:form action="Ftp" method="POST">
        <table class="registration">
            <s:textfield key="userForm.user.user_id" labelposition="top" labelSeparator="" label="" id="name"
                         placeholder="Name"/>
            <s:textfield key="userForm.user.user_password" labelposition="top" labelSeparator="" label="" id="password"
                         placeholder="Password"/>
            <s:textfield key="userForm.user.home_directory" labelposition="top" labelSeparator="" label=""
                         id="home_directory" placeholder="User home directory"/>
            <s:textfield key="userForm.user.email" id="email" labelposition="top" labelSeparator="" label=""
                         placeholder="Email"/>
            <br>
            <td><s:submit action="updateUserRegistration" value="Save" theme="simple"
                          onclick="return registrationFormValidation()"/></td>
        </table>
    </s:form>
</div>
</body>
</html>