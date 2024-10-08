/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package koikd.controllers;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import koikd.employees.EmployeesDAO;
import koikd.employees.EmployeesDTO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Huu Khoan
 */
@WebServlet(name = "SearchEmployee", urlPatterns = {"/searchemployee"})
public class SearchEmployee extends HttpServlet {
    
    private static final String SEARCH_PAGE = "manageEmployee.jsp";
    private static final String SEARCH_RESULT = "searchEmployee.jsp";
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
        String searchValue = request.getParameter("txtSearchValue");
        String url = SEARCH_PAGE;
        try {
            if (searchValue == null || searchValue.trim().isEmpty()) {
                // Trường hợp không nhập từ khóa
                request.setAttribute("SEARCH_MESSAGE", "No keyword entered !");
            } else {
                EmployeesDAO dao = new EmployeesDAO();
                List<EmployeesDTO> result = dao.searchEmployees(searchValue);
                if (result != null && !result.isEmpty()) {
                    url = SEARCH_RESULT;
                    request.setAttribute("SEARCH_EMPLOYEE", result);
                } else {
                    request.setAttribute("SEARCH_MESSAGE", "No employee found !");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchByTourName.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchByTourName.class.getName()).log(Level.SEVERE, null, ex);
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
