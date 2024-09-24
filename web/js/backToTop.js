/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
// Hiện nút khi cuộn xuống
window.onscroll = function () {
    const button = document.getElementById("backToTop");
    if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
        button.style.display = "flex";
    } else {
        button.style.display = "none";
    }
};

// Cuộn lên đầu trang khi nhấn nút
document.getElementById("backToTop").onclick = function () {
    window.scrollTo({top: 0, behavior: 'smooth'});
};

