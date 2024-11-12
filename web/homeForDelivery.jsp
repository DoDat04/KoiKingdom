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
        <style>
            .pagination {
                justify-content: center; /* Canh giữa */
                margin-top: 20px; /* Khoảng cách phía trên */
            }

            .pagination .page-item {
                margin: 0 5px; /* Khoảng cách giữa các ô */
            }

            .pagination .page-link {
                padding: 10px 15px; /* Padding cho các ô phân trang */
                border: 1px solid #04AA6D; /* Viền cho các ô */
                border-radius: 5px; /* Bo tròn các góc */
                background-color: #f4f4f9; /* Màu nền cho các ô */
                color: #333; /* Màu chữ */
                transition: background-color 0.3s; /* Hiệu ứng chuyển màu */
            }

            .pagination .page-link:hover {
                background-color: #04AA6D; /* Màu nền khi hover */
                color: white; /* Màu chữ khi hover */
            }

            .pagination .active .page-link {
                background-color: #04AA6D; /* Màu nền cho ô đang hoạt động */
                color: white; /* Màu chữ cho ô đang hoạt động */
                border: 1px solid #04AA6D; /* Viền cho ô đang hoạt động */
            }
        </style>
    </head>

    <body>
        <jsp:include page="headerForDelivery.jsp" flush="true"/>

        <c:if test="${not empty sessionScope.updateSuccessDelivery}">
            <script>
                window.onload = function () {
                    showToast('${sessionScope.updateSuccessDelivery}', 'success');
                };
            </script>
            <c:set var="updateSuccessDelivery" value="${null}" scope="session"/>
        </c:if>

        <div style="margin-top: 25vh; margin-left: 17%; margin-right: 6%;" class="main-content">         
            <table id="content" class="styled-table">
                <thead>
                    <tr>
                        <th>KoiOrderID</th>
                        <th>CustomerID</th>
                        <th>Customer Name</th>
                        <th>Phone Number</th>
                        <th>Estimated Delivery</th>
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
                                    <td>${requestScope.customerPhones[status.index]}</td>
                                    <td>${order.estimatedDelivery}</td>
                                    <td>
                                        <select id="orderStatus_${order.koiOrderID}" 
                                                onchange="checkStatusChange(${order.koiOrderID}, this.value)"
                                                ${order.tempStatus == 'Complete' ? 'disabled' : ''}>
                                            <option value="In Process" ${order.tempStatus == 'In Process'}>In Process</option>
                                            <option value="On-going" ${order.tempStatus == 'On-going' ? 'selected' : ''}>On-going</option>
                                            <option value="Complete" ${order.tempStatus == 'Complete' ? 'selected' : ''}>Complete</option>                                            
                                        </select>
                                    </td>

                            <script>
                                function checkStatusChange(orderID, newStatus) {
                                    if (confirm("Are you sure you want to change the status to '" + newStatus + "'?")) {
                                        updateStatus(orderID, newStatus);

                                        // Delay page reload by 6 seconds only if the status is changed to 'Complete'
                                        if (newStatus === 'Complete') {
                                            setTimeout(function () {
                                                location.reload();
                                            }, 6000);
                                        }
                                    } else {
                                        // Revert to the previous status if the user cancels the action
                                        document.getElementById("orderStatus_" + orderID).value = '${order.tempStatus}';
                                    }
                                }
                            </script>

                            <td style="padding-left: 4%;">
                                <form action="GetKoiOrderDetail" method="POST" style="display:inline;">
                                    <input type="hidden" name="orderID" value="${order.koiOrderID}">
                                    <input type="hidden" name="customerID" value="${order.customerID}">
                                    <button class="btn btn-primary" type="submit" style="
                                            position: relative;
                                            right: 53%;
                                            ">Detail</button>
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
                    <c:choose>
                        <c:when test="${numberOfPages <= 5}">
                            <c:forEach begin="1" end="${numberOfPages}" var="i">
                                <li class="page-item ${pageIndex == i ? 'active' : ''}">
                                    <a class="page-link" href="GetKoiOrder?index=${i}&txtNameCustomer=${param.txtNameCustomer}">${i}</a>
                                </li>
                            </c:forEach>
                        </c:when>

                        <c:otherwise>
                            <!-- JSP comment: Hiển thị dấu ... nếu trang hiện tại lớn hơn 3 -->
                            <c:if test="${pageIndex > 3}">
                                <li class="page-item">
                                    <a class="page-link" href="GetKoiOrder?index=1&txtNameCustomer=${param.txtNameCustomer}">1</a>
                                </li>
                                <li class="page-item disabled"><span class="page-link">...</span></li>
                                </c:if>

                            <!-- JSP comment: Hiển thị 3 trang trước và 2 trang sau trang hiện tại -->
                            <c:forEach begin="${pageIndex > 2 ? pageIndex - 2 : 1}" 
                                       end="${pageIndex + 2 <= numberOfPages ? pageIndex + 2 : numberOfPages}" var="i">
                                <li class="page-item ${pageIndex == i ? 'active' : ''}">
                                    <a class="page-link" href="GetKoiOrder?index=${i}&txtNameCustomer=${param.txtNameCustomer}">${i}</a>
                                </li>
                            </c:forEach>

                            <!-- JSP comment: Hiển thị dấu ... nếu trang hiện tại không gần cuối -->
                            <c:if test="${pageIndex < numberOfPages - 2}">
                                <li class="page-item disabled"><span class="page-link">...</span></li>
                                <li class="page-item">
                                    <a class="page-link" href="GetKoiOrder?index=${numberOfPages}&txtNameCustomer=${param.txtNameCustomer}">${numberOfPages}</a>
                                </li>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
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
