/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
// Function to open the Terms of Service & Privacy Policy modal
function openTermsModal() {
    event.preventDefault(); 
    document.getElementById("terms-modal").style.display = "block";
}

// Function to close the Terms of Service & Privacy Policy modal
function closeTermsModal() {
    document.getElementById("terms-modal").style.display = "none";
}

document.addEventListener('DOMContentLoaded', function() {
    var passwordField = document.getElementById('password');
    var passwordIcon = document.getElementById('togglePasswordIcon');

    passwordIcon.addEventListener('click', function () {
        if (passwordField.type === 'password') {
            passwordField.type = 'text';
            passwordIcon.classList.remove('fa-eye');
            passwordIcon.classList.add('fa-eye-slash');
        } else {
            passwordField.type = 'password';
            passwordIcon.classList.remove('fa-eye-slash');
            passwordIcon.classList.add('fa-eye');
        }
    });

    var confirmPasswordField = document.getElementById('confirmPassword');
    var confirmPasswordIcon = document.getElementById('toggleConfirmPasswordIcon');

    confirmPasswordIcon.addEventListener('click', function () {
        if (confirmPasswordField.type === 'password') {
            confirmPasswordField.type = 'text';
            confirmPasswordIcon.classList.remove('fa-eye');
            confirmPasswordIcon.classList.add('fa-eye-slash');
        } else {
            confirmPasswordField.type = 'password';
            confirmPasswordIcon.classList.remove('fa-eye-slash');
            confirmPasswordIcon.classList.add('fa-eye');
        }
    });

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

