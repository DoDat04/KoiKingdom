/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package koikd.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import koikd.booking.BookingDAO;
import koikd.booking.BookingDTO;
import koikd.cart.CartBean;
import koikd.cart.CartItem;
import koikd.customtour.CustomTourDTO;
import koikd.koi.KoiDAO;
import koikd.koi.KoiDTO;
import koikd.order.KoiOrderDTO;
import koikd.tour.TourDTO;
import koikd.tourbookingdetail.TourBookingDetailDAO;
import koikd.tourbookingdetail.TourBookingDetailDTO;
import koikd.utils.ConfigVNPayUtils;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "VNPayCallBackController", urlPatterns = {"/VNPayCallBackController"})
public class VNPayCallBackController extends HttpServlet {

    private static final String SUCCESS_VNPAY = "vnpay_return.jsp";
    private static final String FAIL_VNPAY = "vnpay_return_fail.jsp";
    private static final String GMAIL_USERNAME = "koikingdomsystem@gmail.com";
    private static final String GMAIL_APP_PASSWORD = "srzw mrnk kkhj ipcx";
    private static final ObjectMapper objectMapper = new ObjectMapper();

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
        String cookieName = null;
        String userId = null;
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
            System.out.println("Secure hash: " + vnp_SecureHash);
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
                String email = (String) session.getAttribute("email");
                Integer custID = (Integer) session.getAttribute("custID");
                String fullName = (String) session.getAttribute("custFullName");
                String custEmail = (String) session.getAttribute("custEmail");
                String custAddress = (String) session.getAttribute("custAddress");
                Timestamp today = new Timestamp(System.currentTimeMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String formattedDate = sdf.format(today);

                CartBean cart = (CartBean) session.getAttribute("cart");
                if (cart != null && signValue.equals(vnp_SecureHash)) {
                    // Nếu thanh toán thành công mới sendEmail, createOrder, addOrderDetail và RemoveCookie
                    if ("00".equals(transactionStatus)) {
                        url = SUCCESS_VNPAY;
                        message = "Giao dịch thành công!";
                        mail_message = "Đơn xác nhận đã được gửi qua email. Vui lòng kiểm tra email!";
                        notificationSvg = "<svg viewBox='0 0 52 52'><path class='checkmark' fill='none' stroke='green' stroke-width='4' d='M14 27 L22 35 L38 19' /></svg>";

                        // Iterate through the cart items to insert each tour
                        Map<Integer, CartItem> items = cart.getItems();
                        for (CartItem item : items.values()) {
                            TourDTO tour = item.getTour();
                            CustomTourDTO customTour = item.getCustomTour();
                            KoiDTO koi = item.getKoi();
                            int numberOfPeople = item.getNumberOfPeople();

                            BookingDTO booking = new BookingDTO();
                            TourBookingDetailDTO tourBookingDetail = new TourBookingDetailDTO();
                            KoiOrderDTO koiDto = new KoiOrderDTO();

                            booking.setCustomerID(custID);
                            booking.setCustName(fullName);
                            booking.setCustEmail(custEmail);
                            booking.setBookingDate(today);
                            booking.setShippingAddress(custAddress);
                            booking.setQuantity(numberOfPeople);

                            BookingDAO bookingDAO = new BookingDAO();
                            TourBookingDetailDAO tourBookingDetailDAO = new TourBookingDetailDAO();
                            KoiDAO koiDAO = new KoiDAO();

                            if (customTour != null) {
                                booking.setTourID(customTour.getRequestID());
                                booking.setStatus("Paid");

                                // Add the booking for the custom tour
                                int bookingID = bookingDAO.addBooking(booking, customTour);

                                tourBookingDetail.setCustomerID(custID);
                                tourBookingDetail.setTourID(customTour.getRequestID());
                                tourBookingDetail.setQuantity(numberOfPeople);
                                tourBookingDetail.setUnitPrice(customTour.getQuotationPrice());
                                tourBookingDetail.setTotalPrice(customTour.getQuotationPrice() * numberOfPeople);
                                tourBookingDetail.setStatus("Confirmed");
                                tourBookingDetail.setTourType("Custom");
                                tourBookingDetail.setFeedbackStatus(false);
                                tourBookingDetailDAO.addTourBookingDetail(bookingID, tourBookingDetail, customTour);

                            } else if (tour != null) {
                                booking.setTourID(tour.getTourID());
                                booking.setStatus("Paid");
                                booking.setTourType("Available");

                                // Add the booking for the standard tour
                                int bookingID = bookingDAO.addBooking(booking, customTour);

                                // Set properties for TourBookingDetailDTO
                                tourBookingDetail.setCustomerID(custID);
                                tourBookingDetail.setTourID(tour.getTourID());
                                tourBookingDetail.setQuantity(numberOfPeople);
                                tourBookingDetail.setUnitPrice(tour.getTourPrice());
                                tourBookingDetail.setTotalPrice(tour.getTourPrice() * numberOfPeople);
                                tourBookingDetail.setStatus("Confirmed");
                                tourBookingDetail.setFeedbackStatus(false);
                                // Add the tour booking detail
                                tourBookingDetailDAO.addTourBookingDetail(bookingID, tourBookingDetail, customTour);
                            } else if (koi != null) {
                                koiDto.setCustomerID(custID);
                                koiDto.setStatus(false);
                                koiDto.setType("Online");
                                koiDAO.insertKoiOrder(koiDto);
                            }
                        }

                        // Send confirmation email
                        sendBillForCustomer(email, custID, fullName, custAddress, custEmail);

                        if (session.getAttribute("LOGIN_USER") != null) {
                            userId = (String) session.getAttribute("userId");
                            cookieName = "USER_CART" + userId;
                        } else if (session.getAttribute("LOGIN_GMAIL") != null) {
                            userId = (String) session.getAttribute("emailPrefix");
                            cookieName = "GMAIL_CART" + userId;
                        }

                        RemoveCookie(request, response, cookieName, cart);
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
        } catch (Exception ex) {
            Logger.getLogger(VNPayCallBackController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.sendRedirect(url);
        }
    }

    private void sendBillForCustomer(String toEmail, int custID, String fullName, String custAddress, String custEmail) throws Exception {
        String host = "smtp.gmail.com";
        String port = "587";

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
        message.setSubject("Your Bill Details from Koi Kingdom");

        TourBookingDetailDAO dao = new TourBookingDetailDAO();
        TourBookingDetailDTO dto = dao.getTourBookingDetailByCustomerID(custID);
        if (dto != null) {
            String emailContent = "<div style='font-family: Arial, sans-serif; color: #333; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px;'>"
                    + "<h1 style='color: #4CAF50; text-align: center;'>Thank you for your purchase!</h1>"
                    + "<p style='font-size: 16px;'>Here are your bill details:</p>"
                    + "<table style='width: 100%; border-collapse: collapse;'>"
                    + "<tr>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd;'>Fullname:</td>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd;'>" + fullName + "</td>"
                    + "</tr>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd;'>Email:</td>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd;'>" + custEmail + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd;'>ID Tour:</td>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd;'>" + dto.getTourID() + "</td>"
                    + "</tr>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd;'>Tour Name:</td>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd;'>" + dto.getTourName() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd;'>Quantity:</td>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd;'>" + dto.getQuantity() + " person(s)" + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd;'>Unit Price:</td>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd;'>$" + dto.getUnitPrice() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd;'>Total Price:</td>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd; font-weight: bold;'>$" + dto.getTotalPrice() + "</td>"
                    + "</tr>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd;'>Address:</td>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd; font-weight: bold;'>" + custAddress + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd;'>Status:</td>"
                    + "<td style='padding: 8px; border-bottom: 1px solid #ddd; font-weight: bold;'>" + dto.getStatus() + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "<p style='margin-top: 20px;'>If you have any questions, please contact us at <a href='mailto:koikingdomsystem@gmail.com' style='color: #4CAF50; text-decoration: none;'>koikingdomsystem@gmail.com</a></p>"
                    + "<p style='font-size: 14px; color: #888; text-align: center;'>Thank you for choosing Koi Kingdom!</p>"
                    + "</div>";

            // Đặt nội dung của email
            message.setContent(emailContent, "text/html; charset=UTF-8");

            // Gửi email
            Transport.send(message);
        }
    }

    private String encodeCartToCookie(CartBean cart) throws IOException {
        byte[] cartBytes = objectMapper.writeValueAsBytes(cart);
        return Base64.getEncoder().encodeToString(cartBytes);
    }

    private void RemoveCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, CartBean cart) throws IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    // Xóa cookie
                    Cookie updatedCartCookie = new Cookie(cookieName, encodeCartToCookie(cart));
                    updatedCartCookie.setMaxAge(0); // Xóa cookie
                    updatedCartCookie.setPath("/"); 
                    response.addCookie(updatedCartCookie); // Ghi cookie đã cập nhật trở lại trình duyệt
                    break;
                }
            }
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
