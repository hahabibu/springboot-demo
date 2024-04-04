package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.Employee;
import com.design.sm.model.Product;

public interface EmployeeDAO extends BaseDAO<Employee> {
	// ����Ա��id��ȡԱ������
	public Object getEmployeeName(String id) throws SQLException;

	// ����Ա����Ϣ
	public void addEmployee(Employee e) throws SQLException;

	// ����idɾ��Ա����Ϣ
	public void deleteEmployee(String id) throws SQLException;

	// �޸�Ա����Ϣ
	public void updateEmployee(Employee e) throws SQLException;

	// ��ѯ����Ա����Ϣ
	public List<Employee> findAllEmployee() throws SQLException;

	// ����Ա���������йؼ��ֲ���Ա����Ϣ
	public List<Employee> getEmployeeByNameKeyword(String keyword)
			throws SQLException;

	// ����Ա��id��ȡ����Ա����Ϣ
	public Employee getEmployeeById(String id) throws SQLException;

	// //����Ա��id�����˺���Ϣ
	// public void setEmployeeAccount(String eid,String accountId)throws
	// SQLException;

	// ��ѯ��ǰʹ��ĳ���˺ŵ�Ա��id
	public Object getEmployeeIdByAccountId(String accountId)
			throws SQLException;

	// ���ݲ���id��ȡ��Ӧ���ŵ�Ա����Ϣ
	public List<Employee> getEmployeeByDeptId(String deptId)
			throws SQLException;

	// ��ҳ��������
	public List<Employee> getAllEmployeeByPage(int page) throws SQLException;

	// ��ȡ��Ʒ�����Ӷ��ó���Ӧ�ķ�ҳ��
	public Object getEmployeeCount() throws SQLException;
	
	// ��ȡ��ǰԱ����ŵ���һ�����к�
	public Object getEmployeeNumNextSeq()throws SQLException;
	
	// ���ݲ���id��ȡÿ�����ŵ�Ӧ���Ĺ�������
	public Object getSalarySumByDeptId(String deptId)throws SQLException;
	
	// ���ݲ���id��ȡÿ�����ŵ�������
	public Object getEmployeeSumByDeptId(String deptId)throws SQLException;
	
}
