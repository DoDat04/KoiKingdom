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
        <!-- Script get api province -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    </head>
    <body>
        <header class="text-black py-4" style="background-color: #fdf4f0;">
            <div class="header-content">
                <div class="d-flex justify-content-between align-items-center">
                    <!-- Logo -->
                    <div class="logo">
                        <a href="home">
                            <img src="img/logo.png" style="width: 185px; height: 150px;">
                        </a>
                    </div>

                    <!-- Center Menu (Home, Booking, Contact, Information) -->
                    <div class="menu-center">
                        <ul class="nav justify-content-center">
                            <li class="nav-item">
                                <a class="nav-link text-black" href="home">Home</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle text-black" id="bookingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Booking</a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="bookingDropdown">
                                    <li><a class="dropdown-item" href="tour-list">Tour</a></li>
                                    <li><a class="dropdown-item" href="home?action=customTour">Custom Tour</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-black" href="service">Services</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle text-black" id="bookingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Information</a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="bookingDropdown">
                                    <li><a class="dropdown-item" href="listkoitype">Koi Fish</a></li>
                                    <li><a class="dropdown-item" href="listfarm">Farms</a></li>
                                </ul>
                            </li>                           
                            <li class="nav-item">
                                <a class="nav-link text-black" href="home?action=contact">Contact</a>
                            </li>
                        </ul>
                    </div>

                    <!-- Right Menu (Add to Cart, Account) -->
                    <div class="menu-right d-flex align-items-center">
                        <a href="cart" class="text-black me-3 icon-size position-relative">
                            <i class="fas fa-shopping-cart"></i>
                            <!-- Cart Item Count -->
                            <span class="cart-badge">
                                <c:choose>
                                    <c:when test="${sessionScope.cartItemCount != null or sessionScope.cartItemCount > 0}">
                                        ${sessionScope.cartItemCount}
                                    </c:when>
                                    <c:otherwise>
                                        0
                                    </c:otherwise>
                                </c:choose>
                            </span>
                        </a>
                        <c:choose>
                            <c:when test="${sessionScope.LOGIN_USER == null and sessionScope.LOGIN_GMAIL == null}">  
                                <a href="#" class="text-black me-3 icon-size" onclick="alert('You need to login to see favorite tours!')">
                                    <i class="fa-solid fa-heart"></i>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="favorite" class="text-black me-3 icon-size">
                                    <i class="fa-solid fa-heart"></i>
                                </a>
                            </c:otherwise>
                        </c:choose>                      
                        <!-- User Info with Name and Icon -->
                        <div class="user-info d-flex align-items-center">
                            <!-- Display Name -->
                            <span class="user-name text-black">
                                <c:choose>                                   
                                    <c:when test="${sessionScope.LOGIN_USER != null}">  
                                        <span class="separator mx-2">|</span>
                                        ${sessionScope.LOGIN_USER.firstName} ${sessionScope.LOGIN_USER.lastName}                                       
                                    </c:when>
                                    <c:when test="${sessionScope.LOGIN_GMAIL != null}">
                                        <span class="separator mx-2">|</span>
                                        ${sessionScope.lastName} ${sessionScope.firstName}                                        
                                    </c:when>
                                </c:choose>
                            </span>                          

                            <!-- User Icon -->
                            <a href="#" class="text-black icon-size nav-link dropdown-toggle" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fas fa-user"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">      
                                <c:choose>
                                    <c:when test="${sessionScope.LOGIN_USER == null and sessionScope.LOGIN_GMAIL == null}">
                                        <li><a class="dropdown-item" href="#"><i class="adm_icon fas fa-question-circle"></i> FAQS</a></li>
                                        <li><a class="dropdown-item" href="login"><i class="adm_icon fas fa-sign-in-alt"></i> Sign in</a></li>
                                        </c:when>
                                        <c:when test="${sessionScope.LOGIN_USER != null}">
                                        <li><a class="dropdown-item" href="#"><i class="adm_icon fas fa-question-circle"></i> FAQS</a></li>
                                        <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#changePasswordModal"><i class="fa-solid fa-key"></i> Change Password</a></li>
                                        <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#updateProfileModal"><i class="fa-solid fa-user-pen"></i> Update Profile</a></li>
                                        <li><a class="dropdown-item" href="#"><i class="fa-solid fa-bell"></i> Notification</a></li>
                                        <li><a class="dropdown-item" href="MyOrder?customerID=${sessionScope.LOGIN_USER.customerID}"><i class="fa fa-history"></i> My Order KOI</a></li>
                                        <li><a class="dropdown-item" href="get-booking?customerID=${sessionScope.LOGIN_USER.customerID}"><i class="fa fa-history"></i> My Order <Booking/a></li>
                                        <li><a class="dropdown-item" href="home?action=Logout"><i class="fa-solid fa-right-from-bracket"></i> Sign out</a></li>
                                        </c:when>
                                        <c:otherwise>                                       
                                        <li><a class="dropdown-item" href="#"><i class="adm_icon fas fa-question-circle"></i> FAQS</a></li>
                                        <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#updateProfileModal"><i class="fa-solid fa-user-pen"></i> Update Profile</a></li>
                                        <li><a class="dropdown-item" href="#"><i class="fa-solid fa-bell"></i> Notification</a></li>
                                        <li>
                                            <a class="dropdown-item" href="MyOrder?customerID=${sessionScope.custID}">
                                                <i class="fa fa-history"></i> My Order KOI
                                            </a>
                                        </li>
                                        <li>
                                            <a class="dropdown-item" href="get-booking?customerID=${sessionScope.custID}">
                                                <i class="fa fa-history"></i> My Order Booking
                                            </a>
                                        </li>

                                        <li><a class="dropdown-item" href="home?action=Logout"><i class="fa-solid fa-right-from-bracket"></i> Sign out</a></li>
                                        </c:otherwise>
                                    </c:choose>
                            </ul>
                        </div>                       

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
                                                    <img id="avatarPreview" 
                                                         src="${sessionScope.AVATAR}" 
                                                         alt="">
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
                                                <label for="address" class="form-label">Default Address:</label>
                                                <div class="d-flex align-items-center">
                                                    <input type="text" class="form-control" id="address" name="address" placeholder="Your address will appear here"
                                                           value="${sessionScope.LOGIN_GMAIL != null ? sessionScope.address : sessionScope.LOGIN_USER.address}" readonly="">
                                                    <button class="btn btn-outline-secondary ms-2" type="button" id="editAddressBtn">
                                                        <i class="fa-solid fa-pen-to-square"></i>
                                                    </button>
                                                </div>
                                            </div>

                                            <div class="mb-3" id="homeAddressDiv" style="display: none;">
                                                <label for="homeAddress" class="form-label">Home Address:</label>
                                                <input type="text" class="form-control" id="homeAddress" name="homeAddress" placeholder="Số nhà, tên đường">
                                            </div>

                                            <div class="mb-3 select-group" id="addressSelectDiv" style="display: none;">
                                                <div class="select-container">
                                                    <select id="city" name="city">
                                                        <option value="">Select province</option>
                                                    </select>
                                                </div>
                                                <div class="select-container">
                                                    <select id="district" name="district">
                                                        <option value="">Select district</option>
                                                    </select>
                                                </div>
                                                <div class="select-container">
                                                    <select id="ward" name="ward">
                                                        <option value="">Select ward</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="text-center">
                                                <button type="submit" class="btn btn-primary" name="action">Update</button>
                                            </div>
                                            <script src="js/address.js"></script>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Modal for Changing Password -->
                        <div class="modal fade" id="changePasswordModal" tabindex="-1" aria-labelledby="changePasswordModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="changePasswordModalLabel">Change Password</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="ChangePasswordController" method="post">
                                            <!-- Old Password Field -->
                                            <div class="mb-3">
                                                <label for="oldPassword" class="form-label">Old Password:</label>
                                                <input type="password" class="form-control" id="oldPassword" name="oldPassword" placeholder="Enter your old password" required>
                                            </div>

                                            <!-- New Password Field -->
                                            <div class="mb-3">
                                                <label for="newPassword" class="form-label">New Password:</label>
                                                <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Enter your new password" required>
                                            </div>

                                            <!-- Confirm New Password Field (Optional) -->
                                            <div class="mb-3">
                                                <label for="confirmPassword" class="form-label">Confirm New Password:</label>
                                                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm your new password" required>
                                            </div>

                                            <!-- Submit Button -->
                                            <div class="text-center">
                                                <button type="submit" class="btn btn-primary">Update Password</button>
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
        <script src="js/load.js"></script>
    </body>
</html>

