<%-- 
    Document   : koiList
    Created on : Oct 11, 2024, 12:34:06 PM
    Author     : ADMIN LAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Koi List Information Page</title>
    </head>
    <body>
        <jsp:include page="headerForConsulting.jsp" flush="true"/>
        <div style="margin-top: 25vh; margin-left: 17%;" class="main-content"> 
                <h2 style="text-align: center; color: #4CAF50; font-weight: bold;">Koi's Information</h2>
                <table class="table table-bordered" style="width: 100%; text-align: center; margin-top: 20px;">
                    <thead style="background-color: #f2f2f2;">
                        <tr>
                            <th>Koi ID</th>
                            <th>Koi Name</th>
                            <th>Koi Type ID</th>
                            <th>Age</th>
                            <th>Length</th>
                            <th>Weight</th>
                            <th>Price</th>
                            <th>Image</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="koi" items="${requestScope.KOI_TABLE}">
                        <tr>
                            <td>${koi.koiID}</td>
                            <td>${koi.koiName}</td>
                            <td>${koi.koiTypeID}</td>
                            <td>${koi.age}</td>
                            <td>${koi.length}</td>
                            <td>${koi.weight}</td>
                            <td>${koi.price}</td>
                            <td><img src="${koi.image}" alt="Koi Image" style="width: 100px; height: auto;"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            <c:if test="${not empty requestScope.ERROR_TABLE}">
                <div class="error-message">${requestScope.ERROR_TABLE}</div>
            </c:if>
        </div>
    </body>
</html>
