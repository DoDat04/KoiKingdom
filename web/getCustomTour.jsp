<%-- 
    Document   : getCustomTour
    Created on : Oct 5, 2024, 8:26:16 AM
    Author     : ADMIN LAM
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <link href="css/headerForSales.css" rel="stylesheet">
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
                        <th>Detail Rejected</th>
                        <th>Quantity</th>
                        <th>Quotation Price</th>
                        <th>Image</th>
                        <th>Actions</th>
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
                                    <td class="${custom.status == 'Pending' ? 'text-warning' : (custom.status == 'Approved' ? 'text-success' : (custom.status == 'Rejected' ? 'text-danger' : ''))}">
                                        ${custom.status}
                                    </td>
                                    <td class="${custom.managerApprovalStatus == 'Pending' ? 'text-warning' : (custom.managerApprovalStatus == 'Approved' ? 'text-success' : (custom.managerApprovalStatus == 'Rejected' ? 'text-danger' : ''))}">
                                        ${custom.managerApprovalStatus}
                                    </td>
                                    <td>${custom.departureLocation}</td>
                                    <td>${custom.farmName}</td>
                                    <td>${custom.koiTypeName}</td>
                                    <td>${custom.detailRejected}</td>
                                    <td>${custom.quantity}</td>
                                    <td><fmt:formatNumber value="${custom.quotationPrice}" type="currency" currencySymbol="$" /></td>
                                    <td><img src="${custom.image}" class="custom-tour" alt="" style="height: 100px; width: 150px; border-radius: 10px;"/></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${custom.status != 'Approved' && custom.status != 'Rejected'}">
                                                <button type="button" class="btn btn-primary mb-2" data-bs-toggle="modal" data-bs-target="#updateModal${custom.requestID}">
                                                    Update
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="button" class="btn btn-secondary mb-2" disabled>
                                                    Locked
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                        <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#sendModal${custom.requestID}">
                                            Send
                                        </button>
                                    </td>


                                    <!-- Modal -->
                            <div class="modal fade" id="updateModal${custom.requestID}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="updateModalLabel${custom.requestID}" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="updateModalLabel${custom.requestID}">Update Price and Status</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form id="updateForm${custom.requestID}" method="post" action="update-price">
                                                <div class="mb-3">
                                                    <label for="quotationPrice${custom.requestID}" class="form-label">Quotation Price</label>
                                                    <input type="number" step="0.01" class="form-control" id="quotationPrice${custom.requestID}" name="txtQuoPrice" placeholder="Enter new quotation price" required>
                                                </div>
                                                <!--                                                <div class="mb-3">
                                                                                                    <label for="status${custom.requestID}" class="form-label">Status</label>
                                                                                                    <select class="form-control" id="status${custom.requestID}" name="txtStatus" required>
                                                                                                        <option value="Pending">Pending</option>
                                                                                                        <option value="Approved">Approved</option>
                                                                                                        <option value="Rejected">Rejected</option>
                                                                                                    </select>
                                                                                                </div>-->
                                                <!-- Hidden input to pass the request ID -->
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

        <c:if test="${not empty sessionScope.UPDATE_SUCCESS}">
            <script>
                window.onload = function () {
                    showToast('${sessionScope.UPDATE_SUCCESS}', 'success');
                };
            </script>
            <c:set var="UPDATE_SUCCESS" value="${null}" scope="session"/>
        </c:if>

        <c:if test="${not empty sessionScope.SEND_SUCCESS}">
            <script>
                window.onload = function () {
                    showToast('${sessionScope.SEND_SUCCESS}', 'success');
                };
            </script>
            <c:set var="SEND_SUCCESS" value="${null}" scope="session"/>
        </c:if>

        <div id="toastBox"></div>
        <script src="js/homeForDelivery.js"></script>
        <script src="js/showToast.js"></script>
    </body>
</html>
z