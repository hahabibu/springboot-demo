package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Warehouse;

public interface WarehouseService {

	// ͨ���ֿ�id��ȡ�ֿ�����
	public Object getWarehouseName(String id) throws SQLException;

	// ��ȡ��ǰ���ݿ����вֿ���Ϣ�б�
	public List<Warehouse> findAllWarehouseList() throws SQLException;

	// ��ȡ��ǰ���ݿ����вֿ���Ϣ��¼
	public Vector<Vector> findAllWarehouseVector() throws SQLException;

	// �����ֿ���Ϣ
	public void addWarehouse(Warehouse w) throws SQLException;

	// �޸Ĳֿ���Ϣ
	public void updateWarehouse(Warehouse w) throws SQLException;

	// ����ɾ���ֿ���Ϣ
	public void deleteWarehouse(String id) throws SQLException;
	
	//���������ֿ����ƹؼ��ֻ�ȡ�ֿ���Ϣ
	public Vector<Vector> getWarehouseByNameKeyword(String keyword) throws SQLException;

}
