/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



function showToast(msg, type) {
    let toast = document.createElement('div');
    toast.classList.add('toast');

    if (type === 'success') {
        toast.style.backgroundColor = '#28a745'; // Màu xanh lá cho thành công
        toast.style.color = '#fff'; // M
    } else if (type === 'error') {
        toast.style.backgroundColor = '#dc3545'; // Màu đỏ cho lỗi
        toast.style.color = '#fff';
    } else {
        toast.style.backgroundColor = '#6c757d'; // Màu xám cho loại thông báo không xác định
        toast.style.color = '#fff'; // Màu chữ trắng
    }

    let content = document.createElement('span');
    content.innerText = msg;
    content.style.flexGrow = '1';

    let closeButton = document.createElement('button');
    closeButton.classList.add('btn-close-toast');
    closeButton.innerHTML = '&times;';
    closeButton.onclick = function () {
        removeToast(toast);
    };

    toast.appendChild(content);
    toast.appendChild(closeButton);
    document.getElementById('toastBox').appendChild(toast);

    setTimeout(() => {
        removeToast(toast);
    }, 6000);

        // Hiển thị toast với hiệu ứng
    toast.style.opacity = '0';
    toast.style.transition = 'opacity 0.5s';
    setTimeout(() => {
        toast.style.opacity = '1';
    }, 0);
}

function removeToast(toast) {

        toast.style.opacity = '0';
    toast.style.transition = 'opacity 0.5s';
    setTimeout(() => {
        toast.remove();
    }, 500);
}
