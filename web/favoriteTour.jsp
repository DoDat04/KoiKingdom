<%-- 
    Document   : favoriteTour
    Created on : Oct 2, 2024, 9:31:39 AM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Favorite Tour - Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/tourList.css"> 
    </head>
    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>
        
        <div class="banner-container">
            <div class="banner-text">
                <h2>Category</h2>
                <h1>Favorite Tours</h1>
            </div>
            <div class="banner-images">
                <img src="img/TourImage/1.jpg" alt="Tour 1">
                <img src="img/TourImage/1.jpg" alt="Tour 2">
                <img src="img/TourImage/1.jpg" alt="Tour 3">
            </div>
        </div>
        
        <div class="text-end my-4 backToTour">
            <a href="tour-list" class="btn btn-link">&lt; Back to Tour List</a>
        </div>

        <div class="container mt-5">
            <h2 class="text-center">Favorite Tour List</h2>
            <div class="row">
                <c:if test="${empty favoriteList}">
                    <div class="col-12 text-center">
                        <div class="alert alert-warning" role="alert" style="font-size: 1.2rem; border-radius: 8px;">
                            <strong>Không tìm thấy tour!</strong> <br>
                            Chúng tôi rất tiếc, nhưng bạn chưa thêm tour nào vào mục yêu thích cả. 
                            Hãy thử quay lại và thêm tour vào mục yêu thích.
                        </div>
                    </div>
                </c:if>
                <c:forEach var="tour" items="${favoriteList}">
                    <div class="col-md-4 mb-4">
                        <div class="card h-100 position-relative">

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
                            <form action="remove-favorite" method="post" id="favoriteForm-${tour.tourID}">
                                <input type="hidden" name="favoriteID" value="${tour.favoriteTourID}">
                            </form>

                            <!-- Biểu tượng trái tim -->
                            <div class="favorite-icon position-absolute" style="top: -9px; left: 395px;">
                                <i class="fas fa-heart text-danger" style="font-size: 2rem; cursor: pointer;"
                                   onclick="document.getElementById('favoriteForm-${tour.tourID}').submit(); event.stopPropagation();">
                                </i>
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
        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>
