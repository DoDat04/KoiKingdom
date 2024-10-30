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
import koikd.customer.CustomerDAO;
import java.sql.SQLException;

/**
 *
 * @author Nguyen Huu Khoan
 */
@WebServlet(name = "UpdateStatusCustomerController", urlPatterns = {"/updateStatusCustomer"})
public class UpdateStatusCustomerController extends HttpServlet {
    private static final String MANAGE_CUSTOMER_PAGE = "managecustomer";
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
        String url = MANAGE_CUSTOMER_PAGE;
        String idCustomerString = request.getParameter("customerID");
        int idCustomer = 0;
        boolean updateStatusResult = false;

        try {
            if (idCustomerString != null && !idCustomerString.trim().isEmpty()) {
                try {
                    idCustomer = Integer.parseInt(idCustomerString);
                } catch (NumberFormatException e) {
                    request.setAttribute("ERROR_UPDATE", "Invalid customer ID format: " + idCustomerString);
                    request.getRequestDispatcher(url).forward(request, response);
                    return;
                }

                CustomerDAO dao = new CustomerDAO();
                try {
                    updateStatusResult = dao.updateStatusCustomer(idCustomer);
                } catch (SQLException e) {
                    request.setAttribute("ERROR_UPDATE", "Database error: " + e.getMessage());
                    request.getRequestDispatcher(url).forward(request, response);
                    return;
                }

                if (updateStatusResult) {
                    request.setAttribute("UPDATE_STATUS", "Status updated successfully.");
                } else {
                    request.setAttribute("ERROR_UPDATE", "Failed to update status for some reason!");
                }
            } else {
                request.setAttribute("ERROR_UPDATE", "Customer ID is missing or empty.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR_UPDATE", "Unexpected error: " + e.getMessage());
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
