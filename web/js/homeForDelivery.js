/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function loadPage(page) {
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
    const tabs = ['orderDetailTab', 'customerInfoTab', 'trackingProductTab'];
    tabs.forEach(tab => document.getElementById(tab).classList.remove('active'));
    document.getElementById(page === 'orderDetail.jsp' ? 'orderDetailTab' :
            page === 'customerInfo.jsp' ? 'customerInfoTab' :
            'trackingProductTab').classList.add('active');
}
