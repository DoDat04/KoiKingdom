<%-- 
    Document   : orderDetailForCustomer
    Created on : Sep 14, 2024, 7:15:52 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Order Detail</title>
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
                    <div class="header">
                        <div class="order-info">
                            <c:forEach var="koiOrderListByOrderList" items="${requestScope.koiOrderListByOrderList}" varStatus="koiOrderID">
                                <span style="padding-right: 19px;">Order date: ${koiOrderListByOrderList.deliveryDate}</span>
                                <span>Estimated delivery: ${koiOrderListByOrderList.estimatedDelivery}</span>
                                <div>Type: ${koiOrderListByOrderList.type}</div>
                                <div>Address: ${koiOrderListByOrderList.shippingAddress}</div>
                            </c:forEach>
                        </div>

                    </div>
                    <div class="koi-section" style="max-height: 450px; overflow-y: auto;">
                        <c:forEach var="koiOrderDetails" items="${requestScope.koiOrderDetails}" varStatus="koiOrderID">
                            <div style="font-size: 20px;">${requestScope.farmNames[koiOrderID.index].farmName}</div>
                            <div class="koi-item">
                                <div class="koi-row">
                                    <img src="${requestScope.koiNames[koiOrderID.index].image}" alt="Picture of KOI" style="width: 58px"/>
                                    <div class="koi-name" style="position: relative; left: -111px;">${requestScope.koiNames[koiOrderID.index].koiName}
                                        <div>
                                            <span style="font-weight: normal;">
                                                <!-- Hiển thị loại koi -->
                                                <c:forEach var="koiType" items="${requestScope.koiTypeList}">
                                                    <c:if test="${koiType.koiTypeID == koiOrderDetails.koiTypeID}">
                                                        ${koiType.typeName}
                                                    </c:if>
                                                </c:forEach>
                                            </span>
                                        </div>
                                        <div style="font-weight: normal;">
                                            <div>Age: ${requestScope.koiNames[koiOrderID.index].age} years</div>
                                            <div>Length: ${requestScope.koiNames[koiOrderID.index].length} cm</div>
                                            <div>Weight: ${requestScope.koiNames[koiOrderID.index].weight} kg</div>
                                            <div>Price ${requestScope.koiNames[koiOrderID.index].price} $</div>
                                        </div>
                                    </div>
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
                            <c:set var="costShipping" value="10" />

                            <c:if test="${requestScope.koiOrderListByOrderList[koiOrderID.index].type == 'Online'}">
                                <c:set var="paidAmount" value="${totalPriceSum * 0.3}" /> <!-- 30% for Online -->
                                <c:set var="priceKoi" value="${requestScope.koiNames[koiOrderID.index].price}" />
                                <c:set var="remainingAmount" value="${totalPriceSum - paidAmount + 10}" />
                            </c:if>

                            <c:if test="${requestScope.koiOrderListByOrderList[koiOrderID.index].type == 'Offline'}">
                                <c:set var="paidAmount" value="${totalPriceSum * 0.3}" /> <!-- 30% for Offline -->
                                <c:set var="remainingAmount" value="${totalPriceSum - paidAmount + 10}" />
                            </c:if>
                        </c:forEach>
                                
                        <h3>Paid Amount: <span class="price"><fmt:formatNumber type="currency" currencySymbol="$" value="${paidAmount}"/></span></h3>
                        <h3>Cost Shipping: <span class="price"><fmt:formatNumber type="currency" currencySymbol="$" value="10" /></span></h3>                 
                        <h3>Remaining Amount: <span class="price"><fmt:formatNumber type="currency" currencySymbol="$" value="${remainingAmount}"/></span></h3>
                    </div>

                </div>
            </c:if>
        </div>
        <c:if test="${empty requestScope.koiOrderDetails}">
            <p class="alert alert-danger" style="
               margin-top: 15px;">${Error}</p>
        </c:if>
        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>
