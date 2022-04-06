package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.VendorContact;

public interface VendorContactDAO extends BaseDAO<VendorContact>{
	
	//���ݹ�Ӧ����ϵ��id��ȡ��Ӧ����ϵ������
	public Object getVendorContactName(String id)throws SQLException;
	
	//��ȡ���еĹ�Ӧ����ϵ����Ϣ
	public List<VendorContact> findAllVendorContact() throws SQLException;
	
	//������Ӧ����ϵ����Ϣ
	public void addVendorContact(VendorContact vc)throws SQLException;
	
	//����idɾ����Ӧ����ϵ����Ϣ
	public void deleteVendorContact(String id)throws SQLException;
	
	//����id�޸Ĺ�Ӧ����ϵ����Ϣ
	public void updateVendorContact(VendorContact vc)throws SQLException;
	
	//���ݹ�Ӧ��id������Ӧ����ϵ���б�
	public List<VendorContact> getVendorContactByVendorId(String vendorId)
			throws SQLException;
	
	//���ݹ�Ӧ����ϵ��id��ȡ��Ӧ����ϵ����Ϣ
	public VendorContact getVendorContactByContactId(String contactId)throws SQLException;
}
