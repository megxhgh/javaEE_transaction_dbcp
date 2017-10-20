package com.gxh.pool;

import java.sql.Connection;
import java.util.Collections;
import java.util.LinkedList;

import com.gxh.utils.DBUtils;

public class SimpleConnectionPool {
	
	private static LinkedList<Connection> pool = (LinkedList<Connection>)Collections.synchronizedList(new LinkedList<Connection>()) ;
	//初始化，创建数据库连接池
	static{
		try {
				for(int i=0;i<10;i++){
					Connection conn = DBUtils.getConnection();
					pool.add(conn);
				}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new ExceptionInInitializerError("初始化数据库连接异常，请检查数据库配置文件是否正确！");
		}		
	}
	//从连接池中取出一个连接
	public static Connection getConnectionFromPool(){
		Connection conn = null;
		if(pool.size()>0){
			conn = pool.removeFirst();	//从连接池中取出一个连接
			return conn;
		}else{
			//等待，创建新连接
			throw new RuntimeException("服务器正忙，请稍后重试！");
		}
	}
	//释放资源
	public static void release(Connection conn){
		pool.addLast(conn);
	}
}
