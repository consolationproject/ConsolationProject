package com.dao.util;

import java.sql.*;

public class DBUtil {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			System.out.println("数据库驱动无法加载");
		}
	}

	public static Connection getConnection(){//数据库
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3307/grade_query_system";
		String user = "root";
		String password = "adobe197341022";
		try {
			conn = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("Connection 获取错误");
		}
		return conn;
	}
	
	public static void close(ResultSet rs, Statement st, Connection conn){
		try {
			if(rs!=null)
				rs.close();
			if(st!=null)
				st.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs, PreparedStatement st, Connection conn){
		try {
			if(rs!=null)
				rs.close();
			if(st!=null)
				st.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}