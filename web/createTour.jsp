<%-- 
    Document   : createTour
    Created on : Oct 3, 2024, 11:55:11 PM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Tour</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any"> 
        <link rel="stylesheet" href="css/createTour.css"> 
        <link href="css/toast.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="headerForManager.jsp" flush="true"/> 
       <div style="    margin-top: 25vh;
             margin-left: 17%;
             margin-right: 6%;" class="main-content">     
            <div class="container">
                <h1>Create Tour</h1>
                <c:if test="${not empty CREATE_SUCCESS}">
                    <script>
                        window.onload = function () {
                            showToast('${CREATE_SUCCESS}', 'success');
                        };
                    </script>
                </c:if>
                    
                <c:if test="${not empty errorMessage}">
                    <script>
                        window.onload = function () {
                            showToast('${errorMessage}', 'error');
                        };
                    </script>
                </c:if>

                <div id="toastBox"></div>
                <script src="js/showToast.js"></script>
                <form action="createtour" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="tourName">Tour Name</label>
                        <input type="text" id="tourName" name="tourName" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="duration">Duration</label>
                        <select class="form-select" aria-label="Default select example" id="duration" name="duration" required>
                            <option value="" selected>Choose Duration</option>
                            <option value="2 Days 1 Night">2 Days 1 Night</option>
                            <option value="3 Days 2 Nights">3 Days 2 Nights</option>
                            <option value="4 Days 3 Nights">4 Days 3 Nights</option>
                            <option value="5 Days 4 Nights">5 Days 4 Nights</option>
                            <option value="6 Days 5 Nights">6 Days 5 Nights</option>
                            <option value="7 Days 6 Nights">7 Days 6 Nights</option>
                            <option value="8 Days 7 Nights">8 Days 7 Nights</option>
                            <option value="9 Days 8 Nights">9 Days 8 Nights</option>
                            <option value="10 Days 9 Nights">10 Days 9 Nights</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="description">Description</label>
                        <input type="text" id="description" name="description" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="tourPrice">Tour Price</label>
                        <input type="number" id="tourPrice" name="tourPrice" class="form-control" required min="0" step="0.01">
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
                        <label for="tourImage">Tour Image</label>
                        <input type="file" id="tourImage" name="tourImage" class="form-control" accept="image/*" required>
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
                    <div class="form-group">
                        <label for="consultingID">Consulting</label>
                       
                        <select class="form-select" id="tourConsulting${tour.tourID}" name="consultingID" required>
                            <option value="" selected>Select consulting</option>
                            <c:forEach var="option" items="${sessionScope.CONSULTING}">
                                <option value="${option.employeeID}"> ${option.employeeID} - ${option.lastName}  ${option.firstName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group checkbox-group">
                        <label class="checkbox-group-label">Select Farms</label>
                        <div class="checkbox-grid">
                            <c:forEach var="farm" items="${sessionScope.LIST_FARM}">
                                <div class="checkbox-item">
                                    <input type="checkbox" name="farms" value="${farm.farmID}" id="farm-${farm.farmID}">
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
                                    <input type="checkbox" name="koiTypes" value="${koiType.koiTypeID}" id="koiType-${koiType.koiTypeID}">
                                    <label for="koiType-${koiType.koiTypeID}">${koiType.typeName}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <button type="submit" class="btn-custom-tour">Create Tour</button>
                    
                    <script>
                        // Lấy ngày hiện tại theo định dạng yyyy-mm-dd
                        const today = new Date().toISOString().split("T")[0];

                        // Đặt ngày hiện tại làm giá trị tối thiểu cho các trường chọn ngày
                        document.getElementById("startDate").setAttribute("min", today);
                        document.getElementById("endDate").setAttribute("min", today);
                    </script>
                </form>
            </div>
        </div>
    </body>
</html>
