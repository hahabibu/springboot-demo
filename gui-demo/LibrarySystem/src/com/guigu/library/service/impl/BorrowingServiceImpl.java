package com.guigu.library.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.dao.BooksDAO;
import com.guigu.library.dao.BorrowingDAO;
import com.guigu.library.dao.ReaderDAO;
import com.guigu.library.dao.impl.BooksDAOImpl;
import com.guigu.library.dao.impl.BorrowingDAOImpl;
import com.guigu.library.dao.impl.ReaderDAOImpl;
import com.guigu.library.model.Books;
import com.guigu.library.model.Borrowing;
import com.guigu.library.model.Reader;
import com.guigu.library.service.BorrowingService;

public class BorrowingServiceImpl implements BorrowingService {

	private BorrowingDAO borrowingDAO = new BorrowingDAOImpl();
	private BooksDAO booksDAO = new BooksDAOImpl();
	private ReaderDAO readerDAO = new ReaderDAOImpl();

	@Override
	public void addBorrowing(Borrowing b) throws SQLException {
		borrowingDAO.addBorrowing(b);
	}

	@Override
	public void updateBorrowing(Borrowing b) throws SQLException {
		borrowingDAO.updateBorrowing(b);
	}

	@Override
	public List<Borrowing> findAllBorrowing() throws SQLException {
		return borrowingDAO.findAllBorrowing();
	}

	@Override
	public List<Borrowing> findBorrowingByReaderId(String reader_id)
			throws SQLException {
		return borrowingDAO.findBorrowingByReaderId(reader_id);
	}

	@Override
	public int getBorrowingCountByState() throws SQLException {
		return Integer.valueOf(borrowingDAO.getBorrowingCountByState() + "");
	}

	@Override
	public String getBorrowingSeq() throws SQLException {
		return borrowingDAO.getBorrowingSeq() + "";
	}

	@Override
	public Borrowing getBorrowingByBookId(String book_id) throws SQLException {
		return borrowingDAO.getBorrowingByBookId(book_id);
	}

	@Override
	public List<Borrowing> findBorrowingByTime(String start_time,
			String end_time) throws SQLException {
		return borrowingDAO.findBorrowingByTime(start_time, end_time);
	}

	@Override
	public List<Borrowing> findViolationBorrowingByTime(String start_time,
			String end_time) throws SQLException {
		return borrowingDAO.findViolationBorrowingByTime(start_time, end_time);
	}

	@Override
	public Vector<Vector> pack(List<Borrowing> list) throws SQLException {

		// �ֶ���װ���ҵ���������Ϣ
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Borrowing obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 12; i++) {
					temp.add(obj.getBorrowing_id());// ����id
					temp.add(obj.getBorrowing_num());// ���ı��
					temp.add(obj.getBook_id());// ͼ��id
					Books book = booksDAO.getBooksById(obj.getBook_id());
					temp.add(book.getBook_name());// ͼ������
					temp.add(obj.getReader_id());// ����id
					Reader r = readerDAO.getReaderById(obj.getReader_id());
					temp.add(r.getReader_name());// ��������
					temp.add(obj.getBorrowing_date().substring(0, 10));// ��������
					temp.add(obj.getSuggest_return_date().substring(0, 10));// Ӧ������
					temp.add(obj.getBorrow_state());// ����״̬
					temp.add(this.getBorrowStateStr(obj.getBorrow_state()));// ����״̬
					temp.add(obj.getViolation_state());// Υ��״̬
					temp.add(this.getViolationStateStr(obj.getViolation_state()));// Υ��״̬
				}
				rows.add(temp);
			}
		}
		return rows;
	}
	
	/**
	 * ����״̬��ʶ��ȡ��Ӧ��״̬��Ϣ
	 */
	public String getBorrowStateStr(int borrow_state){
		String str = "";
		if(borrow_state==0){
			str = "�ѹ黹";
		}else if(borrow_state==1){
			str = "������";
		}
		return str;
	}
	
	public String getViolationStateStr(int violation_state){
		String str = "";
		if(violation_state==0){
			str = "����";
		}else if(violation_state==1){
			str = "Υ��";
		}
		return str;
	}
}
