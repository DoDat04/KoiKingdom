/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
let quantity = 0; // Initialize quantity

function toggleButtons() {
    const addToCartBtn = document.getElementById('addToCartBtn');
    const bookNowBtn = document.getElementById('bookNowBtn');

    // Show buttons only if quantity is greater than 0
    const displayStyle = quantity > 0 ? 'block' : 'none';
    addToCartBtn.style.display = displayStyle; 
    bookNowBtn.style.display = displayStyle; 
}

// Function to update the quantity
function updateQuantity(amount) {
    quantity += amount;

    // Ensure quantity is not negative
    if (quantity < 0) {
        quantity = 0; 
    }

    // Update displayed quantity
    document.getElementById('quantityDisplay').innerText = quantity;
    // Update value for the hidden input
    document.getElementById('hiddenQuantity').value = quantity;
    document.getElementById('hiddenQuantityy').value = quantity;

    // Toggle buttons based on updated quantity
    toggleButtons();
}

// Initial setup to hide the buttons
document.addEventListener('DOMContentLoaded', () => {
    toggleButtons(); // Initial call to set button visibility
});

function toggleMoreFeedback(event) {
    event.preventDefault();
    const moreFeedback = document.querySelector('.more-feedback');
    const link = event.target;

    if (moreFeedback.style.display === "none") {
        moreFeedback.style.display = "block";
        link.innerHTML = "Show Less";
    } else {
        moreFeedback.style.display = "none";
        link.innerHTML = "Show More";
    }
}



