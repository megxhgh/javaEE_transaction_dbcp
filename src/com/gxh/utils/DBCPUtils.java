package com.gxh.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtils {
	private static DataSource ds = null;
	//1,获取连接池
	static{
		
		Properties properties = new Properties();
		try {
			properties.load(DBCPUtils.class.getClassLoader()
					.getResourceAsStream("dbcpconfig.properties"));		//加载配置文件
			ds = BasicDataSourceFactory.createDataSource(properties);	//获取连接池
		} catch (Exception e) {
			// TODO: handle exception
			throw new ExceptionInInitializerError("初始化异常，请检查数据库配置文件！");
		}
	}
	
	//2,获取连接
	public static Connection getConnection(){
		try {
			return ds.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("服务器正忙，请稍后重试！");
		}
	}
	
	//3,释放连接
	public static void release(ResultSet rs,PreparedStatement ps,Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
