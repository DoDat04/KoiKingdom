<%-- 
    Document   : managerDashboard
    Created on : Sep 24, 2024, 4:18:35 PM
    Author     : Nguyen Huu Khoan
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/homeForDelivery.css" rel="stylesheet">
        <title>Manager Dashboard</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">

        <jsp:include page="headerForManager.jsp" flush="true"/>   
    <div class="main" style="margin-top: -216px; margin-left: 223px; margin-right: 30px;">       

        <c:choose>                       
            <c:when test="${sessionScope.LOGIN_MANAGER != null}"> 
                <div class="container">
                    <h1>
                        Welcome
                        @<span>${sessionScope.LOGIN_MANAGER.firstName} ${sessionScope.LOGIN_MANAGER.lastName}</span> 
                        to Manager Dashboard.
                    </h1>
                </div>
                <style>
                    .container{
                        text-align: center;
                    }
                </style>

            </c:when>
        </c:choose>
    </div>
</body>

</html>

