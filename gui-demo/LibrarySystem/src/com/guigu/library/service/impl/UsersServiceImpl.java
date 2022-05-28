package com.guigu.library.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.dao.UsersDAO;
import com.guigu.library.dao.impl.UsersDAOImpl;
import com.guigu.library.model.Users;
import com.guigu.library.service.UsersService;

public class UsersServiceImpl implements UsersService {

	private UsersDAO userDAO = new UsersDAOImpl();

	@Override
	public Users loginUsers(Users user) throws SQLException {
		return userDAO.loginUsers(user);
	}

	@Override
	public void addUsers(Users user) throws SQLException {
		userDAO.addUsers(user);
	}

	@Override
	public void deleteUsers(String id) throws SQLException {
		userDAO.deleteUsers(id);
	}

	@Override
	public void updateUsers(Users user) throws SQLException {
		userDAO.updateUsers(user);
	}

	@Override
	public Vector<Vector> findAllUsersVector() throws SQLException {
		List<Users> list = userDAO.findAllUsers();
		return this.pack(list);
	}

	@Override
	public String getUsersName(String id) throws SQLException {
		return userDAO.getUsersName(id)+"";
	}

	@Override
	public Vector<Vector> getUsersByNameKeyword(String keyword)
			throws SQLException {
		List<Users> list = userDAO.getUsersByNameKeyword(keyword);
		return this.pack(list);
	}

	// ��List<Users>��װΪVector<Vector>����
	public Vector<Vector> pack(List<Users> list) {
		// �ֶ���װ���ҵ���������Ϣ
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Users obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 5; i++) {
					temp.add(obj.getUser_id());// �û�id
					temp.add(obj.getUser_name());// �û�����
					temp.add(obj.getUser_password());// �û�����
					temp.add(obj.getUser_limits());// �û�Ȩ��
					temp.add(this.getUsersIdentity(obj.getUser_limits()));
				}
				rows.add(temp);
			}
		}
		return rows;
	}
	
	public String getUsersIdentity(int limits) {
		String identity = null;
		if (limits == 1) {
			identity = "��������Ա";
		} else if (limits == 2) {
			identity = "ͼ��ݹ���Ա";
		} else if (limits == 3) {
			identity = "����";
		} else if (limits == 0) {
			identity = "�����˻�";
		}
		return identity;
	}

	@Override
	public Object getUsersLimits(String id) throws SQLException {
		return userDAO.getUsersLimits(id);
	}

	@Override
	public boolean isValidUsersname(String username) throws SQLException {
		if(userDAO.getUsersByName(username)==null)
			return true;
		return false;
	}

	@Override
	public List<Users> findAllUsersList() throws SQLException {
		return userDAO.findAllUsers();
	}

	@Override
	public Users getUsersByName(String username) throws SQLException {
		return userDAO.getUsersByName(username);
	}

	@Override
	public void setUsersLimits(String id, int limits) throws SQLException {
		userDAO.setUsersLimits(id, limits);
	}

	@Override
	public Users getUsersById(String id) throws SQLException {
		return userDAO.getUsersById(id);
	}
}