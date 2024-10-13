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
import java.util.List;
import koikd.customer.CustomerDTO;
import koikd.farm.FarmDTO;
import koikd.koi.KoiDTO;
import koikd.order.KoiOrderDAO;
import koikd.order.KoiOrderDTO;
import koikd.order.KoiOrderDetailDTO;
import koikd.utils.DBUtils;

/**
 *
 * @author ADMIN LAM
 */
public class KoiTypeDAO {

    /**
     *
     * @param nameKoiType
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
                String sql = "SELECT [KoiTypeID], [TypeName], [Description], [Image], [KoiTypeStatus] "
                        + "FROM [dbo].[KOITYPE] "
                        + "WHERE [KoiTypeStatus] = 'TRUE' ";
                if (nameKoiType != null && !nameKoiType.isEmpty()) {
                    sql += "AND [TypeName] LIKE ?";
                }
                pst = conn.prepareStatement(sql);

                if (nameKoiType != null && !nameKoiType.isEmpty()) {
                    pst.setString(1, "%" + nameKoiType + "%");
                }

                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int koiTypeID = rs.getInt("KoiTypeID");
                        String typeName = rs.getString("TypeName");
                        String description = rs.getString("Description");
                        String koiImageURL = rs.getString("Image");
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

//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        String nameKoiType = "Benigoi";
//        KoiTypeDAO services = new KoiTypeDAO();
//        ArrayList<KoiTypeDTO> dto = services.getKoiTypeList(nameKoiType);
//        for (KoiTypeDTO koiTypeDTO : dto) {
//            if (koiTypeDTO != null) {
//                System.out.println(koiTypeDTO);
//            }
//        }
//    }
    /**
     *
     * @param id
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
                String sql = "DELETE FROM [dbo].[KOITYPE]"
                        + " WHERE [KoiTypeID] = ?";
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
     * @param id,status
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

    public List<KoiTypeDTO> getKoiTypeList() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<KoiTypeDTO> koiTypeList = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT KoiTypeID, TypeName, Description, Image, KoiTypeStatus "
                        + "FROM KOITYPE";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int koiTypeID = rs.getInt("KoiTypeID");
                    String typeName = rs.getString("TypeName");
                    String description = rs.getString("Description");
                    String koiImageURL = rs.getString("Image");
                    boolean koiTypeStatus = rs.getBoolean("KoiTypeStatus");
                    KoiTypeDTO dto = new KoiTypeDTO(koiTypeID, typeName, description, koiImageURL, koiTypeStatus);
                    koiTypeList.add(dto);
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
        return koiTypeList;
    }

    /** 
     *  Get Koi Type
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public KoiTypeDTO getKoiType(int id) throws ClassNotFoundException, SQLException {
        KoiTypeDTO dto = null;
        Connection conn = DBUtils.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            if (conn != null) {
                String sql = "SELECT t.KoiTypeID, t.TypeName, t.Description, t.Image, t.KoiTypeStatus "
                        + "FROM dbo.KOITYPE t "
                        + "INNER JOIN dbo.KOI k ON t.KoiTypeID = k.KoiTypeID "
                        + "WHERE k.KoiTypeID = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, id); 

                rs = pst.executeQuery();

                // Retrieve results
                if (rs.next()) {
                    int koiTypeID = rs.getInt("KoiTypeID");
                    String typeName = rs.getString("TypeName");
                    String description = rs.getString("Description");
                    String koiImageURL = rs.getString("Image");
                    boolean koiTypeStatus = rs.getBoolean("KoiTypeStatus");
                    dto = new KoiTypeDTO(koiTypeID, typeName, description, koiImageURL, koiTypeStatus);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure resources are closed in reverse order of their creation
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
        return dto; // Return the retrieved KoiTypeDTO
    }
}
