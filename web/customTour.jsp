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
                <input type="number" id="numberOfPeople" name="numberOfPeople" class="form-control" required min="1">
            </div>
            <div class="form-group">
                <label for="departureLocation">Departure Location</label>
                <input type="text" id="departureLocation" name="departureLocation" class="form-control" required>
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


