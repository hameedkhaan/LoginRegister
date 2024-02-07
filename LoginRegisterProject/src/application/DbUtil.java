package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	
	public Connection getConnection() {
		
		Connection conn;
		String url = "jdbc:mysql://localhost:3306/db";
		String username = "root";
		String password = "";
		
		try {
			conn = DriverManager.getConnection(url,username,password);
			return conn;
			
		}catch(Exception e) {
			System.out.println("Error connecting to database " + e.getMessage());
			return null;
		}
		
	}

}
