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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
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
                                    <li><a type="button" class="btn btn-primary dropdown-item" data-bs-toggle="modal" data-bs-target="#profileModal" href="home?action=Profile">Profile</a></li>
                                    <li><a class="dropdown-item" href="home?action=Logout">Sign out</a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="profileModal" tabindex="-1" aria-labelledby="profileModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="UpdateProfileDeliveryController" method="post" enctype="multipart/form-data" id="profileForm">
                                <div class="text-center mb-4">
                                    <div class="avatar-container">
                                        <img id="avatarPreview" 
                                             src="${sessionScope.AVATAR != null ? sessionScope.AVATAR : 'images/default_avatar.png'}" 
                                             alt="">
                                    </div>
                                    <input type="file" id="avatar" name="profileImage" class="form-control mt-2" accept="image/*" onchange="previewAvatar()">
                                </div>

                                <label for="fname">Họ:</label>
                                <input type="text" id="fname" name="fname" readonly value="${sessionScope.LOGIN_DELIVERY.lastName}">

                                <label for="lname">Tên:</label>
                                <input type="text" id="lname" name="lname" readonly value="${sessionScope.LOGIN_DELIVERY.firstName}">

                                <label for="email">Email:</label>
                                <input type="email" id="email" name="email" readonly value="${sessionScope.LOGIN_DELIVERY.email}">

                                <label for="address">Địa chỉ:</label>
                                <input type="text" id="address" name="address" readonly value="${sessionScope.LOGIN_DELIVERY.address}">

                                <div class="mb-3 select-group" id="addressSelectDiv" style="display: none;">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label for="city" class="form-label">Tỉnh / Thành:</label>
                                            <select class="form-select" id="city" name="city">
                                                <option value="">Chọn tỉnh / thành</option>
                                            </select>
                                        </div>
                                        <div class="col-md-4">
                                            <label for="district" class="form-label">Quận / Huyện:</label>
                                            <select class="form-select" id="district" name="district">
                                                <option value="">Chọn quận / huyện</option>
                                            </select>
                                        </div>
                                        <div class="col-md-4">
                                            <label for="ward" class="form-label">Phường / Xã:</label>
                                            <select class="form-select" id="ward" name="ward">
                                                <option value="">Chọn phường / xã</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <label for="role">Vai trò:</label>
                                <input type="text" id="role" name="role" readonly value="${sessionScope.LOGIN_DELIVERY.role}">

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" id="closeButton" data-bs-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-secondary" id="editButton" onclick="toggleEdit()">Edit</button>
                                    <button type="submit" name="action" class="btn btn-secondary" id="saveButton" style="display: none;">Save</button>
                                    <button type="button" class="btn btn-primary" id="cancelButton" onclick="toggleEdit()" style="display: none;">Cancel</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <<script src="js/headerForDelivery.js"></script>
                </div>
            </div>
        </header>
        <script src="js/address.js"></script>
    </body>
</html>
