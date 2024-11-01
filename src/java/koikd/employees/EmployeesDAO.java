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

//    public static void main(String[] args) {
//        try {
//            // Tạo đối tượng DAO (Data Access Object)
//            EmployeesDAO deliveryDAO = new EmployeesDAO();
//
//            // Thông tin cần cập nhật
//            String firstName = "John";
//            String lastName = "Doe";
//            String email = "sale@gmail.com";
//            String address = "123 Main Street, New York, NY";
//
//            // Gọi phương thức updateDeliveryProfile và kiểm tra kết quả
//            boolean isUpdated = deliveryDAO.updateDeliveryProfile(firstName, lastName, email, address);
//
//            if (isUpdated) {
//                System.out.println("Profile updated successfully!");
//            } else {
//                System.out.println("Profile update failed. Please check the email address.");
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace(); // In lỗi ra console để kiểm tra
//        }
//    }
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

    public List<EmployeesDTO> searchEmployees(String searchValue, int index) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<EmployeesDTO> result = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT EmployeeID, Email, Role, LastName, FirstName, Address, Status "
                        + "FROM EMPLOYEE ";

                // Xử lý điều kiện tìm kiếm theo TourName và TourID
                boolean hasSearchValue = searchValue != null && !searchValue.trim().isEmpty();
                boolean isNumeric = hasSearchValue && searchValue.matches("\\d+");

                if (hasSearchValue) {
                    sql += "WHERE LastName LIKE ? OR FirstName LIKE ? "; // Điều kiện tìm kiếm theo tên customer
                    if (isNumeric) {
                        sql += "OR EmployeeID = ? "; // Điều kiện tìm kiếm theo ID nếu searchValue là số
                    }
                }
                // Add pagination
                sql += "ORDER BY EmployeeID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";
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

    public int getNumberPageInSearchPage(String searchValue) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int countPage = 0;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                // Khởi tạo câu SQL đếm tổng số tour theo điều kiện tìm kiếm
                String sql = "SELECT COUNT(*) FROM EMPLOYEE ";

                // Kiểm tra điều kiện tìm kiếm
                boolean hasSearchValue = searchValue != null && !searchValue.trim().isEmpty();
                boolean isNumeric = hasSearchValue && searchValue.matches("\\d+");

                // Nếu có từ khóa tìm kiếm
                if (hasSearchValue) {
                    sql += "WHERE LastName LIKE ? OR FirstName LIKE ? "; // Điều kiện tìm kiếm theo tên tour
                    if (isNumeric) {
                        sql += "OR EmployeeID = ? "; // Điều kiện tìm kiếm theo ID nếu searchValue là số
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
    
    
    
    public List<EmployeesDTO> getAllConsulting() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<EmployeesDTO> list = new ArrayList<>();

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT EmployeeID, Email, Role, LastName, FirstName, Address, Status "
                        + "FROM EMPLOYEE "
                        + "WHERE Role = 'Consulting' ";
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
                    EmployeesDTO result = new EmployeesDTO(id, email, role, lastName, firstName, address, status);
                    list.add(result);
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
        return list;
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
