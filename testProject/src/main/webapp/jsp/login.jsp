<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${param.error == 'true'}">
    <div class="errorblock">
        Your login attempt was not successful, try again.<br /> Caused :
        ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
    </div>
</c:if>

<s:form action="/j_spring_security_check" method="post" id="login">
    <s:textfield name="j_username" label="username" placeholder="username"/>
    <s:password  name="j_password" label="password" placeholder="password"/>
    <s:submit label="enter"/>
</s:form>
