<%-- 
    Document   : customerInfo
    Created on : Sep 21, 2024, 2:30:24 PM
    Author     : Minhngo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="css/customerInfoDelivery.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <form style="background-color: #d3c3c3">   

            <label for="fname">Họ:</label>
            <input type="text" id="fname" name="fname" placeholder="Nhập họ của bạn">

            <label for="lname">Tên:</label>
            <input type="text" id="lname" name="lname" placeholder="Nhập tên của bạn">

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Nhập địa chỉ email của bạn">

            <label for="address">Địa   
                chỉ:</label>
            <textarea id="address" name="address" placeholder="Nhập địa chỉ của bạn"></textarea>

            <label for="phone">Số điện thoại:</label>
            <input type="text" id="phone" name="phone" placeholder="Nhập số điện thoại của bạn">

            <label for="city">Thành   
                phố:</label>
            <input type="text" id="city" name="city" placeholder="Nhập thành phố">

            <label for="state">Tỉnh/Thành:</label>
            <input type="text" id="state" name="state" placeholder="Nhập tỉnh/thành">
        </form>
    </body>
</html>
