package koikd.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import koikd.order.KoiOrderDAO;
import koikd.order.KoiOrderDTO;
import koikd.order.KoiOrderDetailDTO;

/**
 *
 * @author ADMIN LAM
 */
@WebServlet(name = "CreateKoiOrderFormController", urlPatterns = {"/create-koi-order"})
public class CreateKoiOrderFormController extends HttpServlet {

    private static final String KOI_ORDER_FORM = "koiOrderForm.jsp";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String url = KOI_ORDER_FORM;
        HttpSession session = request.getSession();

        try {
            // Lấy thông tin đơn hàng từ form
            int custID = Integer.parseInt(request.getParameter("txtCustID"));
            String deliveryDateStr = request.getParameter("txtDelivery");
            String estimateDateStr = request.getParameter("txtEstimate");

            // Chuyển đổi chuỗi thành đối tượng Date
            Date deliveryDate = DATE_FORMAT.parse(deliveryDateStr);
            Date estimateDate = DATE_FORMAT.parse(estimateDateStr);

            // Tạo đối tượng KoiOrderDTO và lưu đơn hàng
            KoiOrderDTO koiOrderDTO = new KoiOrderDTO();
            koiOrderDTO.setCustomerID(custID);
            koiOrderDTO.setDeliveryDate(deliveryDate);
            koiOrderDTO.setStatus(false);
            koiOrderDTO.setEstimatedDelivery(estimateDate);

            KoiOrderDAO koiOrderDAO = new KoiOrderDAO();
            int koiOrderID = koiOrderDAO.createKoiOrder(koiOrderDTO);  // Lấy KoiOrderID sau khi tạo

            if (koiOrderID > 0) {  // Kiểm tra xem KoiOrder có được tạo thành công không
                // Lấy các giá trị từ form (mảng) cho farmID, quantity, unitPrice, totalPrice
                String[] farmIDs = request.getParameterValues("txtFarmIDs");
                String[] koiIDs = request.getParameterValues("txtKoiIDs");
                String[] quantities = request.getParameterValues("txtQuantity");
                String[] unitPrices = request.getParameterValues("txtUnitPrice");
                String[] totalPrices = request.getParameterValues("txtTotalPrice");

                // Duyệt qua các mảng và lưu chi tiết đơn hàng
                for (int i = 0; i < farmIDs.length; i++) {
                    int koiID = Integer.parseInt(koiIDs[i]);
                    int farmID = Integer.parseInt(farmIDs[i]);
                    int quantity = Integer.parseInt(quantities[i]);
                    double unitPrice = Double.parseDouble(unitPrices[i]);
                    double totalPrice = Double.parseDouble(totalPrices[i]);

                    // Tạo đối tượng KoiOrderDetailDTO và lưu chi tiết đơn hàng
                    KoiOrderDetailDTO koiOrderDetailDTO = new KoiOrderDetailDTO();
                    koiOrderDetailDTO.setKoiOrderID(koiOrderID);  // Set KoiOrderID vừa tạo
                    koiOrderDetailDTO.setKoiID(koiID);
                    koiOrderDetailDTO.setFarmID(farmID);
                    koiOrderDetailDTO.setQuantity(quantity);
                    koiOrderDetailDTO.setUnitPrice(unitPrice);
                    koiOrderDetailDTO.setTotalPrice(totalPrice);

                    koiOrderDAO.createKoiOrderDetail(koiOrderDetailDTO);
                }

                // Thiết lập thông báo thành công
                session.setAttribute("message", "Koi order created successfully!");

            } else {
                // Nếu không tạo được đơn hàng
                session.setAttribute("errorMessage", "Failed to create Koi order.");
            }

        } catch (ParseException e) {
            e.printStackTrace();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateKoiOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateKoiOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateKoiOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateKoiOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
