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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.customer.CustomerDAO;
import koikd.customer.CustomerDTO;
import koikd.customer.RegistrationCreateError;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "SignUpController", urlPatterns = {"/signup"})
public class SignUpController extends HttpServlet {

    private static final String SIGN_UP_PAGE = "signUp.jsp";

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
        String url = SIGN_UP_PAGE;
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        boolean foundErr = false;
        RegistrationCreateError errors = new RegistrationCreateError();
        String passwordPattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{6,50}$";
        try {
            if (!password.trim().matches(passwordPattern)) {
                foundErr = true;
                errors.setPasswordLengthErr("Password must be 6 to 50 characters, include at least one uppercase letter, one digit, and one special character.");
            } else if (!confirmPassword.trim().equals(password)) {
                foundErr = true;
                errors.setConfirmNotMacthed("Confirm password does not match the password");
            }
            if (firstName.trim().length() < 2 || firstName.trim().length() > 50) {
                foundErr = true;
                errors.setPasswordLengthErr("Firstname is required from 2 to 50 characters");
            }
            if (lastName.trim().length() < 2 || lastName.trim().length() > 50) {
                foundErr = true;
                errors.setPasswordLengthErr("Lastname is required from 2 to 50 characters");
            }
            if (foundErr) { //errors occur
                request.setAttribute("CREATE_ERROR", errors);
            } else {
                String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                CustomerDAO dao = new CustomerDAO();
                CustomerDTO dto = new CustomerDTO(email, hashedPassword, lastName, firstName, "", "default", true);
                boolean result = dao.createCustomerAccount(dto);
                if (result) {
                    url = SIGN_UP_PAGE;
                    request.setAttribute("CREATE_SUCCESS", "Your account was created successfully");
                }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();            
            if (msg.contains("duplicate")) {
                errors.setEmailIsExisted(email + " is existed");
                request.setAttribute("CREATE_ERROR", errors);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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