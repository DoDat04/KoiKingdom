/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package koikd.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.employees.EmployeesDAO;
import koikd.employees.EmployeesDTO;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "UpdateProfileConsultingController", urlPatterns = {"/UpdateProfileConsultingController"})
@MultipartConfig
public class UpdateProfileConsultingController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // Lấy các tham số từ request
            String firstName = request.getParameter("fname");
            String lastName = request.getParameter("lname");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String district = request.getParameter("district");
            String ward = request.getParameter("ward");
            Part filePart = request.getPart("profileImage");

            HttpSession session = request.getSession();
            EmployeesDTO employee = (EmployeesDTO) session.getAttribute("LOGIN_CONSULTING");
            String emailPrefix = null, newFileName = null;

            if (email != null) {
                // Lấy phần trước @ trong email để làm tiền tố cho tên tệp
                emailPrefix = getUserIdBeforeAt(email);
                newFileName = emailPrefix + "user_picture.png"; // Tạo tên file hình ảnh

                // Đường dẫn upload
                String uploadPath = getServletContext().getRealPath("images");

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir(); // Tạo thư mục nếu chưa tồn tại
                }

                // Đường dẫn đầy đủ của tệp hình ảnh
                String filePath = uploadPath + File.separator + newFileName;

                // Nếu file ảnh được chọn, tiến hành upload
                if (filePart != null && filePart.getSize() > 0) {
                    try (InputStream input = filePart.getInputStream(); OutputStream output = new FileOutputStream(filePath)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = input.read(buffer)) != -1) {
                            output.write(buffer, 0, bytesRead);
                        }
                    }
                    // Cập nhật đường dẫn ảnh vào session
                    session.setAttribute("AVATAR", "images/" + newFileName);
                }

                // Xử lý cập nhật địa chỉ
                String custAddress = null;
                if (address != null && !address.isEmpty()
                        && city != null && !city.isEmpty()
                        && district != null && !district.isEmpty()
                        && ward != null && !ward.isEmpty()) {
                    // Nếu có địa chỉ mới thì tạo mới địa chỉ
                    custAddress = address + ", " + ward + ", " + district + ", " + city;
                } else {
                    // Giữ nguyên địa chỉ cũ nếu không có thông tin mới
                    custAddress = employee.getAddress();
                }

                employee.setAddress(custAddress); // Cập nhật địa chỉ mới cho đối tượng `employee`

                // Gọi DAO để cập nhật hồ sơ nhân viên giao hàng
                EmployeesDAO delivery = new EmployeesDAO();
                boolean isUpdate = delivery.updateDeliveryProfile(firstName, lastName, email, custAddress);

                if (isUpdate) {
                    // Cập nhật thông tin vào session nếu update thành công
                    employee.setFirstName(firstName);
                    employee.setLastName(lastName);
                    employee.setEmail(email);
                    session.setAttribute("LOGIN_CONSULTING", employee);
                    session.setAttribute("updateSuccessConsulting", "Update successfully!");
                } else {
                    // Thông báo lỗi nếu quá trình cập nhật không thành công
                    request.setAttribute("updateError", "Failed to update profile.");
                }

                // Chuyển tiếp đến trang hiển thị thông tin
                response.sendRedirect("home?action=Consulting");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfileDeliveryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateProfileDeliveryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getUserIdBeforeAt(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex > 0) {
            return email.substring(0, atIndex);
        }
        return email; // Nếu không có dấu '@', trả về toàn bộ chuỗi

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
