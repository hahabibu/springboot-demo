package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.SaleOrder;

public interface SaleOrderService {
	// ���Ӷ�����ϸ��¼
	public void addSaleOrder(SaleOrder t) throws SQLException;

	// ͨ�����������ȡ������ϸ��¼��Ϣ
	public List<SaleOrder> getSaleOrderBySMId(String smId) throws SQLException;

	// ��װList<StockOrder>��Ϣ
	public Vector<Vector> pack(List<SaleOrder> list) throws SQLException;
}
