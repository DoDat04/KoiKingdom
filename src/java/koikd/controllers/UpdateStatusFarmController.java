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
import koikd.farm.FarmDAO;
import java.sql.SQLException;

/**
 *
 * @author Nguyen Huu Khoan
 */
@WebServlet(name = "UpdateStatusFarmController", urlPatterns = {"/updateStatusFarm"})
public class UpdateStatusFarmController extends HttpServlet {
private static final String MANAGE_FARM_PAGE = "managefarm";
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
        String url = MANAGE_FARM_PAGE;
        
        String idFarmString = request.getParameter("farmID");
        int idFarm = 0;
        boolean updateStatusResult = false;

        try {
            if (idFarmString != null && !idFarmString.trim().isEmpty()) {
                try {
                    idFarm = Integer.parseInt(idFarmString);
                } catch (NumberFormatException e) {
                    request.setAttribute("ERROR_UPDATE", "Invalid farm ID format: " + idFarmString);
                    request.getRequestDispatcher(url).forward(request, response);
                    return;
                }

                FarmDAO dao = new FarmDAO();
                try {
                    updateStatusResult = dao.updateFarmStatus(idFarm);
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
                request.setAttribute("ERROR_UPDATE", "farm ID is missing or empty.");
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
