/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.employees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import koikd.utils.DBUtils;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Admin
 */
public class DeliveryDAO {

    public boolean isDeliveryRole(String role) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean isDelivery = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT Role "
                        + "FROM DBO.EMPLOYEE "
                        + "WHERE Role = 'Delivery'";
                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();
                if (rs.next()) {
                    isDelivery = true;
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
        return isDelivery;
    }

    public EmployeesDTO checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        EmployeesDTO result = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [EmployeeID], [Email], [Password], [Role], [LastName], [FirstName], [Address], [Status] "
                        + "FROM DBO.EMPLOYEE "
                        + "WHERE Email = ? and Status = 1;";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String hashedPassword = rs.getString("Password");
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        String role = rs.getString("role");
                        String lastName = rs.getString("LastName");
                        String firstName = rs.getString("FirstName");
                        String address = rs.getString("Address");
                        boolean status = rs.getBoolean("Status");
                        result = new EmployeesDTO(email, "", role, lastName, firstName, address, status);
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
    
}
