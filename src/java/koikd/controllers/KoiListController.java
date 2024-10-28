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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.farm.FarmDAO;
import koikd.farm.FarmDTO;
import koikd.koi.KoiDAO;
import koikd.koi.KoiDTO;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "KoiListController", urlPatterns = {"/koi-list-order"})
public class KoiListController extends HttpServlet {
    private static final String KOI_LIST_PAGE = "KoiListOrder.jsp";
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
        String url = KOI_LIST_PAGE;
        String farmID = request.getParameter("farmID");
        try {
            KoiDAO dao = new KoiDAO();  
            FarmDAO farmDao = new FarmDAO();
            List<KoiDTO> koiList = dao.getAllKoiList();
            request.setAttribute("KOILIST", koiList);
            
            List<FarmDTO> farmList = farmDao.getFarmList();
            request.setAttribute("farmList", farmList);
            
            if (farmID != null) {
                List<KoiDTO> filterKoiList = dao.filterKois(farmID);
                request.setAttribute("KOILIST", filterKoiList);
            } else {
                request.setAttribute("KOILIST", koiList);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(KoiListController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KoiListController.class.getName()).log(Level.SEVERE, null, ex);
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
