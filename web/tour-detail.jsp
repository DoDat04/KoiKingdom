<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tour Detail Page</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">

        <!-- Bootstrap and Font Awesome -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@700&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/tour-detail.css">

    </head>
    <body>
        <div class="colorlib-loader"></div>

        <!-- Header Include -->
        <jsp:include page="headerForCustomer.jsp" flush="true"/>

        <!-- Tour Header Section -->
        <div class="banner-container">
            <div class="banner-text">
                <h2>Category</h2>
                <h1>Tour Details</h1>
            </div>
            <div class="banner-images">
                <img src="img/TourImage/1.jpg" alt="Tour 1">
                <img src="img/TourImage/1.jpg" alt="Tour 2">
                <img src="img/TourImage/1.jpg" alt="Tour 3">
            </div>
        </div>      

        <!-- Back to Tour List Link -->
        <div class="text-end my-4 backToTour">
            <a href="tour-list" class="btn btn-link">&lt; Back to Tour List</a>
        </div>

        <!-- Tour List Section -->
        <section class="tour-list py-5">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 mb-4">
                        <!-- Tour Image -->
                        <img src="${selectedTour.tourImage}" alt="${selectedTour.tourName}" class="tour-image img-fluid">
                    </div>
                    <div class="col-md-6 mb-4">
                        <div class="tour-info">
                            <!-- Tour Details -->
                            <h3>${selectedTour.tourName}</h3>
                            <span class="rating">
                                <i class="fas fa-star text-warning"></i>
                                <span class="rating-number">${selectedTour.tourRating}</span>
                            </span>
                            <p><strong>Duration:</strong> ${selectedTour.duration}</p>
                            <p><strong>Start Date:</strong> <fmt:formatDate value="${selectedTour.startDate}" pattern="dd-MM-yyyy"/></p>
                            <p><strong>End Date:</strong> <fmt:formatDate value="${selectedTour.endDate}" pattern="dd-MM-yyyy"/></p>
                            <p><strong>Farm:</strong> ${selectedTour.farmName}</p>
                            <p><strong>Koi Type:</strong> ${selectedTour.koiTypeName}</p>
                            <p class="text-end tour-price">${selectedTour.tourPrice} $</p>

                            <!-- Quantity Input -->
                            <div class="quantity-container">
                                <span class="quantity-label"><strong>Number of people:</strong></span>
                                <br>
                                <button class="btn btn-secondary" type="button" onclick="updateQuantity(-1)">-</button>
                                <span id="quantityDisplay" class="quantity-display">0</span>
                                <button class="btn btn-secondary" type="button" onclick="updateQuantity(1)">+</button>                                
                            </div>

                            <div class="buttonTour d-flex justify-content-between align-items-center mt-4">
                                <!-- Form to add to cart -->
                                <form action="AddToCartController" method="post">
                                    <input type="hidden" name="tourID" value="${selectedTour.tourID}">
                                    <input type="hidden" name="numberOfPeople" id="hiddenQuantity" value="0">
                                    <input type="hidden" name="customerID" value="${sessionScope.custID}">

                                    <!-- Add to Cart -->
                                    <div class="tour-buttons">
                                        <button type="submit" class="btn btn-primary btn-block" id="addToCartBtn" style="width: 200px;">
                                            <i class="fas fa-cart-plus"></i> Add to Cart
                                        </button>
                                    </div>
                                </form>

                                <!-- Form to book now -->
                                <form action="BookNowController" method="post">
                                    <input type="hidden" name="tourID" value="${selectedTour.tourID}">
                                    <input type="hidden" name="numberOfPeople" id="hiddenQuantityy" value="0">
                                    <input type="hidden" name="customerID" value="${sessionScope.custID}">

                                    <!-- Book Now -->
                                    <div class="tour-buttons">
                                        <button type="submit" class="btn btn-danger btn-block" id="bookNowBtn" style="width: 200px;">
                                            <i class="fas fa-credit-card"></i> Book Now
                                        </button>
                                    </div>
                                </form>
                            </div>   
                            <script>
                                document.getElementById("bookNowBtn").addEventListener("click", function (event) {
                                    // Kiểm tra nếu người dùng chưa đăng nhập
                                    const isUserLoggedIn = "${sessionScope.LOGIN_USER}" || "${sessionScope.LOGIN_GMAIL}";
                                    if (!isUserLoggedIn) {
                                        alert('You need to login to book now!');
                                        event.preventDefault(); // Ngăn chặn gửi form
                                    } else {
                                        // Cho phép gửi form nếu đã đăng nhập
                                        document.getElementById("bookNowForm").submit();
                                    }
                                });
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Tour Schedule Section -->
        <section class="tour-schedule py-5">
            <div class="container">
                <h2 class="section-title text-center mb-4">Tour Schedule</h2>

                <!-- Schedule Card -->
                <div class="card schedule-card">
                    <div class="card-body">
                        <p class="description h5"><strong>Description:</strong></p>
                        <hr>
                        <div class="description-content">${selectedTour.description}</div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Tour Feedback Section -->
        <section class="tour-feedback py-5">
            <div class="container">
                <h2 class="section-title text-center mb-4">Tour Feedback</h2>

                <!-- Card for Feedback -->
                <div class="card">
                    <div class="card-body">
                        <div class="feedback-list" id="feedbackContainer">
                            <div class="feedback-item">
                                <p class="user-name"><strong>John Doe:</strong></p>
                                <p class="feedback-text">The tour was fantastic! Highly recommend.</p>
                            </div>
                            <div class="feedback-item">
                                <p class="user-name"><strong>Jane Smith:</strong></p>
                                <p class="feedback-text">Great experience! Will come back for more.</p>
                            </div>
                            <!-- More feedback items -->
                            <div class="more-feedback" style="display: none;">
                                <div class="feedback-item">
                                    <p class="user-name"><strong>Mike Johnson:</strong></p>
                                    <p class="feedback-text">An unforgettable experience!</p>
                                </div>
                                <div class="feedback-item">
                                    <p class="user-name"><strong>Emily Davis:</strong></p>
                                    <p class="feedback-text">Highly informative and enjoyable.</p>
                                </div>
                            </div>
                        </div>
                        <a href="#" onclick="toggleMoreFeedback(event)" class="show-more-link">Show More</a>
                    </div>
                </div>
            </div>
        </section>       

        <!-- Back to Top Button -->
        <button id="backToTop" class="btn btn-primary" style="display: none;">
            <i class="fas fa-angle-up"></i>
        </button>

        <script src="js/backToTop.js"></script>        

        <!-- Footer Include -->
        <jsp:include page="footer.jsp" flush="true"/>
        <script src="js/tourDetail.js"></script>
    </body>
</html>
