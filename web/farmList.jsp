<%-- 
    Document   : farmList
    Created on : Oct 11, 2024, 12:34:06 PM
    Author     : ADMIN LAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Farm List Information Page</title>
    </head>
    <body>
        <jsp:include page="headerForConsulting.jsp" flush="true"/>
        <div style="margin-top: 25vh; margin-left: 17%;" class="main-content"> 
                <h2 style="text-align: center; color: #4CAF50; font-weight: bold;">Farm's Information</h2>
                <table class="table table-bordered" style="width: 100%; text-align: center; margin-top: 20px;">
                    <thead style="background-color: #f2f2f2;">
                        <tr>
                            <th>Farm ID</th>
                            <th>Farm Name</th>
                            <th>Location</th>
                            <th>Image</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="farm" items="${requestScope.LIST_FARM}">
                        <tr>
                            <td>${farm.farmID}</td>
                            <td>${farm.farmName}</td>
                            <td>${farm.location}</td>
                            <td><img src="${farm.image}" alt="Farm Image" style="width: 100px; height: auto;"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            <c:if test="${not empty requestScope.ERROR_NULL}">
                <div class="error-message">${requestScope.ERROR_NULL}</div>
            </c:if>
        </div>
    </body>
</html>
