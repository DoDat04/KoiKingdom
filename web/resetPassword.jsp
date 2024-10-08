<%-- 
    Document   : resetPassword
    Created on : Sep 15, 2024, 3:10:47 PM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password - Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <link rel="stylesheet" href="css/resetPassword.css">
        <link href="css/toast.css" rel="stylesheet">
        <script src="js/resetPassword.js"></script> 
        <!--=============== SWIPER CSS ===============-->
        <link rel="stylesheet" href="css/swiper-bundle.min.css">
    </head>
    <body>
        <div class="container">
            <div class="login-form">
                <h2>Reset Password</h2>
                <form action="ResetPasswordController" method="post">
                    <input type="hidden" name="emailReset" value="${email}">

                    <div class="password-container">
                        <label for="newPassword">New Password</label>
                        <input type="password" id="newPassword" name="newPassword" placeholder="Enter new password" required>
                        <span class="toggle-password">
                            <i id="toggleNewPasswordIcon" class="fa-regular fa-eye"></i>
                        </span>
                    </div>

                    <div class="password-container">
                        <label for="confirmPassword">Confirm Password</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm new password" required>
                        <span class="toggle-password">
                            <i id="toggleConfirmPasswordIcon" class="fa-regular fa-eye"></i>
                        </span>
                    </div>

                    <button type="submit" class="btn" name="action" value="Reset Password">Reset Password</button>

                    <div class="login-link">
                        <a href="login">&lt; Back to login</a>
                    </div>
                </form>
            </div>
            <!-- Image carousel -->
            <div class="image-container"> 
                <div class="swiper carousel"> <!-- class 'swiper' cần có -->
                    <div class="swiper-wrapper"> <!-- Bọc các slide bên trong swiper-wrapper -->
                        <div class="swiper-slide"><img src="img/1.jpg"></div>
                        <div class="swiper-slide"><img src="img/2.jpg"></div>
                        <div class="swiper-slide"><img src="img/3.jpg"></div>
                    </div>
                    <!-- Nút điều hướng -->
                    <a class="swiper-button-prev"></a>
                    <a class="swiper-button-next"></a>

                    <!-- Pagination -->
                    <div class="swiper-pagination"></div>
                </div>
            </div>
        </div>

        <!-- Modal for error message -->
        <div id="error-modal" class="modal">
            <div class="modal-content">
                <span class="close-button" onclick="closeModal()">&times;</span>
                <p id="error-message">${errorMessage}</p>
            </div>
        </div>

        <c:if test="${not empty errorMessage}">
            <script>
                window.onload = function () {
                    showToast('${errorMessage}', 'error');
                };

            </script>
        </c:if>

        <c:if test="${not empty success}">
            <script>
                window.onload = function () {
                    showToast('${success}', 'success');
                };
            </script>
        </c:if>

       <div id="toastBox"></div>
        <!-- SWIPER JS -->
        <script src="js/showToast.js"></script>
        <!-- SWIPER JS -->
        <script src="js/swiper-bundle.min.js"></script>
    </body>
</html>
