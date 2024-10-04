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
            .form-group.checkbox-group {
                margin-top: 20px;
            }

            .checkbox-grid {
                display: flex;
                flex-wrap: wrap;
                gap: 10px; /* Thêm khoảng cách giữa các ô */
            }

            .checkbox-item {
                display: flex;
                align-items: center;
                gap: 10px;
                width: calc(33.333% - 10px);
                padding: 5px 0;
            }

            .checkbox-item label {
                margin-left: 0;
            }
            .alert {
                margin: 20px auto;
                width: 80%;
                max-width: 600px;
            }
        </style>
    </head>
    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>

        <h1>Custom Tour Request</h1>

        <!-- Display success or error message -->
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>

        <form action="create-customtour" method="post">
            <div class="form-group">
                <label for="duration">Full Name</label>
                <input type="text" id="fullName" name="fullName" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="duration">Duration</label>
                <input type="text" id="duration" name="duration" class="form-control" required placeholder="Example: 4 Days 3 Nights">
            </div>
            <div class="form-group">
                <label for="startDate">Start Date</label>
                <input type="date" id="startDate" name="startDate" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="endDate">End Date</label>
                <input type="date" id="endDate" name="endDate" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="quantity">Number of People</label>
                <input type="number" id="quantity" name="quantity" class="form-control" required min="1" max="50">
            </div>
            <div class="form-group">
                <label for="departureLocation">Departure Location</label>
                <select class="form-select" aria-label="Default select example" id="departureLocation" name="departureLocation" required>
                    <option value="" selected>Choose Departure Location</option>
                    <option value="Hà Nội">Hà Nội</option>
                    <option value="Hồ Chí Minh">Hồ Chí Minh</option>
                    <option value="Đà Nẵng">Đà Nẵng</option>
                    <option value="Cam Ranh">Cam Ranh</option>
                    <option value="Phú Quốc">Phú Quốc</option>
                    <option value="Hải Phòng">Hải Phòng</option>
                    <option value="Cần Thơ">Cần Thơ</option>
                    <option value="Quảng Ninh">Quảng Ninh</option>
                    <option value="Thanh Hóa">Thanh Hóa</option>
                </select>
            </div>
            <div class="form-group checkbox-group">
                <label class="checkbox-group-label">Select Farms</label>
                <div class="checkbox-grid">
                    <c:forEach var="farm" items="${sessionScope.LIST_FARM}">
                        <div class="checkbox-item">
                            <input type="checkbox" name="farms" value="${farm.farmName}" id="farm-${farm.farmID}">
                            <label for="farm-${farm.farmID}">${farm.farmName}</label>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="form-group checkbox-group">
                <label class="checkbox-group-label">Select Koi Types</label>
                <div class="checkbox-grid">
                    <c:forEach var="koiType" items="${sessionScope.LIST_KOITYPE}">
                        <div class="checkbox-item">
                            <input type="checkbox" name="koiTypes" value="${koiType.typeName}" id="koiType-${koiType.koiTypeID}">
                            <label for="koiType-${koiType.koiTypeID}">${koiType.typeName}</label>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <button type="submit" id="submitTourBtn">Submit Tour Request</button>
            <script>
                document.getElementById("submitTourBtn").addEventListener("click", function (event) {
                    // Kiểm tra nếu người dùng chưa đăng nhập
                    const isUserLoggedIn = "${sessionScope.LOGIN_USER}" || "${sessionScope.LOGIN_GMAIL}";
                    if (!isUserLoggedIn) {
                        alert('You need to login to custom tour!');
                        event.preventDefault(); // Ngăn chặn gửi form
                    } 
                });
            </script>
        </form>
        <button id="backToTop" class="btn btn-primary" style="display: none;">
            <i class="fas fa-angle-up"></i>
        </button>

        <script src="js/backToTop.js"></script> 
        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>



