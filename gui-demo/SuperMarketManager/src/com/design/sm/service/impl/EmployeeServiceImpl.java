package com.design.sm.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.design.sm.dao.AccountsDAO;
import com.design.sm.dao.DepartmentDAO;
import com.design.sm.dao.EmployeeDAO;
import com.design.sm.dao.JobDAO;
import com.design.sm.dao.impl.AccountsDAOImpl;
import com.design.sm.dao.impl.DepartmentDAOImpl;
import com.design.sm.dao.impl.EmployeeDAOImpl;
import com.design.sm.dao.impl.JobDAOImpl;
import com.design.sm.model.Department;
import com.design.sm.model.Employee;
import com.design.sm.model.Job;
import com.design.sm.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	private AccountsDAO accountsDAO = new AccountsDAOImpl();
	private DepartmentDAO departmentDAO = new DepartmentDAOImpl();
	private JobDAO jobDAO = new JobDAOImpl();

	@Override
	public Object getEmployeeName(String id) throws SQLException {
		return employeeDAO.getEmployeeName(id);
	}

	@Override
	public void addEmployee(Employee e) throws SQLException {
		employeeDAO.addEmployee(e);
	}

	@Override
	public void deleteEmployee(String id) throws SQLException {
		employeeDAO.deleteEmployee(id);
	}

	@Override
	public void updateEmployee(Employee e) throws SQLException {
		employeeDAO.updateEmployee(e);
	}

	@Override
	public List<Employee> findAllEmployee() throws SQLException {
		return employeeDAO.findAllEmployee();
	}

	@Override
	public List<Employee> getEmployeeByNameKeyword(String keyword)
			throws SQLException {
		return employeeDAO.getEmployeeByNameKeyword(keyword);
	}

	@Override
	public Employee getEmployeeById(String id) throws SQLException {
		return employeeDAO.getEmployeeById(id);
	}

	@Override
	public String getEmployeeIdByAccountId(String accountId)
			throws SQLException {
		return (String) employeeDAO.getEmployeeIdByAccountId(accountId);
	}

	@Override
	public List<Employee> getEmployeeByDeptId(String deptId)
			throws SQLException {
		return employeeDAO.getEmployeeByDeptId(deptId);
	}

	@Override
	public Vector<Vector> pack(List<Employee> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Employee obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 18; i++) {
					temp.add(obj.getEmployee_id());// Ա��id
					temp.add(obj.getEmployee_num());// Ա�����
					temp.add(obj.getEmployee_name());// Ա������
					temp.add(obj.getId_card());// ���֤��
					temp.add(obj.getAge());// ����
					temp.add(obj.getGender());// �Ա�
					temp.add(obj.getPhone());// ��ϵ��ʽ
					temp.add(obj.getEmail());// ��������
					temp.add(obj.getAddress());// ��ͥסַ
					temp.add(obj.getHire_date());// ��ְ����
					temp.add(obj.getAccount_id());// �˺�id
					temp.add(accountsDAO.getAccountsName(obj.getAccount_id()));// �˺�����
					temp.add(obj.getJob_id());// ְλid
					temp.add(jobDAO.getJobName(obj.getJob_id()));// ְλ����
					temp.add(obj.getDepartment_id());// ����id
					temp.add(departmentDAO.getDepartmentName(obj
							.getDepartment_id()));// ��������
					// ������Ҫ����Ա���Ĺ�����Ϣ�����ڴ˴�ֱ��ͨ��ְλid��Ӧ�Ļ�ȡ��ְλ�ϵ�Ա����Ϣ��ֱ�ӽ�����ʾ
					// ��ȡ�������ʡ�������ɼ���
					Job job = jobDAO.getJobById(obj.getJob_id());
					temp.add(job.getBase_salary());
					temp.add(job.getCommission_rate());
					// ���أ�3 4 5 8 10 12 14
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public List<Employee> getAllEmployeeByPage(int page) throws SQLException {
		return employeeDAO.getAllEmployeeByPage(page);
	}

	@Override
	public int getEmployeeMaxPage() throws SQLException {
		// ����ҳ���� 10��������Ϊ1ҳ
		BigDecimal i = BigDecimal.valueOf(Integer.valueOf(employeeDAO
				.getEmployeeCount().toString()));
		BigDecimal i2 = BigDecimal.valueOf(10);
		return i.divide(i2).intValue() + 1;
	}

	@Override
	public int exportData(String[] empIds) {
		try {
			// ��������Ŀ¼
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// ����������
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;
			// ������һ�б�ͷ����
			cell = row.createCell((short) 0);
			cell.setCellValue("Ա�����");
			cell = row.createCell((short) 1);
			cell.setCellValue("����");
			cell = row.createCell((short) 2);
			cell.setCellValue("���֤��");
			cell = row.createCell((short) 3);
			cell.setCellValue("��ϵ��ʽ");
			cell = row.createCell((short) 4);
			cell.setCellValue("��������");
			cell = row.createCell((short) 5);
			cell.setCellValue("��ͥסַ");
			cell = row.createCell((short) 6);
			cell.setCellValue("��ְ����");
			cell = row.createCell((short) 7);
			cell.setCellValue("ʹ���˺�");
			cell = row.createCell((short) 8);
			cell.setCellValue("ְλ");
			cell = row.createCell((short) 9);
			cell.setCellValue("����");
			/**
			 * Ĭ���ǽ�ȫ�����ݵ���
			 * ����ͨ���û�ѡ����Ӧ�����ݽ��е���
			 */
			List<Employee> list = null;
			if(empIds!=null){
				list = this.findAllEmployeeByIds(empIds);
			}else{
				list = employeeDAO.findAllEmployee();
			}
			for (int i = 0; i < list.size(); i++) {
				Employee emp = list.get(i);
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				cell.setCellValue(emp.getEmployee_num());
				cell = row.createCell(1);
				cell.setCellValue(emp.getEmployee_name());
				cell = row.createCell(2);
				cell.setCellValue(emp.getId_card());
				cell = row.createCell(3);
				cell.setCellValue(emp.getPhone());
				cell = row.createCell(4);
				cell.setCellValue(emp.getEmail());
				cell = row.createCell(5);
				cell.setCellValue(emp.getAddress());
				cell = row.createCell(6);
				cell.setCellValue(emp.getHire_date());
				cell = row.createCell(7);
				cell.setCellValue(accountsDAO.getAccountsName(emp.getAccount_id()).toString());
				cell = row.createCell(8);
				cell.setCellValue(jobDAO.getJobName(emp.getJob_id()).toString());
				cell = row.createCell(9);
				cell.setCellValue(departmentDAO.getDepartmentName(emp.getDepartment_id()).toString());
			}
		//�����ļ�ѡ���  
	    JFileChooser chooser = new JFileChooser();  
	    //��׺��������  
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(  
	            "����ļ�(*.xlsx)", "xlsx");  
	    chooser.setFileFilter(filter);  
	    //����ķ�����������ֱ�����û����±��水ť�ҡ��ļ������ı���Ϊ�ա����û�����ȡ����ť��  
	    int option = chooser.showSaveDialog(null);  
	    if(option==JFileChooser.APPROVE_OPTION){    //�����û�ѡ���˱���  
	        File file = chooser.getSelectedFile();  
	        String fname = chooser.getName(file);   //���ļ���������л�ȡ�ļ���  
	        //�����û���д���ļ������������ƶ��ĺ�׺������ô���Ǹ������Ϻ�׺  
	        if(fname.indexOf(".xlsx")==-1){  
	            file=new File(chooser.getCurrentDirectory(),fname+".xlsx");  
	        }  
	        try {  
	            FileOutputStream FOut = new FileOutputStream(file);
	            workbook.write(FOut);
	            FOut.flush();
	            FOut.close();
				workbook.close();
				return 1;
	        } catch (IOException e) {  
	            System.err.println("IO�쳣");  
	            e.printStackTrace();  
	        }     
	    }  
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public List<Employee> findAllEmployeeByIds(String[] ids)
			throws SQLException {
		// ѭ������id��Ϣ
		List<Employee> list = new ArrayList<Employee>();
		for (String id : ids) {
			Employee emp = employeeDAO.getEmployeeById(id);
			list.add(emp);
		}
		return list;
	}

	@Override
	public String getEmployeeNumNextSeq() throws SQLException {
		return (String) employeeDAO.getEmployeeNumNextSeq();
	}

	@Override
	public int exportSalaryData(String[] empIds) {
		try {
			// ��������Ŀ¼
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// ����������
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;
			// ������һ�б�ͷ����
			cell = row.createCell((short) 0);
			cell.setCellValue("Ա�����");
			cell = row.createCell((short) 1);
			cell.setCellValue("����");
			cell = row.createCell((short) 2);
			cell.setCellValue("���֤��");
			cell = row.createCell((short) 3);
			cell.setCellValue("��ϵ��ʽ");
			cell = row.createCell((short) 4);
			cell.setCellValue("��ְְλ");
			cell = row.createCell((short) 5);
			cell.setCellValue("��������");
			cell = row.createCell((short) 6);
			cell.setCellValue("��������");
			cell = row.createCell((short) 7);
			cell.setCellValue("�������");
			cell = row.createCell((short) 8);
			cell.setCellValue("ʵ�ʹ���");
			/**
			 * Ĭ���ǽ�ȫ�����ݵ���
			 * ����ͨ���û�ѡ����Ӧ�����ݽ��е���
			 */
			List<Employee> list = null;
			if(empIds!=null){
				list = this.findAllEmployeeByIds(empIds);
			}else{
				list = employeeDAO.findAllEmployee();
			}
			for (int i = 0; i < list.size(); i++) {
				Employee emp = list.get(i);
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				cell.setCellValue(emp.getEmployee_num());
				cell = row.createCell(1);
				cell.setCellValue(emp.getEmployee_name());
				cell = row.createCell(2);
				cell.setCellValue(emp.getId_card());
				cell = row.createCell(3);
				cell.setCellValue(emp.getPhone());
				cell = row.createCell(4);
				cell.setCellValue(emp.getEmail());
				cell = row.createCell(5);
				cell.setCellValue(jobDAO.getJobName(emp.getJob_id()).toString());
				cell = row.createCell(6);
				cell.setCellValue(departmentDAO.getDepartmentName(emp.getDepartment_id()).toString());
				cell = row.createCell(7);
				Job job = jobDAO.getJobById(emp.getJob_id());
				cell.setCellValue(job.getBase_salary());
				cell = row.createCell(8);
				cell.setCellValue(job.getCommission_rate());
				cell = row.createCell(9);
				// ���㹤��
				double count = ((int)(job.getBase_salary()*(1+job.getCommission_rate())*100))/100;
				cell.setCellValue(count);
			}
		//�����ļ�ѡ���  
	    JFileChooser chooser = new JFileChooser();  
	    //��׺��������  
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(  
	            "����ļ�(*.xlsx)", "xlsx");  
	    chooser.setFileFilter(filter);  
	    //����ķ�����������ֱ�����û����±��水ť�ҡ��ļ������ı���Ϊ�ա����û�����ȡ����ť��  
	    int option = chooser.showSaveDialog(null);  
	    if(option==JFileChooser.APPROVE_OPTION){    //�����û�ѡ���˱���  
	        File file = chooser.getSelectedFile();  
	        String fname = chooser.getName(file);   //���ļ���������л�ȡ�ļ���  
	        //�����û���д���ļ������������ƶ��ĺ�׺������ô���Ǹ������Ϻ�׺  
	        if(fname.indexOf(".xlsx")==-1){  
	            file=new File(chooser.getCurrentDirectory(),fname+".xlsx");  
	        }  
	        try {  
	            FileOutputStream FOut = new FileOutputStream(file);
	            workbook.write(FOut);
	            FOut.flush();
	            FOut.close();
				workbook.close();
				return 1;
	        } catch (IOException e) {  
	            System.err.println("IO�쳣");  
	            e.printStackTrace();  
	        }     
	    }  
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	// ���ʽ���ͳ�ƣ���ȡÿ�����ŵ�������������ͳ��
	@Override
	public Map<String, Double> getSalarySumByDeptId() throws SQLException {
		Map<String, Double> map = new HashMap<String, Double>();
		List<Department> list_dept = departmentDAO.findAllDepartment();
		for(Department d : list_dept){
			String deptId = d.getDepartment_id();
			Double sum = Double.valueOf(employeeDAO.getSalarySumByDeptId(deptId).toString());
			map.put(deptId, sum);
		}
		return map;
	}

	@Override
	public Map<String, Integer> getEmployeeSumByDeptId() throws SQLException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<Department> list_dept = departmentDAO.findAllDepartment();
		for(Department d : list_dept){
			String deptId = d.getDepartment_id();
			int sum = Integer.valueOf(employeeDAO.getEmployeeSumByDeptId(deptId).toString());
			map.put(deptId, sum);
		}
		return map;
	}

	@Override
	public int exportBalanceData(JTable table) {
		try {
			// ��������Ŀ¼
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// ����������
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;
			// ������һ�б�ͷ����
			cell = row.createCell((short) 0);
			cell.setCellValue("��������");
			cell = row.createCell((short) 1);
			cell.setCellValue("��������");
			cell = row.createCell((short) 2);
			cell.setCellValue("�ܹ���");
			cell = row.createCell((short) 3);
			cell.setCellValue("ƽ������");
			/**
			 * Ĭ���ǽ�ȫ�����ݵ���
			 */
			for (int i = 0; i < table.getRowCount(); i++) {
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				cell.setCellValue(table.getValueAt(i, 0).toString());
				cell = row.createCell(1);
				cell.setCellValue(table.getValueAt(i, 1).toString());
				cell = row.createCell(2);
				cell.setCellValue(table.getValueAt(i, 2).toString());
				cell = row.createCell(3);
				cell.setCellValue(table.getValueAt(i, 3).toString());
			}
		//�����ļ�ѡ���  
	    JFileChooser chooser = new JFileChooser();  
	    //��׺��������  
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(  
	            "����ļ�(*.xlsx)", "xlsx");  
	    chooser.setFileFilter(filter);  
	    //����ķ�����������ֱ�����û����±��水ť�ҡ��ļ������ı���Ϊ�ա����û�����ȡ����ť��  
	    int option = chooser.showSaveDialog(null);  
	    if(option==JFileChooser.APPROVE_OPTION){    //�����û�ѡ���˱���  
	        File file = chooser.getSelectedFile();  
	        String fname = chooser.getName(file);   //���ļ���������л�ȡ�ļ���  
	        //�����û���д���ļ������������ƶ��ĺ�׺������ô���Ǹ������Ϻ�׺  
	        if(fname.indexOf(".xlsx")==-1){  
	            file=new File(chooser.getCurrentDirectory(),fname+".xlsx");  
	        }  
	        try {  
	            FileOutputStream FOut = new FileOutputStream(file);
	            workbook.write(FOut);
	            FOut.flush();
	            FOut.close();
				workbook.close();
				return 1;
	        } catch (IOException e) {  
	            System.err.println("IO�쳣");  
	            e.printStackTrace();  
	        }     
	    }  
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
}
