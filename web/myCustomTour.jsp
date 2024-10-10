<%-- 
    Document   : myCustomTour
    Created on : Oct 7, 2024, 10:01:51 AM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <title></title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
    </head>
    <body>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>
        <c:if test="${not empty requestScope.customerTourList}">
            <div style=" margin: 20px;">  
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
                                <tr> 
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
                                    <td><a href="my-detail-custom-tour?requestid=${customerTourList.requestID}">Detail</a></td>
                                    <td>
                                        <c:if test="${customerTourList.status == 'Approved'}">
                                            <a href="checkout?requestid=${customerTourList.requestID}&numberofpeople=${customerTourList.quantity}" class="btn btn-success">Check Out</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </c:if>
        <c:if test="${empty requestScope.customerTourList}">
            <p class="alert alert-danger" style="margin-top: 31px">${errorMessage}</p>
        </c:if>
        <script src="js/backToTop.js"></script> 
        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>
