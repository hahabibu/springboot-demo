package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.Renew;
import com.guigu.library.model.Returning;

public interface RenewDAO extends BaseDAO<Renew> {

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
	public Object getRenewSeq() throws SQLException;

	// ��ȡָ��ʱ��εĹ黹��¼
	public List<Renew> findRenewByTime(String start_time,
			String end_time) throws SQLException;

}
