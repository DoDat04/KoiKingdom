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
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.customer.CustomerDAO;
import koikd.customer.CustomerDTO;
import koikd.employees.EmployeesDAO;
import koikd.employees.EmployeesDTO;

/**
 *
 * @author Do Dat, Minhngo
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String HOME_PAGE = "home";
    private static final String DELIVERY_PAGE = "homeForDelivery.jsp";
    private static final String MANAGER_PAGE = "managerDashboard.jsp";
    private static final String SALES_PAGE = "homeForSales.jsp";
    private static final String CONSULTING_PAGE = "homeForConsulting.jsp";

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
                CustomerDAO customerDao = new CustomerDAO();
                CustomerDTO customerResult = customerDao.checkLogin(email.toLowerCase(), password);
                EmployeesDAO deliveryDao = new EmployeesDAO();
                EmployeesDTO employeeResult = deliveryDao.checkLoginDelivery(email, password);

                HttpSession session = request.getSession();

                if (customerResult != null) {
                    session.setAttribute("LOGIN_USER", customerResult);
                    session.setAttribute("custID", customerResult.getCustomerID());
                    session.setAttribute("email", customerResult.getEmail());
                    String sanitizedUserId = getUserIdBeforeAt(customerResult.getEmail());
                    session.setAttribute("userId", sanitizedUserId);
                    session.setAttribute("SUCCESS", "Login Successfully!");
                    setUserImage(session, email);
                    url = HOME_PAGE;
                } else if (employeeResult != null) {
                    
                    setUserImage(session, email);

                    String role = employeeResult.getRole(); 

                    if ("Delivery".equals(role)) {
                        session.setAttribute("LOGIN_DELIVERY", employeeResult);
                        url = DELIVERY_PAGE;
                    } else if ("Manager".equals(role)) {
                        session.setAttribute("LOGIN_MANAGER", employeeResult);
                        url = MANAGER_PAGE;
                    } else if("Sales".equals(role)){
                        session.setAttribute("LOGIN_SALES", employeeResult);
                        url = SALES_PAGE;
                    } else if ("Consulting".equals(role)) {
                        session.setAttribute("LOGIN_CONSULTING", employeeResult);
                        url = CONSULTING_PAGE;
                    }                   
                    else {
                        request.setAttribute("ERROR", "Invalid role for this employee!");
                    }
                } else {
                    request.setAttribute("ERROR", "Your email or password is wrong!");
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("ERROR", "An error occurred during login. Please try again.");
        } finally {
            if (HOME_PAGE.equals(url)) {
                response.sendRedirect("home");
            } else if (DELIVERY_PAGE.equals(url)) {
                response.sendRedirect("home?action=Delivery");
            } else if(MANAGER_PAGE.equals(url)) {
                response.sendRedirect("home?action=Manager");
            } else if(SALES_PAGE.equals(url)){
                response.sendRedirect("home?action=Sales");
            } else if(CONSULTING_PAGE.equals(url)){
                response.sendRedirect("home?action=Consulting");
            }
            else {
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
    }

    private void setUserImage(HttpSession session, String email) {
        String emailPrefix = getUserIdBeforeAt(email);
        String uploadPath = getServletContext().getRealPath("/images");
        String userImageFileName = emailPrefix + "user_picture.png";
        File userImageFile = new File(uploadPath + File.separator + userImageFileName);

        if (userImageFile.exists()) {
            session.setAttribute("AVATAR", "images/" + userImageFileName);
        }
        String appPath = getServletContext().getRealPath("/");
        System.out.println("Application Path: " + appPath);
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
