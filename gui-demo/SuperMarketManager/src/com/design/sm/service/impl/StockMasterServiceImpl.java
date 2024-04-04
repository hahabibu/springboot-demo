package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.EmployeeDAO;
import com.design.sm.dao.StockMasterDAO;
import com.design.sm.dao.VendorContactDAO;
import com.design.sm.dao.VendorDAO;
import com.design.sm.dao.impl.EmployeeDAOImpl;
import com.design.sm.dao.impl.StockMasterDAOImpl;
import com.design.sm.dao.impl.VendorContactDAOImpl;
import com.design.sm.dao.impl.VendorDAOImpl;
import com.design.sm.model.StockMaster;
import com.design.sm.service.StockMasterService;

public class StockMasterServiceImpl implements StockMasterService {

	private StockMasterDAO stockMasterDAO = new StockMasterDAOImpl();
	private VendorDAO vendorDAO = new VendorDAOImpl();
	private VendorContactDAO contactDAO = new VendorContactDAOImpl();
	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

	@Override
	public void addStockMaster(StockMaster sm) throws SQLException {
		stockMasterDAO.addStockMaster(sm);
	}

	@Override
	public void updateStockMaster(StockMaster sm) throws SQLException {
		stockMasterDAO.updateStockMaster(sm);
	}

	@Override
	public void deleteStockMaster(String id) throws SQLException {
		stockMasterDAO.deleteStockMaster(id);
	}

	@Override
	public void joinRecycleBin(String id) throws SQLException {
		stockMasterDAO.setDeleteFlag(id, 1);
	}

	@Override
	public void revokeRecycleBin(String id)
			throws SQLException {
		stockMasterDAO.setDeleteFlag(id, 0);
	}

	@Override
	public List<StockMaster> findAllStockInList() throws SQLException {
		return stockMasterDAO.findAllStockList(0);
	}

	@Override
	public List<StockMaster> finfAllStockOutList() throws SQLException {
		return stockMasterDAO.findAllStockList(1);
	}

	@Override
	public void rejectProduct(String id) throws SQLException {
		stockMasterDAO.setSign(id, 1);
	}

	@Override
	public void cancelRejectProduct(String id) throws SQLException {
		stockMasterDAO.setSign(id, 1);
	}

	@Override
	public void commitStockMaster(String id) throws SQLException {
		stockMasterDAO.setState(id, 0);
	}

	@Override
	public void cancelStockMaster(String id) throws SQLException {
		stockMasterDAO.setState(id, -1);
	}

	@Override
	public void passStockMaster(String id) throws SQLException {
		stockMasterDAO.setState(id, 1);
	}

	@Override
	public Vector<Vector> pack(List<StockMaster> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (StockMaster obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 15; i++) {
					temp.add(obj.getStock_master_id());// ��������id
					temp.add(obj.getOrder_num());// �������
					temp.add(obj.getHandler_id());// ������id
					temp.add(employeeDAO.getEmployeeName(obj.getHandler_id()));//����������
					temp.add(obj.getVendor_id());// ��Ӧ��id
					temp.add(vendorDAO.getVendorName(obj.getVendor_id()));// ��Ӧ������
					temp.add(obj.getContact_id());// ��Ӧ����ϵ��id
					temp.add(contactDAO.getVendorContactName(obj
							.getContact_id()));// ��Ӧ����ϵ������
					temp.add(obj.getHandle_time());// ����ʱ��
					temp.add(obj.getSign());// ������ʶ
					temp.add(this.getSignMean(obj.getSign()));// ������ʶ����
					temp.add(obj.getDelete_flag());// ɾ����ʶ
					temp.add(this.getDeleteFlagMean(obj.getDelete_flag()));// ɾ����ʶ����
					temp.add(obj.getState());// ״̬��ʶ
					temp.add(this.getStateMean(obj.getState()));// ״̬��ʶ����
				}
				// ���� 2 4 6 9 11 13
				rows.add(temp);
			}
		}
		return rows;
	}

	/**
	 * ���岻ͬ�ķ������ò�ͬ��ʶ��Ӧ�ĺ���
	 */
	public String getSignMean(int sign) {
		String str = "";
		if(sign==0){
			str = "���";
		}else if(sign==1){
			str = "����";
		}
		return str;
	}

	public String getDeleteFlagMean(int sign) {
		String str = "";
		if(sign==0){
			str = "����";
		}else if(sign==1){
			str = "��ɾ��";
		}
		return str;
	}

	public String getStateMean(int sign) {
		String str = "";
		if(sign==-1){
			str = "���δͨ��";
		}else if(sign==0){
			str = "���ύ";
		}else if(sign==1){
			str = "���ͨ��";
		}
		return str;
	}

	@Override
	public String getStockInNextSeq() throws SQLException {
		return stockMasterDAO.getStockInNextSeq()+"";
	}

	@Override
	public String getSMOrderNumById(String id) throws SQLException {
		return stockMasterDAO.getSMOrderNumById(id)+"";
	}

	@Override
	public List<StockMaster> findAllStockListByState(int sign, int state)
			throws SQLException {
		return stockMasterDAO.findAllStockListByState(sign, state);
	}

	@Override
	public List<StockMaster> findAllStockListByTimeUnionState(int sign,
			String start, String end, int state) throws SQLException {
		return stockMasterDAO.findAllStockListByTimeUnionState(sign,start, end, state);
	}

	@Override
	public List<StockMaster> findAllStockListRecycleBin(int sign)
			throws SQLException {
		return stockMasterDAO.findAllStockListRecycleBin(sign);
	}

	@Override
	public StockMaster getStockMasterByOrderNum(String orderNum)
			throws SQLException {
		return stockMasterDAO.getStockMasterByOrderNum(orderNum);
	}

}
