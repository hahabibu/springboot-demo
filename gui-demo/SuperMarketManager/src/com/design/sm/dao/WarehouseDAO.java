package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.Warehouse;

public interface WarehouseDAO {
	// ͨ���ֿ�id��ȡ�ֿ�����
	public Object getWarehouseName(String id) throws SQLException;

	// ��ȡ��ǰ���ݿ����вֿ���Ϣ
	public List<Warehouse> findAllWarehouse() throws SQLException;

	// �����ֿ���Ϣ
	public void addWarehouse(Warehouse w) throws SQLException;

	// �޸Ĳֿ���Ϣ
	public void updateWarehouse(Warehouse w) throws SQLException;

	// ����ɾ���ֿ���Ϣ
	public void deleteWarehouse(String id) throws SQLException;
	
	//���������ֿ����ƹؼ��ֻ�ȡ������Ϣ
	public List<Warehouse> getWarehouseByNameKeyword(String keyword) throws SQLException;
}
