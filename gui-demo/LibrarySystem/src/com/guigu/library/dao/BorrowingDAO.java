package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.Borrowing;

public interface BorrowingDAO extends BaseDAO<Borrowing> {

	// ���ӽ�����Ϣ
	public void addBorrowing(Borrowing b) throws SQLException;

	// �޸Ľ�����Ϣ
	public void updateBorrowing(Borrowing b) throws SQLException;

	// �������е�ͼ�������Ϣ
	public List<Borrowing> findAllBorrowing() throws SQLException;

	// ���ݶ���id���Ҷ�Ӧ���ߵ�ͼ�������Ϣ
	public List<Borrowing> findBorrowingByReaderId(String reader_id)
			throws SQLException;

	// ͳ�Ƶ�ǰ���ڽ���״̬����δ�黹�鼮���ļ�¼����
	public Object getBorrowingCountByState() throws SQLException;

	// ��ȡ��ǰ�Ľ������
	public Object getBorrowingSeq() throws SQLException;

	/**
	 * ���ڿ��ܴ��ڶ���û���ͬһ���û���ν���ͬһ��ͼ�飬��������Ǹ���ͼ��id
	 * ��ȡ��صĽ�����Ϣ��������ʵ�ʣ���˴˴������õ�ǰ����Ľ���ʱ���ͼ��id ���в��ң�����ͼ��id���ܹ���Ӧ��ø�ͼ������Ľ�����Ϣ
	 */
	public Borrowing getBorrowingByBookId(String book_id) throws SQLException;

	// ����ʱ�䷶Χ�������н�����Ϣ
	public List<Borrowing> findBorrowingByTime(String start_time,
			String end_time) throws SQLException;

	// ����ʱ�䷶Χ����Υ�µĽ��ļ�¼
	public List<Borrowing> findViolationBorrowingByTime(String start_time,
			String end_time) throws SQLException;
}
