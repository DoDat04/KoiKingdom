<%-- 
    Document   : footer
    Created on : Sep 14, 2024, 7:24:36 AM
    Author     : ADMIN LAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/footer.css"/>
    </head>
    <body>
        <footer class="text-black py-4 mt-4" style="background-color: antiquewhite">
            <div class="container">
                <div class="logo">
                    <a href="MainController?action=homePage">
                        <img src="img/logo.png" alt="logo" class="img-fluid mb-2 logo-size">
                    </a>
                </div>
                <div class="row d-flex justify-content-center text-center">
                    <div class="col-md-3 mb-4">
                        <h5>Contact Us for Support</h5>
                        <ul class="list-unstyled">
                            <li><i class="fas fa-phone"></i> 0393.447.831</li>
                            <li><i class="fas fa-envelope"></i> KoiKingdomService@gmail.com</li>
                            <li><i class="fas fa-map"></i><a href="https://maps.app.goo.gl/sWhZrkhg6J2VgfV77" style="text-decoration: none">    Google Map</a></li>
                        </ul>
                    </div>
                    <!-- Categories Section -->
                    <div class="col-md-3 mb-4">
                        <h5>Categories</h5>
                        <ul class="list-unstyled">
                            <li><i class="fas fa-ticket-alt"></i><a class="text-footer" href="#"> Tour Booking</a></li>
                            <li><i class="fas fa-map-signs"></i><a class="text-footer" href="#"> Custom Tour Booking</a></li>
                            <li><i class=""></i>
                        </ul>
                    </div>
                    <!-- Services Section -->
                    <div class="col-md-3 mb-4">
                        <h5>Services</h5>
                        <ul class="list-unstyled">
                            <li><a href="#" class="text-black text-footer">My Profile</a></li>
                            <li><a href="#" class="text-black text-footer">Order History</a></li>
                            <li><a href="#" class="text-black text-footer">Notification</a></li>
                            <li><a href="#" class="text-black text-footer">Shopping cart</a></li>
                            <li><a href="#" class="text-black text-footer">Chat with us</a></li>
                        </ul>
                    </div>
                    <!-- Optional Section: Links or Social Media -->
                    <div class="col-md-3 mb-4">
                        <h5>Follow Us</h5>
                        <ul class="list-unstyled">
                            <li><a href="#" class="text-black text-footer"><i class="fab fa-facebook"></i> Facebook</a></li>
                            <li><a href="#" class="text-black text-footer"><i class="fab fa-instagram"></i> Instagram</a></li>
                            <li><a href="#" class="text-black text-footer"><i class="fab fa-twitter"></i> Twitter</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="container d-flex justify-content-center mt-3 ">
                <div class="col-md-6 text-center" style="border-right: 2px solid black ">
                    <strong class="text-center">&copy; 2024 KoiKingdom. All rights reserved.</strong>
                </div>
                <div class="col-md-6 text-center" >
                    <a   href="#" style="text-decoration: none;"><strong > Privacy </strong></a>
                </div>
            </div>
        </div>
    </footer>
</body>
</html>
