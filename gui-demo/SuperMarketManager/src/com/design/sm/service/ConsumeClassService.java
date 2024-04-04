package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.ConsumeClass;

public interface ConsumeClassService {

	// ��ȡ��ǰ������������ѵȼ�
	public List<ConsumeClass> findAllConsumeClassList() throws SQLException;

	// ��List<ConsumeClass>���ݷ�װΪVector<Vector>����
	public Vector<Vector> pack(List<ConsumeClass> list) throws SQLException;

	// ����idɾ�����ѵȼ���Ϣ
	public void deleteConsumeClass(String id) throws SQLException;

	// ����id�޸����ѵȼ���Ϣ
	public void updateConsumeClass(ConsumeClass cc) throws SQLException;

	// ����id��ȡ���ѵȼ�����
	public Object getConsumeClassNameById(int id) throws SQLException;

	// ����id��ȡ���ѵȼ���Ϣ
	public ConsumeClass getConsumeClassById(int id) throws SQLException;

}
