package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Vendor;

public interface VendorService {
	// ���ݹ�Ӧ��id��ȡ��Ӧ������
	public Object getVendorName(String id) throws SQLException;

	// ��ȡ���й�Ӧ����Ϣ�б�
	public List<Vendor> findAllVendorList() throws SQLException;

	// ��ȡ���й�Ӧ�̼�¼
	public Vector<Vector> findAllVendorVector() throws SQLException;

	// ������Ӧ����Ϣ
	public void addVendor(Vendor v) throws SQLException;

	// �޸Ĺ�Ӧ����Ϣ
	public void updateVendor(Vendor v) throws SQLException;

	// ����idɾ����Ӧ����Ϣ
	public void deleteVendor(String id) throws SQLException;

	// ���ݹ�Ӧ�����ƹؼ��ֻ�ȡ��Ӧ����Ϣ
	public Vector<Vector> getVendorByNameKeyword(String keyword)
			throws SQLException;
	
	// �����ṩ�Ĺ�Ӧ��id�����ȡ��Ӧ�Ĺ�Ӧ����Ϣ
	public List<Vendor> findAllVendorByIds(String[] vendorIds)throws SQLException;
	/**
	 * �Զ��嵼������
	 */
	public int exportData(String[] vendorIds)throws SQLException;
}
