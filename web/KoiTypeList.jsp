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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/stylstyle.css">
    </head>
    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>
        <h1 class="welcome-text">All Koi Types</h1>
        
        <!-- Search form -->
        <form action="listkoitype" method="GET" class="search-koitype-form" style="text-align: center; align-items: center; padding-bottom: 50px;">
            <input type="text" name="txtNameKoiType" placeholder="Search Koi Type..." style="border-radius: 10px;"/>
            <button type="submit"><i class="fa-solid fa-magnifying-glass">Search</i></button>
        </form>
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
