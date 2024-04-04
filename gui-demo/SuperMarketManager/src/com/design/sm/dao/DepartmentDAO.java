package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.Department;

public interface DepartmentDAO extends BaseDAO<Department>{
	
	//ͨ������id��ȡ��������
	public Object getDepartmentName(String id) throws SQLException;
	
	//���ݲ������ƹؼ��ֻ�ȡ������Ϣ
	public List<Department> getDepartmentByNameKeyword(String keyword) throws SQLException;

	//�������в�����Ϣ
	public List<Department> findAllDepartment() throws SQLException;
	
	//����������Ϣ
	public void addDepartment(Department d)throws SQLException;
	
	//�޸Ĳ�����Ϣ
	public void updateDepartment(Department d)throws SQLException;
	
	//����idɾ��������Ϣ
	public void deleteDepartment(String id)throws SQLException;
	
}
