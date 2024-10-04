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
import koikd.farm.FarmDAO;
import koikd.farm.FarmDTO;
import koikd.koitype.KoiTypeDAO;
import koikd.koitype.KoiTypeDTO;

/**
 *
 * @author Do Dat, Minhngo
 */
@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
    private static final String HOME_PAGE = "homeForCustomer.jsp";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String LOGOUT_MANAGER_CONTROLLER = "LogoutManagerController";
    private static final String DELIVERY_PAGE = "GetKoiOrder";
    private static final String MANAGER_PAGE = "managerDashboard.jsp";
    private static final String SALES_PAGE = "homeForSales.jsp";
    private static final String CONSULTING_PAGE = "homeForConsulting.jsp";
    private static final String CUSTOM_TOUR_PAGE = "customTour.jsp";
    private static final String CONTACT_PAGE = "contact.jsp";
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
        String url = HOME_PAGE;
        try {
            HttpSession session = request.getSession();
            KoiTypeDAO services = new KoiTypeDAO();
            List<KoiTypeDTO> dtoList = services.getKoiTypeList();
            if (dtoList != null && !dtoList.isEmpty()) {
                session.setAttribute("LIST_KOITYPE", dtoList);
            } else {
                request.setAttribute("ERROR_NULL", "No Koi types found.");
            }
            
            FarmDAO dao = new FarmDAO();
            List<FarmDTO> listFarm = dao.getFarmList();
            if (listFarm != null && !listFarm.isEmpty()) {
                session.setAttribute("LIST_FARM", listFarm);
            } else {
                request.setAttribute("ERROR_NULL", "No Farms found.");
            }
            
            
            if (session != null) {
                // Ở đây sẽ nhận biến session logout đó xong đổi nó thành requestScope
                String message = (String) session.getAttribute("SUCCESS");
                if (message != null) {
                    request.setAttribute("notiSuccess", message);
                    session.removeAttribute("SUCCESS");
                }
            }
            String action = request.getParameter("action");
            if (action == null) {
                url = HOME_PAGE;
            } else if (action.equals("Logout")) {
                url = LOGOUT_CONTROLLER;
            } else if (action.equals("Delivery")) {
                url = DELIVERY_PAGE;
            } else if(action.equals("LogoutManager")){
                url = LOGOUT_MANAGER_CONTROLLER;
            } else if (action.equals("Manager")) {
                url = MANAGER_PAGE;
            } else if (action.equals("Sales")){               
                url = SALES_PAGE;
            } else if (action.equals("Consulting")) {
                url = CONSULTING_PAGE;
            } else if (action.equals("customTour")){
                url = CUSTOM_TOUR_PAGE;
            } else if(action.equals("contact")){
                url = CONTACT_PAGE;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
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
