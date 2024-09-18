<%-- 
    Document   : signUp
    Created on : Sep 12, 2024, 11:35:29 PM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <!--=============== FONT AWESOME ===============-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <!--=============== MAIN CSS ===============-->
        <link rel="stylesheet" href="css/signUp.css">
        <!--=============== SWIPER CSS ===============-->
        <link rel="stylesheet" href="css/swiper-bundle.min.css">    
        <!-- MAIN JS-->
        <script src="js/signUp.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="login-form">
                <h2>Sign Up</h2>
                <form action="signup" method="post" id="form">
                    <div class="name-section">
                        <div class="first-name-wrapper">
                            <label for="fullName">First Name</label>
                            <input type="text" id="firstName" name="firstName" value="${param.firstName}" placeholder="Enter first name" required>
                        </div>
                        <div class="last-name-wrapper">
                            <label for="fullName">Last Name</label>
                            <input type="text" id="lastName" name="lastName" value="${param.lastName}" placeholder="Enter last name" required>
                        </div>
                    </div>                    
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" value="${param.email}" placeholder="Enter email" required>

                    <div class="password-section position-relative">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" placeholder="Enter password" required>
                        <span class="toggle-password">
                            <i id="togglePasswordIcon" class="fa-regular fa-eye"></i>
                        </span>
                    </div>

                    <div class="confirm-password-section position-relative">
                        <label for="confirmPassword">Confirm Password</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm password" required>
                        <span class="toggle-confirm-password">
                            <i id="toggleConfirmPasswordIcon" class="fa-regular fa-eye"></i>
                        </span>
                    </div>       
                    
                    <div class="g-recaptcha" data-sitekey="6LeaJEgqAAAAAFXJKJaU9KqiJL1lXQQjY2HmEr1z"></div>
                        <div id="error">
                            
                        </div>
                    
                    <div class="terms">
                        <input type="checkbox" id="terms" name="terms" required>
                        <label for="terms">I agree with the <a onclick="openTermsModal()">Terms of Service & Privacy Policy</a></label>
                    </div>
                    
                    <button type="submit" class="btn" name="action" value="Sign up">Sign Up</button>
                </form>

                <!-- Already have an account? -->
                <div class="already-account">
                    <span>Already have an account? <a href="login.jsp" class="sign-in-link">Sign in</a></span>
                </div>
            </div>

            <!-- Image carousel -->
            <div class="image-container"> 
                <div class="swiper carousel"> 
                    <div class="swiper-wrapper"> 
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

        <!-- Terms Modal HTML -->
        <div id="terms-modal" class="modal">
            <div class="modal-content">
                <!-- Close Button (X) -->
                <span class="close-button" onclick="closeTermsModal()">&times;</span>
                <h2>Terms of Service & Privacy Policy</h2>
                <p>Here you can include the content of your Terms of Service & Privacy Policy.</p>
            </div>
        </div> 

        <!-- Error Modal HTML -->
        <div id="error-modal" class="modal">
            <div class="modal-content">
                <span class="close-button" onclick="closeModal()">&times;</span>
                <p id="error-message"></p>
            </div>
        </div>

        <c:set var="success" value="${requestScope.CREATE_SUCCESS}" />
        <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
        <c:choose>
            <c:when test="${not empty errors.emailIsExisted}">
                <script>
                    showModal('${errors.emailIsExisted}');
                </script>
            </c:when>
            <c:when test="${not empty errors.passwordLengthErr}">
                <script>
                    showModal('${errors.passwordLengthErr}');
                </script>
            </c:when>
            <c:when test="${not empty errors.firstNameLengthErr}">
                <script>
                    showModal('${errors.firstNameLengthErr}');
                </script>
            </c:when>
            <c:when test="${not empty errors.lastNameLengthErr}">
                <script>
                    showModal('${errors.lastNameLengthErr}');
                </script>
            </c:when>
            <c:when test="${not empty errors.confirmNotMacthed}">
                <script>
                    showModal('${errors.confirmNotMacthed}');
                </script>
            </c:when>
            <c:when test="${not empty success}">
                <script>
                    showModal('${success}');
                </script>
            </c:when>
        </c:choose>

        <!-- SWIPER JS -->
        <script src="js/swiper-bundle.min.js"></script>   
        <!-- reCAPTCHA -->
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
                    <script>
                        window.onload = function (){
                            let isValid = false;
                            const form = document.getElementById("form");
                            const error = document.getElementById("error");
                            
                            form.addEventListener("submit", function (event){
                                event.preventDefault();
                                const response = grecaptcha.getResponse();
                                if(response){
                                    form.submit();
                                } else{
                                    error.innerHTML = "Please check";
                                }
                            }
                        });
                    </script>
    </body>
</html>
