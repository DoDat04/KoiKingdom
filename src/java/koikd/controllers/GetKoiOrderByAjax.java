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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.customer.CustomerDTO;
import koikd.order.KoiOrderDAO;
import koikd.order.KoiOrderDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "GetKoiOrderByAjax", urlPatterns = {"/GetKoiOrderByAjax"})
public class GetKoiOrderByAjax extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String nameOrder = request.getParameter("txtNameCustomer");
            KoiOrderDAO koiOrderDAO = new KoiOrderDAO();
            ArrayList<KoiOrderDTO> koiList = koiOrderDAO.getKoiOrderListByNameCustomer(nameOrder);

            if (koiList != null && !koiList.isEmpty()) {
                out.print("<table class=\"styled-table\">");
                out.print("<thead>");
                out.print("<tr>");
                out.print("<th>KoiOrderID</th>");
                out.print("<th>CustomerID</th>");
                out.print("<th>Customer Name</th>");
                out.print("<th>Delivery Date</th>");
                out.print("<th>Status</th>");
                out.print("<th>Actions</th>");
                out.print("</tr>");
                out.print("</thead>");
                out.print("<tbody>");

                for (KoiOrderDTO koiOrderDTO : koiList) {
                    CustomerDTO name = koiOrderDAO.getCustomerByCustomerID(koiOrderDTO.getCustomerID());
                    out.print("<tr>");
                    out.print("<td>" + koiOrderDTO.getKoiOrderID() + "</td>");
                    out.print("<td>" + koiOrderDTO.getCustomerID() + "</td>");
                    out.print("<td>" + name.getLastName() + " " + name.getFirstName() + "</td>");
                    out.print("<td>" + koiOrderDTO.getDeliveryDate() + "</td>");
                    out.print("<td>" + (koiOrderDTO.isStatus() ? "Completed" : "Pending") + "</td>");
                    out.print("<td>");

                    // Corrected form structure without extra closing tag
                    out.print("<form action=\"GetKoiOrderDetail\" method=\"GET\" style=\"display:inline;\">");
                    out.print("<input type=\"hidden\" name=\"orderID\" value=\"" + koiOrderDTO.getKoiOrderID() + "\">");
                    out.print("<input type=\"hidden\" name=\"customerID\" value=\"" + koiOrderDTO.getCustomerID() + "\">");
                    out.print("<button class=\"btn-detail\" type=\"submit\" style=\"border: none; background: none;\">Detail</button>");
                    out.print("</form>"); // Close the form tag here

                    out.print("</td>");
                    out.print("</tr>");
                }

                out.print("</tbody>");
                out.print("</table>");
            } else {
                out.print("<p>No orders found for this customer.</p>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request.");
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
