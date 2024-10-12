<%-- 
    Document   : managerDashboard
    Created on : Sep 24, 2024, 4:18:35 PM
    Author     : Nguyen Huu Khoan
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/homeForDelivery.css" rel="stylesheet">
        <title>Manager Dashboard</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
    </head>
    <body>
        <jsp:include page="headerForManager.jsp" flush="true"/>   
        <div style="margin-top: 25vh; margin-left: 17%; margin-right: 6%;" class="main-content">         
            <c:choose>                       
                <c:when test="${sessionScope.LOGIN_MANAGER != null}"> 
                    <div class="container">
                        <!--                    <h1>
                                                Welcome
                                                @<span>${sessionScope.LOGIN_MANAGER.firstName} ${sessionScope.LOGIN_MANAGER.lastName}</span> 
                                                to Manager Dashboard.
                                            </h1>-->
                        <form action="count" method="GET">
                            <div class="row">
                                <div class="search col-md-5">
                                    <label for="startDateFilter" class="form-label">From Date</label>
                                    <input type="date" id="startDateFilter" name="startDate" class="form-control" placeholder="dd/mm/yyyy" value="${param.startDate}" >
                                </div>
                                <div class="search col-md-5">
                                    <label for="endDateFilter" class="form-label">To Date</label>
                                    <input type="date" id="endDateFilter" name="endDate" class="form-control" placeholder="dd/mm/yyyy" value="${param.endDate}" >
                                </div>
                                <!-- onchange="this.form.submit()" -->
                                <div class="search col-md-1 d-flex justify-content-center align-items-end">
                                    <input type="submit" value="Filter" class="btn btn-primary"/>

                                </div>
                                <div class="search col-md-1 d-flex justify-content-center align-items-end">

                                    <button type="button" class="btn btn-secondary ms-2" onclick="resetForm()">Reset</button>
                                </div>
                            </div> <!-- End row -->
                        </form>                           
                        <div class="dashboard d-flex flex-wrap justify-content-center">

                            <div class="row d-flex w-100">
                                <div class="card col-md-6">
                                    <h3>Total Sold Tours</h3>
                                    <div class="number">${BOOKING_COUNT}</div>
                                </div>
                                <div class="card col-md-6">
                                    <h3>Total KOI Orders</h3>
                                    <div class="number">${ORDER_COUNT}</div>
                                </div>
                            </div>

                            <div class="row d-flex w-100">
                                <div class="card col-md-6">
                                    <h3>Total Customers</h3>
                                    <div class="number">${CUSTOMER_COUNT}</div>
                                </div>
                                <div class="card col-md-6">
                                    <h3>Total Revenue</h3>
                                    <div class="number" >$${REVENUE_COUNT}</div>
                                </div>
                            </div>

                        </div>



                    </div>
                    <script>
                        function resetForm() {
                            window.location.href = "count"; // Đường dẫn đến trang mà không có tham số
                        }
                    </script>
                    <style>
                        .container{
                            text-align: center;
                        }
                        .dashboard {
                            display: flex; /* Sử dụng Flexbox */
                            flex-direction: column; /* Căn theo chiều dọc */
                            align-items: center; /* Căn giữa các phần tử */
                            margin-top: 20px; /* Khoảng cách phía trên */
                        }

                        .card {
                            background-color: white;
                            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
                            border-radius: 10px;
                            width: 100%; /* Đặt chiều rộng là 100% */
                            max-width: 500px; /* Đặt chiều rộng tối đa */
                            text-align: center;
                            padding: 20px;
                            margin: 10px; /* Khoảng cách giữa các card */
                        }

                        .card h3 {
                            margin-bottom: 15px;
                            color: #888;
                        }

                        .card .number {
                            font-size: 36px;
                            color: #333;
                        }

                        .card .number {
                            font-size: 36px;
                            color: #333;
                        }

                    </style>

                </c:when>
            </c:choose>


        </div>
    </body>

</html>

