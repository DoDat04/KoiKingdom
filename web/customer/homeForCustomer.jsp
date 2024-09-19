<%-- 
    Document   : homeForCustomer
    Created on : Sep 14, 2024, 7:15:52 AM
    Author     : ADMIN LAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Koi Kingdom</title>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/home.css">
    </head>
    <body>
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
                        <img src="img/4.jpg" class="d-block w-100" alt="Slide 3">
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
            <div id="multiCarousel" class="carousel slide" data-bs-ride="carousel" data-bs-interval="3000">
                <div class="carousel-inner">
                    <!-- Slide 1 -->
                    <div class="carousel-item active">
                        <div class="row">
                            <div class="col-md-4">
                                <img src="img/hiroshima.jpg" class="d-block w-100" alt="Hiroshima">
                            </div>
                            <div class="col-md-4">
                                <img src="img/tokyo.jpg" class="d-block w-100" alt="Tokyo">
                            </div>
                            <div class="col-md-4">
                                <img src="img/kyoto.jpg" class="d-block w-100" alt="Kyoto">
                            </div>
                        </div>
                    </div>
                    <!-- Slide 2 -->
                    <div class="carousel-item">
                        <div class="row">
                            <div class="col-md-4">
                                <img src="img/osaka.jpg" class="d-block w-100" alt="Osaka">
                            </div>
                            <div class="col-md-4">
                                <img src="img/nagoya.jpg" class="d-block w-100" alt="Nagoya">
                            </div>
                            <div class="col-md-4">
                                <img src="img/kyushu.jpg" class="d-block w-100" alt="Kyushu">
                            </div>
                        </div>
                    </div>
                </div>

                <button class="carousel-control-prev" type="button" data-bs-target="#multiCarousel" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#multiCarousel" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>

            <!-- Popular Tour -->
            <h1><strong class="welcome-text">Popular Tour</strong></h1>
            <div id="popularTourCarousel" class="carousel slide" data-bs-ride="carousel" data-bs-interval="3000">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="row">
                            <div class="col-md-4">
                                <img src="img/hiroshima.jpg" alt="Hiroshima" class="d-block w-100">
                            </div>
                            <div class="col-md-4">
                                <img src="img/kyoto.jpg" alt="Kyoto" class="d-block w-100">
                            </div>
                            <div class="col-md-4">
                                <img src="img/kyushu.jpg" alt="Kyusu" class="d-block w-100">
                            </div>
                        </div>
                    </div>

                    <div class="carousel-item">
                        <div class="row">
                            <div class="col-md-4">
                                <img src="img/nagoya.jpg" alt="Nagoya" class="d-block w-100">
                            </div>
                            <div class="col-md-4">
                                <img src="img/osaka.jpg" alt="Osaka" class="d-block w-100">
                            </div>
                            <div class="col-md-4">
                                <img src="img/tokyo.jpg" alt="Tokyo" class="d-block w-100">
                            </div>
                        </div>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#popularTourCarousel" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#popularTourCarousel" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>

            <!-- Popular Koi Fish Breeds -->
            <h1><strong class="welcome-text">Popular Koi Fish Breeds</strong></h1>
            <div id="koiFIshBreeds" class="carousel slide" data-bs-ride="carousel" data-bs-interval="3000">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="row">
                            <div class="col-md-4">
                                <img src="img/Kohaku.jpg" alt="Kohaku" class="d-block w-100">
                            </div>
                            <div class="col-md-4">
                                <img src="img/Shiro Utsuri.jpg" alt="Shiro Utsuri" class="d-block w-100">
                            </div>
                            <div class="col-md-4">
                                <img src="img/Koi Showa Sanshoku.jpg" alt="Koi Showa Sanshoku" class="d-block w-100">
                            </div>
                        </div>
                    </div>

                    <div class="carousel-item">
                        <div class="row">
                            <div class="col-md-4">
                                <img src="img/Koi Showa Sanshoku.jpg" alt="Showa Sanshoku" class="d-block w-100">
                            </div>
                            <div class="col-md-4">
                                <img src="img/Kohaku.jpg" alt="Kohaku" class="d-block w-100">
                            </div>
                            <div class="col-md-4">
                                <img src="img/Shiro Utsuri.jpg" alt="Shiro Utsuri" class="d-block w-100">
                            </div>
                        </div>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#koiFIshBreeds" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#koiFIshBreeds" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>

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
        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>
