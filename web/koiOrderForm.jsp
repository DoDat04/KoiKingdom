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
        <jsp:include flush="true" page="headerForConsulting.jsp"/>
        <div style="margin-top: 25vh; margin-left: 17%;" class="main-content">  
            <div class="container">
                <h2 style="text-align: center; color: #4CAF50;"><strong>Create Koi Order</strong></h2>
                <form class="form-center" action="create-koi-order" method="POST" style="max-width: 600px; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);">
                    <div class="mb-3" style="margin-bottom: 15px;">
                        <label for="txtCustID" style="font-weight: bold;">Customer ID</label>
                        <input type="number" id="txtCustID" name="txtCustID" required style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
                    </div>
                    <div class="mb-3" style="margin-bottom: 15px;">
                        <label for="txtDelivery" style="font-weight: bold;">Order Date</label>
                        <input type="date" id="txtDelivery" name="txtDelivery" required style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
                    </div>
                    <h3 style="text-align: center; color: #4CAF50;">Koi Order Details</h3>
                    <div id="orderDetails">
                        <div class="detailRow" style="display: flex; flex-wrap: wrap; gap: 10px; justify-content: space-between;">
                            <!-- Select Farm -->
                            <div class="mb-3" style="flex: 1 1 calc(50% - 10px); margin-bottom: 15px;">
                                <label for="txtFarmIDs" style="font-weight: bold;">Select Farm</label>
                                <select id="txtFarmIDs" name="txtFarmIDs" required style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;" onchange="updateKoiTypeDropdown()">
                                    <option value="">-- Select Farm --</option>
                                    <c:forEach var="farm" items="${LIST_FARM}">
                                        <option value="${farm.farmID}">${farm.farmName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- Select Koi Type -->
                            <div class="mb-3" style="flex: 1 1 calc(50% - 10px); margin-bottom: 15px;">
                                <label for="txtKoiTypeID" style="font-weight: bold;">Select Koi Type</label>
                                <select id="txtKoiTypeID" name="txtKoiTypeID" required style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;" onchange="updateKoiDropdown()">
                                    <option value="">-- Select Koi Type --</option>
                                </select>
                            </div>

                            <!-- Select Koi -->
                            <div class="mb-3" style="flex: 1 1 calc(50% - 10px); margin-bottom: 15px;">
                                <label for="txtKoiIDs" style="font-weight: bold;">Select Koi</label>
                                <select id="txtKoiIDs" name="txtKoiIDs" required style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
                                    <option value="">-- Select Koi --</option>
                                </select>
                            </div>
                            <div class="mb-3" style="flex: 1 1 calc(50% - 10px); margin-bottom: 15px;">
                                <label for="txtQuantity" style="font-weight: bold;">Quantity</label>
                                <input type="number" id="txtQuantity" min="1" max="50" name="txtQuantity" required style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
                            </div>
                        </div>
                    </div>

                    <div class="d-flex justify-content-between">
                        <div class="col-md-6 d-flex justify-content-center">
                            <a class="btn btn-secondary" href="home?action=Consulting" style="color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;">Return</a>
                        </div>
                        <div class="col-md-6 d-flex justify-content-center">
                            <input type="submit" value="Submit Order" style="background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;">
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <script>
            function updateKoiTypeDropdown() {
                var farmID = document.getElementById("txtFarmIDs").value;
                var koiTypeSelect = document.getElementById("txtKoiTypeID");
                koiTypeSelect.innerHTML = '<option value="">-- Select Koi Type --</option>'; // Reset options

                if (farmID) {
                    fetch('getKoiData?action=getKoiTypeByFarm&farmID=' + farmID)
                            .then(response => response.json())
                            .then(data => {
                                data.forEach(koiType => {
                                    var option = document.createElement("option");
                                    option.value = koiType.koiTypeID;
                                    option.textContent = koiType.typeName;
                                    koiTypeSelect.appendChild(option);
                                });
                            })
                            .catch(error => console.error('Error fetching koi type data:', error));
                }
            }

            function updateKoiDropdown() {
                var koiTypeID = document.getElementById("txtKoiTypeID").value;
                var koiSelect = document.getElementById("txtKoiIDs");
                koiSelect.innerHTML = '<option value="">-- Select Koi --</option>'; // Reset options

                if (koiTypeID) {
                    fetch('getKoiData?action=getKoiByKoiType&koiTypeID=' + koiTypeID)
                            .then(response => response.json())
                            .then(data => {
                                data.forEach(koi => {
                                    var option = document.createElement("option");
                                    option.value = koi.koiID;
                                    option.textContent = koi.koiName;
                                    koiSelect.appendChild(option);
                                });
                            })
                            .catch(error => console.error('Error fetching koi data:', error));
                }
            }
        </script>

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