package com.gxh.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.gxh.utils.DBUtils;

public class TransactionTest {
	@Test
	//��Ԫ���Բ���ʹ�þ�̬����
//	public static void main(String[] args){
	public void test(){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);	//���������൱��begin��start transaction
			
			String sql = "update account set money=money-100 where name='aaa'";
			String sql2 = "update account set money=money+100 where name='bbb'";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			//int i = 10/0;
			ps = conn.prepareStatement(sql2);
			ps.executeUpdate();
			
			conn.commit();	//�ύ����
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();	//�ع�
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
