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
<div class="d-flex justify-content-center w-100" style="color: whitesmoke;position: relative;">
    <div class="font-weight-light col-md-6">
        <h3 class="m-3 font-weight-light" style="color: whitesmoke">Posts:</h3>
        <% for (Post post : posts) { %>
        <div class="m-3 rounded d-flex font-weight-light" style=" border:2px solid">
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

            <form action="/deleteServlet">
                <div style="border: solid 1px" class="d mt-3 mr-3 rounded ">
                    <button style="color: aliceblue; background-color:rgba(61,61,61,0.11) " class=" btn " name="id"
                            value="<%=post.getId()%>">Delete
                    </button>
                </div>
            </form>

        </div>
        <% } %>
    </div>

    <div class="body font-weight-light  col-md-4" style="position: relative">
        <h3 style="color: whitesmoke" class="m-3 font-weight-light">ADD Post:</h3>
        <div style="border: 2px solid" class="pl-5 pr-5 pb-5 pt-2  rounded ">
            <form class="font-weight-light" action="/addPostServlet" method="post">
                <div class="form-group">
                    <h3 class="mt-2" style="color: whitesmoke">Title:</h3>
                    <input style="background-color: rgba(61,61,61,0.11);color: whitesmoke"
                           class="form-control border border-light" type="text" placeholder="title" name="title">
                </div>
                <div class="form-group font-weight-light ">
                    <h3 style="color: whitesmoke">Content:</h3>
                    <textarea style="background-color: rgba(61,61,61,0.11);color: whitesmoke"
                              class="form-control border border-light" placeholder="content" name="content">
                </textarea>
                    <h3 class="mt-3" style="color: whitesmoke">Category:</h3>
                    <select class=" form-control border border-light"
                            style="background-color: rgba(61,61,61,0.11);color: whitesmoke" name="category">--%>
                        <% for (Category category : categories) {%>
                        <option value="<%=category.getId()%>"><%=category.getName()%>
                        </option>
                        <%}%>
                    </select>
                </div>
                <%--<input class="form-control border border-light" type="file" name="file">--%>
                <br>
                <div class="d ">
                    <input style="background-color: rgba(0,0,0,0.11);color:whitesmoke;width: 100%" type="submit"
                           class="btn btn-secondary border border-light" value="Send">
                </div>
            </form>

        </div>
        <h3 style="color: whitesmoke" class="m-3 font-weight-light">UPDATE Post:</h3>
        <div style="border: 2px solid" class="pl-5 pr-5 pb-5 pt-2  rounded ">
            <form class="font-weight-light" action="/updateServlet" method="post">
                <div class="form-group">
                    <h3 class="mt-2" style="color: whitesmoke">Select Id for Update</h3>
                    <input style="background-color: rgba(61,61,61,0.11);color: whitesmoke"
                           class="form-control border border-light" type="text" placeholder="ID" name="id">
                </div>

                <div class="form-group">
                    <h3 class="" style="color: whitesmoke">Title:</h3>
                    <input style="background-color: rgba(61,61,61,0.11);color: whitesmoke"
                           class="form-control border border-light" type="text" placeholder="title" name="title">
                </div>

                <div class="form-group font-weight-light ">
                    <h3 style="color: whitesmoke">Content:</h3>
                    <textarea style="background-color: rgba(61,61,61,0.11);color: whitesmoke"
                              class="form-control border border-light" placeholder="content" name="content">
                </textarea>
                    <h3 class="" style="color: whitesmoke">Category:</h3>
                    <select class=" form-control border border-light"
                            style="background-color: rgba(61,61,61,0.11);color: whitesmoke" name="category">--%>
                        <% for (Category category : categories) {%>
                        <option value="<%=category.getId()%>"><%=category.getName()%>
                        </option>
                        <%}%>
                    </select>
                </div>
                <%--<input class="form-control border border-light" type="file" onchange="picUrl">--%>
                <br>
                <div class="d">
                    <input style="background-color: rgba(0,0,0,0.11);color:whitesmoke;width: 100%" type="submit"
                           class="btn btn-secondary border border-light" value="Send">
                </div>
            </form>

        </div>

    </div>

    <div style="display: flex; flex-direction: column;" class="col-md-2 ">
        <h3 class="m-3 font-weight-light" style="color: whitesmoke">Categories:</h3>
        <ul class="font-weight-light rounded"
            style="list-style: none; border: 2px solid;display: flex;flex-direction: column;justify-content: center;align-items: center;">
            <% for (Category category : categories) { %>
            <li class=" font-weight-light"><%=category.getName()%>
            </li>
            <% } %>
        </ul>

        <a class="rounded d" style="border: 2px solid; color:aliceblue; text-align: center" href="/logout"><h4
                class="a font-weight-light">Logout</h4></a>
    </div>

</div>
</body>
</html>
