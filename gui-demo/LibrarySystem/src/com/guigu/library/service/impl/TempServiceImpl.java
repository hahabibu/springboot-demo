package com.guigu.library.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.dao.BooksDAO;
import com.guigu.library.dao.TempDAO;
import com.guigu.library.dao.impl.BooksDAOImpl;
import com.guigu.library.dao.impl.TempDAOImpl;
import com.guigu.library.model.Books;
import com.guigu.library.model.Temp;
import com.guigu.library.service.TempService;

public class TempServiceImpl implements TempService {

	private TempDAO tempDAO = new TempDAOImpl();
	private BooksDAO booksDAO = new BooksDAOImpl();

	@Override
	public void addTemp(Temp t) throws SQLException {
		tempDAO.addTemp(t);
	}

	@Override
	public void deleteTemp(String id) throws SQLException {
		tempDAO.deleteTemp(id);
	}

	@Override
	public List<Temp> findTempByReaderId(String reader_id) throws SQLException {
		return tempDAO.findTempByReaderId(reader_id);
	}

	@Override
	public Vector<Vector> pack(List<Temp> list) throws SQLException {
		// �ֶ���װ���ҵ���������Ϣ
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Temp obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 4; i++) {
					temp.add(obj.getTemp_id());// ��¼id
					// �����ղص�ͼ��id��ȡͼ��ļ���Ϣ
					String book_id = obj.getBook_id();
					Books b = booksDAO.getBooksById(book_id);
					if(b!=null){
						temp.add(b.getBarcode());// ������
						temp.add(b.getIsbn()); // isbn
						temp.add(b.getCallno()); // �����
						temp.add(b.getBook_name()); // ����
					}else{
						// �鼮��ʧЧ
						temp.add("�鼮��ʧЧ");
						temp.add("��"); 
						temp.add("��"); 
						temp.add("��"); 
					}
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public Temp getTempByISBN(String isbn,String reader_id) throws SQLException {
		return tempDAO.getTempByISBN(isbn,reader_id);
	}
}
