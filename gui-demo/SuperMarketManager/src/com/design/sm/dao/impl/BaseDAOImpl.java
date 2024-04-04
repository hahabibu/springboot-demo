package com.design.sm.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.design.sm.dao.BaseDAO;
import com.design.sm.utils.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * ����ʵ��BaseDAO��ʵ���� 
 */
public class BaseDAOImpl<T> implements BaseDAO<T>{
	private QueryRunner qr = null;
	/**
	 * ���÷���ʵ�ֵõ����������
	 * �˴����˽⣬֮���ڵڶ��׶�������ѧϰ�йط���ĸ���
	 * ע�⵼��İ���import java.lang.reflect.Type;
	 */
	private Class<T> type;
	//����һ�����õ�Connection
	public Connection conn = null;
	
	public BaseDAOImpl() {
		//����c3p0���ӳػ�ȡ����
//		try {
//			//ͨ��c3p0���ӳ����ƻ�ȡ���ݿ�����
//			DataSource dataSource = new ComboPooledDataSource("c3p0connection");
//			conn = dataSource.getConnection();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		conn = JDBCUtils.getJDBCUtils().getConnection();
		qr = new QueryRunner();
		Type genType =getClass().getGenericSuperclass();
		Type [] params= ((ParameterizedType)genType).getActualTypeArguments();
		type=(Class) params[0];
	}

	@Override
	public void batch(Connection conn, String sql, Object[]... args)
			throws SQLException {
		qr.batch(conn, sql, args);
	}

	@Override
	public <E> E getForValue(Connection conn, String sql, Object... args)
			throws SQLException {
		return (E) qr.query(conn, sql, new ScalarHandler(),args);
	}

	@Override
	public List<T> getForList(Connection conn, String sql, Object... args)
			throws SQLException {
		return qr.query(conn, sql, new BeanListHandler<>(type), args);
	}

	@Override
	public T get(Connection conn, String sql, Object... args)
			throws SQLException {
		return qr.query(conn, sql, new BeanHandler<>(type), args);
	}
	
	@Override
	public void update(Connection conn, String sql, Object... args)
			throws SQLException {
		qr.update(conn, sql, args);
	}
}
