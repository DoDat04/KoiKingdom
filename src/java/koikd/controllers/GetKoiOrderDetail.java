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
        try (PrintWriter out = response.getWriter()) {
            String userType = request.getParameter("userType");
            String koiOrderID = request.getParameter("orderID");
            String customerID = request.getParameter("customerID");
            if (customerID != null && koiOrderID != null) {
                KoiOrderDAO dao = new KoiOrderDAO();
                FarmDTO farm = new FarmDTO();
                KoiDTO koiFish = new KoiDTO();
                CustomerDTO customer = new CustomerDTO();

                ArrayList<KoiOrderDTO> koiOrderList = dao.getKoiOrderListByID(Integer.parseInt(customerID));
                ArrayList<KoiOrderDTO> koiOrderListByOrderList = dao.getKoiOrderListByOrderID(Integer.parseInt(koiOrderID));

                ArrayList<KoiOrderDetailDTO> koiOrderDetailCollection = new ArrayList<>();
                ArrayList<FarmDTO> farmCollection = new ArrayList<>();
                ArrayList<KoiDTO> koiFishCollection = new ArrayList<>();

                for (KoiOrderDTO koiOrderDTO : koiOrderList) {
                    if (koiOrderDTO.getKoiOrderID() == Integer.parseInt(koiOrderID)) {
                        ArrayList<KoiOrderDetailDTO> koiOrderDetailList = dao.getKoiOrderDetaiListById(Integer.parseInt(koiOrderID));
                        for (KoiOrderDetailDTO koiOrderDetailItem : koiOrderDetailList) {
                            koiOrderDetailCollection.add(koiOrderDetailItem);
                            farm = dao.getFarmByFarmID(koiOrderDetailItem.getFarmID());
                            farmCollection.add(farm);
                            koiFish = dao.getKoiFishByKoiID(koiOrderDetailItem.getKoiID());
                            koiFishCollection.add(koiFish);
                        }
                    }
                }
                customer = dao.getCustomerByCustomerID(Integer.parseInt(customerID));

                request.setAttribute("koiOrderListByOrderList", koiOrderListByOrderList);
                request.setAttribute("koiOrderDetails", koiOrderDetailCollection);
                request.setAttribute("koiNames", koiFishCollection);
                request.setAttribute("customer", customer);
                request.setAttribute("userType", userType);
                request.setAttribute("farmNames", farmCollection);
                request.setAttribute("myOrders", koiOrderList);
            }
           

            request.getRequestDispatcher(url).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GetKoiOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetKoiOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
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
