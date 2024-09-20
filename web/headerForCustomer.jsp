<%-- 
    Document   : headerForCustomer
    Created on : Sep 14, 2024, 7:21:01 AM
    Author     : ADMIN LAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Footer</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/header.css">       
    </head>
    <body>
        <header class="text-black py-4" style="background-color: #fdf4f0;">
            <div class="container">
                <div class="d-flex justify-content-between align-items-center">
                    <!-- Logo -->
                    <div class="logo">
                        <a href="home?action=homePage">
                            <img src="img/logo.png" style="width: 150px; height: 150px;">
                        </a>
                    </div>

                    <!-- Center Menu (Home, Booking, Contact, Information) -->
                    <div class="menu-center">
                        <ul class="nav justify-content-center">
                            <li class="nav-item">
                                <a class="nav-link text-black" href="#">Home</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle text-black" id="bookingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Booking</a>
                                <ul class="dropdown-menu" aria-labelledby="bookingDropdown">
                                    <li><a class="dropdown-item" href="#">Tour</a></li>
                                    <li><a class="dropdown-item" href="#">Custom Tour</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-black" href="#">Services</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-black dropdown-toggle" id="informationDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Information</a>
                                <ul class="dropdown-menu" aria-labelledby="informationDropdown">
                                    <li><a class="dropdown-item" href="#">Koi Fish</a></li>
                                    <li><a class="dropdown-item" href="#">Farms</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-black" href="#">Contact</a>
                            </li>
                        </ul>
                    </div>

                    <!-- Right Menu (Add to Cart, Account) -->
                    <div class="menu-right d-flex align-items-center">
                        <a href="#" class="text-black me-3 icon-size">
                            <i class="fas fa-shopping-cart"></i>
                        </a>
                        <a href="#" class="text-black me-3 icon-size">
                            <i class="fa-solid fa-heart"></i>
                        </a>
                        <a href="#" class="text-black icon-size nav-link dropdown-toggle" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fas fa-user"></i>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="userDropdown">      
                            <c:choose>
                                <c:when test="${sessionScope.LOGIN_USER == null and sessionScope.LOGIN_GMAIL == null}">
                                    <li><a class="dropdown-item" href="#"><i class="adm_icon fas fa-question-circle"></i> FAQS</a></li>
                                    <li><a class="dropdown-item" href="login"><i class="adm_icon fas fa-sign-in-alt"></i> Sign in</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${sessionScope.LOGIN_USER != null}">
                                            <li class="dropdown-item" style="color: red"> ${sessionScope.LOGIN_USER.firstName} ${sessionScope.LOGIN_USER.lastName}</li>
                                            </c:when>
                                            <c:when test="${sessionScope.LOGIN_GMAIL != null}">
                                            <li class="dropdown-item" style="color: red"> ${sessionScope.lastName} ${sessionScope.firstName}</li>
                                            </c:when>
                                        </c:choose>
                                    <li><a class="dropdown-item" href="#"><i class="adm_icon fas fa-question-circle"></i> FAQS</a></li>
                                    <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#updateProfileModal"><i class="fa-solid fa-user-pen"></i> Update Profile</a></li>
                                    <li><a class="dropdown-item" href="#"><i class="fa-solid fa-bell"></i> Notification</a></li>
                                    <li><a class="dropdown-item" href="#"><i class="fa fa-history"></i> My Order</a></li>
                                    <li><a class="dropdown-item" href="home?action=Logout"><i class="fa-solid fa-right-from-bracket"></i> Sign out</a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>

                        <!-- Update Profile Modal -->
                        <div class="modal fade" id="updateProfileModal" tabindex="-1" aria-labelledby="updateProfileModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="updateProfileModalLabel">Update Profile</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="UpdateProfileController" method="post" enctype="multipart/form-data">
                                            <!-- Avatar Section -->
                                            <div class="text-center mb-4">
                                                <div class="avatar-container">
                                                    <img id="avatarPreview" src="${sessionScope.AVATAR}" alt="">
                                                </div>
                                                <input type="file" id="avatar" name="profileImage" class="form-control mt-2" accept="image/*" onchange="previewAvatar()">
                                            </div>

                                            <div class="mb-3">
                                                <label for="email" class="form-label">Email:</label>
                                                <input type="email" class="form-control" id="email" name="email" 
                                                       value="${sessionScope.LOGIN_GMAIL != null ? sessionScope.LOGIN_GMAIL.email : sessionScope.LOGIN_USER.email}">
                                            </div>
                                            <div class="mb-3">
                                                <label for="firstName" class="form-label">First Name:</label>
                                                <input type="text" class="form-control" id="firstName" name="firstName" 
                                                       value="${sessionScope.LOGIN_GMAIL != null ? sessionScope.firstName : sessionScope.LOGIN_USER.firstName}">
                                            </div>
                                            <div class="mb-3">
                                                <label for="lastName" class="form-label">Last Name:</label>
                                                <input type="text" class="form-control" id="lastName" name="lastName" 
                                                       value="${sessionScope.LOGIN_GMAIL != null ? sessionScope.lastName : sessionScope.LOGIN_USER.lastName}">
                                            </div>
                                            <div class="mb-3">
                                                <label for="address" class="form-label">Address:</label>
                                                <input type="text" class="form-control" id="address" name="address" value="">
                                            </div>
                                            <div class="text-center">
                                                <button type="submit" class="btn btn-primary" name="action">Update</button>
<!--                                                <span id="updateMessage" class="text-success" style="margin-left: 10px; display: none;">Update successfully</span>-->
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </header>
        
        <script>
            function previewAvatar() {
                const file = document.getElementById('avatar').files[0];
                const preview = document.getElementById('avatarPreview');
                const reader = new FileReader();

                reader.onloadend = function () {
                    preview.src = reader.result;
                };

                if (file) {
                    reader.readAsDataURL(file);
                } else {
                    preview.src = "";
                }
            }
        </script>

    </body>
</html>

