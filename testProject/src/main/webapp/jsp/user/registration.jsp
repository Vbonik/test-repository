<%@ taglib prefix="s" uri="/struts-tags" %>

<h1>Type your information</h1>

<div>
    <s:form action="updateUserRegistration" method="POST">
        <s:textfield key="administrationForm.user.userId" id="name" placeholder="Name" label="Username"/>
        <s:password key="administrationForm.user.userPassword" id="password" placeholder="Password" label="Password"/>
        <s:textfield key="administrationForm.user.email" id="email" placeholder="Email" label="Email"/>
        <s:submit value="Save" onclick="return registrationFormValidation()"/>
    </s:form>
</div>
</body>
</html>