<%-- 
    Document   : cartPage
    Created on : Sep 26, 2024, 2:39:00 PM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart - Koi Kingdom</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
              rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/cart.css">
    </head>
    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-8">
                    <div class="d-flex justify-content-between align-items-center mb-4 continue-buy-link">
                        <h2>My Cart</h2>
                        <a href="tour-list" class="btn btn-link">&lt; Continue Buying</a>
                    </div>
                    <c:choose>
                        <c:when test="${empty cart.items}">
                            <div class="cart-empty">
                                <p>Nothing here... Let's add something to the cart!</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Tour Image</th>
                                        <th>Tour Details</th>
                                        <th>Number of People</th>
                                        <th>Tour Price</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="entry" items="${cart.items}">
                                        <tr>
                                            <td>
                                                <img class="tour-img" 
                                                     src="${entry.value.tour.tourImage}" 
                                                     alt="${entry.value.tour.tourName}">
                                            </td>
                                            <td>
                                                <div class="tour-info">
                                                    <div class="tour-name">${entry.value.tour.tourName}</div>
                                                    <div class="tour-dates">
                                                        <span>Start Date: 
                                                            <fmt:formatDate value="${entry.value.tour.startDate}"
                                                                            pattern="dd-MM-yyyy" />
                                                        </span>
                                                        <span>End Date: 
                                                            <fmt:formatDate value="${entry.value.tour.endDate}"
                                                                            pattern="dd-MM-yyyy" />
                                                        </span>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>${entry.value.numberOfPeople}</td>
                                            <td>$${entry.value.tour.tourPrice}</td>
                                            <td>$${entry.value.totalPrice}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="col-md-4">
                    <div class="summary-card">
                        <h4>Summary</h4>
                        <p>Subtotal: <strong>$<c:out value="${cart.totalPrice}"/></strong></p>
                        <p>Total: <strong class="total-price">$<c:out value="${cart.totalPrice}"/></strong></p>
                        <button class="checkout-btn">Proceed to checkout</button>
                        <p class="mt-3 text-muted" style="text-align: center">24/7 Customer Service</p>
                        <p class="phone-info" style="text-align: center"><i class="fas fa-phone"></i> 0931 339 228</p>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>



