<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <title>LOGIN</title>
</head>

<body>
<img style="width: 100%; height:100vh; position: absolute" src="image/background_image.jpg">
<div class="body  p-3" style="position: relative">
    <div style="width: 30%" class="pl-5 pr-5 pb-5 pt-2 border rounded border-light  ">
        <% String errMessage = (String) request.getAttribute("errMessage"); %>
        <% if (errMessage != null) {%>
        <h4 style="color: red"><%=errMessage%>
        </h4>
        <%}%>
        <h1 style="color: whitesmoke" class="font-weight-light">LOGIN</h1>
        <form action="/loginServlet" method="post">
            <div class="form-group">
                <input style="background-color: rgba(61,61,61,0.11);color: whitesmoke" type="text"
                       class="form-control border border-light" name="email" type="text" placeholder="email">
            </div>
            <br>
            <div class="form-group">
                <input style="background-color: rgba(61,61,61,0.11);color: whitesmoke" type="password"
                       class="form-control border border-light" name="password" type="password" placeholder="password">
            </div>
            <br>
            <div class="d">
                <button style="background-color: rgba(0,0,0,0.11);color:whitesmoke;width: 100%" type="submit"
                        class=" btn btn-secondary border border-light">LOGIN
                </button>
            </div>
        </form>

        <a href="regForm.jsp">
            <div class="d1 mt-3">
                <button style="background-color: rgba(0,0,0,0.11);color:whitesmoke;width: 100%" type="submit"
                        class="btn btn-secondary border border-light">REGISTER
                </button>
            </div>
        </a>
    </div>
</div>
</body>
</html>
