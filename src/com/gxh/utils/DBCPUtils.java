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
	//1,��ȡ���ӳ�
	static{
		
		Properties properties = new Properties();
		try {
			properties.load(DBCPUtils.class.getClassLoader()
					.getResourceAsStream("dbcpconfig.properties"));		//���������ļ�
			ds = BasicDataSourceFactory.createDataSource(properties);	//��ȡ���ӳ�
		} catch (Exception e) {
			// TODO: handle exception
			throw new ExceptionInInitializerError("��ʼ���쳣���������ݿ������ļ���");
		}
	}
	
	//2,��ȡ����
	public static Connection getConnection(){
		try {
			return ds.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("��������æ�����Ժ����ԣ�");
		}
	}
	
	//3,�ͷ�����
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
