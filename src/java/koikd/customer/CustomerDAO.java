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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import koikd.google.GooglePojo;
import koikd.utils.DBUtils;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Do Dat
 */
public class CustomerDAO implements Serializable {

    public List<CustomerDTO> getAllCustomers(int index) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<CustomerDTO> customerList = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT CustomerID, Email, LastName, FirstName, Address, AccountType, Status "
                        + "FROM CUSTOMER ";
                // Add pagination
                sql += "ORDER BY CustomerID \n"
                        + "OFFSET ? ROWS \n"
                        + "FETCH NEXT 5 ROWS ONLY;";
                stm = con.prepareStatement(sql);
                stm.setInt(1, (index - 1) * 5);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int customerID = rs.getInt("CustomerID");
                    String email = rs.getString("Email");
                    String lastName = rs.getString("LastName");
                    String firstName = rs.getString("FirstName");
                    String address = rs.getString("Address");
                    String accountType = rs.getString("AccountType");
                    boolean status = rs.getBoolean("Status");

                    CustomerDTO customer = new CustomerDTO(customerID, email, "", lastName, firstName, address, accountType, status);
                    customerList.add(customer);
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
        return customerList;
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
                        + "FROM CUSTOMER ";
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

    public List<CustomerDTO> searchCustomerName(String searchValue, int index) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<CustomerDTO> customerList = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT CustomerID, Email, LastName, FirstName, Address, AccountType, Status "
                        + "FROM CUSTOMER ";
                // Xử lý điều kiện tìm kiếm theo TourName và TourID
                boolean hasSearchValue = searchValue != null && !searchValue.trim().isEmpty();
                boolean isNumeric = hasSearchValue && searchValue.matches("\\d+");

                if (hasSearchValue) {
                    sql += "WHERE LastName LIKE ? OR FirstName LIKE ? "; // Điều kiện tìm kiếm theo tên customer
                    if (isNumeric) {
                        sql += "OR CustomerID = ? "; // Điều kiện tìm kiếm theo ID nếu searchValue là số
                    }
                }
                // Add pagination
                sql += "ORDER BY CustomerID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";
                stm = con.prepareStatement(sql);
                int paramIndex = 1;
                if (hasSearchValue) {
                    stm.setString(paramIndex++, "%" + searchValue + "%"); // Tìm kiếm theo tên tour
                    stm.setString(paramIndex++, "%" + searchValue + "%"); // Tìm kiếm theo tên tour

                    if (isNumeric) {
                        stm.setInt(paramIndex++, Integer.parseInt(searchValue)); // Tìm kiếm theo ID nếu searchValue là số
                    }
                }

                // Set index for pagination
                stm.setInt(paramIndex, (index - 1) * 5);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int customerID = rs.getInt("CustomerID");
                    String email = rs.getString("Email");
                    String lastName = rs.getString("LastName");
                    String firstName = rs.getString("FirstName");
                    String address = rs.getString("Address");
                    String accountType = rs.getString("AccountType");
                    boolean status = rs.getBoolean("Status");

                    CustomerDTO customer = new CustomerDTO(customerID, email, "", lastName, firstName, address, accountType, status);
                    customerList.add(customer);
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
        return customerList;
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
                String sql = "SELECT COUNT(*) FROM CUSTOMER ";

                // Kiểm tra điều kiện tìm kiếm
                boolean hasSearchValue = searchValue != null && !searchValue.trim().isEmpty();
                boolean isNumeric = hasSearchValue && searchValue.matches("\\d+");

                // Nếu có từ khóa tìm kiếm
                if (hasSearchValue) {
                    sql += "WHERE LastName LIKE ? OR FirstName LIKE ? "; // Điều kiện tìm kiếm theo tên tour
                    if (isNumeric) {
                        sql += "OR CustomerID = ? "; // Điều kiện tìm kiếm theo ID nếu searchValue là số
                    }
                }

                stm = con.prepareStatement(sql);

                // Thiết lập tham số cho PreparedStatement
                int paramIndex = 1;
                if (hasSearchValue) {
                    stm.setString(paramIndex++, "%" + searchValue + "%"); // Tìm kiếm theo tên tour
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
                        + "WHERE LOWER(Email) = ? AND AccountType = 'default' ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();

                if (rs.next()) {
                    boolean status = rs.getBoolean("Status"); 

                    if (!status) {
                        return new CustomerDTO(0, email, "", "", "", "", "default", false);
                    }

                    String hashedPassword = rs.getString("Password");
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        int customerId = rs.getInt("CustomerID");
                        String lastName = rs.getString("LastName");
                        String firstName = rs.getString("FirstName");
                        String address = rs.getString("Address");
                        String accountType = rs.getString("AccountType");

                        result = new CustomerDTO(customerId, email, password, lastName, firstName, address, accountType, true);
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
                String sql = "SELECT LOWER(Email) "
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
                String sql = "SELECT Email, Password, LastName, FirstName, Address, AccountType, Status "
                        + "FROM CUSTOMER "
                        + "WHERE Email = ? AND Status = 1";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();

                if (rs.next()) {
                    String password = rs.getString("Password");
                    String lastName = rs.getString("LastName");
                    String firstName = rs.getString("FirstName");
                    String address = rs.getString("Address");
                    String accountType = rs.getString("AccountType");
                    boolean status = rs.getBoolean("Status");
                    result = new CustomerDTO(email, password, lastName, firstName, address, accountType, status);
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

    public boolean updatePassword(String email, String newPassword) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE CUSTOMER SET Password = ? WHERE Email = ? AND Status = 1";
                stm = con.prepareStatement(sql);
                stm.setString(1, newPassword);
                stm.setString(2, email);

                int rowsUpdated = stm.executeUpdate();
                if (rowsUpdated > 0) {
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

    public boolean updateUserProfile(String userId, String firstName, String lastName, String address, String accountType) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE CUSTOMER "
                        + "SET FirstName = ?, Lastname = ?, Address = ? "
                        + "WHERE Email = ? "
                        + "AND AccountType = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, firstName);
                stm.setString(2, lastName);
                stm.setString(3, address);
                stm.setString(4, userId);
                stm.setString(5, accountType); // Thêm accountType vào điều kiện

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
                String sql = "SELECT customerID, Email, FirstName, LastName, AccountType, Address, Status "
                        + "FROM CUSTOMER "
                        + "WHERE Email = ? "
                        + "AND AccountType = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, accountType);

                rs = stm.executeQuery();
                if (rs.next()) {
                    int customerID = rs.getInt("CustomerID");
                    String customerEmail = rs.getString("Email");
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String accType = rs.getString("AccountType");
                    String address = rs.getString("Address");
                    boolean status = rs.getBoolean("Status");

                    customer = new CustomerDTO(customerID, customerEmail, "", lastName, firstName, address, accType, status);
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

    /**
     *
     * @param id
     * @return update status
     * @throws SQLException
     */
    public boolean updateStatusCustomer(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String selectSql = "SELECT [Status] FROM [dbo].[CUSTOMER] "
                        + "WHERE [CustomerID] = ?";
                pst = conn.prepareStatement(selectSql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    boolean currentStatus = rs.getBoolean("Status");

                    String updateSql = "UPDATE [dbo].[CUSTOMER] SET [STATUS] = ? WHERE [CustomerID] = ?";
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

    public CustomerDTO getCustomerByCustomerID(int customerId) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        CustomerDTO result = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT CustomerID, Email, LastName, FirstName, Address, AccountType, Status "
                        + "FROM CUSTOMER "
                        + "WHERE CustomerID = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, customerId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int customerID = rs.getInt("CustomerID");
                    String email = rs.getString("Email");
                    String lastName = rs.getString("LastName");
                    String firstName = rs.getString("FirstName");
                    String address = rs.getString("Address");
                    String accountType = rs.getString("AccountType");
                    boolean status = rs.getBoolean("Status");
                    // Fixed constructor call
                    result = new CustomerDTO(customerID, email, lastName, lastName, firstName, address, accountType, status);
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
        return result;
    }

//    public static void main(String[] args) throws SQLException {
//        int id = 1;
//        CustomerDAO dao = new CustomerDAO();
//        boolean update = dao.updateStatusCustomer(id);
//        if(update){
//            System.out.println("Update status successfully");
//        }
//    }
}
