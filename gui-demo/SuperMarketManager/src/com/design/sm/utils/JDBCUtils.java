package com.design.sm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * �����ݿ���������Ϊ����ģʽ��һ��ֻ�����ڴ��д���һ������
 * 1.����˽�еľ�̬�������Լ��ı���
 * 2.���캯��˽�л�
 * 3.ͨ����̬������ʼ����ص����ԣ����ݿ����ӵ��ĸ����ԣ�
 * 4.������ȡJDBCUtils������  �� ˫����֤ģʽ����������ʽ������ʽ��
 *   a.�жϵ�ǰ�����Ƿ�Ϊ��
 *   b.���Ϊ�����������synchronized (JDBCUtils.class) {...}
 *   c.�����жϵ�ǰ�����Ƿ�Ϊ��
 *     ���п��ܴ�����������ʱ�������Ķ����Ѿ������ˣ�����Ҫ�ظ�������
 *   d.���ص�ǰ�Ķ���
 * 5.��ȡ���ݿ����ӣ��������ӡ���������
 * �������ݿ����ӻ�ȡ��ʽ��JDBCUtils.getConnection();
 */
public class JDBCUtils {
	//1.����˽�еľ�̬�������Լ��ı���
	private static JDBCUtils jdbcutils;
	/**
	 *   �������ݿ����ӵ��ĸ�����
	 *   �û��� username:haha
	 *   ����    password:haha
	 *   ����    driver:
	 *   ����    url:
	 */
	private static String username = null;
	private static String password = null;
	private static String driver = null;
	private static String url = null;
	//2.���캯��˽�л�
	private JDBCUtils()
	{  
		
	}
	//3.ͨ����̬������ʼ����ص�����
	static
	{
		Properties p = new Properties();
		try {
			// ���õ�ǰ������������������ļ��ļ���
			InputStream in = JDBCUtils.class.getClassLoader()
					.getResourceAsStream("jdbc.properties");
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ��ȡ�����ļ��е����������Ϣ
		username = p.getProperty("jdbc.username");
		password = p.getProperty("jdbc.password");
		driver = p.getProperty("jdbc.driver");
		url = p.getProperty("jdbc.url");
	}
	/**
	 *   ��ȡ���ݿ�����
	 *   a.����������Class.forName(driver);
	 *   b.ͨ��DriverManager��getConnection������ȡ���ݿ�����
	 *   c.���շ��ػ�ȡ������
	 *   Ϊ�˷���˵���˴������쳣ͳһ�׸���һ������
	 *   @throws Exception 
	 */
	//4.������ȡJDBCUtils������  �� ˫����֤ģʽ
	public static JDBCUtils getJDBCUtils()
	{
		//�жϵ�ǰjdbcutils�Ƿ�Ϊ��
		if(jdbcutils==null)
		{
			//���jdbcutilsΪ�����������
			synchronized (JDBCUtils.class) {
				/**
				 * �ٽ��ж����жϣ���Ϊ�п��ܻ���ּ�����ͬʱ����һ������
				 * �Ѿ��������µ����ݣ���ʱ����Ҫ�ٴδ���
				 */
				if(jdbcutils==null)
				{
					jdbcutils=new JDBCUtils();
				}
			}
		}
		//��˫������֤��ȡ��ǰ�Ķ���
		return jdbcutils;
	}
	//5.��ȡ���ݿ�����
	public static Connection getConnection() 
	{
		
		Connection conn = null;
		// �������ӣ������������������ӡ����ش���������.....
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	//�ر�����
	public static void freeAll(Connection conn,Statement st,ResultSet rs)
	{
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(st!=null)
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}	
