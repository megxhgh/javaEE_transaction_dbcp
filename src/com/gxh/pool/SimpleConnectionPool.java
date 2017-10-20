package com.gxh.pool;

import java.sql.Connection;
import java.util.Collections;
import java.util.LinkedList;

import com.gxh.utils.DBUtils;

public class SimpleConnectionPool {
	
	private static LinkedList<Connection> pool = (LinkedList<Connection>)Collections.synchronizedList(new LinkedList<Connection>()) ;
	//��ʼ�����������ݿ����ӳ�
	static{
		try {
				for(int i=0;i<10;i++){
					Connection conn = DBUtils.getConnection();
					pool.add(conn);
				}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new ExceptionInInitializerError("��ʼ�����ݿ������쳣���������ݿ������ļ��Ƿ���ȷ��");
		}		
	}
	//�����ӳ���ȡ��һ������
	public static Connection getConnectionFromPool(){
		Connection conn = null;
		if(pool.size()>0){
			conn = pool.removeFirst();	//�����ӳ���ȡ��һ������
			return conn;
		}else{
			//�ȴ�������������
			throw new RuntimeException("��������æ�����Ժ����ԣ�");
		}
	}
	//�ͷ���Դ
	public static void release(Connection conn){
		pool.addLast(conn);
	}
}
