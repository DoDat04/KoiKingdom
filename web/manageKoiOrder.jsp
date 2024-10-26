<%-- 
    Document   : manageKoiOrder
    Created on : Oct 26, 2024, 4:06:54 PM
    Author     : Admin
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
    </body>
</html>
