package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Category;

public interface CategoryService {

	// ͨ������id��ȡ��������
	public Object getCategoryName(String id) throws SQLException;

	// ��ȡ������Ʒ������Ϣ�б�
	public List<Category> findAllCategoryList() throws SQLException;

	// ��ȡ������Ʒ������Ϣ�б�
	public Vector<Vector> findAllCategoryVector() throws SQLException;

	// ����������Ϣ
	public void addCategory(Category c) throws SQLException;

	// �޸ķ�����Ϣ
	public void updateCategory(Category c) throws SQLException;

	// ����ɾ��������Ϣ
	public void deleteCategory(String id) throws SQLException;

	//���������������ƹؼ��ֻ�ȡ������Ϣ
	public Vector<Vector> getCategoryByNameKeyword(String keyword) throws SQLException;

}
