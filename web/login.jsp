<%-- 
    Document   : login
    Created on : Sep 10, 2024, 2:00:21 PM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login Form</title>
        <!--=============== FONT AWESOME ===============-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <!--=============== MAIN CSS ===============-->
        <link rel="stylesheet" href="css/login.css">  
        <!--=============== SWIPER CSS ===============-->
        <link rel="stylesheet" href="css/swiper-bundle.min.css">
        <!-- MAIN JS-->
        <script src="js/login.js"></script>
    </head>
    <body>       
        <div class="container">
            <div class="login-form">
                <h2>Sign in</h2>
                <form id="login-form" action="login" method="post" onsubmit="return validateForm()">
                    <label for="email" class="email">Email</label>
                    <input type="email" id="email" name="email" value="${param.email}" placeholder="Enter email">

                    <label for="password" class="password">Password</label>
                    <div class="password-container">
                        <input type="password" id="password" name="password" placeholder="Enter password">
                        <span class="toggle-password">
                            <i id="togglePasswordIcon" class="fa-regular fa-eye"></i>
                        </span>
                    </div>

                    <div class="remember_forgot">
                        <input type="checkbox" id="remember" name="remember">
                        <label for="remember" class="remember">Remember me</label>
                        <a href="forgot-password" class="forgot-password">Forgot password?</a>
                    </div>

                    <button type="submit" class="btn" name="action">Sign in</button>
                </form>
                <div class="sign-up-section">
                    <p>Don't have an account? <a href="signup" class="sign-up-link">Sign up</a></p>
                </div>
                <div class="or-divider">
                    <hr>
                    <span>or</span>
                    <hr>
                </div>
                <div class="login-section">
                    <div>
                        <a href="google-auth" class="google-login">
                            <img src="https://www.gstatic.com/firebasejs/ui/2.0.0/images/auth/google.svg" alt="Google Logo" width="20" height="20">
                            <span style="margin-left: 0.5rem;" class="sign-in-with-google">Sign in with Google</span>
                        </a>
                    </div>                  
                    <!-- Guest Button -->
                    <div class="guest-section">
                        <a href="home" class="guest-btn">Continue as Guest</a>
                    </div>
                </div>             
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

            <!-- Modal HTML -->
            <div id="error-modal" class="modal">
                <div class="modal-content">
                    <span class="close-button" onclick="closeModal()">&times;</span>
                    <p id="error-message"></p>
                </div>
            </div>

            <c:set var="error" value="${requestScope.ERROR}"/>
            <c:if test="${not empty error and error != null}">
                <script>
                    showModal('${error}');
                </script>
            </c:if> 

            <!-- SWIPER JS -->
            <script src="js/swiper-bundle.min.js"></script>           
    </body>
</html>
