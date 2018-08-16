<%@ page import="model.Category" %>
<%@ page import="model.Post" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="../css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body style="background-image: url('../image/background_image.jpg')">
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    List<Post> posts = (List<Post>) request.getAttribute("posts");
%>
<div class="d-flex justify-content-center w-75" style="color: whitesmoke;position: relative;">
    <div class="font-weight-light" style="width: 70%">
        <h3 class="m-3 font-weight-light" style="color: whitesmoke">Posts:</h3>
        <% for (Post post : posts) { %>
        <div class="m-3  d-flex font-weight-light" style=" border:2px solid">
            <div class="w-75"><h2 class="m-4 font-weight-light"><%=post.getTitle()%>
            </h2>
                <p class="m-2 font-weight-light"><%=post.getContent()%>
                </p>
                <span class="m-2 font-weight-light">id: <%=post.getId()%></span><br>
                <span class="m-2 font-weight-light">date: <%=post.getCreatedDate()%></span><br>
                <span class="m-2 font-weight-light">author: <%=post.getUser().getName() + post.getUser().getSurname()%></span><br>
                <span class="m-2 font-weight-light">category: <%=post.getCategory().getName()%></span><br>
            </div>
            <div class="m-3 w-25">
                <img class="w-100" src="<%=post.getPicUrl()%>">
            </div>

        </div>
        <% } %>
    </div>

    <div style="display: flex; flex-direction: column;" class="w-25">
        <h3 class="m-3 font-weight-light" style="color: whitesmoke">Categories:</h3>
        <ul class="font-weight-light"
            style="list-style: none; border: 2px solid;display: flex;flex-direction: column;justify-content: center;align-items: center;">
            <% for (Category category : categories) { %>
            <li class=" font-weight-light"><%=category.getName()%>
            </li>
            <% } %>
        </ul>
        <a style="border: 2px solid; color:aliceblue; text-align: center" href="/logout"><h3
                class="a font-weight-light">LOGOUT</h3></a>
    </div>
</div>
</body>
</html>
