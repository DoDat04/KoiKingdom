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
                String sql = "SELECT t.TourID, t.TourName, t.Duration, t.Description, t.TourPrice, t.StartDate, t.EndDate, t.Image, t.Rating, "
                        + "(SELECT STRING_AGG(f.FarmName, ', ') FROM TOUR_FARM tf "
                        + "INNER JOIN FARM f ON tf.FarmID = f.FarmID WHERE tf.TourID = t.TourID) AS 'Farm', "
                        + "(SELECT STRING_AGG(k.TypeName, ', ') FROM TOUR_KOITYPE tk "
                        + "INNER JOIN KOITYPE k ON tk.KoiTypeID = k.KoiTypeID WHERE tk.TourID = t.TourID) AS 'KoiType' "
                        + "FROM TOUR t "
                        + "GROUP BY t.TourID, t.TourName, t.Duration, t.Description, t.TourPrice, t.StartDate, t.EndDate, t.Image, t.Rating";
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

                    TourDTO dto = new TourDTO(tourID, tourName, koiTypeName, farmName, tourDuration, tourDescription, tourPrice, startDate, endDate, tourImage, tourRating);
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
                        + "t.TourPrice, t.StartDate, t.EndDate, t.Image, t.Rating, "
                        + "(SELECT STRING_AGG(f.FarmName, ', ') FROM TOUR_FARM tf "
                        + "INNER JOIN FARM f ON tf.FarmID = f.FarmID WHERE tf.TourID = t.TourID) AS Farm, "
                        + "(SELECT STRING_AGG(k.TypeName, ', ') FROM TOUR_KOITYPE tk "
                        + "INNER JOIN KOITYPE k ON tk.KoiTypeID = k.KoiTypeID WHERE tk.TourID = t.TourID) AS KoiType "
                        + "FROM TOUR t WHERE 1=1 ");

                // Thêm các bộ lọc một cách động dựa trên đầu vào không null
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
                if (priceOrder != null && !priceOrder.isEmpty()) {
                    sql.append(" ORDER BY t.TourPrice ").append(priceOrder.equalsIgnoreCase("asc") ? "ASC" : "DESC");
                }

                stm = con.prepareStatement(sql.toString());

                int paramIndex = 1;
                // Gán các tham số cho các bộ lọc
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

                    TourDTO dto = new TourDTO(tourID, tourName, koiTypeName, farmName, tourDuration, tourDescription, tourPrice, start, end, tourImage, tourRating);
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
}