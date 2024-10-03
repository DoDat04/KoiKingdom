<%-- 
    Document   : customTour
    Created on : Sep 30, 2024, 6:06:51 PM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Custom Tour</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link rel="stylesheet" href="css/style.css">
        <script src="js/load.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
            }
            h1 {
                color: #343a40;
                text-align: center;
                margin: 20px 0;
            }
            form {
                max-width: 600px;
                margin: auto;
                padding: 30px;
                background: #ffffff;
                border-radius: 8px;
                box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            }
            .form-group {
                margin-bottom: 20px;
            }
            .form-group label {
                font-weight: bold;
                margin-bottom: 10px;
            }
            button {
                background-color: #007bff;
                color: white;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                font-size: 16px;
                width: 100%;
            }
            button:hover {
                background-color: #0056b3;
            }
            .checkbox-group {
                margin-bottom: 10px;
            }
            .checkbox-group input[type="checkbox"] {
                margin-right: 10px;
            }
        </style>
    </head>
    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>

        <h1>Custom Tour Request</h1>

        <form action="submitTourRequest.jsp" method="post">
            <div class="form-group">
                <label for="startDate">Start Date</label>
                <input type="datetime-local" id="startDate" name="startDate" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="endDate">End Date</label>
                <input type="datetime-local" id="endDate" name="endDate" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="numberOfPeople">Number of People</label>
                <input type="number" id="numberOfPeople" name="numberOfPeople" class="form-control" required min="1" max="50">
            </div>
            <div class="form-group">
                <label for="departureLocation">Departure Location</label>
                <select class="form-select" aria-label="Default select example" id="departureLocation" name="departureLocation" required="">
                    <option selected>Noi Bai International Airport (Hanoi)</option>
                    <option value="1">Tan Son Nhat International Airport (Ho Chi Minh City)</option>
                    <option value="2">Da Nang International Airport (Da Nang)</option>
                    <option value="3">Cam Ranh International Airport (Nha Trang)</option>
                    <option value="4">Phu Quoc International Airport (Phu Quoc)</option>
                    <option value="5">Cat Bi International Airport (Hai Phong)</option>
                    <option value="6">Can Tho International Airport (Can Tho)</option>
                    <option value="7">Van Don International Airport (Quang Ninh)</option>
                    <option value="8">Tho Xuan International Airport (Thanh Hoa)</option>
                </select>

            </div>
            <div class="form-group checkbox-group">
                <label>Select Farms</label><br>
                <c:forEach var="farm" items="${sessionScope.LIST_FARM}">
                    <input type="checkbox" name="farms" value="${farm.farmID}" id="farm-${farm.farmID}">
                    <label for="farm-${farm.farmID}">${farm.farmName}</label><br>
                </c:forEach>
            </div>
            <div class="form-group checkbox-group">
                <label>Select Koi Types</label><br>
                <c:forEach var="koiType" items="${sessionScope.LIST_KOITYPE}">
                    <input type="checkbox" name="koiTypes" value="${koiType.koiTypeID}" id="koiType-${koiType.koiTypeID}">
                    <label for="koiType-${koiType.koiTypeID}">${koiType.typeName}</label><br>
                </c:forEach>
            </div>
            <button type="submit">Submit Tour Request</button>
        </form>

        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>


