<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Index</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>This is secured!</h1>
    <p>
        Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>
    </p>
    <form action="logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <%--<input type="submit" value="Log out"/>--%>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Log out</button>
        </div>
    </form>
</div>
<br>
<div style="text-align: center">
    <a href="welcome.html">Click here to See Welcome Message... </a>
</div>
</body>
</html>