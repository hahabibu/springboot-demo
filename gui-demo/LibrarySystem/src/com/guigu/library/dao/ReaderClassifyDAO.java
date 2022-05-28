package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.ReaderClassify;

public interface ReaderClassifyDAO extends BaseDAO<ReaderClassify>{
	// �޸Ķ��߷�����Ϣ
	public void updateReaderClassify(ReaderClassify rc)throws SQLException;
	// ��ȡ���ж��߷�����Ϣ
	public List<ReaderClassify> findAllReaderClassify()throws SQLException;
	// ���ݷ����Ż�ȡ��������
	public Object getClassifyNameByum(int num)throws SQLException;
	// ���ݶ��߷����Ż�ȡ��ǰ�Ķ��߷�����Ϣ
	public ReaderClassify getReaderClassifyBynum(int num)throws SQLException;
}
