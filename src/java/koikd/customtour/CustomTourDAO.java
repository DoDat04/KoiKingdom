package koikd.customtour;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import koikd.utils.DBUtils;

/**
 *
 * @author ADMIN LAM
 */
public class CustomTourDAO implements Serializable {

    public boolean createCustomTourFromCustomer(CustomTourDTO customTourDTO) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        boolean resultSet = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO [dbo].[CUSTOMTOURREQUEST] "
                        + "([Duration], [StartDate], [EndDate], [Status], [ManagerApprovalStatus], [DepartureLocation], [FarmName], [KoiTypeName], [Quantity]) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                pst = conn.prepareStatement(sql);

                pst.setString(1, customTourDTO.getDuration());  
                pst.setTimestamp(2, new java.sql.Timestamp(customTourDTO.getStartDate().getTime()));
                pst.setTimestamp(3, new java.sql.Timestamp(customTourDTO.getEndDate().getTime())); 
                pst.setString(4, customTourDTO.getStatus()); 
                pst.setString(5, customTourDTO.getManagerApprovalStatus()); 
                pst.setString(6, customTourDTO.getDepartureLocation()); 
                pst.setString(7, customTourDTO.getFarmName());
                pst.setString(8, customTourDTO.getKoiTypeName());
                pst.setInt(9, customTourDTO.getQuantity());

                int affectedRows = pst.executeUpdate();
                if (affectedRows > 0) {
                    resultSet = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error while creating custom tour: " + e.getMessage());
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return resultSet;
    }

    public static void main(String[] args) {
        CustomTourDTO customTourDTO = new CustomTourDTO();
        
        // Thiết lập thông tin cho CustomTourDTO
        customTourDTO.setDuration("5 Days 4 Nights");
        
        Calendar cal = Calendar.getInstance();
        
        // Thiết lập ngày bắt đầu
        cal.set(2024, Calendar.OCTOBER, 15);
        Date startDate = cal.getTime();
        customTourDTO.setStartDate(startDate);
        
        // Thiết lập ngày kết thúc
        cal.set(2024, Calendar.OCTOBER, 20);
        Date endDate = cal.getTime();
        customTourDTO.setEndDate(endDate);
        
        // Thiết lập các thông tin khác
        customTourDTO.setStatus("Pending");
        customTourDTO.setManagerApprovalStatus("Pending");
        customTourDTO.setDepartureLocation("Tokyo, Japan");
        customTourDTO.setFarmName("Koi Farm A");
        customTourDTO.setKoiTypeName("Tancho");
        customTourDTO.setQuantity(10); 

        // Tạo đối tượng CustomTourDAO để gọi phương thức tạo tour
        CustomTourDAO customTourDAO = new CustomTourDAO();

        try {
            // Gọi phương thức tạo custom tour
            boolean isCreated = customTourDAO.createCustomTourFromCustomer(customTourDTO);

            // Kiểm tra kết quả
            if (isCreated) {
                System.out.println("Custom tour was created successfully!");
            } else {
                System.out.println("Failed to create custom tour.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
