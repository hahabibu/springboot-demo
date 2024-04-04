package com.design.sm.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * �������ݵ�DAO�Ľӿ�
 * �˴�����������ݱ�ĸ��ַ���
 */
public interface BaseDAO<T> {
	/**
	 * ������������ķ���
	 * @param conn
	 * @param sql
	 * @param args�����ռλ��������������
	 * @throws SQLException
	 */
	public void batch(Connection conn,String sql,Object[]... args)throws SQLException;

	/**
	 * ���巵��һ�������ֵ�ķ���
	 * �����ѯĳ���˵�ĳ����Ϣ����ѯ����������ѯƽ�����ʵ�
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public <E> E getForValue(Connection conn,String sql,Object... args)throws SQLException;
	
	/**
	 * ��ѯһ������ļ��ϣ�����T���ϵ����ж���
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public List<T> getForList(Connection conn,String sql,Object... args)throws SQLException;
	
	/**
	 * ���ز�ѯ��һ��T���͵Ķ���
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public T get(Connection conn,String sql,Object... args)throws SQLException;
	
	/**
	 * ��ɾ�ĵ�ͨ�÷���
	 * @param conn
	 * @param sql
	 * @param args
	 * @throws SQLException
	 */
	public void update(Connection conn,String sql,Object... args)throws SQLException;
}
