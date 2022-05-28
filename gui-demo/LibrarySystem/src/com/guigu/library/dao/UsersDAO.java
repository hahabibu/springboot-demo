package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.Setting;
import com.guigu.library.model.Users;

public interface UsersDAO extends BaseDAO<Users> {

	// �����û���¼�ķ���
	public Users loginUsers(Users user) throws SQLException;

	// �����û�
	public void addUsers(Users user) throws SQLException;

	// �����û�idɾ���û�
	public void deleteUsers(String id) throws SQLException;

	// �޸�ָ���û�
	public void updateUsers(Users user) throws SQLException;

	// ���������û�
	public List<Users> findAllUsers() throws SQLException;

	// �����û����ƹؼ��ֲ����û���Ϣ
	public List<Users> getUsersByNameKeyword(String keyword) throws SQLException;

	// ͨ���û�id�����û�����
	public Object getUsersName(String id) throws SQLException;

	// �����˺�id��ȡ�˺�Ȩ��
	public Object getUsersLimits(String id) throws SQLException;

	// �����˺�id�����˺�Ȩ��
	public void setUsersLimits(String id, int limits) throws SQLException;

	// �����û��������û���Ϣ
	public Users getUsersByName(String username) throws SQLException;
	
	// �����˺�id��ȡ�û���Ϣ
	public Users getUsersById(String id) throws SQLException;
	
}
