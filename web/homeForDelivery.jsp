<%-- 
    Document   : homeForDelivery
    Created on : Sep 21, 2024, 2:15:10 PM
    Author     : Minhngo
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
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/homeForDelivery.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
           <jsp:include page="headerForDelivery.jsp" flush="true"/>
        <main>
            <div class="container">
                <div class="row d-flex justify-content-between">
                    <div class="col-md-6 column-left">
                        <div class="box1">
                            <i class="fa fa-history icon-history" aria-hidden="true"></i> <span>Shipment History</span>
                            <div class="rectangle1">
                                <div class="heading heading-rectangle1">
                                    <div class="title">All</div>
                                    <div class="title">Delivered</div>
                                    <div class="title">In Progress</div>
                                </div>
                                <div class="body body-rectangle1 row" style=" margin: 0px">

                                    <div class="row">
                                        <div class="col-md-6"> Tracking number</div>
                                        <div class="col-md-6 d-flex justify-content-end">Status</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">#123456789</div>
                                        <div class="col-md-6 d-flex justify-content-end">Delivery</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 "><i class="material-icons">&#xe55c;</i> <span>Từ trạm</span></div>
                                        <div class="col-md-6 d-flex justify-content-end" style="margin-top:0px;">Ngày và giờ giao</div>
                                    </div>           
                                    <div class="vl"></div>     
                                    <div class="row">
                                        <div class="col-md-6 "><i class="material-icons">&#xe55f;</i> <span>Đến nhà khách</span></div>
                                        <div class="col-md-6 d-flex justify-content-end" >Ngày và giờ đã giao</div>
                                    </div>
                                </div>


                            </div>
                        </div>
                        <div class="box2">
                            <i style="font-size:24px" class='far icon-notify'>&#xf0f3;</i> <span>Notification</span>
                            <div class="rectangle2">                             
                                <div class="body body-rectangle2 row" style=" margin: 0px;">
                                    <div class="row">
                                        <div> The status of #85425456665425 
                                            has changed to Deliveried. 
                                            20/02/2021    17:53PM
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 column-right"> 
                        <div class="box3">
                            <div class="rectangle3">
                                <div class="heading heading-rectangle3">
                                    <a href="#" onclick="loadPage('orderDetail.jsp'); return false;"  class="tab active " id="orderDetailTab">Order detail</a>
                                    <a href="#"  onclick="loadPage('customerInfo.jsp'); return false;" class="tab" id="customerInfoTab">Customer information</a>
                                    <a href="#" onclick="loadPage('trackingProduct.jsp'); return false;" class="tab" id="trackingProductTab">Tracking product</a>
                                </div> 
                                <div id="content">
                                    <jsp:include page="orderDetail.jsp" />
                                </div>
                            </div>

                            <script>
                                function loadPage(page) {
                                    // Make an AJAX request to load the page content
                                    var xhr = new XMLHttpRequest();
                                    xhr.open('GET', page, true);
                                    xhr.onreadystatechange = function () {
                                        if (xhr.readyState === 4 && xhr.status === 200) {
                                            document.getElementById('content').innerHTML = xhr.responseText;
                                            updateActiveTab(page);
                                        }
                                    };
                                    xhr.send();
                                }

                                function updateActiveTab(page) {
                                    // Update the active class for tabs
                                    document.getElementById('orderDetailTab').classList.remove('active');
                                    document.getElementById('customerInfoTab').classList.remove('active');
                                    document.getElementById('trackingProductTab').classList.remove('active');

                                    if (page === 'orderDetail.jsp') {
                                        document.getElementById('orderDetailTab').classList.add('active');
                                    } else if (page === 'customerInfo.jsp') {
                                        document.getElementById('customerInfoTab').classList.add('active');
                                    } else if (page === 'trackingProduct.jsp') {
                                        document.getElementById('trackingProductTab').classList.add('active');
                                    }
                                }
                            </script>
                        </div>

                    </div>
                </div>
            </div>

        </div>
    </div>
</main>
</body>
</html>
