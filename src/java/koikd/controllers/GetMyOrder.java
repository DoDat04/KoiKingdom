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
 * @author Admin
 */
@WebServlet(name = "GetMyOrder", urlPatterns = {"/MyOrder"})
public class GetMyOrder extends HttpServlet {

    private final String MYORDERPAGE = "myOrderForCustomer.jsp";

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
        String url = MYORDERPAGE;
        try (PrintWriter out = response.getWriter()) {
            String customerID = request.getParameter("customerID");
            if (customerID != null) {

                KoiOrderDAO dao = new KoiOrderDAO();
                CustomerDTO customer = new CustomerDTO();
                FarmDTO farm = new FarmDTO();
                KoiDTO koiFish = new KoiDTO();

                ArrayList<KoiOrderDTO> koiOrderList = dao.getKoiOrderListByID(Integer.parseInt(customerID));
                ArrayList<FarmDTO> farmCollection = new ArrayList<>();
                ArrayList<KoiDTO> koiFishCollection = new ArrayList<>();
                ArrayList<KoiOrderDetailDTO> koiOrderDetailCollection = new ArrayList<>();

                if (koiOrderList != null && !koiOrderList.isEmpty()) {

                    for (KoiOrderDTO koiOrder : koiOrderList) {
                        ArrayList<KoiOrderDetailDTO> koiOrderDetailList = dao.getKoiOrderDetaiListById(koiOrder.getKoiOrderID());
                        if (!koiOrderDetailList.isEmpty()) {

                            for (KoiOrderDetailDTO koiOrderDetailItem : koiOrderDetailList) {

                                farm = dao.getFarmByFarmID(koiOrderDetailItem.getFarmID());
                                farmCollection.add(farm);
                                koiFish = dao.getKoiFishByKoiID(koiOrderDetailItem.getKoiID());
                                koiFishCollection.add(koiFish);
                                koiOrderDetailCollection.add(koiOrderDetailItem);
                            }
                        } else {
                            request.setAttribute("Error", "Some orders do not have details");
                        }
                        customer = dao.getCustomerByCustomerID(koiOrder.getCustomerID());
                    }

                } else {
                    request.setAttribute("Error", "No orders found.");
                }
                request.setAttribute("koiOrderDetails", koiOrderDetailCollection);
                request.setAttribute("koiNames", koiFishCollection);
                request.setAttribute("customerNames", customer);
                request.setAttribute("farmNames", farmCollection);
                request.setAttribute("myOrder", koiOrderList);

            } else {
                request.setAttribute("Error", "User is not logged in.");
            }

            request.getRequestDispatcher(url).forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(GetMyOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetMyOrder.class.getName()).log(Level.SEVERE, null, ex);
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