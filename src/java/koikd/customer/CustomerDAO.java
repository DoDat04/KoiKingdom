/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.customer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import koikd.google.GooglePojo;
import koikd.utils.DBUtils;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Do Dat
 */
public class CustomerDAO implements Serializable {

    public CustomerDTO checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CustomerDTO result = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT CustomerID, Password, LastName, FirstName, Address, AccountType, Status "
                        + "FROM CUSTOMER "
                        + "WHERE Email = ? and Status = 1 ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String hashedPassword = rs.getString("Password");
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        String lastName = rs.getString("LastName");
                        String firstName = rs.getString("FirstName");
                        String address = rs.getString("Address");
                        String accountType = rs.getString("AccountType");
                        boolean status = rs.getBoolean("Status");
                        result = new CustomerDTO(email, "", lastName, firstName, address, accountType, status);
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

    public boolean createCustomerAccount(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO CUSTOMER("
                        + "Email, Password, LastName, FirstName, Address, AccountType, Status"
                        + ") VALUES("
                        + "?,?,?,?,?,?,?"
                        + ")";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getEmail());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getLastName());
                stm.setString(4, dto.getFirstName());
                stm.setString(5, dto.getAddress());
                stm.setString(6, dto.getAccountType());
                stm.setBoolean(7, dto.isStatus());
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

    public boolean checkEmailExist(String email) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean exist = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT Email "
                        + "FROM CUSTOMER "
                        + "WHERE Email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    exist = true;
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
        return exist;
    }

    public String generateOTP() {
        Random random = new Random();
        int otp = random.nextInt(1000000);
        return String.format("%06d", otp);
    }

    public boolean resetPassword(String email, String passReset) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean success = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String hashedPassword = BCrypt.hashpw(passReset, BCrypt.gensalt());

                String sql = "UPDATE CUSTOMER "
                        + "SET password = ? "
                        + "WHERE Email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, hashedPassword);
                stm.setString(2, email);
                int rowsUpdated = stm.executeUpdate();
                if (rowsUpdated > 0) {
                    success = true;
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
        return success;
    }

    public CustomerDTO getUserByEmail(String email) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CustomerDTO result = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT Email, LastName, FirstName, Address, AccountType, Status "
                        + "FROM CUSTOMER "
                        + "WHERE Email = ? AND Status = 1";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();

                if (rs.next()) {
                    String lastName = rs.getString("LastName");
                    String firstName = rs.getString("FirstName");
                    String address = rs.getString("Address");
                    String accountType = rs.getString("AccountType");
                    boolean status = rs.getBoolean("Status");
                    result = new CustomerDTO(email, "", lastName, firstName, address, accountType, status);
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

    public boolean createEmailUser(GooglePojo googlePojo, String accessToken) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                // Check if the user already exists
                String checkSql = "SELECT Email FROM CUSTOMER "
                        + "WHERE Email = ? "
                        + "AND AccountType = 'google'";
                stm = con.prepareStatement(checkSql);
                stm.setString(1, googlePojo.getEmail());
                rs = stm.executeQuery();

                if (!rs.next()) { // Only insert if user does not exist
                    String sql = "INSERT INTO CUSTOMER (Email, Password, Lastname, Firstname, Address, AccountType, Status) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, googlePojo.getEmail());
                    stm.setString(2, hashToken(accessToken));
                    stm.setString(3, googlePojo.getGiven_name());
                    stm.setString(4, googlePojo.getFamily_name());
                    stm.setString(5, "");
                    stm.setString(6, "google");
                    stm.setBoolean(7, true);
                    int rowsInserted = stm.executeUpdate();
                    result = rowsInserted > 0;
                } else {
                    result = true;
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

    private String hashToken(String token) {
        return BCrypt.hashpw(token, BCrypt.gensalt());
    }

    public boolean updateUserProfile(String userId, String firstName, String lastName, String accountType) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE CUSTOMER SET FirstName = ?, Lastname = ? WHERE Email = ? AND AccountType = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, firstName);
                stm.setString(2, lastName);
                stm.setString(3, userId);
                stm.setString(4, accountType); // Thêm accountType vào điều kiện

                int rowsUpdated = stm.executeUpdate();
                result = rowsUpdated > 0;
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

    public CustomerDTO findCustomerByEmailAndAccountType(String email, String accountType) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CustomerDTO customer = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                // Cập nhật truy vấn SQL để lấy lastName và firstName
                String sql = "SELECT Email, FirstName, LastName, AccountType, Status FROM CUSTOMER WHERE Email = ? AND AccountType = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, accountType);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String customerEmail = rs.getString("Email");
                    String firstName = rs.getString("FirstName"); 
                    String lastName = rs.getString("LastName");   
                    String accType = rs.getString("AccountType");
                    boolean status = rs.getBoolean("Status");

                    // Cập nhật CustomerDTO để chứa firstName và lastName
                    customer = new CustomerDTO(customerEmail, "", lastName, firstName, "", accType, status);
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
        return customer;
    }
}
