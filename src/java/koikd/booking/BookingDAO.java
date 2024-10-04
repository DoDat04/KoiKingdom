/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.booking;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import koikd.utils.DBUtils;


/**
 *
 * @author Do Dat
 */
public class BookingDAO implements Serializable {
    public boolean addBooking(BookingDTO booking) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO BOOKING (CustomerID, TourID, Name, Email, BookingDate, ShippingAddress, Quantity, Status, TourType) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, booking.getCustomerID());
                stm.setInt(2, booking.getTourID());
                stm.setString(3, booking.getCustName());
                stm.setString(4, booking.getCustEmail());
                stm.setTimestamp(5, new java.sql.Timestamp(booking.getBookingDate().getTime()));
                stm.setString(6, booking.getShippingAddress());
                stm.setInt(7, booking.getQuantity());
                stm.setString(8, booking.getStatus());
                stm.setString(9, booking.getTourType());

                int affectedRows = stm.executeUpdate();
                if (affectedRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
