/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.addEventListener('DOMContentLoaded', function() {
    // Lắng nghe sự kiện khi modal được đóng
    const updateProfileModal = document.getElementById('updateProfileModal');
    
    updateProfileModal.addEventListener('hidden.bs.modal', function () {
        document.getElementById('homeAddressDiv').style.display = 'none';
        document.getElementById('addressSelectDiv').style.display = 'none';
    });

    document.getElementById('editAddressBtn').addEventListener('click', function() {
        document.getElementById('homeAddressDiv').style.display = 'block';
        document.getElementById('addressSelectDiv').style.display = 'flex';
    });
});


