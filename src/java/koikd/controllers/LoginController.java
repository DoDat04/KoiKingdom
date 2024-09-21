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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.customer.CustomerDAO;
import koikd.customer.CustomerDTO;
import koikd.employees.DeliveryDAO;
import koikd.employees.EmployeesDTO;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String HOME_PAGE = "home";
    private static final String DELIVERY_PAGE = "deliveryFolder/homeForDelivery.jsp";

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
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            } else {
                CustomerDAO dao = new CustomerDAO();
                CustomerDTO result = dao.checkLogin(email, password);
                DeliveryDAO deliveryDao = new DeliveryDAO();
                EmployeesDTO employee = new EmployeesDTO();
                EmployeesDTO deliveryResult = deliveryDao.checkLogin(email, password);

                if (result != null) {
                    HttpSession session = request.getSession();
                    session.removeAttribute("LOGIN_GMAIL");
                    session.setAttribute("LOGIN_USER", result);
                    // sau khi login lấy address từ db bỏ vào attribute
                    session.setAttribute("address", result.getAddress());
                    String emailPrefix = getUserIdBeforeAt(email);

                    // Lấy đường dẫn đến thư mục images
                    String uploadPath = getServletContext().getRealPath("/images");
                    // Tạo file ảnh người dùng cùng tên lúc update
                    String userImageFileName = emailPrefix + "user_picture.png";
                    File userImageFile = new File(uploadPath + File.separator + userImageFileName);

                    // Kiểm tra xem ảnh có không
                    if (userImageFile.exists()) {
                        // Có thì set vào attribute
                        session.setAttribute("AVATAR", "images/" + userImageFileName);
                    }

                    url = HOME_PAGE;
                } else if (deliveryResult != null) {
                    HttpSession session = request.getSession();
                    session.removeAttribute("LOGIN_GMAIL");
                    session.setAttribute("LOGIN_USER", result);
                    String emailPrefix = getUserIdBeforeAt(email);
                    // Lấy đường dẫn đến thư mục images
                    String uploadPath = getServletContext().getRealPath("/images");
                    // Tạo file ảnh người dùng cùng tên lúc update
                    String userImageFileName = emailPrefix + "user_picture.png";
                    File userImageFile = new File(uploadPath + File.separator + userImageFileName);

                    // Kiểm tra xem ảnh có không
                    if (userImageFile.exists()) {
                        // Có thì set vào attribute
                        session.setAttribute("AVATAR", "images/" + userImageFileName);
                    }
                    url = DELIVERY_PAGE;

                } else {
                    request.setAttribute("ERROR", "Your email or password is wrong!");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (HOME_PAGE.equals(url)) {
                response.sendRedirect("home");
            } else {
                request.getRequestDispatcher(url).forward(request, response);
            }
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
