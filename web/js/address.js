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

const host = "https://provinces.open-api.vn/api/";
var callAPI = (api) => {
    return axios.get(api)
        .then((response) => {
            console.log(response.data); // Check the response data
            renderData(response.data, "city");
        })
        .catch((error) => {
            console.error("Error fetching cities:", error);
        });
};

callAPI('https://provinces.open-api.vn/api/?depth=1');

var callApiDistrict = (api) => {
    return axios.get(api)
        .then((response) => {
            console.log(response.data); // Check the response data
            renderData(response.data.districts, "district");
        })
        .catch((error) => {
            console.error("Error fetching districts:", error);
        });
};

var callApiWard = (api) => {
    return axios.get(api)
        .then((response) => {
            console.log(response.data); // Check the response data
            renderData(response.data.wards, "ward");
        })
        .catch((error) => {
            console.error("Error fetching wards:", error);
        });
};

var renderData = (array, select) => {
    let row = '<option disable value="">Chọn ';
    if (select === 'city') {
        row += 'tỉnh / thành';
    } else if (select === 'district') {
        row += 'quận / huyện';
    } else if (select === 'ward') {
        row += 'phường / xã';
    }
    row += '</option>';

    array.forEach(element => {
        row += `<option data-id="${element.code}" value="${element.name}">${element.name}</option>`;
    });
    document.querySelector("#" + select).innerHTML = row;
};

$("#city").change(() => {
    const selectedCity = $("#city").find(':selected');
    if (selectedCity.data('id')) {
        callApiDistrict(host + "p/" + selectedCity.data('id') + "?depth=2");
    }
});

$("#district").change(() => {
    const selectedDistrict = $("#district").find(':selected');
    if (selectedDistrict.data('id')) {
        callApiWard(host + "d/" + selectedDistrict.data('id') + "?depth=2");
    }
});

$("#ward").change(() => {
    // Logic if needed when ward changes
});


