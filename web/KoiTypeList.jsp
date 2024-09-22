<%-- 
    Document   : KoiTypeList
    Created on : Sep 21, 2024, 8:43:35 PM
    Author     : ADMIN LAM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KoiType List</title>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/header.css">
    </head>
    <body>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>
        <h1 class="welcome-text">All Koi Types</h1>
        
        <div class="container">
            <div class="row">
                <c:forEach var="koiType" items="${requestScope.LIST_KOITYPE}">
                    <div class="col-md-4">
                        <div class="koi-type-card">
                            <h2 class="koi-text"><strong>${koiType.typeName}</strong></h2>
                            <img src="${koiType.koiImageURL}" alt="${koiType.typeName}" class="koi-image"/>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>

