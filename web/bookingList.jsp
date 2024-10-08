<%-- 
    Document   : bookingList
    Created on : Oct 8, 2024, 12:30:08 AM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://cdn.tiny.cloud/1/sh1a9yujtib6v1803atuatuqb18ai0hfi32thc2u0y9nuah7/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
        <link href="css/homeForDelivery.css" rel="stylesheet">
        <link href="css/toast.css" rel="stylesheet">
        <link href="css/headerForSales.css" rel="stylesheet">
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
    </head>
    <body>
        <jsp:include page="headerForConsulting.jsp" flush="true"/>
        <div style="margin-top: 25vh; margin-left: 17%;" class="main-content">   
            <h1 style="text-align: center">Booking Management</h1>
            <table id="content" class="styled-table">
                <thead>
                    <tr>
                        <th>BookingID</th>
                        <th>CustomerID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Booking Date</th>
                        <th>Status</th>
                        <th>Tour Type</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty requestScope.BOOKING_LIST}">
                            <c:forEach var="custom" items="${requestScope.BOOKING_LIST}" varStatus="status">
                                <tr>
                                    <td>${custom.bookingID}</td>
                                    <td>${custom.customerID}</td>
                                    <td>${custom.custName}</td>
                                    <td>${custom.custEmail}</td>                                   
                                    <td>${custom.bookingDate}</td>
                                    <td>${custom.status}</td>
                                    <td>${custom.tourType}</td>
                                    <td>
                                        <c:if test="${custom.tourType == 'Custom'}">
                                            <!-- Trigger the modal with this button -->
                                            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#sendDetailModal${custom.bookingID}">Send Tour Detail</button>
                                        </c:if>
                                    </td>  

                                    <!-- Modal for entering tour details -->
                            <div class="modal fade" id="sendDetailModal${custom.bookingID}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="sendDetailModalLabel${custom.bookingID}" aria-hidden="true">
                                <div class="modal-dialog modal-xl modal-dialog-scrollable">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="sendDetailModalLabel${custom.bookingID}">Enter Tour Details for ${custom.custName}</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form id="sendTourDetailForm${custom.bookingID}" method="post" action="send-tour-detail">
                                                <!-- Hidden field to pass CustomerEmail -->
                                                <input type="hidden" name="txtCustomerEmail" value="${custom.custEmail}">
                                                <!-- Hidden field to pass BookingID -->
                                                <input type="hidden" name="txtBookingID" value="${custom.bookingID}">

                                                <!-- Single input field for tour details -->
                                                <div class="mb-3">
                                                    <label for="tourDetails${custom.bookingID}" class="form-label">Tour Details</label>
                                                    <textarea class="form-control" id="tourDetails${custom.bookingID}" name="txtTourDetails" rows="5" required></textarea>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <button type="submit" form="sendTourDetailForm${custom.bookingID}" class="btn btn-primary">Send Tour Detail</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="8" class="text-center alert alert-danger">No bookings found.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>      

        <script>
            tinymce.init({
                selector: 'textarea[id^="tourDetails"]',
                plugins: 'advlist autolink lists link image charmap print preview hr anchor pagebreak textcolor', // Thêm textcolor vào danh sách plugin
                toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | forecolor backcolor | charmap | hr | pagebreak |', // Cập nhật thanh công cụ để bao gồm lựa chọn màu
                toolbar_mode: 'floating',
                images_file_types: 'jpg,svg,webp',
                file_picker_callback: function (callback, value, meta) {
                    if (meta.filetype === 'image') {
                        var input = document.createElement('input');
                        input.setAttribute('type', 'file');
                        input.setAttribute('accept', 'image/*');

                        input.onchange = function () {
                            var file = this.files[0];
                            var reader = new FileReader();

                            reader.onload = function () {
                                // Callback để chèn ảnh vào editor
                                callback(reader.result, {
                                    alt: file.name
                                });
                            };

                            reader.readAsDataURL(file);
                        };

                        input.click();
                    }
                },
                setup: function (editor) {
                    editor.on('change', function () {
                        editor.save(); // Lưu nội dung vào textarea
                    });
                }
            });
        </script>




        <c:if test="${not empty requestScope.SEND_SUCCESS}">
            <script>
                window.onload = function () {
                    showToast('${requestScope.SEND_SUCCESS}', 'success');
                };
            </script>
        </c:if>

        <div id="toastBox"></div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/homeForDelivery.js"></script>
        <script src="js/showToast.js"></script>      
    </body>
</html>


