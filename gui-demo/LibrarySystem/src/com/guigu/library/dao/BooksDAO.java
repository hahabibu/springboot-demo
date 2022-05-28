package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.Books;

public interface BooksDAO extends BaseDAO<Books> {

	// ����ͼ����Ϣ
	public void addBooks(Books book) throws SQLException;

	// �޸�ͼ����Ϣ
	public void updateBooks(Books book) throws SQLException;

	// ����ͼ��idɾ��ͼ����Ϣ
	public void deleteBooks(String id) throws SQLException;

	// ����ͼ��id��ȡͼ����Ϣ
	public Books getBooksById(String id) throws SQLException;

	// ��������ͼ���¼
	public List<Books> findAllBooks() throws SQLException;

	// �����ֶιؼ��ּ���ͼ���¼
	// ����ͼ�����Ƽ���ͼ���¼
	public List<Books> findBooksByName(String name_keyword) throws SQLException;

	// ����ͼ���isbn����ͼ���¼
	public List<Books> findBooksByISBN(String isbn_keyword) throws SQLException;

	// ����ͼ�������ż���ͼ���¼
	public List<Books> findBooksByCallno(String callno_keyword)
			throws SQLException;

	// ����ͼ��������ͼ���¼
	public List<Books> findBooksByClassify(String classify_keyword)
			throws SQLException;

	// �������߼���ͼ���¼
	public List<Books> findBooksByAuthor(String author_keyword)
			throws SQLException;

	// �������߼���ͼ���¼
	public List<Books> findBooksByTranslator(String translator_keyword)
			throws SQLException;

	// ���ݳ��������ͼ���¼
	public List<Books> findBooksByPress(String press_keyword)
			throws SQLException;

	// ��ȡ��ǰͼ������
	public Object getBookSeq() throws SQLException;

	// �����鼮�ĵ��������ȡ�鼮��������Ϣ
	public Books getBookByBarcode(String barcode) throws SQLException;

	// ���մӸߵ��͵�˳���ȡĳ��ʱ��ν��Ĵ�����ǰ10λ��ͼ����Ϣ
	public List<Books> findTop10BooksByTime(String start, String end)
			throws SQLException;

	// ������Ӧ��ͼ��id(ֻҪͼ���isbn��ͬ��Ĭ����ͬ������)ͳ�Ƶ�ǰͼ����ĳ��ʱ��α����ĵĴ���
	public Object getCountByBookId(String book_id, String start, String end)
			throws SQLException;

}
