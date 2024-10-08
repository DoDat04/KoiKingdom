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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.customtour.CustomTourDAO;
import koikd.customtour.CustomTourDTO;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "ManagerCustomActionController", urlPatterns = {"/ManagerCustomActionController"})
public class ManagerCustomActionController extends HttpServlet {

    private static final String CUSTOMTOUR_LIST = "listCustomTour.jsp";

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
        String url = CUSTOMTOUR_LIST;
        String requestIDParam = request.getParameter("customId");
        String action = request.getParameter("action");

        try {
            if (requestIDParam != null && !requestIDParam.isEmpty()) {
                int requestID = Integer.parseInt(requestIDParam);
                CustomTourDAO dao = new CustomTourDAO();

                String message = "";
                if ("approve".equals(action)) {
                    boolean updated = dao.updateManagerApprovalStatus(requestID, "Approved");
                    message = updated ? "Request approved successfully." : "Failed to approve request.";
                } else if ("reject".equals(action)) {
                    double newPrice = Double.parseDouble(request.getParameter("newPrice"));
                    String rejectReason = request.getParameter("rejectReason");

                    // Use the updateQuotationAndRejectionDetails method
                    boolean updated = dao.updateQuotationAndRejectionDetails(requestID, newPrice, rejectReason);
                    message = updated ? "Request rejected successfully with updated details." : "Failed to reject request.";
                }
                
                HttpSession session = request.getSession();
                session.setAttribute("MESSAGE", message);
                
                List<CustomTourDTO> listCustomTour = dao.getListCustomTourForManager();
                session.setAttribute("CUSTOM_LIST", listCustomTour);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("MESSAGE", "Invalid input values.");
            Logger.getLogger(ManagerCustomActionController.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ManagerCustomActionController.class.getName()).log(Level.SEVERE, null, e);
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
