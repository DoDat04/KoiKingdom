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
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.order.KoiOrderDAO;

/**
 *
 * @author Minhngo
 */
@WebServlet(name = "AssignToDeliveryController", urlPatterns = {"/assignToDelivery"})
public class AssignToDeliveryController extends HttpServlet {

    private static String MANAGE_KOI_ORDER = "GetKoiOrder";

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
        String url = MANAGE_KOI_ORDER;

        try {
            String employeeId = request.getParameter("employeeId");
            String koiOrderId = request.getParameter("koiOrderId");
            String userType = request.getParameter("userType");

            if (employeeId != null && koiOrderId != null) {
                KoiOrderDAO dao = new KoiOrderDAO();
                request.setAttribute("userType", userType);
                request.setAttribute("employeeId", employeeId);
                System.out.println("employId đã assigned" + employeeId);
                // Gọi hàm gán employee cho đơn hàng
                boolean result = dao.assignedToDelivery(Integer.parseInt(employeeId), Integer.parseInt(koiOrderId));

                if (!result) {
                    request.setAttribute("errorMessage", "Failed to assign employee to delivery.");
                }
            } else {
                request.setAttribute("errorMessage", "Invalid parameters for employee or order ID.");
            }
        } catch (NumberFormatException | ClassNotFoundException e) {
            Logger.getLogger(AssignToDeliveryController.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("errorMessage", "Error occurred: " + e.getMessage());
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
