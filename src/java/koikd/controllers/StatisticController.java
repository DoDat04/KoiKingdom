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
import koikd.booking.BookingDAO;
import koikd.order.KoiOrderDAO;

/**
 *
 * @author ADMIN LAM
 */
@WebServlet(name = "CountBookingController", urlPatterns = {"/count"})
public class StatisticController extends HttpServlet {

    private static final String COUNT_BOOKING = "managerDashboard.jsp";

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
        String url = COUNT_BOOKING;
        response.setContentType("text/html;charset=UTF-8");
        
       String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        /**
         * Count Booking
         */
        BookingDAO daotour = new BookingDAO();
        int countBooking = daotour.countBooking(startDate, endDate);
        if (countBooking > 0) {
//            System.out.println("Booking count: " + countBooking);
            request.setAttribute("BOOKING_COUNT", countBooking);
        } else {
            request.setAttribute("BOOKING_COUNT", 0);
        }
        /**
         * Count customer
         */

        int countCustomer = daotour.countCustomer(startDate, endDate);
        if (countCustomer > 0) {
//            System.out.println("Booking count: " + countBooking);
            request.setAttribute("CUSTOMER_COUNT", countCustomer);
        } else {
            request.setAttribute("CUSTOMER_COUNT", 0);
        }
        /**
         * Count Koi order
         */
        KoiOrderDAO daoorder = new KoiOrderDAO();
        int countOrder = daoorder.countKoiOrder(startDate, endDate);
        if (countOrder > 0) {
//            System.out.println("Booking count: " + countBooking);
            request.setAttribute("ORDER_COUNT", countOrder);
        } else {
            request.setAttribute("ORDER_COUNT",0);
        }

        /**
         * Count revenue
         */
        double countRevenue = daoorder.countRevenue(startDate, endDate);
        if (countRevenue > 0) {
//            System.out.println("Booking count: " + countBooking);
            request.setAttribute("REVENUE_COUNT", countRevenue);
        } else {
            request.setAttribute("REVENUE_COUNT", 0);
        }
        
        
        request.getRequestDispatcher(url).forward(request, response);
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
