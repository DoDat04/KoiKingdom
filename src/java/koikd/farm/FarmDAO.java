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
import java.util.List;
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
                String sql = "SELECT [FarmID], [FarmName], [Location], [Description], [Image], [Status] "
                        + " FROM [dbo].[FARM] "
                        + " WHERE [Status] = 'TRUE'";
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
                        int farmID = rs.getInt("FarmID");
                        String farmName = rs.getString("FarmName");
                        String farmLocation = rs.getString("Location");
                        String description = rs.getString("Description");
                        String farmImageURL = rs.getString("Image");
                        boolean farmStatus = rs.getBoolean("Status");
                        FarmDTO dao = new FarmDTO(farmID, farmName, farmLocation, description, farmImageURL, farmStatus);
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
//        String nameFarm = "";
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
                        + "SET [Status] = ?\n"
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
    
    public boolean updateFarmStatus(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String selectSql = "SELECT [Status] FROM [dbo].[FARM] "
                        + "WHERE [FarmID] = ?";
                pst = conn.prepareStatement(selectSql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    boolean currentStatus = rs.getBoolean("Status");

                    String updateSql = "UPDATE [dbo].[FARM] SET [Status] = ? WHERE [FarmID] = ?";
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

    public List<FarmDTO> getFarmList() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<FarmDTO> farmList = new ArrayList<>();
        
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT FarmID, FarmName, Location, Description, Image, Status "
                        + "FROM FARM";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int farmID = rs.getInt("FarmID");
                    String farmName = rs.getString("FarmName");
                    String location = rs.getString("Location");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    boolean status = rs.getBoolean("Status");
                    
                    FarmDTO dto = new FarmDTO(farmID, farmName, location, description, image, status);
                    farmList.add(dto);
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
        return farmList;
    }
    
    
    public List<FarmDTO> getAllFarm(int index) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<FarmDTO> result = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT FarmID, FarmName, Location, Description, Image, Status "
                        + "FROM FARM ";
                // Add pagination
                sql += "ORDER BY FarmID \n"
                        + "OFFSET ? ROWS \n"
                        + "FETCH NEXT 8 ROWS ONLY;";

                stm = con.prepareStatement(sql);
                stm.setInt(1, (index - 1) * 8);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int farmID = rs.getInt("FarmID");
                    String farmName = rs.getString("FarmName");
                    String location = rs.getString("Location");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    boolean status = rs.getBoolean("Status");
                    
                    FarmDTO dto = new FarmDTO(farmID, farmName, location, description, image, status);
                    
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
    
    public int getNumberPageInManagePage() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int countPage = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(*) "
                        + "FROM FARM ";
                stm = conn.prepareStatement(sql);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    int total = rs.getInt(1);
                    countPage = total / 8;

                    if (total % 8 != 0) {
                        countPage++;
                    }

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return countPage;
    }
    
    public List<FarmDTO> searchFarmName(String searchValue, int index) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<FarmDTO> result = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT FarmID, FarmName, Location, Description, Image, Status "
                        + "FROM FARM ";

                // Xử lý điều kiện tìm kiếm theo TourName và TourID
                boolean hasSearchValue = searchValue != null && !searchValue.trim().isEmpty();
                boolean isNumeric = hasSearchValue && searchValue.matches("\\d+");

                if (hasSearchValue) {
                    sql += "WHERE FarmName LIKE ? "; // Điều kiện tìm kiếm theo tên tour
                    if (isNumeric) {
                        sql += "OR FarmID = ? "; // Điều kiện tìm kiếm theo ID nếu searchValue là số
                    }
                }

                // Add pagination
                sql += "ORDER BY FarmID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";
                stm = con.prepareStatement(sql);

                int paramIndex = 1;
                if (hasSearchValue) {
                    stm.setString(paramIndex++, "%" + searchValue + "%"); // Tìm kiếm theo tên tour
                    if (isNumeric) {
                        stm.setInt(paramIndex++, Integer.parseInt(searchValue)); // Tìm kiếm theo ID nếu searchValue là số
                    }
                }

                // Set index for pagination
                stm.setInt(paramIndex, (index - 1) * 5);

                rs = stm.executeQuery();
                while (rs.next()) {

                    int farmID = rs.getInt("FarmID");
                    String farmName = rs.getString("FarmName");
                    String location = rs.getString("Location");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");
                    boolean status = rs.getBoolean("Status");
                    
                    FarmDTO dto = new FarmDTO(farmID, farmName, location, description, image, status);
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
    
    
    public int getNumberPageInSearchPage(String searchValue) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int countPage = 0;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                // Khởi tạo câu SQL đếm tổng số tour theo điều kiện tìm kiếm
                String sql = "SELECT COUNT(*) FROM FARM ";

                // Kiểm tra điều kiện tìm kiếm
                boolean hasSearchValue = searchValue != null && !searchValue.trim().isEmpty();
                boolean isNumeric = hasSearchValue && searchValue.matches("\\d+");

                // Nếu có từ khóa tìm kiếm
                if (hasSearchValue) {
                    sql += "WHERE FarmName LIKE ? "; // Điều kiện tìm kiếm theo tên tour
                    if (isNumeric) {
                        sql += "OR FarmID = ? "; // Điều kiện tìm kiếm theo ID nếu searchValue là số
                    }
                }

                stm = con.prepareStatement(sql);

                // Thiết lập tham số cho PreparedStatement
                int paramIndex = 1;
                if (hasSearchValue) {
                    stm.setString(paramIndex++, "%" + searchValue + "%"); // Tìm kiếm theo tên tour
                    if (isNumeric) {
                        stm.setInt(paramIndex++, Integer.parseInt(searchValue)); // Tìm kiếm theo ID nếu searchValue là số
                    }
                }

                // Thực hiện câu truy vấn
                rs = stm.executeQuery();
                if (rs.next()) {
                    int total = rs.getInt(1); // Tổng số bản ghi
                    countPage = total / 5;

                    // Nếu không chia hết cho 5, thì tăng thêm 1 trang
                    if (total % 5 != 0) {
                        countPage++;
                    }
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
        return countPage; // Trả về số trang
    }

}
