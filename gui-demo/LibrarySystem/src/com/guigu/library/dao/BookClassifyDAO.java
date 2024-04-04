package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.BookClassify;

public interface BookClassifyDAO extends BaseDAO<BookClassify>{
	// ����ͼ�������Ϣ
	public void addBookClassify(BookClassify bc)throws SQLException;
	
	// �޸�ͼ�������Ϣ
	public void updateBookClassify(BookClassify bc,String old_num)throws SQLException;
	
	// �������ͱ��ɾ��ͼ�������Ϣ
	public void deleteBookClassify(String classify_num)throws SQLException;
	
	// ���ݲ�������ͼ�������Ϣ
	public List<BookClassify> findAllBookClassify()throws SQLException;

	// ����ͼ�����ȼ����ҵ�ǰ����ȼ�������ͼ�������Ϣ
	public List<BookClassify> findBookClassifyByLevel(int level)throws SQLException;
	
	// ���ݸ���ͼ�����ͱ�ţ�������һ�ȼ���ͼ�������Ϣ
	public List<BookClassify> findChildBookClassifyByParent(String parent_classify_num)throws SQLException;
	
	// ����ͼ�����ͱ�Ż�����ͼ���������ƹؼ��ֲ���ͼ��������Ϣ
	public List<BookClassify> findBookClassifyByKeyWord(String keyword)throws SQLException;
	
	// ����ָ���ı�Ų���ָ����ͼ�������Ϣ
	public BookClassify getBookClassifyByNum(String classify_num)throws SQLException;
	
}
