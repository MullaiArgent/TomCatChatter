<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mulla
  Date: 01-02-2022
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        #fb{
            width:500px;
            border:1px solid gray;
            border-radius:5px;
            position:relative;
            height:175px;
        }
        #fb p{
            font-family:sans-serif;
            margin: 0 0 0 10px;
            line-height:30px;
        }

        #fb-top span{
            color:#4267B2;
            float:right;
            margin-right:10px;
        }
        #fb-top{
            background-color:#efefef;
            height:30px;
            width:500px;
            border-radius:5px 5px 0 0;
            position:absolute;
            top:-1px;
            left:-1px;
            border:1px solid gray;
        }

        #fb img{
            position:absolute;
            left:10px;
            top:52.5px;
        }

        #info{
            position:absolute;
            left:120px;
            top:75px;
        }

        #info {
            color:#4267B2;
            line-height:25px;
            font-size:18px;
        }

        #info span{
            color:#777;
            font-size:14px;
        }

        #button-block{
            position:absolute;
            right:10px;
            top:85px;
        }

        #button-block div{
            display:inline-block;
        }

        #confirm, #delete{
            background-color:#4267B2;
            color:white;
            padding:7px;
            border-radius:2px;
            margin-right:10px;
            font-family:sans-serif;
        }

        #delete{
            color:#222;
            background-color:#bbb;
            border:1px solid #999;
            padding:6px;
            margin-right:0;
        }

        #button-block div:hover{
            opacity:.8;
            cursor:pointer;
        }

    </style>
    <title><% out.println("Notifications("+ request.getAttribute("count") +")");%> </title> <!-- TODO NOTIFICATION COUNT-->
</head>
<body>
<table>
    <%
            if (request.getAttribute("count").equals("0")){
                out.println("You have no friend Requests");
            }
    %>

    <%--@elvariable id="notificationModels" type="java.util.List"--%>
    <c:forEach items="${notificationModels}" var="i">
        <form action="confirmInComingFriendRequest" method="POST">
        <div id="fb">
            <img src="" height="100" width="100" alt="">
            <label>
                <input value="${i.getSenderId()}" name="friendId" hidden>
            </label>
            <p id="info"><b></b><c:out value="${i.getSenderId()}"/><br></p>
            <div id="button-block">
                <button type="submit" id="confirm">Confirm</button>

            </div>
        </div>
        </form>
        <form action="rejectIncomingFriendRequest" method="POST">
            <input value="${i.getSenderId()}" name="friendId" hidden>
            <button type="submit" id="delete">Delete Request</button>
        </form>
    </c:forEach>

</table>
</body>
</html>
