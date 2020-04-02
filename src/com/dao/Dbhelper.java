package com.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class Dbhelper {
	private static String driverName;
	private static String url;
	private static String user;
	private static String password;
	private static Connection con = null;
	static{
		try {
			Properties properties = new Properties();
			InputStream is = Dbhelper.class.getClassLoader().getResourceAsStream("jdbc.properties");		
			properties.load(is);
			driverName = properties.getProperty("driverName");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建连接对象
	 * 
	 */
	public void createConn(){		
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	public void execute(String sql,Map<String,Object> param) throws Exception {
		if(con==null || con.isClosed()){
			throw new Exception("请首先调用createConnection方法创建连接");
		} 		
		PreparedStatement psmt = con.prepareStatement(sql);		
		if(param != null){
			int index = 1;
			for(String key:param.keySet()){
				Object value = param.get(key);
				System.out.println(index+","+key+"="+value);
				
				psmt.setObject(index, value);

				index++;
			}
		}
		
		psmt.executeUpdate();
		release(con, psmt,param);
	}
	public ResultSet query(String sql,Map<String,Object> param) throws Exception {
		if(con==null || con.isClosed()){
			throw new Exception("请首先调用createConnection方法创建连接");
		}
		
		PreparedStatement psmt = con.prepareStatement(sql);
		
		if(param != null){
			int index = 1;
			for(String key:param.keySet()){
				Object value = param.get(key);
				psmt.setObject(index, value);
				index++;
			}
		}
		
		ResultSet rs = psmt.executeQuery();
		return rs;
	}
	public void closeConnection() throws Exception {
		if(con==null) return;
		if(con.isClosed()) return;
		con.close();
	}
	/**
	 * 释放资源
	 * @param conn
	 * @param st
	 * @param rs
	 * @param mp
	 */
	public static void release(Connection conn , PreparedStatement st){
		closeSt(st);
		closeConn(conn);
	}
	public static void release(Connection conn , PreparedStatement st ,Map<String, Object> mp){
		closeSt(st);
		closeConn(conn);
		closeMp(mp);
	}
	
	private static void closeMp(Map<String, Object> mp){
			if(mp != null)
				mp.clear();
	}
	
	private static void closeSt(PreparedStatement st){
		try {
			if(st != null){
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			st = null;
		}
	}
	
	private static void closeConn(Connection conn){
		try {
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn = null;
		}
	}
}
