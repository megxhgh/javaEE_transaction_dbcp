package com.gxh.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.gxh.utils.C3P0Utils;

import org.junit.Test;
public class TestC3P0 {
	//注意数据库配置文件的参数名，url使用jdbcUrl;
	@Test
	public void testC3p0(){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = C3P0Utils.getConnection();
			System.out.println(conn.getClass().getName());
			ps = conn.prepareStatement("insert into account(name,money) values(?,?)");
			ps.setString(1, "tom");
			ps.setDouble(2, 20);
			
			int i = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			C3P0Utils.release(null, ps, conn);
		}
		
	}
}
