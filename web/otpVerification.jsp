<%-- 
    Document   : otpVerification
    Created on : Sep 14, 2024, 6:17:43 PM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify OTP - Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link rel="stylesheet" href="css/login.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="js/otpVerification.js"></script>
        <!--=============== SWIPER CSS ===============-->
        <link rel="stylesheet" href="css/swiper-bundle.min.css">
        <link href="css/toast.css" rel="stylesheet">
        <link href="css/toast.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="login-form">
                <h2>Verify OTP</h2>
                <form action="reset-password" method="post">
                    <label for="otp" class="otp">OTP</label>
                    <input type="text" id="otp" name="otp" placeholder="Enter OTP" required>

                    <button type="submit" class="btn" name="action">Verify OTP</button>
                </form>
                <div id="countdown">Time remaining: <span id="time">300</span> seconds</div>
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
                <p id="error-message">Some error message</p>
            </div>
        </div>

        <c:if test="${not empty sessionScope.errorMessage}">
            <script>
                window.onload = function () {
                    showToast('${sessionScope.errorMessage}', 'success');
                };
            </script>
            <c:remove var="errorMessage" scope="session"/>
        </c:if>

        <div id="toastBox" class="toast-container position-fixed top-0 end-0 p-3"></div>
        <script src="js/showToast.js"></script>
        <script src="js/otpTimer.js"></script> 
        <!-- SWIPER JS -->
        <script src="js/swiper-bundle.min.js"></script> 
    </body>
</html>
