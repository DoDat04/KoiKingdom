<%-- 
    Document   : addEmployee
    Created on : Oct 8, 2024, 9:44:49 PM
    Author     : Nguyen Huu Khoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Tour</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any"> 
        <link rel="stylesheet" href="css/createTour.css"> 
        <link href="css/toast.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="headerForManager.jsp" flush="true"/> 
        <div style="    margin-top: 25vh;
             margin-left: 17%;
             margin-right: 6%;" class="main-content">     
            <div class="container">
                <h1>Add employee</h1>
                <c:if test="${not empty CREATE_SUCCESS}">
                    <script>
                        window.onload = function () {
                            showToast('${CREATE_SUCCESS}', 'success');
                        };
                    </script>
                </c:if>

                <div id="toastBox"></div>
                <script src="js/showToast.js"></script>
                <form action="addemployee" method="post" >
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="Email" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="Password" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="cfpassword">Confirm password</label>
                        <input type="password" id="cfpassword" name="ConfirmPassword" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="lastname">Last Name</label>
                        <input type="text" id="lastname" name="LastName" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="firstname">First Name</label>
                        <input type="text" id="firstname" name="FirstName" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="role">Role</label>
                        <select class="form-select" aria-label="Default select example" id="role" name="Role" required>
                            <option value="" selected>Choose role</option>
                            <option value="Sales">Sales</option>
                            <option value="Consulting">Consulting</option>
                            <option value="Delivery">Delivery</option>
                        </select>
                    </div>
                    <button type="submit" class="btn-custom-tour">Add employee</button>
                </form>
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
        </div>
    </body>
</html>

