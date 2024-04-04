package com.guigu.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.model.BookClassify;

public interface BookClassifyService {

	// ����ͼ�������Ϣ
	public void addBookClassify(BookClassify bc) throws SQLException;

	// �޸�ͼ�������Ϣ
	public void updateBookClassify(BookClassify bc,String old_num) throws SQLException;

	// �������ͱ��ɾ��ͼ�������Ϣ
	public void deleteBookClassify(String classify_num) throws SQLException;

	// �����ۺϷ��������Բ�������������
	public List<BookClassify> findBookClassifyUnion(String type,Object... args)throws SQLException;
	
	// ���巽��pack���Խ�List<BookClassify>���ͷ�װΪVector<Vector>
	public Vector<Vector> pack(List<BookClassify> list)throws SQLException;

	// ����ָ���ı�Ų���ָ����ͼ�������Ϣ
	public BookClassify getBookClassifyByNum(String classify_num)
			throws SQLException;
}
