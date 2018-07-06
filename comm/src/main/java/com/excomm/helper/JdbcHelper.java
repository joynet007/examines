package com.excomm.helper;


import java.sql.*;


public class JdbcHelper {
	
	public static Connection getCon() throws SQLException{
		
//		String IP = "47.94.102.232";
		String IP = "127.0.0.1";
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			conn=DriverManager.getConnection("jdbc:mysql://"+IP+":3306/ruankao?characterEncoding=utf8&useSSL=false","root","uAiqwVwjJ8-i");
			conn=DriverManager.getConnection("jdbc:mysql://"+IP+":3306/ruankao?characterEncoding=utf8&useSSL=false","root","root123");
			if(conn != null){
				return conn;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	
	public static void main(String[] args) {

		String IP = "47.94.102.232";
		
		try {
			Class.forName("com.jnetdirect.jsql.JSQLDriver");
			
			Connection conn=DriverManager.getConnection("jdbc:mysql://"+IP+":3306/ruankao?characterEncoding=utf8&useSSL=false","root","uAiqwVwjJ8-i");
			 
			PreparedStatement ps =null;
			ResultSet rs = null;
			
			if(conn != null){
				ps = conn.prepareStatement("select * from sys_user ");
				rs = ps.executeQuery();
				while(rs.next()){
					String userID = rs.getString(1);
					String userName = rs.getString(2);
					System.out.println(userID+"----"+userName);
				}
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
