# 🐟 KoiTrip - Phần mềm quản lý dịch vụ mua cá Koi tại Nhật

## 🧾 Giới thiệu

**KoiTrip** là một ứng dụng web được xây dựng bằng **JSP/Servlet** nhằm hỗ trợ công ty tổ chức dịch vụ **tham quan và mua cá Koi tại các trang trại nổi tiếng ở Nhật Bản**. Phần mềm giúp theo dõi toàn bộ quy trình từ đặt dịch vụ đến khi cá được giao đến tay khách hàng.

---

## 🌟 Chức năng chính

### 🏠 1. Trang chủ
- Giới thiệu các **trang trại cá Koi tại Nhật Bản**
- Giới thiệu **giống cá Koi nổi tiếng** được nhân giống tại các trang trại

### 🔍 2. Tra cứu chuyến đi
Người dùng có thể tra cứu chuyến đi dựa trên:
- Trang trại
- Giống cá
- Mức giá
- Thời gian khởi hành

### 📋 3. Đặt dịch vụ mua cá Koi
- Đặt theo chuyến đi sẵn có
- Hoặc đặt theo **yêu cầu cá nhân** riêng của khách hàng
- Đặt mua cá Koi ngay trên web

### 🔄 4. Quy trình thực hiện dịch vụ

Khách đặt dịch vụ → Nhân viên kinh doanh chốt lộ trình → Gửi báo giá cho quản lý duyệt → Quản lý phê duyệt báo giá → Gửi báo giá cho khách → Khách xác nhận và thanh toán → Nhân viên tư vấn gửi thông tin chuyến đi → Check-in sân bay → Hỗ trợ khách mua cá và đặt cọc → Cập nhật ngày giao cá → Giao cá và nhận phần thanh toán còn lại


### 💳 5. Quản lý thanh toán, hủy/trả dịch vụ

- Tích hợp chính sách thanh toán
- Cho phép hủy đơn và xử lý hoàn tiền 

### ⭐ 6. Đánh giá và phản hồi

- Cho phép khách hàng để lại **rating** và **feedback** sau khi kết thúc dịch vụ

### 👤 7. Quản lý khách hàng

- Hồ sơ cá nhân
- Lịch sử đặt dịch vụ

### 📈 8. Dashboard & Báo cáo

- Thống kê doanh thu, trạng thái đơn hàng, chuyến đi
- Hiệu suất nhân viên, lượt đánh giá

---

## 🛠️ Công nghệ sử dụng

- **Ngôn ngữ**: Java
- **Frontend**: JSP, HTML/CSS, JavaScript
- **Backend**: Servlet (Java EE)
- **CSDL**: SQL Server
- **Thư viện**: JDBC, JSTL
- **Xác thực**: Đăng nhập với Google (OAuth2) + tài khoản riêng
- **IDE**: NetBeans
- **Web Server**: Apache Tomcat
- **Thanh toán**: Tích hợp cổng thanh toán điện tử **VNPAY** (redirect mode)

---

## 💳 Cấu hình thanh toán VNPAY

1. Đăng ký tài khoản tại [VNPAY](https://vnpay.vn)
2. Lấy các thông tin sau:
   - vnp_TmnCode
   - vnp_HashSecret
   - vnp_Url (thường là: `https://sandbox.vnpayment.vn/paymentv2/vpcpay.html`)
3. Cập nhật thông tin cấu hình vào file `VnpayConfig.java` hoặc file `.properties`

```java
public class VnpayConfig {
    public static String vnp_TmnCode = "XXXXX";
    public static String vnp_HashSecret = "XXXXXXXX";
    public static String vnp_Url = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static String vnp_ReturnUrl = "http://localhost:8080/KoiTrip/vnpay_return";
}
```
---
## ▶️ Hướng dẫn chạy

### Bước 1: Import vào NetBeans hoặc Eclipse

- File `.war` hoặc dự án Maven thường được hỗ trợ tốt
- Dùng Tomcat Server (phiên bản 9+)

### Bước 2: Cấu hình Database (MySQL)

- Tạo CSDL tên `koitrip`
- Import file `koitrip.sql` (nếu có)

### Bước 3: Cập nhật file `DBUtils.java` hoặc `context.xml` (nếu dùng DataSource)

```java
String url = "jdbc:mysql://localhost:3306/koitrip";
String username = "root";
String password = "yourpassword";

