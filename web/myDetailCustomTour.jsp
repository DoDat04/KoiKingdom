<%-- 
    Document   : myDetailCustomTour
    Created on : Oct 7, 2024, 3:46:34 PM
    Author     : Minhngo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Detail Custom Tour</title>
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
        <link rel="stylesheet" href="css/myDetailCustomTour.css"/>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
    </head>
    <style>

    </style>
    <body>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>
        <c:choose>
            <c:when test="${sessionScope.LOGIN_USER != null}">
                <div class="text-end my-4 backToTour">
                    <a href="my-custom-tour?customerID=${sessionScope.LOGIN_USER.customerID}" class="btn btn-link">&lt; Back to Custom Tour List</a>
                </div>
            </c:when>
            <c:otherwise>  
                <div class="text-end my-4 backToTour">
                    <form action="my-custom-tour" method="post">
                        <input type="hidden" name="customerID" value="${sessionScope.custID}" />
                        <button type="submit" class="dropdown-item">&lt; Back to Custom Tour List
                        </button>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
        <c:if test="${not empty requestScope.customerTourDetail}">
            <div class="container mt-5">
                <div class="row">
                    <!-- Tour Details -->
                    <div class="col-md-12">
                        <!-- Tour Information Section -->
                        <div class="tour-detail section mb-4">
                            <h4 class="mb-3">Tour Information</h4>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Duration</th>
                                        <th>Quotation Price</th>
                                        <th>Start Date</th>
                                        <th>End Date</th>
                                        <th>Departure Location</th>
                                        <th>Quantity</th>
                                    </tr>
                                </thead>
                                <tbody>                     
                                    <tr>
                                        <td>${customerTourDetail.duration}</td>
                                        <td>${customerTourDetail.quotationPrice}</td>
                                        <td>${customerTourDetail.startDate}</td>
                                        <td>${customerTourDetail.endDate}</td>
                                        <td>${customerTourDetail.departureLocation}</td>
                                        <td>${customerTourDetail.quantity}</td>
                                    </tr>     
                                </tbody>
                            </table>
                        </div>



                        <!-- Row for Farm Name and Koi Type Sections -->
                        <div class="row mb-4">
                            <!-- Farm Name Section -->
                            <div class="col-md-6">
                                <div class="tour-detail section">
                                    <h4 class="mt-4">Farm Name:</h4>
                                    <c:forEach var="customerTourDetail" items="requestScope.customerTourDetail">  
                                        <ul class="list-group">
                                            <li class="list-group-item">
                                                ${fn:replace(requestScope.customerTourDetail.farmName, ',', '<hr/> ')}
                                            </li>
                                        </ul>
                                    </c:forEach>   
                                </div>
                            </div>

                            <!-- Koi Type Section -->
                            <div class="col-md-6">
                                <div class="tour-detail section">
                                    <h4 class="mt-4">Koi Type:</h4>
                                    <ul class="list-group">
                                        <li class="list-group-item">
                                            ${fn:replace(requestScope.customerTourDetail.koiTypeName, ',', '<hr/> ')}
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${empty requestScope.customerTourDetail}">
            <p class="alert alert-danger">${errorMessage}</p>
        </c:if>
        <script src="js/backToTop.js"></script>
        <jsp:include page="footer.jsp" flush="true"/>
    </body>

</html>
