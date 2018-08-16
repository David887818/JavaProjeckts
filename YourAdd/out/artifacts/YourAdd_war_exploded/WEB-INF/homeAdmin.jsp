<%@ page import="model.User" %>
<%@ page import="model.Advertisement" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: DAVID
  Date: 07.08.2018
  Time: 0:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <title>Admin Page</title>
</head>
<body>
<%
    //    List<User> userList = (List<User>) request.getAttribute("user");
    User currentUser = (User) request.getAttribute("currentUser");
    List<Advertisement> advertisements = (List<Advertisement>) request.getAttribute("advertisement");
    List<Category> categoryList = (List<Category>) request.getAttribute("category");
//    List<Category> categoryList1=new ArrayList<>();
//    for (Advertisement advertisement : advertisements) {
//        categoryList1=(advertisement.getCategory());
//    }
%>
<div class="background w-100 d-flex">
    <div class="w-50 m-2 form rounded ">
        <div class="d-flex  main m-2 rounded justify-content-between align-items-center">
            <h3 class="m-2">Welcome:<%=currentUser.getName().toUpperCase()%>
            </h3>
            <form class="w-25 m-3 " action="/logout">
                <button style="height: 70%" class="btn btn-outline-dark bt w-100">Logout</button>
            </form>
        </div>
        <div class="d-flex w-100  mt-5 justify-content-center align-items-center">

            <div class="w-50 name m-5">


                <div class="m-5">
                    <h5 class="mb-2">Add Advertisement:</h5>
                    <form action="/addAdsServlet" method="post" enctype="multipart/form-data">
                        <input type="text" class="name w-100 mt-2 btn btn-outline-dark" placeholder="title"
                               name="title">
                        <input type="text" class="name w-100 mt-2 btn btn-outline-dark" placeholder="description"
                               name="description">
                        <input type="number" class="name w-100 mt-2 btn btn-outline-dark" placeholder="price"
                               name="price">
                        <input type="file" class="name w-100 mt-2 btn btn-outline-dark" placeholder="price"
                        name="image">
                        <select class="w-100 mt-2" name="category">
                            <% for (Category category : categoryList) {%>
                            <option value="<%=category.getId()%>"><%=category.getName()%></option>
                            <%}%>
                        </select>
                        <input type="submit" class="w-100 mt-3 btn btn-dark" placeholder="Add" value="Add" name="add">
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="w-50 m-2 form rounded ">
       <div class="d-flex justify-content-between m-4"> <h3 class="">Advertisements:</h3>
           <form  action="/searchServlet" method="post">
               <div >
           <select  name="category">
        <% for (Category category : categoryList) {%>
            <option value="<%=category.getId()%>"><%=category.getName()%></option>
               <%}%>
        </select>
               <input type="submit" class="w-50 mt-2 btn btn-dark" placeholder="Search" value="Search" name="Search">
               </div>
           </form>
       </div>
        <%for (Advertisement advertisement : advertisements) {%>
        <div class="name m-3">
            <div class="m-2">
                <h6>Title: <%=advertisement.getTitle()%></h6>
                <h6>Id: <%=advertisement.getId()%></h6>
                <h6>Author: <%=advertisement.getAuthor().getName()%></h6>
                <h6>Price: <%=advertisement.getPrice()%></h6>
                <h6>Description: <%=advertisement.getDescription()%></h6>
                <h6>Category: <%=advertisement.getCategory().getName()%></h6>
            </div>
            <form action="/deleteServlet" method="post">
                <input type="hidden" name="id" value="<%=advertisement.getId()%>">
                <input type="submit" class="w-25 m-3 btn btn-dark" placeholder="Delete" value="Delete" name="Delete">
            </form>
        </div>
        <%}%>

    </div>
</div>
</body>
</html>