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
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.employees.EmployeesDAO;
import koikd.employees.EmployeesDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "GetListDeliveryEmployeeController", urlPatterns = {"/GetListDeliveryEmployee"})
public class GetListDeliveryEmployeeController extends HttpServlet {

    private static String MANAGE_KOI_ORDER = "manageKoiOrder.jsp";

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
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            EmployeesDAO dao = new EmployeesDAO();
            List<EmployeesDTO> em = dao.getAllEmployees();
            List<EmployeesDTO> delivery = new ArrayList<>();

            // Filter employees with "Delivery" role
            for (EmployeesDTO employee : em) {
                if ("Delivery".equalsIgnoreCase(employee.getRole())) {
                    delivery.add(employee);
                }
            }

            // Create JSON string
            StringBuilder jsonResponse = new StringBuilder("[");
            for (int i = 0; i < delivery.size(); i++) {
                EmployeesDTO employee = delivery.get(i);
                jsonResponse.append(String.format(
                        "{\"employeeID\": %d, \"firstName\": \"%s\", \"lastName\": \"%s\", \"role\": \"%s\"}",
                        employee.getEmployeeID(), // Ensure this method exists
                        employee.getFirstName().replace("\"", "\\\""),
                        employee.getLastName().replace("\"", "\\\""),
                        employee.getRole().replace("\"", "\\\"")
                ));
                if (i < delivery.size() - 1) {
                    jsonResponse.append(","); // Add comma if not the last element
                }
            }
            jsonResponse.append("]"); // Close JSON array

            // Send JSON response
            out.print(jsonResponse.toString());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GetListDeliveryEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"message\": \"An error occurred while retrieving employees.\"}");
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
