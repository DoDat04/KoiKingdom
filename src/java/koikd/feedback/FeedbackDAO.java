/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.feedback;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Statement;
import koikd.tour.TourDTO;
import koikd.tourbookingdetail.TourBookingDetailDAO;
import koikd.tourbookingdetail.TourBookingDetailDTO;
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
        String sql = "INSERT INTO [dbo].[FEEDBACK] (CustomerID, TourID, Rating, FeedbackText, CreatedAt) "
                + "VALUES (?, ?, ?, ?, GETDATE());";

        try {
            conn = DBUtils.getConnection();
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

}
