import java.sql.*;
import java.util.*;

public class Mysql_test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/";
			Connection con = DriverManager.getConnection(url, "root", "feuerwehr1992");
			
			Statement stmt;
			stmt = con.createStatement();
			
			ResultSet rs;
			rs = stmt.executeQuery("show databases");
			
			System.out.println("Datenbanken:");
			while(rs.next()){
				System.out.println("\t" +rs.getString("Database"));
			}
		} catch (SQLException e) {
			System.out.println("MySQL Error: " +e);
		} catch (ClassNotFoundException e) {
			System.out.println("Cant't load JDBC Connector!\n" +e);
		}
	}

}
