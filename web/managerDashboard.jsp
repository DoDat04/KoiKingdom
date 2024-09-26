<%-- 
    Document   : managerDashboard
    Created on : Sep 24, 2024, 4:18:35 PM
    Author     : Nguyen Huu Khoan
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manager Dashboard</title>
    <style>
        /* Tổng quan */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }

        h1 {
            text-align: center;
            margin: 20px 0;
            color: #333;
        }

        /* Menu */
        .navbar {
            background-color: #333;
            overflow: hidden;
        }

        .navbar a {
            float: left;
            display: block;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
            font-size: 17px;
        }

        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }

        .navbar a.active {
            background-color: #04AA6D;
            color: white;
        }

        /* Container cho nội dung */
        .container {
            padding: 20px;
            margin: auto;
            max-width: 1200px;
            background-color: #fff;
            box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
            margin-top: 20px;
        }

        /* Nội dung trong trang */
        .content {
            text-align: center;
            margin-top: 20px;
        }

        /* Footer */
        .footer {
            background-color: #333;
            color: white;
            padding: 10px 0;
            text-align: center;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>

<!-- Menu -->
<div class="navbar">
    <a href="managerDashboard.jsp" class="active">Home</a>
    <a href="GetListCustomer">Customer</a>
    <a href="GetListEmployee">Employee</a>
    <a href="#">Tour</a>
</div>

<!-- Nội dung chính -->
<div class="container">
    <h1>Welcome to Manager Dashboard</h1>
    
</div>

<!-- Footer -->
<div class="footer">
    <p>&copy; 2024 Manager Dashboard. All rights reserved.</p>
</div>

</body>
</html>

