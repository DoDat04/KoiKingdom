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
        <title>Sign Up - Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <!--=============== FONT AWESOME ===============-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <!--=============== MAIN CSS ===============-->
        <link rel="stylesheet" href="css/signUp.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/toast.css" rel="stylesheet">
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
                    <input type="hidden" name="purpose" value="register"> 
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
                        <label for="password">Password
                            <span class="info-icon" title="Password must include special characters, letters, and numbers, with a total length of more than 7 characters." style="color: red">*</span>
                        </label>
                        <input type="password" id="password" name="password" placeholder="Enter password" required>

                        <!-- Show/Hide Password Icon -->
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
                        <label for="terms" class="tos-agree">I agree with the <a onclick="openTermsModal()" class="tos-link">Terms of Service & Privacy Policy</a></label>
                    </div>
                    <button type="submit" class="btn" name="action" value="Sign up">Sign Up</button>
                </form>

                <!-- Already have an account? -->
                <div class="already-account">
                    <span>Already have an account? <a href="login" class="sign-in-link">Sign in</a></span>
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
                <p class="tos-introduce">Welcome to Koi Kingdom! By signing up and creating an account on our website, you agree to the following terms, conditions, and privacy policy. You acknowledge that you have read and agree to our Terms of Service & Privacy Policy. Thank you for your visit.</p>

                <h3>1. Introduction</h3>
                <p>We value your privacy and are committed to protecting your personal information. This Privacy Policy outlines how we collect, use, and protect your data.</p>

                <h3>2. Information We Collect</h3>
                <p>We collect information that you provide to us directly, such as when you create an account, make a purchase, or contact us. This may include your name, email address, phone number, shipping address, and payment information. We also collect information automatically as you navigate our site, including IP address, browser type, and usage data.</p>

                <h3>3. How We Use Your Information</h3>
                <p>We use your information to provide and improve our services, process transactions, communicate with you, and for marketing purposes. We may also use your information to comply with legal obligations and protect our rights.</p>

                <h3>4. Sharing Your Information</h3>
                <p>We do not sell your personal information. We may share your information with third parties to facilitate our services, such as payment processors, shipping companies, and marketing partners. These third parties are obligated to protect your information and use it only for the purposes we specify.</p>

                <h3>5. Data Security</h3>
                <p>We implement various security measures to protect your personal information. However, no method of transmission over the internet or electronic storage is 100% secure. We strive to use commercially acceptable means to protect your data but cannot guarantee absolute security.</p>

                <h3>6. Your Choices</h3>
                <p>You have the right to access, update, and delete your personal information. You can manage your account settings or contact us to make changes. You can also opt out of receiving promotional emails by following the unsubscribe instructions in the emails.</p>

                <h3>7. Changes to This Policy</h3>
                <p>We may update this Privacy Policy periodically. Any changes will be posted on our website, and your continued use of our services constitutes acceptance of the updated policy.</p>

                <h3>8. Contact Us</h3>
                <p>If you have any questions or concerns about this Privacy Policy or our data practices, please contact us at <a href="home">Koi Kingdom</a>.</p>
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
                    window.onload = function () {
                        showToast('${errors.emailIsExisted}', 'error');
                    };
                </script>
            </c:when>
            <c:when test="${not empty errors.passwordLengthErr}">
                <script>
                    window.onload = function () {
                        showToast('${errors.passwordLengthErr}', 'error');
                    };
                </script>
            </c:when>
            <c:when test="${not empty errors.firstNameLengthErr}">
                <script>
                    window.onload = function () {
                        showToast('${errors.firstNameLengthErr}', 'error');
                    };
                </script>
            </c:when>
            <c:when test="${not empty errors.lastNameLengthErr}">
                <script>
                    window.onload = function () {
                        showToast('${errors.lastNameLengthErr}', 'error');
                    };
                </script>
            </c:when>
            <c:when test="${not empty errors.firstNameInvalidErr}">
                <script>
                    window.onload = function () {
                        showToast('${errors.firstNameInvalidErr}', 'error');
                    };
                </script>
            </c:when>
            <c:when test="${not empty errors.lastNameInvalidErr}">
                <script>
                    window.onload = function () {
                        showToast('${errors.lastNameInvalidErr}', 'error');
                    };
                </script>
            </c:when>
            <c:when test="${not empty errors.confirmNotMacthed}">
                <script>
                    window.onload = function () {
                        showToast('${errors.confirmNotMacthed}', 'error');
                    };
                </script>
            </c:when>
            <c:when test="${not empty success}">
                <script>
                    window.onload = function () {
                        showToast('${success}', 'success');
                    };
                </script>
            </c:when>
        </c:choose>


        <div id="toastBox"></div>
        <!-- SWIPER JS -->
        <script src="js/showToast.js"></script>

        <!-- SWIPER JS -->
        <script src="js/swiper-bundle.min.js"></script>   
        <!-- reCAPTCHA -->
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script>
                    document.getElementById('form').addEventListener('submit', function (event) {
                        var recaptchaResponse = grecaptcha.getResponse();
                        if (recaptchaResponse.length === 0) {
                            event.preventDefault(); // Ngăn gửi biểu mẫu
                            showModal('Please complete the reCAPTCHA.');
                        }
                    });
        </script>
    </body>
</html>
