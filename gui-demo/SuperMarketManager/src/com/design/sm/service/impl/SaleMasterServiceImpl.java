package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.CustomerDAO;
import com.design.sm.dao.EmployeeDAO;
import com.design.sm.dao.SaleMasterDAO;
import com.design.sm.dao.impl.CustomerDAOImpl;
import com.design.sm.dao.impl.EmployeeDAOImpl;
import com.design.sm.dao.impl.SaleMasterDAOImpl;
import com.design.sm.model.SaleMaster;
import com.design.sm.model.SaleMaster;
import com.design.sm.service.SaleMasterService;

public class SaleMasterServiceImpl implements SaleMasterService {

	private SaleMasterDAO saleMasterDAO = new SaleMasterDAOImpl();
	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	private CustomerDAO customerDAO = new CustomerDAOImpl();

	@Override
	public void addSaleMaster(SaleMaster sm) throws SQLException {
		saleMasterDAO.addSaleMaster(sm);
	}

	@Override
	public void updateSaleMaster(SaleMaster sm) throws SQLException {
		saleMasterDAO.updateSaleMaster(sm);
	}

	@Override
	public void deleteSaleMaster(String id) throws SQLException {
		saleMasterDAO.deleteSaleMaster(id);
	}

	@Override
	public void joinRecycleBin(String id) throws SQLException {
		saleMasterDAO.setDeleteFlag(id, 1);
	}

	@Override
	public void revokeRecycleBin(String id) throws SQLException {
		saleMasterDAO.setDeleteFlag(id, 0);
	}

	@Override
	public List<SaleMaster> findAllSaleList() throws SQLException {
		return saleMasterDAO.findAllSaleList();
	}

	@Override
	public Object getSaleNextSeq() throws SQLException {
		return saleMasterDAO.getSaleNextSeq();
	}

	@Override
	public Object getSOOrderNumById(String id) throws SQLException {
		return saleMasterDAO.getSOOrderNumById(id);
	}

	@Override
	public List<SaleMaster> findAllStockListByTimeUnionState(String start,
			String end, int state) throws SQLException {
		return saleMasterDAO.findAllSaleListByTimeUnionState(start, end, state);
	}

	@Override
	public List<SaleMaster> findAllSaleListRecycleBin() throws SQLException {
		return saleMasterDAO.findAllSaleListRecycleBin();
	}

	@Override
	public Vector<Vector> pack(List<SaleMaster> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (SaleMaster obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 15; i++) {
					temp.add(obj.getSale_master_id());// ��������id
					temp.add(obj.getOrder_num());// �������
					temp.add(obj.getHandler_id());// ������id
					temp.add(employeeDAO.getEmployeeName(obj.getHandler_id()));// ����������
					if(obj.getCustomer_id()==null){
						temp.add("��");
						temp.add("��");
					}else{
						temp.add(obj.getCustomer_id());// �˿�id
						temp.add(customerDAO.getCustomerName(obj.getCustomer_id()));// �˿�����
					}
					temp.add(obj.getHandle_time());// ����ʱ��
					temp.add(obj.getDelete_flag());// ɾ����ʶ
					temp.add(this.getDeleteFlagMean(obj.getDelete_flag()));// ɾ����ʶ����
					temp.add(obj.getState());// ״̬��ʶ
					temp.add(this.getStateMean(obj.getState()));// ״̬��ʶ����
				}
				// ���� 2 4 7 9 10
				rows.add(temp);
			}
		}
		return rows;
	}

	/**
	 * ���岻ͬ�ķ������ò�ͬ��ʶ��Ӧ�ĺ���
	 */
	public String getDeleteFlagMean(int sign) {
		String str = "";
		if (sign == 0) {
			str = "����";
		} else if (sign == 1) {
			str = "��ɾ��";
		}
		return str;
	}

	public String getStateMean(int sign) {
		String str = "";
		if (sign == -1) {
			str = "���δͨ��";
		} else if (sign == 0) {
			str = "���ύ";
		} else if (sign == 1) {
			str = "���ͨ��";
		}
		return str;
	}

}
