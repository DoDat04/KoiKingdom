/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.tourbookingdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import koikd.tour.TourDTO;
import koikd.utils.DBUtils;
import java.sql.Timestamp;
import java.util.List;
import koikd.customtour.CustomTourDTO;

/**
 *
 * @author Do Dat
 */
public class TourBookingDetailDAO implements Serializable {

    public boolean addTourBookingDetail(int bookingID, TourBookingDetailDTO tourBookingDetail, CustomTourDTO customTour) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO TOURBOOKINGDETAIL (BookingID, CustomerID, TourID, Quantity, UnitPrice, TotalPrice, Status, TourType, FeedbackStatus) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, bookingID);  // Sử dụng bookingID được truyền vào
                stm.setInt(2, tourBookingDetail.getCustomerID());

                if (customTour != null) {
                    stm.setInt(3, customTour.getRequestID());
                } else {
                    stm.setInt(3, tourBookingDetail.getTourID());
                }

                stm.setInt(4, tourBookingDetail.getQuantity());
                stm.setDouble(5, customTour != null ? customTour.getQuotationPrice() : tourBookingDetail.getUnitPrice());
                stm.setDouble(6, (customTour != null ? customTour.getQuotationPrice() : tourBookingDetail.getUnitPrice()) * tourBookingDetail.getQuantity());
                stm.setString(7, tourBookingDetail.getStatus());
                stm.setString(8, customTour != null ? "Custom" : "Available");  // Set TourType
                stm.setBoolean(9, tourBookingDetail.isFeedbackStatus());
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

    /**
     *
     * @param custID
     * @return tour booking bill.
     * @throws SQLException
     */
    public TourBookingDetailDTO getTourBookingDetailByCustomerID(int custID) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        TourBookingDetailDTO dto = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT a.TourBookingDetail, a.TourID, a.CustomerID, a.Quantity, a.UnitPrice, a.TotalPrice, a.Status, b.TourName\n"
                        + "FROM TOURBOOKINGDETAIL a\n"
                        + "INNER JOIN TOUR b ON a.TourID = b.TourID\n"
                        + "WHERE a.CustomerID = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, custID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int tourBookingDetail = rs.getInt("tourBookingDetail");
                    int customerID = rs.getInt("customerID");
                    int tourID = rs.getInt("tourID");
                    String tourName = rs.getString("tourName");
                    int quantity = rs.getInt("quantity");
                    double unitPrice = rs.getDouble("unitPrice");
                    double totalPrice = rs.getDouble("totalPrice");
                    String status = rs.getString("status");
                    dto = new TourBookingDetailDTO(tourBookingDetail, customerID, tourID, tourName, quantity, unitPrice, totalPrice, status);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return dto;
    }

//    public static void main(String[] args) throws SQLException {
//        int custID = 12;
//        TourBookingDetailDAO dao = new TourBookingDetailDAO();
//        TourBookingDetailDTO dto = dao.getTourBookingDetailByCustomerID(custID);
//        if (dto != null) {
//            System.out.println(dto);
//        }
//    }
    public ArrayList<TourBookingDetailDTO> getTourBookingDetailListByCustomerID(int custID) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        TourBookingDetailDTO dto = null;
        ArrayList<TourBookingDetailDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [TourBookingDetail], [CustomerID], [TourID], [Quantity], [UnitPrice], [TotalPrice], [Status], [TourType] ,[FeedbackStatus], [bookingID] \n"
                        + "FROM [dbo].[TOURBOOKINGDETAIL]\n"
                        + "WHERE [CustomerID] = ? AND ([Status] = 'Completed' OR [Status] = 'Confirmed')";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, custID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int tourBookingDetail = rs.getInt("tourBookingDetail");
                    int customerID = rs.getInt("customerID");
                    int tourID = rs.getInt("tourID");
                    int quantity = rs.getInt("quantity");
                    double unitPrice = rs.getDouble("unitPrice");
                    double totalPrice = rs.getDouble("totalPrice");
                    String status = rs.getString("status");
                    String tourType = rs.getString("tourType");
                    boolean feedbackStatus = rs.getBoolean("FeedbackStatus");
                    int bookingID = rs.getInt("bookingID");
                    dto = new TourBookingDetailDTO(tourBookingDetail, customerID, tourID, quantity, unitPrice, totalPrice, status, tourType, bookingID, feedbackStatus);
                    list.add(dto);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    /**
     * Get Tour By ID
     * @param tourID
     * @return
     * @throws SQLException 
     */
    public TourDTO getTourByID(int tourID) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        TourDTO result = null;
        try {
            conn = DBUtils.getConnection(); // Ensure connection is retrieved properly.

            if (conn != null) {
                String sql = "SELECT [TourID], [TourName], [Duration], [Description], [TourPrice], [StartDate], [EndDate], [Image], [Status], [DepartureLocation]\n"
                        + "FROM [dbo].[TOUR]\n"
                        + "WHERE [TourID] = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, tourID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String tourName = rs.getString("TourName");
                    String tourDuration = rs.getString("Duration");
                    String tourDescription = rs.getString("Description");
                    double tourPrice = rs.getDouble("TourPrice");
                    Timestamp startDate = rs.getTimestamp("StartDate");
                    Timestamp endDate = rs.getTimestamp("EndDate");
                    String tourImage = rs.getString("Image");
                    String departureLocation = rs.getString("DepartureLocation");

                    // Create the TourDTO object with retrieved data
                    result = new TourDTO(tourID, tourName, tourDuration, tourDescription, tourPrice, startDate, endDate, tourImage, true, departureLocation);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    /**
     * Get All Tour Booking Detail
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public List<TourBookingDetailDTO> getAllTourBookingDetail() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TourBookingDetailDTO> listTourBookingDetail = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT t.TourBookingDetail, t.CustomerID, b.Name, t.TourID, t.Quantity, t.UnitPrice, t.TotalPrice, t.Status, t.TourType "
                        + "FROM TOURBOOKINGDETAIL t INNER JOIN BOOKING b ON "
                        + "t.CustomerID = b.CustomerID";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int tourBookingDetailID = rs.getInt("TourBookingDetail");
                    int custID = rs.getInt("CustomerID");
                    String custName = rs.getString("Name");
                    int tourID = rs.getInt("TourID");
                    int quantity = rs.getInt("Quantity");
                    double unitPrice = rs.getDouble("UnitPrice");
                    double totalPrice = rs.getDouble("TotalPrice");
                    String status = rs.getString("Status");
                    String tourType = rs.getString("TourType");

                    TourBookingDetailDTO listDTO = new TourBookingDetailDTO(tourBookingDetailID, custID, custName, tourID, tourType, quantity, unitPrice, totalPrice, status, tourType);
                    listTourBookingDetail.add(listDTO);
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
        return listTourBookingDetail;
    }

    /**
     * Update Tour Booking Detail Status
     * @param tourBookingDetailID
     * @param newStatus
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void updateTourBookingDetailStatus(int tourBookingDetailID, String newStatus) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE TOURBOOKINGDETAIL "
                        + "SET Status = ? "
                        + "WHERE TourBookingDetail = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, newStatus);
                stm.setInt(2, tourBookingDetailID);
                stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    /**
     * Cancel Tour Booking DetailByID
     * @param ID
     * @return
     * @throws SQLException 
     */
    public TourBookingDetailDTO cancelTourBookingDetailByID(int ID) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        TourBookingDetailDTO dto = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE TOURBOOKINGDETAIL\n"
                        + "SET Status = 'Canceled', CancelAt = GETDATE()\n"
                        + " WHERE TourBookingDetail = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, ID);
                int rowsUpdated = pst.executeUpdate(); 

                if (rowsUpdated > 0) {
                    Timestamp cancelAt = new Timestamp(System.currentTimeMillis());
                    dto = new TourBookingDetailDTO(rowsUpdated, sql, cancelAt);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return dto; 
    }
}
