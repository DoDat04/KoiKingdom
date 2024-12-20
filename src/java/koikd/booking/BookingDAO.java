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

    public List<BookingDTO> getAllBooking(String searchBooking) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<BookingDTO> listBooking = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT BookingID, CustomerID, TourID, Name, Email, BookingDate, ShippingAddress, Quantity, Status, TourType "
                        + "FROM BOOKING "
                        + "WHERE TourType = 'Custom' ";
                if (searchBooking != null && !searchBooking.isEmpty()) {
                    sql += "AND Name LIKE ? ";
                }
                sql += "ORDER BY BookingDate DESC ";
                stm = con.prepareStatement(sql);
                if (searchBooking != null && !searchBooking.isEmpty()) {
                    stm.setString(1, "%" + searchBooking + "%");
                }

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
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        String searchBooking = "Phuong Anh Do";
//        BookingDAO services = new BookingDAO();
//        ArrayList<BookingDTO> searchListBooking = (ArrayList<BookingDTO>) services.getAllBooking(searchBooking);
//        if(searchListBooking!=null){
//            System.out.println(searchListBooking);
//        }
//    }

    public int countBooking(String startDate, String endDate) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int bookingCount = 0;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (startDate != null && endDate != null) {
                    String sql = "SELECT COUNT([BookingID]) FROM [dbo].[BOOKING] "
                            + "WHERE CAST(BookingDate AS DATE) BETWEEN ? AND ? ";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, startDate);
                    pst.setString(2, endDate);
                } else {
                    String sql = "SELECT COUNT([BookingID]) FROM [dbo].[BOOKING]";
                    pst = conn.prepareStatement(sql);
                }

                rs = pst.executeQuery();

                if (rs.next()) {
                    bookingCount = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bookingCount;
    }

    public int countCustomer(String startDate, String endDate) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int customerCount = 0;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql;
                if (startDate != null && endDate != null) {
                    sql = "SELECT COUNT(DISTINCT [CustomerID]) FROM [dbo].[BOOKING]"
                            + "WHERE CAST(BookingDate AS DATE) BETWEEN ? AND ? ";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, startDate);
                    pst.setString(2, endDate);
                } else {
                    sql = "SELECT COUNT(DISTINCT [CustomerID]) FROM [dbo].[BOOKING]";
                    pst = conn.prepareStatement(sql);
                }
                rs = pst.executeQuery();

                if (rs.next()) {
                    customerCount = rs.getInt(1); // ta phải lấy ở cột đầu tiên của result set mặc dù nó chỉ có đúng 1 cột
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return customerCount;
    }

    public int countAvailableTour(String startDate, String endDate) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int avaiTour = 0;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (startDate != null && endDate != null) {
                    String sql = "SELECT COUNT([BookingID]) FROM [dbo].[BOOKING] "
                            + "WHERE TourType = 'Available' AND CAST(BookingDate AS DATE) BETWEEN ? AND ? ";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, startDate);
                    pst.setString(2, endDate);
                } else {
                    String sql = "SELECT COUNT([BookingID]) FROM [dbo].[BOOKING] "
                            + "WHERE TourType = 'Available' ";
                    pst = conn.prepareStatement(sql);
                }

                rs = pst.executeQuery();

                if (rs.next()) {
                    avaiTour = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return avaiTour;
    }

    public int countCustomTour(String startDate, String endDate) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int cusTour = 0;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (startDate != null && endDate != null) {
                    String sql = "SELECT COUNT([BookingID]) FROM [dbo].[BOOKING] "
                            + "WHERE TourType = 'Custom' AND CAST(BookingDate AS DATE) BETWEEN ? AND ? ";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, startDate);
                    pst.setString(2, endDate);
                } else {
                    String sql = "SELECT COUNT([BookingID]) FROM [dbo].[BOOKING] "
                            + "WHERE TourType = 'Custom' ";
                    pst = conn.prepareStatement(sql);
                }

                rs = pst.executeQuery();

                if (rs.next()) {
                    cusTour = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cusTour;
    }
    
      public BookingDTO getBooking(int cusID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        BookingDTO booking = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                // Query to select a booking based only on CustomerID
                String sql = "SELECT BookingID, CustomerID, TourID, Name, Email, BookingDate, ShippingAddress, Quantity, Status, TourType "
                        + "FROM BOOKING "
                        + "WHERE CustomerID = ? " ;  // Get the latest booking (if any)

                stm = con.prepareStatement(sql);
                stm.setInt(1, cusID);  // Set the CustomerID parameter

                rs = stm.executeQuery();
                if (rs.next()) {
                    int bookingID = rs.getInt("BookingID");
                    int customerID = rs.getInt("CustomerID");
                    int tourID = rs.getInt("TourID");
                    String custName = rs.getString("Name");
                    String custEmail = rs.getString("Email");
                    Timestamp bookingDate = rs.getTimestamp("BookingDate");
                    String shipAddress = rs.getString("ShippingAddress");
                    int quantity = rs.getInt("Quantity");
                    String status = rs.getString("Status");
                    String tourType = rs.getString("TourType");

                    // Create a BookingDTO object with the retrieved data
                    booking = new BookingDTO(bookingID, customerID, tourID, custName, custEmail, bookingDate, shipAddress, quantity, status, tourType);
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
        return booking; // Return the single BookingDTO object or null if not found
    }
//    public static void main(String[] args) {
//        BookingDAO mainApp = new BookingDAO();
//        
//        int bookingCount = mainApp.countBooking();
//        
//        System.out.println("Number: " + bookingCount);
//    }
}
