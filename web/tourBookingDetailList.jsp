<%-- 
    Document   : tourBookingDetailList
    Created on : Oct 8, 2024, 1:35:46 AM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/homeForDelivery.css" rel="stylesheet">
        <link href="css/toast.css" rel="stylesheet">
        <link href="css/headerForSales.css" rel="stylesheet">
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
    </head>
    <body>
        <jsp:include page="headerForConsulting.jsp" flush="true"/>
        
        <c:if test="${not empty requestScope.UPDATE_STATUS_TOUR}">
            <script>
                window.onload = function () {
                    showToast('${requestScope.UPDATE_STATUS_TOUR}', 'success');
                };
            </script>   
        </c:if>
        
        <div style="margin-top: 25vh; margin-left: 17%;" class="main-content"> 
            <h1 style="text-align: center">Tour Detail Management</h1>
            <table id="content" class="styled-table">
                <thead>
                    <tr>
                        <th>Tour Booking ID</th>
                        <th>Customer ID</th>
                        <th>Customer Name</th>
                        <th>Tour ID</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                        <th>Total Price</th>
                        <th>Status</th>
                        <th>Tour Type</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty requestScope.TOUR_BOOKING_DETAIL}">
                            <c:forEach var="custom" items="${requestScope.TOUR_BOOKING_DETAIL}" varStatus="status">
                                <tr>
                                    <td>${custom.tourBookingDetailID}</td>
                                    <td>${custom.customerID}</td>
                                    <td>${custom.custName}</td>
                                    <td>${custom.tourID}</td>
                                    <td>${custom.quantity}</td>                                   
                                    <td>${custom.unitPrice}</td>
                                    <td>${custom.totalPrice}</td>
                                    <td style="color: green; font-weight: bold">${custom.status}</td>
                                    <td>${custom.tourType}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${custom.status != 'Completed'}">
                                                <button type="button" class="btn btn-primary mb-2" data-bs-toggle="modal" data-bs-target="#updateModal${custom.tourBookingDetailID}">
                                                    Update
                                                </button> 
                                            </c:when>
                                            <c:otherwise>
                                                <button type="button" class="btn btn-primary mb-2" disabled>
                                                    Update
                                                </button>
                                            </c:otherwise>
                                        </c:choose>                                                                       
                                    </td>     

                                    <!--Modal-->
                            <div class="modal fade" id="updateModal${custom.tourBookingDetailID}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="updateModalLabel${custom.tourBookingDetailID}" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="updateModalLabel${custom.tourBookingDetailID}">Update Status</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form id="updateForm${custom.tourBookingDetailID}" method="post" action="update-status?employeeID=${sessionScope.consultingID}">
                                                <!-- Dropdown to select the status -->
                                                <div class="mb-3">
                                                    <label for="status${custom.tourBookingDetailID}" class="form-label">Status</label>
                                                    <select class="form-control" id="status${custom.tourBookingDetailID}" name="txtStatus" required>
                                                        <option value="Confirmed">Confirmed</option>
                                                        <option value="Completed">Completed</option>
                                                    </select>
                                                </div>
                                                <!-- Hidden input to pass the tour booking ID -->
                                                <input type="hidden" name="txtTourBookingDetailID" value="${custom.tourBookingDetailID}">
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <button type="submit" form="updateForm${custom.tourBookingDetailID}" class="btn btn-primary">Update</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="9" class="text-center alert alert-danger">No Tour Booking Details Found.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>      

        <div id="toastBox"></div>
        <script src="js/homeForDelivery.js"></script>
        <script src="js/showToast.js"></script>
    </body>
</html>
