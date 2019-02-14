<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>Please Login</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>Login:</h1>
    <form name="f" action="login" method="post">
        <fieldset>
            <legend>Please Login</legend>
            <c:if test="${param.error != null}">
                <div class="alert alert-danger">
                    Invalid username and password.
                </div>
            </c:if>
            <c:if test="${param.logout != null}">
                <div class="alert alert-success">
                    You have been logged out.
                </div>
            </c:if>

            <label for="username">Username</label>
            <input type="text" id="username" name="username"/>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Log in</button>
            </div>
        </fieldset>
    </form>
    <a href="register">Register</a>
</div>
</body>
</html>