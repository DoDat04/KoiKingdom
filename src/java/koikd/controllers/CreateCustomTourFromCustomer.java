/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package koikd.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import koikd.customtour.CustomTourDAO;
import koikd.customtour.CustomTourDTO;

/**
 *
 * @author ADMIN LAM
 */
@WebServlet(name = "CreateCustomTourFromCustomer", urlPatterns = {"/create-customtour"})
public class CreateCustomTourFromCustomer extends HttpServlet {

    private static final String SUCCESS_CREATE_CUSTOMTOUR = "customTour.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String url = SUCCESS_CREATE_CUSTOMTOUR;

        HttpSession session = request.getSession();
        Integer custID = (Integer) session.getAttribute("custID");
        String fullName = request.getParameter("fullName");
        String duration = request.getParameter("duration");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String departureLocation = request.getParameter("departureLocation");
        String[] farmNames = request.getParameterValues("farms");
        String farmNameString = "";

        if (farmNames != null) {
            farmNameString = String.join(", ", farmNames);
        }
        String[] koiTypeNames = request.getParameterValues("koiTypes");
        String koiTypeNameString = "";

        if (koiTypeNames != null) {
            koiTypeNameString = String.join(", ", koiTypeNames);
        }
        String quantityStr = request.getParameter("quantity");
        int quantity = 0;

        if (quantityStr != null && !quantityStr.isEmpty()) {
            quantity = Integer.parseInt(quantityStr);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = dateFormat.parse(startDateStr);
            endDate = dateFormat.parse(endDateStr);
            if (endDate.compareTo(startDate) <= 0) {
                request.setAttribute("errorMessage", "End date must be later than start date.");
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
                
        } catch (ParseException e) {
            request.setAttribute("errorMessage", "Invalid date format. Please use yyyy-MM-dd.");
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

        CustomTourDTO customTourDTO = new CustomTourDTO();
        customTourDTO.setCustomerID(custID);
        customTourDTO.setCustName(fullName);
        customTourDTO.setDuration(duration);
        customTourDTO.setStartDate(startDate);
        customTourDTO.setEndDate(endDate);
        customTourDTO.setDepartureLocation(departureLocation);
        customTourDTO.setFarmName(farmNameString);
        customTourDTO.setKoiTypeName(koiTypeNameString);
        customTourDTO.setQuantity(quantity);
        customTourDTO.setStatus("Pending");
        customTourDTO.setManagerApprovalStatus("Pending");

        CustomTourDAO customTourDAO = new CustomTourDAO();
        try {
            boolean isCreated = customTourDAO.createCustomTourFromCustomer(customTourDTO);

            if (isCreated) {
                request.setAttribute("successMessage", "Custom tour was created successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to create custom tour.");
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
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
