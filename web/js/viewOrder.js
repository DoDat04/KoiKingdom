/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
const hostt = "https://provinces.open-api.vn/api/";
var callAPI = (api) => {
    return axios.get(api)
        .then((response) => {
            console.log(response.data); // Check the response data
            renderData(response.data, "cityy");
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
            renderData(response.data.districts, "districtt");
        })
        .catch((error) => {
            console.error("Error fetching districts:", error);
        });
};

var callApiWard = (api) => {
    return axios.get(api)
        .then((response) => {
            console.log(response.data); // Check the response data
            renderData(response.data.wards, "wardd");
        })
        .catch((error) => {
            console.error("Error fetching wards:", error);
        });
};

var renderData = (array, select) => {
    let row = '<option disable value="">Select ';
    if (select === 'cityy') {
        row += 'province';
    } else if (select === 'districtt') {
        row += 'district';
    } else if (select === 'wardd') {
        row += 'ward';
    }
    row += '</option>';

    array.forEach(element => {
        row += `<option data-id="${element.code}" value="${element.name}">${element.name}</option>`;
    });
    document.querySelector("#" + select).innerHTML = row;
};

$("#cityy").change(() => {
    const selectedCity = $("#cityy").find(':selected');
    if (selectedCity.data('id')) {
        callApiDistrict(host + "p/" + selectedCity.data('id') + "?depth=2");
    }
});

$("#districtt").change(() => {
    const selectedDistrict = $("#districtt").find(':selected');
    if (selectedDistrict.data('id')) {
        callApiWard(host + "d/" + selectedDistrict.data('id') + "?depth=2");
    }
});

$("#wardd").change(() => {
    // Logic if needed when ward changes
});


