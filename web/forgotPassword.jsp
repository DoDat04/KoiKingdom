<%-- 
    Document   : forgotPassword
    Created on : Sep 14, 2024, 5:03:25 PM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password - Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <!--=============== MAIN CSS ===============-->
        <link rel="stylesheet" href="css/login.css">  
        <!--=============== SWIPER CSS ===============-->
        <link rel="stylesheet" href="css/swiper-bundle.min.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

        <!--=============== MAIN JS ===============-->
        <script src="js/forgotPassword.js"></script>
        <link href="css/toast.css" rel="stylesheet">

    </head>
    <body>
        <div class="container">
            <div class="login-form">
                <h2>Forgot Password</h2>
                <p class="fp_sub_title">Enter your account's email so that we can send a verify email to confirm for reset password</p>
                <form action="forgot-password" method="post">
                    <label for="email" class="email">Email</label>
                    <input type="email" id="email" name="email" value="${param.email}" placeholder="Enter email" required>
                    <button type="submit" class="btn" name="action" style="width: 104.5%">Send OTP</button>
                </form>
                <a class="back_to_login" href="login">&lt; Back to login</a>
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

        <!-- Modal HTML -->
        <div id="error-modal" class="modal">
            <div class="modal-content">
                <span class="close-button" onclick="closeModal()">&times;</span>
                <p id="error-message">Some error message</p>
            </div>
        </div>

        <c:set var="error" value="${requestScope.errorMessage}"/>
        <c:if test="${not empty error and error != null}">
            <script>
                window.onload = function () {
                    showToast('${error}', 'error');
                };
            </script>
        </c:if>
      <div id="toastBox"></div>
        <script src="js/showToast.js"></script>

        <!-- SWIPER JS -->
        <script src="js/swiper-bundle.min.js"></script>
    </body>
</html>
