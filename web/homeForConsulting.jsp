<%-- 
    Document   : homeForConsulting
    Created on : Oct 3, 2024, 8:23:34 AM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link href="css/toast.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="headerForConsulting.jsp" flush="true"/>

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

        <div id="toastBox"></div>
        <script src="js/homeForDelivery.js"></script>
        <script src="js/showToast.js"></script>
    </body>
</html>
