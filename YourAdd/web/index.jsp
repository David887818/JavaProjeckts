<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <title>Login</title>
</head>
<body>
<div class="background w-100 d-flex justify-content-center align-items-center">
    <div class="form w-25 rounded">
        <div class="m-5">
            <% String errMessage = (String) request.getAttribute("errMessage"); %>
            <% if (errMessage != null) {%>
            <h5 class="error"><%=errMessage%>
            </h5>
            <%}%>
            <form action="/loginServlet" method="post">
                <input type="email" class="name w-100 mt-2 btn btn-outline-dark" name="email" placeholder="Email">
                <input type="password" class="name w-100 mt-2 btn btn-outline-dark" name="password"
                       placeholder="password">
                <input type="submit" class=" w-100 mt-5 btn btn-dark" name="submit" value="Login">
            </form>
            <a href="regForm.jsp"> <input type="submit" class=" w-100 mt-3 btn btn-dark" name="submit" value="Register"></a>
        </div>
    </div>

</div>
</body>
</html>
