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

function showToast(msg, type) {
    let toast = document.createElement('div');
    toast.classList.add('toast', 'fade');
    toast.setAttribute('role', 'alert');

    // Thay đổi màu sắc dựa vào loại thông báo
    if (type === 'success') {
        toast.classList.add('bg-success', 'text-white'); // Màu xanh lá
    } else if (type === 'error') {
        toast.classList.add('bg-danger', 'text-white'); // Màu đỏ
    }

    let content = document.createElement('span');
    content.innerText = msg;

    let closeButton = document.createElement('button');
    closeButton.classList.add('btn-close');
    closeButton.onclick = function () {
        removeToast(toast);
    };

    toast.appendChild(content);
    toast.appendChild(closeButton);
    document.getElementById('toastBox').appendChild(toast);

    // Tự động xóa sau 6 giây
    setTimeout(() => {
        removeToast(toast);
    }, 6000);

    // Hiện thông báo
    new bootstrap.Toast(toast).show();
}

function removeToast(toast) {
    toast.remove();
}