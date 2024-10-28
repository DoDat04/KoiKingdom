<%-- 
    Document   : tourList
    Created on : Sep 22, 2024, 7:45:55 PM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tour List - Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <!--MUST IMPLEMENT BOOTSRAP-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">       
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@700&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/koiList.css">         
        <!-- AOS -->
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
    </head>   
    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>

        <!-- Banner / Slideshow -->
        <div class="banner-container">
            <div class="banner-text">
                <h2>Category</h2>
                <h1>Travel Tours</h1>
            </div>
            <div class="banner-images">
                <img src="img/TourImage/1.jpg" alt="Tour 1">
                <img src="img/TourImage/1.jpg" alt="Tour 2">
                <img src="img/TourImage/1.jpg" alt="Tour 3">
            </div>
        </div>


        <div class="container mt-5">            
            <!-- Filter Form -->
            <form method="GET" action="tour-list" class="mb-5">
                <div class="row mb-4">
                    <div class="col-md-12 mb-3">
                        <c:if test="${not empty param.farmID or not empty param.koiTypeID or not empty param.priceOrder or not empty param.startDate or not empty param.endDate}">
                            <button type="button" class="btn btn-danger" onclick="removeFilters()">Remove Filter</button>
                        </c:if>
                    </div>

                    <!-- Filter by farm -->
                    <div class="col-md-2">
                        <strong><label for="farmFilter" class="form-label">Farm</label></strong>
                        <select id="farmFilter" name="farmID" class="form-select" onchange="this.form.submit()">
                            <option value="" ${empty param.farmID ? 'selected' : ''}>All Farms</option>
                            <c:forEach var="farm" items="${farmList}">
                                <option value="${farm.farmID}" ${param.farmID == farm.farmID ? 'selected' : ''}>${farm.farmName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Filter by Koi Type -->
                    <div class="col-md-2">
                        <strong><label for="koiTypeFilter" class="form-label">Koi Type</label></strong>
                        <select id="koiTypeFilter" name="koiTypeID" class="form-select" onchange="this.form.submit()">
                            <option value="" ${empty param.koiTypeID ? 'selected' : ''}>All Types</option>
                            <c:forEach var="koiType" items="${koiTypeList}">
                                <option value="${koiType.koiTypeID}" ${param.koiTypeID == koiType.koiTypeID ? 'selected' : ''}>${koiType.typeName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Filter by Price Order -->
                    <div class="col-md-2">
                        <strong><label for="priceFilter" class="form-label">Price</label></strong>
                        <select id="priceFilter" name="priceOrder" class="form-select" onchange="this.form.submit()">
                            <option value="" ${empty param.priceOrder ? 'selected' : ''}>All Prices</option>
                            <option value="asc" ${param.priceOrder == 'asc' ? 'selected' : ''}>Price (Low to High)</option>
                            <option value="desc" ${param.priceOrder == 'desc' ? 'selected' : ''}>Price (High to Low)</option>
                        </select>
                    </div>

                    <!-- Filter by Dates -->
                    <div class="col-md-6 d-flex">
                        <div class="me-3 w-100">
                            <strong><label for="startDateFilter" class="form-label">From Date</label></strong>
                            <input type="date" id="startDateFilter" name="startDate" class="form-control" placeholder="dd/mm/yyyy" value="${param.startDate}" onchange="this.form.submit()">
                        </div>
                        <div class="w-100">
                            <strong><label for="endDateFilter" class="form-label">To Date</label></strong>
                            <input type="date" id="endDateFilter" name="endDate" class="form-control" placeholder="dd/mm/yyyy" value="${param.endDate}" onchange="this.form.submit()">
                        </div>
                    </div>                   
                </div>
            </form>                                

            <!-- Tour List Section -->
            <h2 class="text-center">Tour List</h2>
            <div class="row">
                <c:if test="${empty tourList}">
                    <div class="col-12 text-center">
                        <div class="alert alert-warning" role="alert" style="font-size: 1.2rem; border-radius: 8px;">
                            <strong>No tour found!</strong> <br>
                            We're sorry, but there are no tours that match your search criteria. 
                            Try changing the filter or check again later.
                        </div>
                    </div>
                </c:if>
                <c:forEach var="tour" items="${tourList}">
                    <div class="col-md-4 mb-4">
                        <div class="card h-100 position-relative" data-aos="fade-up"
                             data-aos-duration="3000">
                            <!-- Form trang chi tiết tour -->
                            <form action="tour-detail" method="get">
                                <input type="hidden" name="tourID" value="${tour.tourID}" />
                                <!-- Chỉ bao quanh phần hình ảnh và tên tour -->
                                <button type="submit" class="btn btn-link p-0" style="text-decoration: none; color: inherit;">
                                    <!-- Tour Image -->
                                    <img src="${tour.tourImage}" class="card-img-top" alt="${tour.tourName}">

                                    <!-- Tên tour -->
                                    <div class="card-body text-start">
                                        <h5 class="card-title">${tour.tourName}</h5>
                                    </div>
                                </button>
                            </form>

                            <!-- Nội dung tour không liên quan tới link -->
                            <div class="card-body text-start">
                                <span class="rating">
                                    <i class="fas fa-star text-warning"></i>
                                    <span class="rating-number">${tour.tourRating}</span>
                                </span>
                                <p class="card-text"><strong>Duration:</strong> ${tour.duration}</p>
                                <p class="card-text"><strong>Start Date:</strong>
                                    <fmt:formatDate value="${tour.startDate}" pattern="dd-MM-yyyy" />
                                </p>
                                <p class="card-text"><strong>End Date:</strong>
                                    <fmt:formatDate value="${tour.endDate}" pattern="dd-MM-yyyy" />
                                </p>
                                <p class="card-text"><strong>Farm:</strong> ${tour.farmName}</p>
                                <p class="card-text"><strong>Koi Type:</strong> ${tour.koiTypeName}</p>
                                <p class="card-text"><strong>Departure Location:</strong> ${tour.tourDepartLoca}</p>
                            </div>
                            <div class="card-footer text-end">
                                <strong class="fs-4 text-danger">${tour.tourPrice} $</strong>
                            </div>

                            <!-- Form ẩn để thêm tour vào yêu thích -->
                            <form action="favorite" method="post" id="favoriteForm-${tour.tourID}">
                                <input type="hidden" name="tourID" value="${tour.tourID}">
                                <input type="hidden" name="custID" value="${sessionScope.custID}">
                            </form>

                            <!-- Biểu tượng trái tim -->
                            <div class="favorite-icon position-absolute" style="top: -9px; left: 395px;">
                                <c:choose>
                                    <c:when test="${sessionScope.LOGIN_USER == null and sessionScope.LOGIN_GMAIL == null}">
                                        <i class="fas fa-heart" style="font-size: 2rem; cursor: pointer;"
                                           onclick="alert('You need to login to add favorite tours!')">
                                        </i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fas fa-heart" style="font-size: 2rem; cursor: pointer;"
                                           onclick="document.getElementById('favoriteForm-${tour.tourID}').submit(); event.stopPropagation();">
                                        </i> 
                                    </c:otherwise>
                                </c:choose>
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

        <script src="js/backToTop.js"></script>    
        <script src="js/tourList.js"></script>     
        <script>
                                               AOS.init();
        </script>

        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>

