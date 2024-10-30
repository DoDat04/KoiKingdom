<%-- 
    Document   : searchTour
    Created on : Oct 6, 2024, 3:59:26 PM
    Author     : Nguyen Huu Khoan
--%>

<%@page import="java.util.List"%>
<%@page import="koikd.tour.TourDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                        <c:forEach var="SEARCH_TOUR" items="${SEARCH_TOUR}">
                            <tr>
                                <td>${SEARCH_TOUR.tourID}</td>
                                <td>${SEARCH_TOUR.tourName}</td>
                                <td>${SEARCH_TOUR.duration}</td>
                                <td>
                                    <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal${SEARCH_TOUR.tourID}">
                                        View detail
                                    </button>
                                </td>
                                <!-- Modal -->
                        <div class="modal fade" id="exampleModal${SEARCH_TOUR.tourID}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="exampleModalLabel">Descriptions</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        ${SEARCH_TOUR.description}
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <td>${SEARCH_TOUR.tourPrice}</td>
                        <td><fmt:formatDate value="${SEARCH_TOUR.startDate}" pattern="dd-MM-yyyy" /></td>
                        <td><fmt:formatDate value="${SEARCH_TOUR.endDate}" pattern="dd-MM-yyyy" /></td>
<!--                        <td><img src="${SEARCH_TOUR.tourImage}" alt="tour-image" height="150px" width="250px" style="border-radius: 20px; object-fit: contain" ></td>-->

                        
                        <td>
                            <c:choose>
                                <c:when test="${SEARCH_TOUR.status}">
                                    <a class="status-active btn btn-success" href="updateStatusTour?tourID=${SEARCH_TOUR.tourID}"
                                       onclick="return confirm('Are you sure you want to change the status?');">
                                        Active
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a class="status-inactive btn btn-danger" href="updateStatusTour?tourID=${SEARCH_TOUR.tourID}"
                                       onclick="return confirm('Are you sure you want to change the status?');">
                                        Inactive
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${SEARCH_TOUR.tourDepartLoca}</td>
                        <td>${SEARCH_TOUR.consultingID}</td>
                        <td>
                            <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#updateModal${SEARCH_TOUR.tourID}">
                                Update
                            </button>

                        </td>
                        <!-- modal update tour -->
                        <div class="modal fade" id="updateModal${SEARCH_TOUR.tourID}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="exampleModalLabel">Update tour</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <!-- Form to update tour details -->
                                        <form id="updateTourForm${SEARCH_TOUR.tourID}" action="updatetour" method="POST" onsubmit="return confirmUpdate();">
                                            <!-- Thẻ label chỉ có tác dụng hiển thị văn bản và liên kết với cái input phía dưới nó thông qua for
                                            For của label khác với name của input
                                            For là trùng với id của input có tác dụng là
                                            Ví dụ khi người dùng nháy chuột vào dòng chữ TourID thì con trỏ chuột sẽ trỏ đến ô nhập liệu input của tourID luôn
                                            Còn name trong input thì là để đẩy dữ liệu qua controller
                                            -->
                                            <!-- Tour ID (Readonly) -->
                                            <div class="mb-3">
                                                <label for="tourID${SEARCH_TOUR.tourID}" class="form-label">Tour ID</label>
                                                <input type="text" class="form-control" id="tourID${SEARCH_TOUR.tourID}" name="tourID" value="${SEARCH_TOUR.tourID}" readonly>
                                            </div>
                                            <!-- Tour Name -->
                                            <div class="mb-3">
                                                <label for="tourName${SEARCH_TOUR.tourID}" class="form-label">Tour Name</label>
                                                <input type="text" class="form-control" id="tourName${SEARCH_TOUR.tourID}" name="tourName" value="${SEARCH_TOUR.tourName}">
                                            </div>
                                            <!-- Duration -->
                                            <div class="mb-3">
                                                <label for="tourDuration${SEARCH_TOUR.tourID}" class="form-label">Duration</label>
                                                <input type="text" class="form-control" id="tourDuration${SEARCH_TOUR.tourID}" name="duration" value="${SEARCH_TOUR.duration}">
                                            </div>
                                            <!-- Description -->
                                            <div class="mb-3">
                                                <label for="tourDescription${SEARCH_TOUR.tourID}" class="form-label">Description</label>
                                                <textarea class="form-control" id="tourDescription${SEARCH_TOUR.tourID}" name="description">${SEARCH_TOUR.description}</textarea>
                                            </div>
                                            <!-- Price -->
                                            <div class="mb-3">
                                                <label for="tourPrice${SEARCH_TOUR.tourID}" class="form-label">Price</label>
                                                <input type="number" class="form-control" id="tourPrice${SEARCH_TOUR.tourID}" name="tourPrice" value="${SEARCH_TOUR.tourPrice}">
                                            </div>
                                            <!-- Start Date -->
                                            <div class="mb-3">
                                                <label for="tourStartDate${SEARCH_TOUR.tourID}" class="form-label">Start Date</label>
                                                <input type="date" class="form-control" id="tourStartDate${SEARCH_TOUR.tourID}" name="startDate" 
                                                       value="<c:out value='${fn:substring(SEARCH_TOUR.startDate, 0, 10)}'/>"> <!-- Lấy định dạng yyyy-MM-dd -->
                                            </div>
                                            <!-- End Date -->
                                            <div class="mb-3">
                                                <label for="tourEndDate${SEARCH_TOUR.tourID}" class="form-label">End Date</label>
                                                <input type="date" class="form-control" id="tourEndDate${SEARCH_TOUR.tourID}" name="endDate" 
                                                       value="<c:out value='${fn:substring(SEARCH_TOUR.endDate, 0, 10)}'/>"> <!-- Lấy định dạng yyyy-MM-dd -->
                                            </div>


                                            <!-- Location -->
                                            <div class="mb-3">
                                                <label for="tourLocation${SEARCH_TOUR.tourID}" class="form-label">Location</label>
                                                <input type="text" class="form-control" id="tourLocation${SEARCH_TOUR.tourID}" name="departureLocation" value="${SEARCH_TOUR.tourDepartLoca}">
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
                <jsp:useBean id="a" class="koikd.tour.TourDAO" scope="request"></jsp:useBean>

                    <nav>
                        <ul class="pagination pagination-lg">
                            <!-- Thiết lập trang trước và trang sau -->
                        <c:set var="prevPage" value="${pageIndex - 1}" />
                        <c:set var="nextPage" value="${pageIndex + 1}" />

                        <!-- Nút Back: chỉ hiển thị khi pageIndex > 1 -->
                        <c:if test="${pageIndex > 1}">
                            <li class="page-item">
                                <a class="page-link" href="searchtour?index=${prevPage}&txtSearchValue=${param.txtSearchValue}">Back</a>
                            </li>
                        </c:if>

                        <!-- Hiển thị các trang phân trang -->
                        <c:forEach begin="1" end="${numberOfPages}" var="i">
                            <li class="page-item ${pageIndex == i ? 'active' : ''}">
                                <a class="page-link" href="searchtour?index=${i}&txtSearchValue=${param.txtSearchValue}">${i}</a>
                            </li>
                        </c:forEach>

                        <!-- Nút Next: chỉ hiển thị khi pageIndex < totalPage -->
                        <c:if test="${pageIndex < numberOfPages}">
                            <li class="page-item">
                                <a class="page-link" href="searchtour?index=${nextPage}&txtSearchValue=${param.txtSearchValue}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
                
            </div>
        </div>
    </div>
    
</body>
</html>
