<%@ page import="model.Message" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
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
    <title>HOME</title>
</head>
<style>
    .logout:hover {
        background-color: rgb(8, 253, 255);
        text-decoration: none;
        color: black;
    }

    .b {
        display: flex;
        justify-content: center;
        text-decoration: none;
    }

    .bt {
        color: aliceblue;
        background-color: rgba(61, 61, 61, 0.11)
    }

    .bt:hover {
        background-color: greenyellow;
        color: black;

    }
</style>
<body style="color: whitesmoke; background-image:url('../image/background_image.jpg')">
<%
    List<User> userList = (List<User>) request.getAttribute("user");
    User currentUser = (User) request.getAttribute("currentUser");
    List<Message> messageList = (List<Message>) request.getAttribute("message");
%>
<div class="w-100 d-flex">
    <div class="w-50">
        <div style="color: whitesmoke; height: 90px"
             class=" d-flex align-items-center justify-content-between m-2 w-100 border  border-secondary">
            <h3>WELCOME : <%=currentUser.getName()%>
            </h3>
            <form class="w-25 mr-3"  action="/logout">
                <button style="height: 70%" class="btn border border-secondary  bt w-100">Logout</button>
            </form>
        </div>
        <div class="border m-2 mt-5 w-100 border-secondary">
            <div class="m-5">
                <h3>Messages:</h3>
                <% for (Message message : messageList) {%>
                <div class="d-flex">
                    <span class="m-2 font-weight-light">From: <%=message.getFrom().getName()%></span>
                    <span class="m-2 font-weight-light">-- <%=message.getText()%></span>
                </div>
                <%--<form action="/messageServlet">--%>
                <%--<div style="border: solid 1px" class="d mt-3 mr-3 rounded ">--%>
                <%--<input type="text" name="sms" placeholder="input message">--%>
                <%--<button style="color: aliceblue; background-color:rgba(61,61,61,0.11) " class=" btn " name="id"--%>
                <%--value="<%=user.getId()%>">Answer--%>
                <%--</button>--%>
                <%--</div>--%>
                <%--</form>--%>
                <% } %>
            </div>
        </div>
    </div>


    <div class=" w-50 ml-5 d-flex">

        <div class="border w-100 m-2 border-secondary">
            <div class="m-5">
                <h3 class="mb-4">Users:</h3>
                <% for (User user : userList) {%>
                <span class="m-2  font-weight-light">Name: <%=user.getName()%></span><br>
                <span class="m-2 font-weight-light">Surname: <%=user.getSurname()%></span><br>
                <span class="m-2 font-weight-light">Email: <%=user.getEmail()%></span><br>
                <form class="mb-5" action="/messageServlet">
                    <div  class="b mt-3 mr-3  ">
                        <input type="text" class="w-75 btn border border-secondary mr-3" name="sms" style="background-color: rgba(0,0,0,0.23)"
                               placeholder="input message">
                        <button class=" bt w-25 btn btn-secondary " name="id"
                                value="<%=user.getId()%>">Send Message
                        </button>
                    </div>
                </form>
                <% } %>
            </div>
        </div>

    </div>


</div>
</body>
</html>
