package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.Vendor;

public interface VendorDAO {
	//���ݹ�Ӧ��id��ȡ��Ӧ������
	public Object getVendorName(String id)throws SQLException;
	
	//��ȡ���й�Ӧ����Ϣ
	public List<Vendor> findAllVendor()throws SQLException;
	
	//������Ӧ����Ϣ
	public void addVendor(Vendor c)throws SQLException;
	
	//�޸Ĺ�Ӧ����Ϣ
	public void updateVendor(Vendor c)throws SQLException;
	
	//����idɾ����Ӧ����Ϣ
	public void deleteVendor(String id)throws SQLException;
	
	//���ݹ�Ӧ�����ƹؼ��ֻ�ȡ��Ӧ����Ϣ
	public List<Vendor> getVendorByNameKeyword(String keyword)throws SQLException;
	
	//���ݹ�Ӧ��id��ȡ��Ӧ����Ϣ
	public Vendor getVendorById(String id)throws SQLException;
	
}
