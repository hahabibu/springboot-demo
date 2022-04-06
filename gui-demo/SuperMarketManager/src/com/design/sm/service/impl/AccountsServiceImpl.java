package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.AccountsDAO;
import com.design.sm.dao.impl.AccountsDAOImpl;
import com.design.sm.model.Accounts;
import com.design.sm.service.AccountsService;
import com.design.sm.utils.SecretKey;

public class AccountsServiceImpl implements AccountsService {

	private AccountsDAO accountsDAO = new AccountsDAOImpl();
	// �����û���¼�ķ���
	@Override
	public Accounts loginAccounts(Accounts user) throws SQLException {
		return accountsDAO.loginAccounts(user);
	}

	// ����û�
	@Override
	public void addAccounts(Accounts user) throws SQLException {
		accountsDAO.addAccounts(user);
	}

	// ɾ���û�
	@Override
	public void deleteAccounts(String id) throws SQLException {
		accountsDAO.deleteAccounts(id);
	}

	// �޸��û�
	@Override
	public void updateAccounts(Accounts user) throws SQLException {
		accountsDAO.updateAccounts(user);
	}

	// ��ȡ���е��û���¼
	@Override
	public Vector<Vector> findAllAccountsVector() throws SQLException {
		return this.pack(accountsDAO.findAllAccounts());
	}

	// ��ȡ���е��û��б�
	@Override
	public List<Accounts> findAllAccountsList() throws SQLException {
		return accountsDAO.findAllAccounts();
	}

	// �����û�id��ȡ�û��˺�����
	@Override
	public Object getAccountsName(String id) throws SQLException {
		return accountsDAO.getAccountsName(id);
	}

	// �����û����ƹؼ��ֲ����û���Ϣ
	@Override
	public Vector<Vector> getAccountsByNameKeyword(String keyword)
			throws SQLException {
		return this.pack(accountsDAO.getAccountsByNameKeyword(keyword));
	}

	// �����û�id��ȡָ�����û�Ȩ��
	@Override
	public Object getAccountsLimits(String id) throws SQLException {
		return accountsDAO.getAccountsLimits(id);
	}

	// �����û��������жϣ��жϵ�ǰ�û��Ƿ��Ѵ��ڣ������������Ϊ���Ϸ�����false���������������Ϊ�Ϸ�����true
	@Override
	public boolean isValidAccountsname(String username) throws SQLException {
		Accounts findAccount = accountsDAO.getAccountsByName(username);
		if(findAccount==null)
			return true;
		return false;
	}

	// �����û�����ȡ��ǰ��Ӧ�û�����Ϣ
	@Override
	public Accounts getAccountsByName(String username) throws SQLException {
		return accountsDAO.getAccountsByName(username);
	}

	// �����û�id�����û�Ȩ��
	@Override
	public void setAccountsLimits(String id, int limits) throws SQLException {
		accountsDAO.setAccountsLimits(id, limits);
	}

	// ��List<User>��װΪVector<Vector>����
	public Vector<Vector> pack(List<Accounts> list) {
		// �ֶ���װ���ҵ���������Ϣ
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Accounts obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 5; i++) {
					temp.add(obj.getAccount_id());// �û�id
					temp.add(obj.getUsername());// �û�����
					temp.add(obj.getPassword());// �û�����
					temp.add(SecretKey.encrypt(obj.getPassword()));//�û��������
					temp.add(obj.getLimits());// �û�Ȩ��
					temp.add(this.getUserIdentity(obj.getLimits()));//�û����
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	/**
	 * ͨ���û�Ȩ�޺Ż�ȡ�û������ 
	 * 0.�˺Ž���
	 * 1.��������Ա���ܹ������������ģ�� ��ǰ̨��̨��
	 * 2.��������Ա���ܹ�ά��������������ŵĹ�������
	 * 3.����Ա����ֻ�ܹ��鿴����������Ʒ��Ϣ ����ɶ�Ӧְλ�ϵĹ���
	 * Ȩ�޿��ƣ���������֮�䲻�ܿ�Խ�������ڲ�������Ӧ�ļ�ܻ���
	 */
	public String getUserIdentity(int limits) {
		String identity = null;
		if (limits == 1) {
			identity = "��������Ա";
		} else if (limits == 2) {
			identity = "����/����";
		} else if (limits == 3) {
			identity = "��ͨԱ��";
		}else if (limits == 0) {
			identity = "����";
		}
		return identity;
	}

	@Override
	public Accounts getAccountsById(String id) throws SQLException {
		return accountsDAO.getAccountsById(id);
	}
	
}
