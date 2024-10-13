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
            <c:if test="${not empty requestScope.koiOrderDetails}">
                <c:forEach var="koiOrderDetails" items="${requestScope.koiOrderDetails}" varStatus="koiOrderID">
                    <!-- Kiểm tra lần đầu hoặc khi koiOrderID khác so với lần trước -->
                    <c:if test="${koiOrderID.first || koiOrderDetails.koiOrderID != prevKoiOrderID}">
                        <!-- Đóng thẻ div trước đó nếu không phải là dòng đầu tiên -->
                        <c:if test="${not koiOrderID.first}">
                        </div>
                    </div>
                </c:if>

                <div class="container-fluid" id="detail" >
                    <c:forEach var="myOrder" items="${requestScope.myOrder}">
                        <c:if test="${koiOrderDetails.koiOrderID != myOrder.koiOrderID}">
                            <div style="font-size: 18px;  margin-top: 92px; margin-left: 92px">
                                Ngày giao: ${myOrder.deliveryDate}
                            </div>
                        </c:if>
                    </c:forEach>
                </div>

                <!-- Bắt đầu div mới cho nhóm koiOrderID mới -->
                <div style="border: 1px solid #ccc; border-radius: 10px; margin-bottom: 20px; width: 1117px; margin: 0 auto; ">

                </c:if>

                <!-- Thông tin chi tiết đơn hàng -->
                <div class="order-details" style="display: flex; justify-content: center;">
                    <div class="koi-section" style="padding: 20px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); width: 909px">
                        <div style="font-size: 20px; margin-bottom: 17px;">
                            ${requestScope.farmNames[koiOrderID.index].farmName}
                        </div>
                        <a href="MyOrderDetail?koiOrderID=${koiOrderDetails.koiOrderID}&customerID=${requestScope.customerNames.customerID}" style="text-decoration: none; color: black">
                            <div class="koi-item">
                                <div class="koi-row" style="display: flex; align-items: center;">
                                    <img src="${requestScope.koiNames[koiOrderID.index].image}" alt="Picture of KOI" style="width: 58px; margin-right: 10px;"/>
                                    <div class="koi-name" style="margin-right: 454px;">
                                        ${requestScope.koiNames[koiOrderID.index].koiName}
                                        <span style="font-weight: normal;">
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
                                            <fmt:formatNumber type="currency" currencySymbol="$" value="${requestScope.koiNames[koiOrderID.index].price}"/> 
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

                <!-- Cập nhật giá trị prevKoiOrderID để so sánh với nhóm tiếp theo -->
                <c:set var="prevKoiOrderID" value="${koiOrderDetails.koiOrderID}"/>

                <!-- Đóng nhóm khi đến dòng cuối cùng -->
                <c:if test="${koiOrderID.last}">
                </div> 
            </div>
        </c:if>
    </c:forEach>

</c:if>



<c:if test="${empty requestScope.koiOrderDetails}">
    <p class="alert alert-danger" style="
       margin-top: 15px;">${Error}</p>
</c:if>
</div>

<jsp:include page="footer.jsp" flush="true"/>
</body>
</html>
