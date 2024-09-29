<%-- 
    Document   : headerForSales
    Created on : Sep 28, 2024, 9:13:55 PM
    Author     : ADMIN LAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/header.css">  
        <!-- Script get api province -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    </head>
    <body>
        <header class="text-black py-4" style="background-color: #fdf4f0;">
            <div class="header-content">
                <div class="d-flex justify-content-between align-items-center">
                    <!-- Logo -->
                    <div class="logo">
                        <a href="home?action=Sales">
                            <img src="img/logo.png" style="width: 185px; height: 150px;">
                        </a>
                    </div>

                    <!-- Sales Menu (e.g., Dashboard, Orders, Customers) -->
                    <div class="menu-center">
                        <ul class="nav justify-content-center">
                            <li class="nav-item">
                                <a class="nav-link text-black" href="dashboard">Dashboard</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-black" href="orders">Orders</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-black" href="customers">Customers</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-black" href="products">Products</a>
                            </li>
                        </ul>
                    </div>

                    <!-- Right Menu (Sales User Info, Settings) -->
                    <div class="menu-right d-flex align-items-center">
                        <!-- User Info with Name and Icon -->
                        <div class="user-info d-flex align-items-center">
                            <!-- Display Name -->
                            <span class="user-name text-black">
                                <c:choose>
                                    <c:when test="${sessionScope.LOGIN_SALES != null}">
                                        <span class="separator mx-2">|</span>
                                        ${sessionScope.LOGIN_SALES.firstName} ${sessionScope.LOGIN_SALES.lastName}
                                    </c:when>
                                </c:choose>
                            </span>

                            <!-- User Icon -->
                            <a href="#" class="text-black icon-size nav-link dropdown-toggle" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fas fa-user"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                                <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#updateProfileModal"><i class="fa-solid fa-user-pen"></i> Update Profile</a></li>
                                <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#changePasswordModal"><i class="fa-solid fa-key"></i> Change Password</a></li>
                                <li><a class="dropdown-item" href="home?action=Logout"><i class="fa-solid fa-right-from-bracket"></i> Sign out</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <!-- Update Profile Modal -->
        <div class="modal fade" id="updateProfileModal" tabindex="-1" aria-labelledby="updateProfileModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5 align-items-center" id="exampleModalLabel">Update Profile</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="update-profile-sales" method="post" enctype="multipart/form-data" id="profileForm">
                            <div class="text-center mb-4">
                                <div class="avatar-container">
                                    <img id="avatarPreview" 
                                         src="${sessionScope.AVATAR}" 
                                         alt="">
                                </div>
                                <input type="file" id="avatar" name="profileImage" class="form-control mt-2" accept="image/*" onchange="previewAvatar()">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="fname-addon"><i class="fa-solid fa-user"></i></span>
                                <input type="text" class="form-control" id="fname" name="fname" value="${sessionScope.LOGIN_SALES.lastName}" placeholder="First Name">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="lname-addon"><i class="fa-solid fa-user"></i></span>
                                <input type="text" class="form-control" id="lname" name="lname" value="${sessionScope.LOGIN_SALES.firstName}" placeholder="Last Name">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="email-addon"><i class="fa-solid fa-envelope"></i></span>
                                <input type="email" class="form-control" id="email" name="email" value="${sessionScope.LOGIN_SALES.email}" readonly placeholder="Email">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="address-addon"><i class="fa-solid fa-location-dot"></i></span>
                                <input type="text" class="form-control" id="address" name="address" value="${sessionScope.LOGIN_SALES.address}" readonly placeholder="Address">
                            </div>
                            <div class="mb-3 select-group" id="addressSelectDiv" style="display: none;">
                                <div class="row">
                                    <div class="col-md-4">
                                        <label for="city" class="form-label">Tỉnh / Thành:</label>
                                        <select class="form-select" id="city" name="city">
                                            <option value="">Chọn tỉnh / thành</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="district" class="form-label">Quận / Huyện:</label>
                                        <select class="form-select" id="district" name="district">
                                            <option value="">Chọn quận / huyện</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="ward" class="form-label">Phường / Xã:</label>
                                        <select class="form-select" id="ward" name="ward">
                                            <option value="">Chọn phường / xã</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <script src="js/address.js"></script>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="role-addon"><i class="fa-solid fa-user-tag"></i></span>
                                <input type="text" class="form-control" id="role" name="role" value="${sessionScope.LOGIN_SALES.role}" readonly placeholder="Role">
                            </div>

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

        <!-- Modal for Changing Password -->
        <div class="modal fade" id="changePasswordModal" tabindex="-1" aria-labelledby="changePasswordModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="changePasswordModalLabel">Change Password</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="ChangePasswordController" method="post">
                            <!-- Old Password Field -->
                            <div class="mb-3">
                                <label for="oldPassword" class="form-label">Old Password:</label>
                                <input type="password" class="form-control" id="oldPassword" name="oldPassword" placeholder="Enter your old password" required>
                            </div>

                            <!-- New Password Field -->
                            <div class="mb-3">
                                <label for="newPassword" class="form-label">New Password:</label>
                                <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Enter your new password" required>
                            </div>

                            <!-- Confirm New Password Field (Optional) -->
                            <div class="mb-3">
                                <label for="confirmPassword" class="form-label">Confirm New Password:</label>
                                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm your new password" required>
                            </div>

                            <!-- Submit Button -->
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Update Password</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Hiển thị thông tin từ request nếu có lỗi -->
    <c:if test="${not empty requestScope.updateError}">
        <p style="color:red">${requestScope.updateError}</p>
    </c:if>
    <script>
        function previewAvatar() {
            const file = document.getElementById('avatar').files[0];
            const preview = document.getElementById('avatarPreview');
            const reader = new FileReader();

            reader.onloadend = function () {
                preview.src = reader.result;
            };

            if (file) {
                reader.readAsDataURL(file);
            } else {
                preview.src = "";
            }
        }

        function toggleEdit() {
            const isReadOnly = document.getElementById('fname').readOnly;
            const fields = ['fname', 'lname', 'email', 'address', 'avatar'];
            const saveButton = document.getElementById('saveButton');
            const cancelButton = document.getElementById('cancelButton');
            const editButton = document.getElementById('editButton');
            const addressSelectDiv = document.getElementById('addressSelectDiv');

            fields.forEach(field => {
                const element = document.getElementById(field);
                if (element) {
                    element.readOnly = !isReadOnly;
                    element.disabled = !isReadOnly;
                }
            });

            // Toggle visibility of buttons and address select div
            saveButton.style.display = isReadOnly ? 'inline-block' : 'none';
            cancelButton.style.display = isReadOnly ? 'inline-block' : 'none';
            editButton.style.display = isReadOnly ? 'none' : 'inline-block';
            addressSelectDiv.style.display = isReadOnly ? 'block' : 'none';
        }
    </script>
</body>

</html>
