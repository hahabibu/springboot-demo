package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.AccountsDAO;
import com.design.sm.model.Accounts;

public class AccountsDAOImpl extends BaseDAOImpl<Accounts> implements AccountsDAO{
	// �����û������û�������ҵ�¼�û�
	@Override
	public Accounts loginAccounts(Accounts user) throws SQLException {
		String sql = "select * from accounts where username=? and password=?";
		Object[] args = { user.getUsername(), user.getPassword() };
		Accounts loginAccounts = this.get(conn, sql, args);
		return loginAccounts;
	}

	// ����û���Ϣ
	@Override
	public void addAccounts(Accounts user) throws SQLException {
		// �������10λ�ַ�������,��Ϊ�û�id
		String sql = "insert into accounts values(?,?,?,?)";
		Object[] args = { user.getAccount_id(), user.getUsername(),
				user.getPassword(), user.getLimits() };
		this.update(conn, sql, args);
	}

	// ɾ���û���Ϣ
	@Override
	public void deleteAccounts(String id) throws SQLException {
		String sql = "delete from accounts where account_id=?";
		this.update(conn, sql, id);
	}

	// �޸��û���Ϣ
	@Override
	public void updateAccounts(Accounts user) throws SQLException {
		String sql = "update accounts set username=?,password=?,limits=? where account_id=?";
		Object[] args = { user.getUsername(), user.getPassword(),
				user.getLimits(), user.getAccount_id() };
		this.update(conn, sql, args);
	}

	// ���������û���Ϣ
	@Override
	public List<Accounts> findAllAccounts() throws SQLException {
		String sql = "select * from accounts";
		return this.getForList(conn, sql);
	}

	// �����û�id��ȡ�û�����
	@Override
	public Object getAccountsName(String id) throws SQLException {
		String sql = "select username from accounts where account_id=?";
		return this.getForValue(conn, sql, id);
	}

	// ������Ӧ�Ĺؼ���ƥ���û������������Ϣ
	@Override
	public List<Accounts> getAccountsByNameKeyword(String keyword) throws SQLException {
		String sql = "select * from accounts where username like ?";
		return this.getForList(conn, sql, keyword);
	}

	// �����û�id��ȡ�û�Ȩ��
	@Override
	public Object getAccountsLimits(String id) throws SQLException {
		String sql = "select limits from accounts where account_id=?";
		return this.getForValue(conn, sql, id);
	}

	// �����û�������ȡ��ǰ�û����û�������Ϣ
	@Override
	public Accounts getAccountsByName(String username) throws SQLException {
		String sql = "select * from accounts where username=?";
		return this.get(conn, sql, username);
	}

	// ���ݵ�ǰ�û�id�����û���Ȩ��
	@Override
	public void setAccountsLimits(String id, int limits) throws SQLException {
		String sql = "update accounts set limits=? where account_id=?";
		Object[] args = { limits, id };
		this.update(conn, sql, args);
	}

	@Override
	public Accounts getAccountsById(String id) throws SQLException {
		String sql = "select * from accounts where account_id=?";
		return this.get(conn, sql, id);
	}
}
