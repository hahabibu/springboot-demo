package com.guigu.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.model.Renew;
import com.guigu.library.model.Returning;

public interface ReturningService {
	// ��ӹ黹��¼
	public void addReturning(Returning returning) throws SQLException;

	// ��ȡ���еĹ黹��¼
	public List<Returning> findAllReturning() throws SQLException;

	// ���ݶ���id��ȡ���еĹ黹��¼
	public List<Returning> findReturningByReaderId(String reader_id)
			throws SQLException;

	// ����ͼ��id��ȡ���еĹ黹��¼
	public List<Returning> findReturningByBookId(String book_id)
			throws SQLException;

	// ��ȡ�黹���
	public String getReturningSeq() throws SQLException;

	// ��ȡָ��ʱ��εĹ黹��¼
	public List<Returning> findReturningByTime(String start_time,
			String end_time) throws SQLException;

	// ��List<Renew>ת��Ϊ��Ӧ��Vector<Vector>
	public Vector<Vector> pack(List<Returning> list) throws SQLException;
}
