/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.koitype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import koikd.utils.DBUtils;

/**
 *
 * @author ADMIN LAM
 */
public class KoiTypeDAO {

    /**
     *
     * @param Customer can see the koi type list, whether a name koi type is
     * null.
     * @return a list of koi type
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<KoiTypeDTO> getKoiTypeList(String nameKoiType) throws ClassNotFoundException, SQLException {
    ArrayList<KoiTypeDTO> list = new ArrayList<>();
    Connection conn = DBUtils.getConnection();
    PreparedStatement pst = null;
    ResultSet rs = null;
    try {
        conn = DBUtils.getConnection();
        if (conn != null) {
            String sql = "SELECT [KoiTypeID], [TypeName], [Description], [KoiImageURL], [KoiTypeStatus] "
                    + "FROM [dbo].[KOITYPE] "
                    + "WHERE [KoiTypeStatus] = 'TRUE' ";
            // Chỉ thêm điều kiện tìm kiếm nếu nameKoiType có giá trị
            if (nameKoiType != null && !nameKoiType.isEmpty()) {
                sql += "AND [TypeName] LIKE ?";
            }
            pst = conn.prepareStatement(sql);

            // Gán giá trị tìm kiếm nếu có
            if (nameKoiType != null && !nameKoiType.isEmpty()) {
                pst.setString(1, "%" + nameKoiType + "%");
            }

            rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int koiTypeID = rs.getInt("KoiTypeID");
                    String typeName = rs.getString("TypeName");
                    String description = rs.getString("Description");
                    String koiImageURL = rs.getString("KoiImageURL");
                    boolean koiTypeStatus = rs.getBoolean("KoiTypeStatus");
                    KoiTypeDTO dto = new KoiTypeDTO(koiTypeID, typeName, description, koiImageURL, koiTypeStatus);
                    list.add(dto);
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
    return list;
}


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String nameKoiType = "Chagoi";
        KoiTypeDAO services = new KoiTypeDAO();
        ArrayList<KoiTypeDTO> dto = services.getKoiTypeList(nameKoiType);
        for (KoiTypeDTO koiTypeDTO : dto) {
            if (koiTypeDTO != null) {
                System.out.println(koiTypeDTO);
            }
        }
    }

    /**
     *
     * @param Delete the koi type
     * @return the list of koi type before a manager deleted it.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean deleteKoiType(int id) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM [dbo].[KOITYPE]\n"
                        + "WHERE [KoiTypeID] = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, id);
                int affectedRows = pst.executeUpdate();
                return affectedRows > 0;
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
        return false;
    }

    /**
     *
     * @param Update status koi type, manager can update status koi type. If a
     * manager want to change a status of koi type (block/active), they can do
     * it through a button.
     * @param update status of koi type.
     * @return
     * @throws SQLException
     */
    public boolean updateStatusKoiType(int id, boolean status) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE [dbo].[KOITYPE] \n"
                        + "SET [KoiTypeStatus] = ? \n"
                        + "WHERE [KoiTypeID] = ?";
                pst = conn.prepareStatement(sql);
                pst.setBoolean(1, !status);
                pst.setInt(2, id);
                int affectedRows = pst.executeUpdate();
                return affectedRows > 0;
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

//    public static void main(String[] args) {
//        KoiTypeDAO dao = new KoiTypeDAO();
//
//        int koiTypeID = 2;
//        boolean newStatus = true;
//
//        try {
//            boolean isUpdated = dao.updateStatusKoiType(koiTypeID, newStatus);
//            if (isUpdated) {
//                System.out.println("Update status successfully.");
//            } else {
//                System.out.println("No data is updated.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
