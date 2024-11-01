<%-- 
    Document   : manageTour
    Created on : Sep 26, 2024, 3:25:30 PM
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
        <title>Tour Management</title>
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
            <jsp:param name="searchController" value="searchtour"/>
        </jsp:include>
        <!-- Nội dung chính -->
        <div style="    margin-top: 25vh;
             margin-left: 17%;
             margin-right: 6%;" class="main-content">     
            <div class="container">
                <h1>Tour Management</h1>

                <table>
                    <%-- Thông báo sau khi update status--%>
                    <c:if test="${not empty UPDATE_STATUS}">
                        <script>
                            window.onload = function () {
                                showToast('${UPDATE_STATUS}', 'success');
                            };
                        </script>   
                    </c:if>
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
                            <th>Tour ID</th>
                            <th>Tour Name</th>
                            <th>Duration</th>
                            <th>Description</th>
                            <th>Tour Price</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Status</th>
                            <th>Departure Location</th>
                            <th>Consulting</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="tour" items="${tour}">
                            <tr>
                                <td>${tour.tourID}</td>
                                <td>${tour.tourName}</td>
                                <td>${tour.duration}</td>
                                <td>
                                    <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#viewDetailModal${tour.tourID}">
                                        View detail
                                    </button>
                                </td>
                                <!-- Modal của description -->
                        <div class="modal fade" id="viewDetailModal${tour.tourID}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="exampleModalLabel">Descriptions</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        ${tour.description}
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <td>${tour.tourPrice}</td>
                        <td><fmt:formatDate value="${tour.startDate}" pattern="dd-MM-yyyy" /></td>
                        <td><fmt:formatDate value="${tour.endDate}" pattern="dd-MM-yyyy" /></td>

<!--                        <td style="width: 200px"><img src="${tour.tourImage}" alt="tour-image" height="100px" width="100%" style="border-radius: 20px; object-fit: contain" ></td>-->

                        <td>
                            <c:choose>
                                <c:when test="${tour.status}">
                                    <a class="status-active btn btn-success" href="updateStatusTour?tourID=${tour.tourID}"
                                       onclick="return confirm('Are you sure you want to change the status?');">
                                        Active
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a class="status-inactive btn btn-danger" href="updateStatusTour?tourID=${tour.tourID}"
                                       onclick="return confirm('Are you sure you want to change the status?');">
                                        Inactive
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${tour.tourDepartLoca}</td>
                        <td>${tour.consultingID}</td>
                        <td>
                            <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#updateModal${tour.tourID}">
                                Update
                            </button>

                        </td>
                        <!-- modal update tour -->
                        <div class="modal fade" id="updateModal${tour.tourID}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="exampleModalLabel">Update tour</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <!-- Form to update tour details -->
                                        <form id="updateTourForm${tour.tourID}" action="updatetour" method="POST" onsubmit="return confirmUpdate();">
                                            <!-- Thẻ label chỉ có tác dụng hiển thị văn bản và liên kết với cái input phía dưới nó thông qua for
                                            For của label khác với name của input
                                            For là trùng với id của input có tác dụng là
                                            Ví dụ khi người dùng nháy chuột vào dòng chữ TourID thì con trỏ chuột sẽ trỏ đến ô nhập liệu input của tourID luôn
                                            Còn name trong input thì là để đẩy dữ liệu qua controller
                                            -->
                                            <!-- Tour ID (Readonly) -->
                                            <div class="mb-3">
                                                <label for="tourID${tour.tourID}" class="form-label">Tour ID</label>
                                                <input type="text" class="form-control" id="tourID${tour.tourID}" name="tourID" value="${tour.tourID}" readonly>
                                            </div>
                                            <!-- Tour Name -->
                                            <div class="mb-3">
                                                <label for="tourName${tour.tourID}" class="form-label">Tour Name</label>
                                                <input type="text" class="form-control" id="tourName${tour.tourID}" name="tourName" value="${tour.tourName}">
                                            </div>
                                            <!-- Duration -->
                                            <div class="mb-3">
                                                <label for="tourDuration${tour.tourID}" class="form-label">Duration</label>
                                                <input type="text" class="form-control" id="tourDuration${tour.tourID}" name="duration" value="${tour.duration}">
                                            </div>
                                            <!-- Description -->
                                            <div class="mb-3">
                                                <label for="tourDescription${tour.tourID}" class="form-label">Description</label>
                                                <textarea class="form-control" id="tourDescription${tour.tourID}" name="description">${tour.description}</textarea>
                                            </div>
                                            <!-- Price -->
                                            <div class="mb-3">
                                                <label for="tourPrice${tour.tourID}" class="form-label">Price</label>
                                                <input type="number" class="form-control" id="tourPrice${tour.tourID}" name="tourPrice" value="${tour.tourPrice}">
                                            </div>
                                            <!-- Start Date -->
                                            <div class="mb-3">
                                                <label for="tourStartDate${tour.tourID}" class="form-label">Start Date</label>
                                                <input type="date" class="form-control" id="tourStartDate${tour.tourID}" name="startDate" 
                                                       value="<c:out value='${fn:substring(tour.startDate, 0, 10)}'/>"> <!-- Lấy định dạng yyyy-MM-dd -->
                                            </div>
                                            <!-- End Date -->
                                            <div class="mb-3">
                                                <label for="tourEndDate${tour.tourID}" class="form-label">End Date</label>
                                                <input type="date" class="form-control" id="tourEndDate${tour.tourID}" name="endDate" 
                                                       value="<c:out value='${fn:substring(tour.endDate, 0, 10)}'/>"> <!-- Lấy định dạng yyyy-MM-dd -->
                                            </div>


                                            <!-- Location -->
                                            <div class="mb-3">
                                                <label for="tourLocation${tour.tourID}" class="form-label">Location</label>
                                                <input type="text" class="form-control" id="tourLocation${tour.tourID}" name="departureLocation" value="${tour.tourDepartLoca}">
                                            </div>
                                            
                                            <!-- Consulting -->
                                            <div class="mb-3">
                                                <label for="tourConsulting${tour.tourID}" class="form-label">Consulting</label>
                                                <select class="form-select" id="tourConsulting${tour.tourID}" name="consultingID">
                                                    <c:forEach var="option" items="${sessionScope.CONSULTING}">
                                                        <option value="${option.employeeID}" ${option.employeeID == tour.consultingID ? 'selected' : ''}>
                                                            ${option.employeeID} - ${option.lastName} ${option.firstName}
                                                        </option>
                                                    </c:forEach>
                                                </select>
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
                        <c:set var="prevPage" value="${pageIndex - 1}" />
                        <c:set var="nextPage" value="${pageIndex + 1}" />

                        <!-- Nút Back -->
                        <c:if test="${pageIndex > 1}">
                            <li class="page-item">
                                <a class="page-link" href="managetour?index=${prevPage}">Back</a>
                            </li>
                        </c:if>

                        <!-- Các trang phân trang -->
                        <c:forEach begin="1" end="${numberOfPages}" var="i">
                            <li class="page-item ${pageIndex == i ? 'active' : ''}">
                                <a class="page-link" href="managetour?index=${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <!-- Nút Next -->
                        <c:if test="${pageIndex < numberOfPages}">
                            <li class="page-item">
                                <a class="page-link" href="managetour?index=${nextPage}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>

            </div>
        </div>
    </div>
</body>
</html>