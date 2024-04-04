package com.guigu.library.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.dao.BookClassifyDAO;
import com.guigu.library.dao.BooksDAO;
import com.guigu.library.dao.StorageAreaDAO;
import com.guigu.library.dao.impl.BookClassifyDAOImpl;
import com.guigu.library.dao.impl.BooksDAOImpl;
import com.guigu.library.dao.impl.StorageAreaDAOImpl;
import com.guigu.library.model.BookClassify;
import com.guigu.library.model.Books;
import com.guigu.library.model.StorageArea;
import com.guigu.library.service.BooksService;

public class BooksServiceImpl implements BooksService {

	private BooksDAO booksDAO = new BooksDAOImpl();
	private BookClassifyDAO bookClassifyDAO = new BookClassifyDAOImpl();
	private StorageAreaDAO storageAreaDAO = new StorageAreaDAOImpl();

	@Override
	public void addBooks(Books book) throws SQLException {
		booksDAO.addBooks(book);
	}

	@Override
	public void updateBooks(Books book) throws SQLException {
		booksDAO.updateBooks(book);
	}

	@Override
	public void deleteBooks(String id) throws SQLException {
		booksDAO.deleteBooks(id);
	}

	@Override
	public Books getBooksById(String id) throws SQLException {
		return booksDAO.getBooksById(id);
	}

	@Override
	public List<Books> findBooksUnion(int field, int match, Object... args)
			throws SQLException {
		/**
		 * ���ݼ����ֶκ�ƥ�䷽ʽ���м��� 1.��������ֶ�Ϊ���������ݡ�����Ϊ�Ǽ�������ͼ�飬�󷽵�ƥ�䷽ʽĬ��ʧЧ
		 * 2.��������ֶβ�Ϊ���������ݡ�����ݼ����ֶν��ƥ�䷽ʽ����ͼ����Ϣ�ļ��� a.field=1������ b.field=2��isbn
		 * c.field=3������� d.field=4��ͼ����� e.field=5������ f.field=6������ g.field=7��������
		 * ��Ӧƥ�䷽ʽ��0.ǰ��һ�� 1.��ȫƥ�� 2.����ƥ��
		 */
		// �����ֶ�Ϊ��������
		if (field == 0) {
			return booksDAO.findAllBooks();
		} else {
			String keyword = args[0] + "";
			String text = "";
			// ���ж�ѡ���ƥ�䷽ʽ
			if (match == 0) {
				text = keyword + "%";
			} else if (match == 1) {
				text = keyword;
			} else if (match == 2) {
				text = "%" + keyword + "%";
			}
			if (field == 1) {
				return booksDAO.findBooksByName(text);
			} else if (field == 2) {
				return booksDAO.findBooksByISBN(text);
			} else if (field == 3) {
				return booksDAO.findBooksByCallno(text);
			} else if (field == 4) {
				return booksDAO.findBooksByClassify(text);
			} else if (field == 5) {
				return booksDAO.findBooksByAuthor(text);
			} else if (field == 6) {
				return booksDAO.findBooksByTranslator(text);
			} else if (field == 7) {
				return booksDAO.findBooksByPress(text);
			}
		}
		return null;
	}

	@Override
	public Vector<Vector> pack(List<Books> list) throws SQLException {
		// �ֶ���װ���ҵ���������Ϣ
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Books obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 24; i++) {
					// ��������id��ȡ��Ӧ�ķ��ࡢ�洢�������
					BookClassify bc = bookClassifyDAO.getBookClassifyByNum(obj.getClassify_num());
					StorageArea sa = storageAreaDAO.getStorageAreaByNum(obj.getArea_num());
					temp.add(obj.getBook_id()); // ͼ��id
					temp.add(obj.getBarcode()); // ������
					temp.add("ISBN "+obj.getIsbn());    // ISBN ����ͳһ��׼���
					temp.add(obj.getCallno());  // �����
					temp.add(obj.getBook_name());  // ���� 
					temp.add(obj.getClassify_num()); // ͼ�����id
					temp.add(bc.getClassify_name());// ͼ���������
					temp.add(obj.getArea_num()); // �洢������
					temp.add(sa.getArea_name());// �洢��������
					temp.add(obj.getAuthor()); // ����
					temp.add(obj.getTranslator()); // ����
					temp.add(obj.getPublish_date());// ��������
					temp.add(obj.getPress()); // ������
					temp.add(obj.getPrice()); // �۸�
					temp.add(obj.getFormat()); // ���
					temp.add(obj.getEntry_date());// ¼������
					temp.add(obj.getPut_on_date());// �ϼ�����
					temp.add(obj.getAbstract_descr());// ��Ҫ��ժ��ע
					temp.add(obj.getProposal_reader());// ʹ�ö���ע
					temp.add(obj.getBorrow_flag());// ���ı�ʶ
					temp.add(this.getBorrowState(obj.getBorrow_flag()));// ����״̬
					temp.add(obj.getPut_on_flag());// �ϼܱ�ʶ
					temp.add(this.getPutOnState(obj.getPut_on_flag()));// �ϼ�״̬
					temp.add(obj.getDelete_flag());// ɾ����ʶ
				}
				rows.add(temp);
				// ����5 7 11 13 14 15 16 17 18 19 21 23
			}
		}
		return rows;
	}
	
	/**
	 * ���ݽ��ı�ʶ��ȡ��Ӧ�Ľ���״̬
	 */
	public String getBorrowState(int borrow_flag){
		String state = "";
		if(borrow_flag==0){
			state = "�ɽ���";
		}else if(borrow_flag==1){
			state = "�ѽ��";
		}
		return state;
	}
	
	/**
	 * �����ϼܱ�ʶ��ȡ��Ӧ���ϼ�״̬
	 */
	public String getPutOnState(int puton_flag){
		String state = "";
		if(puton_flag==0){
			state = "�����ϼ�";
		}else if(puton_flag==1){
			state = "���ϼ�";
		}
		return state;
	}

	@Override
	public String getBookSeq() throws SQLException {
		return booksDAO.getBookSeq()+"";
	}

	@Override
	public Books getBookByBarcode(String barcode) throws SQLException {
		return booksDAO.getBookByBarcode(barcode);
	}
}
