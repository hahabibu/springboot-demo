package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Vendor;
import com.design.sm.model.VendorContact;

public interface VendorContactService {

	// ���ݹ�Ӧ����ϵ��id��ȡ��Ӧ����ϵ������
	public Object getVendorContactName(String id) throws SQLException;

	// ��ȡ���еĹ�Ӧ����ϵ����Ϣ�б�
	public List<VendorContact> findAllVendorContactList() throws SQLException;

	// ��ȡ���еĹ�Ӧ����ϵ����Ϣ��¼
	public Vector<Vector> findAllVendorContactVector() throws SQLException;

	// ������Ӧ����ϵ����Ϣ
	public void addVendorContact(VendorContact vc) throws SQLException;

	// ����idɾ����Ӧ����ϵ����Ϣ
	public void deleteVendorContact(String id) throws SQLException;

	// ����id�޸Ĺ�Ӧ����ϵ����Ϣ
	public void updateVendorContact(VendorContact vc) throws SQLException;

	// ���ݹ�Ӧ��id��ȡ��Ӧ����ϵ���б�
	public List<VendorContact> getVendorContactByVendorId(String vendorId)
			throws SQLException;

	// ��װList<Vendor>��ϢΪVector<Vector>
	public Vector<Vector> pack(List<VendorContact> list) throws SQLException;

	// ���ݹ�Ӧ����ϵ��id��ȡ��Ӧ����ϵ����Ϣ
	public VendorContact getVendorContactByContactId(String contactId) throws SQLException;
}
