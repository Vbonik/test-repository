<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <tiles:insertAttribute name="title" ignore="true" />
    </title>
    <tiles:useAttribute id="styles" name="styles" classname="java.util.List" />
    <c:forEach items="${styles}" var="style">
        <link rel="stylesheet" href="${style}" />
    </c:forEach>
</head>
<body>
    <div class="wrapper">
        <tiles:insertAttribute name="header"/>
        <div class="body">
            <tiles:insertAttribute name="body"/>
        </div>
        <tiles:insertAttribute name="footer"/>
    </div>
    <tiles:useAttribute id="scripts" name="scripts" classname="java.util.List" />
    <c:forEach items="${scripts}" var="script">
        <script type="text/javascript" src="<c:out value='${script}'/>"></script>
    </c:forEach>
</body>
</html>