/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package koikd.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
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
@WebServlet(name = "UpdateFeedbackController", urlPatterns = {"/update_feedback"})
@MultipartConfig
public class UpdateFeedbackController extends HttpServlet {

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Retrieve and validate input parameters
          String customerIDStr = request.getParameter("customerID");
    String tourIDStr = request.getParameter("tourID");
    String bookingIDStr = request.getParameter("bookingID");
    String feedback = request.getParameter("feedback");
    String ratingStr = request.getParameter("rate");

    // Kiểm tra xem các tham số có nhận được giá trị không
    System.out.println("customerID: " + customerIDStr);
    System.out.println("tourID: " + tourIDStr);
    System.out.println("bookingID: " + bookingIDStr);
    System.out.println("feedback: " + feedback);
    System.out.println("rating: " + ratingStr);

            if (customerIDStr == null || tourIDStr == null || bookingIDStr == null || feedback == null || ratingStr == null) {
                out.print("{\"success\": false, \"message\": \"Tất cả các trường đều là bắt buộc.\"}");
                return;
            }

            int customerID, tourID, bookingID, rating;

            try {
                customerID = Integer.parseInt(customerIDStr);
                tourID = Integer.parseInt(tourIDStr);
                bookingID = Integer.parseInt(bookingIDStr);
                rating = Integer.parseInt(ratingStr);
            } catch (NumberFormatException e) {
                out.print("{\"success\": false, \"message\": \"ID và đánh giá phải là số hợp lệ.\"}");
                return;
            }

            FeedbackDAO feedbackDAO = new FeedbackDAO();
            FeedbackDTO feedbackDTO = feedbackDAO.updateFeedbackForCustomer(customerID, tourID, rating, feedback, bookingID);

            if (feedbackDTO != null) {
                String jsonResponse = "{\"success\": true, \"message\": \"Cảm ơn bạn đã đánh giá!\", \"feedbackID\": " + feedbackDTO.getFeedbackID() + "}";
                out.print(jsonResponse);
            } else {
                out.print("{\"success\": false, \"message\": \"Đã xảy ra lỗi khi gửi đánh giá.\"}");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdateFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().print("{\"success\": false, \"message\": \"Đã xảy ra lỗi hệ thống.\"}");
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
