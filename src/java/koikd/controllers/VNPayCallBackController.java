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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import koikd.cart.CartBean;
import koikd.utils.ConfigVNPayUtils;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "VNPayCallBackController", urlPatterns = {"/VNPayCallBackController"})
public class VNPayCallBackController extends HttpServlet {
    private static final String SUCCESS_VNPAY = "vnpay_return.jsp";
    private static final String FAIL_VNPAY = "vnpay_return_fail.jsp";
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
        String url = FAIL_VNPAY;
        try {
            // Begin process return from VNPAY
            Map<String, String> fields = new HashMap<>();
            for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements();) {
                String fieldName = URLEncoder.encode(params.nextElement(), StandardCharsets.US_ASCII.toString());
                String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
                if (fieldValue != null && !fieldValue.isEmpty()) {
                    fields.put(fieldName, fieldValue);
                }
            }

            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
            fields.remove("vnp_SecureHashType");
            fields.remove("vnp_SecureHash");
            String signValue = ConfigVNPayUtils.hashAllFields(fields);

            // Determine transaction status
            String transactionStatus = request.getParameter("vnp_TransactionStatus");
            String message = null;
            String notificationSvg = null;
            String mail_message = null;

            HttpSession session = request.getSession(false);
            if (session != null) {
                CartBean cart = (CartBean) session.getAttribute("cart");
                if (signValue.equals(vnp_SecureHash)) {
                    // Nếu thanh toán thành công mới sendEmail, createOrder, addOrderDetail và RemoveCookie
                    if ("00".equals(transactionStatus)) {
                        url = SUCCESS_VNPAY;
                        message = "Giao dịch thành công!";
                        mail_message = "Đơn xác nhận đã được gửi qua email. Vui lòng kiểm tra email!";
                        notificationSvg = "<svg viewBox='0 0 52 52'><path class='checkmark' fill='none' stroke='green' stroke-width='4' d='M14 27 L22 35 L38 19' /></svg>";    
                        session.removeAttribute("cart");
                        session.removeAttribute("cartItemCount");
                    } else {
                        url = FAIL_VNPAY;
                        message = "Giao dịch thất bại.";
                        notificationSvg = "<svg viewBox='0 0 52 52' class='xmark'><line x1='15' y1='15' x2='37' y2='37' stroke='red' stroke-width='4' /><line x1='37' y1='15' x2='15' y2='37' stroke='red' stroke-width='4' /></svg>";
                    }
                } else {
                    url = FAIL_VNPAY;
                    message = "Chữ ký không hợp lệ.";
                    notificationSvg = "<svg viewBox='0 0 52 52' class='xmark'><line x1='15' y1='15' x2='37' y2='37' stroke='red' stroke-width='4' /><line x1='37' y1='15' x2='15' y2='37' stroke='red' stroke-width='4' /></svg>";
                }
                session.setAttribute("mail_message", mail_message);
                session.setAttribute("VNPay_message", message);
                session.setAttribute("notificationSvg", notificationSvg);
            }
        } finally {
            response.sendRedirect(url);
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
