<%-- 
    Document   : KoiBookingOnline
    Created on : Oct 30, 2024, 11:17:34 AM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Koi Order List</title>
        <link href="css/toast.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="headerForConsulting.jsp" flush="true"/>
        <div style="margin-top: 25vh; margin-left: 17%;" class="main-content"> 
            <h2 style="text-align: center; color: #4CAF50; font-weight: bold;">Koi Booking Online</h2>
            <table class="table table-bordered" style="width: 100%; text-align: center; margin-top: 20px;">
                <thead style="background-color: #f2f2f2;">
                    <tr>
                        <th>Koi Order ID</th>
                        <th>Full Name</th>
                        <th>Order Date</th>
                        <th>Estimated Date</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="farm" items="${requestScope.KOIBOOKINGLIST}">
                        <tr>
                            <td>${farm.koiOrderID}</td>
                            <td>${farm.fullName}</td>
                            <td>${farm.deliveryDate}</td>
                            <td>
                                <form action="koi-booking-list" method="post" style="display: inline;">
                                    <input type="hidden" name="koiOrderID" value="${farm.koiOrderID}"/>
                                    <input type="date" name="estimatedDelivery" value="${farm.estimatedDelivery}" required/>
                            </td>
                            <td>
                                <button type="submit" class="btn btn-primary">Update</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <c:if test="${not empty requestScope.UPDATESUCCESS}">
                <script>
                    window.onload = function () {
                        showToast('${requestScope.UPDATESUCCESS}', 'success');
                    };
                </script>
            </c:if>

            <c:if test="${not empty requestScope.ERROR_NULL}">
                <div class="error-message">${requestScope.ERROR_NULL}</div>
            </c:if>
                <div id="toastBox"></div>
        <script src="js/showToast.js"></script>
        </div>
    </body>
</html>
