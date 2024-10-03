    Document   : shipHistory
    Created on : Sep 26, 2024, 7:10:01 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/homeForDelivery.css" rel="stylesheet">
        <title>Ship History</title>
    </head>
    <body>
        <jsp:include page="headerForDelivery.jsp" flush="true"/>
        <div class="main" style="margin-top: -216px; margin-left: 223px; margin-right: 30px;">
            <c:if test="${not empty requestScope.koiOrderDetails}">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <h5 class="mb-3">Order Information</h5>
                            <div class="card">
                                <div class="card-body">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">Koi Order Detail ID</th>
                                                <th scope="col">Koi Order ID</th>
                                                <th scope="col">Koi ID</th>
                                                <th scope="col">Farm ID</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="details" items="${requestScope.koiOrderDetails}" varStatus="koiOrderID">
                                                <tr>
                                                    <td>${details.koiOrderDetailID}</td>
                                                    <td>${details.koiOrderID}</td>
                                                    <td>${requestScope.koiNames[koiOrderID.index].koiName}</td>
                                                    <td>${requestScope.farmNames[koiOrderID.index].farmName}</td>

                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <!-- Pricing Information Section -->
                        <div class="col">
                            <h5 class="mb-3">Pricing Information</h5>
                            <div class="card">
                                <div class="card-body">
                                    <table class="table">
                                        <thead>

                                            <tr>

                                                <th scope="col">Quantity</th>
                                                <th scope="col">Unit Price</th>
                                                <th scope="col">Total Price</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="details" items="${requestScope.koiOrderDetails}" varStatus="koiOrderID">
                                                <tr>
                                                    <td>${details.quantity}</td>
                                                    <td>${details.unitPrice}</td>
                                                    <td>${details.totalPrice}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <h5 class="mt-3">Delivery Information</h5>
                        <div class="card">
                            <div class="card-body">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Name Customer</th>
                                            <th scope="col">Delivery Date</th>
                                            <th scope="col">Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>${requestScope.customer.lastName} ${requestScope.customer.firstName}</td> 
                                            <c:forEach var="koiOrderListByOrderList" items="${requestScope.koiOrderListByOrderList}" varStatus="koiOrderID">
                                                <td>${koiOrderListByOrderList.deliveryDate}</td> 
                                                <td>${koiOrderListByOrderList.status}</td>    
                                            </c:forEach>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

            </c:if>
            <c:if test="${empty requestScope.koiOrderDetails}">
                <p class="alert alert-danger">${errorMessage}</p>
            </c:if>
        </div>

    </body>
</html>
