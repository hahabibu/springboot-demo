package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Accounts;

public interface AccountsService {

	// ��װ�û���¼����
	public Accounts loginAccounts(Accounts user) throws SQLException;

	// �����û�
	public void addAccounts(Accounts user) throws SQLException;

	// �����û�idɾ���û�
	public void deleteAccounts(String id) throws SQLException;

	// �޸�ָ���û�
	public void updateAccounts(Accounts user) throws SQLException;

	// ���������û���¼
	public Vector<Vector> findAllAccountsVector() throws SQLException;

	// ���������û��б�
	public List<Accounts> findAllAccountsList() throws SQLException;

	// ͨ���û�id�����û�����
	public Object getAccountsName(String id) throws SQLException;

	// �����û����ƹؼ��ֲ����û���Ϣ
	public Vector<Vector> getAccountsByNameKeyword(String keyword)
			throws SQLException;

	// �����˺�id��ȡ�˺�Ȩ��
	public Object getAccountsLimits(String id) throws SQLException;

	// �����û����жϵ�ǰ���û������Ƿ�Ϸ������������Ϊ�Ƿ�����false����������Ϊ�Ϸ�����true
	public boolean isValidAccountsname(String username) throws SQLException;

	// ͨ���û��ǳƲ����û���Ϣ
	public Accounts getAccountsByName(String username) throws SQLException;

	// �����˺�id�����˺�Ȩ��
	public void setAccountsLimits(String id, int limits) throws SQLException;

	// �����˺�id�����˻���Ϣ
	public Accounts getAccountsById(String id) throws SQLException;

}
