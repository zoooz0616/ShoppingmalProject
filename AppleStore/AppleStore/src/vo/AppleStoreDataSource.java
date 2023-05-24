package src.vo;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class AppleStoreDataSource {

	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "hr";
	private static final String PASSWORD = "hr";
	
	private static BasicDataSource dataSource;
	
	static {
		try {
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(DRIVER);            
			dataSource.setUrl(URL);
			dataSource.setUsername(USERNAME);                                  
			dataSource.setPassword(PASSWORD);  
			dataSource.setInitialSize(10);
			dataSource.setMaxTotal(10);
			System.out.println("DataSource created");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static void closeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close();
			}catch(Exception e) {}
		}
	}

}