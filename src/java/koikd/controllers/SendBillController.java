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
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ADMIN LAM
 */
@WebServlet(name = "SendBillController", urlPatterns = {"/send-bookingTour-bill"})
public class SendBillController extends HttpServlet {

    private static final String GMAIL_USERNAME = "koikingdomsystem@gmail.com";
    private static final String GMAIL_APP_PASSWORD = "srzw mrnk kkhj ipcx";
    private static final String SUCCESS_PAGE = "sendBillSuccess.jsp";
    private static final String ERROR_PAGE = "sendBillError.jsp";

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
        String email = request.getParameter("email");
        String billDetails = request.getParameter("billDetails");
        String url = ERROR_PAGE;

        try {
            if (email == null || email.trim().isEmpty() || billDetails == null || billDetails.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Email or Bill Details cannot be empty!");
            } else {
                // Sau khi khách hàng thanh toán booking tour thành công, sẽ dẫn luồng tới:
                sendBillForCustomer(email, billDetails); //1. Gửi mail qua khách hàng để xác nhận thông tin về tour.
                url = SUCCESS_PAGE; //2. Sau khi gửi mail thành công tới gmail của khách hàng thì sẽ dẫn từ trang thanh toán đến trang thông báo thanh toán và booking tour thành công.
            }
        } catch (Exception e) {
            log("Error at SendBillController: " + e.toString());
            request.setAttribute("errorMessage", "Failed to send bill: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private void sendBillForCustomer(String toEmail, String billDetails) throws Exception {
        String host = "smtp.gmail.com";
        String port = "587";

        // Thiết lập các thuộc tính SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Xác thực thông tin tài khoản email
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(GMAIL_USERNAME, GMAIL_APP_PASSWORD);
            }
        });

        // Tạo nội dung email
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(GMAIL_USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Your Bill Details from Koi Kingdom");

        // Nội dung của hóa đơn, có thể là văn bản hoặc HTML
        String emailContent = "<h1>Thank you for your purchase!</h1>"
                + "<p>Here are your bill details:</p>"
                + "<pre>" + billDetails + "</pre>"
                + "<p>If you have any questions, please contact us at support@koikingdom.com</p>";

        // Đặt nội dung của email
        message.setContent(emailContent, "text/html; charset=UTF-8");

        // Gửi email
        Transport.send(message);
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
