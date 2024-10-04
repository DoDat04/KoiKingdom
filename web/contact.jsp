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
        <title>Contact Us</title>
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
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
            background-color: #b0e0e6;
            padding: 40px;
        }

        .banner-text h2 {
            font-family: 'Dancing Script', cursive;
            font-size: 4em;
            color: #003366;
            text-align: center;
            margin-bottom: 0.2em;
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
                <img src="img/phone.jpg" alt="Phone Contact">
                <img src="img/message.jpg" alt="Message Contact">
                <img src="img/letter.jpg" alt="Letter Contact">
            </div>
        </div>

        <!-- Contact Information Section -->
        <div class="container contact-info">
            <div class="row">
                <div class="col-md-4">
                    <h3>Our Office</h3>
                    <p>123 Koi Fish Lane, Tokyo, Japan</p>
                </div>
                <div class="col-md-4">
                    <h3>Call Us</h3>
                    <p>0931 339 228</p>
                </div>
                <div class="col-md-4">
                    <h3>Email Us</h3>
                    <p>koikingdomsystem@gmail.com</p>
                </div>
            </div>
        </div>


        <!-- Contact Form and Map Section -->
        <div class="container form-map-container">
            <!-- Contact Form -->
            <div class="contact-form">
                <h2>Send Us a Message</h2>
                <form action="submit_contact" method="POST">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="name">Your Name</label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="Enter your name" required>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="email">Your Email</label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="subject">Subject</label>
                        <input type="text" class="form-control" id="subject" name="subject" placeholder="Enter subject" required>
                    </div>
                    <div class="form-group">
                        <label for="message">Your Message</label>
                        <textarea class="form-control" id="message" name="message" rows="5" placeholder="Enter your message" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary mt-3 w-100">Send Message</button>
                </form>
            </div>

            <!-- Google Maps -->
            <div class="map">
                <iframe src="https://maps.google.com/maps?q=TPHCM&t=&z=13&ie=UTF8&iwloc=&output=embed" width="100%" height="400" frameborder="0" style="border:0" allowfullscreen=""></iframe>
            </div>
        </div>

        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>
