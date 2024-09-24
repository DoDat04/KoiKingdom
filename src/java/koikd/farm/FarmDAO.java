/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.farm;

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
public class FarmDAO {

    /**
     *
     * @param nameFarm
     * @return farm list if nameFarm is null. If nameFarm is not null, return a
     * list with nameFarm keyword.
     * @throws SQLException
     */
    public ArrayList<FarmDTO> getFarmList(String nameFarm) throws SQLException {
        ArrayList<FarmDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [FarmID], [FarmName], [Location], [Description], [Image], [FarmStatus]\n"
                        + " FROM [dbo].[FARM]\n"
                        + " WHERE [FarmStatus] = 'TRUE'";
                if (nameFarm != null && !nameFarm.isEmpty()) {
                    sql += " AND [FarmName] LIKE ?";
                }
                pst = conn.prepareStatement(sql);
                if (nameFarm != null && !nameFarm.isEmpty()) {
                    pst.setString(1, "%" + nameFarm + "%");
                }
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int farmID = rs.getInt("farmID");
                        String farmName = rs.getString("farmName");
                        String location = rs.getString("location");
                        String description = rs.getString("description");
                        String farmImageURL = rs.getString("image");
                        boolean farmStatus = rs.getBoolean("farmStatus");
                        FarmDTO dao = new FarmDTO(farmID, farmName, location, description, farmImageURL, farmStatus);
                        list.add(dao);
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
//    public static void main(String[] args) throws SQLException {
//        String nameFarm = "Sakura";
//        FarmDAO services = new FarmDAO();
//        ArrayList<FarmDTO> dto = services.getFarmList(nameFarm);
//        for (FarmDTO farmDTO : dto) {
//            if(farmDTO!=null){
//                System.out.println(farmDTO);
//            }
//        }
//    }

    /**
     *
     * @param id
     * @return list of farm with without the deleted farm.
     * @throws SQLException
     */
    public boolean deleteFarm(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM [dbo].[FARM]"
                        + " WHERE [FarmID] = ?";
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
     * @param id
     * @param update status farm
     * @return
     * @throws SQLException 
     */
    public boolean updateStatusFarm(int id, boolean status) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE [dbo].[FARM]\n"
                        + "SET [FarmStatus] = ?\n"
                        + "WHERE [FarmID] = ?";
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
//        FarmDAO dao = new FarmDAO();
//
//        int farmID = 1;
//        boolean newStatus = false;
//
//        try {
//            boolean isUpdated = dao.updateStatusFarm(farmID, newStatus);
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
