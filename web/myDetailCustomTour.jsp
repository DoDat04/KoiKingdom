<%-- 
    Document   : myDetailCustomTour
    Created on : Oct 7, 2024, 3:46:34 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
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
    <style>
        body {
            background-color: #f5f5f5;
            font-family: Arial, sans-serif; /* Changed font for a cleaner look */
        }
        .tour-header {
            background-color: #007bff;
            color: white;
            padding: 40px; /* Increased padding for a bolder header */
            text-align: center;
            border-radius: 10px;
            margin-bottom: 30px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2); /* Added shadow for depth */
        }
        .tour-detail {
            padding: 30px; /* Increased padding for more spacious feel */
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
        }
        h1, h4 {
            color: #007bff; /* Unified header color */
        }
        h4 {
            margin-top: 1.5rem; /* Consistent spacing */
            border-bottom: 2px solid #e0e0e0; /* Underline for better emphasis */
            padding-bottom: 10px; /* Space below headings */
        }
        .list-group-item {
            border: none;
            border-bottom: 1px solid #f0f0f0;
            padding: 12px; /* Increased padding for list items */
        }
        .description {
            background-color: #f9f9f9; /* Light background for description */
            padding: 20px; /* Padding for description area */
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1); /* Shadow for depth */
            margin-top: 20px; /* Space above description */
        }
        .container {
            max-width: 900px; /* Set a max width for better readability */
        }
    </style>
    <body>


        <jsp:include page="headerForCustomer.jsp" flush="true"/>
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
                                    <td>${requestScope.customerTourDetail.duration}</td>
                                    <td>${requestScope.customerTourDetail.quotationPrice}</td>
                                    <td>${requestScope.customerTourDetail.startDate}</td>
                                    <td>${requestScope.customerTourDetail.endDate}</td>
                                    <td>${requestScope.customerTourDetail.departureLocation}</td>
                                    <td>${requestScope.customerTourDetail.quantity}</td>
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
