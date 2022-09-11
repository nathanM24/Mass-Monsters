import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Bridge {
	private String serverLocation = "jdbc:mysql://localhost:3306/workoutplanner";
	private String username = "root";
	private String password = "Scoast1362$#";

	public Bridge(String serverLocation, String username, String password) {
		this.serverLocation = serverLocation;
		this.username = username;
		this.password = password;
	}

	public Bridge() {
		
	}
	
	// Does select statements
	public String SQLstatementSelect(String input, String a) throws SQLException {
		ResultSet temp = null;
		String value = "";
		try (Connection c = DriverManager.getConnection(serverLocation, username, password); Statement s = c.createStatement();) {
			temp = s.executeQuery(input);
			while (temp.next()) {
				value = temp.getString(a);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return value;
	}

	// Does update, delete, insert statements
	public void SQLstatementUpdate(String input) {
		try (Connection c = DriverManager.getConnection(serverLocation, username, password); Statement s = c.createStatement();) {
			s.executeUpdate(input);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public int SQLstatementExists(String input) throws SQLException {
		ResultSet temp = null;
		int value = 0;
		try (Connection c = DriverManager.getConnection(serverLocation, username, password); Statement s = c.createStatement();) {
			temp = s.executeQuery(input);
			while (temp.next()) {
				value = temp.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return value;
	}
}