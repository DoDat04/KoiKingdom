/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package koikd.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.cart.CartBean;
import koikd.farm.FarmDAO;
import koikd.farm.FarmDTO;
import koikd.koi.KoiDAO;
import koikd.koi.KoiDTO;
import koikd.koitype.KoiTypeDAO;
import koikd.koitype.KoiTypeDTO;
import koikd.order.KoiOrderDAO;
import koikd.tour.TourDAO;
import koikd.tour.TourDTO;

/**
 *
 * @author Do Dat, Minhngo, ADMIN LAM
 */
@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    private static final String HOME_PAGE = "homeForCustomer.jsp";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String DELIVERY_PAGE = "GetKoiOrder";
    private static final String MANAGER_PAGE = "/count";
    private static final String SALES_PAGE = "homeForSales.jsp";
    private static final String CONSULTING_PAGE = "homeForConsulting.jsp";
    private static final String CUSTOM_TOUR_PAGE = "customTour.jsp";
    private static final String ORDER_KOI_PAGE = "koiOrderForm.jsp";
    private static final String FAQ_PAGE = "faq.jsp";
    private static final ObjectMapper objectMapper = new ObjectMapper();

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
            CartBean guestCart = getCartFromCookie(request);
            session.setAttribute("cart", guestCart);

            KoiTypeDAO services = new KoiTypeDAO();
            KoiOrderDAO daoo = new KoiOrderDAO();
            List<KoiDTO> dtoListt = daoo.getKoiList();
            if (dtoListt != null && !dtoListt.isEmpty()) {
                session.setAttribute("LIST_KOI", dtoListt);
            } else {
                request.setAttribute("ERROR_NULL", "No Koi types found.");
            }
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
                TourDAO services1 = new TourDAO();
                ArrayList<TourDTO> trendingTours = services1.getTrendingTour();
                if (trendingTours != null && !trendingTours.isEmpty()) {
                    request.setAttribute("TRENDING_TOURS", trendingTours);
                } else {
                    request.setAttribute("ERROR", "Cannot find trending tour");
                }
                
                KoiDAO services2 = new KoiDAO();
                ArrayList<KoiDTO> list = services2.koiOrderTrending();
                if (list != null && !list.isEmpty()) {
                    request.setAttribute("KOI_TRENDING", list);
                } else {
                    request.setAttribute("ERROR", "Cannot display.");
                }
                
                TourDAO services3 = new TourDAO();
                ArrayList<TourDTO> highestTourRating = services3.TopHighestRatingTour();
                if(highestTourRating!=null && !highestTourRating.isEmpty()){
                    request.setAttribute("HIGHEST_RATING_TOUR", highestTourRating);
                } else{
                    request.setAttribute("ERROR", "Cannot list.");
                }
                url = HOME_PAGE;

            } else if (action.equals("Logout")) {
                url = LOGOUT_CONTROLLER;
            } else if (action.equals("Delivery")) {
                url = DELIVERY_PAGE;
            } else if (action.equals("Manager")) {
                url = MANAGER_PAGE;
            } else if (action.equals("Sales")) {
                url = SALES_PAGE;
            } else if (action.equals("Consulting")) {
                url = CONSULTING_PAGE;
            } else if (action.equals("customTour")) {
                url = CUSTOM_TOUR_PAGE;
            } else if (action.equals("koiOrderForm")) {
                url = ORDER_KOI_PAGE;
            } else if (action.equals("faq")) {
                url = FAQ_PAGE;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private CartBean getCartFromCookie(HttpServletRequest request) throws IOException {
        Cookie[] cookies = request.getCookies();
        String cookieName = null;
        HttpSession session = request.getSession();
        int cartItemCount = 0;

        if (session.getAttribute("LOGIN_USER") != null) {
            String userId = (String) session.getAttribute("userId");
            cookieName = "USER_CART" + userId;
        } else if (session.getAttribute("LOGIN_GMAIL") != null) {
            String userEmail = (String) session.getAttribute("emailPrefix");
            cookieName = "GMAIL_CART" + userEmail;
        }

        if (cookieName != null && cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    try {
                        byte[] cartBytes = Base64.getDecoder().decode(cookie.getValue());
                        CartBean cart = objectMapper.readValue(cartBytes, CartBean.class);
                        cartItemCount = cart.getTotalQuantity();
                        session.setAttribute("cartItemCount", cartItemCount);
                        return cart;
                    } catch (IOException e) {
                        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        }

        session.setAttribute("cartItemCount", cartItemCount);
        return null;
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
