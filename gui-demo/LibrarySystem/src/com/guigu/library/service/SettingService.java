package com.guigu.library.service;

import java.sql.SQLException;

import com.guigu.library.model.Setting;

public interface SettingService {

	// ���������Ϣ
	public void addSetting(Setting s) throws SQLException;

	// �޸�������Ϣ
	public void updateSetting(Setting s) throws SQLException;

	// ����reader_idɾ��������Ϣ
	public void deleteSetting(String reader_id) throws SQLException;

	// ����reader_id��ȡ������Ϣ
	public Setting getSettingByReaderId(String reader_id) throws SQLException;

	// ���ݵ�ǰ�û����˺�id��ȡ��ǰ�û�������Ȩ��
	public Setting getUsersSettingById(String user_id) throws SQLException;
}
