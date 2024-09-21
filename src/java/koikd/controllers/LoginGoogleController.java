/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package koikd.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import koikd.customer.CustomerDAO;
import koikd.customer.CustomerDTO;
import koikd.google.GooglePojo;
import koikd.utils.GoogleUtils;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/LoginGoogleController"})
public class LoginGoogleController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String HOME_PAGE = "home";

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
        String url = LOGIN_PAGE;
        try {
            String code = request.getParameter("code");
            if (code == null || code.isEmpty()) {
                url = LOGIN_PAGE;
            } else {
                // Get access token from Google
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);

                if (googlePojo != null) {
                    url = HOME_PAGE;
                    CustomerDAO dao = new CustomerDAO();
                    HttpSession session = request.getSession();
                    session.removeAttribute("LOGIN_USER"); // Clear regular login user info
                    
                    //Kiểm tra trong database có tài khoản google đó chưa
                    CustomerDTO dto = dao.findCustomerByEmailAndAccountType(googlePojo.getEmail(), "google");
                    if (dto != null) {
                        String firstName = dto.getFirstName();
                        String lastName = dto.getLastName();
                        session.setAttribute("firstName", firstName);
                        session.setAttribute("lastName", lastName);
                        // sau khi login lấy address từ db bỏ vào attribute
                        session.setAttribute("address", dto.getAddress());
                    } else {
                        session.setAttribute("firstName", googlePojo.getFamily_name());
                        session.setAttribute("lastName", googlePojo.getGiven_name());
                    }
                    session.setAttribute("LOGIN_GMAIL", googlePojo);

                    String emailPrefix = getUserIdBeforeAt(googlePojo.getEmail());
                    String avatarUrl = googlePojo.getPicture();
                    dao.createEmailUser(googlePojo, accessToken);

                    String uploadPath = getServletContext().getRealPath("/images");
                    String userImageFileName = emailPrefix + "gmail_picture.png";
                    File userImageFile = new File(uploadPath + File.separator + userImageFileName);

                    if (userImageFile.exists()) {
                        session.setAttribute("AVATAR", "images/" + userImageFileName);
                    } else {
                        session.setAttribute("AVATAR", avatarUrl);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.sendRedirect(url);
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
