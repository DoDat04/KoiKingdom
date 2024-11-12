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
import koikd.customer.CustomerDTO;
import koikd.customtour.CustomTourDTO;

/**
 *
 * @author Do Dat
 */
public class TourBookingDetailDAO implements Serializable {

    public int addTourBookingDetail(int bookingID, TourBookingDetailDTO tourBookingDetail, CustomTourDTO customTour) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int tourBookingDetailID = -1;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO TOURBOOKINGDETAIL (BookingID, CustomerID, TourID, Quantity, UnitPrice, TotalPrice, Status, TourType, FeedbackStatus) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
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
                    rs = stm.getGeneratedKeys();

                    if (rs.next()) {
                        tourBookingDetailID = rs.getInt(1);
                    }
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
        return tourBookingDetailID;
    }

    /**
     *
     * @param custID
     * @return tour booking bill.
     * @throws SQLException
     */
    public TourBookingDetailDTO getTourBookingDetailByCustomerID(int custID, int tourBookingDetailID) throws SQLException {
        TourBookingDetailDTO dto = null;
        String sql = "SELECT a.TourBookingDetail, a.TourID, a.CustomerID, a.Quantity, a.UnitPrice, a.TotalPrice, a.Status, b.TourName "
                + "FROM TOURBOOKINGDETAIL a "
                + "INNER JOIN TOUR b ON a.TourID = b.TourID "
                + "WHERE a.CustomerID = ? AND a.TourBookingDetail = ?";

        try (Connection conn = DBUtils.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, custID);
            pst.setInt(2, tourBookingDetailID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Record found for CustomerID: " + custID);
                    int tourBookingDetail = rs.getInt("TourBookingDetail");
                    int customerID = rs.getInt("CustomerID");
                    int tourID = rs.getInt("TourID");
                    String tourName = rs.getString("TourName");
                    int quantity = rs.getInt("Quantity");
                    double unitPrice = rs.getDouble("UnitPrice");
                    double totalPrice = rs.getDouble("TotalPrice");
                    String status = rs.getString("Status");

                    System.out.println("CustomerID Retrieved: " + customerID);  // Debug statement
                    dto = new TourBookingDetailDTO(tourBookingDetail, customerID, tourID, tourName, quantity, unitPrice, totalPrice, status);
                } else {
                    System.out.println("No record found for CustomerID: " + custID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

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
                        + "WHERE [CustomerID] = ? AND ([Status] = 'Completed' OR [Status] = 'Confirmed' OR [Status] = 'Canceled')"
                        + "ORDER BY TourBookingDetail DESC";
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
     *
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
     *
     * @param consultingID
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<TourBookingDetailDTO> getAllTourBookingDetail(int consultingID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TourBookingDetailDTO> listTourBookingDetail = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT t.TourBookingDetail, t.CustomerID, b.Name, t.TourID, t.Quantity, t.UnitPrice, t.TotalPrice, t.Status, t.TourType "
                        + "FROM TOURBOOKINGDETAIL t "
                        + "INNER JOIN BOOKING b ON t.CustomerID = b.CustomerID "
                        + "INNER JOIN TOUR tour ON t.TourID = tour.TourID "
                        + "WHERE tour.Consulting = ? AND t.Status IN ('Confirmed', 'Completed')";

                stm = con.prepareStatement(sql);
                stm.setInt(1, consultingID); // Gán giá trị cho consultingID
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
     *
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
     *
     * @param ID
     * @param reason
     * @return
     * @throws SQLException
     */
    public TourBookingDetailDTO cancelTourBookingDetailByID(int ID, String reason) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        TourBookingDetailDTO dto = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE TOURBOOKINGDETAIL\n"
                        + "SET Status = 'Canceled', CancelAt = GETDATE(), ReasonCancel = ?\n"
                        + " WHERE TourBookingDetail = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, reason);  // Set the reason first
                pst.setInt(2, ID);
                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    Timestamp cancelAt = new Timestamp(System.currentTimeMillis());
                    dto = new TourBookingDetailDTO(rowsUpdated, sql, cancelAt, reason);
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
    
    
    public ArrayList<TourBookingDetailDTO> getTourCancel() {
        ArrayList<TourBookingDetailDTO> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            connection = DBUtils.getConnection();
            if (connection != null) {
                String sql = "SELECT "
                        + " td.TourBookingDetail, "
                        + " c.lastName, "
                        + " c.firstName, "
                        + " c.phoneNumber, "
                        + " t.tourName, "
                        + " t.startDate, "
                        + " t.endDate, "
                        + " td.totalPrice, "
                        + " td.cancelAt, "
                        + " td.reasonCancel "
                        + "FROM "
                        + " TOURBOOKINGDETAIL td "
                        + " INNER JOIN TOUR t ON td.tourID = t.tourID "
                        + " INNER JOIN CUSTOMER c ON td.customerID = c.customerID "
                        + "WHERE td.status = 'Canceled' " 
                        + "ORDER BY td.TourBookingDetail";

                stm = connection.prepareStatement(sql);
                rs = stm.executeQuery();
                
                while (rs.next()) {
                    int tourBookingDetailID = rs.getInt("TourBookingDetail");
                    String lastName = rs.getString("lastName");
                    String firstName = rs.getString("firstName");
                    String phoneNumber = rs.getString("phoneNumber");
                    String tourName = rs.getString("tourName");
                    Timestamp startDate = rs.getTimestamp("startDate");
                    Timestamp endDate = rs.getTimestamp("endDate");
                    double totalPrice = rs.getDouble("totalPrice");
                    Timestamp cancelAt = rs.getTimestamp("cancelAt");
                    String reasonCancel = rs.getString("reasonCancel");

                    // Tạo đối tượng DTO cho thông tin hủy tour
                    CustomerDTO customerDTO = new CustomerDTO(lastName, firstName, phoneNumber);
                    TourDTO tourDTO = new TourDTO(tourName, startDate, endDate);
                    TourBookingDetailDTO dto = new TourBookingDetailDTO(tourBookingDetailID,totalPrice, cancelAt, customerDTO, tourDTO, reasonCancel);
                    result.add(dto); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng kết nối và các đối tượng
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
