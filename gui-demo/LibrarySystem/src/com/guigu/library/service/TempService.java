package com.guigu.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.model.Temp;

public interface TempService {

	// ����ղؼ�¼
	public void addTemp(Temp t) throws SQLException;

	// ���ݼ�¼idɾ���ղؼ�¼
	public void deleteTemp(String id) throws SQLException;

	// ����ָ���Ķ���id�����ղؼ�¼
	public List<Temp> findTempByReaderId(String reader_id) throws SQLException;

	// ��List<Temp>���ͷ�װΪVector<Vector>����
	public Vector<Vector> pack(List<Temp> list) throws SQLException;

	// ����ISBN�Ų����ղؼ�¼(�����ظ�����ղؼ�¼)
	public Temp getTempByISBN(String isbn,String reader_id) throws SQLException;

}
