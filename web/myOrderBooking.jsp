<%-- 
    Document   : myOrderBooking
    Created on : Oct 2, 2024, 9:20:36 PM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="css/toast.css" rel="stylesheet">
        <link rel="stylesheet" href="css/myOrderBooking.css"/>
        <title>My Booking - Koi Kingdom</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
    </head>
    <style>
        .star-widget {
            text-align: center; 
        }

        .star-widget input {
            display: none; 
        }

        .star-widget label {
            font-size: 40px;
            color: #444;
            padding: 10px;
            cursor: pointer; 
            transition: all 0.2s ease;
        }

        input:not(:checked) ~ label:hover,
        input:not(:checked) ~ label:hover ~ label {
            color: #fd4;
        }

        input:checked ~ label {
            color: #fd4;
        }

        input#rate-5:checked ~ label {
            color: #fe7;
            text-shadow: 0 0 20px #952;
        }

        /* Cập nhật nội dung cho h3 khi chọn */
        input#rate-5:checked ~ .content::before {
            content: "I just hate it";
        }

        input#rate-4:checked ~ .content::before {
            content: "I don't like it";
        }

        input#rate-3:checked ~ .content::before {
            content: "It is awesome";
        }

        input#rate-2:checked ~ .content::before {
            content: "I just like it";
        }

        input#rate-1:checked ~ .content::before {
            content: "I just love it";
        }

        .content {
            display: block; /* Đảm bảo h3 luôn hiển thị */
            font-size: 25px;
            color: #fe7;
            font-weight: 500;
            margin-top: 10px; /* Khoảng cách giữa label và h3 */
            transition: all 0.2s ease;
        }

    </style>
    <body>
        <div class="colorlib-loader"></div>
        <jsp:include page="headerForCustomer.jsp" flush="true"/>       
        <div style=" margin: 20px;">  
            <c:if test="${not empty requestScope.orders}">
                <c:forEach var="orders" items="${requestScope.orders}" varStatus="tourBookingDetailID">                
                    <div class="order_booking" style="display: flex; justify-content: center; margin-bottom: 20px;">
                        <div class="order_booking-section card" style="width: 100%; max-width: 900px;">
                            <div class="card-body">
                                <a href="tour-detail?tourID=${requestScope.tours[tourBookingDetailID.index].tourID}" style="text-decoration: none; color: black;">
                                    <div class="d-flex align-items-center justify-content-between mb-3">
                                        <div class="d-flex align-items-center">
                                            <img src="${requestScope.tours[tourBookingDetailID.index].tourImage}" alt="Picture of TOUR" class="rounded" style="width: 80px; height: 80px; object-fit: cover; margin-right: 15px;"/>
                                            <div class="order_booking-name" style="font-size: 18px; font-weight: 600; color: #333; margin-right: 20px;">
                                                ${requestScope.tours[tourBookingDetailID.index].tourName}
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
                                                <c:if test="${orders.status == 'completed'}">
                                                    <div class="thank-you-message" style="margin-top: 5px; color: green;">
                                                        Cảm ơn bạn đã đặt hàng!
                                                        <br />
                                                        Xin vui lòng đánh giá dịch vụ của chúng tôi.
                                                        <a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#reviewTour"
                                                           data-tourid="${orders.tourID}" data-customerid="${orders.customerID}" id="review">Review</a>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </a>
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

                        var tourIDInput = reviewModal.querySelector('input[name="tourID"]');
                        var customerIDInput = reviewModal.querySelector('input[name="customerID"]');

                        tourIDInput.value = tourID;
                        customerIDInput.value = customerID;
                    });
                });
            </script>


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

                                <div class="mb-3">
                                    <label for="feedback" class="form-label">Phản hồi của bạn</label>
                                    <textarea class="form-control" id="feedback"  name="feedback" rows="3" required></textarea>
                                </div>
                                <div class="star-widget">
                                    <input type="radio" name="rate" id="rate-1" value="5">
                                    <label for="rate-1" class="fas fa-star"></label>
                                    <input type="radio" name="rate" id="rate-2" value="4">
                                    <label for="rate-2" class="fas fa-star"></label>
                                    <input type="radio" name="rate" id="rate-3" value="3">
                                    <label for="rate-3" class="fas fa-star"></label>
                                    <input type="radio" name="rate" id="rate-4" value="2">
                                    <label for="rate-4" class="fas fa-star"></label>
                                    <input type="radio" name="rate" id="rate-5" value="1">
                                    <label for="rate-5" class="fas fa-star"></label>
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

        <div id="toastBox"></div>
        <script src="js/showToast.js"></script>
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

        <c:if test="${empty requestScope.orders}">
            <p class="alert alert-danger">${Error}</p>
        </c:if>
    </div>  

    <jsp:include page="footer.jsp" flush="true"/>
</body>


</html>
