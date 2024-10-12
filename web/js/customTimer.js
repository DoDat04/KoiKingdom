/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.addEventListener("DOMContentLoaded", function() {
    // Lấy danh sách tất cả các yêu cầu theo requestID
    var rows = document.querySelectorAll(".customer-tour-row");
    
    rows.forEach(function(row) {
        var requestID = row.getAttribute("data-requestid");
        var countdownTime = 30 * 1000; // 30 giây 
        var countdownEndTimestamp = localStorage.getItem('countdownEndTimestamp_' + requestID);
        var currentTime = new Date().getTime();
        var expired = localStorage.getItem('expired_' + requestID);

        var timerElement = row.querySelector(".decision-timer");

        // Nếu thời gian đã hết trước khi trang tải lại (hoặc trạng thái đã được lưu là hết hạn)
        if (expired === 'true' || (countdownEndTimestamp && countdownEndTimestamp <= currentTime)) {
            // Vô hiệu hóa các nút "Check Out" và "Reject"
            row.querySelectorAll('.decision-buttons a').forEach(function(button) {
                button.classList.add('disabled');
                button.setAttribute('disabled', 'true');
            });

            // Hiển thị thông báo hết thời gian
            timerElement.textContent = "Time's up!";
        } else {
            // Nếu thời gian chưa hết, tính toán thời gian còn lại
            var timeLeft;
            if (countdownEndTimestamp && countdownEndTimestamp > currentTime) {
                timeLeft = Math.floor((countdownEndTimestamp - currentTime) / 1000);
            } else {
                timeLeft = 30;
                localStorage.setItem('countdownEndTimestamp_' + requestID, currentTime + countdownTime);
            }

            // Hiển thị thời gian ban đầu lên giao diện
            timerElement.textContent = timeLeft;

            // Cập nhật đồng hồ đếm ngược mỗi giây
            var countdown = setInterval(function() {
                timeLeft--;
                timerElement.textContent = timeLeft;

                if (timeLeft <= 0) {
                    clearInterval(countdown);
                    localStorage.setItem('expired_' + requestID, 'true'); // Lưu trạng thái hết thời gian vào localStorage
                    localStorage.removeItem('countdownEndTimestamp_' + requestID); // Xóa thời gian hết hạn sau khi hết

                    // Vô hiệu hóa tất cả các nút "Check Out" và "Reject"
                    row.querySelectorAll('.decision-buttons a').forEach(function(button) {
                        button.classList.add('disabled');
                        button.setAttribute('disabled', 'true');
                    });

                    // Hiển thị thông báo hết thời gian
                    timerElement.textContent = "Time's up!";
                }
            }, 1000);
        }
    });
});








