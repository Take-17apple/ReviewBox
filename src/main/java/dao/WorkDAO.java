package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Work;

public class WorkDAO {
	
	private final String DB_URL = "jdbc:mysql://localhost:3306/reviewbox";
	private final String DB_USER = "root";
	private final String DB_PASS = "password1234";
	
	public List<Work> searchList(String search) {
		// 引数の文字を含む作品を探して取得する
		List<Work> searchWorkList = new ArrayList<Work>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライブを読み込めませんでした");
		}
		String sql = "SELECT * FROM work WHERE title LIKE ? OR creator LIKE ? OR price LIKE ?";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, "%" + search + "%");
			pstmt.setString(3, "%" + search + "%");
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int workId = rs.getInt("workId");
				String title = rs.getString("title");
				String creator = rs.getString("creator");
				String genre = rs.getString("genre");
				String type = rs.getString("type");
				int price = rs.getInt("price");
				String plot = rs.getString("plot");
				int rating = rs.getInt("rating");
				int reviewerId = rs.getInt("reviewerId");
				Work work = new Work(workId, title, creator, genre, type, price, plot, rating, reviewerId);
				searchWorkList.add(work);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchWorkList;
	}
	public List<Work> workList() {
		// 全作品を取得する
		List<Work> allWorkList = new ArrayList<Work>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライブを読み込めませんでした");
		}
		String sql = "SELECT * FROM work";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int workId = rs.getInt("workId");
				String title = rs.getString("title");
				String creator = rs.getString("creator");
				String genre = rs.getString("genre");
				String type = rs.getString("type");
				int price = rs.getInt("price");
				String plot = rs.getString("plot");
				int rating = rs.getInt("rating");
				int reviewerId = rs.getInt("reviewerId");
				Work work = new Work(workId, title, creator, genre, type, price, plot, rating, reviewerId);
				allWorkList.add(work);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allWorkList;
	}
	public List<Work> myList(int accountId) {
		List<Work> myWorkList = new ArrayList<Work>();
		// ログインユーザーの作品を取得する
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライブを読み込めませんでした");
		}
		String sql = "SELECT * FROM work WHERE reviewerId = ?";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, accountId);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int workId = rs.getInt("workId");
				String title = rs.getString("title");
				String creator = rs.getString("creator");
				String genre = rs.getString("genre");
				String type = rs.getString("type");
				int price = rs.getInt("price");
				String plot = rs.getString("plot");
				int rating = rs.getInt("rating");
				int reviewerId = rs.getInt("reviewerId");
				Work work = new Work(workId, title, creator, genre, type, price, plot, rating, reviewerId);
				myWorkList.add(work);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myWorkList;
	}
	public boolean myPost(Work work) {
		// ユーザーの作品をを登録する
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライブを読み込めませんでした");
		}
		String sql = "INSERT INTO work(title, creator, genre, price, plot, rating, reviewerId)"
									+ " VALUES(?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, work.getTitle());
			pstmt.setString(2, work.getCreator());
			pstmt.setString(3, work.getGenre());
			pstmt.setInt(4, work.getPrice());
			pstmt.setString(5, work.getPlot());
			pstmt.setInt(6, work.getRating());
			pstmt.setInt(7, work.getReviewerId());
			
			int result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean workDelete(Account account) {
		// ログインユーザの作品を削除する
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライブを読み込めませんでした");
		}
		String sql = "DELETE FROM work WHERE reviewerId = ?";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, account.getAccountId());
			
			int result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
