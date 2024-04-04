package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.StorageArea;

public interface StorageAreaDAO extends BaseDAO<StorageArea>{
	
	// �������еĴ洢����
	public List<StorageArea> findAllStorageArea()throws SQLException;

	// ���������Ż�ȡ������Ϣ
	public StorageArea getStorageAreaByNum(int num)throws SQLException;
}
