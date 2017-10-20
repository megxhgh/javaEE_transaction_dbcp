package com.gxh.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.gxh.utils.C3P0Utils;
import com.gxh.utils.DBCPUtils;

//此方法通过测试有效
public class TestDBCP {
	@Test
	public void testDBCP(){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBCPUtils.getConnection();
			ps = conn.prepareStatement("insert into account(name,money) values(?,?)");
			ps.setString(1, "JERRY");
			ps.setDouble(2, 20);
			
			int i = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			C3P0Utils.release(null, ps, conn);
		}
		
	}
}
