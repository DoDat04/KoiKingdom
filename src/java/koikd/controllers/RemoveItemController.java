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
import java.util.Base64;
import koikd.cart.CartBean;
import koikd.cart.CartItem;
import koikd.koi.KoiDTO;
import koikd.tour.TourDTO;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "RemoveItemController", urlPatterns = {"/RemoveItemController"})
public class RemoveItemController extends HttpServlet {

    private static final String CART_PAGE = "cart";
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
        String url = CART_PAGE;
        String itemIDParam = request.getParameter("itemID");
        String itemType = request.getParameter("itemType");
        try {
            HttpSession session = request.getSession();
            CartBean cart = (CartBean) session.getAttribute("cart");

            if (cart != null && itemIDParam != null) {
                int itemID = Integer.parseInt(itemIDParam);

                if ("tour".equals(itemType)) {
                    TourDTO tourToRemove = null;
                    for (CartItem item : cart.getItems().values()) {
                        TourDTO tour = item.getTour();
                        if (tour != null && tour.getTourID() == itemID) {
                            tourToRemove = tour;
                            break;
                        }
                    }
                    if (tourToRemove != null) {
                        cart.removeItemFromCart(tourToRemove, 1);
                    }
                } else if ("koi".equals(itemType)) {
                    KoiDTO koiToRemove = null;
                    for (CartItem item : cart.getItems().values()) {
                        KoiDTO koi = item.getKoi();
                        if (koi != null && koi.getKoiID() == itemID) {
                            koiToRemove = koi;
                            break;
                        }
                    }
                    if (koiToRemove != null) {
                        cart.removeKoiFromCart(koiToRemove, 1);
                    }
                }

                // Update the cart item count in session
                session.setAttribute("cartItemCount", cart.getTotalQuantity());

                // Update the cookie if necessary
                String userId = null;
                String cookieName = null;
                if (session.getAttribute("LOGIN_USER") != null) {
                    userId = (String) session.getAttribute("userId");
                    cookieName = "USER_CART" + userId;
                } else if (session.getAttribute("LOGIN_GMAIL") != null) {
                    userId = (String) session.getAttribute("emailPrefix");
                    cookieName = "GMAIL_CART" + userId;
                }

                if (cookieName != null) {
                    UpdateCookie(request, response, cookieName, cart);
                }
            }
        } finally {
            response.sendRedirect(url);
        }
    }

    private void UpdateCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, CartBean cart) throws IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    // Lấy cookie cũ và cập nhật
                    Cookie updatedCartCookie = new Cookie(cookieName, encodeCartToCookie(cart));
                    updatedCartCookie.setMaxAge(60 * 60 * 24 * 7); // Lưu trong 1 tuần
                    updatedCartCookie.setPath("/");
                    response.addCookie(updatedCartCookie); // Ghi cookie đã cập nhật trở lại trình duyệt
                    break;
                }
            }
        }
    }

    private String encodeCartToCookie(CartBean cart) throws IOException {
        byte[] cartBytes = objectMapper.writeValueAsBytes(cart);
        return Base64.getEncoder().encodeToString(cartBytes);
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
