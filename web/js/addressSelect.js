/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
const host = "https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province";
const token = '5083b8cf-8d0a-11ef-8e53-0a00184fe694'; 

// Function to call API and fetch provinces
const callApiProvince = (selectId) => {
    return axios.get(host, {
        headers: {
            'Content-Type': 'application/json',
            'Token': token
        }
    })
    .then((response) => {
        renderData(response.data.data, selectId); // Render provinces in the select
    })
    .catch((error) => {
        console.error("Error fetching provinces:", error);
    });
};

// Function to call API and fetch districts based on province ID
const callApiDistrict = (provinceId, selectId) => {
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
            renderData(response.data.data, selectId); // Render districts
        } else {
            console.error("No data found in response:", response.data);
        }
    })
    .catch((error) => {
        console.error("Error fetching districts:", error);
    });
};

// Function to call API and fetch wards based on district ID
const callApiWard = (districtId, selectId) => {
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
            renderData(response.data.data, selectId); // Render wards
        } else {
            console.error("No data found in response:", response.data);
        }
    })
    .catch((error) => {
        console.error("Error fetching wards:", error);
    });
};

// Function to render data in the select dropdown
const renderData = (array, selectId) => {
    let row = '<option selected value="">Select ';
    if (selectId.includes('city')) {
        row += 'Province';
    } else if (selectId.includes('district')) {
        row += 'District';
    } else if (selectId.includes('ward')) {
        row += 'Ward';
    }
    row += '</option>';

    array.forEach(element => {
        if (selectId.includes('city')) {
            row += `<option data-id="${element.ProvinceID}" value="${element.ProvinceName}">${element.NameExtension[1]}</option>`;
        } else if (selectId.includes('district')) {
            row += `<option data-id="${element.DistrictID}" value="${element.DistrictName}">${element.DistrictName}</option>`;
        } else if (selectId.includes('ward')) {
            row += `<option data-id="${element.WardCode}" value="${element.WardName}">${element.WardName}</option>`;
        }
    });

    const selectElement = document.querySelector("#" + selectId);
    if (selectElement) {
        selectElement.innerHTML = row;
    } else {
        console.error("Select element not found:", selectId);
    }
};

// Function to set up event listeners for select elements
const setupEventListeners = (cityId, districtId, wardId) => {
    const citySelect = document.getElementById(cityId);
    const districtSelect = document.getElementById(districtId);
    const wardSelect = document.getElementById(wardId);

    if (citySelect) {
        citySelect.addEventListener("change", () => {
            const selectedCity = citySelect.selectedOptions[0];
            if (selectedCity && selectedCity.dataset.id) {
                callApiDistrict(selectedCity.dataset.id, districtId);
                districtSelect.innerHTML = '<option disabled selected value="">Select District</option>'; // Reset district
                wardSelect.innerHTML = '<option disabled selected value="">Select Ward</option>'; // Reset ward
            }
        });
    }

    if (districtSelect) {
        districtSelect.addEventListener("change", () => {
            const selectedDistrict = districtSelect.selectedOptions[0];
            if (selectedDistrict && selectedDistrict.dataset.id) {
                callApiWard(selectedDistrict.dataset.id, wardId);
                wardSelect.innerHTML = '<option disabled selected value="">Select Ward</option>'; // Reset ward
            }
        });
    }
};

// Initialize the application
async function initialize() {
    console.log("Fetching provinces on page load...");
    await callApiProvince("city");
    await callApiProvince("cityy"); // Fetch for second select as well
    setupEventListeners("city", "district", "ward");
    setupEventListeners("cityy", "districtt", "wardd");
}

window.onload = initialize;


