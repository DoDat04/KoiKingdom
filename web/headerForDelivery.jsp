<%-- 
    Document   : headerForDelivery
    Created on : Sep 21, 2024, 2:43:06 PM
    Author     : Minhngo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/headerForDelivery.css">
        <title>Header</title>
    </head>
    <body>
        <header class="text-black py-4" style="background-color: antiquewhite;">
            <div class="container">
                <div class="d-flex justify-content-between align-items-center">
                    <!-- Logo -->
                    <div class="logo">
                        <a href="MainController?action=homePage">
                            <img src="img/logo.png" style="width: 150px; height: 150px;">
                        </a>
                    </div>

                    <!-- Center Menu (Dashboard, Shipment History, Notification) -->
                    <div class="menu-center">
                        <ul class="nav justify-content-center">
                            <li class="nav-item">
                                <a class="nav-link text-black" href="homeForDelivery.jsp">Dashboard</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link text-black" href="shipmentHistory.jsp">Shipment History</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-black" href="#">Notification</a>
                            </li>
                        </ul>
                    </div>

                    <!-- Right Menu (Add to Cart, Account) -->
                    <div class="menu-right d-flex align-items-center"> 
                        <a href="#" class="text-black icon-size nav-link dropdown-toggle" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fas fa-user"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                            <c:choose>
                                <c:when test="${sessionScope.LOGIN_DELIVERY == null}">
                                    <li><a class="dropdown-item" href="deliveryProfile.jsp">Setting</a></li>
                                    <li><a class="dropdown-item" href="LogoutController">Logout</a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li class="dropdown-item" style="color: red">
                                        ${sessionScope.LOGIN_DELIVERY.firstName} ${sessionScope.LOGIN_DELIVERY.lastName}
                                    </li>
                                    <li><a class="dropdown-item" href="#">Shipment History</a></li>
                                    <li><a class="dropdown-item" href="#">Notification</a></li>
                                    <li><a class="dropdown-item" href="#">Profile</a></li>
                                    <li><a class="dropdown-item" href="home?action=Logout">Sign out</a></li>
                                    </c:otherwise>
                                </c:choose>

                        </ul>

                    </div>
                </div>
            </div>
        </header>
    </body>
</html>
