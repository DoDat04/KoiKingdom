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
        </style>
    </head>
    <body>
        <jsp:include page="headerForManager.jsp" flush="true">
            <jsp:param name="searchController" value="SearchByTourName"/>
        </jsp:include>
        <!-- Nội dung chính -->
        <div style="    margin-top: 25vh;
             margin-left: 17%;
             margin-right: 6%;" class="main-content">     
            <div class="container">
                <h1>Tour Management</h1>

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
                            
                            <th>Status</th>
                            <th>Departure Location</th>
                            <th>Change status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="tour" items="${tour}">
                            <tr>
                                <td>${tour.tourID}</td>
                                <td>${tour.tourName}</td>
                                <td>${tour.duration}</td>
                                <td>
                                    <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal${tour.tourID}">
                                        View detail
                                    </button>
                                </td>
                                <!-- Modal -->
                        <div class="modal fade" id="exampleModal${tour.tourID}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                                    <span class="status-active">Active</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="status-inactive">Inactive</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${tour.tourDepartLoca}</td>
                        <td>
                            <a class="btn btn-primary" href="updateStatusTour?tourID=${tour.tourID}" 
                               onclick="return confirm('Are you sure you want to change the status?');">
                                Change
                            </a>
                        </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:if test="${not empty SEARCH_MESSAGE}">
                    <p style="color: red; text-align: center;">${SEARCH_MESSAGE}</p>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>