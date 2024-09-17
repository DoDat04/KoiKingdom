<%-- 
    Document   : headerForCustomer
    Created on : Sep 14, 2024, 7:21:01 AM
    Author     : ADMIN LAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Footer</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/header.css">
    </head>
    <body>
        <header class="text-black py-4" style="background-color: antiquewhite;">
            <div class="container">
                <div class="d-flex justify-content-between align-items-center">
                    <!-- Logo -->
                    <div class="logo">
                        <a href="MainController?action=homePage">
                            <img src="img/logo.png" style="width: 150px; height: 150px;">
                        </a>
                    </div>

                    <!-- Center Menu (Home, Booking, Contact, Information) -->
                    <div class="menu-center">
                        <ul class="nav justify-content-center">
                            <li class="nav-item">
                                <a class="nav-link text-black" href="#">Home</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle text-black" id="bookingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Booking</a>
                                <ul class="dropdown-menu" aria-labelledby="bookingDropdown">
                                    <li><a class="dropdown-item" href="#">Tour</a></li>
                                    <li><a class="dropdown-item" href="#">Custom Tour</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-black" href="#">Services</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-black dropdown-toggle" id="informationDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Information</a>
                                <ul class="dropdown-menu" aria-labelledby="informationDropdown">
                                    <li><a class="dropdown-item" href="#">Koi Fish</a></li>
                                    <li><a class="dropdown-item" href="#">Farms</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-black" href="#">Contact</a>
                            </li>
                        </ul>
                    </div>

                    <!-- Right Menu (Add to Cart, Account) -->
                    <div class="menu-right d-flex align-items-center">
                        <a href="#" class="text-black me-3 icon-size">
                            <i class="fas fa-shopping-cart"></i>
                        </a>
                        <a href="#" class="text-black me-3 icon-size">
                            <i class="fa-solid fa-heart"></i>
                        </a>
                        <a href="#" class="text-black icon-size nav-link dropdown-toggle" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fas fa-user"></i>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="userDropdown">
                            <li><a class="dropdown-item" href="#">Order History</a></li>
                            <li><a class="dropdown-item" href="#">Notification</a></li>
                            <li><a class="dropdown-item" href="#">Setting</a></li>
                            <li><a class="dropdown-item" href="#">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>

        </header>
    </body>
</html>
