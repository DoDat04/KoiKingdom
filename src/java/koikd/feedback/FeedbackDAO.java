/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import koikd.customer.CustomerDTO;
import koikd.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class FeedbackDAO implements Serializable {

    /**
     * Create feedback
     *
     * @param customerID
     * @param tourID
     * @param rating
     * @param feedbackText
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
   public FeedbackDTO createFeedbackForCustomer(int customerID, int tourID, int rating, String feedbackText) 
        throws ClassNotFoundException, SQLException {
    FeedbackDTO feedbackDTO = null;
    PreparedStatement pst = null;
    PreparedStatement updateStatusPst = null;
    Connection conn = null;
    
    try {
        conn = DBUtils.getConnection();
        if (conn != null) {
            // Bắt đầu giao dịch
            conn.setAutoCommit(false);
            
            // 1. Chèn Feedback vào bảng FEEDBACK
            String sql = "INSERT INTO [dbo].[FEEDBACK] (CustomerID, TourID, Rating, FeedbackText, CreatedAt, Status) "
                       + "VALUES (?, ?, ?, ?, GETDATE(), 1);";
            pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, customerID);
            pst.setInt(2, tourID);
            pst.setInt(3, rating);
            pst.setString(4, feedbackText);
            
            // Thực thi lệnh chèn
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                // Lấy ID phản hồi đã tạo
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        int feedbackID = rs.getInt(1);
                        Date createdAt = new Date(); // Có thể thay đổi tùy theo yêu cầu, nếu bạn muốn lấy ngày hiện tại
                        
                        // Tạo đối tượng FeedbackDTO
                        feedbackDTO = new FeedbackDTO(feedbackID, customerID, rating, feedbackText, (Timestamp) createdAt, tourID);
                    }
                }
                
                // 2. Cập nhật FeedbackStatus trong bảng TOURBOOKINGDETAIL
                String updateStatusSQL = "UPDATE [dbo].[TOURBOOKINGDETAIL] SET FeedbackStatus = 1 WHERE CustomerID = ? AND TourID = ?;";
                updateStatusPst = conn.prepareStatement(updateStatusSQL);
                updateStatusPst.setInt(1, customerID);
                updateStatusPst.setInt(2, tourID);
                updateStatusPst.executeUpdate();
            }

            // Cam kết giao dịch
            conn.commit();
        }
    } catch (SQLException e) {
        if (conn != null) {
            try {
                conn.rollback(); // Hoàn tác giao dịch nếu có lỗi
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        throw new SQLException("Error creating feedback for customer " + customerID, e);
    } finally {
        // Đảm bảo đóng tài nguyên để tránh rò rỉ bộ nhớ
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (updateStatusPst != null) {
            try {
                updateStatusPst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return feedbackDTO;
}
   
    /**
     *
     * @param customerID
     * @param tourID
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<FeedbackDTO> getListFeedback(int tourID) throws ClassNotFoundException, SQLException {
        ArrayList<FeedbackDTO> feedbackList = new ArrayList<>();
        PreparedStatement pst = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [FeedbackID], [CustomerID], [TourID], [Rating], [FeedbackText], [CreatedAt]\n"
                        + "FROM [dbo].[FEEDBACK] \n"
                        + "WHERE [TourID] = ? ";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, tourID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int feedbackID = rs.getInt("FeedbackID");
                    int customerID = rs.getInt("CustomerID");
                    int rating = rs.getInt("Rating");
                    String feedbackText = rs.getString("FeedbackText");
                    Timestamp createdAt = rs.getTimestamp("CreatedAt");
                    // Fixed constructor call
                    FeedbackDTO feedback = new FeedbackDTO(feedbackID, customerID, rating, feedbackText, createdAt, tourID);
                    feedbackList.add(feedback);
                }
            }
        } catch (SQLException e) {
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return feedbackList;
    }    

    public CustomerDTO getCustomerByCustomerID(int customerId) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        CustomerDTO result = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT CustomerID, Email, LastName, FirstName, Address, AccountType, Status "
                        + "FROM CUSTOMER "
                        + "WHERE CustomerID = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, customerId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int customerID = rs.getInt("CustomerID");
                    String email = rs.getString("Email");
                    String lastName = rs.getString("LastName");
                    String firstName = rs.getString("FirstName");
                    String address = rs.getString("Address");
                    String accountType = rs.getString("AccountType");
                    boolean status = rs.getBoolean("Status");
                    result = new CustomerDTO(customerID, email, lastName, lastName, firstName, address, accountType, status);
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
     * Check Feedback For Customer
     * @param feedbackID
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public FeedbackDTO checkFeedbackForCustomer(int feedbackID) throws ClassNotFoundException, SQLException {
        FeedbackDTO feedbackDTO = null;
        PreparedStatement pst = null;
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT FeedbackID, CustomerID, TourID, Rating, FeedbackText, CreatedAt "
                        + "FROM [dbo].[FEEDBACK] WHERE FeedbackID = ? AND Status = 1"; 
                pst = conn.prepareStatement(sql);
                pst.setInt(1, feedbackID);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        // Lấy các trường từ kết quả truy vấn
                        int customerID = rs.getInt("CustomerID");
                        int tourID = rs.getInt("TourID");
                        int rating = rs.getInt("Rating");
                        String feedbackText = rs.getString("FeedbackText");
                        Timestamp createdAt = rs.getTimestamp("CreatedAt");

                        feedbackDTO = new FeedbackDTO(feedbackID, customerID, rating, feedbackText, createdAt, tourID);
                    }
                }
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
            throw new SQLException("Error checking feedback for feedbackID " + feedbackID, e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return feedbackDTO;  
    }

}
