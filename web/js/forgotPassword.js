/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.addEventListener('DOMContentLoaded', function () {      
    new Swiper(".carousel", {
        loop: true, // Cho phép lặp lại các slide
        spaceBetween: 10, // Khoảng cách giữa các slides
        grabCursor: true, // Hiển thị con trỏ khi kéo slide

        pagination: {
            el: ".swiper-pagination", // Phần tử để hiển thị các chấm phân trang
            clickable: true // Cho phép người dùng click vào các chấm để chuyển slide
        },

        navigation: {
            nextEl: ".swiper-button-next", // Nút chuyển slide tiếp theo
            prevEl: ".swiper-button-prev" // Nút quay lại slide trước
        },

        autoplay: {
            delay: 5000, // Thời gian chờ giữa các slide (3 giây)
            disableOnInteraction: false // Không tắt autoplay khi người dùng tương tác
        },

        breakpoints: {
            600: {
                slidesPerView: 1 // Hiển thị 1 slide khi màn hình lớn hơn 600px
            },
            968: {
                slidesPerView: 1 // Hiển thị 1 slide khi màn hình lớn hơn 968px
            }
        }
    });
});

function showModal(error) {
    var errorMessageDiv = document.getElementById("error-message");
    errorMessageDiv.innerText = error;
    var modal = document.getElementById("error-modal");
    modal.style.display = "block";      
}

function closeModal() {
    var modal = document.getElementById("error-modal");
    modal.style.display = "none";
}

window.onclick = function (event) {
    var modal = document.getElementById("error-modal");
    if (event.target === modal) {
        modal.style.display = "none";
    }
};



