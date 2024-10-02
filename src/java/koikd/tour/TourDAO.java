/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.tour;

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
public class TourDAO implements Serializable {

    public List<TourDTO> getTourList() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TourDTO> tourList = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT t.TourID, t.TourName, t.Duration, t.Description, t.TourPrice, t.StartDate, t.EndDate, t.Image, t.DepartureLocation, "
                        + "AVG(f.Rating) AS Rating,  "
                        + "(SELECT STRING_AGG(f2.FarmName, ', ') FROM TOUR_FARM tf "
                        + "INNER JOIN FARM f2 ON tf.FarmID = f2.FarmID WHERE tf.TourID = t.TourID) AS Farm, "
                        + "(SELECT STRING_AGG(k.TypeName, ', ') FROM TOUR_KOITYPE tk "
                        + "INNER JOIN KOITYPE k ON tk.KoiTypeID = k.KoiTypeID WHERE tk.TourID = t.TourID) AS KoiType "
                        + "FROM TOUR t "
                        + "LEFT JOIN TOUR_FEEDBACK tf ON t.TourID = tf.TourID "
                        + "LEFT JOIN FEEDBACK f ON tf.FeedbackID = f.FeedbackID "
                        + "GROUP BY t.TourID, t.TourName, t.Duration, t.Description, t.TourPrice, t.StartDate, t.EndDate, t.Image, t.DepartureLocation";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
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

                    TourDTO dto = new TourDTO(tourID, tourName, koiTypeName, farmName, tourDuration, tourDescription, tourPrice, startDate, endDate, tourImage, tourRating, departureLocation);
                    tourList.add(dto);
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

        return tourList;
    }

    public List<TourDTO> filterTours(String farmID, String koiTypeID, String priceOrder, String startDate, String endDate)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TourDTO> tourList = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                StringBuilder sql = new StringBuilder("SELECT t.TourID, t.TourName, t.Duration, t.Description, "
                        + "t.TourPrice, t.StartDate, t.EndDate, t.Image, t.DepartureLocation, "
                        + "AVG(f.Rating) AS Rating,  "
                        + "(SELECT STRING_AGG(f.FarmName, ', ') FROM TOUR_FARM tf "
                        + "INNER JOIN FARM f ON tf.FarmID = f.FarmID WHERE tf.TourID = t.TourID) AS Farm, "
                        + "(SELECT STRING_AGG(k.TypeName, ', ') FROM TOUR_KOITYPE tk "
                        + "INNER JOIN KOITYPE k ON tk.KoiTypeID = k.KoiTypeID WHERE tk.TourID = t.TourID) AS KoiType "
                        + "FROM TOUR t "
                        + "LEFT JOIN TOUR_FEEDBACK tf ON t.TourID = tf.TourID "
                        + "LEFT JOIN FEEDBACK f ON tf.FeedbackID = f.FeedbackID "
                        + "WHERE 1=1 ");

                // Add dynamic filters based on non-null inputs
                if (farmID != null && !farmID.isEmpty()) {
                    sql.append(" AND EXISTS (SELECT 1 FROM TOUR_FARM tf WHERE tf.TourID = t.TourID AND tf.FarmID = ?) ");
                }
                if (koiTypeID != null && !koiTypeID.isEmpty()) {
                    sql.append(" AND EXISTS (SELECT 1 FROM TOUR_KOITYPE tk WHERE tk.TourID = t.TourID AND tk.KoiTypeID = ?) ");
                }
                if (startDate != null && !startDate.isEmpty()) {
                    sql.append(" AND t.StartDate >= ? ");
                }
                if (endDate != null && !endDate.isEmpty()) {
                    sql.append(" AND t.EndDate <= ? ");
                }
                sql.append(" GROUP BY t.TourID, t.TourName, t.Duration, t.Description, t.TourPrice, "
                        + "t.StartDate, t.EndDate, t.Image, t.DepartureLocation ");

                if (priceOrder != null && !priceOrder.isEmpty()) {
                    sql.append(" ORDER BY t.TourPrice ").append(priceOrder.equalsIgnoreCase("asc") ? "ASC" : "DESC");
                }

                stm = con.prepareStatement(sql.toString());

                int paramIndex = 1;
                // Set parameters for the filters
                if (farmID != null && !farmID.isEmpty()) {
                    stm.setString(paramIndex++, farmID);
                }
                if (koiTypeID != null && !koiTypeID.isEmpty()) {
                    stm.setString(paramIndex++, koiTypeID);
                }
                if (startDate != null && !startDate.isEmpty()) {
                    stm.setString(paramIndex++, startDate);
                }
                if (endDate != null && !endDate.isEmpty()) {
                    stm.setString(paramIndex++, endDate);
                }

                rs = stm.executeQuery();
                while (rs.next()) {
                    int tourID = rs.getInt("TourID");
                    String tourName = rs.getString("TourName");
                    String tourDuration = rs.getString("Duration");
                    String tourDescription = rs.getString("Description");
                    double tourPrice = rs.getDouble("TourPrice");
                    Timestamp start = rs.getTimestamp("StartDate");
                    Timestamp end = rs.getTimestamp("EndDate");
                    String farmName = rs.getString("Farm");
                    String koiTypeName = rs.getString("KoiType");
                    String tourImage = rs.getString("Image");
                    double tourRating = rs.getDouble("Rating");
                    String departureLocation = rs.getString("DepartureLocation");

                    TourDTO dto = new TourDTO(tourID, tourName, koiTypeName, farmName, tourDuration, tourDescription, tourPrice, start, end, tourImage, tourRating, departureLocation);
                    tourList.add(dto);
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
        return tourList;
    }

    public TourDTO getTourByID(int tourID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TourDTO tour = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT t.TourID, t.TourName, t.Duration, t.Description, t.TourPrice, "
                        + "t.StartDate, t.EndDate, t.Image, t.DepartureLocation, "
                        + "AVG(f.Rating) AS Rating, " 
                        + "(SELECT STRING_AGG(f.FarmName, ', ') FROM TOUR_FARM tf "
                        + "INNER JOIN FARM f ON tf.FarmID = f.FarmID WHERE tf.TourID = t.TourID) AS Farm, "
                        + "(SELECT STRING_AGG(k.TypeName, ', ') FROM TOUR_KOITYPE tk "
                        + "INNER JOIN KOITYPE k ON tk.KoiTypeID = k.KoiTypeID WHERE tk.TourID = t.TourID) AS KoiType "
                        + "FROM TOUR t "
                        + "LEFT JOIN TOUR_FEEDBACK tf ON t.TourID = tf.TourID "
                        + "LEFT JOIN FEEDBACK f ON tf.FeedbackID = f.FeedbackID "
                        + "WHERE t.TourID = ? "
                        + "GROUP BY t.TourID, t.TourName, t.Duration, t.Description, "
                        + "t.TourPrice, t.StartDate, t.EndDate, t.Image, t.DepartureLocation"; 

                stm = con.prepareStatement(sql);
                stm.setInt(1, tourID);  // Set tourID as a parameter
                rs = stm.executeQuery();

                if (rs.next()) {
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

                    tour = new TourDTO(tourID, tourName, koiTypeName, farmName, tourDuration, tourDescription, tourPrice, startDate, endDate, tourImage, tourRating, departureLocation);
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

        return tour;
    }

    public List<TourDTO> getAllTour() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TourDTO> result = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT TourID, TourName, Duration, Description, TourPrice, StartDate, EndDate, Image, Status, DepartureLocation "
                        + "FROM TOUR ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {

                    int id = rs.getInt("TourID");
                    String name = rs.getString("TourName");
                    String duration = rs.getString("Duration");
                    String description = rs.getString("Description");
                    double price = rs.getDouble("TourPrice");
                    Timestamp start = rs.getTimestamp("StartDate");
                    Timestamp end = rs.getTimestamp("EndDate");
                    String img = rs.getString("Image");
                    boolean status = rs.getBoolean("Status");
                    String loca = rs.getString("DepartureLocation");
                    TourDTO dto = new TourDTO(id, name, duration, description, price, start, end, img, status, duration);
                    result.add(dto);
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
        return result;
    }

    /**
     *
     * @param id
     * @return status tour.
     * @throws SQLException
     */
    public boolean updateStatusTour(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String selectSql = "SELECT [Status] FROM [dbo].[TOUR] "
                        + "WHERE [TourID] = ?";
                pst = conn.prepareStatement(selectSql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    boolean currentStatus = rs.getBoolean("Status");

                    String updateSql = "UPDATE [dbo].[TOUR] SET [Status] = ? WHERE [TourID] = ?";
                    pst = conn.prepareStatement(updateSql);
                    pst.setBoolean(1, !currentStatus);
                    pst.setInt(2, id);
                    int affectedRows = pst.executeUpdate();
                    return affectedRows > 0;
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
        return false;
    }

//    public static void main(String[] args) throws SQLException {
//        int id = 11;
//        TourDAO dao = new TourDAO();
//        boolean dto = dao.updateStatusTour(id);
//        if(dto){
//            System.out.println("Update status successfully.");
//        } else{
//            System.out.println("Fail.");
//        }
//    }
}
