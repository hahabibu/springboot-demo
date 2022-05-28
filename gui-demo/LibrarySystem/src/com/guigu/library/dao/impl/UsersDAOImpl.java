package com.guigu.library.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.dao.UsersDAO;
import com.guigu.library.model.Setting;
import com.guigu.library.model.Users;

public class UsersDAOImpl extends BaseDAOImpl<Users> implements UsersDAO{

	//�����û������û�������ҵ�¼�û�
	@Override
	public Users loginUsers(Users user) throws SQLException {
		String sql = "select * from users where user_name=? and user_password=?";
		Object[] args = {user.getUser_name(),user.getUser_password()};
		Users loginUsers = this.get(conn, sql, args);
		return loginUsers;
	}

	//����û���Ϣ
	@Override
	public void addUsers(Users user) throws SQLException {
		String sql = "insert into users values(?,?,?,?)";
		Object[] args = {user.getUser_id(),user.getUser_name(),user.getUser_password(),user.getUser_limits()};
		this.update(conn, sql, args);
	}

	//ɾ���û���Ϣ
	@Override
	public void deleteUsers(String id) throws SQLException {
		String sql = "delete from users where user_id=?";
		this.update(conn, sql, id);
	}
	
	//�޸��û���Ϣ
	@Override
	public void updateUsers(Users user) throws SQLException {
		String sql = "update users set user_name=?,user_password=?,user_limits=? where user_id=?";
		Object[] args = {user.getUser_name(),user.getUser_password(),user.getUser_limits(),user.getUser_id()};
		this.update(conn, sql, args);
	}

	//���������û���Ϣ
	@Override
	public List<Users> findAllUsers() throws SQLException {
		String sql = "select * from users";
		return this.getForList(conn, sql);
	}

	//�����û�id��ȡ�û�����
	@Override
	public Object getUsersName(String id) throws SQLException {
		String sql = "select user_name from users where user_id=?";
		return this.getForValue(conn, sql, id);
	}

	@Override
	public List<Users> getUsersByNameKeyword(String keyword) throws SQLException {
		String sql = "select * from users where user_name like ?";
		return this.getForList(conn, sql, keyword);
	}

	@Override
	public Object getUsersLimits(String id) throws SQLException {
		String sql = "select user_limits from users where user_id=?";
		return this.getForValue(conn, sql, id);
	}

	@Override
	public Users getUsersByName(String username) throws SQLException {
		String sql = "select * from users where user_name=?";
		return this.get(conn, sql, username);
	}

	@Override
	public void setUsersLimits(String id,int limits) throws SQLException {
		String sql = "update users set user_limits=? where user_id=?";
		Object[] args = {limits,id};
		this.update(conn, sql, args);
	}

	@Override
	public Users getUsersById(String id) throws SQLException {
		String sql = "select * from users where user_id=?";
		return this.get(conn, sql, id);
	}

}
