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
import koikd.feedback.FeedbackDAO;
import koikd.feedback.FeedbackDTO;
import koikd.tour.TourDAO;
import koikd.tour.TourDTO;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "TourDetailController", urlPatterns = {"/tour-detail"})
public class TourDetailController extends HttpServlet {

    private static final String TOUR_DETAIL_PAGE = "tour-detail.jsp";

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
        String url = TOUR_DETAIL_PAGE;
        String tourIDParam = request.getParameter("tourID");
        try {
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            TourDAO dao = new TourDAO();
            if (tourIDParam != null && !tourIDParam.isEmpty()) {
                int tourID = Integer.parseInt(tourIDParam);
                TourDTO selectedTour = dao.getTourByID(tourID);
                CustomerDTO customer = new CustomerDTO();
                ArrayList<CustomerDTO> customerList = new ArrayList<>();
                ArrayList<FeedbackDTO> feedbackTour = feedbackDAO.getListFeedback(tourID);
                for (FeedbackDTO feedbackDTO : feedbackTour) {
                    customer = feedbackDAO.getCustomerByCustomerID(feedbackDTO.getCustomerID());
                    customerList.add(customer);
                }
                if (feedbackTour != null) {
                    request.setAttribute("feedbackTour", feedbackTour);
                    request.setAttribute("customerList", customerList);
                    url = TOUR_DETAIL_PAGE;
                }

                if (selectedTour != null) {
                    request.setAttribute("selectedTour", selectedTour);
                    url = TOUR_DETAIL_PAGE;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(TourDetailController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TourDetailController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
