<%-- 
    Document   : listCustomTour
    Created on : Oct 7, 2024, 12:30:18 AM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Custom Tour Request</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/homeForDelivery.css" rel="stylesheet">
        <link href="css/toast.css" rel="stylesheet">
        <link href="css/headerForSales.css" rel="stylesheet">
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <style>
            /* Pagination */
            .pagination {
                justify-content: center; /* Canh giữa */
                margin-top: 20px; /* Khoảng cách phía trên */
            }

            .pagination .page-item {
                margin: 0 5px; /* Khoảng cách giữa các ô */
            }

            .pagination .page-link {
                padding: 10px 15px; /* Padding cho các ô phân trang */
                border: 1px solid #04AA6D; /* Viền cho các ô */
                border-radius: 5px; /* Bo tròn các góc */
                background-color: #f4f4f9; /* Màu nền cho các ô */
                color: #333; /* Màu chữ */
                transition: background-color 0.3s; /* Hiệu ứng chuyển màu */
            }

            .pagination .page-link:hover {
                background-color: #04AA6D; /* Màu nền khi hover */
                color: white; /* Màu chữ khi hover */
            }

            .pagination .active .page-link {
                background-color: #04AA6D; /* Màu nền cho ô đang hoạt động */
                color: white; /* Màu chữ cho ô đang hoạt động */
                border: 1px solid #04AA6D; /* Viền cho ô đang hoạt động */
            }
        </style>
    </head>
    <body>
        <jsp:include page="headerForManager.jsp" flush="true"/>
        <div style="    margin-top: 25vh;
             margin-left: 17%;
             margin-right: 6%;" class="main-content">  
            <h1 style="text-align: center">Custom Tour Request</h1>
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
                        <c:when test="${not empty sessionScope.CUSTOM_LIST}">
                            <c:forEach var="custom" items="${sessionScope.CUSTOM_LIST}" varStatus="status">
                                <tr>
                                    <td>${custom.custName}</td>
                                    <td>${custom.duration}</td>
                                    <td><fmt:formatDate value="${custom.startDate}" pattern="dd-MM-yyyy" /></td>
                                    <td><fmt:formatDate value="${custom.endDate}" pattern="dd-MM-yyyy" /></td>                                   
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
                                                    <!-- Nút Reject hiển thị modal -->
                                                    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#updateModal${custom.requestID}">Adjust</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="submit" name="action" value="approve" class="btn btn-success" disabled style="margin-bottom: 10px;">Approve</button>
                                                    <button type="submit" name="action" value="reject" class="btn btn-danger" disabled>Adjust</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </form>
                                    </td>

                                    <!-- Modal for Reject -->
                            <div class="modal fade" id="updateModal${custom.requestID}" tabindex="-1" aria-labelledby="updateModalLabel${custom.requestID}" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="updateModalLabel${custom.requestID}">Update Section</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form id="updateForm${custom.requestID}" method="post" action="ManagerCustomActionController">
                                                <div class="mb-3">
                                                    <label for="newPrice${custom.requestID}" class="form-label">Quotation Price</label>
                                                    <input type="number" step="0.01" class="form-control" id="newPrice${custom.requestID}" name="newPrice" placeholder="Enter new quotation price" required>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="rejectReason${custom.requestID}" class="form-label">Rejection Reason</label>
                                                    <textarea class="form-control" id="rejectReason${custom.requestID}" name="rejectReason" placeholder="Enter reason for rejection" required></textarea>
                                                </div>
                                                <input type="hidden" name="customId" value="${custom.requestID}">
                                                <input type="hidden" name="action" value="reject">
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                    <button type="submit" class="btn btn-primary">Reject</button>
                                                </div>
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
            <!-- phân trang -->
                <jsp:useBean id="a" class="koikd.customtour.CustomTourDAO" scope="request"></jsp:useBean>
                    <nav>
                        <ul class="pagination pagination-lg">
                        <c:set var="prevPage" value="${pageIndex - 1}" />
                        <c:set var="nextPage" value="${pageIndex + 1}" />

                        <!-- Nút Back -->
                        <c:if test="${pageIndex > 1}">
                            <li class="page-item">
                                <a class="page-link" href="custom-tour?index=${prevPage}">Back</a>
                            </li>
                        </c:if>

                        <!-- Các trang phân trang -->
                        <c:forEach begin="1" end="${numberOfPages}" var="i">
                            <li class="page-item ${pageIndex == i ? 'active' : ''}">
                                <a class="page-link" href="custom-tour?index=${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <!-- Nút Next -->
                        <c:if test="${pageIndex < numberOfPages}">
                            <li class="page-item">
                                <a class="page-link" href="custom-tour?index=${nextPage}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
        </div>

        <c:if test="${not empty sessionScope.MESSAGE}">
            <script>
                window.onload = function () {
                    showToast('${sessionScope.MESSAGE}', 'success');
                };
            </script>
            <c:set var="MESSAGE" value="${null}" scope="session"/>
        </c:if>       

        <div id="toastBox"></div>
        <script src="js/homeForDelivery.js"></script>
        <script src="js/showToast.js"></script>
    </body>
</html>
