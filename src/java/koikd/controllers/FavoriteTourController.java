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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.favoritetour.FavoriteTourDAO;
import koikd.favoritetour.FavoriteTourDTO;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "FavoriteTourController", urlPatterns = {"/favorite"})
public class FavoriteTourController extends HttpServlet {
    private static final String FAVORITE_PAGE = "favoriteTour.jsp";
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
        String url = FAVORITE_PAGE;
        HttpSession session = request.getSession();
        Integer custIDParam = (Integer) session.getAttribute("custID");
        String tourIDParam = request.getParameter("tourID");
        try {
            FavoriteTourDAO dao = new FavoriteTourDAO();
            if (custIDParam != null && tourIDParam != null 
                     && !tourIDParam.isEmpty()) {
                int tourID = Integer.parseInt(tourIDParam);
                
                // Gọi DAO để thêm vào bảng FAVORITETOUR               
                dao.insertFavoriteTour(custIDParam, tourID);                                
            }
            List<FavoriteTourDTO> favoriteList = dao.getFavoriteTour(custIDParam);
            request.setAttribute("favoriteList", favoriteList);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FavoriteTourController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FavoriteTourController.class.getName()).log(Level.SEVERE, null, ex);
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