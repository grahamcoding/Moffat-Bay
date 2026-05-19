/* Alpha Team
 * Created by: Stephanie Lara
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import beans.UserBean;
import db.DBConnection;

public class UserDAO {

    public boolean registerUser(UserBean user) {

        String sql = "INSERT INTO customer (first_name, last_name, email, phone_number, password_hash) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // HASH PASSWORD
	        	// BCrypt password hashing from:
	        	// https://www.baeldung.com/java-password-hashing
	        	// https://mvnrepository.com/artifact/org.mindrot/jbcrypt
            String hashedPassword = BCrypt.hashpw(user.getPasswordHash(), BCrypt.gensalt());

            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhoneNumber());
            pstmt.setString(5, hashedPassword);

            int rowsInserted = pstmt.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {

            throw new RuntimeException("DB ERROR: " + e.getMessage(), e);
        }
    }

    public boolean validateUser(String email, String password) {

        String sql = "SELECT password_hash FROM customer WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                String storedHash = rs.getString("password_hash");

                return BCrypt.checkpw(password, storedHash);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }
}