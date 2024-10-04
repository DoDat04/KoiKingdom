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
        <div class="main" style="margin-top: -216px; margin-left: 223px; margin-right: 30px;">       
            <div class="container">
                <h1>Create Tour</h1>
                <form action="CreateTourController" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="tourName">Tour Name</label>
                        <input type="text" id="tourName" name="tourName" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="duration">Duration</label>
                        <input type="text" id="duration" name="duration" class="form-control" required>
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
                </form>
            </div>
        </div>
        <c:set var="success" value="${requestScope.CREATE_SUCCESS}"/>
        <c:if test="${not empty success}">
            <script>
                window.onload = function () {
                    showToast('${success}', 'success');
                };
            </script>
        </c:if>
        <script src="js/showToast.js"></script>
    </body>
</html>
