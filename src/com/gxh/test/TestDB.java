package com.gxh.test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import com.gxh.utils.DBUtils;

public class TestDB {
	//注意数据库资源文件的放置路径，放在src目录下，放其他位置易出错
	@Test
	public void testDB(){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("insert into account(name,money) values(?,?)");
			ps.setString(1, "gxh");
			ps.setDouble(2, 56);
			
			int i = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
		}finally{
			DBUtils.release(null, ps, conn);
		}
	}
}
