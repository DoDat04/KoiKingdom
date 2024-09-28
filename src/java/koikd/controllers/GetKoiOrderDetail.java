/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package koikd.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.customer.CustomerDTO;
import koikd.farm.FarmDTO;
import koikd.koi.KoiDTO;
import koikd.order.KoiOrderDAO;
import koikd.order.KoiOrderDTO;
import koikd.order.KoiOrderDetailDTO;

/**
 *
 * @author Minhngo
 */
public class GetKoiOrderDetail extends HttpServlet {

    private final String SHIPHISTORYPAGE = "shipHistory.jsp";

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

        String url = SHIPHISTORYPAGE;
        String nameOrder = request.getParameter("orderName");

        try (PrintWriter out = response.getWriter()) {
            KoiOrderDAO koiOrderDAO = new KoiOrderDAO();
            ArrayList<KoiOrderDTO> koiOrders = koiOrderDAO.getKoiOrderListByNameCustomer(nameOrder);

            if (koiOrders != null && !koiOrders.isEmpty()) {
                processOrderDetails(koiOrders, request, koiOrderDAO);
            } else {
                request.setAttribute("errorMessage", "No orders found.");
            }

            request.getRequestDispatcher(url).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GetKoiOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processOrderDetails(ArrayList<KoiOrderDTO> koiOrders, HttpServletRequest request, KoiOrderDAO koiOrderDAO) throws SQLException {
        ArrayList<String> customerNames = new ArrayList<>();
        ArrayList<KoiOrderDetailDTO> koiOrderDetails = new ArrayList<>();
        ArrayList<FarmDTO> farmNames = new ArrayList<>();
        ArrayList<KoiDTO> koiNames = new ArrayList<>();

        for (KoiOrderDTO koiOrder : koiOrders) {
            // Lấy tên khách hàng
            CustomerDTO name = koiOrderDAO.getCustomerByCustomerID(koiOrder.getCustomerID());
            customerNames.add(name.getLastName() + " " + name.getFirstName());

            // Lấy chi tiết đơn hàng
            KoiOrderDetailDTO koiOrderDetail = koiOrderDAO.getKoiOrderDetaiByID(koiOrder.getKoiOrderID());

            if (koiOrderDetail != null) {
                koiOrderDetails.add(koiOrderDetail);

                // Lấy tên trại và tên koi
                FarmDTO farmName = koiOrderDAO.getFarmByFarmID(koiOrderDetail.getFarmID());
                KoiDTO koiName = koiOrderDAO.getKoiFishByKoiID(koiOrderDetail.getKoiID());

                farmNames.add(farmName);
                koiNames.add(koiName);
            } else {
                // Xử lý nếu không tìm thấy chi tiết đơn hàng (tuỳ chọn)
                request.setAttribute("errorMessage", "No farm found.");
                request.setAttribute("errorMessage", "No koiname found.");

            }
        }

        // Thiết lập thuộc tính cho request
        request.setAttribute("listKoiName", koiNames);
        request.setAttribute("nameFarmList", farmNames);
        request.setAttribute("koiList", koiOrders);
        request.setAttribute("customerNames", customerNames);
        request.setAttribute("koiOrderDetails", koiOrderDetails);
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
