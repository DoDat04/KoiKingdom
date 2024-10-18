<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
        <link href="css/homeForDelivery.css" rel="stylesheet">
        <link href="css/toast.css" rel="stylesheet">
        <title>Delivery Home</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
    </head>

    <body>
        <jsp:include page="headerForDelivery.jsp" flush="true"/>
        <div style="margin-top: 25vh; margin-left: 17%; margin-right: 6%;" class="main-content">         

            <table id="content" class="styled-table">
                <thead>
                    <tr>
                        <th>KoiOrderID</th>
                        <th>CustomerID</th>
                        <th>Customer Name</th>
                        <th>Delivery Date</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty requestScope.koiList}">
                            <c:forEach var="order" items="${requestScope.koiList}" varStatus="status">
                                <tr>
                                    <td>${order.koiOrderID}</td>
                                    <td>${order.customerID}</td>
                                    <td>${requestScope.customerNames[status.index]}</td>
                                    <td>${order.deliveryDate}</td>
                                    <td>
                                        <select id="orderStatus_${order.koiOrderID}" onchange="updateStatus(${order.koiOrderID}, this.value)">
                                            <option value="true" ${order.status ? 'selected' : ''}>Complete</option>
                                            <option value="false" ${!order.status ? 'selected' : ''}>On-going</option>
                                        </select>
                                    </td>
                                    <td style="padding-left: 4%;">
                                        <form action="GetKoiOrderDetail" method="GET" style="display:inline;">
                                            <input type="hidden" name="orderID" value="${order.koiOrderID}">
                                            <input type="hidden" name="customerID" value="${order.customerID}">
                                            <button class="btn-detail" type="submit" style="border: none; background: none;">Detail</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6" class="text-center alert alert-danger">No orders found.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>

            <jsp:useBean id="a" class="koikd.order.KoiOrderDAO" scope="request"></jsp:useBean>
                <nav class="navbar fixed-bottom justify-content-center">
                    <ul class="pagination pagination-lg">
                    <c:forEach begin="1" end="${numberOfPages}" var="i">
                        <li class="page-item ${pageIndex == i ? 'active' : ''}">
                            <a class="page-link" href="GetKoiOrder?index=${i}&txtNameCustomer=${param.txtNameCustomer}">${i}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>

        </div>

        <script>
            function updateStatus(koiOrderID, status) {
                $.ajax({
                    url: "/KoiKingdom/updatestatuskoiorder", // Change this to your servlet's URL
                    type: "POST",
                    data: {
                        koiOrderID: koiOrderID,
                        status: status
                    },
                    success: function (response) {
                        showToast('Order status updated!', 'success'); // Notify user
                    },
                    error: function (xhr) {
                        console.error("Error occurred while updating order status:", xhr);
                        showToast('Failed to update order status.', 'error'); // Notify user
                    }
                });
            }
        </script>

        <c:if test="${not empty sessionScope.updateSuccess}">
            <script defer>
                document.addEventListener('DOMContentLoaded', function () {
                    showToast('${success}', 'success');
                });
            </script>
            <c:set var="updateSuccess" value="${null}" scope="session"/>
        </c:if>

        <c:if test="${not empty sessionScope.updateError}">
            <script>
                window.onload = function () {
                    showToast('${sessionScope.updateError}', 'error');
                };
            </script>
            <c:set var="updateError" value="${null}" scope="session"/>
        </c:if>

        <div id="toastBox"></div>
        <script src="js/homeForDelivery.js"></script>
        <script src="js/showToast.js"></script>
    </body>
</html>
