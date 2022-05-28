package com.guigu.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.model.ReaderClassify;

public interface ReaderClassifyService {
	// �޸Ķ��߷�����Ϣ
	public void updateReaderClassify(ReaderClassify rc) throws SQLException;

	// ��ȡ���ж��߷�����Ϣ
	public List<ReaderClassify> findAllReaderClassify() throws SQLException;

	// ��List<ReaderClassify>��װΪVector<Vector>����
	public Vector<Vector> pack(List<ReaderClassify> list) throws SQLException;

	// ���ݷ����Ż�ȡ��������
	public String getClassifyNameByum(int num) throws SQLException;

	// ���ݶ��߷����Ż�ȡ��ǰ�Ķ��߷�����Ϣ
	public ReaderClassify getReaderClassifyBynum(int num) throws SQLException;

}
