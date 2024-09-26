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
import koikd.customer.CustomerDAO;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "SendOTPController", urlPatterns = {"/forgot-password"})
public class SendOTPController extends HttpServlet {
    private static final String GMAIL_USERNAME = "koikingdomsystem@gmail.com"; 
    private static final String GMAIL_APP_PASSWORD = "srzw mrnk kkhj ipcx"; 
    private static final String FORGOT_PASSWORD_PAGE = "forgotPassword.jsp";
    private static final String OTP_VERIFICATION_PAGE = "otpVerification.jsp";

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
        CustomerDAO dao = new CustomerDAO();
        String url = FORGOT_PASSWORD_PAGE;
        try {
            if (email == null || email.trim().isEmpty()) {
            } else {
                if (dao.checkEmailExist(email)) {
                    String otp = dao.generateOTP();
                    sendEmail(email, otp);
                    request.getSession().setAttribute("OTP", otp);
                    request.getSession().setAttribute("email", email);
                    request.getSession().setAttribute("otpGeneratedTime", System.currentTimeMillis());
                    url = OTP_VERIFICATION_PAGE + "?resetTimer=true";
                } else {
                    // Handle case where email does not exist
                    request.setAttribute("errorMessage", "Email does not exist!");
                }
            }
        } catch (Exception e) {
            log("Error at SendOTPController: " + e.toString());
        } finally {
            if (url.equals(FORGOT_PASSWORD_PAGE)) {
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                response.sendRedirect(url);
            }
        }
    }

    private void sendEmail(String toEmail, String otp) throws Exception {
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
        message.setSubject("Your OTP Code For Password Reset");
        message.setText("Your OTP code is: " + otp);

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
