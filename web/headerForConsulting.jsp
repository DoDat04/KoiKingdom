<%-- 
    Document   : headerForConsulting
    Created on : Oct 3, 2024, 8:25:54 AM
    Author     : Do Dat
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Font Awesome for Icons -->
              <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Trumbowyg/2.25.1/ui/trumbowyg.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Trumbowyg/2.25.1/trumbowyg.min.js"></script>
        
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        <link rel="stylesheet" href="css/headerForDelivery.css">
        <title>Header For Delivery</title>
    </head>
    <body>  

        <div class="navbar-header">           
            <div class="d-flex justify-content-between align-items-center main-frame" style="margin-left: -0.2%; padding-top: 31.7px; padding-right: 161px">     
                <a href="home?action=Consulting" style="margin-left: 116px">
                    <img src="img/logo.png" class="main-icon" >
                </a>


                <div class="menu-center">
                    <ul class="nav justify-content-center">
                        <form action="GetKoiOrder" method="get" class="search--box"> 
                            <input oninput="searchByName(this)" type="text" name="txtNameCustomer" placeholder="Search name customer" style=" border: none;"/>
                            <button type="submit" style="border: none; border-radius: 4px;">  <i class="fa-solid fa-search"></i> </button>
                        </form>
                    </ul>
                </div>
            </div>
        </div>



        <div class="sidebar" style="margin-top: -79px;">
            <c:choose>
                <c:when test="${sessionScope.LOGIN_CONSULTING != null}">
                    <ul class="menu">
                        <div class="user-frame">
                            <li class="user-info">
                                <div class="user-details">      
                                    <div style="max-width: 200px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
                                        <span class="user-name" \>
                                            ${sessionScope.LOGIN_CONSULTING.firstName} ${sessionScope.LOGIN_CONSULTING.lastName}
                                        </span><br>
                                        <span class="user-role">${sessionScope.LOGIN_CONSULTING.role}</span>
                                    </div>
                                    <i class="fa-solid fa-angles-left toggle-btn" style="font-size:18px;"></i>
                                </div>
                            </li>
                        </div>
                        <li class="menu-item">
                            <a href="booking-list" style="color: black"><i style='font-size:24px' class='fas'>&#xf0d1;</i>Manage Booking</a>
                        </li>
                        <li class="menu-item">
                            <a href="tour-booking-detail-list?employeeID=${sessionScope.consultingID}" style="color: black"><i style='font-size:24px' class='fas'>&#xf0d1;</i>Tour Detail Management</a>
                        </li>
                        <li class="menu-item">
                            <a href="list-tour-custom" style="color: black"><i style='font-size:24px' class='fas'>&#xf0d1;</i>Manage Tour Custom</a>
                        </li>
                        <li class="menu-item">
                            <a href="home?action=koiOrderForm" style="color: black"><i style='font-size:24px' class='fas'>&#xF478;</i>Create Koi Order</a>
                        </li>
                        <li class="menu-item">
                            <a href="koi-order-list" style="color: black"><i style='font-size:24px' class='fas'>&#xF433;</i>Koi Order</a>
                        </li>
                        <li class="menu-item">
                            <a href="koi-booking-list" style="color: black"><i style='font-size:24px' class='fas'>&#xF433;</i>Koi Booking</a>
                        </li>
                        <li class="menu-item"><a type="button" style="color: black" class="btn btn-primary dropdown-item" data-bs-toggle="modal" data-bs-target="#profileModal" href="home?action=Profile">
                                <i style='font-size:24px' class='fas'>&#xf406;</i>Profile</a></li>
                        <li class="menu-item">
                            <a href="home?action=Logout" style="color: black"><i style="font-size:24px" class="fa">&#xf08b;</i>Sign out</a>
                        </li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <script>
                        window.location.href = "login";
                    </script>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="modal fade" id="profileModal" tabindex="-1" aria-labelledby="profileModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Update Profile</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="UpdateProfileConsultingController" method="post" enctype="multipart/form-data" id="profileForm">
                            <div class="text-center mb-4">
                                <div class="avatar-container">
                                    <img id="avatarPreview" 
                                         src="${sessionScope.AVATAR}" 
                                         alt="">
                                </div>
                                <input type="file" id="avatar" name="profileImage" class="form-control mt-2" accept="image/*" onchange="previewAvatar()">
                            </div>
                            <label for="fname">Họ:</label>
                            <input type="text" id="fname" name="fname" readonly value="${sessionScope.LOGIN_CONSULTING.lastName}">
                            <label for="lname">Tên:</label>
                            <input type="text" id="lname" name="lname" readonly value="${sessionScope.LOGIN_CONSULTING.firstName}">
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" readonly value="${sessionScope.LOGIN_CONSULTING.email}">
                            <label for="address">Địa chỉ:</label>
                            <input type="text" id="address" name="address" readonly value="${sessionScope.LOGIN_CONSULTING.address}">
                            <div class="mb-3 select-group" id="addressSelectDiv" style="display: none;">
                                <div class="row">
                                    <div class="col-md-4">
                                        <label for="city" class="form-label">Province:</label>
                                        <select class="form-select" id="city" name="city">
                                            <option value="">Select province</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="district" class="form-label">District:</label>
                                        <select class="form-select" id="district" name="district">
                                            <option value="">Select district</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="ward" class="form-label">Ward:</label>
                                        <select class="form-select" id="ward" name="ward">
                                            <option value="">Select ward</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <script src="js/addressSelect.js"></script>
                            <label for="role">Vai trò:</label>
                            <input type="text" id="role" name="role" readonly value="${sessionScope.LOGIN_CONSULTING.role}">

                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" id="closeButton" data-bs-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-secondary" id="editButton" onclick="toggleEdit()">Edit</button>
                                <button type="submit" name="action" class="btn btn-secondary" id="saveButton" style="display: none;">Save</button>
                                <button type="button" class="btn btn-primary" id="cancelButton" onclick="toggleEdit()" style="display: none;">Cancel</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>   
        <script src="js/headerForDelivery.js"></script>

        <script>
            document.querySelector('.toggle-btn').addEventListener('click', function () {
                const sidebar = document.querySelector('.sidebar');
                const mainFrame = document.querySelector('.main-frame');
                // Ensure 'main-content' exists in your HTML or adjust the selector
                const mainContent = document.querySelector('.main-content');
                const toggleIcon = this; // 'this' refers to the clicked '.toggle-btn' element

                sidebar.classList.toggle('collapsed');

                if (sidebar.classList.contains('collapsed')) {
                    mainFrame.style.marginLeft = '-143px';
                    if (mainContent)
                        mainContent.style.marginLeft = '10%';
                    toggleIcon.classList.remove('fa-angles-left');
                    toggleIcon.classList.add('fa-angles-right');
                } else {
                    mainFrame.style.marginLeft = '-0.2%';
                    if (mainContent)
                        mainContent.style.marginLeft = '20%';
                    toggleIcon.classList.remove('fa-angles-right');
                    toggleIcon.classList.add('fa-angles-left');
                }
            });

            function searchByName(param) {
                var txtSearch = param.value;
                $.ajax({
                    url: "/KoiKingdom/GetKoiOrderByAjax",
                    type: "GET",
                    data: {
                        txtNameCustomer: txtSearch
                    },
                    success: function (data) {
                        var row = document.getElementById("content");
                        row.innerHTML = data;

                        // Re-attach the click event to handle "Detail" button clicks
                        row.addEventListener("click", function (event) {
                            if (event.target && event.target.classList.contains("btn-detail")) {
                                // Prevent the default form submission
                                event.preventDefault();
                                // Find the closest form element and submit it
                                var form = event.target.closest("form");
                                if (form) {
                                    form.submit();
                                }
                            }
                        });
                    },
                    error: function (xhr) {
                        console.error("Error occurred while fetching data:", xhr);
                    }
                });
            }
        </script>
    </body>
</html>
