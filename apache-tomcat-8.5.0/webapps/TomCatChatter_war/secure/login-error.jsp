
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h4>Either UserName or Password is Incorrect</h4>
<a href="login.jsp" type="button">Log in</a>

<form action="adduser">
    <button type="submit">
        Create New Accout
    </button>
</form>
<%
    out.println("auth");
    out.println(request.getAuthType());
    out.println("user");
    out.println(request.getRemoteUser());
    out.println("principal");
    out.println(request.getUserPrincipal());
%>

</body>
</html>
