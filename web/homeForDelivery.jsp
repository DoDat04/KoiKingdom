<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/homeForDelivery.css" rel="stylesheet">
        <title>Delivery Home</title>
    </head>
    <body>
        <jsp:include page="headerForDelivery.jsp" flush="true"/>
        <div class="main" style="margin-top: -216px; margin-left: 223px; margin-right: 30px;">                 
            <table class="styled-table">
                <thead>
                    <tr>
                        <th>KoiOrderID</th>
                        <th>CustomerID</th>
                        <th>Customer Name</th>
                        <th>Delivery Date</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty requestScope.koiList}">
                            <c:forEach var="order" items="${requestScope.koiList}" varStatus="status">
                                <tr>
                                    <td>${order.koiOrderID}</td>
                                    <td>${order.customerID}</td>
                                    <td>${requestScope.customerNames[status.index]}</td>
                                    <td>${order.deliveryDate}</td>
                                    <td>${order.status}</td>
                                    <td>
                                        <form action="GetKoiOrderDetail" method="GET">
                                            <input type="hidden" name="orderName" value="${requestScope.customerNames[status.index]}">
                                            <button class="btn-detail" type="submit" >Detail</button>
                                        </form>
                                    </td>

                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6" class="text-center alert alert-danger">No orders found.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>

        <c:if test="${not empty sessionScope.updateSuccess}">
            <script>
                window.onload = function () {
                    showToast('${sessionScope.updateSuccess}', 'success');
                };
            </script>
            <c:set var="updateSuccess" value="${null}" scope="session"/>
        </c:if>

        <c:if test="${not empty sessionScope.updateError}">
            <script>
                window.onload = function () {
                    showToast('${sessionScope.updateError}', 'error');
                };
            </script>
            <c:set var="updateError" value="${null}" scope="session"/>
        </c:if>



        <div id="toastBox" class="toast-container position-fixed top-0 end-0 p-3"></div>
        <script src="js/homeForDelivery.js"></script>



        <style>
            .styled-table {
                width: 100%;
                border-collapse: collapse;
                margin: 25px 0;
                font-size: 16px;
                text-align: left;
            }
            .styled-table thead tr {
                background-color: #000;
                color: #fff;
                text-align: left;
            }
            .styled-table tbody tr {
                border-bottom: 1px solid #dddddd;
            }
            .styled-table tbody tr:nth-of-type(even) {
                background-color: #f3f3f3;
            }
            .styled-table tbody tr:hover {
                background-color: #f1f1f1;
            }
            .styled-table td, .styled-table th {
                padding: 12px 15px;
            }
            .edit-btn {
                background-color: #009879;
                color: white;
                border: none;
                padding: 8px 12px;
                cursor: pointer;
                border-radius: 5px;
                transition: background-color 0.3s;
            }

            .edit-btn:hover {
                background-color: #007f67;
            }
            a {
                color: #009879;
                text-decoration: none;
            }
            a:hover {
                text-decoration: underline;
            }
        </style>
    </body>
</html>
