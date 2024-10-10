/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package koikd.controllers;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
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
import koikd.employees.EmployeesDAO;
import koikd.employees.EmployeesDTO;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Nguyen Huu Khoan
 */
@WebServlet(name = "AddEmployee", urlPatterns = {"/addemployee"})

public class AddEmployee extends HttpServlet {
    private static final String ADD_EMPLOYEE_PAGE = "addEmployee.jsp";
    private static final String RESULT_PAGE = "manageEmployee.jsp";
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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ADD_EMPLOYEE_PAGE;
        String email = request.getParameter("Email");
        String password = request.getParameter("Password");
        String cfPassword = request.getParameter("ConfirmPassword");
        String role = request.getParameter("Role");
        String lastName = request.getParameter("LastName");
        String firstName = request.getParameter("FirstName");
        boolean foundErr = false;
        RegistrationCreateError errors = new RegistrationCreateError();
        String passwordPattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{8,50}$";
        String namePattern = "^[\\p{L}\\s]+$"; // Cho phép tất cả các ký tự chữ cái và dấu cách
        try {
            if (email != null && firstName != null && lastName != null && password != null && cfPassword != null && role != null) {
                // Kiểm tra password
                if (!password.trim().matches(passwordPattern)) {
                    foundErr = true;
                    errors.setPasswordLengthErr("Password must be 8 to 50 characters, include at least one uppercase letter, one digit, and one special character.");
                } else if (!cfPassword.trim().equals(password.trim())) {
                    foundErr = true;
                    errors.setConfirmNotMacthed("Confirm password does not match the password.");
                }

                // Kiểm tra ký tự đặc biệt cho firstName và lastName
                if (!firstName.trim().matches(namePattern)) {
                    foundErr = true;
                    errors.setFirstNameInvalidErr("First name is not allowed to contain special characters.");
                }
                if (!lastName.trim().matches(namePattern)) {
                    foundErr = true;
                    errors.setLastNameInvalidErr("Last name is not allowed to contain special characters.");
                }

                // Kiểm tra độ dài firstName và lastName
                if (firstName.trim().length() < 2 || firstName.trim().length() > 50) {
                    foundErr = true;
                    errors.setFirstNameLengthErr("First name is required to be between 2 and 50 characters.");
                }
                if (lastName.trim().length() < 2 || lastName.trim().length() > 50) {
                    foundErr = true;
                    errors.setLastNameLengthErr("Last name is required to be between 2 and 50 characters.");
                }

                // Kiểm tra xem email đã tồn tại chưa
                EmployeesDAO dao = new EmployeesDAO();
                EmployeesDTO existingUser = dao.getEmployeeByEmail(email);
                if (existingUser != null) {
                    foundErr = true;
                    errors.setEmailIsExisted(email + " is already registered.");
                }

                if (foundErr) { // Nếu có lỗi
                    request.setAttribute("CREATE_ERROR", errors);
                } else {
                    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                    boolean result = dao.addEmployee(email, hashedPassword, role, lastName, firstName, "", true);
                    if (result) {
                        url = ADD_EMPLOYEE_PAGE;
                        request.setAttribute("CREATE_SUCCESS", "Your account was created successfully.");
                    }
                }
            } else {
                
                url = ADD_EMPLOYEE_PAGE;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);           
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
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
