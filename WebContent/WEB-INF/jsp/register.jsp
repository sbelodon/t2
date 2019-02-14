<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<script type="text/javascript">
    $(document).ready(function () {
        $('#password').keyup(function () {
            var data = {};
            data["password"] = $("#password").val();
            $.ajax({
                url: "register/check",
                data: JSON.stringify(data),
                type: "POST",
                contentType: "application/json",
                dataType: 'json',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader('X-CSRF-TOKEN', '${_csrf.token}')
                },
                success: function (data) {
                    if (data == 0) {
                        $("#msgid").html("Password is weak");
                        $("#msgid").attr("class", "alert alert-danger");
                    }
                    if (data == 1) {
                        $("#msgid").html("Password has middle strength");
                        $("#msgid").attr("class", "alert alert-warning");
                    }
                    if (data == 2) {
                        $("#msgid").html("Password has good strength");
                        $("#msgid").attr("class", "alert alert-success");
                    }
                }

            });
        });
    });
</script>
<div class="container">
    <h1>Register:</h1>
<form action="register" method="post">
    <label for="username">Username</label>
    <input type="text" id="username" name="username"/>
    <label for="password">Password</label>
    <input type="password" id="password" name="password"/>
    <div id="msgid">
    </div>
    <c:if test="${not empty fn:trim(message)}">
        <div class="alert alert-danger">${message}</div>
    </c:if>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Add User</button>
        </div>
    </div>
</form>
</div>
</body>
</html>