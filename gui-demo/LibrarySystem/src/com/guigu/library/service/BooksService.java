package com.guigu.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.model.Books;

public interface BooksService {

	// ���ͼ����Ϣ
	public void addBooks(Books book) throws SQLException;

	// �޸�ͼ����Ϣ
	public void updateBooks(Books book) throws SQLException;

	// ����ͼ��idɾ��ͼ����Ϣ
	public void deleteBooks(String id) throws SQLException;

	// ����ͼ��id��ȡ��Ӧͼ����Ϣ
	public Books getBooksById(String id) throws SQLException;

	// ���������������ͼ����Ϣ
	public List<Books> findBooksUnion(int field, int match, Object... args)
			throws SQLException;

	// ����pack������List<Books>��װΪVector<Vector>��¼
	public Vector<Vector> pack(List<Books> list) throws SQLException;

	// ��ȡ��ǰͼ������
	public String getBookSeq() throws SQLException;
	
	// �����鼮�ĵ��������ȡ�鼮��������Ϣ
	public Books getBookByBarcode(String barcode)throws SQLException;
}
