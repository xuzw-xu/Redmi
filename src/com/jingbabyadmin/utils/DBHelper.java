package com.jingbabyadmin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {

	private static Connection conn = null;
	String driver = null;
	String url = null;
	String username = null;
	String password = null;

	public DBHelper() {
		
	}

	private void init() {
		InputStream is = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			Properties p = new Properties();
			p.load(is);
			driver = p.getProperty("driver");
			url = p.getProperty("url");
			username = p.getProperty("username");
			password = p.getProperty("password");
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Connection getConnection() {
		if (conn == null) {
			init();
		}
		return conn;
	}
	
	public void closeConnection(){
		if(conn!=null){
			try {
				conn.close();
				conn = null ;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void rollback(Connection connection) {
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
