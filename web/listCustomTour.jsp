<%-- 
    Document   : listCustomTour
    Created on : Oct 7, 2024, 12:30:18 AM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Get Custom Tour</title>
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
        <jsp:include page="headerForManager.jsp" flush="true"/>
        <div style="    margin-top: 25vh;
             margin-left: 17%;
             margin-right: 6%;" class="main-content">          
            <table id="content" class="styled-table">
                <thead>
                    <tr>
                        <th>Customer Name</th>
                        <th>Duration</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Departure Location</th>
                        <th>Farm Name</th>
                        <th>KoiType Name</th>
                        <th>Quantity</th>
                        <th>Quotation Price</th>
                        <th>Image</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty requestScope.CUSTOM_LIST}">
                            <c:forEach var="custom" items="${requestScope.CUSTOM_LIST}" varStatus="status">
                                <tr>
                                    <td>${custom.custName}</td>
                                    <td>${custom.duration}</td>
                                    <td>${custom.startDate}</td>
                                    <td>${custom.endDate}</td>                                   
                                    <td>${custom.departureLocation}</td>
                                    <td>${custom.farmName}</td>
                                    <td>${custom.koiTypeName}</td>
                                    <td>${custom.quantity}</td>
                                    <td>$${custom.quotationPrice}</td>
                                    <td>
                                        <img src="${custom.image}" class="custom-tour" alt="" style="height: 100px; width: 150px; border-radius: 10px;"/>
                                    </td>
                                    <td>
                                        <form action="ManagerCustomActionController" method="post">
                                            <input type="hidden" name="customId" value="${custom.requestID}" />
                                            <c:choose>
                                                <c:when test="${custom.managerApprovalStatus != 'Approved' && custom.managerApprovalStatus != 'Rejected'}">
                                                    <button type="submit" name="action" value="approve" class="btn btn-success" style="margin-bottom: 10px;">Approve</button>
                                                    <button type="submit" name="action" value="reject" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#updateModal${custom.requestID}">Reject</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="submit" name="action" value="approve" class="btn btn-success" disabled style="margin-bottom: 10px;">Approve</button>
                                                    <button type="submit" name="action" value="reject" class="btn btn-danger" disabled="">Reject</button>
                                                </c:otherwise>
                                            </c:choose>                                          
                                        </form>
                                    </td>

                                    <!--Modal-->
                            <div class="modal fade" id="updateModal${custom.requestID}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="updateModalLabel${custom.requestID}" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="updateModalLabel${custom.requestID}">Update Section</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form id="updateForm${custom.requestID}" method="post" action="update-price">
                                                <div class="mb-3">
                                                    <label for="quotationPrice${custom.requestID}" class="form-label">Quotation Price</label>
                                                    <input type="number" step="0.01" class="form-control" id="quotationPrice${custom.requestID}" name="txtQuoPrice" placeholder="Enter new quotation price" required>
                                                </div>
                                                <input type="hidden" name="txtReq" value="${custom.requestID}">
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <button type="submit" form="updateForm${custom.requestID}" class="btn btn-primary">Update</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="modal fade" id="sendModal${custom.requestID}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Send</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            Are you sure about that?
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                            <form action="sales-to-manager" method="POST">
                                                <input type="hidden" name="txtReqID" value="${custom.requestID}">
                                                <button type="submit" class="btn btn-primary">Yes</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="11" class="text-center alert alert-danger">No customs found.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>

        <c:if test="${not empty requestScope.MESSAGE}">
            <script>
                window.onload = function () {
                    showToast('${requestScope.MESSAGE}', 'error');
                };
            </script>
        </c:if>

        <c:if test="${not empty requestScope.MESSAGE}">
            <script>
                window.onload = function () {
                    showToast('${requestScope.MESSAGE}', 'success');
                };
            </script>
        </c:if>       

        <div id="toastBox"></div>
        <script src="js/homeForDelivery.js"></script>
        <script src="js/showToast.js"></script>
    </body>
</html>
