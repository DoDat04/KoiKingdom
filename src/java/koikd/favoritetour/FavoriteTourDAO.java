/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.favoritetour;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import koikd.utils.DBUtils;

/**
 *
 * @author Do Dat
 */
public class FavoriteTourDAO implements Serializable {

    public List<FavoriteTourDTO> getFavoriteTour(int custID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<FavoriteTourDTO> favoriteTourList = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT fa.FavoriteTourID, t.TourID, t.TourName, t.Duration, t.Description, t.TourPrice, t.StartDate, t.EndDate, t.Image, t.DepartureLocation, "
                        + "AVG(f.Rating) AS Rating, "
                        + "(SELECT STRING_AGG(f2.FarmName, ', ') FROM TOUR_FARM tf INNER JOIN FARM f2 ON tf.FarmID = f2.FarmID WHERE tf.TourID = t.TourID) AS Farm, "
                        + "(SELECT STRING_AGG(k.TypeName, ', ') FROM TOUR_KOITYPE tk INNER JOIN KOITYPE k ON tk.KoiTypeID = k.KoiTypeID WHERE tk.TourID = t.TourID) AS KoiType "
                        + "FROM FAVORITETOUR fa "
                        + "INNER JOIN TOUR t ON fa.TourID = t.TourID "
                        + "LEFT JOIN TOUR_FEEDBACK tf ON t.TourID = tf.TourID "
                        + "LEFT JOIN FEEDBACK f ON tf.FeedbackID = f.FeedbackID "
                        + "WHERE fa.CustomerID = ? "
                        + "GROUP BY fa.FavoriteTourID, t.TourID, t.TourName, t.Duration, t.Description, t.TourPrice, t.StartDate, t.EndDate, t.Image, t.DepartureLocation";

                stm = con.prepareStatement(sql);
                stm.setInt(1, custID);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int favoriteTourID = rs.getInt("FavoriteTourID"); 
                    int tourID = rs.getInt("TourID");
                    String tourName = rs.getString("TourName");
                    String tourDuration = rs.getString("Duration");
                    String tourDescription = rs.getString("Description");
                    double tourPrice = rs.getDouble("TourPrice");
                    Timestamp startDate = rs.getTimestamp("StartDate");
                    Timestamp endDate = rs.getTimestamp("EndDate");
                    String farmName = rs.getString("Farm");
                    String koiTypeName = rs.getString("KoiType");
                    String tourImage = rs.getString("Image");
                    double tourRating = rs.getDouble("Rating");
                    String departureLocation = rs.getString("DepartureLocation");

                    FavoriteTourDTO dto = new FavoriteTourDTO(favoriteTourID, custID, tourID, tourName, koiTypeName, farmName, tourDuration, tourDescription, tourPrice, startDate, endDate, tourImage, tourRating, departureLocation);
                    favoriteTourList.add(dto);
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
        return favoriteTourList;
    }

    public void insertFavoriteTour(int custID, int tourID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement checkStm = null;
        PreparedStatement insertStm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                // Check if the record already exists
                String checkSql = "SELECT COUNT(*) FROM FAVORITETOUR WHERE CustomerID = ? AND TourID = ?";
                checkStm = con.prepareStatement(checkSql);
                checkStm.setInt(1, custID);
                checkStm.setInt(2, tourID);

                ResultSet rs = checkStm.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt(1);
                    if (count == 0) {
                        // Record does not exist, proceed to insert
                        String insertSql = "INSERT INTO FAVORITETOUR (CustomerID, TourID) VALUES (?, ?)";
                        insertStm = con.prepareStatement(insertSql);
                        insertStm.setInt(1, custID);
                        insertStm.setInt(2, tourID);
                        insertStm.executeUpdate();
                    } else {
                        System.out.println("This favorite tour already exists for the customer.");
                    }
                }
            }
        } finally {
            if (insertStm != null) {
                insertStm.close();
            }
            if (checkStm != null) {
                checkStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void deleteFavoriteTour(int favoriteTourID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "DELETE FROM FAVORITETOUR "
                        + "WHERE FavoriteTourID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, favoriteTourID);
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
}
