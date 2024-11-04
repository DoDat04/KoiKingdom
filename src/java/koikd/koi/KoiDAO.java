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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<KoiDTO> getKoiTypeByFarm(String farmID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<KoiDTO> koiList = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT kt.KoiTypeID, kt.TypeName "
                        + "FROM KOITYPE kt "
                        + "INNER JOIN KOI k ON kt.KoiTypeID = k.KoiTypeID "
                        + "INNER JOIN KOI_FARM kf ON k.KoiID = kf.KoiID "
                        + "WHERE kf.farmID = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, farmID);

                rs = stm.executeQuery();

                while (rs.next()) {
                    KoiDTO koi = new KoiDTO();
                    koi.setKoiTypeID(rs.getInt("KoiTypeID"));
                    koi.setKoiName(rs.getString("TypeName"));
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

    public List<KoiDTO> getKoiByKoiType(String koiTypeID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<KoiDTO> koiList = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT KoiID, KoiName "
                        + "FROM KOI "
                        + "WHERE KoiTypeID = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, koiTypeID);

                rs = stm.executeQuery();

                while (rs.next()) {
                    KoiDTO koi = new KoiDTO();
                    koi.setKoiID(rs.getInt("KoiID"));
                    koi.setKoiName(rs.getString("KoiName"));
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

    public int insertKoiOrder(KoiOrderDTO koiOrderDTO) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int koiOrderID = -1;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO [dbo].[KOIORDER]([CustomerID], [DeliveryDate], [Status], [EstimatedDelivery], [Type], [CostShipping], [ShippingAddress]) "
                        + "VALUES(?, ?, ?, NULL, ?, ?, ?)";
                pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); // Lấy giá trị khóa tự động

                pst.setInt(1, koiOrderDTO.getCustomerID());
                pst.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
                pst.setBoolean(3, koiOrderDTO.isStatus());
                pst.setString(4, koiOrderDTO.getType());
                pst.setDouble(5, koiOrderDTO.getCostShipping());
                pst.setString(6, koiOrderDTO.getShippingAddress());

                int affectedRows = pst.executeUpdate();
                if (affectedRows > 0) {
                    // Lấy KoiOrderID tự động sinh
                    rs = pst.getGeneratedKeys();

                    if (rs.next()) {
                        koiOrderID = rs.getInt(1); // Lấy giá trị KoiOrderID từ kết quả
                    }
                }
            }
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
        return koiOrderID;
    }

    public int getKoiTypeIDByKoiID(int koiID) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int koiTypeID = -1; // Default value if koiTypeID is not found

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT kt.KoiTypeID "
                        + "FROM KOI k INNER JOIN KOITYPE kt ON k.KoiTypeID = kt.KoiTypeID "
                        + "WHERE k.KoiID = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, koiID);

                rs = pst.executeQuery();
                if (rs.next()) {
                    koiTypeID = rs.getInt("KoiTypeID");
                }
            }
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
        return koiTypeID;
    }

    public double getDistance(String province) {
        Map<String, Double> distanceMap = new HashMap<>();
        distanceMap.put("Hà Nội", 1690.0);
        distanceMap.put("Hà Giang", 1949.0);
        distanceMap.put("Cao Bằng", 1957.0);
        distanceMap.put("Bắc Kạn", 1840.0);
        distanceMap.put("Tuyên Quang", 1796.0);
        distanceMap.put("Lào Cai", 1949.0);
        distanceMap.put("Điện Biên", 1971.0);
        distanceMap.put("Lai Châu", 2004.0);
        distanceMap.put("Sơn La", 1818.0);
        distanceMap.put("Yên Bái", 1801.0);
        distanceMap.put("Hòa Bình", 1640.0);
        distanceMap.put("Thái Nguyên", 1665.0);
        distanceMap.put("Lạng Sơn", 1832.0);
        distanceMap.put("Quảng Ninh", 1823.0);
        distanceMap.put("Bắc Giang", 1733.0);
        distanceMap.put("Phú Thọ", 1729.0);
        distanceMap.put("Vĩnh Phúc", 1729.0);
        distanceMap.put("Bắc Ninh", 1710.0);
        distanceMap.put("Hải Dương", 1683.0);
        distanceMap.put("Hải Phòng", 1708.0);
        distanceMap.put("Hưng Yên", 1640.0);
        distanceMap.put("Thái Bình", 1665.0);
        distanceMap.put("Hà Nam", 1632.0);
        distanceMap.put("Nam Định", 1619.0);
        distanceMap.put("Ninh Bình", 1586.0);
        distanceMap.put("Thanh Hóa", 1531.0);
        distanceMap.put("Nghệ An", 1513.0);
        distanceMap.put("Hà Tĩnh", 1333.0);
        distanceMap.put("Quảng Bình", 1239.0);
        distanceMap.put("Quảng Trị", 1113.0);
        distanceMap.put("Thừa Thiên Huế", 1024.0);
        distanceMap.put("Đà Nẵng", 924.0);
        distanceMap.put("Quảng Nam", 973.0);
        distanceMap.put("Quảng Ngãi", 780.0);
        distanceMap.put("Bình Định", 623.0);
        distanceMap.put("Phú Yên", 538.0);
        distanceMap.put("Khánh Hòa", 387.0);
        distanceMap.put("Ninh Thuận", 317.0);
        distanceMap.put("Bình Thuận", 184.0);
        distanceMap.put("Kon Tum", 559.0);
        distanceMap.put("Gia Lai", 654.0);
        distanceMap.put("Đắk Lắk", 358.0);
        distanceMap.put("Đắk Nông", 311.0);
        distanceMap.put("Lâm Đồng", 273.0);
        distanceMap.put("Bình Phước", 123.0);
        distanceMap.put("Tây Ninh", 88.0);
        distanceMap.put("Bình Dương", 42.0);
        distanceMap.put("Đồng Nai", 101.0);
        distanceMap.put("Bà Rịa - Vũng Tàu", 93.0);
        distanceMap.put("Hồ Chí Minh", 0.0); // mốc, nên chi phí là 0
        distanceMap.put("Long An", 60.0);
        distanceMap.put("Tiền Giang", 66.0);
        distanceMap.put("Bến Tre", 88.0);
        distanceMap.put("Trà Vinh", 128.0);
        distanceMap.put("Vĩnh Long", 127.0);
        distanceMap.put("Đồng Tháp", 147.0);
        distanceMap.put("An Giang", 232.0);
        distanceMap.put("Kiên Giang", 243.0);
        distanceMap.put("Cần Thơ", 193.0);
        distanceMap.put("Hậu Giang", 188.0);
        distanceMap.put("Sóc Trăng", 209.0);
        distanceMap.put("Bạc Liêu", 254.0);
        distanceMap.put("Cà Mau", 193.0);

        // Lấy khoảng cách cho tỉnh đã cho, nếu không tìm thấy trả về 0
        return distanceMap.getOrDefault(province, 0.0);
    }

    // Hàm tính cước phí vận chuyển
    public double calculateShippingFee(int quantity, double weightPerFish, double distance) {
        double BASE_FEE_PER_KG = 5000; // VND per kg
        double DISTANCE_FEE_PER_KM = 1000; // VND per km
        double EXCHANGE_RATE_VND_TO_USD = 23000.0; // VND to USD

        // Calculate the fee in VND
        double feeInVND = (quantity * weightPerFish * BASE_FEE_PER_KG) + (distance * DISTANCE_FEE_PER_KM);

        // Convert the fee to USD and round to 2 decimal places
        double feeInUSD = feeInVND / EXCHANGE_RATE_VND_TO_USD;
        return Math.round(feeInUSD * 100.0) / 100.0;
    }

    public ArrayList<KoiDTO> koiOrderTrending() throws SQLException {
        ArrayList<KoiDTO> koiOrderTrending = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 8 b.KoiID, b.KoiName, b.Image, COUNT(a.KoiID) as KoiOrderTrending "
                        + " FROM [dbo].[KOIORDERDETAIL] a "
                        + " INNER JOIN [dbo].[KOI] b ON a.KoiID = b.KoiID "
                        + " GROUP BY b.KoiID, b.KoiName, b.Image "
                        + " ORDER BY KoiOrderTrending DESC;";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int koiID = rs.getInt("koiID");
                        String koiName = rs.getString("koiName");
                        String image = rs.getString("image");
                        KoiDTO dto = new KoiDTO(koiID, koiName, image);
                        koiOrderTrending.add(dto);
                    }
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
        return koiOrderTrending;
    }
    
    public static void main(String[] args) throws SQLException {
        KoiDAO services = new KoiDAO();
        ArrayList<KoiDTO> list = services.koiOrderTrending();
        for (KoiDTO koiDTO : list) {
            if(koiDTO!=null){
                System.out.println(koiDTO);
            }
        }
    }
}
