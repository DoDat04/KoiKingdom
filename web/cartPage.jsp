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
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link rel="stylesheet" href="css/cart.css"> 
        <style>
            .koi-img {
                max-width: 100px;
                height: auto;
                object-fit: cover;
            }
        </style>
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
                        <c:when test="${cart == null || cart.totalQuantity == 0}">
                            <div class="cart-empty">
                                <p>Nothing here... Let's add something to the cart!</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Image</th>
                                        <th>Details</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                        <th>Total Price</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="entry" items="${cart.items}" varStatus="status">
                                        <tr>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty entry.value.tour}">
                                                        <img class="tour-img" src="${entry.value.tour.tourImage}" alt="${entry.value.tour.tourName}">
                                                    </c:when>
                                                    <c:when test="${not empty entry.value.customTour}">
                                                        <img class="tour-img" src="${entry.value.customTour.image}" alt="Custom tour">
                                                    </c:when>
                                                    <c:when test="${not empty entry.value.koi}">
                                                        <img class="koi-img" src="${entry.value.koi.image}" alt="${entry.value.koi.koiName}">
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty entry.value.tour}">
                                                        <div class="tour-info">
                                                            <div class="tour-name">${entry.value.tour.tourName}</div>
                                                            <div class="tour-dates">
                                                                <span>Start Date: <fmt:formatDate value="${entry.value.tour.startDate}" pattern="dd-MM-yyyy" /></span>
                                                                <span>End Date: <fmt:formatDate value="${entry.value.tour.endDate}" pattern="dd-MM-yyyy" /></span>
                                                                <span>Departure Location: ${entry.value.tour.tourDepartLoca}</span>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${not empty entry.value.customTour}">
                                                        <div class="tour-info">
                                                            <div class="tour-name">Custom Tour</div>
                                                            <div class="tour-dates">
                                                                <span>Start Date: <fmt:formatDate value="${entry.value.customTour.startDate}" pattern="dd-MM-yyyy" /></span>
                                                                <span>End Date: <fmt:formatDate value="${entry.value.customTour.endDate}" pattern="dd-MM-yyyy" /></span>
                                                                <span>Departure Location: ${entry.value.customTour.departureLocation}</span>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${not empty entry.value.koi}">
                                                        <div class="koi-info">
                                                            <div class="koi-name">${entry.value.koi.koiName}</div>
                                                            <div class="tour-dates">
                                                                <span>Koi Type: ${entry.value.koi.koiTypeName}</span>
                                                                <span>Age: ${entry.value.koi.age}</span>
                                                                <span>Length: ${entry.value.koi.length}</span>
                                                                <span>Weight: ${entry.value.koi.weight}</span>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty entry.value.tour}">
                                                        ${entry.value.numberOfPeople}
                                                    </c:when>
                                                    <c:when test="${not empty entry.value.customTour}">
                                                        ${entry.value.numberOfPeople}
                                                    </c:when>
                                                    <c:when test="${not empty entry.value.koi}">
                                                        ${entry.value.quantity}
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty entry.value.tour}">
                                                        $${entry.value.tour.tourPrice}
                                                    </c:when>
                                                    <c:when test="${not empty entry.value.customTour}">
                                                        $${entry.value.customTour.quotationPrice}
                                                    </c:when>
                                                    <c:when test="${not empty entry.value.koi}">
                                                        $${entry.value.koi.price}
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty entry.value.tour}">
                                                        $${entry.value.totalPrice}
                                                    </c:when>
                                                    <c:when test="${not empty entry.value.customTour}">
                                                        $${entry.value.customTour.quotationPrice * entry.value.numberOfPeople}
                                                    </c:when>
                                                    <c:when test="${not empty entry.value.koi}">
                                                        $${entry.value.totalPrice}
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <form action="RemoveItemController" method="post">
                                                    <input type="hidden" name="itemID" value="${entry.value.tour != null ? entry.value.tour.tourID : (entry.value.koi != null ? entry.value.koi.koiID : entry.value.customTour.requestID)}">
                                                    <input type="hidden" name="itemType" value="${entry.value.tour != null ? 'tour' : (entry.value.koi != null ? 'koi' : 'custom')}">
                                                    <button type="submit" class="btn-remove">Remove</button>
                                                </form>
                                            </td>
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
                        <c:set var="subtotal" value="0" />
                        <c:set var="total" value="0" />

                        <c:forEach var="entry" items="${cart.items}">
                            <c:choose>
                                <c:when test="${not empty entry.value.tour}">
                                    <c:set var="subtotal" value="${subtotal + entry.value.tour.tourPrice * entry.value.numberOfPeople}" />
                                    <c:set var="total" value="${total + entry.value.tour.tourPrice * entry.value.numberOfPeople}" />
                                </c:when>
                                <c:when test="${not empty entry.value.customTour}">
                                    <c:set var="subtotal" value="${subtotal + entry.value.customTour.quotationPrice * entry.value.numberOfPeople}" />
                                    <c:set var="total" value="${total + entry.value.customTour.quotationPrice * entry.value.numberOfPeople}" />
                                </c:when>
                                <c:when test="${not empty entry.value.koi}">
                                    <c:set var="subtotal" value="${subtotal + entry.value.koi.price * 0.3 * entry.value.quantity}" /> 
                                    <c:set var="total" value="${total + entry.value.koi.price * entry.value.quantity}" />
                                </c:when>
                            </c:choose>
                        </c:forEach>

                        <p>Subtotal: <strong>$<c:out value="${subtotal}"/></strong></p>
                        <p>Total: <strong class="total-price">$<c:out value="${total}"/></strong></p>

                        <c:choose>
                            <c:when test="${cart.totalQuantity == null || cart.totalQuantity == 0}">
                                <button type="button" class="btn btn-danger w-100" 
                                        onclick="alert('Please add something to the cart!')">
                                    Proceed to checkout
                                </button>
                            </c:when>

                            <c:when test="${cart.totalQuantity > 0 && sessionScope.LOGIN_USER == null && sessionScope.LOGIN_GMAIL == null}">
                                <button type="button" class="btn btn-danger w-100" 
                                        onclick="alert('You need to login to checkout!')">
                                    Proceed to checkout
                                </button>
                            </c:when>

                            <c:otherwise>
                                <a href="checkout" class="btn btn-danger w-100">
                                    Proceed to checkout
                                </a>
                            </c:otherwise>
                        </c:choose>
                        <p class="mt-3 text-muted" style="text-align: center">24/7 Customer Service</p>
                        <p class="phone-info" style="text-align: center"><i class="fas fa-phone"></i> 0931 339 228</p>
                    </div>
                </div>
            </div>
        </div>  

        <!-- Confirmation Modal -->
        <div id="confirmModal" class="modal">
            <div class="modal-content">
                <span class="close-button">&times;</span>
                <p>Are you sure you want to remove this item?</p>
                <div class="modal-buttons">
                    <button id="confirmRemove" class="btn-confirm">OK</button>
                    <button id="cancelRemove" class="btn-cancel">Cancel</button>
                </div>
            </div>
        </div>

        <script>
            // JavaScript to handle the modal
            document.addEventListener("DOMContentLoaded", function () {
                var modal = document.getElementById("confirmModal");
                var closeBtn = document.querySelector(".close-button");
                var confirmRemoveBtn = document.getElementById("confirmRemove");
                var cancelRemoveBtn = document.getElementById("cancelRemove");

                var formToSubmit = null; // Store the form to submit if confirmed

                // Show the modal when the remove button is clicked
                document.querySelectorAll(".btn-remove").forEach(function (button) {
                    button.addEventListener("click", function (event) {
                        event.preventDefault(); // Prevent form submission

                        formToSubmit = button.closest("form"); // Save the form that needs to be submitted
                        modal.style.display = "block"; // Show the modal
                    });
                });

                // Close the modal
                closeBtn.onclick = function () {
                    modal.style.display = "none";
                };

                cancelRemoveBtn.onclick = function () {
                    modal.style.display = "none"; // Close modal on cancel
                };

                // If "OK" is clicked, submit the form
                confirmRemoveBtn.onclick = function () {
                    if (formToSubmit) {
                        formToSubmit.submit(); // Submit the saved form
                    }
                };

                // Close modal if clicked outside of modal content
                window.onclick = function (event) {
                    if (event.target === modal) {
                        modal.style.display = "none";
                    }
                };
            });
        </script>

        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>



