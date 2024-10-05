<%-- 
    Document   : getCustomTour
    Created on : Oct 5, 2024, 8:26:16 AM
    Author     : ADMIN LAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Get Custom Tour</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/homeForDelivery.css" rel="stylesheet">
        <link href="css/toast.css" rel="stylesheet">
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
    </head>
    <body>
        <jsp:include page="headerForSales.jsp" flush="true"/>
        <div style="    margin-top: -26vh;
             margin-left: 17%;
             margin-right: 6%" class="main-content">          

            <table id="content" class="styled-table">
                <thead>
                    <tr>
                        <th>Request ID</th>
                        <th>Customer ID</th>
                        <th>Customer Name</th>
                        <th>Duration</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Status</th>
                        <th>Manager Approval Status</th>
                        <th>Departure Location</th>
                        <th>Farm Name</th>
                        <th>KoiType Name</th>
                        <th>Quantity</th>
                        <th>Quotation Price</th>
                        <th>Image</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty sessionScope.CUSTOM_LIST}">
                            <c:forEach var="custom" items="${sessionScope.CUSTOM_LIST}" varStatus="status">
                                <tr>
                                    <td>${custom.requestID}</td>
                                    <td>${custom.customerID}</td>
                                    <td>${custom.custName}</td>
                                    <td>${custom.duration}</td>
                                    <td>${custom.startDate}</td>
                                    <td>${custom.endDate}</td>
                                    <td class="text-warning">${custom.status}</td>
                                    <td class="text-warning">${custom.managerApprovalStatus}</td>
                                    <td>${custom.departureLocation}</td>
                                    <td>${custom.farmName}</td>
                                    <td>${custom.koiTypeName}</td>
                                    <td>${custom.quantity}</td>
                                    <td>${custom.quotationPrice}</td>
                                    <td><img src="${custom.image}" class="custom-tour" alt="" style="height: 100px; width: 150px; border-radius: 10px;"/></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6" class="text-center alert alert-danger">No customs found.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>

        <c:if test="${not empty sessionScope.ERROR_MESSAGE}">
            <script>
                window.onload = function () {
                    showToast('${sessionScope.ERROR_MESSAGE}', 'error');
                };
            </script>
            <c:set var="ERROR_MESSAGE" value="${null}" scope="session"/>
        </c:if>

        <div id="toastBox"></div>
        <script src="js/homeForDelivery.js"></script>
        <script src="js/showToast.js"></script>
    </body>
</html>
z