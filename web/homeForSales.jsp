<%-- 
    Document   : homeForSales
    Created on : Sep 28, 2024, 8:55:40 PM
    Author     : ADMIN LAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sales Page</title>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="headerForSales.jsp" flush="true"/>
        <h1>Hello World!</h1>
        <c:if test="${not empty sessionScope.updateSuccess}">
            <script>
                window.onload = function () {
                    showToast('${sessionScope.updateSuccess}', 'success');
                };
            </script>
            <c:set var="updateSuccess" value="${null}" scope="session"/>
        </c:if>

        <c:if test="${not empty sessionScope.updateError}">
            <script>
                window.onload = function () {
                    showToast('${sessionScope.updateError}', 'error');
                };
            </script>
            <c:set var="updateError" value="${null}" scope="session"/>
        </c:if>
        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>
