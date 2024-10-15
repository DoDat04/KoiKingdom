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
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.feedback.FeedbackDAO;
import koikd.feedback.FeedbackDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "GetFeedbackController", urlPatterns = {"/get_feedback"})
public class GetFeedbackController extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String customerIDStr = request.getParameter("customerID");
            String tourIDStr = request.getParameter("tourID");
            String bookingIDStr = request.getParameter("bookingID");

            // Validate the parameters
            if (customerIDStr == null || tourIDStr == null || bookingIDStr == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"message\": \"Missing parameters.\"}");
                return;
            }

            int customerID = Integer.parseInt(customerIDStr);
            int tourID = Integer.parseInt(tourIDStr);
            int bookingID = Integer.parseInt(bookingIDStr);

            FeedbackDAO feedbackDAO = new FeedbackDAO();
            FeedbackDTO feedbackDTO = feedbackDAO.getFeedbackForCustomer(customerID, tourID, bookingID);

            // Check if feedback exists
            if (feedbackDTO == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print("{\"message\": \"Feedback not found.\"}");
                return;
            }

            // Construct JSON response without bookingID
            String jsonResponse = String.format(
                    "{\"customerID\": %d, \"tourID\": %d, \"feedbackID\": %d, \"feedbackText\": \"%s\", \"rating\": %d, \"bookingID\": %d}",
                    feedbackDTO.getCustomerID(),
                    feedbackDTO.getTourID(),
                    feedbackDTO.getFeedbackID(),
                    feedbackDTO.getFeedbackText().replace("\"", "\\\""),
                    feedbackDTO.getRating(),
                    bookingID // Ensure this is included correctly
            );

            out.print(jsonResponse);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(GetFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (NumberFormatException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            
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
