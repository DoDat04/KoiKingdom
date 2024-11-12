<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.text.DecimalFormat" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Kết quả thanh toán VNPay">
        <meta name="author" content="Do Dat">
        <title>Payment Results</title>
        <!-- Bootstrap core CSS -->
        <script src="js/load.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="/vnpay_jsp/assets/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f9f9f9;
                margin: 0;
                padding: 0;
            }

            .mail-notification {
                margin: 20px auto;
                text-align: center;
                color: #5cb85c;
                font-size: 18px;
                font-weight: bold;
            }

            #notification {
                display: none;
                margin: 40px auto;
                max-width: 500px;
                padding: 20px;
                text-align: center;
                background-color: #ffffff;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            }

            #notification svg {
                width: 60px; /* Tăng kích thước */
                height: 60px; /* Tăng kích thước */
                margin-bottom: 20px;
            }

            #notification h2 {
                font-size: 22px;
                color: #333;
                margin-top: 10px;
            }

            .back-to-home {
                display: inline-block;
                margin: 30px auto;
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                font-size: 16px;
                font-weight: bold;
                border-radius: 5px;
                text-decoration: none;
                transition: background-color 0.3s ease;
            }

            .back-to-home:hover {
                background-color: #0056b3;
            }

            footer {
                margin-top: 50px;
                text-align: center;
                color: #888;
                font-size: 14px;
            }
        </style>
    </head>

    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true" />
        <c:set var="mail_message" value="${sessionScope.mail_message}" />
        <c:set var="message" value="${sessionScope.VNPay_message}" />
        <c:set var="notificationSvg" value="${sessionScope.notificationSvg}" />

        <div class="mail-notification">
            <p>${mail_message}</p>
        </div>

        <!-- Notification Box -->
        <div id="notification" class="notification">
            <div class="svg-container">${notificationSvg}</div>
            <h2>${message}</h2>
        </div>

        <script>
            // Function to show the notification
            function showNotification() {
                document.getElementById("notification").style.display = "block";
            }

            // Show the notification when the page loads
            window.onload = function () {
                showNotification();
            };
        </script>
        <jsp:include page="footer.jsp" flush="true" />
    </body>

</html>
