package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.ConsumeClass;

public interface ConsumeClassDAO extends BaseDAO<ConsumeClass>{
	
	// ��ȡ��ǰ������������ѵȼ�
	public List<ConsumeClass> findAllConsumeClass()throws SQLException;
	
	// ����idɾ�����ѵȼ���Ϣ
	public void deleteConsumeClass(String id)throws SQLException;
	
	// ����id�޸����ѵȼ���Ϣ
	public void updateConsumeClass(ConsumeClass cc)throws SQLException;
	
	// ����id��ȡ���ѵȼ�����
	public Object getConsumeClassNameById(int id)throws SQLException;
	
	// ����id��ȡ���ѵȼ���Ϣ
	public ConsumeClass getConsumeClassById(int id)throws SQLException;

}
