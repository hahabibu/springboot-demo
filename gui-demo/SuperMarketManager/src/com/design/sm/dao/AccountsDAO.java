package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.Accounts;

public interface AccountsDAO extends BaseDAO<Accounts> {

	// �����û���¼�ķ���
	public Accounts loginAccounts(Accounts user) throws SQLException;

	// �����û�
	public void addAccounts(Accounts user) throws SQLException;

	// �����û�idɾ���û�
	public void deleteAccounts(String id) throws SQLException;

	// �޸�ָ���û�
	public void updateAccounts(Accounts user) throws SQLException;

	// ���������û�
	public List<Accounts> findAllAccounts() throws SQLException;

	// �����û����ƹؼ��ֲ����û���Ϣ
	public List<Accounts> getAccountsByNameKeyword(String keyword)
			throws SQLException;

	// ͨ���û�id�����û�����
	public Object getAccountsName(String id) throws SQLException;

	// �����˺�id��ȡ�˺�Ȩ��
	public Object getAccountsLimits(String id) throws SQLException;

	// �����˺�id�����˺�Ȩ��
	public void setAccountsLimits(String id, int limits) throws SQLException;

	// �����û��������û���Ϣ
	public Accounts getAccountsByName(String username) throws SQLException;
	
	// �����˺�id�����˻���Ϣ
	public Accounts getAccountsById(String id)throws SQLException;

}
