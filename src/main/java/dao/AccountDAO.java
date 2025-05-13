package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;

public class AccountDAO {
	
	private final String DB_URL = "jdbc:mysql://localhost:3306/reviewbox";
	private final String DB_USER = "root";
	private final String DB_PASS = "password1234";
	
	public boolean accountCheck(Account a) {
		// 登録されているか確認し登録されていたらアカウントIDを取得する
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライブを読み込めませんでした");
		}
		String sql = "SELECT name, pass, userId FROM account WHERE name=? AND pass=?";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, a.getName());
			pstmt.setString(2, a.getPass());
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				a.setAccountId(rs.getInt("userId"));
				return true;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean accountRegister(Account account) {
		// アカウントを登録する
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライブを読み込めませんでした");
		}
		String sql = "INSERT INTO account(name, pass) VALUES(?, ?)";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, account.getName());
			pstmt.setString(2, account.getPass());
			
			int result = pstmt.executeUpdate();
			if (result > 0) {
				// 登録成功したのでIdを取得する
				return accountCheck(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean accountDelet(Account a) {
		// アカウントを削除する
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライブを読み込めませんでした");
		}
		String sql = "DELETE FROM account WHERE name=? AND pass=?";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, a.getName());
			pstmt.setString(2, a.getPass());
			
			int result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
