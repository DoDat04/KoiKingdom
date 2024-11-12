<%-- 
    Document   : manageCanceledTour
    Created on : Nov 12, 2024, 10:55:33 PM
    Author     : Nguyen Huu Khoan
--%>

<%@page import="java.util.List"%>
<%@page import="koikd.tourbookingdetail.TourBookingDetailDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Canceled tour</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
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
            <jsp:param name="searchController" value="searchkoi"/>
        </jsp:include>
        <!-- Nội dung chính -->
        <div style="    margin-top: 25vh;
             margin-left: 17%;
             margin-right: 6%;" class="main-content">     
            <div class="container">
                <h1>Canceled tour</h1>

                <table>


                    <thead>
                        <tr>
                            <th>Tour Booking Detail</th>
                            <th>Customer Name</th>
                            <th>Phone number</th>
                            <th>Tour name</th>
                            <th>Start date</th>
                            <th>End date</th>
                            <th>Total price</th>
                            <th>Cancel at</th>
                            <th>Cancel reason</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="canceltour" items="${canceltour}">
                            <tr>
                                <td>${canceltour.tourBookingDetailID}</td>
                                <td>${canceltour.customerDTO.lastName} ${canceltour.customerDTO.firstName}</td>
                                <td>${canceltour.customerDTO.phoneNumber}</td>
                                <td>${canceltour.tourDTO.tourName}</td>
                                <td><fmt:formatDate value="${canceltour.tourDTO.startDate}" pattern="dd-MM-yyyy" /></td>
                                <td><fmt:formatDate value="${canceltour.tourDTO.endDate}" pattern="dd-MM-yyyy" /></td>
                                <td>${canceltour.totalPrice}</td>
                                <td><fmt:formatDate value="${canceltour.cancelAt}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
                                <td>${canceltour.reasonCancel}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>



            </div>
        </div>
    </div>
</body>
</html>

