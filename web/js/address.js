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

const host = "https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province";
const token = '5083b8cf-8d0a-11ef-8e53-0a00184fe694'; 
// Function to call API and fetch provinces
const callApiProvince = () => {
    return axios.get(host, {
        headers: {
            'Content-Type': 'application/json',
            'Token': token
        }
    })
    .then((response) => {
        // Assuming the response has a `data` property containing the province list
        renderData(response.data.data, "city"); // Render provinces in the city select
    })
    .catch((error) => {
        console.error("Error fetching provinces:", error);
    });
};

// Function to call API and fetch districts based on province ID
const callApiDistrict = (provinceId) => {
    console.log("Fetching districts for province ID:", provinceId); // Log the province ID
    return axios.get(`https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/district`, {
        headers: {
            'Content-Type': 'application/json',
            'Token': token
        },
        params: {
            province_id: provinceId // Sending province_id as a query parameter
        }
    })
    .then((response) => {
        if (response.data && response.data.data) {
            console.log("Districts fetched successfully:", response.data); // Log success
            renderData(response.data.data, "district"); // Render districts
        } else {
            console.error("No data found in response:", response.data);
        }
    })
    .catch((error) => {
        console.error("Error fetching districts:", error);
        if (error.response) {
            console.error("Response data:", error.response.data); // Log response data for debugging
            console.error("Response status:", error.response.status); // Log response status for debugging
        }
    });
};

// Function to call API and fetch wards based on district ID
const callApiWard = (districtId) => {
    console.log("Fetching wards for district ID:", districtId); // Log the district ID
    return axios.get(`https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/ward`, {
        headers: {
            'Content-Type': 'application/json',
            'Token': token
        },
        params: {
            district_id: districtId // Sending district_id as a query parameter
        }
    })
    .then((response) => {
        if (response.data && response.data.data) {
            console.log("Wards fetched successfully:", response.data); // Log success
            renderData(response.data.data, "ward"); // Render wards
        } else {
            console.error("No data found in response:", response.data);
        }
    })
    .catch((error) => {
        console.error("Error fetching wards:", error);
        if (error.response) {
            console.error("Response data:", error.response.data); // Log response data for debugging
            console.error("Response status:", error.response.status); // Log response status for debugging
        }
    });
};

// Function to render data in the select dropdown
const renderData = (array, select) => {
    let row = '<option selected value="">Select ';
    if (select === 'city') {
        row += 'Province';
    } else if (select === 'district') {
        row += 'District';
    } else if (select === 'ward') {
        row += 'Ward';
    }
    row += '</option>';

    array.forEach(element => {
        if (select === 'city') {
            row += `<option data-id="${element.ProvinceID}" value="${element.ProvinceName}">${element.NameExtension[1]}</option>`;
        } else if (select === 'district') {
            row += `<option data-id="${element.DistrictID}" value="${element.DistrictName}">${element.DistrictName}</option>`;
        } else if (select === 'ward') {
            row += `<option data-id="${element.WardCode}" value="${element.WardName}">${element.WardName}</option>`;
        }
    });

    document.querySelector("#" + select).innerHTML = row;
};

// Event listeners for the select elements
document.getElementById("city").addEventListener("change", () => {
    const selectedCity = document.getElementById("city").selectedOptions[0];
    if (selectedCity && selectedCity.dataset.id) {
        callApiDistrict(selectedCity.dataset.id);
        document.getElementById("district").innerHTML = '<option disabled selected value="">Select District</option>'; // Reset district
        document.getElementById("ward").innerHTML = '<option disabled selected value="">Select Ward</option>'; // Reset ward
    }
});

document.getElementById("district").addEventListener("change", () => {
    const selectedDistrict = document.getElementById("district").selectedOptions[0];
    if (selectedDistrict && selectedDistrict.dataset.id) {
        callApiWard(selectedDistrict.dataset.id);
        document.getElementById("ward").innerHTML = '<option disabled selected value="">Select Ward</option>'; // Reset ward
    }
});

// Initialize the application
async function initialize() {
    console.log("Fetching provinces on page load...");
    await callApiProvince();
}

window.onload = initialize;
