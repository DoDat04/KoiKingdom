<%-- 
    Document   : listfarm
    Created on : Sep 24, 2024, 3:03:22 PM
    Author     : ADMIN LAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Farm List</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>
        <div class="banner-container">
            <div class="banner-text">
                <h2>Category</h2>
                <h1>List Farms</h1>
            </div>
            <div class="banner-images">
                <img src="img/TourImage/farm.jpg" alt="Tour 1">
                <img src="img/TourImage/farm.jpg" alt="Tour 2">
                <img src="img/TourImage/farm.jpg" alt="Tour 3">
            </div>
        </div>
        <h1 class="welcome-text">Farms</h1>

        <!-- Search form -->
        <form action="listfarm" method="GET" class="search-koitype-form" style="text-align: center; align-items: center; padding-bottom: 50px;">
            <input type="text" name="txtFarm" placeholder="Search farm's name..." style="border-radius: 10px;"/>
            <button type="submit"><i class="fa-solid fa-magnifying-glass">Search</i></button>
        </form>
        <div class="container">
            <div class="row">
                <c:forEach var="farm" items="${sessionScope.LIST_FARM}">
                    <div class="col-md-4 mb-4">
                        <div class="card" style="width: 15rem;">
                            <img src="${farm.image}" class="card-img-top koi-image" alt="${farm.farmName}">
                            <div class="card-body">
                                <h5 class="card-title">${farm.farmName}</h5>
                                <p class="card-text">${farm.location}</p>
                                <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#staticBackdrop${farm.farmID}">
                                    Details
                                </button>
                            </div>
                        </div>

                        <!-- Modal -->
                        <div class="modal fade" id="staticBackdrop${farm.farmID}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="staticBackdropLabel">${farm.farmName}</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <img src="${farm.image}" class="card-img-top" alt="${farm.farmName}" style="height: 100%; width: 100%;"/>
                                            </div>
                                            <div class="col-md-8">
                                                <p>${farm.description}</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>
                <c:if test="${not empty requestScope.ERROR_NULL}">
                    <div class="alert alert-danger" role="alert">
                        ${requestScope.ERROR_NULL}
                    </div>
                </c:if>

            </div>
        </div>
        
        <!-- Back to Top Button -->
        <button id="backToTop" class="btn btn-primary" style="display: none;">
            <i class="fas fa-angle-up"></i>
        </button>

        <script src="js/backToTop.js"></script>
        
        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>
