package com.shop.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBManager {
	
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String uid = "shop";
	private static String pwd = "1234";
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, uid, pwd);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		
		try {
			if(rs != null) {rs.close();}
			if(pstmt != null) {pstmt.close();}
			if(conn != null) {conn.close();}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		
		try {
			if(pstmt != null) {pstmt.close();}
			if(conn != null) {conn.close();}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
