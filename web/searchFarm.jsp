<%-- 
    Document   : searchFarm
    Created on : Oct 31, 2024, 3:29:14 AM
    Author     : Nguyen Huu Khoan
--%>
<%@page import="java.util.List"%>
<%@page import="koikd.farm.FarmDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Farm Management</title>
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

        <!-- Menu điều hướng -->
        <!--        <div class="navbar">
                    <a href="home?action=Manager">Home</a>
                    <a href="GetListCustomer">Customer</a>
                    <a href="GetListEmployee">Employee</a>
                    <a href="GetListTour">Tour</a>
                </div>-->
        <jsp:include page="headerForManager.jsp" flush="true">
            <jsp:param name="searchController" value="searchfarm"/>
        </jsp:include>   
        <!-- Nội dung chính -->
        <div style="    margin-top: 25vh;
             margin-left: 17%;
             margin-right: 6%;" class="main-content">      
            <div class="container">
                <h1>Farm Management</h1>

                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Farm name</th>
                            <th>Location</th>
                            <th>Description</th>
                            <th>Image</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="SEARCH_FARM" items="${SEARCH_FARM}">
                            <tr>
                                <td>${SEARCH_FARM.farmID}</td>
                                <td>${SEARCH_FARM.farmName}</td>
                                <td>${SEARCH_FARM.location}</td>
                                <td>${SEARCH_FARM.description}</td>
                                <td><img src="${SEARCH_FARM.image}" width="200px" height="200px" style="border-radius: 10px;"></td>
                                
                                <td>
                                    <c:choose>
                                        <c:when test="${SEARCH_FARM.status}">
                                            <a class="status-active btn btn-success" href="updateStatusFarm?farmID=${SEARCH_FARM.farmID}"
                                               onclick="return confirm('Are you sure you want to change the status?');">
                                                Active
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="status-inactive btn btn-danger" href="updateStatusFarm?farmID=${SEARCH_FARM.farmID}"
                                               onclick="return confirm('Are you sure you want to change the status?');">
                                                Inactive
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <jsp:useBean id="a" class="koikd.customer.CustomerDAO" scope="request"></jsp:useBean>
                    <nav>
                        <ul class="pagination pagination-lg">
                        <c:set var="prevPage" value="${pageIndex - 1}" />
                        <c:set var="nextPage" value="${pageIndex + 1}" />

                        <!-- Nút Back -->
                        <c:if test="${pageIndex > 1}">
                            <li class="page-item">
                                <a class="page-link" href="managefarm?index=${prevPage}">Back</a>
                            </li>
                        </c:if>

                        <!-- Các trang phân trang -->
                        <c:forEach begin="1" end="${numberOfPages}" var="i">
                            <li class="page-item ${pageIndex == i ? 'active' : ''}">
                                <a class="page-link" href="managefarm?index=${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <!-- Nút Next -->
                        <c:if test="${pageIndex < numberOfPages}">
                            <li class="page-item">
                                <a class="page-link" href="managefarm?index=${nextPage}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
                <%-- Thông báo sau khi update status--%>
                    <c:if test="${not empty UPDATE_STATUS}">
                        <script>
                            window.onload = function () {
                                showToast('${UPDATE_STATUS}', 'success');
                            };
                        </script>   
                    </c:if>
                <c:if test="${not empty SEARCH_MESSAGE}">
                    <p style="color: red; text-align: center;">${SEARCH_MESSAGE}</p>
                </c:if>
            </div>
        </div>
    </body>
</html>
