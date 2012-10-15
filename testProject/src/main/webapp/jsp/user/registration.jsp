<%@ taglib prefix="s" uri="/struts-tags" %>

<h1>Type your information for registration</h1>

<div>
    <s:form action="updateUserRegistration" method="POST" id="registrationForm">
        <s:textfield name="administrationForm.user.userId" key="administrationForm.user.userId" placeholder="Name" label="Username"/>
        <s:password name="administrationForm.user.userPassword" key="administrationForm.user.userPassword" placeholder="Password" label="Password"/>
        <s:textfield name="administrationForm.user.email" key="administrationForm.user.email" placeholder="Email" label="Email"/>
        <s:submit value="Save"/>
    </s:form>
</div>
</body>
</html>