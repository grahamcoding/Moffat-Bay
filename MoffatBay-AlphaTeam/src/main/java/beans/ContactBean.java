/* Alpha Team
 * Created by: Reed Bunnell
 */

package beans;

import db.DBConnection;
import java.sql.*;

public class ContactBean {

    private String name;
    private String email;
    private String message;

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setMessage(String message) { this.message = message; }

    public boolean saveMessage() {

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO contact_us (name, email, message) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, message);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
