/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.addEventListener("DOMContentLoaded", function() {
    var rows = document.querySelectorAll(".customer-tour-row");

    rows.forEach(function(row) {
        var requestID = row.getAttribute("data-requestid");
        var countdownTime = 30 * 1000; // 30 giây
        var currentTime = new Date().getTime();
        var countdownEndTimestamp = localStorage.getItem('countdownEndTimestamp_' + requestID);
        var expired = localStorage.getItem('expired_' + requestID);

        var timerElement = row.querySelector(".decision-timer");

        // Lấy giá trị của customerTourList.status và customerTourList.managerApprovalStatus từ các attribute HTML
        var status = row.getAttribute("data-status");
        var managerApprovalStatus = row.getAttribute("data-managerApprovalStatus");

        // Chỉ tạo countdownEndTimestamp và expired khi cả hai trạng thái là 'Approved'
        if (status === 'Approved' && managerApprovalStatus === 'Approved') {
            // Nếu không tìm thấy countdownEndTimestamp, tạo một cái mới và reset expired
            if (!countdownEndTimestamp) {
                countdownEndTimestamp = currentTime + countdownTime;
                localStorage.setItem('countdownEndTimestamp_' + requestID, countdownEndTimestamp);
                expired = 'false'; // Đặt trạng thái chưa hết thời gian
                localStorage.setItem('expired_' + requestID, expired);
            }

            // Kiểm tra trạng thái expired và thời gian còn lại
            if (expired === 'true') {
                // Nếu trạng thái đã hết
                var checkoutButton = row.querySelector('.btn-success');
                checkoutButton.classList.add('disabled');
                checkoutButton.setAttribute('disabled', 'true');

                timerElement.textContent = "Time's up!";

                var rejectButton = row.querySelector('.btn-danger');
                rejectButton.textContent = "Delete"; 
            } else {
                // Nếu expired là false, kiểm tra thời gian còn lại
                if (countdownEndTimestamp > currentTime) {
                    var timeLeft = Math.floor((countdownEndTimestamp - currentTime) / 1000);
                    timerElement.textContent = timeLeft;

                    var countdown = setInterval(function() {
                        timeLeft--;
                        timerElement.textContent = timeLeft;

                        if (timeLeft <= 0) {
                            clearInterval(countdown);
                            localStorage.setItem('expired_' + requestID, 'true'); 

                            var checkoutButton = row.querySelector('.btn-success');
                            checkoutButton.classList.add('disabled');
                            checkoutButton.setAttribute('disabled', 'true');

                            timerElement.textContent = "Time's up!";

                            var rejectButton = row.querySelector('.btn-danger');
                            rejectButton.textContent = "Delete"; // Thay đổi văn bản
                        }
                    }, 1000);
                } else {
                    localStorage.setItem('expired_' + requestID, 'true');

                    var checkoutButton = row.querySelector('.btn-success');
                    checkoutButton.classList.add('disabled');
                    checkoutButton.setAttribute('disabled', 'true');

                    timerElement.textContent = "Time's up!";

                    var rejectButton = row.querySelector('.btn-danger');
                    rejectButton.textContent = "Delete"; 
                }
            }
        }
    });
});

