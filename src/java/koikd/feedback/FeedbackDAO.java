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
    public FeedbackDTO createFeedbackForCustomer(int customerID, int tourID, int rating, String feedbackText) throws ClassNotFoundException, SQLException {
        FeedbackDTO feedbackDTO = null;
        PreparedStatement pst = null;
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO [dbo].[FEEDBACK] (CustomerID, TourID, Rating, FeedbackText, CreatedAt) "
                        + "VALUES (?, ?, ?, ?, GETDATE());";
                pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                pst.setInt(1, customerID);
                pst.setInt(2, tourID);
                pst.setInt(3, rating);
                pst.setString(4, feedbackText);

                // Execute the insert command
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    try (ResultSet rs = pst.getGeneratedKeys()) {
                        if (rs.next()) {
                            int feedbackID = rs.getInt(1);
                            Date createdAt = new Date();

                            feedbackDTO = new FeedbackDTO(feedbackID, customerID, rating, feedbackText, createdAt, tourID);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            // Handle the exception properly (logging, throwing, etc.)
            throw new SQLException("Error creating feedback for customer " + customerID, e);
        } finally {
            // Close resources to avoid memory leaks
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    // Handle exception during closing
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Handle exception during closing
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
                    Date createdAt = rs.getDate("CreatedAt");
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
                    // Fixed constructor call
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
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        ArrayList<FeedbackDTO> dto = feedbackDAO.getListFeedback(11);
        for (FeedbackDTO feedbackDTO : dto) {
            System.out.println(feedbackDTO.getCustomerID());
            System.out.println(feedbackDTO.getTourID());
        }
    }
}
