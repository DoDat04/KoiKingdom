<%-- 
    Document   : myCustomTour
    Created on : Oct 7, 2024, 10:01:51 AM
    Author     : Minhngo
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Custom Tour</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="css/toast.css" rel="stylesheet">
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
    </head>
    <body>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>
        <c:if test="${not empty requestScope.customerTourList}">
            <div style="margin: 20px;">  
                <div class="container mt-5">
                    <h2 class="mb-4">Quotation Information</h2>
                    <table class="table table-bordered table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>Image</th>
                                <th>Departure Location</th>
                                <th>Quotation Price</th>
                                <th>Quantity</th>
                                <th>Duration</th>
                                <th>Start Date</th>
                                <th>End Date</th>
                                <th>Status</th>
                                <th>Details</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="customerTourList" items="${requestScope.customerTourList}">
                                <tr class="customer-tour-row" 
                                    data-requestid="${customerTourList.requestID}" 
                                    data-status="${customerTourList.status}" 
                                    data-managerApprovalStatus="${customerTourList.managerApprovalStatus}"> <!-- Thêm thuộc tính status và managerApprovalStatus -->

                                    <td><img src="${customerTourList.image}" class="custom-tour" alt="" style="height: 100px; width: 150px; border-radius: 10px;"/></td>
                                    <td>${customerTourList.departureLocation}</td>

                                    <td>
                                        <c:choose>
                                            <c:when test="${customerTourList.status == 'Approved'}">
                                                ${customerTourList.quotationPrice}
                                            </c:when>
                                            <c:otherwise>
                                                <span style="color: gray; font-style: italic;">Awaiting Quotation Price</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>

                                    <td>${customerTourList.quantity}</td>
                                    <td>${customerTourList.duration}</td>
                                    <td>${customerTourList.startDate}</td>
                                    <td>${customerTourList.endDate}</td>

                                    <c:choose>
                                        <c:when test="${customerTourList.status == 'Approved'}">
                                            <td style="color: green; font-weight: bold">${customerTourList.status}</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td style="color: brown; font-weight: bold">${customerTourList.status}</td>
                                        </c:otherwise>
                                    </c:choose>

                                    <td>
                                        <form action="my-detail-custom-tour" method="post">
                                            <input type="hidden" name="requestid" value="${customerTourList.requestID}" />
                                            <button type="submit" class="dropdown-item">
                                                Detail
                                            </button>
                                        </form>
                                    </td>

                                    <td>
                                        <c:if test="${customerTourList.status == 'Approved' && customerTourList.managerApprovalStatus == 'Approved'}">
                                            <p style="color: red; font-weight: bold; font-size: 17px">Time left to decide: <span class="decision-timer">300</span></p>
                                            <div class="decision-buttons">
                                                <form action="checkout" method="post">
                                                    <input type="hidden" name="requestid" value="${customerTourList.requestID}" />
                                                    <input type="hidden" name="numberofpeople" value="${customerTourList.quantity}" />
                                                    <button type="submit" class=" btn btn-success mb-2 d-block" style="
                                                            width: 100%;">Check Out
                                                    </button>
                                                </form>
                                                <form action="reject-tour" method="post">
                                                    <input type="hidden" name="requestid" value="${customerTourList.requestID}" />
                                                    <button type="submit" class=" btn btn-danger d-block" style="
                                                            width: 100%;">Reject</button>
                                                </form>
                                            </div>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>

        <script src="js/customTimer.js"></script> 

        <c:if test="${empty requestScope.customerTourList}">
            <p class="alert alert-danger" style="margin-top: 31px">${errorMessage}</p>
        </c:if>
        <script src="js/backToTop.js"></script> 
        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>
