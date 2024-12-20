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
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.cart.CartBean;
import koikd.koi.KoiDAO;
import koikd.koi.KoiDTO;
import koikd.tour.TourDAO;
import koikd.tour.TourDTO;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "BookNowController", urlPatterns = {"/BookNowController"})
public class BookNowController extends HttpServlet {

    private static final String CHECK_OUT_PAGE = "checkout";
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
        String url = CHECK_OUT_PAGE;
        String tourIDParam = request.getParameter("tourID");
        String numberOfPeopleParam = request.getParameter("numberOfPeople");
        String koiIDParam = request.getParameter("koiID");
        String quantityParam = request.getParameter("quantity");      

        try {
            HttpSession session = request.getSession();
            if (tourIDParam != null && numberOfPeopleParam != null && !numberOfPeopleParam.isEmpty()) {
                int tourID = Integer.parseInt(tourIDParam);
                int numberOfPeople = Integer.parseInt(numberOfPeopleParam);

                TourDAO dao = new TourDAO();
                TourDTO selectedTour = dao.getTourByID(tourID);

                if (selectedTour != null && numberOfPeople > 0) {
                    CartBean cart = getCartFromCookies(request, session);

                    if (cart == null) {
                        cart = new CartBean();
                    }
                    cart.addItemToCart(selectedTour, numberOfPeople);
                    session.setAttribute("cart", cart);
                    
                    session.setAttribute("cartItemCount", cart.getTotalQuantity());
                    saveCartToCookies(response, cart, request, session);
                }
            } else if (koiIDParam != null && quantityParam != null) {
                int koiID = Integer.parseInt(koiIDParam);
                int quantity = Integer.parseInt(quantityParam);
                
                KoiDAO koiDAO = new KoiDAO();
                KoiDTO selectedKoi = koiDAO.getKoiByID(koiID);
                if (selectedKoi != null && quantity > 0) {
                    CartBean cart = getCartFromCookies(request, session);
                    
                    if (cart == null) {
                        cart = new CartBean();
                    }
                    cart.addKoiToCart(selectedKoi, quantity);
                    session.setAttribute("cart", cart);
                    
                    session.setAttribute("cartItemCount", cart.getTotalQuantity());
                    saveCartToCookies(response, cart, request, session);
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BookNowController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.sendRedirect(url);
        }
    }
    
    private CartBean getCartFromCookies(HttpServletRequest request, HttpSession session) throws IOException {
        String userId = null;
        String cookieName;
        if (session.getAttribute("LOGIN_USER") != null) {
            userId = (String) session.getAttribute("userId");
            cookieName = "USER_CART" + userId;
        } else if (session.getAttribute("LOGIN_GMAIL") != null) {
            userId = (String) session.getAttribute("emailPrefix");
            cookieName = "GMAIL_CART" + userId;
        } else {
            cookieName = "GUEST_CART";
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return decodeCartFromCookie(cookie.getValue());
                }
            }
        }
        return null;
    }

    private void saveCartToCookies(HttpServletResponse response, CartBean cart, HttpServletRequest request, HttpSession session) throws IOException {
        String userId = null;
        String cookieName;
        if (session.getAttribute("LOGIN_USER") != null) {
            userId = (String) session.getAttribute("userId");
            cookieName = "USER_CART" + userId;
        } else if (session.getAttribute("LOGIN_GMAIL") != null) {
            userId = (String) session.getAttribute("emailPrefix");
            cookieName = "GMAIL_CART" + userId;
        } else {
            cookieName = "GUEST_CART";
        }

        String cartEncoded = encodeCartToCookie(cart);
        Cookie cartCookie = new Cookie(cookieName, cartEncoded);
        cartCookie.setMaxAge(60 * 60 * 24 * 7); // cookie tồn tại 7 ngày
        cartCookie.setPath("/");
        response.addCookie(cartCookie);
    }

    private String encodeCartToCookie(CartBean cart) throws IOException {
        byte[] cartBytes = objectMapper.writeValueAsBytes(cart);
        return Base64.getEncoder().encodeToString(cartBytes);
    }

    private CartBean decodeCartFromCookie(String cartEncoded) throws IOException {
        byte[] cartBytes = Base64.getDecoder().decode(cartEncoded);
        return objectMapper.readValue(cartBytes, CartBean.class);
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
