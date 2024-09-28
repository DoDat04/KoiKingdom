<%-- 
    Document   : homeForCustomer
    Created on : Sep 14, 2024, 7:15:52 AM
    Author     : ADMIN LAM
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">

        <link rel="stylesheet" href="css/orderDetailForCustomer.css">  
    </head>

    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>

        <div class="container-fluid" id="detail" style="padding-top: 90px; padding-bottom: 90px; display: flex; gap: 20px; padding-left: 57px; padding-right: 57px;">
            <c:if test="${not empty requestScope.koiOrderDetails}">
                <div class="order-details">

                    <div class="koi-section" style="max-height: 450px; overflow-y: auto;">
                        <c:forEach var="koiOrderDetails" items="${requestScope.koiOrderDetails}" varStatus="koiOrderID">
                            <div class="header">
                                <div class="order-info">
                                    <span style="padding-right: 19px;">Order date: ${requestScope.myOrders[koiOrderID.index].deliveryDate}</span>
                                    <span>Estimated delivery: ${requestScope.myOrders[koiOrderID.index].estimatedDelivery}</span>
                                </div>
                                <div>Address: ${requestScope.customer.address}</div>
                            </div>
                            <div style="font-size: 20px;">${requestScope.farmNames[koiOrderID.index].farmName}</div>
                            <div class="koi-item">
                                <div class="koi-row">
                                    <img src="${requestScope.koiNames[koiOrderID.index].image}" alt="Picture of KOI" style="width: 58px"/>
                                    <div class="koi-name">${requestScope.koiNames[koiOrderID.index].koiName}</div>
                                    <div>
                                        <div class="koi-price price">
                                            <fmt:formatNumber type="currency" currencySymbol="$" value="${requestScope.koiNames[koiOrderID.index].price}"/>
                                        </div>
                                        <div class="item-koi">${koiOrderDetails.quantity} items</div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>



                <div class="sidebar">
                    <div>
                        <h2 style="font-size: 40px;">Pricing Information</h2>

                        <c:forEach var="koiOrderDetails" items="${requestScope.koiOrderDetails}" varStatus="koiOrderID">
                            <div style="margin-bottom: 10px;">
                                <div>Item ${koiOrderID.index + 1}: <span class="price"><fmt:formatNumber type="currency" currencySymbol="$" value="${koiOrderDetails.totalPrice}"/>  </span></div>
                            </div>
                        </c:forEach>


                        <c:set var="totalPriceSum" value="0" /> <!-- Khởi tạo biến tổng giá -->
                        <c:forEach var="koiOrderDetails" items="${requestScope.koiOrderDetails}" varStatus="koiOrderID">
                            <c:set var="totalPriceSum" value="${totalPriceSum + koiOrderDetails.totalPrice}" />
                        </c:forEach>
                        <h2>Total: <span class="price"><fmt:formatNumber type="currency" currencySymbol="$" value="${totalPriceSum}"/></span></h2>

                    </div>

                    <!-- Di chuyển Payment Methods xuống đây -->
                    <div>
                        <h2 style="font-size: 40px;">Payment Methods</h2>
                        <ul style="list-style-type: none; padding: 0;">
                            <li><i class="fa fa-credit-card" aria-hidden="true"></i> Credit Card</li>
                            <li><i class="fa-brands fa-paypal"></i> PayPal</li>
                            <li><i class="fa-solid fa-money-bill"></i> Cash on Delivery</li>
                        </ul>
                    </div>
                </div>
            </c:if>
        </div>
        <c:if test="${empty requestScope.koiOrderDetails}">
            <p class="alert alert-danger">${Error}</p>
        </c:if>
        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>
