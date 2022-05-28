package com.guigu.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.model.StorageArea;

public interface StorageAreaService {

	// �������еĴ洢����
	public List<StorageArea> findAllStorageArea() throws SQLException;

	// ����pack������List<StorageArea>��װΪVector<Vector>��¼
	public Vector<Vector> pack(List<StorageArea> list) throws SQLException;

	// ���������Ż�ȡ������Ϣ
	public StorageArea getStorageAreaByNum(int num) throws SQLException;

}
