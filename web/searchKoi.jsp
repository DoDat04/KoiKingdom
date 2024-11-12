<%-- 
    Document   : searchKoi
    Created on : Nov 12, 2024, 8:47:34 PM
    Author     : Nguyen Huu Khoan
--%>

<%@page import="java.util.List"%>
<%@page import="koikd.tour.TourDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Koi Management</title>
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
                <h1>Koi Management</h1>

                <table>

                    <%-- Thông báo sau khi update tour  --%>
                    <c:if test="${not empty message}">
                        <script>
                            window.onload = function () {
                                showToast('${message}', 'success');
                            };
                        </script>   
                    </c:if>
                    <%-- Thông báo search --%>
                    <c:if test="${not empty SEARCH_MESSAGE}">
                        <p style="color: red; text-align: center;">${SEARCH_MESSAGE}</p>
                    </c:if>
                    <thead>
                        <tr>
                            <th>Koi ID</th>
                            <th>Koi Name</th>
                            <th>Age</th>
                            <th>Length</th>
                            <th>Weight</th>
                            <th>Price</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="SEARCH_KOI" items="${SEARCH_KOI}">
                            <tr>
                                <td>${SEARCH_KOI.koiID}</td>
                                <td>${SEARCH_KOI.koiName}</td>
                                <td>${SEARCH_KOI.age}</td>
                                <td>${SEARCH_KOI.length}</td>
                                <td>${SEARCH_KOI.weight}</td>
                                <td>${SEARCH_KOI.price}</td>
                                <td>
                                    <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#updateModal${SEARCH_KOI.koiID}">
                                        Update
                                    </button>

                                </td>
                                <!-- modal update tour -->
                        <div class="modal fade" id="updateModal${SEARCH_KOI.koiID}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="exampleModalLabel">Update koi</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <!-- Form to update tour details -->
                                        <form id="updateKoiForm${SEARCH_KOI.koiID}" action="updatekoiprice" method="POST" onsubmit="return confirmUpdate();">
                                            <!-- Thẻ label chỉ có tác dụng hiển thị văn bản và liên kết với cái input phía dưới nó thông qua for
                                            For của label khác với name của input
                                            For là trùng với id của input có tác dụng là
                                            Ví dụ khi người dùng nháy chuột vào dòng chữ TourID thì con trỏ chuột sẽ trỏ đến ô nhập liệu input của tourID luôn
                                            Còn name trong input thì là để đẩy dữ liệu qua controller
                                            -->
                                            <!-- Tour ID (Readonly) -->
                                            <div class="mb-3">
                                                <label for="tourID${SEARCH_KOI.koiID}" class="form-label">Tour ID</label>
                                                <input type="text" class="form-control" id="koiID${SEARCH_KOI.koiID}" name="koiID" value="${SEARCH_KOI.koiID}" readonly>
                                            </div>
                                            
                                            <!-- Price -->
                                            <div class="mb-3">
                                                <label for="koiPrice${SEARCH_KOI.koiID}" class="form-label">Price</label>
                                                <input type="number" class="form-control" id="koiPrice${SEARCH_KOI.koiID}" name="price" value="${SEARCH_KOI.price}">
                                            </div>
                                            

                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <button type="submit"  class="btn btn-primary" data-bs-toggle="modal" >Update Tour</button>
                                            </div>
                                        </form>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <script>
                            function confirmUpdate() {
                                return confirm('Are you sure you want to update this tour?');
                            }
                        </script>
                        
                        <!-- hết phần modal update tour -->



                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <!-- phân trang -->
                <jsp:useBean id="a" class="koikd.tour.TourDAO" scope="request"></jsp:useBean>

                    <nav>
                        <ul class="pagination pagination-lg">
                            <!-- Thiết lập trang trước và trang sau -->
                        <c:set var="prevPage" value="${pageIndex - 1}" />
                        <c:set var="nextPage" value="${pageIndex + 1}" />

                        <!-- Nút Back: chỉ hiển thị khi pageIndex > 1 -->
                        <c:if test="${pageIndex > 1}">
                            <li class="page-item">
                                <a class="page-link" href="searchkoi?index=${prevPage}&txtSearchValue=${param.txtSearchValue}">Back</a>
                            </li>
                        </c:if>

                        <!-- Hiển thị các trang phân trang -->
                        <c:forEach begin="1" end="${numberOfPages}" var="i">
                            <li class="page-item ${pageIndex == i ? 'active' : ''}">
                                <a class="page-link" href="searchkoi?index=${i}&txtSearchValue=${param.txtSearchValue}">${i}</a>
                            </li>
                        </c:forEach>

                        <!-- Nút Next: chỉ hiển thị khi pageIndex < totalPage -->
                        <c:if test="${pageIndex < numberOfPages}">
                            <li class="page-item">
                                <a class="page-link" href="searchkoi?index=${nextPage}&txtSearchValue=${param.txtSearchValue}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>

            </div>
        </div>
    </div>
</body>
</html>

