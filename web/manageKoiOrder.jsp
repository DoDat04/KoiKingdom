<%-- 
    Document   : manageKoiOrder
    Created on : Oct 26, 2024, 4:06:54 PM
    Author     : Minhngo
--%>

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
        <link href="css/toast.css" rel="stylesheet">
        <title>KOI Order Management</title>
        <style>
            /* Tổng quan */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f9;
            }

            h1 {
                text-align: center;
                margin: 20px 0;
                color: #333;
            }

            /* Menu điều hướng */
            .navbar {
                background-color: #333;
                overflow: hidden;
            }

            .navbar a {
                float: left;
                display: block;
                color: #f2f2f2;
                text-align: center;
                padding: 14px 20px;
                text-decoration: none;
                font-size: 17px;
            }

            .navbar a:hover {
                background-color: #ddd;
                color: black;
            }

            .navbar a.active {
                background-color: #04AA6D;
                color: white;
            }

            /* Container */
            .container {
                padding: 20px;
                margin: auto;
                max-width: 1200px;
                background-color: #fff;
                box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
                margin-top: 20px;
            }

            /* Bảng khách hàng */
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            table, th, td {
                border: 1px solid #ddd;
            }

            th, td {
                padding: 12px;
                text-align: left;
            }

            th {
                background-color: #04AA6D;
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tr:hover {
                background-color: #ddd;
            }

            /* Footer */
            .footer {
                background-color: #333;
                color: white;
                padding: 10px 0;
                text-align: center;
                position: fixed;
                bottom: 0;
                width: 100%;
            }

            .status-active {
                color: green;
                font-weight: bold;
            }

            .status-inactive {
                color: red;
                font-weight: bold;
            }
            /* Pagination */
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
        <jsp:include page="headerForManager.jsp" flush="true">
            <jsp:param name="searchController" value="GetKoiOrder"/>
        </jsp:include>

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
                        <th>Delivery</th>
                        <th>Assign</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty requestScope.koiList}">
                            <c:forEach var="order" items="${requestScope.koiList}" varStatus="koiOrderID">
                                <tr>
                                    <td>${order.koiOrderID}</td>
                                    <td>${order.customerID}</td>
                                    <td>${requestScope.customerNames[koiOrderID.index]}</td>
                                    <td>${order.deliveryDate}</td>
                                    <td>${order.tempStatus}</td>

                                    <td style="padding-left: 4%;">
                                        <form action="GetKoiOrderDetail" method="GET" style="display:inline;">
                                            <input type="hidden" name="orderID" value="${order.koiOrderID}">
                                            <input type="hidden" name="customerID" value="${order.customerID}">
                                            <input type="hidden" name="userType" value="manage">
                                            <button class="btn btn-primary" type="submit" style="
                                                    position: relative;
                                                    right: 51%;
                                                    ">Detail</button>
                                        </form>
                                    </td>
                                    <td>${requestScope.employeeNames[koiOrderID.index]}</td>
                                    <td>
                                        <button class="btn btn-primary" type="button" class="btn btn-primary dropdown-item" data-bs-toggle="modal" data-bs-target="#assignModal" onclick="loadDeliveryEmployees(${order.koiOrderID}, ${order.deliveryBy})">
                                            Assign to
                                        </button>
                                    </td>
                            <script>
                                var selectedEmployeeId = ''; // Biến toàn cục để lưu employeeId được chọn

                                function loadDeliveryEmployees(koiOrderID, employeeId) {
                                    selectedEmployeeId = employeeId; // Cập nhật selectedEmployeeId từ tham số

                                    $.ajax({
                                        url: '/KoiKingdom/GetListDeliveryEmployee',
                                        type: 'GET',
                                        success: function (response) {
                                            console.log('Response received:', response);

                                            // Bắt đầu HTML của form
                                            var formHtml = '<form action="assignToDelivery" method="post" enctype="multipart/form-data" id="assignEmployeeForm">' +
                                                    '<label for="employeeSelect">Select Employee:</label>' +
                                                    '<select name="employeeId" id="employeeSelect" class="form-select" onchange="updateFormAction(' + koiOrderID + ')">' +
                                                    '<option value="">-- Select an Employee --</option>'; // Tùy chọn mặc định
                                            console.log('id đã chọn: ' + selectedEmployeeId);
                                            // Kiểm tra nếu response là mảng và có dữ liệu nhân viên
                                            if (Array.isArray(response) && response.length > 0) {
                                                response.forEach(function (employee) {

                                                    console.log('id chưa chọn: ' + employee.employeeID);
                                                    // Kiểm tra xem employeeID có bằng với selectedEmployeeId không
                                                    var selectedAttr = (selectedEmployeeId == employee.employeeID) ? ' selected' : '';
                                                    formHtml += '<option value="' + employee.employeeID + '"' + selectedAttr + '>' +
                                                            employee.firstName + ' ' + employee.lastName +
                                                            '</option>';
                                                });

                                                // Đóng thẻ select và form
                                                formHtml += '</select></form>';

                                                // Chèn toàn bộ HTML của form vào phần tử đích
                                                $('#assignEmployeeContainer').html(formHtml);

                                                // Thiết lập action với employeeId đã chọn
                                                updateFormAction(koiOrderID);
                                                // Hiển thị modal sau khi form đã được thêm vào
                                                $('#assignModal').modal('show');
                                            } else {
                                                alert('No delivery employees found.');
                                            }
                                        },
                                        error: function (xhr, status, error) {
                                            console.error('Error fetching delivery employee data:', error);
                                            alert('Could not fetch delivery employees. Please try again.');
                                        }
                                    });
                                }

                                // Hàm cập nhật action của form với `employeeId` và `koiOrderID`
                                function updateFormAction(koiOrderID) {
                                    // Lấy giá trị employeeId được chọn
                                    var selectedEmployeeId = $('#employeeSelect').val();
                                    console.log('id đã chọn123: ' + selectedEmployeeId);

                                    // Nếu không có employeeId được chọn, action sẽ không có tham số employeeId
                                    if (selectedEmployeeId) {
                                        $('#assignEmployeeForm').attr('action', 'assignToDelivery?employeeId=' + selectedEmployeeId + '&koiOrderId=' + koiOrderID + '&userType=manage');
                                    } else {
                                        $('#assignEmployeeForm').attr('action', 'assignToDelivery?koiOrderId=' + koiOrderID + '&userType=manage'); // Không có employeeId
                                    }
                                }
                            </script>
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


            <div class="modal fade" id="assignModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel">Assign to Delivery Employee</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div id="assignEmployeeContainer"></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" form="assignEmployeeForm" class="btn btn-primary">Assign</button>
                        </div>
                    </div>
                </div>
            </div>


            <jsp:useBean id="a" class="koikd.order.KoiOrderDAO" scope="request"></jsp:useBean>
                <nav>
                    <ul class="pagination pagination-lg">
                    <c:choose>
                        <c:when test="${numberOfPages <= 5}">
                            <c:forEach begin="1" end="${numberOfPages}" var="i">
                                <li class="page-item ${pageIndex == i ? 'active' : ''}">
                                    <a class="page-link" href="GetKoiOrder?index=${i}&userType=manage&txtSearchValue=${param.txtSearchValue}">${i}</a>
                                </li>
                            </c:forEach>
                        </c:when>

                        <c:otherwise>
                            <!-- JSP comment: Hiển thị dấu ... nếu trang hiện tại lớn hơn 3 -->
                            <c:if test="${pageIndex > 3}">
                                <li class="page-item">
                                    <a class="page-link" href="GetKoiOrder?index=1&userType=manage&txtSearchValue=${param.txtSearchValue}">1</a>
                                </li>
                                <li class="page-item disabled"><span class="page-link">...</span></li>
                                </c:if>

                            <!-- JSP comment: Hiển thị 3 trang trước và 2 trang sau trang hiện tại -->
                            <c:forEach begin="${pageIndex > 2 ? pageIndex - 2 : 1}" 
                                       end="${pageIndex + 2 <= numberOfPages ? pageIndex + 2 : numberOfPages}" var="i">
                                <li class="page-item ${pageIndex == i ? 'active' : ''}">
                                    <a class="page-link" href="GetKoiOrder?index=${i}&userType=manage&txtSearchValue=${param.txtSearchValue}">${i}</a>
                                </li>
                            </c:forEach>

                            <!-- JSP comment: Hiển thị dấu ... nếu trang hiện tại không gần cuối -->
                            <c:if test="${pageIndex < numberOfPages - 2}">
                                <li class="page-item disabled"><span class="page-link">...</span></li>
                                <li class="page-item">
                                    <a class="page-link" href="GetKoiOrder?index=${numberOfPages}&userType=manage&txtSearchValue=${param.txtSearchValue}">${numberOfPages}</a>
                                </li>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>

        </div>
                
        <c:if test="${not empty successAssign}">
            <script>
                window.onload = function () {
                    showToast('${successAssign}', 'success');
                };
            </script>
        </c:if>
    </body>
</html>
