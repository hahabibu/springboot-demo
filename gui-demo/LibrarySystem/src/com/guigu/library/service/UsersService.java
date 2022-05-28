package com.guigu.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.model.Setting;
import com.guigu.library.model.Users;

public interface UsersService {
	// ��װ�û���¼����
	public Users loginUsers(Users user) throws SQLException;

	// �����û�
	public void addUsers(Users user) throws SQLException;

	// �����û�idɾ���û�
	public void deleteUsers(String id) throws SQLException;

	// �޸�ָ���û�
	public void updateUsers(Users user) throws SQLException;

	// ���������û���¼
	public Vector<Vector> findAllUsersVector() throws SQLException;

	// ���������û��б�
	public List<Users> findAllUsersList() throws SQLException;

	// ͨ���û�id�����û�����
	public String getUsersName(String id) throws SQLException;

	// �����û����ƹؼ��ֲ����û���Ϣ
	public Vector<Vector> getUsersByNameKeyword(String keyword)
			throws SQLException;

	// �����˺�id��ȡ�˺�Ȩ��
	public Object getUsersLimits(String id) throws SQLException;

	// �����û����жϵ�ǰ���û������Ƿ���ڣ�������ڷ���true�������ڷ���false
	public boolean isValidUsersname(String username) throws SQLException;

	// ͨ���û��ǳƲ����û���Ϣ
	public Users getUsersByName(String username) throws SQLException;

	// �����˺�id�����˺�Ȩ��
	public void setUsersLimits(String id, int limits) throws SQLException;

	// �����˺�id��ȡ�û���Ϣ
	public Users getUsersById(String id) throws SQLException;

}
