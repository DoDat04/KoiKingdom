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
import koikd.customtour.CustomTourDAO;
import koikd.customtour.CustomTourDTO;

/**
 *
 * @author ADMIN LAM
 */
@WebServlet(name = "GetCustomTourByAjaxController", urlPatterns = {"/list-custom-ajax"})
public class GetCustomTourByAjaxController extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String fullName = request.getParameter("txtFullName");
            CustomTourDAO services = new CustomTourDAO();
            ArrayList<CustomTourDTO> dtoList = services.getListCustomTour(fullName);
            if (dtoList != null && !dtoList.isEmpty()) {
                out.print("<table id=\"content\" class=\"styled-table\">\n"
                        + "                <thead>\n"
                        + "                    <tr>\n"
                        + "                        <th>Request ID</th>\n"
                        + "                        <th>Customer ID</th>\n"
                        + "                        <th>Customer Name</th>\n"
                        + "                        <th>Duration</th>\n"
                        + "                        <th>Start Date</th>\n"
                        + "                        <th>End Date</th>\n"
                        + "                        <th>Status</th>\n"
                        + "                        <th>Manager Approval Status</th>\n"
                        + "                        <th>Departure Location</th>\n"
                        + "                        <th>Farm Name</th>\n"
                        + "                        <th>KoiType Name</th>\n"
                        + "                        <th>Quantity</th>\n"
                        + "                        <th>Quotation Price</th>\n"
                        + "                        <th>Image</th>\n"
                        + "                        <th>Actions</th>\n"
                        + "                    </tr>\n"
                        + "                </thead>\n"
                        + "                <tbody>");

                for (CustomTourDTO dto : dtoList) {
                    out.print("<tr>\n"
                            + "<td>" + dto.getRequestID() + "</td>\n"
                            + "<td>" + dto.getCustomerID() + "</td>\n"
                            + "<td>" + dto.getCustName() + "</td>\n"
                            + "<td>" + dto.getDuration() + "</td>\n"
                            + "<td>" + dto.getStartDate() + "</td>\n"
                            + "<td>" + dto.getEndDate() + "</td>\n"
                            + "<td class=\"" + (dto.getStatus().equals("Pending") ? "text-warning"
                            : (dto.getStatus().equals("Approved") ? "text-success"
                            : (dto.getStatus().equals("Rejected") ? "text-danger" : ""))) + "\">\n"
                            + dto.getStatus() + "</td>\n"
                            + "<td class=\"" + (dto.getManagerApprovalStatus().equals("Pending") ? "text-warning"
                            : (dto.getManagerApprovalStatus().equals("Approved") ? "text-success"
                            : (dto.getManagerApprovalStatus().equals("Rejected") ? "text-danger" : ""))) + "\">\n"
                            + dto.getManagerApprovalStatus() + "</td>\n"
                            + "<td>" + dto.getDepartureLocation() + "</td>\n"
                            + "<td>" + dto.getFarmName() + "</td>\n"
                            + "<td>" + dto.getKoiTypeName() + "</td>\n"
                            + "<td>" + dto.getQuantity() + "</td>\n"
                            + "<td>$" + dto.getQuotationPrice() + "</td>\n"
                            + "<td><img src=\"" + dto.getImage() + "\" class=\"custom-tour\" alt=\"\" style=\"height: 100px; width: 150px; border-radius: 10px;\"/></td>\n"
                            + "<td>\n"
                            + "    <button type=\"button\" class=\"btn btn-primary mb-2\" data-bs-toggle=\"modal\" data-bs-target=\"#updateModal" + dto.getRequestID() + "\">\n"
                            + "        Update\n"
                            + "    </button>\n"
                            + "    <button type=\"button\" class=\"btn btn-success\" data-bs-toggle=\"modal\" data-bs-target=\"#sendModal" + dto.getRequestID() + "\">\n"
                            + "        Send\n"
                            + "    </button>\n"
                            + "</td>\n"
                            + "\n"
                            + "<!-- Modal for Update -->\n"
                            + "<div class=\"modal fade\" id=\"updateModal" + dto.getRequestID() + "\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"updateModalLabel" + dto.getRequestID() + "\" aria-hidden=\"true\">\n"
                            + "    <div class=\"modal-dialog\">\n"
                            + "        <div class=\"modal-content\">\n"
                            + "            <div class=\"modal-header\">\n"
                            + "                <h1 class=\"modal-title fs-5\" id=\"updateModalLabel" + dto.getRequestID() + "\">Update Price and Status</h1>\n"
                            + "                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n"
                            + "            </div>\n"
                            + "            <div class=\"modal-body\">\n"
                            + "                <form id=\"updateForm" + dto.getRequestID() + "\" method=\"post\" action=\"update-price-status\">\n"
                            + "                    <div class=\"mb-3\">\n"
                            + "                        <label for=\"quotationPrice" + dto.getRequestID() + "\" class=\"form-label\">Quotation Price</label>\n"
                            + "                        <input type=\"number\" step=\"0.01\" class=\"form-control\" id=\"quotationPrice" + dto.getRequestID() + "\" name=\"txtQuoPrice\" placeholder=\"Enter new quotation price\" required>\n"
                            + "                    </div>\n"
                            + "                    <div class=\"mb-3\">\n"
                            + "                        <label for=\"status" + dto.getRequestID() + "\" class=\"form-label\">Status</label>\n"
                            + "                        <select class=\"form-control\" id=\"status" + dto.getRequestID() + "\" name=\"txtStatus\" required>\n"
                            + "                            <option value=\"Pending\">Pending</option>\n"
                            + "                            <option value=\"Approved\">Approved</option>\n"
                            + "                            <option value=\"Rejected\">Rejected</option>\n"
                            + "                        </select>\n"
                            + "                    </div>\n"
                            + "                    <input type=\"hidden\" name=\"txtReq\" value=\"" + dto.getRequestID() + "\">\n"
                            + "                </form>\n"
                            + "            </div>\n"
                            + "            <div class=\"modal-footer\">\n"
                            + "                <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Close</button>\n"
                            + "                <button type=\"submit\" form=\"updateForm" + dto.getRequestID() + "\" class=\"btn btn-primary\">Update</button>\n"
                            + "            </div>\n"
                            + "        </div>\n"
                            + "    </div>\n"
                            + "</div>\n"
                            + "<!-- Modal for Send -->\n"
                            + "<div class=\"modal fade\" id=\"sendModal" + dto.getRequestID() + "\" tabindex=\"-1\" aria-labelledby=\"sendModalLabel\" aria-hidden=\"true\">\n"
                            + "    <div class=\"modal-dialog\">\n"
                            + "        <div class=\"modal-content\">\n"
                            + "            <div class=\"modal-header\">\n"
                            + "                <h1 class=\"modal-title fs-5\" id=\"sendModalLabel\">Send</h1>\n"
                            + "                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n"
                            + "            </div>\n"
                            + "            <div class=\"modal-body\">\n"
                            + "                Are you sure about that?\n"
                            + "            </div>\n"
                            + "            <div class=\"modal-footer\">\n"
                            + "                <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">No</button>\n"
                            + "                <button type=\"button\" class=\"btn btn-primary\">Yes</button>\n"
                            + "            </div>\n"
                            + "        </div>\n"
                            + "    </div>\n"
                            + "</div>\n"
                            + "</tr>\n");
                }

                out.print("</tbody>");
                out.print("</table>");
            } else {
                out.print("<p>No custom tour found for this customer.</p>");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            Logger.getLogger(GetCustomTourByAjaxController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetCustomTourByAjaxController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GetCustomTourByAjaxController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetCustomTourByAjaxController.class.getName()).log(Level.SEVERE, null, ex);
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
