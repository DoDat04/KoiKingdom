/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
//Giữ vị trí scroll
function saveScrollPosition() {
    sessionStorage.setItem('scrollPosition', window.scrollY);
}

window.onload = function () {
    setTimeout(function () {
        // Kiểm tra nếu có vị trí scroll
        //Thì scroll tới chỗ đó
        const scrollPosition = sessionStorage.getItem('scrollPosition');
        if (scrollPosition) {
            window.scrollTo(0, parseInt(scrollPosition, 10));
            sessionStorage.removeItem('scrollPosition');
        }
    }, 100);
};

window.addEventListener('beforeunload', saveScrollPosition);
function removeFilters() {
    window.location.href = 'tour-list';
}



