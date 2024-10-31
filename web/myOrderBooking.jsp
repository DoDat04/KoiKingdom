<%-- 
    Document   : myOrderBooking
    Created on : Oct 2, 2024, 9:20:36 PM
    Author     : Minhngo
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Booking - Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">   
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
        <link href="css/toast.css" rel="stylesheet">
        <link rel="stylesheet" href="css/myOrderBooking.css"/>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
    </head>
    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>       
        <div style=" margin: 20px;">  
            <c:if test="${not empty requestScope.orders}">
                <c:forEach var="orders" items="${requestScope.orders}" varStatus="tourBookingDetailID">                
                    <div class="order_booking" style="display: flex; justify-content: center; margin-bottom: 20px;">
                        <div class="order_booking-section card" style="width: 100%; max-width: 900px;">
                            <div class="card-body">

                                <c:if test="${orders.tourType == 'Available'}">
                                    <a href="tour-detail?tourID=${requestScope.tours[tourBookingDetailID.index].tourID}" style="text-decoration: none; color: black;">
                                    </c:if>
                                    <div style="font-size: 16px; font-weight: 500; color: #ff6f61; margin-top: 5px;" >${orders.tourType}</div>
                                    <div class="d-flex align-items-center justify-content-between mb-3">
                                        <div class="d-flex align-items-center">
                                            <img src="img/TourImage/1.jpg" alt="Picture of TOUR" class="rounded" style="width: 80px; height: 80px; object-fit: cover; margin-right: 15px;"/>
                                            <div class="order_booking-name" style="font-size: 18px; font-weight: 600; color: #333; margin-right: 20px;">
                                                <c:if test="${orders.tourType != 'Custom'}">
                                                    ${requestScope.tours[tourBookingDetailID.index].tourName}
                                                </c:if>
                                                <!-- Display the number of people and price per person -->
                                                <div style="font-size: 16px; font-weight: 500; color: #666; margin-top: 5px;">
                                                    <span style="font-weight: bold; color: #ff6f61;">
                                                        ${orders.quantity} people x 
                                                        <fmt:formatNumber type="currency" currencySymbol="$" value="${orders.unitPrice}"/>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>

                                        <div style="text-align: right;">
                                            <div class="order_booking-price" style="font-size: 16px; font-weight: bold; color: #333;">
                                                Total: <fmt:formatNumber type="currency" currencySymbol="$" value="${orders.totalPrice}"/>
                                            </div>

                                            <!-- Order Status -->
                                            <div class="order_booking-status" style="font-size: 16px; margin-top: 5px; padding: 5px; border-radius: 4px;">
                                                <span class="status-label">Status:</span> 
                                                <span class="status-value">${orders.status}</span>

                                                <c:if test="${orders.status != 'Completed'}">
                                                    <div>
                                                        <a href="cancel_booking?bookingid=${orders.tourBookingDetailID}&customerID=${orders.customerID}" class="btn btn-danger" onclick="return confirm('Are you sure?')">Cancel</a>
                                                    </div>
                                                </c:if>
                                                <c:if test="${orders.status == 'Completed'}">
                                                    <div class="thank-you-message" style="margin-top: 5px; color: green;">
                                                        Cảm ơn bạn đã đặt dịch vụ!
                                                        <br />
                                                        Xin vui lòng đánh giá dịch vụ của chúng tôi.
                                                        <c:if test="${orders.feedbackStatus == 'false'}">
                                                            <a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#reviewTour"
                                                               data-tourid="${orders.tourID}" data-customerid="${orders.customerID}"  data-bookingid="${orders.bookingID}" id="review">Review</a>
                                                        </c:if>
                                                        <c:if test="${orders.feedbackStatus == 'true'}">

                                                            <a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#editReviewTour"
                                                               data-tourid="${orders.tourID}" data-customerid="${orders.customerID}"  data-bookingid="${orders.bookingID}" id="review"
                                                               onclick="fetchFeedbackData(${orders.tourID}, ${orders.customerID}, ${orders.bookingID})"
                                                               >
                                                                Edit Review
                                                            </a>
                                                        </c:if>

                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${orders.tourType == 'Available'}">
                                    </a>
                                </c:if>
                            </div>
                        </div>
                    </div>                         
                </c:forEach>
            </c:if>

            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    var reviewModal = document.getElementById('reviewTour');

                    reviewModal.addEventListener('show.bs.modal', function (event) {
                        var button = event.relatedTarget;

                        var tourID = button.getAttribute('data-tourid');
                        var customerID = button.getAttribute('data-customerid');
                        var bookingID = button.getAttribute('data-bookingid');

                        var tourIDInput = reviewModal.querySelector('input[name="tourID"]');
                        var customerIDInput = reviewModal.querySelector('input[name="customerID"]');
                        var bookingIDInput = reviewModal.querySelector('input[name="bookingID"]');
                        tourIDInput.value = tourID;
                        customerIDInput.value = customerID;
                        bookingIDInput.value = bookingID;
                    });
                });
            </script>
            .
            <div class="modal fade" id="reviewTour" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="staticBackdropLabel">Đánh Giá Dịch Vụ</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <div class="modal-body">
                            <form action="CreateFeedbackForCustomer" method="post" enctype="multipart/form-data" id="reviewForm">
                                <input type="hidden" name="customerID" value="">
                                <input type="hidden" name="tourID" value="">
                                <input type="hidden" name="bookingID" value="">

                                <div class="mb-3">
                                    <label for="feedback" class="form-label">Phản hồi của bạn</label>
                                    <textarea class="form-control" id="feedback" name="feedback" rows="3" required></textarea>
                                </div>

                                <div class="star-widget">
                                    <input type="radio" name="rate" id="rate-review-1" value="5">
                                    <label for="rate-review-1" class="fas fa-star"></label>
                                    <input type="radio" name="rate" id="rate-review-2" value="4">
                                    <label for="rate-review-2" class="fas fa-star"></label>
                                    <input type="radio" name="rate" id="rate-review-3" value="3">
                                    <label for="rate-review-3" class="fas fa-star"></label>
                                    <input type="radio" name="rate" id="rate-review-4" value="2">
                                    <label for="rate-review-4" class="fas fa-star"></label>
                                    <input type="radio" name="rate" id="rate-review-5" value="1">
                                    <label for="rate-review-5" class="fas fa-star"></label>
                                    <h3 class="content"></h3> 
                                </div>                                  

                                <div class="modal-footer">
                                    <button type="button" id="close" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                    <button type="submit" class="btn btn-primary" id="submitReview">Gửi Đánh Giá</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


            <script>
                $(document).ready(function () {
                    $('#reviewForm').on('submit', function (e) {
                        e.preventDefault();
                        var formData = new FormData(this);
                        $.ajax({
                            url: $(this).attr('action'),
                            type: 'POST',
                            data: formData,
                            processData: false,
                            contentType: false,
                            success: function (response) {
                                if (response.success) {
                                    showToast(response.message, 'success');
                                    $('#close').click();
                                    $('#reviewForm')[0].reset();

                                    $('#review').attr('data-feedbackid', response.feedbackID);
                                    setTimeout(function () {
                                        location.reload();
                                    }, 6001);
                                } else {
                                    showToast(response.message, 'error');
                                }
                            },
                            error: function (xhr, status, error) {
                                showToast('Đánh giá của bạn đã được gửi thất bại!', 'error');
                            }
                        });
                    });
                });
            </script>

            <!-- comment -->


            <script>
                function fetchFeedbackData(tourID, customerID, bookingID) {
                    $.ajax({
                        url: '/KoiKingdom/get_feedback',
                        type: 'GET',
                        data: {
                            tourID: tourID,
                            customerID: customerID,
                            bookingID: bookingID
                        },
                        success: function (response) {
                            console.log('Booking ID:', bookingID);
                            // Populate the inputs in the editReviewTour modal
                            $('#editReviewTour input[name="customerID"]').val(response.customerID);
                            $('#editReviewTour input[name="tourID"]').val(response.tourID);
                            $('#editReviewTour input[name="bookingID"]').val(response.bookingID);
                            $('#editReviewTour input[name="feedbackID"]').val(response.feedbackID);
                            $('#editReviewTour #feedback').val(response.feedbackText);
                            $('#editReviewTour input[name="rate"]').prop('checked', false);
                            $('#editReviewTour input[name="rate"][value="' + response.rating + '"]').prop('checked', true);
                            $('#editReviewTour').modal('show'); // Show the modal with populated data
                        },
                        error: function (xhr, status, error) {
                            console.error('Error fetching feedback data:', error);
                            alert('Could not fetch feedback data. Please try again.');
                        }
                    });
                }
            </script>

            <div class="modal fade" id="editReviewTour" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="staticBackdropLabel">Đánh Giá Dịch Vụ</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <div class="modal-body">
                            <form action="/KoiKingdom/update_feedback" method="post" enctype="multipart/form-data" id="editReviewForm">

                                <input type="" name="customerID" value="">
                                <input type="" name="tourID" value="">
                                <input type="" name="bookingID" value="">
                                <div class="mb-3">
                                    <label for="feedback" class="form-label">Phản hồi của bạn</label>
                                    <textarea class="form-control" id="feedback" name="feedback" rows="3" required></textarea>
                                </div>
                                <div class="star-widget">
                                    <input type="radio" name="rate" id="rate-edit-1" value="5" required>
                                    <label for="rate-edit-1" class="fas fa-star"></label>
                                    <input type="radio" name="rate" id="rate-edit-2" value="4" required>
                                    <label for="rate-edit-2" class="fas fa-star"></label>
                                    <input type="radio" name="rate" id="rate-edit-3" value="3" required>
                                    <label for="rate-edit-3" class="fas fa-star"></label>
                                    <input type="radio" name="rate" id="rate-edit-4" value="2" required>
                                    <label for="rate-edit-4" class="fas fa-star"></label>
                                    <input type="radio" name="rate" id="rate-edit-5" value="1" required>
                                    <label for="rate-edit-5" class="fas fa-star"></label>
                                    <h3 class="content"></h3> 
                                </div>                                  

                                <div class="modal-footer">
                                    <button type="button" id="closemodal" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                    <button type="submit" class="btn btn-primary" id="submitReview">Gửi Đánh Giá</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


            <script>
                $(document).ready(function () {
                    $('#editReviewForm').on('submit', function (e) {
                        e.preventDefault();
                        var formData = new FormData(this);
                        console.log([...formData]);
                        $.ajax({
                            url: $(this).attr('action'),
                            type: 'POST',
                            data: formData,
                            processData: false,
                            contentType: false,
                            success: function (response) {
                                if (response.success) {
                                    showToast(response.message, 'success');
                                    $('#closemodal').click();
                                    $('#editReviewForm')[0].reset();

                                    setTimeout(function () {
                                        location.reload();
                                    }, 6001);
                                } else {
                                    showToast(response.message, 'error');
                                }
                            },
                            error: function (xhr, status, error) {
                                showToast('Đánh giá của bạn đã được gửi thất bại!', 'error');
                            }
                        });
                    });
                });
            </script>


            <script>
                const btn = document.querySelector("button");
                const post = document.querySelector(".post");
                const widget = document.querySelector(".star-widget");
                const editBtn = document.querySelector(".edit");
                btn.onclick = () => {
                    widget.style.display = "none";
                    post.style.display = "block";
                    editBtn.onclick = () => {
                        widget.style.display = "block";
                        post.style.display = "none";
                    }
                    return false;
                }
            </script>
            
            <c:if test="${not empty requestScope.Successfully}">
                <script>
                    window.onload = function () {
                        showToast('${requestScope.Successfully}', 'success');
                    };
                </script>
            </c:if>

            <div id="toastBox"></div>
            <script src="js/showToast.js"></script> 
            <c:if test="${empty requestScope.orders}">
                <p class="alert alert-danger">${Error}</p>
            </c:if>
        </div>  

        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>
