<%-- 
    Document   : manageCustomer
    Created on : Sep 24, 2024, 1:23:21 PM
    Author     : Nguyen Huu Khoan
--%>
<%@page import="java.util.List"%>
<%@page import="koikd.customer.CustomerDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Customer Management</title>
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

        /* Menu điều hướng */
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

        /* Container */
        .container {
            padding: 20px;
            margin: auto;
            max-width: 1200px;
            background-color: #fff;
            box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
            margin-top: 20px;
        }

        /* Bảng khách hàng */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #04AA6D;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
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

        .status-active {
            color: green;
            font-weight: bold;
        }

        .status-inactive {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>

<!-- Menu điều hướng -->
<div class="navbar">
    <a href="managerDashboard.jsp">Home</a>
    <a href="manageCustomer.jsp" class="active">Customer</a>
    <a href="#">Employee</a>
    <a href="#">Tour</a>
</div>

<!-- Nội dung chính -->
<div class="container">
    <h1>Customer Management</h1>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>Last Name</th>
                <th>First Name</th>
                <th>Address</th>
                <th>Account Type</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="customer" items="${customer}">
                <tr>
                    <td>${customer.customerID}</td>
                    <td>${customer.email}</td>
                    <td>${customer.lastName}</td>
                    <td>${customer.firstName}</td>
                    <td>${customer.address}</td>
                    <td>${customer.accountType}</td>
                    <td>
                        <c:choose>
                            <c:when test="${customer.status}">
                                <span class="status-active">Active</span>
                            </c:when>
                            <c:otherwise>
                                <span class="status-inactive">Inactive</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- Footer -->
<div class="footer">
    <p>&copy; 2024 Customer Management. All rights reserved.</p>
</div>

</body>
</html>