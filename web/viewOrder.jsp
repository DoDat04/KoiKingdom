<%-- 
    Document   : viewOrder
    Created on : Sep 27, 2024, 1:35:37 AM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Check Out - Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/viewOrder.css">
        <!-- Script get api province -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    </head>
    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>

        <div class="container mt-5">
            <h2 class="mb-4">Checkout Information</h2>
            <div class="tour-info-card">
                <h4>Your Tour Details</h4>
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
                                    <img class="tour-img" src="${entry.value.tour.tourImage}" alt="${entry.value.tour.tourName}">
                                </td>
                                <td>
                                    <div class="tour-info">
                                        <div class="tour-name">${entry.value.tour.tourName}</div>
                                        <div class="tour-dates">
                                            <span>Start Date: 
                                                <fmt:formatDate value="${entry.value.tour.startDate}" pattern="dd-MM-yyyy" />
                                            </span>
                                            <span>End Date: 
                                                <fmt:formatDate value="${entry.value.tour.endDate}" pattern="dd-MM-yyyy" />
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
                    <tfoot>
                        <tr>
                            <td colspan="4" class="text-end fw-bold">Subtotal:</td>
                            <td>$<c:out value="${cart.totalPrice}"/></td>
                        </tr>
                    </tfoot>
                </table>
            </div>

            <h4 class="mb-4">Contact Information</h4>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="fullName"><strong>Full Name</strong></label>
                    <input type="text" class="form-control" name="fullName" placeholder="Enter your full name" required>
                </div>
                <div class="form-group col-md-6">
                    <label for="email"><strong>Email Address</strong></label>
                    <input type="email" class="form-control" name="email" placeholder="Enter your email" required>
                </div>
            </div>

            <div class="mb-3">
                <label for="homeAddress" class="form-label"><strong>Shipping Address</strong></label>
                <input type="text" class="form-control" id="homeAddresss" name="homeAddress" placeholder="Số nhà, tên đường">
            </div>

            <div class="mb-3 select-group">
                <div class="select-container">
                    <select id="cityy" name="city">
                        <option value="">Select province</option>
                    </select>
                </div>
                <div class="select-container">
                    <select id="districtt" name="district">
                        <option value="">Select district</option>
                    </select>
                </div>
                <div class="select-container">
                    <select id="wardd" name="ward">
                        <option value="">Select ward</option>
                    </select>
                </div>
            </div>
            <script src="js/viewOrder.js"></script>

            <form action="ajaxServlet" id="frmCreateOrder" method="post">        
                <input type="hidden" id="amount" name="amount" value="${cart.totalPrice * 24610}">                

                <h4 class="mt-4">Payment Method</h4>
                <div class="form-group payment-method">
                    <label for="bankCode" class="payment-label">
                        <input type="radio" checked="true" id="bankCode" name="bankCode" value="">
                        <img src="img/Icon-VNPAY-QR.webp" alt="VNPAY Logo" class="payment-logo"> Cổng thanh toán VNPAYQR
                    </label>
                </div>

                <div class="form-group mt-3">
                    <h5>Select payment interface language:</h5>
                    <input type="radio" id="language" checked="true" name="language" value="vn">
                    <label for="language">Tiếng việt</label><br>
                    <input type="radio" id="language" name="language" value="en">
                    <label for="language">Tiếng anh</label><br>
                </div>

                <button type="submit" class="btn btn-primary payment-button">Thanh toán</button>
            </form>                   
        </div>        
        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>

