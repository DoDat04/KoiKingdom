/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.employees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import koikd.utils.DBUtils;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Minhngo
 */
public class EmployeesDAO {

    /**
     * Check if the user account is already in the database
     *
     * @param email
     * @param password
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public EmployeesDTO checkLoginDelivery(String email, String password) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        EmployeesDTO result = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [EmployeeID], [Email], [Password], [Role], [LastName], [FirstName], [Address], [Status]\n"
                        + "FROM DBO.EMPLOYEE\n"
                        + "WHERE Email = ? and Status = 1;";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String hashedPassword = rs.getString("Password");
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        int id = rs.getInt("EmployeeID");
                        String role = rs.getString("role");
                        String lastName = rs.getString("LastName");
                        String firstName = rs.getString("FirstName");
                        String address = rs.getString("Address");
                        boolean status = rs.getBoolean("Status");
                        result = new EmployeesDTO(id, email, "", role, lastName, firstName, address, status);

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
        return result;
    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        String email = "sale@gmail.com";
//        String password = "123";
//        EmployeesDAO dao = new EmployeesDAO();
//        EmployeesDTO dto = dao.checkLoginDelivery(email, password);
//        if (dto != null) {
//            System.out.println(dto);
//        } else {
//            System.out.println("Fail");
//        }
//    }
    /**
     * Get User Information
     *
     * @param email
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public EmployeesDTO getProfile(String email) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        EmployeesDTO result = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [EmployeeID], [Email], [Password], [Role], [LastName], [FirstName], [Address], [Status]\n"
                        + "FROM DBO.EMPLOYEE\n"
                        + "WHERE Email = ? and Status = 1;";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("EmployeeID");
                    String role = rs.getString("role");
                    String lastName = rs.getString("LastName");
                    String firstName = rs.getString("FirstName");
                    String address = rs.getString("Address");
                    boolean status = rs.getBoolean("Status");
                    result = new EmployeesDTO(id, email, "", role, lastName, firstName, address, status);
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
     * Update information of delivery
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param address
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean updateDeliveryProfile(String firstName, String lastName, String email, String address) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                // Câu lệnh SQL sửa lại từ 'SUPDATE' thành 'UPDATE'
                String sql = "UPDATE DBO.EMPLOYEE "
                        + "SET firstname = ?, lastname = ?, address = ? "
                        + "WHERE Email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, firstName);
                stm.setString(2, lastName);
                stm.setString(3, address);
                stm.setString(4, email);  // Đặt giá trị cho email

                int rowsUpdated = stm.executeUpdate();
                result = rowsUpdated > 0;  // Kiểm tra nếu có hàng nào được cập nhật
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;  // Trả về kết quả
    }

    public static void main(String[] args) {
        try {
            // Tạo đối tượng DAO (Data Access Object)
            EmployeesDAO deliveryDAO = new EmployeesDAO();

            // Thông tin cần cập nhật
            String firstName = "John";
            String lastName = "Doe";
            String email = "sale@gmail.com";
            String address = "123 Main Street, New York, NY";

            // Gọi phương thức updateDeliveryProfile và kiểm tra kết quả
            boolean isUpdated = deliveryDAO.updateDeliveryProfile(firstName, lastName, email, address);

            if (isUpdated) {
                System.out.println("Profile updated successfully!");
            } else {
                System.out.println("Profile update failed. Please check the email address.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // In lỗi ra console để kiểm tra
        }
    }

    public List<EmployeesDTO> getAllEmployees(int index) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<EmployeesDTO> result = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT EmployeeID, Email, Role, LastName, FirstName, Address, Status "
                        + "FROM EMPLOYEE ";
                // Add pagination
                sql += "ORDER BY EmployeeID \n"
                        + "OFFSET ? ROWS \n"
                        + "FETCH NEXT 5 ROWS ONLY;";
                stm = con.prepareStatement(sql);
                stm.setInt(1, (index - 1) * 5);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("EmployeeID");
                    String email = rs.getString("Email");
                    String role = rs.getString("Role");
                    String lastName = rs.getString("LastName");
                    String firstName = rs.getString("FirstName");
                    String address = rs.getString("Address");
                    boolean status = rs.getBoolean("Status");
                    EmployeesDTO dto = new EmployeesDTO(id, email, role, lastName, firstName, address, status);
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
                        + "FROM EMPLOYEE ";
                stm = conn.prepareStatement(sql);

                rs = stm.executeQuery();
                if (rs.next()) {
                    int total = rs.getInt(1);
                    countPage = total / 5;

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
            if (conn != null) {
                conn.close();
            }
        }
        return countPage;
    }

    /**
     *
     * @param id
     * @return employee status.
     * @throws SQLException
     */
    public boolean updateStatusEmployee(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String selectSql = "SELECT [Status] FROM [dbo].[EMPLOYEE] "
                        + "WHERE [EmployeeID] = ?";
                pst = conn.prepareStatement(selectSql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    boolean currentStatus = rs.getBoolean("Status");

                    String updateSql = "UPDATE [dbo].[EMPLOYEE] SET [STATUS] = ? WHERE [EmployeeID] = ?";
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
    
    public List<EmployeesDTO> searchEmployees(String searchValue) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<EmployeesDTO> result = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT EmployeeID, Email, Role, LastName, FirstName, Address, Status "
                        + "FROM EMPLOYEE "
                        + "WHERE LastName LIKE ? OR FirstName LIKE ? ";
               boolean isNumeric = searchValue.matches("\\d+");
                if (isNumeric) {
                    sql += "OR EmployeeID = ? ";
                }
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, "%" + searchValue + "%");

                if (isNumeric) {
                    stm.setInt(3, Integer.parseInt(searchValue));
                }
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("EmployeeID");
                    String email = rs.getString("Email");
                    String role = rs.getString("Role");
                    String lastName = rs.getString("LastName");
                    String firstName = rs.getString("FirstName");
                    String address = rs.getString("Address");
                    boolean status = rs.getBoolean("Status");
                    EmployeesDTO dto = new EmployeesDTO(id, email, role, lastName, firstName, address, status);
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
    
    public boolean addEmployee(String email, String password, String role, String lastName,
            String firstName, String address, boolean status) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO EMPLOYEE (Email, Password, Role, LastName, FirstName, Address, Status) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                stm.setString(3, role);
                stm.setString(4, lastName);
                stm.setString(5, firstName);
                stm.setString(6, address);
                stm.setBoolean(7, true); 
                int affectedRows = stm.executeUpdate();
                if (affectedRows > 0) {
                    result = true;
                }

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public EmployeesDTO getEmployeeByEmail(String email) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        EmployeesDTO result = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT Email, Password, Role, LastName, FirstName, Address, Status "
                        + "FROM EMPLOYEE "
                        + "WHERE Email = ? AND Status = 1";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();

                if (rs.next()) {
                    String password = rs.getString("Password");
                    String role = rs.getString("Role");
                    String lastName = rs.getString("LastName");
                    String firstName = rs.getString("FirstName");
                    String address = rs.getString("Address");
                    boolean status = rs.getBoolean("Status");
                    result = new EmployeesDTO(email, password, role, lastName, firstName, address, status);
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
//    public static void main(String[] args) throws SQLException {
//        int id = 1;
//        EmployeesDAO dao = new EmployeesDAO();
//        boolean dto = dao.updateStatusEmployee(id);
//        if (dto) {
//            System.out.println("Update status successfully.");
//        } else {
//            System.out.println("Fail.");
//        }
//    }
}
