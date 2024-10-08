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
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
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
        <!--        <div class="navbar">
                    <a href="home?action=Manager">Home</a>
                    <a href="GetListCustomer">Customer</a>
                    <a href="GetListEmployee">Employee</a>
                    <a href="GetListTour">Tour</a>
                </div>-->
         <jsp:include page="headerForManager.jsp" flush="true">
            <jsp:param name="searchController" value="SearchByCustomerName"/>
            <jsp:param name="placeholder" value="Search customer name"/>
        </jsp:include>   
        <!-- Nội dung chính -->
          <div style="    margin-top: 25vh;
             margin-left: 17%;
             margin-right: 6%;" class="main-content">      
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
                            <th>Action</th>
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
                                <td>
                                    <a href="updateStatus?customerID=${customer.customerID}" 
                                       onclick="return confirm('Are you sure you want to change the status?');">
                                        Change Status
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${not empty UPDATE_STATUS}">
                    <div class="alert alert-success">
                        ${UPDATE_STATUS}
                    </div>
                </c:if>
                <c:if test="${not empty SEARCH_MESSAGE}">
                    <p style="color: red; text-align: center;">${SEARCH_MESSAGE}</p>
                </c:if>
            </div>
        </div>
    </body>
</html>
