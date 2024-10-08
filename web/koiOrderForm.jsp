<%-- 
    Document   : koiOrderForm
    Created on : Oct 7, 2024, 10:23:17 PM
    Author     : ADMIN LAM
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Koi Order</title>
        <link rel="icon" href="img/logo-web.png" type="image/x-icon" sizes="any">
        <link href="css/toast.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    </head>
    <body>
        <h2>Create Koi Order</h2>

        <form action="create-koi-order" method="POST">
            <label for="txtCustID">Customer ID:</label>
            <input type="number" id="txtCustID" name="txtCustID" required><br><br>

            <label for="txtDelivery">Delivery Date:</label>
            <input type="date" id="txtDelivery" name="txtDelivery" required><br><br>

            <label for="txtEstimate">Estimated Delivery Date:</label>
            <input type="date" id="txtEstimate" name="txtEstimate" required><br><br>

            <h3>Koi Order Details</h3>
            <div id="orderDetails">
                <div class="detailRow">
                    <label for="txtKoiIDs">Koi ID:</label>
                    <input type="number" id="txtFarmID" min="1" name="txtKoiIDs" required>

                    <label for="txtFarmIDs">Farm ID:</label>
                    <input type="number" id="txtFarmID" min="1" name="txtFarmIDs" required>

                    <label for="txtQuantity">Quantity:</label>
                    <input type="number" id="txtQuantity" min="1" max="50" name="txtQuantity" required>

                    <label for="txtUnitPrice">Unit Price:</label>
                    <input type="number" step="0.01" id="txtUnitPrice" name="txtUnitPrice" required>

                    <label for="txtTotalPrice">Total Price:</label>
                    <input type="number" step="0.01" id="txtTotalPrice" name="txtTotalPrice" required>
                </div>
            </div>

            <br>
            <input type="submit" value="Submit Order">
        </form>

    <c:if test="${not empty sessionScope.message}">
        <script>
            window.onload = function () {
                showToast('${sessionScope.message}', 'success');
            };
        </script>
        <c:set var="message" value="${null}" scope="session"/>
    </c:if>

    <c:if test="${not empty sessionScope.errorMessage}">
        <script>
            window.onload = function () {
                showToast('${sessionScope.errorMessage}', 'success');
            };
        </script>
        <c:set var="errorMessage" value="${null}" scope="session"/>
    </c:if>
    <div id="toastBox"></div>
    <script src="js/showToast.js"></script>

</body>
</html>

