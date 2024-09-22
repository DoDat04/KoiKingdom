<%-- 
    Document   : trackingProduct
    Created on : Sep 21, 2024, 2:31:05 PM
    Author     : Minhngo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="css/orderDetailDelivery.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="body-rectangle3 row" style=" margin: 0px 0; background-color: #d3c3c3">
            <div class="tracking-number">
                <h3>Tracking number</h3>
                <p>#85425456665425</p>
            </div>

            <div class="package-status">
                <h3>Package Status:</h3>
                <ul>
                    <li><i class="fas fa-check-circle"></i> Courier Requested <span>20/02/2021 17:53PM</span></li>
                    <li><i class="fas fa-check-circle"></i> Package picked-up <span>20/02/2021 17:53PM</span></li>
                    <li><i class="fas fa-check-circle"></i> In transit <span>20/02/2021 17:53PM</span></li>
                    <li><i class="fas fa-check-circle"></i> Package delivered <span>20/02/2021 17:53PM</span></li>
                </ul>
            </div>

            <div class="total-cost">
                <p>Total Cost: <span>$78.00</span></p>
            </div>

            <div class="payment-btn">
                <button>PAYMENT</button>
            </div>

        </div>

    </body>
</html>
