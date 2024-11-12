<%-- 
    Document   : cancelKoiList
    Created on : Nov 12, 2024, 11:10:42 PM
    Author     : ADMIN LAM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>List of Canceled Koi Orders</title>
        <link rel="stylesheet" href="styles.css"> <!-- Optional: Include your CSS file here -->
    </head>
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
    <body>
        <jsp:include page="headerForManager.jsp" flush="true"/>
        <div class="container" style="padding-top: 150px;">
            <h1>List of Canceled Koi Orders</h1>

            <!-- Search Form for Filtering by Customer Name -->
            <form action="list-koi-cancel" method="get" class="search-form d-flex align-items-center gap-3">
                <label for="txtCustomerName" class="form-label mb-0">Customer Name:</label>
                <input type="text" id="txtCustomerName" name="txtCustomerName" 
                       class="form-control" 
                       placeholder="Enter Customer Name" 
                       value="${param.txtCustomerName}">
                <button type="submit" class="btn btn-primary">Search</button>
            </form>


            <hr>

            <!-- Display the List of Canceled Orders -->
            <c:if test="${not empty LIST_CANCEL_KOI}">
                <table border="1" cellpadding="5" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Customer Name</th>
                            <th>Koi Name</th>
                            <th>Cancel Date</th>
                            <th>Reason for Cancellation</th>
                            <th>Shipping Cost</th>
                            <th>Type</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${LIST_CANCEL_KOI}">
                            <tr>
                                <td>${order.customerDTO.firstName} ${order.customerDTO.lastName}</td>
                                <td>${order.koiDTO.koiName}</td>
                                <td>${order.cancelAt}</td>
                                <td>${order.reasonCancel}</td>
                                <td>${order.costShipping}</td>
                                <td>${order.type}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <!-- No records found message -->
            <c:if test="${empty LIST_CANCEL_KOI}">
                <p>No canceled Koi orders found.</p>
            </c:if>
        </div>
    </body>
</html>
