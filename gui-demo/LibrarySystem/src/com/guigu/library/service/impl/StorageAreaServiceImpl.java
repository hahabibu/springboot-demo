package com.guigu.library.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.dao.StorageAreaDAO;
import com.guigu.library.dao.impl.StorageAreaDAOImpl;
import com.guigu.library.model.BookClassify;
import com.guigu.library.model.StorageArea;
import com.guigu.library.service.StorageAreaService;

public class StorageAreaServiceImpl implements StorageAreaService {

	private StorageAreaDAO storageAreaDAO = new StorageAreaDAOImpl();

	@Override
	public List<StorageArea> findAllStorageArea() throws SQLException {
		return storageAreaDAO.findAllStorageArea();
	}

	@Override
	public Vector<Vector> pack(List<StorageArea> list) throws SQLException {
		// �ֶ���װ���ҵ���������Ϣ
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (StorageArea obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 5; i++) {
					temp.add(obj.getArea_num());// ������
					temp.add(obj.getArea_name());// ��������
					temp.add(obj.getArea_descr());// ��������
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public StorageArea getStorageAreaByNum(int num) throws SQLException {
		return storageAreaDAO.getStorageAreaByNum(num);
	}

}
