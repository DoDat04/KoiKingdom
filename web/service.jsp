<%-- 
    Document   : service
    Created on : Oct 1, 2024, 2:18:37 PM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Services - Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any"><link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">       
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/service.css"> 
        <style>
            body {
                background-color: #f9f9f9;
            }
            .hero {
                background-image: url('img/koibanner.gif');
                background-size: cover;
                height: 300px; /* Adjusted height */
                display: flex;
                justify-content: center;
                align-items: center;
                color: white;
                text-align: center;
                margin: 40px 0; /* Added margin for spacing */
                border-radius: 8px; /* Added border radius */
                box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2); /* Added shadow for depth */
                object-fit: contain;
            }
            .about-us {
                background-color: #fff;
                padding: 40px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .testimonial {
                background-color: #e7f1ff;
                padding: 30px;
                border-radius: 8px;
                margin: 20px 0;
            }
            .service-card {
                border: 1px solid #eaeaea;
                border-radius: 8px;
                padding: 20px;
                text-align: center;
                transition: transform 0.2s;
            }
            .service-card:hover {
                transform: scale(1.05);
            }
        </style>
    </head>
    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>

        <div class="banner-container">
            <div class="banner-text">
                <h2>Category</h2>
                <h1>Our Services</h1>
            </div>
            <div class="banner-images">
                <img src="img/TourImage/1.jpg" alt="Tour 1">
                <img src="img/TourImage/2.jpg" alt="Tour 2">
                <img src="img/TourImage/3.jpg" alt="Tour 3">
            </div>
        </div>

        <div class="container my-5">
            <h2 class="text-center mb-4" id="aboutUs">About Us</h2>
            <div class="about-us">
                <p>Koi Kingdom is the premier destination for koi fish lovers in Vietnam. We specialize in providing quality koi fish purchasing services from Japan. With our team of experts and many years of experience, we are committed to delivering healthy and beautiful koi to our customers.</p>
                <p>We offer both pre-arranged tours and customized services, ensuring a delightful and unique experience when selecting your favorite koi.</p>
            </div>

            <div class="hero">
                <h1>Purchase Koi Fish from Japan</h1>
            </div>

            <h2 class="text-center my-4">Our Services</h2>
            <div class="row">
                <div class="col-md-4">
                    <div class="service-card">
                        <h3>Order Tour</h3>
                        <p>Join our koi fish ordering tours in Japan with professional guides.</p>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="service-card">
                        <h3>Customized Tour</h3>
                        <p>We help you design a personalized tour based on your needs and preferences.</p>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="service-card">
                        <h3>Koi Consulting</h3>
                        <p>Our team of experts is ready to advise you on koi care and selection.</p>
                    </div>
                </div>
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



