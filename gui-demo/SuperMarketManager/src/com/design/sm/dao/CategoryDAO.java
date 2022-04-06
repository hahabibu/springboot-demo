package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.Category;

public interface CategoryDAO extends BaseDAO<Category>{
	
	//ͨ����������id��ȡ��������
	public Object getCategoryName(String id) throws SQLException;
	
	//���������������ƹؼ��ֻ�ȡ������Ϣ
	public List<Category> getCategoryByNameKeyword(String keyword) throws SQLException;

	//����������Ʒ������Ϣ
	public List<Category> findAllCategory() throws SQLException;
	
	//����������Ϣ
	public void addCategory(Category c)throws SQLException;
	
	//�޸ķ�����Ϣ
	public void updateCategory(Category c)throws SQLException;
	
	//����ɾ��������Ϣ
	public void deleteCategory(String id)throws SQLException;
}
