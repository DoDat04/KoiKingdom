/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


let isEditing = false;

function toggleEdit() {
    isEditing = !isEditing;
    const inputs = document.querySelectorAll('input[type="text"], input[type="email"]');
    const role = document.getElementById('role');
    inputs.forEach(input => {
        if (input.id !== 'role') {
            input.readOnly = !isEditing;
        }
    });

    const addressSelectDiv = document.getElementById('addressSelectDiv');
    addressSelectDiv.style.display = isEditing ? 'block' : 'none';

    const editButton = document.getElementById('editButton');
    const closeButton = document.getElementById('closeButton');
    const saveButton = document.getElementById('saveButton');
    const cancelButton = document.getElementById('cancelButton');

    if (isEditing) {
        editButton.style.display = 'none';
        saveButton.style.display = 'block';
        closeButton.style.display = 'none';
        cancelButton.style.display = 'block';
    } else {
        editButton.style.display = 'block';
        saveButton.style.display = 'none';
        closeButton.style.display = 'block';
        cancelButton.style.display = 'none';
    }
}