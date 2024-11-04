<%-- 
    Document   : KoiListOrder
    Created on : Oct 28, 2024, 3:40:02 PM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Koi List - Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon">

        <!-- Bootstrap & Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@700&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">

        <!-- Custom CSS & AOS -->
        <link rel="stylesheet" href="css/koiList.css">
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">

        <!-- Scripts -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
    </head>

    <body>
        <!-- Loader -->
        <div class="colorlib-loader"></div>

        <!-- Header -->
        <jsp:include page="headerForCustomer.jsp" flush="true"/>

        <!-- Banner -->
        <div class="banner-container">
            <div class="banner-text text-center">
                <h2>Category</h2>
                <h1>Book Koi</h1>
            </div>
            <div class="banner-images">
                <img src="img/TourImage/koitype.jpg" alt="Tour 1">
                <img src="img/TourImage/koitype.jpg" alt="Tour 2">
                <img src="img/TourImage/koitype.jpg" alt="Tour 3">
            </div>
        </div>

        <!-- Main Content -->
        <div class="container mt-5">
            <!-- Filter Form -->
            <form method="GET" action="koi-list-order" class="mb-5">
                <div class="row mb-4">
                    <div class="col-md-12 mb-3">
                        <c:if test="${not empty param.farmID or not empty param.priceOrder}">
                            <button type="button" class="btn btn-danger" onclick="removeFilters()">Remove Filter</button>
                        </c:if>
                    </div>
                    <div class="col-md-2">
                        <strong><label for="farmFilter" class="form-label">Farm</label></strong>
                        <select id="farmFilter" name="farmID" class="form-select" onchange="this.form.submit()">
                            <option value="" ${empty param.farmID ? 'selected' : ''}>All Farms</option>
                            <c:forEach var="farm" items="${farmList}">
                                <option value="${farm.farmID}" ${param.farmID == farm.farmID ? 'selected' : ''}>${farm.farmName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-md-2">
                        <strong><label for="priceFilter" class="form-label">Price</label></strong>
                        <select id="priceFilter" name="priceOrder" class="form-select" onchange="this.form.submit()">
                            <option value="" ${empty param.priceOrder ? 'selected' : ''}>All Prices</option>
                            <option value="asc" ${param.priceOrder == 'asc' ? 'selected' : ''}>Price (Low to High)</option>
                            <option value="desc" ${param.priceOrder == 'desc' ? 'selected' : ''}>Price (High to Low)</option>
                        </select>
                    </div>
                </div>
            </form>

            <!-- Koi List Section -->
            <h2 class="text-center">Koi List</h2>
            <div class="row">
                <c:if test="${empty KOILIST}">
                    <div class="col-12 text-center">
                        <div class="alert alert-warning" role="alert">
                            <strong>No koi found!</strong><br>We’re sorry, but there are no tours that match your search criteria. 
                            Try changing the filter or check again later.
                        </div>
                    </div>
                </c:if>

                <!-- Koi Cards -->
                <c:forEach var="tour" items="${KOILIST}">
                    <div class="col-md-4 mb-4">
                        <div class="card h-100" data-aos="fade-up" data-aos-duration="3000">
                            <img src="${tour.image}" class="card-img-top" alt="${tour.koiName}">
                            <div class="card-body">
                                <p class="card-text"><strong>Koi Name:</strong> ${tour.koiName}</p>
                                <p class="card-text"><strong>Koi Type:</strong> ${tour.koiTypeName}</p>
                                <p class="card-text"><strong>Age:</strong> ${tour.age}</p>
                                <p class="card-text"><strong>Length:</strong> ${tour.length}</p>
                                <p class="card-text"><strong>Weight:</strong> ${tour.weight}</p>
                            </div>
                            <div class="quantity-container mb-3">
                                <span class="quantity-label"><strong>Quantity:</strong></span>
                                <button type="button" class="btn btn-outline-secondary" onclick="updateQuantity('${tour.koiID}', -1)">-</button>
                                <input type="number" id="quantity-${tour.koiID}" value="0" min="0" style="width: 50px; text-align: center;" readonly>
                                <button type="button" class="btn btn-outline-secondary" onclick="updateQuantity('${tour.koiID}', 1)">+</button>
                            </div>
                            <div class="card-footer text-end">
                                <strong class="fs-4 text-danger">${tour.price} $</strong>
                            </div>
                            <div class="buttonTour d-flex justify-content-between align-items-center mt-4">
                                <!-- Form to add to cart -->
                                <form action="AddToCartController" method="post">
                                    <input type="hidden" name="koiID" value="${tour.koiID}">
                                    <input type="hidden" name="quantity" id="quantity-input-add-${tour.koiID}" value="0">
                                    <button type="submit" class="btn btn-primary btn-block" style="width: 200px;">
                                        <i class="fas fa-cart-plus"></i> Add to Cart
                                    </button>
                                </form>

                                <!-- Form to book now -->
                                <form action="BookNowController" method="post">  
                                    <input type="hidden" name="koiID" value="${tour.koiID}">
                                    <input type="hidden" name="quantity" id="quantity-input-book-${tour.koiID}" value="0">
                                    <button type="submit" class="btn btn-danger btn-block" style="width: 200px;">
                                        <i class="fas fa-credit-card"></i> Book Now
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- Back to Top Button -->
        <button id="backToTop" class="btn btn-primary" style="display: none;">
            <i class="fas fa-angle-up"></i>
        </button>

        <!-- Scripts -->
        <script src="js/backToTop.js"></script>
        <script src="js/koiList.js"></script>

        <script>AOS.init();</script>

        <!-- Footer -->
        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>
