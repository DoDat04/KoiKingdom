/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package koikd.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.tour.TourDAO;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "CreateTourController", urlPatterns = {"/CreateTourController"})
@MultipartConfig
public class CreateTourController extends HttpServlet {
    private static final String CREATE_TOUR_PAGE = "createTour.jsp";
    private static final String UPLOAD_DIR = "img/TourImage";

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
        String url = CREATE_TOUR_PAGE;
        String tourName = request.getParameter("tourName");
        String duration = request.getParameter("duration");
        String description = request.getParameter("description");
        String tourPriceStr = request.getParameter("tourPrice");
        String startDateParam = request.getParameter("startDate");
        String endDateParam = request.getParameter("endDate");
        String departureLocation = request.getParameter("departureLocation");
        Part filePart = request.getPart("tourImage");

        String uploadPath = getServletContext().getRealPath("/img/TourImage");

        String imagePath = uploadFile(filePart, uploadPath);

        String[] selectedFarms = request.getParameterValues("farms");
        String[] selectedKoiTypes = request.getParameterValues("koiTypes");
        double tourPrice = 0;
        try {
            tourPrice = Double.parseDouble(tourPriceStr);
            Timestamp startDate = Timestamp.valueOf(startDateParam + " 00:00:00");
            Timestamp endDate = Timestamp.valueOf(endDateParam + " 00:00:00");
            if (endDate.compareTo(startDate) <= 0) {
                request.setAttribute("errorMessage", "End date must be later than start date.");
                return;
            }
            TourDAO dao = new TourDAO();
            boolean result = dao.createTour(tourName, duration, description, tourPrice, startDate, endDate, imagePath, selectedFarms, selectedKoiTypes, departureLocation);
            if (result) {
                url = CREATE_TOUR_PAGE;
                request.setAttribute("CREATE_SUCCESS", "Create tour success");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CreateTourController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateTourController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String uploadFile(Part filePart, String uploadDir) throws IOException {
        File uploadDirectory = new File(uploadDir);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs(); // Create directory if it doesn't exist
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        File file = new File(uploadDirectory, fileName);

        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        // Return the relative path to store in the database
        return UPLOAD_DIR + "/" + fileName;
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
