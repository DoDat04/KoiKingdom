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
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.cart.CartBean;
import koikd.customtour.CustomTourDAO;
import koikd.customtour.CustomTourDTO;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "CheckOutController", urlPatterns = {"/checkout"})
public class CheckOutController extends HttpServlet {
    private static final String VIEW_ORDER_PAGE = "viewOrder.jsp";
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
        String url = VIEW_ORDER_PAGE;
        String requestIDParam = request.getParameter("requestid");
        String numberOfPeopleParam = request.getParameter("numberofpeople");
        try {
            HttpSession session = request.getSession();
            if (requestIDParam != null && numberOfPeopleParam != null) {
                int requestID = Integer.parseInt(requestIDParam);
                int numberOfPeople = Integer.parseInt(numberOfPeopleParam); // Convert to integer

                // Fetch the tour details using the requestID
                CustomTourDAO tourDAO = new CustomTourDAO();
                CustomTourDTO selectedTour = tourDAO.getCustomTourByRequest(requestID);

                if (selectedTour != null && numberOfPeople > 0) {
                    CartBean cart = (CartBean) session.getAttribute("cart");

                    if (cart == null) {
                        cart = new CartBean();
                    }

                    // Add the selected tour to the cart with the specified number of people
                    cart.addItemToCartt(selectedTour, numberOfPeople);
                    session.setAttribute("cart", cart);

                    // Optionally, forward to the checkout page
                    url = VIEW_ORDER_PAGE; // Redirect or forward to the checkout page
                } else {
                    request.setAttribute("ERROR", "Tour not found or invalid number of people!");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CheckOutController.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
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
