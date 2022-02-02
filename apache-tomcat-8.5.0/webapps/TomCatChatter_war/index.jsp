<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="eng">
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" type=""></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" type=""></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <style type="">
        .container{
            max-width:1700px;
            margin:auto;
            max-height: 2000px;
        }
        img{
            max-width:100%;
        }
        .inbox_people {
            background: #f8f8f8 none repeat scroll 0 0;
            float: left;
            overflow: hidden;
            width: 40%; border-right:1px solid #c4c4c4;
        }
        .inbox_msg {
            border: 1px solid white;
            clear: both;
            overflow: hidden;
        }
        .top_spac{ margin: 20px 0 0;}


        .recent_heading {float: left; width:40%;}

        .srch_bar {
            display: inline-block;
            text-align: right;
            width: 60%;
        }
        .headind_srch{ padding:10px 29px 10px 20px; overflow:hidden; border-bottom:1px solid #c4c4c4;}

        .recent_heading h4 {
            color: #05728f;
            font-size: 21px;
            margin: auto;
        }
        .srch_bar input{
            border-width:0 0 1px 0; width:80%; padding:2px 0 4px 6px; background:none;}
        .srch_bar .input-group-addon button {
            background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
            border: medium none;
            padding: 0;
            color: #707070;
            font-size: 18px;
        }
        .srch_bar .input-group-addon { margin: 0 0 0 -27px;}

        .chat_ib h5{ font-size:15px; color:#464646; margin:0 0 8px 0;}
        .chat_ib h6{ font-size:13px; float:right; color: #4c4c4c}
        .chat_ib p{ font-size:14px; color:#989898; margin:auto}

        .chat_img {
            float: left;
            width: 11%;
        }
        .send_img_img{

            width: 57% ;
        }
        .receive_img_img{

            width: 57%;
        }
        .chat_ib {
            float: left;
            padding: 0 0 0 15px;
            width: 100%;
            background-color: white;

        }

        .chat_people{ overflow:hidden; clear:both;}

        .chat_list {
            border-bottom: 1px solid #c4c4c4;
            margin: 0;
            padding: 18px 16px 10px;
            width: 100%;
        }
        .inbox_chat { height: 550px; overflow-y: scroll;}

        .active_chat{

            background:#ebebeb;

        }

        .incoming_msg_img {
            display: inline-block;
            width: 6%;
        }
        .received_msg {
            display: inline-block;
            padding: 0 0 0 10px;
            vertical-align: top;
            width: 92%;
        }
        .received_with_msg p {
            background: #ebebeb none repeat scroll 0 0;
            border-radius: 3px;
            color: #646464;
            font-size: 14px;
            margin: 0;
            padding: 5px 10px 5px 12px;
            width: 100%;
        }
        .time_date {
            color: #747474;
            display: block;
            font-size: 12px;
            margin: 8px 0 0;
        }
        .received_with_msg { width: 57%;}

        .mesgs {
            float: left;
            padding: 30px 15px 0 25px;
            width: 60%;
        }

        .sent_msg p {
            background: #05728f none repeat scroll 0 0;
            border-radius: 3px;
            font-size: 14px;
            margin: 0; color:#fff;
            padding: 5px 10px 5px 12px;
            width:100%;
        }
        .outgoing_msg{ overflow:hidden; margin:26px 0 26px;}

        .sent_msg {
            float: right;
            width: 46%;
        }

        .input_msg_write input {
            background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
            border: medium none;
            color: #4c4c4c;
            font-size: 15px;
            min-height: 48px;
            width: 100%;
        }

        .type_msg {border-top: 1px solid #c4c4c4;
            position:relative;}
        .msg_send_btn {
            background: #05728f none repeat scroll 0 0;
            border: medium none;
            border-radius: 50%;
            color: #fff;
            cursor: pointer;
            font-size: 17px;
            height: 33px;
            position: absolute;
            right: 0;
            top: 11px;
            width: 33px;
        }
        .img_btn{
            background: #05728f none repeat scroll 0 0;
            border: medium none;
            border-radius: 50%;
            color: #fff;
            cursor: pointer;
            font-size: 17px;
            height: 33px;
            position: absolute;
            left: 300px;
            top: 11px;
            width: 33px;
        }
        .search_button {
            background: #05728f none repeat scroll 0 0;
            border: medium none;
            border-radius: 50%;
            color: #fff;
            cursor: pointer;
            font-size: 17px;
            height: 33px;
            position:inherit;
            right: 90px;
            left: 516px;
            top: 11px;
            width: 33px;
        }
        .messaging { padding: 0 0 50px 0;}

        .msg_history {
            height: 516px;
            overflow-y: scroll;
        }
        .logoutLblPos{

            position:fixed;
            right:10px;
            top:5px;
            background: #05728f;
        }
        .notification{

            position:fixed;
            background: #ffffff;
        }
    </style>

    <title><%= request.getRemoteUser() %></title>

    <!--
    TODO number of messages left in the title(total)
              number of message for the each contact
    -->
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
%>
<div class="container">
    <div class="messaging">
        <div class="inbox_msg">
            <div class="inbox_people">
                <div class="headind_srch">
                    <div class="recent_heading">
                        <h4>Recent</h4>
                    </div>
                    <div class="srch_bar">
                        <div class="stylish-input-group">
                            <!-- SEND FRIEND REQUEST  -->
                            <form action="sendFriendRequest" method="POST">
                                <label for="search-box"></label>
                                    <input type="text" id="search-box" class="search-bar" placeholder="@UserId" name="addFriend">
                                        <span class="input-group-addon">
                                            <button type="submit"> <i class="fa fa-user-plus" aria-hidden="true"></i> </button>
                                        </span>

                            </form>
                            <!-- SEND FRIEND REQUEST ENDS HERE -->
                        </div>
                    </div>
                    <form action="logout" align="right" method="POST" >
                        <label class="logoutLblPos">
                            <input name="submit2" type="submit" id="submit2" value="log out">
                        </label>
                    </form>

                </div>
                <div class="inbox_chat">
                    <!-- RECENT CHATS -->
                    <%--@elvariable id="recentChatModels" type="java.util.List"--%>
                    <c:forEach items="${recentChatModels}" var="i">
                    <form action="chat" method="POST">
                        <button class="chat_list"  style="background-color:white">
                            <div class="chat_people">
                                <div class="chat_img"> <img src="${i.profile}" alt="${i.friendId}"/>
                                </div>
                                <div class="chat_ib">
                                    <label>
                                         <p style="color: black"><c:out value="${i.friendId}"/></p>
                                    </label> <h6  class="chat_date"><c:out value="${i.lastDate}"/></h6>

                                    <p>${i.lastMessage}</p>
                                </div>
                            </div>

                        </button>
                        <label>
                            <input  value="${i.friendId}" name="friendId" hidden />
                        </label>
                    </form>
                    </c:forEach>
                </div>
                    <!-- RECENT CHAT ENDS HERE -->
            </div>


            <!-- THE NOTIFICATION STUFFS STARTS HERE -->
            <form action="notification">
                <button style="font-size:24px" class="notification"> <% out.println("Notifications("+ request.getAttribute("count") +")");%>
                    <i class="material-icons" style="font-size:36px;color:red">notifications_none</i>
                </button>
            </form>

            <!-- THE NOTIFICATION STUFFS ENDS HERE -->

                <div class="mesgs">
                    <!-- for each for the msgs-->
                    <div class="msg_history">
                        <%--@elvariable id="chatModels" type="java.util.List"--%>
                        <c:forEach items="${chatModels}" var="i">
                            <div class="outgoing_msg"><c:if test="${i.who == 'you'}">
                                <div class="sent_msg">

                                    <c:if test="${i.type == 'image'}">
                                        <img src="${i.message}"  alt="img"/>
                                    </c:if>

                                    <c:if test="${i.type == 'chat'}">
                                        <p>${i.message}</p>
                                    </c:if>

                                    <span class="time_date">${i.timeAndDate}</span>
                                </div>
                            </div>
                            </c:if>
                            <div class="incoming_msg"><c:if test="${i.who !='you'}">
                                <div class="incoming_msg_img"> <img src="" alt=""> </div>
                                    <div class="received_msg" >
                                        <div class="received_with_msg">

                                        <c:if test="${i.type == 'image'}">
                                            <img  src="${i.message}" alt="img"/>
                                        </c:if>
                                        <c:if test="${i.type == 'chat'}">
                                            <p>${i.message}</p>
                                        </c:if>

                                        <span class="time_date">${i.timeAndDate}</span>
                                    </div>
                                </div>
                            </div>
                            </c:if>
                        </div>
                        </c:forEach>
                    </div>
                    <div class="type_msg">
                        <div class="input_msg_write">
                            <form action="send_message" method="POST">
                                <input type="text" name="message" class="write_msg" placeholder="Type a message" />
                                <button class="msg_send_btn" type="submit"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
                            </form>
                          <!-- <form action="send_multimedia" method="POST" enctype="multipart/form-data">
                                <input type="file" id="myfile" name="file_to_send">
                                <button type="submit" value="myfile">Send</button>
                            </form> -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>