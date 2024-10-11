/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.booking;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import koikd.customtour.CustomTourDTO;
import koikd.utils.DBUtils;

/**
 *
 * @author Do Dat
 */
public class BookingDAO implements Serializable {

    public int addBooking(BookingDTO booking, CustomTourDTO customTour) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet generatedKeys = null;
        int bookingID = -1;  // Giá trị mặc định cho bookingID nếu không thành công

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO BOOKING (CustomerID, TourID, Name, Email, BookingDate, ShippingAddress, Quantity, Status, TourType) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  // Thêm RETURN_GENERATED_KEYS để lấy khóa tự tăng
                stm.setInt(1, booking.getCustomerID());

                if (customTour != null) {
                    stm.setInt(2, customTour.getRequestID());
                } else {
                    stm.setInt(2, booking.getTourID());
                }

                stm.setString(3, booking.getCustName());
                stm.setString(4, booking.getCustEmail());
                stm.setTimestamp(5, new java.sql.Timestamp(booking.getBookingDate().getTime()));
                stm.setString(6, booking.getShippingAddress());
                stm.setInt(7, booking.getQuantity());
                stm.setString(8, booking.getStatus());
                stm.setString(9, customTour != null ? "Custom" : "Available");

                int affectedRows = stm.executeUpdate();
                if (affectedRows > 0) {
                    generatedKeys = stm.getGeneratedKeys();  // Lấy các khóa vừa được sinh
                    if (generatedKeys.next()) {
                        bookingID = generatedKeys.getInt(1);  // Lấy giá trị bookingID
                    }
                }
            }
        } finally {
            if (generatedKeys != null) {
                generatedKeys.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return bookingID;
    }

    public List<BookingDTO> getAllBooking() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<BookingDTO> listBooking = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT BookingID, CustomerID, TourID, Name, Email, BookingDate, ShippingAddress, Quantity, Status, TourType "
                        + "FROM BOOKING";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int bookingID = rs.getInt("BookingID");
                    int custID = rs.getInt("CustomerID");
                    int tourID = rs.getInt("TourID");
                    String custName = rs.getString("Name");
                    String custEmail = rs.getString("Email");
                    Timestamp bookingDate = rs.getTimestamp("BookingDate");
                    String shipAddress = rs.getString("ShippingAddress");
                    int quantity = rs.getInt("Quantity");
                    String status = rs.getString("Status");
                    String tourType = rs.getString("TourType");

                    BookingDTO booking = new BookingDTO(bookingID, custID, tourID, custName, custEmail, bookingDate, shipAddress, quantity, status, tourType);
                    listBooking.add(booking);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listBooking;
    }
}
