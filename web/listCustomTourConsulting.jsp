<%-- 
    Document   : listCustomTourConsulting
    Created on : Oct 8, 2024, 2:39:19 PM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/homeForDelivery.css" rel="stylesheet">
        <link href="css/toast.css" rel="stylesheet">
        <link href="css/headerForSales.css" rel="stylesheet">
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
    </head>
    <body>
        <jsp:include page="headerForConsulting.jsp" flush="true"/>
        <div style="margin-top: 25vh; margin-left: 17%;" class="main-content">   
            <h1 style="text-align: center">Tour Custom Management</h1>
            <table id="content" class="styled-table">
                <thead>
                    <tr>
                        <th>TourRequest ID</th>
                        <th>CustomerID</th>                       
                        <th>Duration</th>
                        <th>Price</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Departure</th>
                        <th>Farm Name</th>
                        <th>KoiType Name</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty requestScope.CUSTOM_TOUR}">
                            <c:forEach var="custom" items="${requestScope.CUSTOM_TOUR}" varStatus="status">
                                <tr>
                                    <td>${custom.requestID}</td>
                                    <td>${custom.customerID}</td>                                    
                                    <td>${custom.duration}</td>                                   
                                    <td>${custom.quotationPrice}</td>
                                    <td>${custom.startDate}</td>
                                    <td>${custom.endDate}</td> 
                                    <td>${custom.departureLocation}</td> 
                                    <td>${custom.farmName}</td> 
                                    <td>${custom.koiTypeName}</td>
                                    <td style="color: green; font-weight: bold">${custom.status}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="8" class="text-center alert alert-danger">No bookings found.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>                    
        
        <div id="toastBox"></div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/homeForDelivery.js"></script>
        <script src="js/showToast.js"></script>  
    </body>
</html>
