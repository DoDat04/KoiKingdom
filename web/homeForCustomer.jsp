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
        <link rel="stylesheet" href="css/home.css">
        <link rel="stylesheet" href="css/style.css">
        <link href="css/toast.css" rel="stylesheet">
        <script src="js/login.js"></script>     
        <script src="js/load.js"></script>
        <style type="text/css">
            .img_box img{
                max-width: 100%;
                border-radius: 20px;
            }
            .slider_container .container{
                padding: 0 15px;
                max-width: 1230px;
                margin: 0 auto;
            }
            .card_slider{
                padding: 50px;
            }
            .slider_container .img_box img {
                width: 100%; /* Hoặc bạn có thể chọn một kích thước cố định, ví dụ: 300px */
                height: auto; /* Đảm bảo giữ tỷ lệ ảnh */
                max-width: 300px; /* Đặt kích thước tối đa nếu cần thu nhỏ ảnh */
                max-height: 200px; /* Giới hạn chiều cao tối đa cho ảnh */
                object-fit: cover; /* Cắt ảnh nếu vượt quá kích thước */
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
                    <img src="img/1.jpg" class="d-block w-100" alt="Slide 1">
                </div>
                <div class="carousel-item banner">
                    <img src="img/2.jpg" class="d-block w-100" alt="Slide 2">
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
                    <img src="img/koi-farm.jpg">
                </div>
            </div>

            <!-- Where do you want to go? -->
            <h1><strong class="welcome-text">Where do you want to go?</strong></h1>
            <!-- Available Tours -->
            <section class="slider_container">
                <div class="container">
                    <div class="swiper card_slider">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/hiroshima.jpg">
                                </div>
                            </div>

                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/kyoto.jpg">
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/kyushu.jpg">
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/nagoya.jpg">
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/osaka.jpg">
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/tokyo.jpg">
                                </div>
                            </div>
                        </div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-pagination"></div>
                    </div>
                </div>
            </section>

            <!-- Popular Tour -->
            <h1><strong class="welcome-text" id="popularTour">Popular Tour</strong></h1>
            <!-- Popular Tours -->
            <section class="slider_container">
                <div class="container">
                    <div class="swiper card_slider">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/hiroshima.jpg">
                                </div>
                            </div>

                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/kyoto.jpg">
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/kyushu.jpg">
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/nagoya.jpg">
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/osaka.jpg">
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/tokyo.jpg">
                                </div>
                            </div>
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
                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/Kohaku.jpg">
                                </div>
                            </div>

                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/Kohaku.jpg">
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/Kohaku.jpg">
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/Kohaku.jpg">
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/Kohaku.jpg">
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <div class="img_box">
                                    <img src="img/Kohaku.jpg">
                                </div>
                            </div>
                        </div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-pagination"></div>
                    </div>
                </div>
            </section>

            <!-- The reason why should you choose our Koi Kingdom -->
            <h1><strong class="welcome-text">Why should you choose our Koi Kingdom?</strong></h1>
            <div class="row text-center align-items-center">
                <div class="col-md-4">
                    <img class="mb-3" src="img/quality.png" alt="quality" height="100px" width="100px">
                    <p class="text-secondary"><strong>Quality Koi Selection:</strong> We offer carefully selected, high-quality Koi fish from top breeders in Japan.</p>
                </div>
                <div class="col-md-4">
                    <img class="mb-3" src="img/doctor-appointment.png" alt="appointment" height="100px" width="100px">
                    <p class="text-secondary"><strong>Expert Care:</strong> Our team has extensive experience in Koi care, ensuring the health and vitality of every fish.</p>
                </div>
                <div class="col-md-4">
                    <img class="mb-3" src="img/comprehension.png" alt="comperhension" width="100px" height="100px">
                    <p class="text-secondary"><strong>Comprehensive Services:</strong> From purchasing to shipping, we provide full support for a seamless Koi buying experience.</p>
                </div>
            </div>
        </div>

        <c:set var="success" value="${sessionScope.updateSuccess}"/>
        <c:if test="${not empty success}">
            <script defer>
                document.addEventListener('DOMContentLoaded', function () {
                    showToast('${success}', 'success');
                });
            </script>
            <c:remove var="updateSuccess" scope="session"/>
        </c:if>

        <c:set var="success" value="${sessionScope.CHANGE_PASS_SUCCESS}"/>
        <c:set var="error" value="${sessionScope.CHANGE_PASS_ERROR}"/>

        <c:if test="${not empty success}">
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    showToast('${success}', 'success');
                });
                <% session.removeAttribute("CHANGE_PASS_SUCCESS"); %>
            </script>
        </c:if>

        <c:if test="${not empty error}">
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    showToast('${error}', 'error');
                });
                <% session.removeAttribute("CHANGE_PASS_ERROR");%>
            </script>
        </c:if>

        <div id="toastBox"></div>

        <!-- Swiper JS -->
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
        <script type="text/javascript">
                var swiper = new Swiper('.card_slider', {
                    slidesPerView: 3, // Hiển thị 3 ảnh một lần
                    spaceBetween: 30, // Khoảng cách giữa các ảnh (tuỳ chỉnh theo ý muốn)
                    slidesPerGroup: 1, // Kéo 1 ảnh mỗi lần
                    loop: true, // Đặt vòng lặp nếu muốn
                    navigation: {
                        nextEl: '.swiper-button-next',
                        prevEl: '.swiper-button-prev',

                    },
                    autoplay: {
                        delay: 3000, // Thời gian giữa các lần chuyển cảnh (tính bằng mili giây)
                        disableOnInteraction: false, // Vẫn tiếp tục autoplay ngay cả khi người dùng tương tác
                    },
                    pagination: {
                        el: '.swiper-pagination',
                        clickable: true,
                    },
                });
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
