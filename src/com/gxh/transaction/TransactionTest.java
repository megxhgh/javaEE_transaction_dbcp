package com.gxh.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.gxh.utils.DBUtils;

public class TransactionTest {
	@Test
	//单元测试不能使用静态方法
//	public static void main(String[] args){
	public void test(){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);	//开启事务，相当于begin或start transaction
			
			String sql = "update account set money=money-100 where name='aaa'";
			String sql2 = "update account set money=money+100 where name='bbb'";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			//int i = 10/0;
			ps = conn.prepareStatement(sql2);
			ps.executeUpdate();
			
			conn.commit();	//提交事务
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();	//回滚
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			DBUtils.release(null,ps,conn);
		}
		
				
	}
}
