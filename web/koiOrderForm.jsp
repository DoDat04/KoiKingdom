<%-- 
    Document   : koiOrderForm
    Created on : Oct 7, 2024, 10:23:17 PM
    Author     : ADMIN LAM
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <h2 style="text-align: center; color: #4CAF50;">Create Koi Order</h2>

        <form class="form-center" action="create-koi-order" method="POST" style="max-width: 600px; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);">
            <div class="mb-3" style="margin-bottom: 15px;">
                <label for="txtCustID" style="font-weight: bold;">Customer ID</label>
                <input type="number" id="txtCustID" name="txtCustID" required style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
            </div>
            <div class="mb-3" style="margin-bottom: 15px;">
                <label for="txtDelivery" style="font-weight: bold;">Delivery Date</label>
                <input type="date" id="txtDelivery" name="txtDelivery" required style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
            </div>
            <div class="mb-3" style="margin-bottom: 15px;">
                <label for="txtEstimate" style="font-weight: bold;">Estimated Delivery Date</label>
                <input type="date" id="txtEstimate" name="txtEstimate" required style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
            </div>

            <h3 style="text-align: center; color: #4CAF50;">Koi Order Details</h3>
            <div id="orderDetails">
                <div class="detailRow" style="display: flex; flex-wrap: wrap; gap: 10px; justify-content: space-between;">
                    <div class="mb-3" style="flex: 1 1 calc(50% - 10px); margin-bottom: 15px;">
                        <label for="txtKoiIDs" style="font-weight: bold;">Koi ID</label>
                        <input type="number" id="txtKoiIDs" min="1" name="txtKoiIDs" required style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
                    </div>
                    <div class="mb-3" style="flex: 1 1 calc(50% - 10px); margin-bottom: 15px;">
                        <label for="txtFarmIDs" style="font-weight: bold;">Farm ID</label>
                        <input type="number" id="txtFarmIDs" min="1" name="txtFarmIDs" required style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
                    </div>
                    <div class="mb-3" style="flex: 1 1 calc(50% - 10px); margin-bottom: 15px;">
                        <label for="txtQuantity" style="font-weight: bold;">Quantity</label>
                        <input type="number" id="txtQuantity" min="1" max="50" name="txtQuantity" required style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
                    </div>
                    <div class="mb-3" style="flex: 1 1 calc(50% - 10px); margin-bottom: 15px;">
                        <label for="txtUnitPrice" style="font-weight: bold;">Unit Price</label>
                        <input type="number" step="0.01" id="txtUnitPrice" name="txtUnitPrice" required style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
                    </div>
                    <div class="mb-3" style="flex: 1 1 calc(50% - 10px); margin-bottom: 15px;">
                        <label for="txtTotalPrice" style="font-weight: bold;">Total Price</label>
                        <input type="number" step="0.01" id="txtTotalPrice" name="txtTotalPrice" required style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
                    </div>
                </div>
            </div>

            <br>
            <div style="text-align: center;">
                <input type="submit" value="Submit Order" style="background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;">
            </div>
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
                showToast('${sessionScope.errorMessage}', 'error');
            };
        </script>
        <c:set var="errorMessage" value="${null}" scope="session"/>
    </c:if>
    <div id="toastBox"></div>
    <script src="js/showToast.js"></script>

</body>
</html>

