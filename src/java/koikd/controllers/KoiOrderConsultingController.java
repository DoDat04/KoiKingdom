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
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.order.KoiOrderDAO;
import koikd.order.KoiOrderDTO;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "KoiOrderConsultingController", urlPatterns = {"/koi-order-list"})
public class KoiOrderConsultingController extends HttpServlet {

    private static final String KOI_ORDER_LIST = "koiOrderList.jsp";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

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
        String url = KOI_ORDER_LIST;
        HttpSession session = request.getSession();
        Integer consultingID = (Integer) session.getAttribute("consultingID");
        String koiOrderIDParam = request.getParameter("koiOrderID");
        String estimateDateParam = request.getParameter("estimatedDelivery");
        try {
            KoiOrderDAO dao = new KoiOrderDAO();
            if (koiOrderIDParam != null && !koiOrderIDParam.isEmpty() && estimateDateParam != null && !estimateDateParam.isEmpty()) {
                int koiOrderID = Integer.parseInt(koiOrderIDParam);
                Date estimateDate = DATE_FORMAT.parse(estimateDateParam);

                Timestamp timestamp = new Timestamp(estimateDate.getTime());

                dao.updateEstimatedDate(koiOrderID, timestamp);
                request.setAttribute("UPDATESUCCESS", "Update Estimated Date Successfully!");
            }

            List<KoiOrderDTO> dto = dao.getKoiOrderForConsulting(consultingID);
            request.setAttribute("KOIORDERLIST", dto);
        } catch (SQLException ex) {
            Logger.getLogger(KoiOrderConsultingController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KoiOrderConsultingController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(KoiOrderConsultingController.class.getName()).log(Level.SEVERE, null, ex);
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
