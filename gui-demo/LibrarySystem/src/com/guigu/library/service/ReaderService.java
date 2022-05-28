package com.guigu.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.model.Reader;
import com.guigu.library.model.Users;

public interface ReaderService {

	// ��Ӷ�����Ϣ
	public void addReader(Reader r) throws SQLException;

	// �޸Ķ�����Ϣ
	public void updateReader(Reader r) throws SQLException;

	// ����idɾ��������Ϣ
	public void deleteReader(String id) throws SQLException;

	// ����id��ȡ����������Ϣ
	public Reader getReaderById(String id) throws SQLException;

	// ����ָ����������ȡ������Ϣ
	public List<Reader> findReaderUnion(int choose, Object... args)
			throws SQLException;

	// ��List<Reader>��װΪVector<Vector>����
	public Vector<Vector> pack(List<Reader> list) throws SQLException;

	// ���ݵ�ǰ��¼���˺�id��ȡ��Ӧ�Ķ��������Ϣ
	public Reader getReaderByUserId(String user_id) throws SQLException;
	
	// ���ݶ��ߵ��������ȡ���ߵ������Ϣ
	public Reader getReaderByBarcode(String barcode)throws SQLException;

}
