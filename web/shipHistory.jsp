<%--    Document   : shipHistory
    Created on : Sep 26, 2024, 7:10:01 AM
    Author     : Minhngo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Ship History</title>
        
    </head>
    <body>
        <c:choose>
            <c:when test="${userType == 'manage'}">
                <jsp:include page="headerForManager.jsp" flush="true"/>
            </c:when>
            <c:when test="${userType == null}">
                <jsp:include page="headerForDelivery.jsp" flush="true"/>
            </c:when>
        </c:choose>
        <div style="    margin-top: 25vh;
             margin-left: 17%;
             margin-right: 6%;" class="main-content">   
            <c:if test="${not empty requestScope.koiOrderDetails}">
                <div style="width: 100%;">
                    <a href="home?action=Delivery" class="dropdown-item" style="text-align: right;">&lt; Back to Ship History</a>
                </div>

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
                                                <th scope="col">Paid Amount</th>
                                                <th scope="col">Cost Shipping </th>
                                                <th scope="col">Total Price</th>
                                                <th scope="col">Remaining</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="details" items="${requestScope.koiOrderDetails}" varStatus="koiOrderID">
                                            <p></p>
                                            <tr>
                                                <td>${details.quantity}</td>
                                                <td>  <fmt:formatNumber type="currency" currencySymbol="$" value="${details.unitPrice}"/></td>
                                                <td> <fmt:formatNumber type="currency" currencySymbol="$" value="${details.totalPrice * 0.3} "/></td>
                                                <td>  <fmt:formatNumber type="currency" currencySymbol="$" value="${requestScope.koiOrderListByOrderList[koiOrderID.index].costShipping}"/></td>        
                                                <td> <fmt:formatNumber type="currency" currencySymbol="$" value="${details.totalPrice + requestScope.koiOrderListByOrderList[koiOrderID.index].costShipping}"/></td>
                                                <td> <fmt:formatNumber type="currency" currencySymbol="$" value="${details.totalPrice - (details.totalPrice * 0.3) + requestScope.koiOrderListByOrderList[koiOrderID.index].costShipping}"/></td>
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
                                            <th scope="col">Order Date</th>
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
