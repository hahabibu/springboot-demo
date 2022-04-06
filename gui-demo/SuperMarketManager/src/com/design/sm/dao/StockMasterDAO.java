package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.SaleMaster;
import com.design.sm.model.StockMaster;

public interface StockMasterDAO extends BaseDAO<StockMaster>{
	
	// ���ӳ���/����¼
	public void addStockMaster(StockMaster sm)throws SQLException;
	
	// �޸ĳ�������¼
	public void updateStockMaster(StockMaster sm)throws SQLException;
	
	// ��������id����ɾ����������¼
	public void deleteStockMaster(String id)throws SQLException;
	
	// ��������id����¼�������վ�򽫼�¼�ӻ���վ�г��أ��޸�ɾ����ʶ��
	public void setDeleteFlag(String id,int delete_flag)throws SQLException; 
	
	// ��ѯ���еĳ��⡢����¼�б�����sign��ʶ����ɸѡsign=0���  sign=1���⣩
	public List<StockMaster> findAllStockList(int sign)throws SQLException;
	
	// ��������id��ɳ��⣨�˻�����ȡ�����⣨�˻�������(sign=0���  sign=1����)
	public void setSign(String id,int sign)throws SQLException;
	
	// ��������id�޸Ķ�������״̬(�޸�state��0��ʾ�������ύ��δ��ˣ�  1��ʾ����ͨ�����  -1��ʾ����δͨ�����)
	public void setState(String id,int state)throws SQLException;
	
	// ��ȡ��ǰ������ŵ�����
	public Object getStockInNextSeq()throws SQLException;
	
	// ���ݶ���id��ȡ�������
	public Object getSMOrderNumById(String id)throws SQLException;
	
	// ���ݶ����ĳ�����ʶ�ʹ���״̬��ѯ������Ϣ
	public List<StockMaster> findAllStockListByState(int sign,int state)throws SQLException;
	
	// ������Ʒ�Ķ�������ʱ���Լ���Ӧ�Ķ�������״̬��ȡ������Ϣ
	public List<StockMaster> findAllStockListByTimeUnionState(int sign,String start,String end,int state)throws SQLException;

	// ��ȡ��Ʒ���\���ⵥ�Ļ���վ�Ķ�����Ϣ��ɾ����ʶΪ1��
	public List<StockMaster> findAllStockListRecycleBin(int sign)throws SQLException;
	
	public StockMaster getStockMasterByOrderNum(String orderNum)
			throws SQLException;
	
}
