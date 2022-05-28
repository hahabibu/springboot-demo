package com.guigu.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.model.Renew;
import com.guigu.library.model.Returning;

public interface RenewService {

	// ��������¼
	public void addRenew(Renew renew) throws SQLException;

	// ��ȡ���е������¼
	public List<Renew> findAllRenew() throws SQLException;

	// ���ݶ���id��ȡ���е������¼
	public List<Renew> findRenewByReaderId(String reader_id)
			throws SQLException;

	// ����ͼ��id��ȡ���е������¼
	public List<Renew> findRenewByBookId(String book_id) throws SQLException;

	// ��ȡ�������
	public String getRenewSeq() throws SQLException;

	// ��ȡָ��ʱ��εĹ黹��¼
	public List<Renew> findRenewByTime(String start_time, String end_time)
			throws SQLException;

	// ��List<Renew>ת��Ϊ��Ӧ��Vector<Vector>
	public Vector<Vector> pack(List<Renew> list) throws SQLException;
	
}
