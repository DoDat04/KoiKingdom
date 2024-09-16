/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.addEventListener("DOMContentLoaded", function() {
    // Lấy các tham số từ URL
    var urlParams = new URLSearchParams(window.location.search);
    var resetTimer = urlParams.has('resetTimer');

    if (resetTimer) {
        // Nếu có tham số 'resetTimer' trong URL, xóa giá trị thời gian hết hạn khỏi localStorage
        localStorage.removeItem('otpExpirationTimestamp');
    }

    // Lấy thời gian hết hạn từ localStorage
    var storedTime = localStorage.getItem('otpExpirationTimestamp');
    var currentTime = new Date().getTime();
    
    var timeLeft;
    if (storedTime) {
        // Nếu có giá trị thời gian hết hạn trong localStorage, tính toán thời gian còn lại
        timeLeft = Math.max(0, Math.floor((parseInt(storedTime, 10) - currentTime) / 1000));
    } else {
        // Nếu không có giá trị thời gian hết hạn, đặt thời gian mặc định là 300 giây
        timeLeft = 300;
        localStorage.setItem('otpExpirationTimestamp', currentTime + (timeLeft * 1000));
    }

    // Hiển thị thời gian ban đầu lên giao diện
    document.getElementById("time").textContent = timeLeft;

    // Cập nhật đồng hồ đếm ngược mỗi giây
    var timer = setInterval(function() {
        timeLeft--;
        document.getElementById("time").textContent = timeLeft;

        if (timeLeft <= 0) {
            clearInterval(timer);
            localStorage.removeItem('otpExpirationTimestamp'); // Xóa giá trị thời gian hết hạn khi thời gian đã hết
            alert("OTP đã hết hạn! Vui lòng yêu cầu mã mới.");
            window.location.href = 'forgot-password'; // Chuyển hướng đến trang forgotPassword.jsp
        } else {
            // Cập nhật thời gian hết hạn trong localStorage
            localStorage.setItem('otpExpirationTimestamp', new Date().getTime() + (timeLeft * 1000));
        }
    }, 1000);
});

