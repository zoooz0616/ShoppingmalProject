package src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import src.dao.IAdminDAO;
import src.vo.AdminVO;
import src.vo.AppleStoreDataSource;

public class AdminDAO implements IAdminDAO {
	@Override
	public int selectAdmin(String adminId, String adminPassword) {
		String sql = "select count(*) from admin where admin_id=? and admin_password=?";
		int rowCount = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = AppleStoreDataSource.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, adminId);
			pstmt.setString(2, adminPassword);
			rs = pstmt.executeQuery();
			rs.next();
			rowCount = rs.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			AppleStoreDataSource.closeConnection(connection);
		}
		return rowCount;
	}
}