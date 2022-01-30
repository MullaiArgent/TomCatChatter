<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="eng">
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css-regiter">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> Registration Page </title>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet">
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
        .cancelbtn {
            width: auto;
            padding: 10px 18px;
            margin: 10px 5px;
        }

        .container {
            padding: 50px;
            background-color: white;
            position:relative;
            width:fit-content;
            max-height: fit-content;
        }

    </style>
</head>
<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
%>
<body>
<div style="text-align: center;"> <h1> Register </h1> </div>
<form action = "register" method = "POST">
    <div class = "container">
        <label>Username : </label>
        <input type="text" placeholder="Enter Username" name= "userid" required>

        <label>Full Name : </label>
        <input type="text" placeholder="Enter Full Name" name="full_name" required>

        <label>Mail Id : </label>
        <input type="text" placeholder="Enter your Email ID" name="mail_id" required>

        <label>Password : </label>
        <input type="password" placeholder="Enter Password" name= "password" required>

        <label>Re-type Password : </label>
        <input type="password" placeholder="Re-Type the Password" name= "re_password" required>

        <label for="myfile"> <i class="fa fa-upload" aria-hidden="true"></i> Select a Profile Picture : </label>
        <input type="file" id="myfile" name="profile_pic">

        <button type="submit" value = "register"><i class="fa fa-sign-in" aria-hidden="true"></i> Sign in </button>


    </div>
</form>
</body>
</html>
