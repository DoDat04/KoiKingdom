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
//        String email = "lamtranmanager@gmail.com";
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
                        + "WHERE Email = ? "
                        + "AND Role = 'Delivery';";  // Kiểm tra vai trò là 'Delivery'
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

    public List<EmployeesDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<EmployeesDTO> result = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT EmployeeID, Email, Role, LastName, FirstName, Address, Status "
                        + "FROM EMPLOYEE ";
                stm = con.prepareStatement(sql);
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
}
