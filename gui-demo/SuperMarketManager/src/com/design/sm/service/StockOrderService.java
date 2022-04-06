package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.StockOrder;

public interface StockOrderService {

	// ���Ӷ�����ϸ��¼
	public void addStockOrder(StockOrder t) throws SQLException;

	// ͨ�����������ȡ������ϸ��¼��Ϣ
	public List<StockOrder> getStockOrderBySMId(String smId)
			throws SQLException;
	
	// ��װList<StockOrder>��Ϣ
	public Vector<Vector> pack(List<StockOrder> list) throws SQLException ;
}
