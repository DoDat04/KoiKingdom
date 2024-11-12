/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package koikd.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.customer.CustomerDTO;
import koikd.employees.EmployeesDTO;
import koikd.order.KoiOrderDAO;
import koikd.order.KoiOrderDTO;

/**
 *
 * @author Minhngo
 */
public class GetKoiOrder extends HttpServlet {

    private final String SHIPHISTORYPAGE = "homeForDelivery.jsp";
    private final String MANAGEKOIORDERPAGE = "manageKoiOrder.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userType = request.getParameter("userType");

        String url = null;

        if ("manage".equals(userType)) {
            url = MANAGEKOIORDERPAGE; // Đi đến trang quản lý cho nhân viên
        } else {
            url = SHIPHISTORYPAGE;
        }

        try {
            // Retrieve the input parameters
            String nameOrder = request.getParameter("txtNameCustomer");
            String searchValue = request.getParameter("txtSearchValue");
            String employeeId = request.getParameter("employeeId");
            String index = request.getParameter("index");
            HttpSession session = request.getSession();

// Lấy giá trị LOGIN_DELIVERY từ session
            EmployeesDTO deliveryEmployee = (EmployeesDTO) session.getAttribute("LOGIN_DELIVERY");
            int deliveryBy = 0; // Default value

            if (deliveryEmployee != null) {
                deliveryBy = deliveryEmployee.getEmployeeID();
            }

            System.out.println(userType);
            // Default to 1 if no index is provided
            if (index == null || index.isEmpty()) {
                index = "1";
            }
            String searchData = null;
            if (nameOrder == null) {
                searchData = searchValue;
            } else {
                searchData = nameOrder;
            }

            // Parse index as an integer
            int pageIndex = Integer.parseInt(index);
            // Call DAO to get order list
            KoiOrderDAO koiOrderDAO = new KoiOrderDAO();
            int numberOfPages = koiOrderDAO.getNumberPage(searchData, deliveryBy);
            request.setAttribute("numberOfPages", numberOfPages);

            ArrayList<KoiOrderDTO> koiList = koiOrderDAO.getKoiOrderListByNameCustomer(searchData, pageIndex, deliveryBy);
            ArrayList<String> customerNames = new ArrayList<>();
            ArrayList<String> customerPhones = new ArrayList<>();
            ArrayList<String> employeeNames = new ArrayList<>();

            // If list is not empty, populate customer names
            if (koiList != null && !koiList.isEmpty()) {
                for (KoiOrderDTO koiOrderDTO : koiList) {
                    CustomerDTO customer = koiOrderDAO.getCustomerByCustomerID(koiOrderDTO.getCustomerID());
                    customerPhones.add(customer.getPhoneNumber());
                    customerNames.add(customer.getLastName() + " " + customer.getFirstName());

                    // Check if deliveryBy is valid
                    int deliveryById = koiOrderDTO.getDeliveryBy();
                    EmployeesDTO employee = (deliveryById != 0) ? koiOrderDAO.getEmployeeByEmployeeID(deliveryById) : null;

                    if (employee != null) {
                        employeeNames.add(employee.getLastName() + " " + employee.getFirstName());
                    } else {
                        employeeNames.add("Not Assigned"); // Placeholder for missing delivery employee
                    }
                }
                request.setAttribute("employeeId", employeeId);
                request.setAttribute("koiList", koiList);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("customerNames", customerNames);
                request.setAttribute("customerPhones", customerPhones);
                request.setAttribute("employeeNames", employeeNames);
            } else {
                // No orders found case
                request.setAttribute("errorMessage", "No orders found.");
            }

            // Forward the request to the JSP page
            request.getRequestDispatcher(url).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GetKoiOrder.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "An error occurred while retrieving the orders.");
            request.getRequestDispatcher(url).forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetKoiOrder.class.getName()).log(Level.SEVERE, null, ex);
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
