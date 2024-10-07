/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package koikd.controllers;

import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.feedback.FeedbackDAO;
import koikd.feedback.FeedbackDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateFeedbackForCustomer", urlPatterns = {"/CreateFeedbackForCustomer"})
@MultipartConfig
public class CreateFeedbackForCustomer extends HttpServlet {

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
            String customerIDStr = request.getParameter("customerID");
            String tourIDStr = request.getParameter("tourID");
            String feedback = request.getParameter("feedback");
            String ratingStr = request.getParameter("rate");

            // Validate input parameters
            if (customerIDStr == null || tourIDStr == null || feedback == null || ratingStr == null) {
                String jsonResponse = "{\"success\": false, \"message\": \"Tất cả các trường đều là bắt buộc.\"}";
                out.print(jsonResponse);
                return;
            }

            int customerID;
            int tourID;
            int rating;

            try {
                customerID = Integer.parseInt(customerIDStr);
                tourID = Integer.parseInt(tourIDStr);
                rating = Integer.parseInt(ratingStr);
            } catch (NumberFormatException ex) {
                String jsonResponse = "{\"success\": false, \"message\": \"ID và đánh giá phải là số hợp lệ.\"}";
                out.print(jsonResponse);
                return;
            }

            FeedbackDAO feedbackDAO = new FeedbackDAO();

            FeedbackDTO feedbackDTO = feedbackDAO.createFeedbackForCustomer(customerID, tourID, rating, feedback);
            String jsonResponse;
            if (feedbackDTO != null) {
                jsonResponse = "{\"success\": true, \"message\": \"Cảm ơn bạn đã đánh giá!\", \"feedbackID\": " + feedbackDTO.getFeedbackID() + "}";
                HttpSession session = request.getSession();

                // Set feedbackDTO in request scope
                request.setAttribute("feedbackDTO", feedbackDTO);

       
            } else {
                jsonResponse = "{\"success\": false, \"message\": \"Đã xảy ra lỗi khi gửi đánh giá.\"}";
            }
            out.print(jsonResponse);
            out.flush();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateFeedbackForCustomer.class.getName()).log(Level.SEVERE, null, ex);
            String jsonResponse = "{\"success\": false, \"message\": \"Lỗi hệ thống: không tìm thấy lớp cần thiết.\"}";
            response.getWriter().print(jsonResponse);
        } catch (SQLException ex) {
            Logger.getLogger(CreateFeedbackForCustomer.class.getName()).log(Level.SEVERE, null, ex);
            String jsonResponse = "{\"success\": false, \"message\": \"Lỗi hệ thống: không thể truy cập cơ sở dữ liệu.\"}";
            response.getWriter().print(jsonResponse);
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
