package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.Customer;

public interface CustomerDAO extends BaseDAO<Customer> {

	// ���ݹ˿�id��ȡ�˿�����
	public Object getCustomerName(String id) throws SQLException;

	// ��ȡ���й˿���Ϣ
	public List<Customer> findAllCustomer() throws SQLException;

	// �����˿���Ϣ
	public void addCustomer(Customer c) throws SQLException;

	// �޸Ĺ˿���Ϣ
	public void updateCustomer(Customer c) throws SQLException;

	// ����idɾ���˿���Ϣ
	public void deleteCustomer(String id) throws SQLException;

	// ���ݹ˿����ƻ���ϵ��ʽ�ؼ��ֻ�ȡ�˿���Ϣ
	public List<Customer> getCustomerByKeyword(String keyword)
			throws SQLException;

	// ���ݹ˿�id��ȡ�˿���Ϣ
	public Customer getCustomerById(String id) throws SQLException;
	
	// ���ݹ˿���ϵ��ʽ��ȡ�˿���Ϣ
	public Customer getCustomerByPhone(String phone)throws SQLException;

}
