<%-- 
    Document   : myOrderForCustomer
    Created on : Sep 27, 2024, 6:40:00 PM
    Author     : Minhngo
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Order KOI- Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/myOrderForCustomer.css">  
    </head>
    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>

        <div>
            <!-- Kiểm tra nếu có dữ liệu đơn hàng koi -->
            <c:if test="${not empty requestScope.koiOrderDetails}">
                <!-- Biến để lưu trữ ngày giao hàng trước đó -->
                <c:set var="prevDeliveryDate" value="" />

                <!-- Duyệt qua danh sách đơn hàng -->
                <c:forEach var="koiOrderDetails" items="${requestScope.koiOrderDetails}" varStatus="koiOrderID">
                    <!-- Xác định deliveryDate từ myOrder -->
                    <c:set var="deliveryDate" value="${requestScope.myOrder[koiOrderID.index].deliveryDate}" />

                    <!-- Kiểm tra nếu ngày giao hàng khác với ngày trước đó -->
                    <c:if test="${deliveryDate != prevDeliveryDate}">
                        <!-- Nếu có, in ra ngày giao hàng -->
                        <div style="font-size: 20px; margin-bottom: 10px; margin-left: 30px; margin-top: 30px; color: #555; padding: 10px;">
                            Order Date: ${deliveryDate}
                        </div>
                        <!-- Cập nhật ngày giao hàng trước đó -->
                        <c:set var="prevDeliveryDate" value="${deliveryDate}" />
                    </c:if>

                    <!-- Thông tin chi tiết đơn hàng -->
                    <div class="order-details" style="display: flex; justify-content: center;">
                        <div class="koi-section" style="padding: 20px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); width: 909px">
                            <!-- Hiển thị tên trang trại -->
                            <div style="font-size: 20px; margin-bottom: 17px;">
                                ${requestScope.farmNames[koiOrderID.index].farmName}
                            </div>

                            <a href="MyOrderDetail?koiOrderID=${koiOrderDetails.koiOrderID}&customerID=${requestScope.customerNames.customerID}" style="text-decoration: none; color: black">
                                <div class="koi-item">
                                    <div class="koi-row" style="display: flex; align-items: center;">
                                        <img src="${requestScope.koiNames[koiOrderID.index].image}" alt="Picture of KOI" style="width: 58px; margin-right: 10px;" />
                                        <div class="koi-name" style="margin-right: 400px;">
                                            ${requestScope.koiNames[koiOrderID.index].koiName}
                                            <span style="font-weight: normal;">
                                                <!-- Hiển thị loại koi -->
                                                <c:set var="koiTypeDisplayed" value="false" />
                                                <c:forEach var="koiType" items="${requestScope.koiType}">
                                                    <c:if test="${koiType.koiTypeID == requestScope.koiNames[koiOrderID.index].koiTypeID}">
                                                        <c:if test="${not koiTypeDisplayed}">
                                                            ${koiType.typeName}
                                                            <c:set var="koiTypeDisplayed" value="true" />
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>
                                            </span>
                                        </div>

                                        <div>
                                            <div class="koi-price price">
                                                <fmt:formatNumber type="currency" currencySymbol="$" value="${requestScope.koiNames[koiOrderID.index].price}" />
                                            </div>
                                            <div class="item-koi">
                                                ${koiOrderDetails.quantity} items
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div> 
                    </div> 
                </c:forEach>
            </c:if>

            <!-- Thông báo khi không có dữ liệu đơn hàng -->
            <c:if test="${empty requestScope.koiOrderDetails}">
                <p class="alert alert-danger" style="margin-top: 15px;">${Error}</p>
            </c:if>
        </div>

        <!-- Back to Top Button -->
        <button id="backToTop" class="btn btn-primary" style="display: none;">
            <i class="fas fa-angle-up"></i>
        </button>

        <script src="js/backToTop.js"></script>

        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>


