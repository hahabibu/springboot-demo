package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.SaleMaster;
import com.design.sm.model.StockMaster;

public interface StockMasterService {

	// ���ӳ���/����¼
	public void addStockMaster(StockMaster sm) throws SQLException;

	// �޸ĳ�������¼
	public void updateStockMaster(StockMaster sm) throws SQLException;

	// ��������id����ɾ����������¼
	public void deleteStockMaster(String id) throws SQLException;

	// ��������id����¼�������վ���޸�ɾ����ʶ��
	public void joinRecycleBin(String id) throws SQLException;

	// ��������id����¼�ӻ���վ�г��أ��޸�ɾ����ʶ��
	public void revokeRecycleBin(String id) throws SQLException;

	// ��ѯ���е�����¼�б�
	public List<StockMaster> findAllStockInList() throws SQLException;

	// ��ѯ���еĳ����¼�б���Ʒ�˻ظ���Ӧ�̵ĳ��������
	public List<StockMaster> finfAllStockOutList() throws SQLException;

	// ��������id��ɳ��⣨�˻�������
	public void rejectProduct(String id) throws SQLException;

	// ��������idȡ�����⣨�˻�������
	public void cancelRejectProduct(String id) throws SQLException;

	// ��������id�޸Ķ�������״̬(�����ύ)
	public void commitStockMaster(String id) throws SQLException;

	// ��������id�޸Ķ�������״̬(����������ͨ��)
	public void cancelStockMaster(String id) throws SQLException;

	// ��������id�޸Ķ�������״̬(��������ͨ��)
	public void passStockMaster(String id) throws SQLException;

	// ��List<StockMaster>��Ϣ��װΪVector<Vector>��¼
	public Vector<Vector> pack(List<StockMaster> list) throws SQLException;

	// ��ȡ��ǰ������ŵ�����
	public String getStockInNextSeq() throws SQLException;

	// ���ݶ���id��ȡ�������
	public String getSMOrderNumById(String id) throws SQLException;

	// ���ݶ�������ĳ����Ͷ�������״̬���Ҽ�¼
	public List<StockMaster> findAllStockListByState(int sign, int state)
			throws SQLException;

	// ������Ʒ�Ķ�������ʱ���Լ���Ӧ�Ķ�������״̬��ȡ������Ϣ
	public List<StockMaster> findAllStockListByTimeUnionState(int sign,
			String start, String end, int state) throws SQLException;

	// ��ȡ��Ʒ���\���ⵥ�Ļ���վ�Ķ�����Ϣ��ɾ����ʶΪ1��
	public List<StockMaster> findAllStockListRecycleBin(int sign)throws SQLException;
	
	// ���ݶ�����Ż�ȡ����������Ϣ
	public StockMaster getStockMasterByOrderNum(String orderNum)throws SQLException;
	
}
