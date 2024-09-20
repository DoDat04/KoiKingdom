/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package koikd.controllers;

import koikd.customer.*;
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
            Part filePart = request.getPart("profileImage");
            HttpSession session = request.getSession();
            CustomerDTO user = (CustomerDTO) session.getAttribute("LOGIN_USER");
            GooglePojo userGmail = (GooglePojo) session.getAttribute("LOGIN_GMAIL");

            String email = null, emailPrefix = null, newFileName = null;
            boolean isUser = false;

            // Xác định kiểu người dùng (user thường hay Gmail)
            if (user != null) {
                email = user.getEmail();
                isUser = true;
            } else if (userGmail != null) {
                email = userGmail.getEmail();
            }

            // Nếu email tồn tại
            if (email != null) {
                emailPrefix = getUserIdBeforeAt(email);
                newFileName = emailPrefix + (isUser ? "user_picture.png" : "gmail_picture.png");
                String uploadPath = getServletContext().getRealPath("images");

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir(); // Tạo thư mục nếu chưa tồn tại
                }

                String filePath = uploadPath + File.separator + newFileName;

                // Nếu có file upload
                if (filePart != null && filePart.getSize() > 0) {
                    try (InputStream input = filePart.getInputStream(); OutputStream output = new FileOutputStream(filePath)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = input.read(buffer)) != -1) {
                            output.write(buffer, 0, bytesRead);
                        }
                    }
                    session.setAttribute("AVATAR", "images/" + newFileName); 
                } else {
                    // Nếu không có ảnh upload, giữ nguyên ảnh hiện tại
                    String currentAvatar = (String) session.getAttribute("AVATAR");
                    if (currentAvatar == null) {
                        // Nếu chưa có ảnh trong session, dùng ảnh Google avatar nếu là tài khoản Google
                        currentAvatar = userGmail.getPicture(); 
                    }
                    session.setAttribute("AVATAR", currentAvatar);  
                }

                // Cập nhật thông tin người dùng
                if (isUser) {
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                } else {
                    userGmail.setGiven_name(lastName);
                    userGmail.setFamily_name(firstName);
                    session.setAttribute("firstName", firstName);  
                    session.setAttribute("lastName", lastName);     
                }

                CustomerDAO dao = new CustomerDAO();
                boolean updateResult = dao.updateUserProfile(email, firstName, lastName, isUser ? "default" : "google");

                if (updateResult) {
                    session.setAttribute(isUser ? "LOGIN_USER" : "LOGIN_GMAIL", isUser ? user : userGmail);
                    session.setAttribute("updateSuccess", "Update successfully!");
                } else {
                    request.setAttribute("updateError", "Failed to update profile.");
                }
            }
            response.sendRedirect(url);
        } catch (Exception e) {
            log("Error at ProfileUpdateServlet: " + e.toString());
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
