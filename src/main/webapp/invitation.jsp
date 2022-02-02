<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html" lang="en">
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css-login">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet">
    <meta charset="ISO-8859-1">
    <title>Add Friend</title>
    <style>
        Body {
            font-family: Calibri, Helvetica, sans-serif;
            background-color: white;
        }
        button {
            background-color: #05728f;
            width: 100%;
            color: rgb(0, 0, 0);
            padding: 15px;
            margin: 10px 0;
            border: none;
            cursor: pointer;
        }
        form {
            border: 3px solid #f1f1f1;
        }
        input[type=text], input[type=password] {
            width: 100%;
            margin: 8px 0;
            padding: 12px 20px;
            display: inline-block;
            border: 2px solid  #05728f;
            box-sizing: border-box;
        }
        button:hover {
            opacity: 0.7;
        }

        .container {
            padding: 50px;
            background-color: white;
            position:relative;
            width:fit-content;
            max-height: fit-content;

        }
    </style>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet">

</head>
<body>
<script>
    function myCopyFunction() {
        var copyText = document.getElementById("myInput");
        copyText.select();
        copyText.setSelectionRange(0, 99999);
        navigator.clipboard.writeText(copyText.value);

        var tooltip = document.getElementById("myTooltip");

    }

    function outFunc() {
        var tooltip = document.getElementById("myTooltip");
    }
</script>
<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
%>
<div style="text-align: center;">
    <h2> Send Invitation to <%= request.getSession().getAttribute("friendId") %> </h2>
</div>
<div class="container">
    <form action="sendInvitation" method="POST">

        <input type=text value=" http://localhost:8080/TomCatChatter_war/invitation?invited=<%= request.getRemoteUser() %> "  name="invitationUrl" id="myInput" required> <!-- invitation url-->

        <button type="submit" value="confirm" name="confirm">Send Friend Request</button>
        <!-- TODO the invitation URL-->
        <br>
    </form>
    <button onclick="myCopyFunction()" onmouseout="outFunc()">Copy Invitation URL</button>
    <br>
    <form action="cancel_friend">
        <button type="submit">Cancel</button>
    </form>
</div>
</body>
</html>