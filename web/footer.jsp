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
        <footer style="background-color: #fdf4f0;">
            <div class="container">
                <div class="row">
                    <!-- Logo and Contact Information -->
                    <div class="col-md-3 footer_first_column">
                        <img src="img/logo.png" alt="logo" class="img-fluid logo-size" style="max-width: 150px;">
                        <h6>Contact us for support</h6>
                        <ul class="list-unstyled">
                            <li><i class="fas fa-phone"></i> 0931 339 228</li>
                            <li><i class="fas fa-envelope"></i> koikingdomsystem@gmail.com</li>
                        </ul>
                    </div>

                    <!-- Categories Section -->
                    <div class="col-md-2">
                        <h6>Categories</h6>
                        <ul class="list-unstyled">
                            <li><a href="#popularTour">Famous Tours</a></li>
                            <li><a href="tour-list">Available Tours</a></li>
                            <li><a href="#">Custom Tour</a></li>
                            <li><a href="#popularKoi">Popular Koi Breeds</a></li>
                            <li><a href="#">Koi Species</a></li>
                        </ul>
                    </div>

                    <!-- Services Section -->
                    <div class="col-md-2">
                        <h6>Services</h6>
                        <ul class="list-unstyled">
                            <li>
                                <a href="#" data-bs-toggle="modal" data-bs-target="#updateProfileModal">
                                    My Profile
                                </a>
                            </li>
                            <li><a href="#">Order history</a></li>
                            <li><a href="#">Notifications</a></li>
                            <li><a href="#">Shopping cart</a></li>
                            <li><a href="#">Chat with us</a></li>
                        </ul>
                    </div>

                    <!-- Information Section -->
                    <div class="col-md-2">
                        <h6>Information</h6>
                        <ul class="list-unstyled">
                            <li><a href="#">FAQs</a></li>
                            <li><a href="#">Articles</a></li>
                            <li><a href="#">About us</a></li>
                            <li><a href="#">Contact us</a></li>
                            <li><a href="#">Tour price</a></li>
                        </ul>
                    </div>

                    <!-- Social Media and Payment -->
                    <div class="col-md-3">
                        <h6>Connect with us</h6>
                        <ul class="list-inline">
                            <li class="list-inline-item"><a href="#"><i class="fab fa-facebook fa-2x"></i></a></li>
                            <li class="list-inline-item"><a href="#"><i class="fab fa-instagram fa-2x"></i></a></li>
                            <li class="list-inline-item"><a href="#"><i class="fab fa-tiktok fa-2x"></i></a></li>
                            <li class="list-inline-item"><a href="#"><i class="fab fa-google fa-2x"></i></a></li>
                        </ul>
                        <h6>Secure payment</h6>
                        <ul class="list-inline">
                            <li class="list-inline-item"><img src="img/Icon-VNPAY-QR.webp" alt="Visa" style="max-width: 45px;"></li>
                            <li class="list-inline-item"><img src="img/mastercard.png" alt="Bank" style="max-width: 45px;"></li>
                            <li class="list-inline-item"><img src="img/visa.jpg" alt="Cash" style="max-width: 50px;"></li>
                        </ul>
                    </div>
                </div>

                <hr class="hr_footer">

                <!-- Bottom Copyright -->
                <div class="row copyright">
                    <div class="col-md-12">
                        <p>&copy; Copyright 2024 by Koi Kingdom | 
                            <a href="#" class="text-decoration-none">Terms of Service & Privacy Policy</a>
                        </p>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
