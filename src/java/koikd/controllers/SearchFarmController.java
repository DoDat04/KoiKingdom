/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package koikd.controllers;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.List;
import koikd.farm.FarmDAO;
import koikd.farm.FarmDTO;

/**
 *
 * @author Nguyen Huu Khoan
 */
@WebServlet(name = "SearchFarmController", urlPatterns = {"/searchfarm"})
public class SearchFarmController extends HttpServlet {

    private static final String SEARCH_PAGE = "manageFarm.jsp";
    private static final String SEARCH_RESULT = "searchFarm.jsp";
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String searchValue = request.getParameter("txtSearchValue");
        String index = request.getParameter("index");
        String url = SEARCH_PAGE;
         try {
            // Default to 1 if no index is provided
            if (index == null || index.isEmpty()) {
                index = "1";
            }

            // Parse index as an integer
            int pageIndex = Integer.parseInt(index);
            if (searchValue == null || searchValue.trim().isEmpty()) {
                // Trường hợp không nhập từ khóa
                request.setAttribute("SEARCH_MESSAGE", "No keyword entered !");
            } else {
                FarmDAO dao = new FarmDAO();
                int numberOfPages = dao.getNumberPageInSearchPage(searchValue);
                request.setAttribute("numberOfPages", numberOfPages);
                List<FarmDTO> result = dao.searchFarmName(searchValue,pageIndex);
                if (result != null && !result.isEmpty()) {
                    url = SEARCH_RESULT;
                    request.setAttribute("SEARCH_FARM", result);
                    request.setAttribute("pageIndex", pageIndex);
               
                } else {
                    request.setAttribute("SEARCH_MESSAGE", "No tour found !");
                }

            }
           
        } catch (SQLException ex) {
            Logger.getLogger(SearchTourController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchTourController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
            Logger.getLogger(SearchFarmController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SearchFarmController.class.getName()).log(Level.SEVERE, null, ex);
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
