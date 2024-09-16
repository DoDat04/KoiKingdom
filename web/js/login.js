/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.addEventListener('DOMContentLoaded', function () {
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

    new Swiper(".carousel", {
        loop: true, 
        spaceBetween: 10, 
        grabCursor: true, 

        pagination: {
            el: ".swiper-pagination", 
            clickable: true 
        },

        navigation: {
            nextEl: ".swiper-button-next", 
            prevEl: ".swiper-button-prev" 
        },

        autoplay: {
            delay: 5000, 
            disableOnInteraction: false 
        },

        breakpoints: {
            600: {
                slidesPerView: 1 
            },
            968: {
                slidesPerView: 1 
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




