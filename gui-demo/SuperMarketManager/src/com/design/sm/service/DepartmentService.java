package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Department;

public interface DepartmentService {

	// ͨ������id��ȡ��������
	public Object getDepartmentName(String id) throws SQLException;

	// ���ݲ������ƹؼ��ֻ�ȡ������Ϣ
	public List<Department> getDepartmentByNameKeyword(String keyword)
			throws SQLException;

	// �������в�����Ϣ
	public List<Department> findAllDepartment() throws SQLException;

	// ����������Ϣ
	public void addDepartment(Department d) throws SQLException;

	// �޸Ĳ�����Ϣ
	public void updateDepartment(Department d) throws SQLException;

	// ����idɾ��������Ϣ
	public void deleteDepartment(String id) throws SQLException;
	
	// ����pack������List<Department>��װΪVector<Vector>��¼
	public Vector<Vector> pack(List<Department> list)throws SQLException;

}
