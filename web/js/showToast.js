/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function showToast(msg, type) {
    let toast = document.createElement('div');
    toast.classList.add('custom-toast');

    // Apply background color based on the type
    if (type === 'success') {
        toast.style.backgroundColor = '#28a745';
    } else if (type === 'error') {
        toast.style.backgroundColor = '#dc3545';
    } else {
        toast.style.backgroundColor = '#6c757d';
    }

    let content = document.createElement('span');
    content.innerText = msg;
    content.style.flexGrow = '1';

    let closeButton = document.createElement('button');
    closeButton.classList.add('close-btn');
    closeButton.innerHTML = 'X';
    closeButton.onclick = function () {
        removeToast(toast);
    };

    toast.appendChild(content);
    toast.appendChild(closeButton);
    document.getElementById('toastBox').appendChild(toast);

    setTimeout(() => {
        removeToast(toast);
    }, 6000);

    toast.style.opacity = '0';
    toast.style.transition = 'opacity 0.5s';
    setTimeout(() => {
        toast.style.opacity = '1';
    }, 0);
}

// Remove toast when close button is clicked
function removeToast(toast) {
    toast.remove();
}
