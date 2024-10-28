/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function saveScrollPosition() {
    sessionStorage.setItem('scrollPosition', window.scrollY);
}

window.onload = function () {
    setTimeout(function () {
        // Check if there is a saved scroll position
        const scrollPosition = sessionStorage.getItem('scrollPosition');
        if (scrollPosition) {
            window.scrollTo(0, parseInt(scrollPosition, 10));
            sessionStorage.removeItem('scrollPosition');
        }
    }, 100);
};

window.addEventListener('beforeunload', saveScrollPosition);

function removeFilters() {
    window.location.href = 'koi-list-order';
}

// Function to update quantity
function updateQuantity(koiID, change) {
    const quantityInput = document.getElementById(`quantity-${koiID}`);
    const quantityHiddenInput = document.getElementById(`quantity-input-${koiID}`);
    
    let currentQuantity = parseInt(quantityInput.value, 10);
    currentQuantity += change;

    if (currentQuantity < 0) {
        currentQuantity = 0; // Prevent negative quantity
    }

    quantityInput.value = currentQuantity; // Update input field
    quantityHiddenInput.value = currentQuantity; // Update hidden input value
}




