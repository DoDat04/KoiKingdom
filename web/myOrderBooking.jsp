<%-- 
    Document   : myOrderBooking
    Created on : Oct 2, 2024, 9:20:36 PM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/myOrderBooking.css"/>
        <title>My Booking - Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
    </head>
    <style>

    </style>

</style>
<body>
    <div class="colorlib-loader"></div>
    <jsp:include page="headerForCustomer.jsp" flush="true"/>       
    <div style=" margin: 20px;">  
        <c:if test="${not empty requestScope.orders}">
            <c:forEach var="orders" items="${requestScope.orders}" varStatus="tourBookingDetailID">                
                <div class="order_booking" style="display: flex; justify-content: center; margin-bottom: 20px;">
                    <div class="order_booking-section card" style="width: 100%; max-width: 900px;">
                        <div class="card-body">
                            <a href="tour-detail?tourID=${requestScope.tours[tourBookingDetailID.index].tourID}" style="text-decoration: none; color: black;">
                                <div class="d-flex align-items-center justify-content-between mb-3">
                                    <div class="d-flex align-items-center">
                                        <img src="${requestScope.tours[tourBookingDetailID.index].tourImage}" alt="Picture of TOUR" class="rounded" style="width: 80px; height: 80px; object-fit: cover; margin-right: 15px;"/>
                                        <div class="order_booking-name" style="font-size: 18px; font-weight: 600; color: #333; margin-right: 20px;">
                                            ${requestScope.tours[tourBookingDetailID.index].tourName}
                                            <!-- Display the number of people and price per person -->
                                            <div style="font-size: 16px; font-weight: 500; color: #666; margin-top: 5px;">
                                                <span style="font-weight: bold; color: #ff6f61;">
                                                    ${orders.quantity} people x 
                                                    <fmt:formatNumber type="currency" currencySymbol="$" value="${orders.unitPrice}"/>
                                                </span>
                                            </div>
                                        </div>

                                        <div class="order_booking-price" style="font-size: 16px; font-weight: 500; color: #666;">

                                        </div>
                                    </div>

                                    <div style="text-align: right;">
                                        <div class="order_booking-price" style="font-size: 16px; font-weight: bold; color: #333;">
                                            Total: <fmt:formatNumber type="currency" currencySymbol="$" value="${orders.totalPrice}"/>
                                        </div>

                                        <!-- Order Status -->
                                        <div class="order_booking-status" style="font-size: 16px; margin-top: 5px; padding: 5px; border-radius: 4px;">
                                            <span class="status-label">Status:</span> 
                                            <span class="status-value">${orders.status}</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>                         
            </c:forEach>

        </c:if>

        <c:if test="${empty requestScope.orders}">
                <p class="alert alert-danger">${Error}</p>
            </c:if>
    </div>  

    <jsp:include page="footer.jsp" flush="true"/>
</body>

</html>
