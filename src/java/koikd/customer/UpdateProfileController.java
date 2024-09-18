/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package koikd.customer;

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
import java.nio.file.Paths;
import koikd.google.GooglePojo;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "UpdateProfileController", urlPatterns = {"/UpdateProfileController"})
//Vì sử dụng enctype="multipart/form-data" ở form update
//nên muốn lấy các parameter phải thêm @MultipartConfig
@MultipartConfig
public class UpdateProfileController extends HttpServlet {

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
        String url = "home";
        try {
            // Lấy thông tin từ form
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            Part filePart = request.getPart("profileImage"); // Lấy phần file upload từ form

            // Kiểm tra nếu file upload không trống
            if (filePart != null && filePart.getSize() > 0) {
                // Đường dẫn lưu file upload
                HttpSession session = request.getSession();
                CustomerDTO user = (CustomerDTO) session.getAttribute("LOGIN_USER");
                GooglePojo userGmail = (GooglePojo) session.getAttribute("LOGIN_GMAIL");

                if (user != null) {
                    // Người dùng đăng nhập bằng tài khoản thường
                    String email = user.getEmail();
                    String emailPrefix = getUserIdBeforeAt(email);
                    String newFileName = emailPrefix + "user_picture.png";
                    String uploadPath = getServletContext().getRealPath("images");

                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir(); // Tạo thư mục nếu chưa tồn tại
                    }

                    String userFilePath = uploadPath + File.separator + newFileName;
                    try (InputStream input = filePart.getInputStream(); OutputStream output = new FileOutputStream(userFilePath)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = input.read(buffer)) != -1) {
                            output.write(buffer, 0, bytesRead);
                        }
                    }

                    // Cập nhật thông tin người dùng trong session
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    CustomerDAO dao = new CustomerDAO();
                    boolean updateResult = dao.updateUserProfile(user.getEmail(), firstName, lastName);

                    if (updateResult) {
                        session.setAttribute("LOGIN_USER", user);
                        session.setAttribute("AVATAR", "images/" + newFileName);
                    } else {
                        request.setAttribute("updateError", "Failed to update profile.");
                    }

                } else if (userGmail != null) {
                    // Người dùng đăng nhập bằng Gmail
                    String email = userGmail.getEmail();
                    String emailPrefix = getUserIdBeforeAt(email);
                    String gmailFileName = emailPrefix + "gmail_picture.png";
                    String uploadPath = getServletContext().getRealPath("images");

                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir(); // Tạo thư mục nếu chưa tồn tại
                    }

                    String gmailFilePath = uploadPath + File.separator + gmailFileName;
                    try (InputStream input = filePart.getInputStream(); OutputStream output = new FileOutputStream(gmailFilePath)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = input.read(buffer)) != -1) {
                            output.write(buffer, 0, bytesRead);
                        }
                    }

                    // Cập nhật thông tin gmail 
                    userGmail.setGiven_name(lastName);
                    userGmail.setFamily_name(firstName);
                    session.setAttribute("LOGIN_GMAIL", userGmail);
                    session.setAttribute("AVATAR", "images/" + gmailFileName);
                }
            }
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
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
