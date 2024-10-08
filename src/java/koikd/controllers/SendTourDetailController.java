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
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import koikd.booking.BookingDAO;
import koikd.booking.BookingDTO;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "SendTourDetailController", urlPatterns = {"/send-tour-detail"})
public class SendTourDetailController extends HttpServlet {

    private static final String GMAIL_USERNAME = "koikingdomsystem@gmail.com";
    private static final String GMAIL_APP_PASSWORD = "srzw mrnk kkhj ipcx";
    private static final String BOOKING_LIST_PAGE = "bookingList.jsp";

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
        String url = BOOKING_LIST_PAGE;
        int bookingID = Integer.parseInt(request.getParameter("txtBookingID"));
        String customerEmail = request.getParameter("txtCustomerEmail");
        String tourDetails = request.getParameter("txtTourDetails");
        try {
            // Gọi phương thức sendEmail với customerEmail, bookingID và tourDetails
            sendEmail(customerEmail, bookingID, tourDetails);
            // Thông báo gửi thành công
            request.setAttribute("SEND_SUCCESS", "Tour details have been successfully sent to " + customerEmail);
            BookingDAO dao = new BookingDAO();
            List<BookingDTO> listBooking = dao.getAllBooking();
            request.setAttribute("BOOKING_LIST", listBooking);

        } catch (Exception e) {
            log("Error at SendTourDetailController: " + e.getMessage());
            request.setAttribute("SEND_ERROR", "Failed to send tour details to " + customerEmail);
        } finally {
            // Điều hướng về trang booking list sau khi xử lý xong
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private void sendEmail(String toEmail, int bookingID, String tourDetails) throws Exception {
        String host = "smtp.gmail.com";
        String port = "587";

        // Thiết lập thuộc tính cho kết nối SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(GMAIL_USERNAME, GMAIL_APP_PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(GMAIL_USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

        message.setSubject("Tour Details for Booking ID: " + bookingID);

        String emailContent = "Dear Customer,<br><br>"
                + "Here are the tour details for your booking with ID: <strong>" + bookingID + "</strong><br><br>"
                + tourDetails + "<br><br>"
                + "Thank you for choosing us!<br><br>"
                + "Best regards,<br>Koi Kingdom";

        // Đảm bảo nội dung email sử dụng HTML và mã hóa UTF-8
        message.setContent(emailContent, "text/html; charset=UTF-8");
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
