package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;

import com.design.sm.model.Employee;

public interface EmployeeService {
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
	public String getEmployeeIdByAccountId(String accountId)
			throws SQLException;

	// ���ݲ���id��ȡ��Ӧ���ŵ�Ա����Ϣ
	public List<Employee> getEmployeeByDeptId(String deptId)
			throws SQLException;

	// ��List<Employee>��װΪVector<Vector>����
	public Vector<Vector> pack(List<Employee> list) throws SQLException;

	// ��ҳ��������
	public List<Employee> getAllEmployeeByPage(int page) throws SQLException;

	// ��ȡ��Ʒ�����Ӷ��ó���Ӧ�ķ�ҳ��
	public int getEmployeeMaxPage() throws SQLException;

	// ��������Ա����Ϣ����
	public int exportData(String[] ids);

	// ���ݸ�������Ʒid�б��ȡ��Ʒ��Ϣ
	public List<Employee> findAllEmployeeByIds(String[] empIds)
			throws SQLException;

	// ��ȡ��ǰԱ����ŵ���һ�����к�
	public String getEmployeeNumNextSeq() throws SQLException;

	// ����Ա��������Ϣ
	public int exportSalaryData(String[] empIds);
	
	// �������в��Ź��ʽ�����Ϣ
	public int exportBalanceData(JTable table);

	// ���ݲ���id��ȡÿ�����ŵ�Ӧ���Ĺ�������
	public Map<String,Double> getSalarySumByDeptId() throws SQLException;

	// ���ݲ���id��ȡÿ�����ŵ�������
	public Map<String,Integer> getEmployeeSumByDeptId() throws SQLException;
}
