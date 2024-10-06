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
        <jsp:include page="headerForManager.jsp" flush="true"/>   
        <!-- Nội dung chính -->

        <div class="main" style="margin-top: -216px; margin-left: 223px; margin-right: 30px;">       
            <div class="container">
                <h1>Tour Management</h1>
                 <div class="menu-center">
                    <ul class="nav justify-content-center">
                        <form action="SearchByTourName" method="get" class="search--box"> 
                            <input  type="text" name="txtNameTour" placeholder="Search name tour" value="<%= (request.getParameter("txtNameTour") != null) ? request.getParameter("txtNameTour") : "" %>"  style=" border: none;"/>
                            <button type="submit" style="border: none; border-radius: 4px;">  <i class="fa-solid fa-search"></i> </button>
                        </form>
                    </ul>
                </div>
                <table>
                    <c:if test="${not empty UPDATE_STATUS}">
                        <div class="alert alert-success">
                            ${UPDATE_STATUS}
                        </div>
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
                            <th>Image</th>
                            <th>Status</th>
                            <th>Departure Location</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="SEARCH_TOUR" items="${SEARCH_TOUR}">
                            <tr>
                                <td>${SEARCH_TOUR.tourID}</td>
                                <td>${SEARCH_TOUR.tourName}</td>
                                <td>${SEARCH_TOUR.duration}</td>
                                <td>
                                    <div id="description-${SEARCH_TOUR.tourID}">
                                        <span id="shortDesc-${SEARCH_TOUR.tourID}">
                                            <c:choose>
                                                <c:when test="${fn:length(SEARCH_TOUR.description) > 100}">
                                                    ${fn:substring(SEARCH_TOUR.description, 0, 100)}...
                                                </c:when>
                                                <c:otherwise>
                                                    ${SEARCH_TOUR.description}
                                                </c:otherwise>
                                            </c:choose>
                                        </span>
                                        <span id="dots-${SEARCH_TOUR.tourID}" style="display:none;">...</span>
                                        <span id="moreDesc-${SEARCH_TOUR.tourID}" style="display:none;">
                                            ${SEARCH_TOUR.description}
                                        </span>
                                    </div>
                                    <button onclick="toggleDescription(${SEARCH_TOUR.tourID})" id="toggleBtn-${SEARCH_TOUR.tourID}">
                                        Show more
                                    </button>
                                </td>
                                <td>${SEARCH_TOUR.tourPrice}</td>
                                <td>${SEARCH_TOUR.startDate}</td>
                                <td>${SEARCH_TOUR.endDate}</td>
                                <td><img src="${SEARCH_TOUR.tourImage}" alt="tour-image" height="150px" width="250px" style="border-radius: 20px; object-fit: contain" ></td>

                                <td>
                                    <c:choose>
                                        <c:when test="${SEARCH_TOUR.status}">
                                            <span class="status-active">Active</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="status-inactive">Inactive</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${SEARCH_TOUR.tourDepartLoca}</td>
                                <td>
                                    <a href="updateStatusTour?tourID=${SEARCH_TOUR.tourID}" 
                                       onclick="return confirm('Are you sure you want to change the status?');">
                                        Change Status
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script>
        function toggleDescription(tourID) {
            var dots = document.getElementById("dots-" + tourID);
            var moreText = document.getElementById("moreDesc-" + tourID);
            var shortDesc = document.getElementById("shortDesc-" + tourID);
            var btnText = document.getElementById("toggleBtn-" + tourID);

            if (dots.style.display === "none") {
                // Hiển thị văn bản rút gọn
                dots.style.display = "inline";
                moreText.style.display = "none";
                shortDesc.style.display = "inline";
                btnText.innerHTML = "Show more";
            } else {
                // Hiển thị toàn bộ văn bản
                dots.style.display = "none";
                moreText.style.display = "inline";
                shortDesc.style.display = "none";
                btnText.innerHTML = "Show less";
            }
        }
    </script>
</body>
</html>
