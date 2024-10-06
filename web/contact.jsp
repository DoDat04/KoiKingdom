<%-- 
    Document   : contact
    Created on : Oct 4, 2024, 9:10:46 PM
    Author     : ADMIN LAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Us - Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">       
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/contact.css">
        <script src="js/load.js"></script>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <style>
        .banner-container {
            display: flex;
            align-items: center;
            justify-content: space-between;
            background-color: #f8d8cc;
            padding: 40px;
        }

        .banner-text h2 {
            font-family: 'Dancing Script', cursive;
            font-size: 4em;
            color: #003366;
            text-align: center;
            margin-bottom: 0.2em;
        }

        .contact-container {
            max-width: 1200px;
            margin: 20px auto;
            text-align: center;
        }

        .contact-card {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            margin-top: 20px;
        }

        .card {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            flex: 1;
            margin: 10px;
            padding: 20px;
            min-width: 250px;
            text-align: left;
        }

        .card h3 {
            margin: 10px 0;
            font-size: 20px;
        }

        .card p {
            margin: 5px 0;
            color: #666;
        }

        .card i {
            font-size: 30px;
        }
        .map-container {
            width: 100%; /* Takes up the full width of the parent */
            max-width: 1200px; /* Matches the max-width of .contact-container */
            margin: 20px auto; /* Center the map with margins */
        }

        .map-container iframe {
            width: 100%; /* Full width of the container */
            height: 400px; /* Keep the height fixed */
            border: none;
        }

    </style>
    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>

        <!-- Banner Section -->
        <div class="banner-container">
            <div class="banner-text">
                <h2>Category</h2>
                <h1>Contact Us</h1>
            </div>
            <div class="banner-images">
                <img src="img/phonee-removebg-preview.png" alt="Phone Contact">
                <img src="img/messagee-removebg-preview.png" alt="Message Contact">
                <img src="img/email-removebg-preview.png" alt="Letter Contact">
            </div>
        </div>

        <div class="contact-container">
            <h1>Contact Koi Kingdom</h1>
            <div class="contact-card">
                <div class="card">
                    <i class="fa-solid fa-location-dot" aria-hidden="true"></i>
                    <h3>ADDRESS</h3>
                    <p>Lot E2a-7, Street D1 High-Tech Park, Long Thanh My Ward, Thu Duc, City. Ho Chi Minh.</p>
                </div>
                <div class="card">
                    <i class="fa fa-clock" aria-hidden="true"></i>
                    <h3>WORKING TIME</h3>
                    <p>Monday - Saturday: 8 a.m - 6 p.m<br>Sunday: 8 a.m - 5 p.m</p>
                </div>
                <div class="card">
                    <i class="fa fa-phone" aria-hidden="true"></i>
                    <h3>HOTLINE</h3>
                    <p>Service 24/7: 0931 339 228<br>Contact us directly when you need.</p>
                </div>
                <div class="card">
                    <i class="fa fa-envelope" aria-hidden="true"></i>
                    <h3>EMAIL</h3>
                    <p>koikingdomsystem@gmail.com<br>If urgent please contact us through hotline for faster reply.</p>
                </div>
            </div>
        </div>
        <!-- Google Maps -->
        <div class="map-container">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.60994153052!2d106.80730807583869!3d10.841132857995182!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752731176b07b1%3A0xb752b24b379bae5e!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBGUFQgVFAuIEhDTQ!5e0!3m2!1svi!2s!4v1728156532845!5m2!1svi!2s" width="100%" height="400" frameborder="0" style="border:0" allowfullscreen=""></iframe>
        </div>

        <!-- Back to Top Button -->
        <button id="backToTop" class="btn btn-primary" style="display: none;">
            <i class="fas fa-angle-up"></i>
        </button>

        <script src="js/backToTop.js"></script>    
        <script src="js/tourList.js"></script>

        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>
