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
    private static final String SEND_OTP_CONTROLLER = "send-OTP";  // URL mapping for SendOTPController

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
        String passwordPattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{8,50}$";
        String namePattern = "^[\\p{L}\\s]+$"; // Allows all letter characters and spaces
        String accountType = "default";

        try {
            if (firstName != null && lastName != null && password != null && confirmPassword != null) {
                // Validate password
                if (!password.trim().matches(passwordPattern)) {
                    foundErr = true;
                    errors.setPasswordLengthErr("Password must be 8 to 50 characters, include at least one uppercase letter, one digit, and one special character.");
                } else if (!confirmPassword.trim().equals(password.trim())) {
                    foundErr = true;
                    errors.setConfirmNotMacthed("Confirm password does not match the password.");
                }

                // Validate firstName and lastName for special characters
                if (!firstName.trim().matches(namePattern)) {
                    foundErr = true;
                    errors.setFirstNameInvalidErr("First name cannot contain special characters.");
                }
                if (!lastName.trim().matches(namePattern)) {
                    foundErr = true;
                    errors.setLastNameInvalidErr("Last name cannot contain special characters.");
                }

                // Check length of firstName and lastName
                if (firstName.trim().length() < 2 || firstName.trim().length() > 50) {
                    foundErr = true;
                    errors.setFirstNameLengthErr("First name must be between 2 and 50 characters.");
                }
                if (lastName.trim().length() < 2 || lastName.trim().length() > 50) {
                    foundErr = true;
                    errors.setLastNameLengthErr("Last name must be between 2 and 50 characters.");
                }

                // Check if email already exists
                CustomerDAO dao = new CustomerDAO();
                CustomerDTO existingUser = dao.findCustomerByEmailAndAccountType(email, accountType);
                if (existingUser != null) {
                    foundErr = true;
                    errors.setEmailIsExisted(email + " is already registered.");
                }

                if (foundErr) { // If there are errors
                    request.setAttribute("CREATE_ERROR", errors);
                } else {
                    // Create account and generate hashed password
                    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                    CustomerDTO dto = new CustomerDTO(email, hashedPassword, lastName, firstName, "", accountType, true);
                    boolean result = dao.createCustomerAccount(dto);
                    if (result) {
                        // Redirect to SendOTPController to generate and send OTP
                        url = SEND_OTP_CONTROLLER + "?email=" + email + "&purpose=register";
                    } else {
                        request.setAttribute("CREATE_ERROR", "Account creation failed. Please try again.");
                    }
                }
            } else {
                url = SIGN_UP_PAGE;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (foundErr) {
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                request.getRequestDispatcher(url).forward(request, response);
            }
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
