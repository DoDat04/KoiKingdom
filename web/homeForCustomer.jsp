<%-- 
    Document   : homeForCustomer
    Created on : Sep 14, 2024, 7:15:52 AM
    Author     : ADMIN LAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <!-- AOS -->
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
        <link rel="stylesheet" href="css/home.css">
        <link rel="stylesheet" href="css/style.css">
        <link href="css/toast.css" rel="stylesheet">
        <script src="js/login.js"></script>     
        <script src="js/load.js"></script>
        <style type="text/css">
            .slider_container {
                padding: 30px 0; /* Vertical padding for the section */
                background-color: #f9f9f9; /* Light background for contrast */
            }

            .slider_container .container {
                max-width: 1230px;
                margin: 0 auto; /* Center the container */
                padding: 0 15px; /* Horizontal padding */
            }

            .card_slider {
                padding: 50px 0; /* Adjust top/bottom padding */
            }

            .swiper-slide {
                display: flex;
                justify-content: center; /* Center slide contents horizontally */
                align-items: center; /* Center slide contents vertically */
                flex-direction: column; /* Stack image and text */
                text-align: center; /* Center text alignment */
            }

            .img_box {
                position: relative;
                overflow: hidden; /* Prevent overflow */
                border-radius: 20px; /* Rounded corners for images */
            }

            .img_box img {
                width: 100%; /* Responsive width */
                height: auto; /* Maintain aspect ratio */
                object-fit: cover; /* Cover to maintain aspect ratio */
                border-radius: 20px; /* Rounded corners for images */
            }

            .tour-info {
                position: absolute; /* Overlay on the image */
                bottom: 10%; /* Position it at the bottom */
                left: 50%;
                transform: translateX(-50%); /* Center it horizontally */
                background: rgba(0, 0, 0, 0.6); /* Semi-transparent background */
                color: #ffffff; /* White text */
                padding: 10px 15px; /* Add padding */
                border-radius: 10px; /* Rounded corners for text box */
                transition: opacity 0.3s; /* Smooth transition for hover effect */
            }

            .img_box:hover .tour-info {
                opacity: 1; /* Show text overlay on hover */
            }

            .swiper-button-next,
            .swiper-button-prev {
                color: #ffffff; /* White arrows */
                background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent background */
                border-radius: 50%; /* Round buttons */
                width: 40px; /* Fixed width */
                height: 40px; /* Fixed height */
                display: flex;
                justify-content: center;
                align-items: center;
                z-index: 10; /* Ensure buttons are above other elements */
            }

            .swiper-pagination {
                bottom: 10px; /* Position pagination */
            }

            .swiper-pagination-bullet {
                background: #007bff; /* Blue bullets */
                opacity: 0.8; /* Semi-transparent */
            }

            .swiper-pagination-bullet-active {
                background: #0056b3; /* Darker blue for active bullet */
            }
        </style>
    </head>
    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>

        <!-- Banner Carousel -->
        <div id="singleImageCarousel" class="carousel slide" data-bs-ride="carousel" data-bs-interval="3000">
            <div class="carousel-inner">
                <div class="carousel-item banner active">
                    <form action="koi-list-order">
                        <button type="submit" class="d-block w-100" style="border: none; background: none; padding: 0;">
                            <img src="img/1.jpg" class="d-block w-100" alt="Slide 1">
                        </button>
                    </form>
                </div>
                <div class="carousel-item banner">
                    <form action="listkoitype">
                        <button type="submit" class="d-block w-100" style="border: none; background: none; padding: 0;">
                            <img src="img/2.jpg" class="d-block w-100" alt="Slide 2">
                        </button>
                    </form>
                </div>
                <div class="carousel-item banner">
                    <img src="img/koibanner.gif" class="d-block w-100" alt="Slide 3">
                </div>
            </div>

            <!-- Nút điều hướng Carousel -->
            <button class="carousel-control-prev" type="button" data-bs-target="#singleImageCarousel" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#singleImageCarousel" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>

        <!-- Welcome -->
        <h1><strong class="welcome-text">Welcome to Koi Kingdom</strong></h1>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h2 style="font-style: italic" class="text-secondary text-sub">- Explore the world of Koi with our exclusive farm tours.<br/></h2>
                    <h2 style="font-style: italic" class="text-secondary text-sub">- Experience Japan's top Koi farms, learn from experts, and see stunning fish up close. <br/></h2>
                    <h2 style="font-style: italic" class="text-secondary text-sub">- Whether you're a Koi enthusiast or new to the hobby, our tours offer an unforgettable glimpse into Koi culture.<br/></h2>
                    </h2>
                </div>
                <div class="koifarm-image col-md-6">
                    <form action="listfarm">
                        <button type="submit" class="d-block w-100" style="border: none; background: none; padding: 0;">
                            <img src="img/koi-farm.jpg">
                        </button>
                    </form>
                </div>
            </div>

            <!-- Where do you want to go? -->
            <h1><strong class="welcome-text">Top highest rating tour for you!</strong></h1>
            <!-- Available Tours -->
            <section class="slider_container">
                <div class="container">
                    <div class="swiper card_slider">
                        <div class="swiper-wrapper">
                            <c:forEach var="tour" items="${requestScope.HIGHEST_RATING_TOUR}">
                                <div class="swiper-slide">
                                    <div class="img_box">
                                        <form action="tour-detail" method="get">
                                            <input type="hidden" name="tourID" value="${tour.tourID}" />
                                            <button type="submit" class="btn btn-link p-0" style="text-decoration: none; color: inherit;">
                                                <img src="${tour.tourImage}" alt="${tour.tourName}">
                                                <div class="tour-info">
                                                    <h3 class="welcome-text">${tour.tourName}</h3>
                                                </div>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-pagination"></div>
                    </div>
                </div>
            </section>

            <h1><strong class="welcome-text" id="popularTour">Popular Tours</strong></h1>
            <!-- Popular Tours -->
            <section class="slider_container">
                <div class="container">
                    <div class="swiper card_slider">
                        <div class="swiper-wrapper">
                            <c:forEach var="tour" items="${requestScope.TRENDING_TOURS}">
                                <div class="swiper-slide">
                                    <div class="img_box">
                                        <form action="tour-detail" method="get">
                                            <input type="hidden" name="tourID" value="${tour.tourID}" />
                                            <button type="submit" class="btn btn-link p-0" style="text-decoration: none; color: inherit;">
                                                <img src="${tour.tourImage}" alt="${tour.tourName}">
                                                <div class="tour-info">
                                                    <h3 class="welcome-text">${tour.tourName}</h3>
                                                </div>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-pagination"></div>
                    </div>
                </div>
            </section>

            <!-- Popular Koi Fish Breeds -->
            <h1><strong class="welcome-text" id="popularKoi">Popular Koi Fish Breeds</strong></h1>
            <!-- Popular Koi Fish -->
            <section class="slider_container">
                <div class="container">
                    <div class="swiper card_slider">
                        <div class="swiper-wrapper">
                            <c:forEach var="koi" items="${requestScope.KOI_TRENDING}">
                                <div class="swiper-slide">
                                    <div class="img_box">
                                        <img src="${koi.image}" alt="${koi.koiName}">
                                        <div class="tour-info">
                                            <h3 class="welcome-text">${koi.koiName}</h3>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-pagination"></div>
                    </div>
                </div>
            </section>

            <!-- The reason why should you choose our Koi Kingdom -->
            <h1><strong class="welcome-text">Why should you choose our Koi Kingdom?</strong></h1>
            <div class="row text-center align-items-center" >
                <div class="col-md-4" data-aos="fade-up" data-aos-duration="3000">
                    <img class="mb-3" src="img/quality.png" alt="quality" height="100px" width="100px">
                    <p class="text-secondary"><strong>Quality Koi Selection:</strong> We offer carefully selected, high-quality Koi fish from top breeders in Japan.</p>
                </div>
                <div class="col-md-4" data-aos="fade-up" data-aos-duration="3000">
                    <img class="mb-3" src="img/doctor-appointment.png" alt="appointment" height="100px" width="100px">
                    <p class="text-secondary"><strong>Expert Care:</strong> Our team has extensive experience in Koi care, ensuring the health and vitality of every fish.</p>
                </div>
                <div class="col-md-4" data-aos="fade-up" data-aos-duration="3000">
                    <img class="mb-3" src="img/comprehension.png" alt="comperhension" width="100px" height="100px">
                    <p class="text-secondary"><strong>Comprehensive Services:</strong> From purchasing to shipping, we provide full support for a seamless Koi buying experience.</p>
                </div>
            </div>
        </div>

        <c:set var="success" value="${sessionScope.updateSuccess}"/>
        <c:if test="${not empty success}">
            <script>
                window.onload = function () {
                    showToast('${success}', 'success');
                };
            </script>
            <c:remove var="updateSuccess" scope="session"/>
        </c:if>

        <c:set var="success" value="${sessionScope.CHANGE_PASS_SUCCESS}"/>
        <c:set var="error" value="${sessionScope.CHANGE_PASS_ERROR}"/>

        <c:if test="${not empty success}">
            <script>
                window.onload = function () {
                    showToast('${success}', 'success');
                };

                <% session.removeAttribute("CHANGE_PASS_SUCCESS"); %>


            </script>
        </c:if>

        <c:if test="${not empty error}">
            <script>
                window.onload = function () {
                    showToast('${error}', 'error');
                };
                <% session.removeAttribute("CHANGE_PASS_ERROR");%>
            </script>
        </c:if>

        <c:set var="logoutSuccess" value="${requestScope.notiSuccess}"/>

        <c:if test="${not empty logoutSuccess}">
            <script>
                window.onload = function () {
                    showToast('${logoutSuccess}', 'success');
                };
            </script>
        </c:if>

        <div id="toastBox"></div>

        <!-- Swiper JS -->
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
        <script type="text/javascript">
                var swiper = new Swiper('.card_slider', {
                    slidesPerView: 3,
                    spaceBetween: 30,
                    slidesPerGroup: 1,
                    loop: true,
                    navigation: {
                        nextEl: '.swiper-button-next',
                        prevEl: '.swiper-button-prev',
                    },
                    autoplay: {
                        delay: 3000,
                        disableOnInteraction: false,
                    },
                    pagination: {
                        el: '.swiper-pagination',
                        clickable: true,
                    },
                });
        </script>
        <!-- AOS -->
        <script>
            AOS.init();
        </script>
        <script src="js/showToast.js"></script>

        <!-- Back to Top Button -->
        <button id="backToTop" class="btn btn-primary" style="display: none;">
            <i class="fas fa-angle-up"></i>
        </button>              

        <script src="js/backToTop.js"></script> 
        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>
