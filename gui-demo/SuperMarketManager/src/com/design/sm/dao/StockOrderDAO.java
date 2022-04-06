package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.StockOrder;
import com.design.sm.model.StockOrder;

public interface StockOrderDAO extends BaseDAO<StockOrder> {

	// ���Ӷ�����ϸ��¼
	public void addStockOrder(StockOrder t) throws SQLException;

//	// �޸Ķ�����ϸ��¼
//	public void update(StockOrder t) throws SQLException;
//
//	// ɾ��������ϸ��¼
//	public void deleteStockOrder(String id) throws SQLException;
//
//	// ��ȡ���еĶ�����ϸ��¼
//	public List<StockOrder> findAllStockOrderList() throws SQLException;

	// ͨ�����������ȡ������ϸ��¼��Ϣ
	public List<StockOrder> getStockOrderBySMId(String smId) throws SQLException;

}
