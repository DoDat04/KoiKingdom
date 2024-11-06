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
import java.util.List;
import koikd.farm.FarmDTO;
import koikd.koi.KoiDAO;
import koikd.koi.KoiDTO;
import koikd.tour.TourDAO;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "GetKoiByFarmIDController", urlPatterns = {"/getKoiData"})
public class GetKoiByFarmIDController extends HttpServlet {

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
        String action = request.getParameter("action"); 
        String farmID = request.getParameter("farmID");
        String koiTypeID = request.getParameter("koiTypeID");
        String tourID = request.getParameter("tourID");
        
        try (PrintWriter out = response.getWriter()) {
            KoiDAO dao = new KoiDAO();
            TourDAO tourDao = new TourDAO();
            JSONArray jsonArray = new JSONArray();
            
            if ("getFarmByTour".equals(action)) {
                List<FarmDTO> farmList = tourDao.getFarmByTour(tourID);
                if (farmList != null && !farmList.isEmpty()) {
                    for (FarmDTO farm : farmList) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("farmID", farm.getFarmID());
                        jsonObject.put("farmName", farm.getFarmName());
                        jsonArray.put(jsonObject);
                    }
                }
            
            } else if ("getKoiTypeByFarm".equals(action)) {
                List<KoiDTO> koiTypeList = dao.getKoiTypeByFarm(farmID);
                if (koiTypeList != null && !koiTypeList.isEmpty()) {
                    for (KoiDTO koiType : koiTypeList) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("koiTypeID", koiType.getKoiTypeID());
                        jsonObject.put("typeName", koiType.getKoiName());
                        jsonArray.put(jsonObject);
                    }
                }
            } else if ("getKoiByKoiType".equals(action)) {
                List<KoiDTO> koiList = dao.getKoiByKoiType(koiTypeID);
                if (koiList != null && !koiList.isEmpty()) {
                    for (KoiDTO koi : koiList) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("koiID", koi.getKoiID());
                        jsonObject.put("koiName", koi.getKoiName());
                        jsonArray.put(jsonObject);
                    }
                }
            }

            out.print(jsonArray.toString());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching koi data");
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
