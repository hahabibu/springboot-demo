package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.SaleOrder;

public interface SaleOrderDAO {
	// ���Ӷ�����ϸ��¼
	public void addSaleOrder(SaleOrder t) throws SQLException;

	// ͨ�����������ȡ������ϸ��¼��Ϣ
	public List<SaleOrder> getSaleOrderBySMId(String smId)
			throws SQLException;
}
