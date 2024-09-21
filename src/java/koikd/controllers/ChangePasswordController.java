/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package koikd.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.customer.CustomerDAO;
import koikd.customer.CustomerDTO;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "ChangePasswordController", urlPatterns = {"/ChangePasswordController"})
public class ChangePasswordController extends HttpServlet {

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
        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        String confirmPass = request.getParameter("confirmPassword");
        String passwordPattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{8,50}$";

        try {
            HttpSession session = request.getSession();
            CustomerDTO user = (CustomerDTO) session.getAttribute("LOGIN_USER");

            CustomerDAO dao = new CustomerDAO();
            CustomerDTO result = dao.getUserByEmail(user.getEmail());

            if (result != null) {
                if (BCrypt.checkpw(oldPass, result.getPassword())) {
                    if (newPass.matches(passwordPattern)) {
                        if (newPass.equals(confirmPass)) {
                            String hashedNewPass = BCrypt.hashpw(newPass, BCrypt.gensalt());
                            boolean isUpdated = dao.updatePassword(user.getEmail(), hashedNewPass); 

                            if (isUpdated) {
                                session.setAttribute("CHANGE_PASS_SUCCESS", "Password changed successfully.");
                                url = "home"; 
                            } else {
                                session.setAttribute("CHANGE_PASS_ERROR", "Failed to update password. Please try again.");
                            }
                        } else {
                            session.setAttribute("CHANGE_PASS_ERROR", "Confirm password does not match the password.");
                        }
                    } else {
                        session.setAttribute("CHANGE_PASS_ERROR", "Password must be 8 to 50 characters, include at least one uppercase letter, one digit, and one special character");
                    }
                } else {
                    session.setAttribute("CHANGE_PASS_ERROR", "Incorrect old password.");
                }
            } 
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.sendRedirect(url); 
        }
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
