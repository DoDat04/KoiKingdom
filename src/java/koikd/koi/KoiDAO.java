/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.koi;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import koikd.order.KoiOrderDTO;
import koikd.utils.DBUtils;

/**
 *
 * @author Do Dat
 */
public class KoiDAO implements Serializable {

    public List<KoiDTO> getAllKoiList() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<KoiDTO> koiList = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT k.KoiID, k.KoiName, k.Age, kt.TypeName, k.Length, k.Weight, k.Price, k.Image "
                        + "FROM KOI k "
                        + "INNER JOIN KOITYPE kt ON k.KoiTypeID = kt.KoiTypeID ";
                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int koiID = rs.getInt("KoiID");
                    String koiName = rs.getString("KoiName");
                    int age = rs.getInt("Age");
                    String typeName = rs.getString("TypeName");
                    double length = rs.getDouble("Length");
                    double weight = rs.getDouble("Weight");
                    double price = rs.getDouble("Price");
                    String koiImage = rs.getString("Image");

                    KoiDTO koi = new KoiDTO(koiID, koiName, typeName, age, length, weight, price, koiImage);
                    koiList.add(koi);
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
        return koiList;
    }

    public List<KoiDTO> filterKois(String farmID, String priceOrder) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<KoiDTO> koiList = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT k.KoiID, k.KoiName, k.Age, kt.TypeName, k.Length, k.Weight, k.Price, k.Image "
                        + "FROM KOI k "
                        + "INNER JOIN KOI_FARM kf ON k.KoiID = kf.KoiID "
                        + "INNER JOIN FARM f ON f.FarmID = kf.FarmID "
                        + "INNER JOIN KOITYPE kt ON k.KoiTypeID = kt.KoiTypeID";

                boolean hasFarmFilter = (farmID != null && !farmID.isEmpty());
                if (hasFarmFilter) {
                    sql += " WHERE f.FarmID = ?";
                }

                if (priceOrder != null && !priceOrder.isEmpty()) {
                    sql += " ORDER BY k.Price " + (priceOrder.equalsIgnoreCase("asc") ? "ASC" : "DESC");
                }

                stm = con.prepareStatement(sql);
                if (hasFarmFilter) {
                    stm.setString(1, farmID);
                }

                rs = stm.executeQuery();
                while (rs.next()) {
                    int koiID = rs.getInt("KoiID");
                    String koiName = rs.getString("KoiName");
                    int age = rs.getInt("Age");
                    String typeName = rs.getString("TypeName");
                    double length = rs.getDouble("Length");
                    double weight = rs.getDouble("Weight");
                    double price = rs.getDouble("Price");
                    String koiImage = rs.getString("Image");

                    KoiDTO koi = new KoiDTO(koiID, koiName, typeName, age, length, weight, price, koiImage);
                    koiList.add(koi);
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
        return koiList;
    }

    public KoiDTO getKoiByID(int koiId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        KoiDTO koi = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT k.KoiID, k.KoiName, k.Age, kt.TypeName, k.Length, k.Weight, k.Price, k.Image "
                        + "FROM KOI k "
                        + "INNER JOIN KOITYPE kt ON k.KoiTypeID = kt.KoiTypeID "
                        + "WHERE k.KoiID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, koiId);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int koiID = rs.getInt("KoiID");
                    String koiName = rs.getString("KoiName");
                    int age = rs.getInt("Age");
                    String typeName = rs.getString("TypeName");
                    double length = rs.getDouble("Length");
                    double weight = rs.getDouble("Weight");
                    double price = rs.getDouble("Price");
                    String koiImage = rs.getString("Image");

                    koi = new KoiDTO(koiID, koiName, typeName, age, length, weight, price, koiImage);

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
        return koi;
    }

    public void insertKoiOrder(KoiOrderDTO koiOrderDTO) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO [dbo].[KOIORDER]([CustomerID], [DeliveryDate], [Status], [EstimatedDelivery], [Type]) "
                        + "VALUES(?, ?, ?, NULL, ?)";
                pst = conn.prepareStatement(sql);

                pst.setInt(1, koiOrderDTO.getCustomerID());
                pst.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
                pst.setBoolean(3, koiOrderDTO.isStatus());
                pst.setString(4, koiOrderDTO.getType());

                pst.executeUpdate();
            }
        } finally {
            // Close resources
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
