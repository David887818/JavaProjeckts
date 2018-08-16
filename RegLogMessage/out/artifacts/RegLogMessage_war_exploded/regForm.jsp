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
    <title>REGISTER</title>
</head>
<body>
<img style="width: 100%; height:100vh; position: absolute" src="image/background_image.jpg">
<div style="width: 30%; color: whitesmoke" class="position-relative p-3">
<div class=" w-100 border border-light rounded pl-5 pr-5 pb-5">
    <a class="back d-flex mr-1" href="index.jsp"  ><h5> < Back</h5></a>

    <h1 style="color: ghostwhite" class="font-weight-light p-2">Register</h1>
<form  action="/registrationServlet" method="post" >
    <div class=" form-group">
        <%--@declare id="name"--%><label for="name">Name:</label>
        <input style="background-color: rgba(61,61,61,0.11);color: whitesmoke" type="text" class="form-control border border-light" name="name"  placeholder="name">
    </div>
    <div class="form-group">
        <%--@declare id="surname"--%><label for="surname">Surname:</label>
        <input style="background-color: rgba(61,61,61,0.11);color: whitesmoke" type="text" class="form-control border border-light" name="surname"  placeholder="surname">
    </div>
<div class="form-group">
    <%--@declare id="email"--%><label for="email">Email:</label>
    <input style="background-color: rgba(61,61,61,0.11);color: whitesmoke" type="email" class="form-control border border-light" name="email"  placeholder="email">
    </div>
    <div class="form-group">
        <%--@declare id="password"--%><label for="password">Password:</label>
        <input style="background-color: rgba(61,61,61,0.11);color: whitesmoke" type="password" class="form-control border border-light" name="password" placeholder="password">
    </div>
    <br>
    <div class="d">
        <button style="background-color: rgba(0,0,0,0.11);color:whitesmoke;width: 100%" type="submit"
                class="btn btn-secondary border border-light">Register
        </button>
    </div>
</form>
</div>
</div>
</body>
</html>
